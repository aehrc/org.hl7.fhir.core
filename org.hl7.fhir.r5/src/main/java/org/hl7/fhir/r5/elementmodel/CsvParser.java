package org.hl7.fhir.r5.elementmodel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.hl7.fhir.exceptions.DefinitionException;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.exceptions.FHIRFormatError;
import org.hl7.fhir.r5.conformance.profile.ProfileUtilities;
import org.hl7.fhir.r5.context.ContextUtilities;
import org.hl7.fhir.r5.context.IWorkerContext;
import org.hl7.fhir.r5.formats.IParser.OutputStyle;
import org.hl7.fhir.r5.model.ElementDefinition;
import org.hl7.fhir.r5.model.StructureDefinition;
import org.hl7.fhir.r5.model.StructureDefinition.StructureDefinitionDifferentialComponent;
import org.hl7.fhir.r5.model.StructureDefinition.StructureDefinitionKind;
import org.hl7.fhir.r5.model.StructureDefinition.TypeDerivationRule;
import org.hl7.fhir.r5.utils.FHIRPathEngine;

public class CsvParser extends ParserBase {
	public static final String CSV_SD_URI = "http://example.com/CSV";

	final private static String CSV = "CSV";
	final private static String RECORD = "CSV.record";
	final private static String PATH = "CSV.record.";

	private final ProfileUtilities profileUtilities;

	public CsvParser(IWorkerContext context) {
		super(context);

		this.profileUtilities = new ProfileUtilities(this.context, null, null, new FHIRPathEngine(context));
	}

        @Override
        public List<NamedElement> parse(InputStream stream)
                throws IOException, FHIRFormatError, DefinitionException, FHIRException {
            final List<NamedElement> res = new ArrayList<>();

            final BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            try (final CSVParser parser = CSVParser.parse(reader, CSVFormat.RFC4180.withFirstRecordAsHeader())) {
        	    final List<String> hds = parser.getHeaderNames();
        	    if (hds.size() == 0) {
        		    throw new RuntimeException("No headers detected");
        	    }

        	    final String[] headers = hds.toArray(new String[hds.size()]);
        	    sanitize(headers);


        	    final Map<String,String> data = new HashMap<>();

        	    final StructureDefinition sd = createSD(headers);

        	    context.cacheResource(sd);

        	    final String name = sd.getType();

        	    final ElementDefinition definition = sd.getSnapshot().getElement().get(0);

        	    final Element baseElement = new Element(name, new Property(context, definition, sd, profileUtilities));
        	    baseElement.setType(name);
        	    baseElement.setPath(baseElement.fhirTypeRoot());

        	    parser.forEach(r -> {
        		    for (int i = 0; i < r.size(); i++) {
        			    final String f = r.get(i);
        			    if (!f.isBlank()) {
        				    data.put(headers[i], f);
        			    }
        		    }

        		    addRecord(baseElement, data);
        	    });

        	    res.add(new NamedElement(null, baseElement));
            }

//            final org.hl7.fhir.r5.elementmodel.JsonParser p = new org.hl7.fhir.r5.elementmodel.JsonParser(context);	// FIXME DELETE
//            p.compose(baseElement, System.out, OutputStyle.PRETTY, "");	// FIXME DELETE

            return res;
        }

	private void addRecord(final Element baseElement, final Map<String, String> data) {
		final Property recordProperty = baseElement.getProperty().getChild("record");
		final Element record = new Element("record", recordProperty);
		record.setPath(RECORD);
		baseElement.getChildren().add(record);

                addChildren(RECORD, data, record);
	}

        private static String truncate(String str, int len, String alt) {
        	if (str.length() > len) {
        		return alt;
        	} else {
        		return str;
        	}
        }

        private void sanitize(String[] headers) {
        	final Set<String> unique = new HashSet<>();
        	for (int i = 0; i < headers.length; i++) {
        		final String field = "field_" + i;
        		final String h = truncate(headers[i].trim().replaceAll("[^\\w]+", "_"), 64, field);

        		if (unique.contains(h)) {
				headers[i] = field;
        		} else {
				headers[i] = h;
				unique.add(h);
        		}
        	}
        }

	private void addChildren(String path, Map<String, String> data, Element element) {
		final List<Property> properties = element.getProperty().getChildProperties(element.getName(), null);
		final Set<String> processed = new HashSet<String>();
		processed.add("resourceType");
		final Map<String, Entry<String, String>> recognisedChildren = new HashMap<>();
		final Set<String> unique = new HashSet<>();
		for (final Entry<String, String> p : data.entrySet()) {
			unique.add(p.getKey());
			recognisedChildren.put(p.getKey(), p);
		}

		for (final Property property : properties) {
			final String name = property.getName();
			if (data.containsKey(name)) {
				final String fpath = element.getPath()+"."+name;
				final Element n = new Element(name, property);
				n.setPath(fpath);
				element.getChildren().add(n);
				n.setValue(data.get(name));
			}
		}
	}

	private StructureDefinition createSD(final String[] headers) {
		final StructureDefinition sd = createStructureDefinitionStub();

		final StructureDefinitionDifferentialComponent diff = sd.getDifferential();
		{
			final ElementDefinition root = diff.addElement();
			root.setId(CSV);
			root.setPath(CSV);
			final ElementDefinition record = diff.addElement();
			record.setId(RECORD);
			record.setPath(RECORD);
			record.addType().setCode("BackboneElement");
			record.setMin(1);
			record.setMax("*");
		}

		for (final String h: headers) {
			final ElementDefinition elt = diff.addElement();
			elt.setId(PATH + h);
			elt.setPath(PATH + h);
			elt.addType().setCode("string");
			elt.setMin(0);
			elt.setMax("1");
		}

		new ContextUtilities(context).generateSnapshot(sd, true);

		return sd;
	}

	public static StructureDefinition createStructureDefinitionStub() {
		final StructureDefinition sd = new StructureDefinition();
		sd.setBaseDefinition("http://hl7.org/fhir/StructureDefinition/Element");
		sd.setUrl(CSV_SD_URI);
		sd.setName(CSV);
		sd.setType(CSV);
		sd.setDerivation(TypeDerivationRule.SPECIALIZATION);
		sd.setKind(StructureDefinitionKind.LOGICAL);
		sd.setAbstract(false);
		return sd;
	}

	@Override
	public void compose(Element e, OutputStream destination, OutputStyle style, String base)
			throws FHIRException, IOException {
		// TODO Auto-generated method stub

	}

}

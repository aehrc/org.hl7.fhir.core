package org.hl7.fhir.r5.elementmodel;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
  public static final String CSV_SD_URI = "http://hl7.org/fhir/tools/StructureDefinition/CSV";

  final private static String CSV = "CSV";
  final private static String RECORD = "CSV.record";

  private final Map<String, Property> props = new HashMap<>();

  private final ProfileUtilities profileUtilities;

  public CsvParser(IWorkerContext context) {
    super(context);

    this.profileUtilities = new ProfileUtilities(this.context, null, null, new FHIRPathEngine(context));
  }

  @Override
  public List<NamedElement> parse(final InputStream stream)
      throws IOException, FHIRFormatError, DefinitionException, FHIRException {
    final List<NamedElement> res = new ArrayList<>();

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    stream.transferTo(baos);

    InputStream firstClone = new ByteArrayInputStream(baos.toByteArray()); 
    InputStream secondClone = new ByteArrayInputStream(baos.toByteArray());

    final BufferedReader dectectReader = new BufferedReader(new InputStreamReader(firstClone));

    final String firstLine = dectectReader.readLine();
    int countSemicolon = 0;
    int countComma = 0;
    char delimiter = ',';
    if (firstLine != null) {
      for (int i = 0; i < firstLine.length(); i++) {
        final char c = firstLine.charAt(i);
        if (c == ';') {
          ++countSemicolon;
        }
        if (c == ',') {
          ++countComma;
        }
      }
    }
 
    if (countSemicolon > countComma) {
      delimiter = ';';
    }
 
    final BufferedReader reader = new BufferedReader(new InputStreamReader(secondClone));

    try (final CSVParser parser = CSVParser.parse(reader, CSVFormat.RFC4180.withFirstRecordAsHeader().withDelimiter(delimiter))) {
      final String[] headers;

      final ElementDefinition recordDefinition;

      if (logical == null || CSV_SD_URI.equals(logical.getUrl())) {
        final List<String> names = parser.getHeaderNames();

        headers = names.toArray(new String[names.size()]);
        sanitize(headers);

        final StructureDefinition sd = createSD(headers);
        setLogical(sd);
        recordDefinition = sd.getDifferential().getElement().get(1);

        context.cacheResource(sd);
      } else {
        recordDefinition = logical.getDifferential().getElement().get(1);

        final List<String> names = new ArrayList<>();
        final List<ElementDefinition> elements = logical.getDifferential().getElement();
        final String path = recordDefinition.getPath();
        for (final ElementDefinition elt: elements) {
          final String name = elt.getName();
          if (elt.getPath().equals(path + "." + name)) {
            names.add(name);
            props.put(name, new Property(context, elt, logical));
          }
        }
        headers = names.toArray(new String[names.size()]);
      }

      final String name = logical.getType();

      final ElementDefinition rootDefinition = logical.getSnapshot().getElementFirstRep();
      final Element rootElement = new Element(name, new Property(context, rootDefinition, logical, profileUtilities));
      rootElement.setType(name);
      rootElement.setPath(rootElement.fhirTypeRoot());

      parser.forEach(r -> {
	final Map<String,String> data = new HashMap<>();

        for (int i = 0; i < r.size(); i++) {
          final String f = r.get(i);
          if (!f.isBlank()) {
            data.put(headers[i], f);
          }
        }

        addChildren(createRecord(rootElement, recordDefinition), data);
      });

      res.add(new NamedElement(null, rootElement));
    }

    return res;
  }

  private Element createRecord(final Element rootElement, final ElementDefinition recordDefinition) {
    final Element recordElement = new Element(recordDefinition.getName(), new Property(context, recordDefinition, logical, profileUtilities));
    rootElement.getChildren().add(recordElement);
    return recordElement;
  }

  private void addChildren(final Element element, final Map<String, String> data) {
    data.entrySet().forEach(e -> {
      final String name = e.getKey();
      final String fpath = element.getPath()+"."+name;
      final Element n = new Element(name, props.get(name));
      n.setPath(fpath);
      element.getChildren().add(n);
      n.setValue(data.get(name));
    });
  }

  private static String truncate(final String str, final int len, final String alt) {
    if (str.length() > len) {
      return alt;
    } else {
      return str;
    }
  }

  private void sanitize(final String[] headers) {
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
      final String fpath = RECORD + "." + h;
      elt.setId(fpath);
      elt.setPath(fpath);
      elt.addType().setCode("string");
      elt.setMin(0);
      elt.setMax("1");

      props.put(h, new Property(context, elt, logical));
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
  public void compose(final Element e, final OutputStream destination, final OutputStyle style, final String base)
      throws FHIRException, IOException {
    throw new UnsupportedOperationException("Not Implemented");
  }

}

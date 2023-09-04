package org.hl7.fhir.r5.profilemodel.gen;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.ArrayList;
import java.util.Date;

import org.hl7.fhir.r5.context.IWorkerContext;
import org.hl7.fhir.r5.model.CodeableConcept;
import org.hl7.fhir.r5.model.Identifier;
import org.hl7.fhir.r5.model.Observation;
import org.hl7.fhir.r5.model.StructureDefinition;
import org.hl7.fhir.r5.model.StructureDefinition.StructureDefinitionKind;
import org.hl7.fhir.r5.profilemodel.PEBuilder;
import org.hl7.fhir.r5.profilemodel.PEBuilder.PEElementPropertiesPolicy;
import org.hl7.fhir.r5.profilemodel.gen.PECodeGenerator.ExtensionPolicy;
import org.hl7.fhir.r5.profilemodel.PEDefinition;
import org.hl7.fhir.r5.profilemodel.PEInstance;
import org.hl7.fhir.r5.profilemodel.PEType;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;

public class PECodeGenerator {

  public enum ExtensionPolicy {
    None, Complexes, Primitives;
  }
  public static final SimpleDateFormat DATE_FORMAT() {
    return new SimpleDateFormat("EEE, MMM d, yyyy HH:mmZ", new Locale("en", "US"));
  }
  private class PEGenClass {
    private String name;
    private String base;
    private String doco;
    private String url;
    private boolean isResource;
    private StringBuilder fields = new StringBuilder();
    private StringBuilder load = new StringBuilder();
    private StringBuilder save = new StringBuilder();
    private StringBuilder clear = new StringBuilder();
    private StringBuilder copy = new StringBuilder();
    private StringBuilder accessors = new StringBuilder();
    private StringBuilder hash = new StringBuilder();

    public void genId() {
      if (isResource) {
        genField(true, "id", "String", "id", "", false, "");
        genAccessors(true, false, "id", "String", "", "String", "String", "Id", "Ids", false, "", false);   
        genLoad(true, false, "id", "IdType", "", "String", "String", "Id", "Ids", false, false, null);
        genClear(false, "id");
      }
    }
    public void write(StringBuilder b) {
      w(b);
      w(b, "// Generated by the HAPI Java Profile Generator, "+DATE_FORMAT().format(new Date()));      
      w(b);      
      jdoc(b, doco, 0, true);
      w(b, "public class "+name+" extends PEGeneratedBase {");
      w(b);
      if (url != null) {
        w(b, "  private static final String CANONICAL_URL = \""+url+"\";");
        w(b);        
      }
      w(b, fields.toString());
      jdoc(b, "Parameter-less constructor. If you use this, the fixed values won't be filled out - they'll be missing. They'll be filled in if/when you call build, so they won't be missing from the resource, only from this particular object model", 2, true);
      w(b, "  public "+name+"() {");
      w(b, "    // todo");
      w(b, "  }");
      w(b);
      if (isResource) {
        jdoc(b, "Construct an instance of the object, and fill out all the fixed values ", 2, true);
        w(b, "  public "+name+"(IWorkerContext context) {");
        w(b, "    workerContext = context;");
        w(b, "    PEBuilder builder = new PEBuilder(context, PEElementPropertiesPolicy.EXTENSION, true);");
        w(b, "    PEInstance src = builder.buildPEInstance(CANONICAL_URL, builder.createResource(CANONICAL_URL, false));");
        w(b, "    load(src);");
        w(b, "  }");
        w(b);
        jdoc(b, "Populate an instance of the object based on this source object ", 2, true);
        w(b, "  public static "+name+" fromSource(IWorkerContext context, "+base+" source) {");
        w(b, "    "+name+" theThing = new "+name+"();");
        w(b, "    theThing.workerContext = context;");
        w(b, "    PEBuilder builder = new PEBuilder(context, PEElementPropertiesPolicy.EXTENSION, true);");
        w(b, "    PEInstance src = builder.buildPEInstance(CANONICAL_URL, source);");
        w(b, "    theThing.load(src);");
        w(b, "    return theThing;");
        w(b, "  }");  
        w(b);
      } else {
        jdoc(b, "Used when loading other models ", 2, true);
        w(b, "  public static "+name+" fromSource(PEInstance source) {");
        w(b, "    "+name+" theThing = new "+name+"();");
        w(b, "    theThing.workerContext = source.getContext();");
        w(b, "    theThing.load(source);");
        w(b, "    return theThing;");
        w(b, "  }");  
      }
      w(b);
      w(b, "  public void load(PEInstance src) {");
      w(b, "    clear();");
      w(b, load.toString());
      w(b, "  }");  
      w(b);

      if (isResource) {
        jdoc(b, "Build an instance of the object based on this source object ", 2, true);
        w(b, "  public "+base+" build(IWorkerContext context) {");
        w(b, "    workerContext = context;");
        w(b, "    "+base+" theThing = new "+base+"();");
        w(b, "    PEBuilder builder = new PEBuilder(context, PEElementPropertiesPolicy.EXTENSION, true);");
        w(b, "    PEInstance tgt = builder.buildPEInstance(CANONICAL_URL, theThing);");      
        w(b, "    save(tgt, false);");
        w(b, "    return theThing;");
        w(b, "  }");
        w(b);
        jdoc(b, "Save this profile class into an existing resource (overwriting enything that exists in the profile) ", 2, true);
        w(b, "  public void save(IWorkerContext context, "+base+" dest, boolean nulls) {");
        w(b, "    workerContext = context;");
        w(b, "    PEBuilder builder = new PEBuilder(context, PEElementPropertiesPolicy.EXTENSION, true);");
        w(b, "    PEInstance tgt = builder.buildPEInstance(CANONICAL_URL, dest);");
        w(b, "    save(tgt, nulls);");
        w(b, "  }");
        w(b);
      }
      w(b, "  public void save(PEInstance tgt, boolean nulls) {");
      w(b, save.toString());
      w(b, "  }");  
      w(b);
      w(b, accessors.toString());
      w(b);
      w(b, "  public void clear() {");
      w(b, clear.toString());
      w(b, "  }");  
      w(b);
      w(b, "}");  
    }

    private void defineField(PEDefinition source, PEDefinition field) {
      if (field.types().size() == 1) {
        StructureDefinition sd = workerContext.fetchTypeDefinition(field.types().get(0).getUrl());
        if (sd != null) {
          boolean isPrim = sd.getKind() == StructureDefinitionKind.PRIMITIVETYPE;
          boolean isAbstract = sd.getAbstract();
          String name = field.name().replace("[x]", "");
          String sname = name;
          String type = null;
          String init = "";
          String ptype = type;
          if (isPrim) {
            // todo: are we extension-less?
            type = Utilities.capitalize(field.types().get(0).getName()+"Type");
            ptype = getPrimitiveType(sd);
          } else {
            type = field.types().get(0).getName();
          }
          String ltype = type;
          if (field.isList()) {
            ltype = "List<"+type+">";
            init = "new ArrayList<>()";
            if (!Utilities.existsInList(name, "contained")) {
              name = Utilities.pluralize(name, 2);
            }
          }
          String cname = Utilities.capitalize(name);
          String csname = Utilities.capitalize(sname);
          String nn = field.min() == 1 ? "// @NotNull" : "";
          genField(isPrim, name, ptype, ltype, nn, field.isList(), field.shortDocumentation());
          genAccessors(isPrim, isAbstract, name, type, init, ptype, ltype, cname, csname, field.isList(), field.documentation(), field.fixedValue());   
          genLoad(isPrim, isAbstract, name, type, init, ptype, ltype, cname, csname, field.isList(), field.fixedValue(), field.types().get(0));
          genClear(field.isList(), name);
        }
      } else {
        // ignoring polymorphics for now
      }

    }

    private void genClear(boolean list, String name) {
      w(clear, "    "+name+" = null;");
    }
    private void genLoad(boolean isPrim, boolean isAbstract, String name, String type, String init, String ptype, String ltype, String cname, String csname, boolean isList, boolean isFixed, PEType typeInfo) {
      if (isList) {
        w(load, "    for (PEInstance item : src.children(\""+name+"\")) {");
        w(load, "      "+name+".add(("+type+") item.asDataType());");
        w(load, "    }");
      } else if (isPrim) {
        w(load, "    if (src.hasChild(\""+name+"\")) {");
        w(load, "      "+name+" = (("+type+") src.child(\""+name+"\").asDataType()).getValue();");
        w(load, "    }");      
      } else if (typeInfo != null && typeInfo.getUrl() != null && !typeInfo.getUrl().startsWith("http://hl7.org/fhir/StructureDefinition")) {
        w(load, "    if (src.hasChild(\""+name+"\")) {");
        w(load, "      "+name+" = "+type+".fromSource(src.child(\""+name+"\"));");
        w(load, "    }");
      } else {
        w(load, "    if (src.hasChild(\""+name+"\")) {");
        w(load, "      "+name+" = ("+type+") src.child(\""+name+"\").asDataType();");
        w(load, "    }");
      }
    }

    private void genAccessors(boolean isPrim, boolean isAbstract, String name, String type, String init, String ptype, String ltype, String cname, String csname, boolean isList, String shortDoco, boolean isFixed) {
      jdoc(accessors, doco, 2, true);
      if (isPrim && extensionPolicy != ExtensionPolicy.Primitives && !isList) {
        w(accessors, "  public "+ptype+" get"+cname+"() {");
        w(accessors, "    return "+name+";");
        w(accessors, "  }");
        w(accessors);
        w(accessors, "  public "+this.name+" set"+cname+"("+ptype+" value) {");
        w(accessors, "    this."+name+" = value;");
        w(accessors, "    return this;");
        w(accessors, "  }");
        w(accessors);
        w(accessors, "  public boolean has"+cname+"() {");
        w(accessors, "    return "+name+" != null;");
        w(accessors, "  }");
      } else {
        if (isPrim && !isList) {
          w(accessors, "  public "+ptype+" get"+cname+"() {");
          w(accessors, "    if ("+name+" == null) { "+name+" = new "+type+"(); }");
          w(accessors, "    return "+name+".getValue();");
          w(accessors, "  }");
          w(accessors, "  public "+ltype+" get"+cname+"Element() {");
        } else if (isAbstract && !isList) {
          w(accessors, "  public @Nullable "+ltype+" get"+cname+"() { // "+ltype+" is abstract ");
        } else {
          w(accessors, "  public "+ltype+" get"+cname+"() {");
        }
        if (isList) {
          w(accessors, "    if ("+name+" == null) { "+name+" = "+init+"; }");
        } else if (!isAbstract) {
          w(accessors, "    if ("+name+" == null) { "+name+" = new "+type+"(); }");
        }
        w(accessors, "    return "+name+";");
        w(accessors, "  }");
        w(accessors);
        if (isList) {
          w(accessors, "  public boolean has"+cname+"() {");
          w(accessors, "    return "+name+" != null && !"+name+".isEmpty();");
          w(accessors, "  }");
          w(accessors);
          if (!isAbstract) {
            w(accessors, "  public "+type+" add"+csname+"() {");
            w(accessors, "    "+type+" theThing = new "+type+"();");
            w(accessors, "    get"+cname+"().add(theThing);");
            w(accessors, "    return theThing;");
            w(accessors, "  }");
            w(accessors); 
          }
          w(accessors, "  public boolean has"+csname+"("+type+" item) {");
          w(accessors, "    return has"+cname+"() && "+name+".contains(item);");
          w(accessors, "  }");
          w(accessors);        
          w(accessors, "  public void remove"+csname+"("+type+" item) {");
          w(accessors, "    if (has"+csname+"(item)) {");
          w(accessors, "      "+name+".remove(item);");
          w(accessors, "    }");
          w(accessors, "  }");
          w(accessors);        
        } else if (isPrim) {
          if (!isFixed) {
            w(accessors, "  public "+this.name+" set"+cname+"("+ptype+" value) {");
            w(accessors, "    if ("+name+" == null) { "+name+" = new "+type+"(); }");
            w(accessors, "    "+name+".setValue(value);");
            w(accessors, "    return this;");
            w(accessors, "  }");
            w(accessors, "  public "+this.name+" set"+cname+"Element("+type+" value) {");
            w(accessors, "    this."+name+" = value;");
            w(accessors, "    return this;");
            w(accessors, "  }");
          }
          w(accessors, "  public boolean has"+cname+"() {");
          w(accessors, "    return "+name+" != null && "+name+".hasValue();");
          w(accessors, "  }");
          w(accessors); 
        } else {
          if (!isFixed) {
            w(accessors, "  public "+this.name+" set"+cname+"("+type+" value) {");
            w(accessors, "    this."+name+" = value;");
            w(accessors, "    return this;");
            w(accessors, "  }");
          }
          w(accessors, "  public boolean has"+cname+"() {");
          w(accessors, "    return "+name+" != null;");
          w(accessors, "  }");
        }
      }
      w(accessors);
    }

    private void genField(boolean isPrim, String name, String ptype, String ltype, String nn, boolean isList, String shortDoco) {
      // jdoc(fields, field.documentation(), 2, true);
      if (isPrim && extensionPolicy != ExtensionPolicy.Primitives && !isList) {
        w(fields, "  private "+ptype+" "+name+";"+nn+"  // "+shortDoco);
      } else {
        w(fields, "  private "+ltype+" "+name+";"+nn+"  // "+shortDoco);             
      }
    }
  }

  private String folder;
  private IWorkerContext workerContext;
  private String canonical;
  private String pkgName;

  // options:
  private ExtensionPolicy extensionPolicy;
  private boolean narrative;
  private boolean contained;
  private boolean meta;
  private String language;
  private boolean keyELementsOnly;

  public PECodeGenerator(IWorkerContext workerContext) {
    super();
    this.workerContext = workerContext;
  }

  public String getFolder() {
    return folder;
  }


  public void setFolder(String folder) {
    this.folder = folder;
  }


  public String getCanonical() {
    return canonical;
  }

  public void setCanonical(String canonical) {
    this.canonical = canonical;
  }


  public String getPkgName() {
    return pkgName;
  }

  public void setPkgName(String pkgName) {
    this.pkgName = pkgName;
  }

  public ExtensionPolicy getExtensionPolicy() {
    return extensionPolicy;
  }

  public void setExtensionPolicy(ExtensionPolicy extensionPolicy) {
    this.extensionPolicy = extensionPolicy;
  }

  public boolean isNarrative() {
    return narrative;
  }

  public void setNarrative(boolean narrative) {
    this.narrative = narrative;
  }

  public boolean isMeta() {
    return meta;
  }

  public void setMeta(boolean meta) {
    this.meta = meta;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public boolean isKeyELementsOnly() {
    return keyELementsOnly;
  }

  public void setKeyELementsOnly(boolean keyELementsOnly) {
    this.keyELementsOnly = keyELementsOnly;
  }

  public boolean isContained() {
    return contained;
  }

  public void setContained(boolean contained) {
    this.contained = contained;
  }

  private StringBuilder imports = new StringBuilder();

  /**
   * @throws IOException 
   * 
   */
  public void execute() throws IOException {
    PEDefinition source = new PEBuilder(workerContext, PEElementPropertiesPolicy.EXTENSION, true).buildPEDefinition(canonical);
    w(imports, "import java.util.List;");
    w(imports, "import java.util.ArrayList;");
    w(imports, "import javax.annotation.Nullable;");
    w(imports, "import java.util.Date;\r\n");
    w(imports);
    w(imports, "import org.hl7.fhir.r5.context.IWorkerContext;");
    w(imports, "import org.hl7.fhir.r5.model.*;");
    w(imports, "import org.hl7.fhir.r5.profilemodel.PEBuilder;");
    w(imports, "import org.hl7.fhir.r5.profilemodel.PEInstance;");
    w(imports, "import org.hl7.fhir.r5.profilemodel.PEBuilder.PEElementPropertiesPolicy;");
    w(imports, "import org.hl7.fhir.r5.profilemodel.gen.PEGeneratedBase;");
    PEGenClass cls = genClass(source);
    StringBuilder b = new StringBuilder();
    w(b, "package "+pkgName+";");
    w(b);
    if (source.getProfile().hasCopyright()) {
      jdoc(b, source.getProfile().getCopyright(), 0, false);
    }
    w(b, imports.toString());
    cls.write(b);
    TextFile.stringToFile(b.toString(), Utilities.path(folder, cls.name+".java"));
  }

  public void jdoc(StringBuilder b, String doco, int indent, boolean jdoc) {
    if (!Utilities.noString(doco)) {
      String pfx = Utilities.padLeft("", ' ', indent);
      w(b, pfx+"/*"+(jdoc ? "*" : ""));
      for (String line : doco.split("\\R")) {
        for (String nl : naturalLines(line))
          w(b, pfx+" * "+nl);
        w(b, pfx+" *");
      }
      w(b, pfx+" */");
    }    
  }

  private List<String> naturalLines(String line) {
    List<String> lines = new ArrayList<>();
    while (line.length() > 80) {
      int cutpoint = 80;
      while (cutpoint > 0 && line.charAt(cutpoint) != ' ') {
        cutpoint--;
      }
      if (cutpoint == 0) {
        cutpoint = 80;
      } else {
        cutpoint++;
      }
      lines.add(line.substring(0, cutpoint));
      line = line.substring(cutpoint);
    }
    lines.add(line);
    return lines;
  }

  private void w(StringBuilder b) {
    b.append("\r\n");

  }

  private void w(StringBuilder b, String line) {
    b.append(line);
    w(b);    
  }

  private PEGenClass genClass(PEDefinition source) {
    PEGenClass cls = new PEGenClass();
    cls.name = source.getProfile().getName();
    cls.base = source.getProfile().getType();
    cls.doco = source.documentation();
    cls.url = source.getProfile().getVersionedUrl();
    cls.isResource = source.getProfile().getKind() == StructureDefinitionKind.RESOURCE;
    cls.genId();
    for (PEDefinition child : source.children()) {
      if (genForField(source, child)) {
        cls.defineField(source, child);
      }
    }    
    return cls;
  }

  private boolean genForField(PEDefinition source, PEDefinition child) {
    if (child.definition().getBase().getPath().equals("Resource.meta")) {
      return meta;
    }
    if (child.definition().getBase().getPath().equals("DomainResource.text")) {
      return narrative;
    }
    if (child.definition().getBase().getPath().equals("Resource.language")) {
      return language == null;
    }
    if (child.definition().getBase().getPath().endsWith(".extension") || child.definition().getBase().getPath().endsWith(".modifierExtension")) {
      return extensionPolicy == ExtensionPolicy.Complexes;
    }
    if (child.definition().getBase().getPath().equals("DomainResource.contained")) {
      return contained;
    }
    return !keyELementsOnly || (child.isKeyElement());
  }


  private String getPrimitiveType(StructureDefinition sd) {

    if (sd.getType().equals("string"))
      return "String";
    if (sd.getType().equals("code"))
      return "String";
    if (sd.getType().equals("markdown"))
      return "String";
    if (sd.getType().equals("base64Binary"))
      return "byte[]";
    if (sd.getType().equals("uri"))
      return "String";
    if (sd.getType().equals("url"))
      return "String";
    if (sd.getType().equals("canonical"))
      return "String";
    if (sd.getType().equals("oid"))
      return "String";
    if (sd.getType().equals("integer"))
      return "int";
    if (sd.getType().equals("integer64"))
      return "long";
    if (sd.getType().equals("unsignedInt"))
      return "int";
    if (sd.getType().equals("positiveInt"))
      return "int";
    if (sd.getType().equals("boolean"))
      return "boolean";
    if (sd.getType().equals("decimal"))
      return "BigDecimal";
    if (sd.getType().equals("dateTime"))
      return "Date";
    if (sd.getType().equals("date"))
      return "Date";
    if (sd.getType().equals("id"))
      return "String";
    if (sd.getType().equals("instant"))
      return "Date";
    if (sd.getType().equals("time"))
      return "String";

    return "??";
  }

}

package org.hl7.fhir.r5.test.profiles;

import java.util.List;
import java.util.ArrayList;
import javax.annotation.Nullable;
import java.util.Date;


import org.hl7.fhir.r5.context.IWorkerContext;
import org.hl7.fhir.r5.model.*;
import org.hl7.fhir.r5.profilemodel.PEBuilder;
import org.hl7.fhir.r5.profilemodel.PEInstance;
import org.hl7.fhir.r5.profilemodel.PEBuilder.PEElementPropertiesPolicy;
import org.hl7.fhir.r5.profilemodel.gen.PEGeneratedBase;
import org.hl7.fhir.r5.profilemodel.gen.Min;
import org.hl7.fhir.r5.profilemodel.gen.Max;
import org.hl7.fhir.r5.profilemodel.gen.Label;
import org.hl7.fhir.r5.profilemodel.gen.Doco;
import org.hl7.fhir.r5.profilemodel.gen.BindingStrength;
import org.hl7.fhir.r5.profilemodel.gen.ValueSet;
import org.hl7.fhir.r5.profilemodel.gen.MustSupport;
import org.hl7.fhir.r5.profilemodel.gen.Definition;


// Generated by the HAPI Java Profile Generator, 1/11/24, 11:07 pm

/**
 * The definition and characteristics of a medicinal manufactured item, such as a 
 * tablet or capsule, as contained in a packaged medicinal product.
 *
 */
public class ManufacturedItemDefinitionUvEpi extends PEGeneratedBase {

  public static final String CANONICAL_URL = "http://hl7.org/fhir/uv/emedicinal-product-info/StructureDefinition/ManufacturedItemDefinition-uv-epi|1.0.0";

  public enum PublicationStatus {
    DRAFT, // "Draft" = http://hl7.org/fhir/publication-status#draft
    ACTIVE, // "Active" = http://hl7.org/fhir/publication-status#active
    RETIRED, // "Retired" = http://hl7.org/fhir/publication-status#retired
    UNKNOWN; // "Unknown" = http://hl7.org/fhir/publication-status#unknown

    public static PublicationStatus fromCode(String s) {
      switch (s) {
      case "draft": return DRAFT;
      case "active": return ACTIVE;
      case "retired": return RETIRED;
      case "unknown": return UNKNOWN;
      default: return null;
      }
    }

    public static PublicationStatus fromCoding(Coding c) {
      if ("http://hl7.org/fhir/publication-status".equals(c.getSystem()) && "draft".equals(c.getCode())) {
        return DRAFT;
      }
      if ("http://hl7.org/fhir/publication-status".equals(c.getSystem()) && "active".equals(c.getCode())) {
        return ACTIVE;
      }
      if ("http://hl7.org/fhir/publication-status".equals(c.getSystem()) && "retired".equals(c.getCode())) {
        return RETIRED;
      }
      if ("http://hl7.org/fhir/publication-status".equals(c.getSystem()) && "unknown".equals(c.getCode())) {
        return UNKNOWN;
      }
      return null;
    }

    public static PublicationStatus fromCodeableConcept(CodeableConcept cc) {
      for (Coding c : cc.getCoding()) {
        PublicationStatus v = fromCoding(c);
        if (v != null) {
          return v;
        }
      }
      return null;
    }

    public String toDisplay() {
      switch (this) {
      case DRAFT: return "Draft";
      case ACTIVE: return "Active";
      case RETIRED: return "Retired";
      case UNKNOWN: return "Unknown";
      default: return null;
      }
    }

    public String toCode() {
      switch (this) {
      case DRAFT: return "draft";
      case ACTIVE: return "active";
      case RETIRED: return "retired";
      case UNKNOWN: return "unknown";
      default: return null;
      }
    }

    public Coding toCoding() {
      switch (this) {
      case DRAFT: return new Coding().setSystem("http://hl7.org/fhir/publication-status").setCode("draft");
      case ACTIVE: return new Coding().setSystem("http://hl7.org/fhir/publication-status").setCode("active");
      case RETIRED: return new Coding().setSystem("http://hl7.org/fhir/publication-status").setCode("retired");
      case UNKNOWN: return new Coding().setSystem("http://hl7.org/fhir/publication-status").setCode("unknown");
      default: return null;
      }
    }

    public CodeableConcept toCodeableConcept() {
      Coding c = toCoding();
      return c == null ? null : new CodeableConcept().addCoding(c);
    }
  }

  @Min("0") @Max("1") @Doco("")
  private String id;  // 

  @Min("0") @Max("*") @Doco("Additional content defined by implementations")
  @Definition("May be used to represent additional information that is not part of the basic definition of the resource. To make the use of extensions safe and managable, there is a strict set of governance applied to the definition and use of extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the extension.")
  private List<Extension> extensions = new ArrayList<>();  // Additional content defined by implementations

  @Min("0") @Max("*") @Doco("Extensions that cannot be ignored")
  @Definition("May be used to represent additional information that is not part of the basic definition of the resource and that modifies the understanding of the element that contains it and/or the understanding of the containing element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe and managable, there is a strict set of governance applied to the definition and use of extensions. Though any implementer is allowed to define an extension, there is a set of requirements that SHALL be met as part of the definition of the extension. Applications processing a resource are required to check for modifier extensions.\n\nModifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot change the meaning of modifierExtension itself).")
  private List<Extension> modifierExtensions = new ArrayList<>();  // Extensions that cannot be ignored

  @Min("1") @Max("*") @Doco("Unique identifier")
  @Definition("Unique identifier.")
  private List<Identifier> identifiers = new ArrayList<>();// @NotNull  // Unique identifier

  @Min("1") @Max("1") @Doco("draft|active|retired|unknown")
  @BindingStrength("required") @ValueSet("http://hl7.org/fhir/ValueSet/publication-status|5.0.0")
  @Definition("The status of this item. Enables tracking the life-cycle of the content.")
  private PublicationStatus status;// @NotNull  // draft|active|retired|unknown

  @Min("1") @Max("1") @Doco("Dose form as manufactured (before any necessary transformation)")
  @BindingStrength("example") @ValueSet("http://hl7.org/fhir/uv/emedicinal-product-info/ValueSet/manufacturedDoseForm")
  @Definition("Dose form as manufactured and before any transformation into the pharmaceutical product.")
  private CodeableConcept manufacturedDoseForm;// @NotNull  // Dose form as manufactured (before any necessary transformation)

  @Min("0") @Max("*") @Doco("Manufacturer of the item, one of several possible")
  @Definition("Manufacturer of the item, one of several possible.")
  private List<Reference> manufacturers = new ArrayList<>();  // Manufacturer of the item, one of several possible

  @Min("0") @Max("*") @Doco("Allows specifying that an item is on the market for sale, or that it is not available, and the dates and locations associated")
  @Definition("Allows specifying that an item is on the market for sale, or that it is not available, and the dates and locations associated.")
  private List<MarketingStatus> marketingStatuses = new ArrayList<>();  // Allows specifying that an item is on the market for sale, or that it is not available, and the dates and locations associated

  @Min("0") @Max("*") @Doco("General characteristics of this item")
  @Definition("General characteristics of this item.")
  private List<BackboneElement> properties = new ArrayList<>();  // General characteristics of this item

  @Min("0") @Max("*") @Doco("General characteristics of this item")
  @Definition("General characteristics of this item.")
  private List<BackboneElement> colors = new ArrayList<>();  // General characteristics of this item

  @Min("0") @Max("*") @Doco("General characteristics of this item")
  @Definition("General characteristics of this item.")
  private List<BackboneElement> flavors = new ArrayList<>();  // General characteristics of this item

  @Min("0") @Max("*") @Doco("General characteristics of this item")
  @Definition("General characteristics of this item.")
  private List<BackboneElement> scores = new ArrayList<>();  // General characteristics of this item

  @Min("0") @Max("*") @Doco("General characteristics of this item")
  @Definition("General characteristics of this item.")
  private List<BackboneElement> shapes = new ArrayList<>();  // General characteristics of this item

  @Min("0") @Max("*") @Doco("General characteristics of this item")
  @Definition("General characteristics of this item.")
  private List<BackboneElement> surfaceforms = new ArrayList<>();  // General characteristics of this item

  @Min("0") @Max("*") @Doco("General characteristics of this item")
  @Definition("General characteristics of this item.")
  private List<BackboneElement> sizes = new ArrayList<>();  // General characteristics of this item

  @Min("0") @Max("*") @Doco("General characteristics of this item")
  @Definition("General characteristics of this item.")
  private List<BackboneElement> images = new ArrayList<>();  // General characteristics of this item

  @Min("0") @Max("*") @Doco("General characteristics of this item")
  @Definition("General characteristics of this item.")
  private List<BackboneElement> imprints = new ArrayList<>();  // General characteristics of this item

  @Min("0") @Max("*") @Doco("Physical parts of the manufactured item, that it is intrisically made from. This is distinct from the ingredients that are part of its chemical makeup")
  @Definition("Physical parts of the manufactured item, that it is intrisically made from. This is distinct from the ingredients that are part of its chemical makeup.")
  private List<BackboneElement> components = new ArrayList<>();  // Physical parts of the manufactured item, that it is intrisically made from. This is distinct from the ingredients that are part of its chemical makeup


  /**
   * Parameter-less constructor.
   *
   */
  public ManufacturedItemDefinitionUvEpi() {
  }

  /**
   * Construct an instance of the object, and fill out all the fixed values 
   *
   */
  public ManufacturedItemDefinitionUvEpi(IWorkerContext context) {
    workerContext = context;
    PEBuilder builder = new PEBuilder(context, PEElementPropertiesPolicy.EXTENSION, true);
    PEInstance src = builder.buildPEInstance(CANONICAL_URL, builder.createResource(CANONICAL_URL, false));
    load(src);
  }

  /**
   * Populate an instance of the object based on this source object 
   *
   */
  public static ManufacturedItemDefinitionUvEpi fromSource(IWorkerContext context, ManufacturedItemDefinition source) {
    ManufacturedItemDefinitionUvEpi theThing = new ManufacturedItemDefinitionUvEpi();
    theThing.workerContext = context;
    PEBuilder builder = new PEBuilder(context, PEElementPropertiesPolicy.EXTENSION, true);
    PEInstance src = builder.buildPEInstance(CANONICAL_URL, source);
    theThing.load(src);
    return theThing;
  }


  public void load(PEInstance src) {
    clear();
    if (src.hasChild("id")) {
      id = ((IdType) src.child("id").asDataType()).getValue();
    }
    for (PEInstance item : src.children("extension")) {
      extensions.add((Extension) item.asDataType());
    }
    for (PEInstance item : src.children("modifierExtension")) {
      modifierExtensions.add((Extension) item.asDataType());
    }
    for (PEInstance item : src.children("identifier")) {
      identifiers.add((Identifier) item.asDataType());
    }
    if (src.hasChild("status")) {
      status = PublicationStatus.fromCode(src.child("status").asDataType().primitiveValue());
    }
    if (src.hasChild("manufacturedDoseForm")) {
      manufacturedDoseForm = (CodeableConcept) src.child("manufacturedDoseForm").asDataType();
    }
    for (PEInstance item : src.children("manufacturer")) {
      manufacturers.add((Reference) item.asDataType());
    }
    for (PEInstance item : src.children("marketingStatus")) {
      marketingStatuses.add((MarketingStatus) item.asDataType());
    }
    for (PEInstance item : src.children("property")) {
      properties.add((BackboneElement) item.asElement());
    }
    for (PEInstance item : src.children("color")) {
      colors.add((BackboneElement) item.asElement());
    }
    for (PEInstance item : src.children("flavor")) {
      flavors.add((BackboneElement) item.asElement());
    }
    for (PEInstance item : src.children("score")) {
      scores.add((BackboneElement) item.asElement());
    }
    for (PEInstance item : src.children("shape")) {
      shapes.add((BackboneElement) item.asElement());
    }
    for (PEInstance item : src.children("surfaceform")) {
      surfaceforms.add((BackboneElement) item.asElement());
    }
    for (PEInstance item : src.children("size")) {
      sizes.add((BackboneElement) item.asElement());
    }
    for (PEInstance item : src.children("image")) {
      images.add((BackboneElement) item.asElement());
    }
    for (PEInstance item : src.children("imprint")) {
      imprints.add((BackboneElement) item.asElement());
    }
    for (PEInstance item : src.children("component")) {
      components.add((BackboneElement) item.asElement());
    }

  }

  /**
   * Build an instance of the object based on this source object 
   *
   */
  public ManufacturedItemDefinition build(IWorkerContext context) {
    workerContext = context;
    ManufacturedItemDefinition theThing = new ManufacturedItemDefinition();
    PEBuilder builder = new PEBuilder(context, PEElementPropertiesPolicy.EXTENSION, true);
    PEInstance tgt = builder.buildPEInstance(CANONICAL_URL, theThing);
    save(tgt, false);
    return theThing;
  }

  /**
   * Save this profile class into an existing resource (overwriting anything that 
   * exists in the profile) 
   *
   */
  public void save(IWorkerContext context, ManufacturedItemDefinition dest, boolean nulls) {
    workerContext = context;
    PEBuilder builder = new PEBuilder(context, PEElementPropertiesPolicy.EXTENSION, true);
    PEInstance tgt = builder.buildPEInstance(CANONICAL_URL, dest);
    save(tgt, nulls);
  }

  public void save(PEInstance tgt, boolean nulls) {
    tgt.clear("id");
    if (id != null) {
      tgt.makeChild("id").data().setProperty("value", new IdType(id));
    }
    tgt.clear("extension");
    for (Extension item : extensions) {
      tgt.addChild("extension", item);
    }
    tgt.clear("modifierExtension");
    for (Extension item : modifierExtensions) {
      tgt.addChild("modifierExtension", item);
    }
    tgt.clear("identifier");
    for (Identifier item : identifiers) {
      tgt.addChild("identifier", item);
    }
    tgt.clear("status");
    if (status != null) {
      tgt.addChild("status", status.toCode());
    }
    tgt.clear("manufacturedDoseForm");
    if (manufacturedDoseForm != null) {
      tgt.addChild("manufacturedDoseForm", manufacturedDoseForm);
    }
    tgt.clear("manufacturer");
    for (Reference item : manufacturers) {
      tgt.addChild("manufacturer", item);
    }
    tgt.clear("marketingStatus");
    for (MarketingStatus item : marketingStatuses) {
      tgt.addChild("marketingStatus", item);
    }
    tgt.clear("property");
    for (BackboneElement item : properties) {
      tgt.addChild("property", item);
    }
    tgt.clear("color");
    for (BackboneElement item : colors) {
      tgt.addChild("color", item);
    }
    tgt.clear("flavor");
    for (BackboneElement item : flavors) {
      tgt.addChild("flavor", item);
    }
    tgt.clear("score");
    for (BackboneElement item : scores) {
      tgt.addChild("score", item);
    }
    tgt.clear("shape");
    for (BackboneElement item : shapes) {
      tgt.addChild("shape", item);
    }
    tgt.clear("surfaceform");
    for (BackboneElement item : surfaceforms) {
      tgt.addChild("surfaceform", item);
    }
    tgt.clear("size");
    for (BackboneElement item : sizes) {
      tgt.addChild("size", item);
    }
    tgt.clear("image");
    for (BackboneElement item : images) {
      tgt.addChild("image", item);
    }
    tgt.clear("imprint");
    for (BackboneElement item : imprints) {
      tgt.addChild("imprint", item);
    }
    tgt.clear("component");
    for (BackboneElement item : components) {
      tgt.addChild("component", item);
    }

  }

  /**
   * The definition and characteristics of a medicinal manufactured item, such as a 
   * tablet or capsule, as contained in a packaged medicinal product.
   *
   */
  public String getId() {
    return id;
  }

  public ManufacturedItemDefinitionUvEpi setId(String value) {
    this.id = value;
    return this;
  }

  public boolean hasId() {
    return id != null;
  }

  /**
   * The definition and characteristics of a medicinal manufactured item, such as a 
   * tablet or capsule, as contained in a packaged medicinal product.
   *
   */
  public List<Extension> getExtensions() {
    if (extensions == null) { extensions = new ArrayList<>(); }
    return extensions;
  }

  public boolean hasExtensions() {
    return extensions != null && !extensions.isEmpty();
  }

  public Extension addExtension() {
    Extension theThing = new Extension();
    getExtensions().add(theThing);
    return theThing;
  }

  public boolean hasExtension(Extension item) {
    return hasExtensions() && extensions.contains(item);
  }

  public void removeExtension(Extension item) {
    if (hasExtension(item)) {
      extensions.remove(item);
    }
  }


  /**
   * The definition and characteristics of a medicinal manufactured item, such as a 
   * tablet or capsule, as contained in a packaged medicinal product.
   *
   */
  public List<Extension> getModifierExtensions() {
    if (modifierExtensions == null) { modifierExtensions = new ArrayList<>(); }
    return modifierExtensions;
  }

  public boolean hasModifierExtensions() {
    return modifierExtensions != null && !modifierExtensions.isEmpty();
  }

  public Extension addModifierExtension() {
    Extension theThing = new Extension();
    getModifierExtensions().add(theThing);
    return theThing;
  }

  public boolean hasModifierExtension(Extension item) {
    return hasModifierExtensions() && modifierExtensions.contains(item);
  }

  public void removeModifierExtension(Extension item) {
    if (hasModifierExtension(item)) {
      modifierExtensions.remove(item);
    }
  }


  /**
   * The definition and characteristics of a medicinal manufactured item, such as a 
   * tablet or capsule, as contained in a packaged medicinal product.
   *
   */
  public List<Identifier> getIdentifiers() {
    if (identifiers == null) { identifiers = new ArrayList<>(); }
    return identifiers;
  }

  public boolean hasIdentifiers() {
    return identifiers != null && !identifiers.isEmpty();
  }

  public Identifier addIdentifier() {
    Identifier theThing = new Identifier();
    getIdentifiers().add(theThing);
    return theThing;
  }

  public boolean hasIdentifier(Identifier item) {
    return hasIdentifiers() && identifiers.contains(item);
  }

  public void removeIdentifier(Identifier item) {
    if (hasIdentifier(item)) {
      identifiers.remove(item);
    }
  }


  /**
   * The definition and characteristics of a medicinal manufactured item, such as a 
   * tablet or capsule, as contained in a packaged medicinal product.
   *
   */
  public PublicationStatus getStatus() {
    return status;
  }

  public ManufacturedItemDefinitionUvEpi setStatus(PublicationStatus value) {
    this.status = value;
    return this;
  }

  public boolean hasStatus() {
    return status != null;
  }

  /**
   * The definition and characteristics of a medicinal manufactured item, such as a 
   * tablet or capsule, as contained in a packaged medicinal product.
   *
   */
  public CodeableConcept getManufacturedDoseForm() {
    if (manufacturedDoseForm == null) { manufacturedDoseForm = new CodeableConcept(); }
    return manufacturedDoseForm;
  }

  public ManufacturedItemDefinitionUvEpi setManufacturedDoseForm(CodeableConcept value) {
    this.manufacturedDoseForm = value;
    return this;
  }
  public boolean hasManufacturedDoseForm() {
    return manufacturedDoseForm != null;
  }

  /**
   * The definition and characteristics of a medicinal manufactured item, such as a 
   * tablet or capsule, as contained in a packaged medicinal product.
   *
   */
  public List<Reference> getManufacturers() {
    if (manufacturers == null) { manufacturers = new ArrayList<>(); }
    return manufacturers;
  }

  public boolean hasManufacturers() {
    return manufacturers != null && !manufacturers.isEmpty();
  }

  public Reference addManufacturer() {
    Reference theThing = new Reference();
    getManufacturers().add(theThing);
    return theThing;
  }

  public boolean hasManufacturer(Reference item) {
    return hasManufacturers() && manufacturers.contains(item);
  }

  public void removeManufacturer(Reference item) {
    if (hasManufacturer(item)) {
      manufacturers.remove(item);
    }
  }


  /**
   * The definition and characteristics of a medicinal manufactured item, such as a 
   * tablet or capsule, as contained in a packaged medicinal product.
   *
   */
  public List<MarketingStatus> getMarketingStatuses() {
    if (marketingStatuses == null) { marketingStatuses = new ArrayList<>(); }
    return marketingStatuses;
  }

  public boolean hasMarketingStatuses() {
    return marketingStatuses != null && !marketingStatuses.isEmpty();
  }

  public MarketingStatus addMarketingStatus() {
    MarketingStatus theThing = new MarketingStatus();
    getMarketingStatuses().add(theThing);
    return theThing;
  }

  public boolean hasMarketingStatus(MarketingStatus item) {
    return hasMarketingStatuses() && marketingStatuses.contains(item);
  }

  public void removeMarketingStatus(MarketingStatus item) {
    if (hasMarketingStatus(item)) {
      marketingStatuses.remove(item);
    }
  }


  /**
   * The definition and characteristics of a medicinal manufactured item, such as a 
   * tablet or capsule, as contained in a packaged medicinal product.
   *
   */
  public List<BackboneElement> getProperties() {
    if (properties == null) { properties = new ArrayList<>(); }
    return properties;
  }

  public boolean hasProperties() {
    return properties != null && !properties.isEmpty();
  }

  public boolean hasProperty(BackboneElement item) {
    return hasProperties() && properties.contains(item);
  }

  public void removeProperty(BackboneElement item) {
    if (hasProperty(item)) {
      properties.remove(item);
    }
  }


  /**
   * The definition and characteristics of a medicinal manufactured item, such as a 
   * tablet or capsule, as contained in a packaged medicinal product.
   *
   */
  public List<BackboneElement> getColors() {
    if (colors == null) { colors = new ArrayList<>(); }
    return colors;
  }

  public boolean hasColors() {
    return colors != null && !colors.isEmpty();
  }

  public boolean hasColor(BackboneElement item) {
    return hasColors() && colors.contains(item);
  }

  public void removeColor(BackboneElement item) {
    if (hasColor(item)) {
      colors.remove(item);
    }
  }


  /**
   * The definition and characteristics of a medicinal manufactured item, such as a 
   * tablet or capsule, as contained in a packaged medicinal product.
   *
   */
  public List<BackboneElement> getFlavors() {
    if (flavors == null) { flavors = new ArrayList<>(); }
    return flavors;
  }

  public boolean hasFlavors() {
    return flavors != null && !flavors.isEmpty();
  }

  public boolean hasFlavor(BackboneElement item) {
    return hasFlavors() && flavors.contains(item);
  }

  public void removeFlavor(BackboneElement item) {
    if (hasFlavor(item)) {
      flavors.remove(item);
    }
  }


  /**
   * The definition and characteristics of a medicinal manufactured item, such as a 
   * tablet or capsule, as contained in a packaged medicinal product.
   *
   */
  public List<BackboneElement> getScores() {
    if (scores == null) { scores = new ArrayList<>(); }
    return scores;
  }

  public boolean hasScores() {
    return scores != null && !scores.isEmpty();
  }

  public boolean hasScore(BackboneElement item) {
    return hasScores() && scores.contains(item);
  }

  public void removeScore(BackboneElement item) {
    if (hasScore(item)) {
      scores.remove(item);
    }
  }


  /**
   * The definition and characteristics of a medicinal manufactured item, such as a 
   * tablet or capsule, as contained in a packaged medicinal product.
   *
   */
  public List<BackboneElement> getShapes() {
    if (shapes == null) { shapes = new ArrayList<>(); }
    return shapes;
  }

  public boolean hasShapes() {
    return shapes != null && !shapes.isEmpty();
  }

  public boolean hasShape(BackboneElement item) {
    return hasShapes() && shapes.contains(item);
  }

  public void removeShape(BackboneElement item) {
    if (hasShape(item)) {
      shapes.remove(item);
    }
  }


  /**
   * The definition and characteristics of a medicinal manufactured item, such as a 
   * tablet or capsule, as contained in a packaged medicinal product.
   *
   */
  public List<BackboneElement> getSurfaceforms() {
    if (surfaceforms == null) { surfaceforms = new ArrayList<>(); }
    return surfaceforms;
  }

  public boolean hasSurfaceforms() {
    return surfaceforms != null && !surfaceforms.isEmpty();
  }

  public boolean hasSurfaceform(BackboneElement item) {
    return hasSurfaceforms() && surfaceforms.contains(item);
  }

  public void removeSurfaceform(BackboneElement item) {
    if (hasSurfaceform(item)) {
      surfaceforms.remove(item);
    }
  }


  /**
   * The definition and characteristics of a medicinal manufactured item, such as a 
   * tablet or capsule, as contained in a packaged medicinal product.
   *
   */
  public List<BackboneElement> getSizes() {
    if (sizes == null) { sizes = new ArrayList<>(); }
    return sizes;
  }

  public boolean hasSizes() {
    return sizes != null && !sizes.isEmpty();
  }

  public boolean hasSize(BackboneElement item) {
    return hasSizes() && sizes.contains(item);
  }

  public void removeSize(BackboneElement item) {
    if (hasSize(item)) {
      sizes.remove(item);
    }
  }


  /**
   * The definition and characteristics of a medicinal manufactured item, such as a 
   * tablet or capsule, as contained in a packaged medicinal product.
   *
   */
  public List<BackboneElement> getImages() {
    if (images == null) { images = new ArrayList<>(); }
    return images;
  }

  public boolean hasImages() {
    return images != null && !images.isEmpty();
  }

  public boolean hasImage(BackboneElement item) {
    return hasImages() && images.contains(item);
  }

  public void removeImage(BackboneElement item) {
    if (hasImage(item)) {
      images.remove(item);
    }
  }


  /**
   * The definition and characteristics of a medicinal manufactured item, such as a 
   * tablet or capsule, as contained in a packaged medicinal product.
   *
   */
  public List<BackboneElement> getImprints() {
    if (imprints == null) { imprints = new ArrayList<>(); }
    return imprints;
  }

  public boolean hasImprints() {
    return imprints != null && !imprints.isEmpty();
  }

  public boolean hasImprint(BackboneElement item) {
    return hasImprints() && imprints.contains(item);
  }

  public void removeImprint(BackboneElement item) {
    if (hasImprint(item)) {
      imprints.remove(item);
    }
  }


  /**
   * The definition and characteristics of a medicinal manufactured item, such as a 
   * tablet or capsule, as contained in a packaged medicinal product.
   *
   */
  public List<BackboneElement> getComponents() {
    if (components == null) { components = new ArrayList<>(); }
    return components;
  }

  public boolean hasComponents() {
    return components != null && !components.isEmpty();
  }

  public boolean hasComponent(BackboneElement item) {
    return hasComponents() && components.contains(item);
  }

  public void removeComponent(BackboneElement item) {
    if (hasComponent(item)) {
      components.remove(item);
    }
  }




  public void clear() {
    id = null;
    extensions.clear();
    modifierExtensions.clear();
    identifiers.clear();
    status = null;
    manufacturedDoseForm = null;
    manufacturers.clear();
    marketingStatuses.clear();
    properties.clear();
    colors.clear();
    flavors.clear();
    scores.clear();
    shapes.clear();
    surfaceforms.clear();
    sizes.clear();
    images.clear();
    imprints.clear();
    components.clear();

  }

}

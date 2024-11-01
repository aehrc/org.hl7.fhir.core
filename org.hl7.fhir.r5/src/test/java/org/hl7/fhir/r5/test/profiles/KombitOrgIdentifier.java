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
 * An identifier - identifies some entity uniquely and unambiguously. Typically 
 * this is used for business identifiers.
 *
 */
public class KombitOrgIdentifier extends PEGeneratedBase {

  public static final String CANONICAL_URL = "http://hl7.dk/fhir/core/StructureDefinition/dk-core-kombit-org-identifier|3.2.0";

  @Min("0") @Max("*") @Doco("Additional content defined by implementations")
  @Definition("May be used to represent additional information that is not part of the basic definition of the element. To make the use of extensions safe and manageable, there is a strict set of governance  applied to the definition and use of extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the extension.")
  private List<Extension> extensions = new ArrayList<>();  // Additional content defined by implementations

  @Min("1") @Max("1") @Doco("The namespace for the identifier value")
  @Definition("Establishes the namespace for the value - that is, a URL that describes a set values that are unique.")
  private String system;// @NotNull  // The namespace for the identifier value

  @Min("1") @Max("1") @Doco("The value that is unique")
  @Definition("The portion of the identifier typically relevant to the user and which is unique within the context of the system.")
  private String value;// @NotNull  // The value that is unique

  @Min("0") @Max("1") @Doco("Time period when id is/was valid for use")
  @Definition("Time period during which identifier is/was valid for use.")
  private Period period;  // Time period when id is/was valid for use

  @Min("0") @Max("1") @Doco("Organization that issued id (may be just text)")
  @Definition("Organization that issued/manages the identifier.")
  private Reference assigner;  // Organization that issued id (may be just text)


  /**
   * Parameter-less constructor.
   *
   */
  public KombitOrgIdentifier() {
    initFixedValues();
  }

  /**
   * Used when loading other models 
   *
   */
  public static KombitOrgIdentifier fromSource(PEInstance source) {
    KombitOrgIdentifier theThing = new KombitOrgIdentifier();
    theThing.workerContext = source.getContext();
    theThing.load(source);
    return theThing;
  }

  public void load(PEInstance src) {
    clear();
    for (PEInstance item : src.children("extension")) {
      extensions.add((Extension) item.asDataType());
    }
    if (src.hasChild("system")) {
      system = ((UriType) src.child("system").asDataType()).getValue();
    }
    if (src.hasChild("value")) {
      value = ((StringType) src.child("value").asDataType()).getValue();
    }
    if (src.hasChild("period")) {
      period = (Period) src.child("period").asDataType();
    }
    if (src.hasChild("assigner")) {
      assigner = (Reference) src.child("assigner").asDataType();
    }

  }

  public void save(PEInstance tgt, boolean nulls) {
    tgt.clear("extension");
    for (Extension item : extensions) {
      tgt.addChild("extension", item);
    }
    tgt.clear("system");
    if (system != null) {
      tgt.makeChild("system").data().setProperty("value", new UriType(system));
    }
    tgt.clear("value");
    if (value != null) {
      tgt.makeChild("value").data().setProperty("value", new StringType(value));
    }
    tgt.clear("period");
    if (period != null) {
      tgt.addChild("period", period);
    }
    tgt.clear("assigner");
    if (assigner != null) {
      tgt.addChild("assigner", assigner);
    }

  }

  private void initFixedValues() {
    system = "https://kombit.dk/sts/organisation";

  }

  /**
   * An identifier - identifies some entity uniquely and unambiguously. Typically 
   * this is used for business identifiers.
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
   * An identifier - identifies some entity uniquely and unambiguously. Typically 
   * this is used for business identifiers.
   *
   */
  public String getSystem() {
    return system;
  }

  public boolean hasSystem() {
    return true;
  }

  /**
   * An identifier - identifies some entity uniquely and unambiguously. Typically 
   * this is used for business identifiers.
   *
   */
  public String getValue() {
    return value;
  }

  public KombitOrgIdentifier setValue(String value) {
    this.value = value;
    return this;
  }

  public boolean hasValue() {
    return value != null;
  }

  /**
   * An identifier - identifies some entity uniquely and unambiguously. Typically 
   * this is used for business identifiers.
   *
   */
  public Period getPeriod() {
    if (period == null) { period = new Period(); }
    return period;
  }

  public KombitOrgIdentifier setPeriod(Period value) {
    this.period = value;
    return this;
  }
  public boolean hasPeriod() {
    return period != null;
  }

  /**
   * An identifier - identifies some entity uniquely and unambiguously. Typically 
   * this is used for business identifiers.
   *
   */
  public Reference getAssigner() {
    if (assigner == null) { assigner = new Reference(); }
    return assigner;
  }

  public KombitOrgIdentifier setAssigner(Reference value) {
    this.assigner = value;
    return this;
  }
  public boolean hasAssigner() {
    return assigner != null;
  }



  public void clear() {
    extensions.clear();
    system = null;
    value = null;
    period = null;
    assigner = null;

  }

}

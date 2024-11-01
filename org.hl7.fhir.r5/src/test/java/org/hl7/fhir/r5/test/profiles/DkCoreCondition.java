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
 * A clinical condition, problem, diagnosis, or other event, situation, issue, or 
 * clinical concept that has risen to a level of concern.
 *
 */
public class DkCoreCondition extends PEGeneratedBase {

  public static final String CANONICAL_URL = "http://hl7.dk/fhir/core/StructureDefinition/dk-core-condition|3.2.0";

  @Min("0") @Max("1") @Doco("")
  private String id;  // 

  @Min("0") @Max("*") @Doco("Extension")
  @Definition("An Extension")
  private List<Extension> extensions = new ArrayList<>();  // Extension

  @Min("0") @Max("1") @Doco("Last date a condition was confirmed valid in its current state")
  @Definition("Extension for the last date a Condition-instance was confirmed valid in its current state. E.g. with its current clinical- and verification status, stage and severity. Typically the last performed follow-up")
  private Date conditionLastAssertedDate;  // Last date a condition was confirmed valid in its current state

  @Min("0") @Max("1") @Doco("Date where a condition lost focus in a specific clinical context")
  @Definition("Extension for the date where a condition lost focus in a specific clinical context")
  private Date notFollowedAnymore;  // Date where a condition lost focus in a specific clinical context

  @Min("0") @Max("*") @Doco("Extensions that cannot be ignored")
  @Definition("May be used to represent additional information that is not part of the basic definition of the resource and that modifies the understanding of the element that contains it and/or the understanding of the containing element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any implementer is allowed to define an extension, there is a set of requirements that SHALL be met as part of the definition of the extension. Applications processing a resource are required to check for modifier extensions.\n\nModifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot change the meaning of modifierExtension itself).")
  private List<Extension> modifierExtensions = new ArrayList<>();  // Extensions that cannot be ignored

  @Min("0") @Max("*") @Doco("External Ids for this condition")
  @Definition("Business identifiers assigned to this condition by the performer or other systems which remain constant as the resource is updated and propagates from server to server.")
  private List<Identifier> identifiers = new ArrayList<>();  // External Ids for this condition

  @Min("0") @Max("1") @Doco("active | recurrence | relapse | inactive | remission | resolved")
  @BindingStrength("required") @ValueSet("http://hl7.org/fhir/ValueSet/condition-clinical|4.0.1")
  @Definition("The clinical status of the condition.")
  private CodeableConcept clinicalStatus;  // active | recurrence | relapse | inactive | remission | resolved

  @Min("0") @Max("1") @Doco("unconfirmed | provisional | differential | confirmed | refuted | entered-in-error")
  @BindingStrength("required") @ValueSet("http://hl7.org/fhir/ValueSet/condition-ver-status|4.0.1")
  @Definition("The verification status to support the clinical status of the condition.")
  private CodeableConcept verificationStatus;  // unconfirmed | provisional | differential | confirmed | refuted | entered-in-error

  @Min("0") @Max("1") @Doco("Identification of the condition, problem or diagnosis")
  @BindingStrength("example") @ValueSet("http://hl7.org/fhir/ValueSet/condition-code")
  @Definition("Identification of the condition, problem or diagnosis.")
  private CodeableConcept code;  // Identification of the condition, problem or diagnosis

  @Min("1") @Max("1") @Doco("Who has the condition?")
  @Definition("Indicates the patient or group who the condition record is associated with.")
  private Reference subject;// @NotNull  // Who has the condition?

  @Min("0") @Max("1") @Doco("Encounter created as part of")
  @Definition("The Encounter during which this Condition was created or to which the creation of this record is tightly associated.")
  private Reference encounter;  // Encounter created as part of

  @Min("0") @Max("1") @Doco("Who recorded the condition")
  @Definition("Individual who recorded the record and takes responsibility for its content.")
  private Reference recorder;  // Who recorded the condition

  @Min("0") @Max("1") @Doco("Person who asserts this condition")
  @Definition("Individual who is making the condition statement.")
  private Reference asserter;  // Person who asserts this condition

  @Min("0") @Max("*") @Doco("Stage/grade, usually assessed formally")
  @Definition("Clinical stage or grade of a condition. May include formal severity assessments.")
  private List<BackboneElement> stages = new ArrayList<>();  // Stage/grade, usually assessed formally

  @Min("0") @Max("*") @Doco("Supporting evidence")
  @Definition("Supporting evidence / manifestations that are the basis of the Condition's verification status, such as evidence that confirmed or refuted the condition.")
  private List<BackboneElement> evidences = new ArrayList<>();  // Supporting evidence

  @Min("0") @Max("*") @Doco("Additional information about the Condition")
  @Definition("Additional information about the Condition. This is a general notes/comments entry  for description of the Condition, its diagnosis and prognosis.")
  private List<Annotation> notes = new ArrayList<>();  // Additional information about the Condition


  /**
   * Parameter-less constructor.
   *
   */
  public DkCoreCondition() {
  }

  /**
   * Construct an instance of the object, and fill out all the fixed values 
   *
   */
  public DkCoreCondition(IWorkerContext context) {
    workerContext = context;
    PEBuilder builder = new PEBuilder(context, PEElementPropertiesPolicy.EXTENSION, true);
    PEInstance src = builder.buildPEInstance(CANONICAL_URL, builder.createResource(CANONICAL_URL, false));
    load(src);
  }

  /**
   * Populate an instance of the object based on this source object 
   *
   */
  public static DkCoreCondition fromSource(IWorkerContext context, Condition source) {
    DkCoreCondition theThing = new DkCoreCondition();
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
    if (src.hasChild("conditionLastAssertedDate")) {
      conditionLastAssertedDate = ((DateTimeType) src.child("conditionLastAssertedDate").asDataType()).getValue();
    }
    if (src.hasChild("notFollowedAnymore")) {
      notFollowedAnymore = ((DateTimeType) src.child("notFollowedAnymore").asDataType()).getValue();
    }
    for (PEInstance item : src.children("modifierExtension")) {
      modifierExtensions.add((Extension) item.asDataType());
    }
    for (PEInstance item : src.children("identifier")) {
      identifiers.add((Identifier) item.asDataType());
    }
    if (src.hasChild("clinicalStatus")) {
      clinicalStatus = (CodeableConcept) src.child("clinicalStatus").asDataType();
    }
    if (src.hasChild("verificationStatus")) {
      verificationStatus = (CodeableConcept) src.child("verificationStatus").asDataType();
    }
    if (src.hasChild("code")) {
      code = (CodeableConcept) src.child("code").asDataType();
    }
    if (src.hasChild("subject")) {
      subject = (Reference) src.child("subject").asDataType();
    }
    if (src.hasChild("encounter")) {
      encounter = (Reference) src.child("encounter").asDataType();
    }
    if (src.hasChild("recorder")) {
      recorder = (Reference) src.child("recorder").asDataType();
    }
    if (src.hasChild("asserter")) {
      asserter = (Reference) src.child("asserter").asDataType();
    }
    for (PEInstance item : src.children("stage")) {
      stages.add((BackboneElement) item.asElement());
    }
    for (PEInstance item : src.children("evidence")) {
      evidences.add((BackboneElement) item.asElement());
    }
    for (PEInstance item : src.children("note")) {
      notes.add((Annotation) item.asDataType());
    }

  }

  /**
   * Build an instance of the object based on this source object 
   *
   */
  public Condition build(IWorkerContext context) {
    workerContext = context;
    Condition theThing = new Condition();
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
  public void save(IWorkerContext context, Condition dest, boolean nulls) {
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
    tgt.clear("conditionLastAssertedDate");
    if (conditionLastAssertedDate != null) {
      tgt.makeChild("conditionLastAssertedDate").data().setProperty("value[x]", new DateTimeType(conditionLastAssertedDate));
    }
    tgt.clear("notFollowedAnymore");
    if (notFollowedAnymore != null) {
      tgt.makeChild("notFollowedAnymore").data().setProperty("value[x]", new DateTimeType(notFollowedAnymore));
    }
    tgt.clear("modifierExtension");
    for (Extension item : modifierExtensions) {
      tgt.addChild("modifierExtension", item);
    }
    tgt.clear("identifier");
    for (Identifier item : identifiers) {
      tgt.addChild("identifier", item);
    }
    tgt.clear("clinicalStatus");
    if (clinicalStatus != null) {
      tgt.addChild("clinicalStatus", clinicalStatus);
    }
    tgt.clear("verificationStatus");
    if (verificationStatus != null) {
      tgt.addChild("verificationStatus", verificationStatus);
    }
    tgt.clear("code");
    if (code != null) {
      tgt.addChild("code", code);
    }
    tgt.clear("subject");
    if (subject != null) {
      tgt.addChild("subject", subject);
    }
    tgt.clear("encounter");
    if (encounter != null) {
      tgt.addChild("encounter", encounter);
    }
    tgt.clear("recorder");
    if (recorder != null) {
      tgt.addChild("recorder", recorder);
    }
    tgt.clear("asserter");
    if (asserter != null) {
      tgt.addChild("asserter", asserter);
    }
    tgt.clear("stage");
    for (BackboneElement item : stages) {
      tgt.addChild("stage", item);
    }
    tgt.clear("evidence");
    for (BackboneElement item : evidences) {
      tgt.addChild("evidence", item);
    }
    tgt.clear("note");
    for (Annotation item : notes) {
      tgt.addChild("note", item);
    }

  }

  /**
   * A clinical condition, problem, diagnosis, or other event, situation, issue, or 
   * clinical concept that has risen to a level of concern.
   *
   */
  public String getId() {
    return id;
  }

  public DkCoreCondition setId(String value) {
    this.id = value;
    return this;
  }

  public boolean hasId() {
    return id != null;
  }

  /**
   * A clinical condition, problem, diagnosis, or other event, situation, issue, or 
   * clinical concept that has risen to a level of concern.
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
   * A clinical condition, problem, diagnosis, or other event, situation, issue, or 
   * clinical concept that has risen to a level of concern.
   *
   */
  public Date getConditionLastAssertedDate() {
    return conditionLastAssertedDate;
  }

  public DkCoreCondition setConditionLastAssertedDate(Date value) {
    this.conditionLastAssertedDate = value;
    return this;
  }

  public boolean hasConditionLastAssertedDate() {
    return conditionLastAssertedDate != null;
  }

  /**
   * A clinical condition, problem, diagnosis, or other event, situation, issue, or 
   * clinical concept that has risen to a level of concern.
   *
   */
  public Date getNotFollowedAnymore() {
    return notFollowedAnymore;
  }

  public DkCoreCondition setNotFollowedAnymore(Date value) {
    this.notFollowedAnymore = value;
    return this;
  }

  public boolean hasNotFollowedAnymore() {
    return notFollowedAnymore != null;
  }

  /**
   * A clinical condition, problem, diagnosis, or other event, situation, issue, or 
   * clinical concept that has risen to a level of concern.
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
   * A clinical condition, problem, diagnosis, or other event, situation, issue, or 
   * clinical concept that has risen to a level of concern.
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
   * A clinical condition, problem, diagnosis, or other event, situation, issue, or 
   * clinical concept that has risen to a level of concern.
   *
   */
  public CodeableConcept getClinicalStatus() {
    if (clinicalStatus == null) { clinicalStatus = new CodeableConcept(); }
    return clinicalStatus;
  }

  public DkCoreCondition setClinicalStatus(CodeableConcept value) {
    this.clinicalStatus = value;
    return this;
  }
  public boolean hasClinicalStatus() {
    return clinicalStatus != null;
  }

  /**
   * A clinical condition, problem, diagnosis, or other event, situation, issue, or 
   * clinical concept that has risen to a level of concern.
   *
   */
  public CodeableConcept getVerificationStatus() {
    if (verificationStatus == null) { verificationStatus = new CodeableConcept(); }
    return verificationStatus;
  }

  public DkCoreCondition setVerificationStatus(CodeableConcept value) {
    this.verificationStatus = value;
    return this;
  }
  public boolean hasVerificationStatus() {
    return verificationStatus != null;
  }

  /**
   * A clinical condition, problem, diagnosis, or other event, situation, issue, or 
   * clinical concept that has risen to a level of concern.
   *
   */
  public CodeableConcept getCode() {
    if (code == null) { code = new CodeableConcept(); }
    return code;
  }

  public DkCoreCondition setCode(CodeableConcept value) {
    this.code = value;
    return this;
  }
  public boolean hasCode() {
    return code != null;
  }

  /**
   * A clinical condition, problem, diagnosis, or other event, situation, issue, or 
   * clinical concept that has risen to a level of concern.
   *
   */
  public Reference getSubject() {
    if (subject == null) { subject = new Reference(); }
    return subject;
  }

  public DkCoreCondition setSubject(Reference value) {
    this.subject = value;
    return this;
  }
  public boolean hasSubject() {
    return subject != null;
  }

  /**
   * A clinical condition, problem, diagnosis, or other event, situation, issue, or 
   * clinical concept that has risen to a level of concern.
   *
   */
  public Reference getEncounter() {
    if (encounter == null) { encounter = new Reference(); }
    return encounter;
  }

  public DkCoreCondition setEncounter(Reference value) {
    this.encounter = value;
    return this;
  }
  public boolean hasEncounter() {
    return encounter != null;
  }

  /**
   * A clinical condition, problem, diagnosis, or other event, situation, issue, or 
   * clinical concept that has risen to a level of concern.
   *
   */
  public Reference getRecorder() {
    if (recorder == null) { recorder = new Reference(); }
    return recorder;
  }

  public DkCoreCondition setRecorder(Reference value) {
    this.recorder = value;
    return this;
  }
  public boolean hasRecorder() {
    return recorder != null;
  }

  /**
   * A clinical condition, problem, diagnosis, or other event, situation, issue, or 
   * clinical concept that has risen to a level of concern.
   *
   */
  public Reference getAsserter() {
    if (asserter == null) { asserter = new Reference(); }
    return asserter;
  }

  public DkCoreCondition setAsserter(Reference value) {
    this.asserter = value;
    return this;
  }
  public boolean hasAsserter() {
    return asserter != null;
  }

  /**
   * A clinical condition, problem, diagnosis, or other event, situation, issue, or 
   * clinical concept that has risen to a level of concern.
   *
   */
  public List<BackboneElement> getStages() {
    if (stages == null) { stages = new ArrayList<>(); }
    return stages;
  }

  public boolean hasStages() {
    return stages != null && !stages.isEmpty();
  }

  public boolean hasStage(BackboneElement item) {
    return hasStages() && stages.contains(item);
  }

  public void removeStage(BackboneElement item) {
    if (hasStage(item)) {
      stages.remove(item);
    }
  }


  /**
   * A clinical condition, problem, diagnosis, or other event, situation, issue, or 
   * clinical concept that has risen to a level of concern.
   *
   */
  public List<BackboneElement> getEvidences() {
    if (evidences == null) { evidences = new ArrayList<>(); }
    return evidences;
  }

  public boolean hasEvidences() {
    return evidences != null && !evidences.isEmpty();
  }

  public boolean hasEvidence(BackboneElement item) {
    return hasEvidences() && evidences.contains(item);
  }

  public void removeEvidence(BackboneElement item) {
    if (hasEvidence(item)) {
      evidences.remove(item);
    }
  }


  /**
   * A clinical condition, problem, diagnosis, or other event, situation, issue, or 
   * clinical concept that has risen to a level of concern.
   *
   */
  public List<Annotation> getNotes() {
    if (notes == null) { notes = new ArrayList<>(); }
    return notes;
  }

  public boolean hasNotes() {
    return notes != null && !notes.isEmpty();
  }

  public Annotation addNote() {
    Annotation theThing = new Annotation();
    getNotes().add(theThing);
    return theThing;
  }

  public boolean hasNote(Annotation item) {
    return hasNotes() && notes.contains(item);
  }

  public void removeNote(Annotation item) {
    if (hasNote(item)) {
      notes.remove(item);
    }
  }




  public void clear() {
    id = null;
    extensions.clear();
    conditionLastAssertedDate = null;
    notFollowedAnymore = null;
    modifierExtensions.clear();
    identifiers.clear();
    clinicalStatus = null;
    verificationStatus = null;
    code = null;
    subject = null;
    encounter = null;
    recorder = null;
    asserter = null;
    stages.clear();
    evidences.clear();
    notes.clear();

  }

}

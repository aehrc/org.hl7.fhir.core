package org.hl7.fhir.convertors.conv10_40.resources10_40;

import org.hl7.fhir.convertors.advisors.impl.BaseAdvisor_10_40;
import org.hl7.fhir.convertors.context.ConversionContext10_40;
import org.hl7.fhir.convertors.conv10_40.datatypes10_40.complextypes10_40.Coding10_40;
import org.hl7.fhir.convertors.conv10_40.datatypes10_40.complextypes10_40.ContactPoint10_40;
import org.hl7.fhir.convertors.conv10_40.datatypes10_40.complextypes10_40.Identifier10_40;
import org.hl7.fhir.convertors.conv10_40.datatypes10_40.primitivetypes10_40.Boolean10_40;
import org.hl7.fhir.convertors.conv10_40.datatypes10_40.primitivetypes10_40.Canonical10_40;
import org.hl7.fhir.convertors.conv10_40.datatypes10_40.primitivetypes10_40.DateTime10_40;
import org.hl7.fhir.convertors.conv10_40.datatypes10_40.primitivetypes10_40.String10_40;
import org.hl7.fhir.dstu2.model.Enumeration;
import org.hl7.fhir.dstu2.model.Questionnaire;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r4.model.ContactDetail;
import org.hl7.fhir.r4.model.Enumerations;
import org.hl7.fhir.r4.model.Questionnaire.QuestionnaireItemAnswerOptionComponent;

public class Questionnaire10_40 {

  public static org.hl7.fhir.dstu2.model.Questionnaire convertQuestionnaire(org.hl7.fhir.r4.model.Questionnaire src, BaseAdvisor_10_40 advisor) {
    if (src == null || src.isEmpty())
      return null;
    org.hl7.fhir.dstu2.model.Questionnaire tgt = new org.hl7.fhir.dstu2.model.Questionnaire();
    ConversionContext10_40.INSTANCE.getVersionConvertor_10_40().copyDomainResource(src, tgt);
    for (org.hl7.fhir.r4.model.Identifier t : src.getIdentifier())
      tgt.addIdentifier(Identifier10_40.convertIdentifier(t));
    if (src.hasVersionElement())
      tgt.setVersionElement(String10_40.convertString(src.getVersionElement()));
    if (src.hasStatus())
      tgt.setStatusElement(convertQuestionnaireStatus(src.getStatusElement()));
    if (src.hasDate())
      tgt.setDateElement(DateTime10_40.convertDateTime(src.getDateElement()));
    if (src.hasPublisherElement())
      tgt.setPublisherElement(String10_40.convertString(src.getPublisherElement()));
    for (ContactDetail t : src.getContact())
      for (org.hl7.fhir.r4.model.ContactPoint t1 : t.getTelecom())
        tgt.addTelecom(ContactPoint10_40.convertContactPoint(t1));
    org.hl7.fhir.dstu2.model.Questionnaire.GroupComponent root = tgt.getGroup();
    root.setTitle(src.getTitle());
    for (org.hl7.fhir.r4.model.Coding t : src.getCode()) {
      root.addConcept(Coding10_40.convertCoding(t));
    }
    for (org.hl7.fhir.r4.model.CodeType t : src.getSubjectType()) tgt.addSubjectType(t.getValue());
    for (org.hl7.fhir.r4.model.Questionnaire.QuestionnaireItemComponent t : src.getItem())
      if (t.getType() == org.hl7.fhir.r4.model.Questionnaire.QuestionnaireItemType.GROUP)
        root.addGroup(convertQuestionnaireGroupComponent(t));
      else
        root.addQuestion(convertQuestionnaireQuestionComponent(t));
    return tgt;
  }

  public static org.hl7.fhir.r4.model.Questionnaire convertQuestionnaire(org.hl7.fhir.dstu2.model.Questionnaire src) throws FHIRException {
    return convertQuestionnaire(src, null);
  }

  public static org.hl7.fhir.r4.model.Questionnaire convertQuestionnaire(org.hl7.fhir.dstu2.model.Questionnaire src, BaseAdvisor_10_40 advisor) throws FHIRException {
    if (src == null || src.isEmpty())
      return null;
    org.hl7.fhir.r4.model.Questionnaire tgt = new org.hl7.fhir.r4.model.Questionnaire();
    ConversionContext10_40.INSTANCE.getVersionConvertor_10_40().copyDomainResource(src, tgt);
    for (org.hl7.fhir.dstu2.model.Identifier t : src.getIdentifier())
      tgt.addIdentifier(Identifier10_40.convertIdentifier(t));
    if (src.hasVersionElement())
      tgt.setVersionElement(String10_40.convertString(src.getVersionElement()));
    if (src.hasStatus())
      tgt.setStatusElement(convertQuestionnaireStatus(src.getStatusElement()));
    if (src.hasDate())
      tgt.setDateElement(DateTime10_40.convertDateTime(src.getDateElement()));
    if (src.hasPublisherElement())
      tgt.setPublisherElement(String10_40.convertString(src.getPublisherElement()));
    for (org.hl7.fhir.dstu2.model.ContactPoint t : src.getTelecom())
      tgt.addContact(convertQuestionnaireContactComponent(t));
    org.hl7.fhir.dstu2.model.Questionnaire.GroupComponent root = src.getGroup();
    tgt.setTitle(root.getTitle());
    for (org.hl7.fhir.dstu2.model.Coding t : root.getConcept()) tgt.addCode(Coding10_40.convertCoding(t));
    for (org.hl7.fhir.dstu2.model.CodeType t : src.getSubjectType()) tgt.addSubjectType(t.getValue());
    tgt.addItem(convertQuestionnaireGroupComponent(root));
    return tgt;
  }

  public static org.hl7.fhir.r4.model.ContactDetail convertQuestionnaireContactComponent(org.hl7.fhir.dstu2.model.ContactPoint src) throws FHIRException {
    if (src == null || src.isEmpty())
      return null;
    org.hl7.fhir.r4.model.ContactDetail tgt = new org.hl7.fhir.r4.model.ContactDetail();
    ConversionContext10_40.INSTANCE.getVersionConvertor_10_40().copyElement(src, tgt);
    tgt.addTelecom(ContactPoint10_40.convertContactPoint(src));
    return tgt;
  }

  public static org.hl7.fhir.dstu2.model.Questionnaire.GroupComponent convertQuestionnaireGroupComponent(org.hl7.fhir.r4.model.Questionnaire.QuestionnaireItemComponent src) throws FHIRException {
    if (src == null || src.isEmpty())
      return null;
    org.hl7.fhir.dstu2.model.Questionnaire.GroupComponent tgt = new org.hl7.fhir.dstu2.model.Questionnaire.GroupComponent();
    ConversionContext10_40.INSTANCE.getVersionConvertor_10_40().copyBackboneElement(src,tgt);
    if (src.hasLinkIdElement())
      tgt.setLinkIdElement(String10_40.convertString(src.getLinkIdElement()));
    for (org.hl7.fhir.r4.model.Coding t : src.getCode()) tgt.addConcept(Coding10_40.convertCoding(t));
    if (src.hasTextElement())
      tgt.setTextElement(String10_40.convertString(src.getTextElement()));
    if (src.hasRequiredElement())
      tgt.setRequiredElement(Boolean10_40.convertBoolean(src.getRequiredElement()));
    if (src.hasRepeatsElement())
      tgt.setRepeatsElement(Boolean10_40.convertBoolean(src.getRepeatsElement()));
    for (org.hl7.fhir.r4.model.Questionnaire.QuestionnaireItemComponent t : src.getItem())
      if (t.getType() == org.hl7.fhir.r4.model.Questionnaire.QuestionnaireItemType.GROUP)
        tgt.addGroup(convertQuestionnaireGroupComponent(t));
      else
        tgt.addQuestion(convertQuestionnaireQuestionComponent(t));
    return tgt;
  }

  public static org.hl7.fhir.r4.model.Questionnaire.QuestionnaireItemComponent convertQuestionnaireGroupComponent(org.hl7.fhir.dstu2.model.Questionnaire.GroupComponent src) throws FHIRException {
    if (src == null || src.isEmpty())
      return null;
    org.hl7.fhir.r4.model.Questionnaire.QuestionnaireItemComponent tgt = new org.hl7.fhir.r4.model.Questionnaire.QuestionnaireItemComponent();
    ConversionContext10_40.INSTANCE.getVersionConvertor_10_40().copyBackboneElement(src,tgt);
    if (src.hasLinkIdElement())
      tgt.setLinkIdElement(String10_40.convertString(src.getLinkIdElement()));
    for (org.hl7.fhir.dstu2.model.Coding t : src.getConcept()) tgt.addCode(Coding10_40.convertCoding(t));
    if (src.hasTextElement())
      tgt.setTextElement(String10_40.convertString(src.getTextElement()));
    tgt.setType(org.hl7.fhir.r4.model.Questionnaire.QuestionnaireItemType.GROUP);
    if (src.hasRequiredElement())
      tgt.setRequiredElement(Boolean10_40.convertBoolean(src.getRequiredElement()));
    if (src.hasRepeatsElement())
      tgt.setRepeatsElement(Boolean10_40.convertBoolean(src.getRepeatsElement()));
    for (org.hl7.fhir.dstu2.model.Questionnaire.GroupComponent t : src.getGroup())
      tgt.addItem(convertQuestionnaireGroupComponent(t));
    for (org.hl7.fhir.dstu2.model.Questionnaire.QuestionComponent t : src.getQuestion())
      tgt.addItem(convertQuestionnaireQuestionComponent(t));
    return tgt;
  }

  static public org.hl7.fhir.dstu2.model.Enumeration<org.hl7.fhir.dstu2.model.Questionnaire.AnswerFormat> convertQuestionnaireItemType(org.hl7.fhir.r4.model.Enumeration<org.hl7.fhir.r4.model.Questionnaire.QuestionnaireItemType> src) throws FHIRException {
      if (src == null || src.isEmpty())
          return null;
      Enumeration<Questionnaire.AnswerFormat> tgt = new Enumeration<>(new Questionnaire.AnswerFormatEnumFactory());
      ConversionContext10_40.INSTANCE.getVersionConvertor_10_40().copyElement(src, tgt);
      if (src.getValue() == null) {
          tgt.setValue(null);
      } else {
          switch (src.getValue()) {
              case BOOLEAN:
                  tgt.setValue(Questionnaire.AnswerFormat.BOOLEAN);
                  break;
              case DECIMAL:
                  tgt.setValue(Questionnaire.AnswerFormat.DECIMAL);
                  break;
              case INTEGER:
                  tgt.setValue(Questionnaire.AnswerFormat.INTEGER);
                  break;
              case DATE:
                  tgt.setValue(Questionnaire.AnswerFormat.DATE);
                  break;
              case DATETIME:
                  tgt.setValue(Questionnaire.AnswerFormat.DATETIME);
                  break;
              case TIME:
                  tgt.setValue(Questionnaire.AnswerFormat.TIME);
                  break;
              case STRING:
                  tgt.setValue(Questionnaire.AnswerFormat.STRING);
                  break;
              case TEXT:
                  tgt.setValue(Questionnaire.AnswerFormat.TEXT);
                  break;
              case URL:
                  tgt.setValue(Questionnaire.AnswerFormat.URL);
                  break;
              case CHOICE:
                  tgt.setValue(Questionnaire.AnswerFormat.CHOICE);
                  break;
              case OPENCHOICE:
                  tgt.setValue(Questionnaire.AnswerFormat.OPENCHOICE);
                  break;
              case ATTACHMENT:
                  tgt.setValue(Questionnaire.AnswerFormat.ATTACHMENT);
                  break;
              case REFERENCE:
                  tgt.setValue(Questionnaire.AnswerFormat.REFERENCE);
                  break;
              case QUANTITY:
                  tgt.setValue(Questionnaire.AnswerFormat.QUANTITY);
                  break;
              default:
                  tgt.setValue(Questionnaire.AnswerFormat.NULL);
                  break;
          }
      }
      return tgt;
  }

  public static org.hl7.fhir.dstu2.model.Questionnaire.QuestionComponent convertQuestionnaireQuestionComponent(org.hl7.fhir.r4.model.Questionnaire.QuestionnaireItemComponent src) throws FHIRException {
    if (src == null || src.isEmpty())
      return null;
    org.hl7.fhir.dstu2.model.Questionnaire.QuestionComponent tgt = new org.hl7.fhir.dstu2.model.Questionnaire.QuestionComponent();
    ConversionContext10_40.INSTANCE.getVersionConvertor_10_40().copyBackboneElement(src,tgt);
    if (src.hasLinkIdElement())
      tgt.setLinkIdElement(String10_40.convertString(src.getLinkIdElement()));
    for (org.hl7.fhir.r4.model.Coding t : src.getCode()) tgt.addConcept(Coding10_40.convertCoding(t));
    if (src.hasTextElement())
      tgt.setTextElement(String10_40.convertString(src.getTextElement()));
    if (src.hasType())
      tgt.setTypeElement(convertQuestionnaireItemType(src.getTypeElement()));
    if (src.hasRequiredElement())
      tgt.setRequiredElement(Boolean10_40.convertBoolean(src.getRequiredElement()));
    if (src.hasRepeatsElement())
      tgt.setRepeatsElement(Boolean10_40.convertBoolean(src.getRepeatsElement()));
    if (src.hasAnswerValueSetElement())
      tgt.setOptions(Canonical10_40.convertCanonicalToReference(src.getAnswerValueSetElement()));
    for (QuestionnaireItemAnswerOptionComponent t : src.getAnswerOption())
      if (t.hasValueCoding())
        try {
          tgt.addOption(Coding10_40.convertCoding(t.getValueCoding()));
        } catch (org.hl7.fhir.exceptions.FHIRException e) {
          throw new FHIRException(e);
        }
    for (org.hl7.fhir.r4.model.Questionnaire.QuestionnaireItemComponent t : src.getItem())
      tgt.addGroup(convertQuestionnaireGroupComponent(t));
    return tgt;
  }

  public static org.hl7.fhir.r4.model.Questionnaire.QuestionnaireItemComponent convertQuestionnaireQuestionComponent(org.hl7.fhir.dstu2.model.Questionnaire.QuestionComponent src) throws FHIRException {
    if (src == null || src.isEmpty())
      return null;
    org.hl7.fhir.r4.model.Questionnaire.QuestionnaireItemComponent tgt = new org.hl7.fhir.r4.model.Questionnaire.QuestionnaireItemComponent();
    ConversionContext10_40.INSTANCE.getVersionConvertor_10_40().copyBackboneElement(src,tgt);
    if (src.hasLinkIdElement())
      tgt.setLinkIdElement(String10_40.convertString(src.getLinkIdElement()));
    for (org.hl7.fhir.dstu2.model.Coding t : src.getConcept()) tgt.addCode(Coding10_40.convertCoding(t));
    if (src.hasTextElement())
      tgt.setTextElement(String10_40.convertString(src.getTextElement()));
    if (src.hasType())
      tgt.setTypeElement(convertQuestionnaireQuestionType(src.getTypeElement()));
    if (src.hasRequiredElement())
      tgt.setRequiredElement(Boolean10_40.convertBoolean(src.getRequiredElement()));
    if (src.hasRepeatsElement())
      tgt.setRepeatsElement(Boolean10_40.convertBoolean(src.getRepeatsElement()));
    if (src.hasOptions())
      tgt.setAnswerValueSetElement(Canonical10_40.convertReferenceToCanonical(src.getOptions()));
    for (org.hl7.fhir.dstu2.model.Coding t : src.getOption())
      tgt.addAnswerOption().setValue(Coding10_40.convertCoding(t));
    for (org.hl7.fhir.dstu2.model.Questionnaire.GroupComponent t : src.getGroup())
      tgt.addItem(convertQuestionnaireGroupComponent(t));
    return tgt;
  }

  static public org.hl7.fhir.r4.model.Enumeration<org.hl7.fhir.r4.model.Questionnaire.QuestionnaireItemType> convertQuestionnaireQuestionType(org.hl7.fhir.dstu2.model.Enumeration<org.hl7.fhir.dstu2.model.Questionnaire.AnswerFormat> src) throws FHIRException {
      if (src == null || src.isEmpty())
          return null;
      org.hl7.fhir.r4.model.Enumeration<org.hl7.fhir.r4.model.Questionnaire.QuestionnaireItemType> tgt = new org.hl7.fhir.r4.model.Enumeration<>(new org.hl7.fhir.r4.model.Questionnaire.QuestionnaireItemTypeEnumFactory());
      ConversionContext10_40.INSTANCE.getVersionConvertor_10_40().copyElement(src, tgt);
      if (src.getValue() == null) {
          tgt.setValue(null);
      } else {
          switch (src.getValue()) {
              case BOOLEAN:
                  tgt.setValue(org.hl7.fhir.r4.model.Questionnaire.QuestionnaireItemType.BOOLEAN);
                  break;
              case DECIMAL:
                  tgt.setValue(org.hl7.fhir.r4.model.Questionnaire.QuestionnaireItemType.DECIMAL);
                  break;
              case INTEGER:
                  tgt.setValue(org.hl7.fhir.r4.model.Questionnaire.QuestionnaireItemType.INTEGER);
                  break;
              case DATE:
                  tgt.setValue(org.hl7.fhir.r4.model.Questionnaire.QuestionnaireItemType.DATE);
                  break;
              case DATETIME:
                  tgt.setValue(org.hl7.fhir.r4.model.Questionnaire.QuestionnaireItemType.DATETIME);
                  break;
              case INSTANT:
                  tgt.setValue(org.hl7.fhir.r4.model.Questionnaire.QuestionnaireItemType.DATETIME);
                  break;
              case TIME:
                  tgt.setValue(org.hl7.fhir.r4.model.Questionnaire.QuestionnaireItemType.TIME);
                  break;
              case STRING:
                  tgt.setValue(org.hl7.fhir.r4.model.Questionnaire.QuestionnaireItemType.STRING);
                  break;
              case TEXT:
                  tgt.setValue(org.hl7.fhir.r4.model.Questionnaire.QuestionnaireItemType.TEXT);
                  break;
              case URL:
                  tgt.setValue(org.hl7.fhir.r4.model.Questionnaire.QuestionnaireItemType.URL);
                  break;
              case CHOICE:
                  tgt.setValue(org.hl7.fhir.r4.model.Questionnaire.QuestionnaireItemType.CHOICE);
                  break;
              case OPENCHOICE:
                  tgt.setValue(org.hl7.fhir.r4.model.Questionnaire.QuestionnaireItemType.OPENCHOICE);
                  break;
              case ATTACHMENT:
                  tgt.setValue(org.hl7.fhir.r4.model.Questionnaire.QuestionnaireItemType.ATTACHMENT);
                  break;
              case REFERENCE:
                  tgt.setValue(org.hl7.fhir.r4.model.Questionnaire.QuestionnaireItemType.REFERENCE);
                  break;
              case QUANTITY:
                  tgt.setValue(org.hl7.fhir.r4.model.Questionnaire.QuestionnaireItemType.QUANTITY);
                  break;
              default:
                  tgt.setValue(org.hl7.fhir.r4.model.Questionnaire.QuestionnaireItemType.NULL);
                  break;
          }
      }
      return tgt;
  }

  static public org.hl7.fhir.dstu2.model.Enumeration<org.hl7.fhir.dstu2.model.Questionnaire.QuestionnaireStatus> convertQuestionnaireStatus(org.hl7.fhir.r4.model.Enumeration<org.hl7.fhir.r4.model.Enumerations.PublicationStatus> src) throws FHIRException {
      if (src == null || src.isEmpty())
          return null;
      Enumeration<Questionnaire.QuestionnaireStatus> tgt = new Enumeration<>(new Questionnaire.QuestionnaireStatusEnumFactory());
      ConversionContext10_40.INSTANCE.getVersionConvertor_10_40().copyElement(src, tgt);
      if (src.getValue() == null) {
          tgt.setValue(null);
      } else {
          switch (src.getValue()) {
              case DRAFT:
                  tgt.setValue(Questionnaire.QuestionnaireStatus.DRAFT);
                  break;
              case ACTIVE:
                  tgt.setValue(Questionnaire.QuestionnaireStatus.PUBLISHED);
                  break;
              case RETIRED:
                  tgt.setValue(Questionnaire.QuestionnaireStatus.RETIRED);
                  break;
              default:
                  tgt.setValue(Questionnaire.QuestionnaireStatus.NULL);
                  break;
          }
      }
      return tgt;
  }

  static public org.hl7.fhir.r4.model.Enumeration<org.hl7.fhir.r4.model.Enumerations.PublicationStatus> convertQuestionnaireStatus(org.hl7.fhir.dstu2.model.Enumeration<org.hl7.fhir.dstu2.model.Questionnaire.QuestionnaireStatus> src) throws FHIRException {
      if (src == null || src.isEmpty())
          return null;
      org.hl7.fhir.r4.model.Enumeration<Enumerations.PublicationStatus> tgt = new org.hl7.fhir.r4.model.Enumeration<>(new Enumerations.PublicationStatusEnumFactory());
      ConversionContext10_40.INSTANCE.getVersionConvertor_10_40().copyElement(src, tgt);
      if (src.getValue() == null) {
          tgt.setValue(null);
      } else {
          switch (src.getValue()) {
              case DRAFT:
                  tgt.setValue(Enumerations.PublicationStatus.DRAFT);
                  break;
              case PUBLISHED:
                  tgt.setValue(Enumerations.PublicationStatus.ACTIVE);
                  break;
              case RETIRED:
                  tgt.setValue(Enumerations.PublicationStatus.RETIRED);
                  break;
              default:
                  tgt.setValue(Enumerations.PublicationStatus.NULL);
                  break;
          }
      }
      return tgt;
  }
}
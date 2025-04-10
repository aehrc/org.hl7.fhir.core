package org.hl7.fhir.convertors.conv10_40.resources10_40;

import org.hl7.fhir.convertors.VersionConvertorConstants;
import org.hl7.fhir.convertors.context.ConversionContext10_40;
import org.hl7.fhir.convertors.conv10_40.datatypes10_40.ElementDefinition10_40;
import org.hl7.fhir.convertors.conv10_40.datatypes10_40.complextypes10_40.ContactPoint10_40;
import org.hl7.fhir.convertors.conv10_40.datatypes10_40.primitivetypes10_40.Boolean10_40;
import org.hl7.fhir.convertors.conv10_40.datatypes10_40.primitivetypes10_40.Canonical10_40;
import org.hl7.fhir.convertors.conv10_40.datatypes10_40.primitivetypes10_40.Code10_40;
import org.hl7.fhir.convertors.conv10_40.datatypes10_40.primitivetypes10_40.DateTime10_40;
import org.hl7.fhir.convertors.conv10_40.datatypes10_40.primitivetypes10_40.Integer10_40;
import org.hl7.fhir.convertors.conv10_40.datatypes10_40.primitivetypes10_40.String10_40;
import org.hl7.fhir.convertors.conv10_40.datatypes10_40.primitivetypes10_40.Uri10_40;
import org.hl7.fhir.dstu2.model.Enumeration;
import org.hl7.fhir.dstu2.model.OperationDefinition;
import org.hl7.fhir.dstu2.model.Reference;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r4.model.Enumerations.SearchParamType;
import org.hl7.fhir.r4.model.Type;
import org.hl7.fhir.utilities.Utilities;

public class OperationDefinition10_40 {

  public static org.hl7.fhir.r4.model.OperationDefinition convertOperationDefinition(org.hl7.fhir.dstu2.model.OperationDefinition src) throws FHIRException {
    if (src == null || src.isEmpty())
      return null;
    org.hl7.fhir.r4.model.OperationDefinition tgt = new org.hl7.fhir.r4.model.OperationDefinition();
    ConversionContext10_40.INSTANCE.getVersionConvertor_10_40().copyDomainResource(src, tgt);
    if (src.hasUrlElement())
      tgt.setUrlElement(Uri10_40.convertUri(src.getUrlElement()));
    if (src.hasVersionElement())
      tgt.setVersionElement(String10_40.convertString(src.getVersionElement()));
    if (src.hasNameElement())
      tgt.setNameElement(String10_40.convertString(src.getNameElement()));
    if (src.hasStatus())
      tgt.setStatusElement(Enumerations10_40.convertConformanceResourceStatus(src.getStatusElement()));
    if (src.hasKind())
      tgt.setKindElement(convertOperationKind(src.getKindElement()));
    if (src.hasExperimental())
      tgt.setExperimentalElement(Boolean10_40.convertBoolean(src.getExperimentalElement()));
    if (src.hasDate())
      tgt.setDateElement(DateTime10_40.convertDateTime(src.getDateElement()));
    if (src.hasPublisherElement())
      tgt.setPublisherElement(String10_40.convertString(src.getPublisherElement()));
    for (org.hl7.fhir.dstu2.model.OperationDefinition.OperationDefinitionContactComponent t : src.getContact())
      tgt.addContact(convertOperationDefinitionContactComponent(t));
    if (src.hasDescription())
      tgt.setDescription(src.getDescription());
    if (src.hasRequirements())
      tgt.setPurpose(src.getRequirements());
    if (src.hasIdempotent())
      tgt.setAffectsState(!src.getIdempotent());
    if (src.hasCodeElement())
      tgt.setCodeElement(Code10_40.convertCode(src.getCodeElement()));
    if (src.hasNotes())
      tgt.setComment(src.getNotes());
    if (src.hasBase())
      tgt.setBaseElement(Canonical10_40.convertReferenceToCanonical(src.getBase()));
    if (src.hasSystemElement())
      tgt.setSystemElement(Boolean10_40.convertBoolean(src.getSystemElement()));
    for (org.hl7.fhir.dstu2.model.CodeType t : src.getType()) tgt.addResource(t.getValue());
    tgt.setType(tgt.hasResource());
    if (src.hasInstanceElement())
      tgt.setInstanceElement(Boolean10_40.convertBoolean(src.getInstanceElement()));
    for (org.hl7.fhir.dstu2.model.OperationDefinition.OperationDefinitionParameterComponent t : src.getParameter())
      tgt.addParameter(convertOperationDefinitionParameterComponent(t));
    return tgt;
  }

  public static org.hl7.fhir.dstu2.model.OperationDefinition convertOperationDefinition(org.hl7.fhir.r4.model.OperationDefinition src) throws FHIRException {
    if (src == null || src.isEmpty())
      return null;
    org.hl7.fhir.dstu2.model.OperationDefinition tgt = new org.hl7.fhir.dstu2.model.OperationDefinition();
    ConversionContext10_40.INSTANCE.getVersionConvertor_10_40().copyDomainResource(src, tgt);
    if (src.hasUrlElement())
      tgt.setUrlElement(Uri10_40.convertUri(src.getUrlElement()));
    if (src.hasVersionElement())
      tgt.setVersionElement(String10_40.convertString(src.getVersionElement()));
    if (src.hasNameElement())
      tgt.setNameElement(String10_40.convertString(src.getNameElement()));
    if (src.hasStatus())
      tgt.setStatusElement(Enumerations10_40.convertConformanceResourceStatus(src.getStatusElement()));
    if (src.hasKind())
      tgt.setKindElement(convertOperationKind(src.getKindElement()));
    if (src.hasExperimental())
      tgt.setExperimentalElement(Boolean10_40.convertBoolean(src.getExperimentalElement()));
    if (src.hasDate())
      tgt.setDateElement(DateTime10_40.convertDateTime(src.getDateElement()));
    if (src.hasPublisherElement())
      tgt.setPublisherElement(String10_40.convertString(src.getPublisherElement()));
    for (org.hl7.fhir.r4.model.ContactDetail t : src.getContact())
      tgt.addContact(convertOperationDefinitionContactComponent(t));
    if (src.hasDescription())
      tgt.setDescription(src.getDescription());
    if (src.hasPurpose())
      tgt.setRequirements(src.getPurpose());
    tgt.setIdempotent(!src.getAffectsState());
    if (src.hasCodeElement())
      tgt.setCodeElement(Code10_40.convertCode(src.getCodeElement()));
    if (src.hasComment())
      tgt.setNotes(src.getComment());
    if (src.hasBase())
      tgt.setBase(Canonical10_40.convertCanonicalToReference(src.getBaseElement()));
    if (src.hasSystemElement())
      tgt.setSystemElement(Boolean10_40.convertBoolean(src.getSystemElement()));
    if (src.getType())
      for (org.hl7.fhir.r4.model.CodeType t : src.getResource()) tgt.addType(t.getValue());
    if (src.hasInstanceElement())
      tgt.setInstanceElement(Boolean10_40.convertBoolean(src.getInstanceElement()));
    for (org.hl7.fhir.r4.model.OperationDefinition.OperationDefinitionParameterComponent t : src.getParameter())
      tgt.addParameter(convertOperationDefinitionParameterComponent(t));
    return tgt;
  }

  public static org.hl7.fhir.dstu2.model.OperationDefinition.OperationDefinitionContactComponent convertOperationDefinitionContactComponent(org.hl7.fhir.r4.model.ContactDetail src) throws FHIRException {
    if (src == null || src.isEmpty())
      return null;
    org.hl7.fhir.dstu2.model.OperationDefinition.OperationDefinitionContactComponent tgt = new org.hl7.fhir.dstu2.model.OperationDefinition.OperationDefinitionContactComponent();
    ConversionContext10_40.INSTANCE.getVersionConvertor_10_40().copyElement(src, tgt);
    if (src.hasNameElement())
      tgt.setNameElement(String10_40.convertString(src.getNameElement()));
    for (org.hl7.fhir.r4.model.ContactPoint t : src.getTelecom())
      tgt.addTelecom(ContactPoint10_40.convertContactPoint(t));
    return tgt;
  }

  public static org.hl7.fhir.r4.model.ContactDetail convertOperationDefinitionContactComponent(org.hl7.fhir.dstu2.model.OperationDefinition.OperationDefinitionContactComponent src) throws FHIRException {
    if (src == null || src.isEmpty())
      return null;
    org.hl7.fhir.r4.model.ContactDetail tgt = new org.hl7.fhir.r4.model.ContactDetail();
    ConversionContext10_40.INSTANCE.getVersionConvertor_10_40().copyElement(src, tgt);
    if (src.hasNameElement())
      tgt.setNameElement(String10_40.convertString(src.getNameElement()));
    for (org.hl7.fhir.dstu2.model.ContactPoint t : src.getTelecom())
      tgt.addTelecom(ContactPoint10_40.convertContactPoint(t));
    return tgt;
  }

  public static org.hl7.fhir.r4.model.OperationDefinition.OperationDefinitionParameterBindingComponent convertOperationDefinitionParameterBindingComponent(org.hl7.fhir.dstu2.model.OperationDefinition.OperationDefinitionParameterBindingComponent src) throws FHIRException {
    if (src == null || src.isEmpty())
      return null;
    org.hl7.fhir.r4.model.OperationDefinition.OperationDefinitionParameterBindingComponent tgt = new org.hl7.fhir.r4.model.OperationDefinition.OperationDefinitionParameterBindingComponent();
    ConversionContext10_40.INSTANCE.getVersionConvertor_10_40().copyBackboneElement(src,tgt);
    if (src.hasStrength())
      tgt.setStrengthElement(ElementDefinition10_40.convertBindingStrength(src.getStrengthElement()));
    Type t = ConversionContext10_40.INSTANCE.getVersionConvertor_10_40().convertType(src.getValueSet());
    if (t != null) {
      if (t instanceof org.hl7.fhir.r4.model.Reference)
        tgt.setValueSet(((org.hl7.fhir.r4.model.Reference) t).getReference());
      else
        tgt.setValueSet(t.primitiveValue());
      tgt.setValueSet(VersionConvertorConstants.refToVS(tgt.getValueSet()));
    }
    return tgt;
  }

  public static org.hl7.fhir.dstu2.model.OperationDefinition.OperationDefinitionParameterBindingComponent convertOperationDefinitionParameterBindingComponent(org.hl7.fhir.r4.model.OperationDefinition.OperationDefinitionParameterBindingComponent src) throws FHIRException {
    if (src == null || src.isEmpty())
      return null;
    org.hl7.fhir.dstu2.model.OperationDefinition.OperationDefinitionParameterBindingComponent tgt = new org.hl7.fhir.dstu2.model.OperationDefinition.OperationDefinitionParameterBindingComponent();
    ConversionContext10_40.INSTANCE.getVersionConvertor_10_40().copyBackboneElement(src,tgt);
    if (src.hasStrength())
      tgt.setStrengthElement(ElementDefinition10_40.convertBindingStrength(src.getStrengthElement()));
    if (src.hasValueSet()) {
      String vsr = VersionConvertorConstants.vsToRef(src.getValueSet());
      if (vsr != null)
        tgt.setValueSet(new org.hl7.fhir.dstu2.model.UriType(vsr));
      else
        tgt.setValueSet(new org.hl7.fhir.dstu2.model.Reference(src.getValueSet()));
    }
    return tgt;
  }

  public static org.hl7.fhir.dstu2.model.OperationDefinition.OperationDefinitionParameterComponent convertOperationDefinitionParameterComponent(org.hl7.fhir.r4.model.OperationDefinition.OperationDefinitionParameterComponent src) throws FHIRException {
    if (src == null || src.isEmpty())
      return null;
    org.hl7.fhir.dstu2.model.OperationDefinition.OperationDefinitionParameterComponent tgt = new org.hl7.fhir.dstu2.model.OperationDefinition.OperationDefinitionParameterComponent();
    ConversionContext10_40.INSTANCE.getVersionConvertor_10_40().copyBackboneElement(src,tgt);
    if (src.hasNameElement())
      tgt.setNameElement(Code10_40.convertCode(src.getNameElement()));
    if (src.hasUse())
      tgt.setUseElement(convertOperationParameterUse(src.getUseElement()));
    if (src.hasMinElement())
      tgt.setMinElement(Integer10_40.convertInteger(src.getMinElement()));
    if (src.hasMaxElement())
      tgt.setMaxElement(String10_40.convertString(src.getMaxElement()));
    if (src.hasDocumentationElement())
      tgt.setDocumentationElement(String10_40.convertString(src.getDocumentationElement()));
    if (src.hasSearchType()) {
      tgt.setType(src.getSearchType().toCode());
    } else
      tgt.setTypeElement(Code10_40.convertCode(src.getTypeElement()));
    for (org.hl7.fhir.r4.model.UriType t : src.getTargetProfile()) tgt.setProfile(new Reference(t.getValue()));
    if (src.hasBinding())
      tgt.setBinding(convertOperationDefinitionParameterBindingComponent(src.getBinding()));
    for (org.hl7.fhir.r4.model.OperationDefinition.OperationDefinitionParameterComponent t : src.getPart())
      tgt.addPart(convertOperationDefinitionParameterComponent(t));
    return tgt;
  }

  public static org.hl7.fhir.r4.model.OperationDefinition.OperationDefinitionParameterComponent convertOperationDefinitionParameterComponent(org.hl7.fhir.dstu2.model.OperationDefinition.OperationDefinitionParameterComponent src) throws FHIRException {
    if (src == null || src.isEmpty())
      return null;
    org.hl7.fhir.r4.model.OperationDefinition.OperationDefinitionParameterComponent tgt = new org.hl7.fhir.r4.model.OperationDefinition.OperationDefinitionParameterComponent();
    ConversionContext10_40.INSTANCE.getVersionConvertor_10_40().copyBackboneElement(src,tgt);
    if (src.hasNameElement())
      tgt.setNameElement(Code10_40.convertCode(src.getNameElement()));
    if (src.hasUse())
      tgt.setUseElement(convertOperationParameterUse(src.getUseElement()));
    if (src.hasMinElement())
      tgt.setMinElement(Integer10_40.convertInteger(src.getMinElement()));
    if (src.hasMaxElement())
      tgt.setMaxElement(String10_40.convertString(src.getMaxElement()));
    if (src.hasDocumentationElement())
      tgt.setDocumentationElement(String10_40.convertString(src.getDocumentationElement()));
    if (Utilities.existsInList(src.getType(), "token", "reference", "composite", "number", "date", "quantity", "uri")) {
      tgt.setType("string");
      if (src.hasType())
        tgt.setSearchType(SearchParamType.fromCode(src.getType()));
    } else {
      if (src.hasTypeElement())
        tgt.setTypeElement(Code10_40.convertCode(src.getTypeElement()));
    }
    tgt.addTargetProfile(src.getProfile().getReference());
    if (src.hasBinding())
      tgt.setBinding(convertOperationDefinitionParameterBindingComponent(src.getBinding()));
    for (org.hl7.fhir.dstu2.model.OperationDefinition.OperationDefinitionParameterComponent t : src.getPart())
      tgt.addPart(convertOperationDefinitionParameterComponent(t));
    return tgt;
  }

  static public org.hl7.fhir.dstu2.model.Enumeration<org.hl7.fhir.dstu2.model.OperationDefinition.OperationKind> convertOperationKind(org.hl7.fhir.r4.model.Enumeration<org.hl7.fhir.r4.model.OperationDefinition.OperationKind> src) throws FHIRException {
      if (src == null || src.isEmpty())
          return null;
      Enumeration<OperationDefinition.OperationKind> tgt = new Enumeration<>(new OperationDefinition.OperationKindEnumFactory());
      ConversionContext10_40.INSTANCE.getVersionConvertor_10_40().copyElement(src, tgt);
      if (src.getValue() == null) {
          tgt.setValue(null);
      } else {
          switch (src.getValue()) {
              case OPERATION:
                  tgt.setValue(OperationDefinition.OperationKind.OPERATION);
                  break;
              case QUERY:
                  tgt.setValue(OperationDefinition.OperationKind.QUERY);
                  break;
              default:
                  tgt.setValue(OperationDefinition.OperationKind.NULL);
                  break;
          }
      }
      return tgt;
  }

  static public org.hl7.fhir.r4.model.Enumeration<org.hl7.fhir.r4.model.OperationDefinition.OperationKind> convertOperationKind(org.hl7.fhir.dstu2.model.Enumeration<org.hl7.fhir.dstu2.model.OperationDefinition.OperationKind> src) throws FHIRException {
      if (src == null || src.isEmpty())
          return null;
      org.hl7.fhir.r4.model.Enumeration<org.hl7.fhir.r4.model.OperationDefinition.OperationKind> tgt = new org.hl7.fhir.r4.model.Enumeration<>(new org.hl7.fhir.r4.model.OperationDefinition.OperationKindEnumFactory());
      ConversionContext10_40.INSTANCE.getVersionConvertor_10_40().copyElement(src, tgt);
      if (src.getValue() == null) {
          tgt.setValue(null);
      } else {
          switch (src.getValue()) {
              case OPERATION:
                  tgt.setValue(org.hl7.fhir.r4.model.OperationDefinition.OperationKind.OPERATION);
                  break;
              case QUERY:
                  tgt.setValue(org.hl7.fhir.r4.model.OperationDefinition.OperationKind.QUERY);
                  break;
              default:
                  tgt.setValue(org.hl7.fhir.r4.model.OperationDefinition.OperationKind.NULL);
                  break;
          }
      }
      return tgt;
  }

  static public org.hl7.fhir.dstu2.model.Enumeration<org.hl7.fhir.dstu2.model.OperationDefinition.OperationParameterUse> convertOperationParameterUse(org.hl7.fhir.r4.model.Enumeration<org.hl7.fhir.r4.model.OperationDefinition.OperationParameterUse> src) throws FHIRException {
      if (src == null || src.isEmpty())
          return null;
      Enumeration<OperationDefinition.OperationParameterUse> tgt = new Enumeration<>(new OperationDefinition.OperationParameterUseEnumFactory());
      ConversionContext10_40.INSTANCE.getVersionConvertor_10_40().copyElement(src, tgt);
      if (src.getValue() == null) {
          tgt.setValue(null);
      } else {
          switch (src.getValue()) {
              case IN:
                  tgt.setValue(OperationDefinition.OperationParameterUse.IN);
                  break;
              case OUT:
                  tgt.setValue(OperationDefinition.OperationParameterUse.OUT);
                  break;
              default:
                  tgt.setValue(OperationDefinition.OperationParameterUse.NULL);
                  break;
          }
      }
      return tgt;
  }

  static public org.hl7.fhir.r4.model.Enumeration<org.hl7.fhir.r4.model.OperationDefinition.OperationParameterUse> convertOperationParameterUse(org.hl7.fhir.dstu2.model.Enumeration<org.hl7.fhir.dstu2.model.OperationDefinition.OperationParameterUse> src) throws FHIRException {
      if (src == null || src.isEmpty())
          return null;
      org.hl7.fhir.r4.model.Enumeration<org.hl7.fhir.r4.model.OperationDefinition.OperationParameterUse> tgt = new org.hl7.fhir.r4.model.Enumeration<>(new org.hl7.fhir.r4.model.OperationDefinition.OperationParameterUseEnumFactory());
      ConversionContext10_40.INSTANCE.getVersionConvertor_10_40().copyElement(src, tgt);
      if (src.getValue() == null) {
          tgt.setValue(null);
      } else {
          switch (src.getValue()) {
              case IN:
                  tgt.setValue(org.hl7.fhir.r4.model.OperationDefinition.OperationParameterUse.IN);
                  break;
              case OUT:
                  tgt.setValue(org.hl7.fhir.r4.model.OperationDefinition.OperationParameterUse.OUT);
                  break;
              default:
                  tgt.setValue(org.hl7.fhir.r4.model.OperationDefinition.OperationParameterUse.NULL);
                  break;
          }
      }
      return tgt;
  }
}
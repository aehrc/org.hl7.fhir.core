package org.hl7.fhir.convertors.conv40_50.resources40_50;

import org.hl7.fhir.convertors.context.ConversionContext40_50;
import org.hl7.fhir.convertors.conv40_50.datatypes40_50.general40_50.CodeableConcept40_50;
import org.hl7.fhir.convertors.conv40_50.datatypes40_50.general40_50.Identifier40_50;
import org.hl7.fhir.convertors.conv40_50.datatypes40_50.general40_50.Period40_50;
import org.hl7.fhir.convertors.conv40_50.datatypes40_50.special40_50.Reference40_50;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r5.model.Enumeration;
import org.hl7.fhir.r5.model.Flag;

/*
  Copyright (c) 2011+, HL7, Inc.
  All rights reserved.
  
  Redistribution and use in source and binary forms, with or without modification, 
  are permitted provided that the following conditions are met:
  
   * Redistributions of source code must retain the above copyright notice, this 
     list of conditions and the following disclaimer.
   * Redistributions in binary form must reproduce the above copyright notice, 
     this list of conditions and the following disclaimer in the documentation 
     and/or other materials provided with the distribution.
   * Neither the name of HL7 nor the names of its contributors may be used to 
     endorse or promote products derived from this software without specific 
     prior written permission.
  
  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
  IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
  INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
  PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
  ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
  POSSIBILITY OF SUCH DAMAGE.
  
*/
// Generated on Sun, Feb 24, 2019 11:37+1100 for FHIR v4.0.0
public class Flag40_50 {

  public static org.hl7.fhir.r5.model.Flag convertFlag(org.hl7.fhir.r4.model.Flag src) throws FHIRException {
    if (src == null)
      return null;
    org.hl7.fhir.r5.model.Flag tgt = new org.hl7.fhir.r5.model.Flag();
    ConversionContext40_50.INSTANCE.getVersionConvertor_40_50().copyDomainResource(src, tgt);
    for (org.hl7.fhir.r4.model.Identifier t : src.getIdentifier())
      tgt.addIdentifier(Identifier40_50.convertIdentifier(t));
    if (src.hasStatus())
      tgt.setStatusElement(convertFlagStatus(src.getStatusElement()));
    for (org.hl7.fhir.r4.model.CodeableConcept t : src.getCategory())
      tgt.addCategory(CodeableConcept40_50.convertCodeableConcept(t));
    if (src.hasCode())
      tgt.setCode(CodeableConcept40_50.convertCodeableConcept(src.getCode()));
    if (src.hasSubject())
      tgt.setSubject(Reference40_50.convertReference(src.getSubject()));
    if (src.hasPeriod())
      tgt.setPeriod(Period40_50.convertPeriod(src.getPeriod()));
    if (src.hasEncounter())
      tgt.setEncounter(Reference40_50.convertReference(src.getEncounter()));
    if (src.hasAuthor())
      tgt.setAuthor(Reference40_50.convertReference(src.getAuthor()));
    return tgt;
  }

  public static org.hl7.fhir.r4.model.Flag convertFlag(org.hl7.fhir.r5.model.Flag src) throws FHIRException {
    if (src == null)
      return null;
    org.hl7.fhir.r4.model.Flag tgt = new org.hl7.fhir.r4.model.Flag();
    ConversionContext40_50.INSTANCE.getVersionConvertor_40_50().copyDomainResource(src, tgt);
    for (org.hl7.fhir.r5.model.Identifier t : src.getIdentifier())
      tgt.addIdentifier(Identifier40_50.convertIdentifier(t));
    if (src.hasStatus())
      tgt.setStatusElement(convertFlagStatus(src.getStatusElement()));
    for (org.hl7.fhir.r5.model.CodeableConcept t : src.getCategory())
      tgt.addCategory(CodeableConcept40_50.convertCodeableConcept(t));
    if (src.hasCode())
      tgt.setCode(CodeableConcept40_50.convertCodeableConcept(src.getCode()));
    if (src.hasSubject())
      tgt.setSubject(Reference40_50.convertReference(src.getSubject()));
    if (src.hasPeriod())
      tgt.setPeriod(Period40_50.convertPeriod(src.getPeriod()));
    if (src.hasEncounter())
      tgt.setEncounter(Reference40_50.convertReference(src.getEncounter()));
    if (src.hasAuthor())
      tgt.setAuthor(Reference40_50.convertReference(src.getAuthor()));
    return tgt;
  }

  static public org.hl7.fhir.r5.model.Enumeration<org.hl7.fhir.r5.model.Flag.FlagStatus> convertFlagStatus(org.hl7.fhir.r4.model.Enumeration<org.hl7.fhir.r4.model.Flag.FlagStatus> src) throws FHIRException {
      if (src == null || src.isEmpty())
          return null;
      Enumeration<Flag.FlagStatus> tgt = new Enumeration<>(new Flag.FlagStatusEnumFactory());
      ConversionContext40_50.INSTANCE.getVersionConvertor_40_50().copyElement(src, tgt);
      if (src.getValue() == null) {
          tgt.setValue(null);
      } else {
          switch (src.getValue()) {
              case ACTIVE:
                  tgt.setValue(Flag.FlagStatus.ACTIVE);
                  break;
              case INACTIVE:
                  tgt.setValue(Flag.FlagStatus.INACTIVE);
                  break;
              case ENTEREDINERROR:
                  tgt.setValue(Flag.FlagStatus.ENTEREDINERROR);
                  break;
              default:
                  tgt.setValue(Flag.FlagStatus.NULL);
                  break;
          }
      }
      return tgt;
  }

  static public org.hl7.fhir.r4.model.Enumeration<org.hl7.fhir.r4.model.Flag.FlagStatus> convertFlagStatus(org.hl7.fhir.r5.model.Enumeration<org.hl7.fhir.r5.model.Flag.FlagStatus> src) throws FHIRException {
      if (src == null || src.isEmpty())
          return null;
      org.hl7.fhir.r4.model.Enumeration<org.hl7.fhir.r4.model.Flag.FlagStatus> tgt = new org.hl7.fhir.r4.model.Enumeration<>(new org.hl7.fhir.r4.model.Flag.FlagStatusEnumFactory());
      ConversionContext40_50.INSTANCE.getVersionConvertor_40_50().copyElement(src, tgt);
      if (src.getValue() == null) {
          tgt.setValue(null);
      } else {
          switch (src.getValue()) {
              case ACTIVE:
                  tgt.setValue(org.hl7.fhir.r4.model.Flag.FlagStatus.ACTIVE);
                  break;
              case INACTIVE:
                  tgt.setValue(org.hl7.fhir.r4.model.Flag.FlagStatus.INACTIVE);
                  break;
              case ENTEREDINERROR:
                  tgt.setValue(org.hl7.fhir.r4.model.Flag.FlagStatus.ENTEREDINERROR);
                  break;
              default:
                  tgt.setValue(org.hl7.fhir.r4.model.Flag.FlagStatus.NULL);
                  break;
          }
      }
      return tgt;
  }
}
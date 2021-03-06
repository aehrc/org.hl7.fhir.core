package org.hl7.fhir.convertors.conv30_50;

/*-
 * #%L
 * org.hl7.fhir.convertors
 * %%
 * Copyright (C) 2014 - 2019 Health Level 7
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
import org.hl7.fhir.convertors.VersionConvertor_30_50;
import org.hl7.fhir.dstu3.model.Period;
import org.hl7.fhir.exceptions.FHIRException;

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
public class Provenance30_50 extends VersionConvertor_30_50 {

    public static org.hl7.fhir.r5.model.Provenance convertProvenance(org.hl7.fhir.dstu3.model.Provenance src) throws FHIRException {
        if (src == null)
            return null;
        org.hl7.fhir.r5.model.Provenance tgt = new org.hl7.fhir.r5.model.Provenance();
        copyDomainResource(src, tgt);
        for (org.hl7.fhir.dstu3.model.Reference t : src.getTarget()) tgt.addTarget(convertReference(t));
        if (src.hasPeriod())
            tgt.setOccurred(convertType(src.getPeriod()));
        if (src.hasRecorded())
            tgt.setRecordedElement(convertInstant(src.getRecordedElement()));
        for (org.hl7.fhir.dstu3.model.UriType t : src.getPolicy()) tgt.getPolicy().add(convertUri(t));
        if (src.hasLocation())
            tgt.setLocation(convertReference(src.getLocation()));
        for (org.hl7.fhir.dstu3.model.Coding t : src.getReason()) 
          tgt.addReason().addCoding(convertCoding(t));
        if (src.hasActivity())
            tgt.getActivity().addCoding(convertCoding(src.getActivity()));
        for (org.hl7.fhir.dstu3.model.Provenance.ProvenanceAgentComponent t : src.getAgent()) tgt.addAgent(convertProvenanceAgentComponent(t));
        for (org.hl7.fhir.dstu3.model.Provenance.ProvenanceEntityComponent t : src.getEntity()) tgt.addEntity(convertProvenanceEntityComponent(t));
        for (org.hl7.fhir.dstu3.model.Signature t : src.getSignature()) tgt.addSignature(convertSignature(t));
        return tgt;
    }

    public static org.hl7.fhir.dstu3.model.Provenance convertProvenance(org.hl7.fhir.r5.model.Provenance src) throws FHIRException {
        if (src == null)
            return null;
        org.hl7.fhir.dstu3.model.Provenance tgt = new org.hl7.fhir.dstu3.model.Provenance();
        copyDomainResource(src, tgt);
        for (org.hl7.fhir.r5.model.Reference t : src.getTarget()) tgt.addTarget(convertReference(t));
        if (src.hasOccurredPeriod())
            tgt.setPeriod(convertPeriod(src.getOccurredPeriod()));
        if (src.hasRecorded())
            tgt.setRecordedElement(convertInstant(src.getRecordedElement()));
        for (org.hl7.fhir.r5.model.UriType t : src.getPolicy()) tgt.getPolicy().add(convertUri(t));
        if (src.hasLocation())
            tgt.setLocation(convertReference(src.getLocation()));
        for (org.hl7.fhir.r5.model.CodeableConcept t : src.getReason())
          for (org.hl7.fhir.r5.model.Coding t2 : t.getCoding())
                tgt.addReason(convertCoding(t2));
        if (src.hasActivity())
            tgt.setActivity(convertCoding(src.getActivity().getCodingFirstRep()));
        for (org.hl7.fhir.r5.model.Provenance.ProvenanceAgentComponent t : src.getAgent()) tgt.addAgent(convertProvenanceAgentComponent(t));
        for (org.hl7.fhir.r5.model.Provenance.ProvenanceEntityComponent t : src.getEntity()) tgt.addEntity(convertProvenanceEntityComponent(t));
        for (org.hl7.fhir.r5.model.Signature t : src.getSignature()) tgt.addSignature(convertSignature(t));
        return tgt;
    }

    public static org.hl7.fhir.r5.model.Provenance.ProvenanceAgentComponent convertProvenanceAgentComponent(org.hl7.fhir.dstu3.model.Provenance.ProvenanceAgentComponent src) throws FHIRException {
        if (src == null)
            return null;
        org.hl7.fhir.r5.model.Provenance.ProvenanceAgentComponent tgt = new org.hl7.fhir.r5.model.Provenance.ProvenanceAgentComponent();
        copyElement(src, tgt);
//        if (src.hasType())
//            tgt.setType(convertCodeableConcept(src.getType()));
        for (org.hl7.fhir.dstu3.model.CodeableConcept t : src.getRole())
          tgt.addRole(convertCodeableConcept(t));
        if (src.hasWhoReference())
            tgt.setWho(convertReference(src.getWhoReference()));
        if (src.hasOnBehalfOfReference())
            tgt.setOnBehalfOf(convertReference(src.getOnBehalfOfReference()));
        return tgt;
    }

    public static org.hl7.fhir.dstu3.model.Provenance.ProvenanceAgentComponent convertProvenanceAgentComponent(org.hl7.fhir.r5.model.Provenance.ProvenanceAgentComponent src) throws FHIRException {
        if (src == null)
            return null;
        org.hl7.fhir.dstu3.model.Provenance.ProvenanceAgentComponent tgt = new org.hl7.fhir.dstu3.model.Provenance.ProvenanceAgentComponent();
        copyElement(src, tgt);
//        if (src.hasType())
//            tgt.setType(convertCodeableConcept(src.getType()));
        for (org.hl7.fhir.r5.model.CodeableConcept t : src.getRole()) tgt.addRole(convertCodeableConcept(t));
        if (src.hasWho())
            tgt.setWho(convertReference(src.getWho()));
        if (src.hasOnBehalfOf())
            tgt.setOnBehalfOf(convertReference(src.getOnBehalfOf()));
        return tgt;
    }

    public static org.hl7.fhir.r5.model.Provenance.ProvenanceEntityComponent convertProvenanceEntityComponent(org.hl7.fhir.dstu3.model.Provenance.ProvenanceEntityComponent src) throws FHIRException {
        if (src == null)
            return null;
        org.hl7.fhir.r5.model.Provenance.ProvenanceEntityComponent tgt = new org.hl7.fhir.r5.model.Provenance.ProvenanceEntityComponent();
        copyElement(src, tgt);
        if (src.hasRole())
            tgt.setRoleElement(convertProvenanceEntityRole(src.getRoleElement()));
        if (src.hasWhatReference())
            tgt.setWhat(convertReference(src.getWhatReference()));
        for (org.hl7.fhir.dstu3.model.Provenance.ProvenanceAgentComponent t : src.getAgent()) tgt.addAgent(convertProvenanceAgentComponent(t));
        return tgt;
    }

    public static org.hl7.fhir.dstu3.model.Provenance.ProvenanceEntityComponent convertProvenanceEntityComponent(org.hl7.fhir.r5.model.Provenance.ProvenanceEntityComponent src) throws FHIRException {
        if (src == null)
            return null;
        org.hl7.fhir.dstu3.model.Provenance.ProvenanceEntityComponent tgt = new org.hl7.fhir.dstu3.model.Provenance.ProvenanceEntityComponent();
        copyElement(src, tgt);
        if (src.hasRole())
            tgt.setRoleElement(convertProvenanceEntityRole(src.getRoleElement()));
        if (src.hasWhat())
            tgt.setWhat(convertReference(src.getWhat()));
        for (org.hl7.fhir.r5.model.Provenance.ProvenanceAgentComponent t : src.getAgent()) tgt.addAgent(convertProvenanceAgentComponent(t));
        return tgt;
    }

    static public org.hl7.fhir.r5.model.Enumeration<org.hl7.fhir.r5.model.Provenance.ProvenanceEntityRole> convertProvenanceEntityRole(org.hl7.fhir.dstu3.model.Enumeration<org.hl7.fhir.dstu3.model.Provenance.ProvenanceEntityRole> src) throws FHIRException {
        if (src == null || src.isEmpty())
            return null;
        org.hl7.fhir.r5.model.Enumeration<org.hl7.fhir.r5.model.Provenance.ProvenanceEntityRole> tgt = new org.hl7.fhir.r5.model.Enumeration<>(new org.hl7.fhir.r5.model.Provenance.ProvenanceEntityRoleEnumFactory());
        VersionConvertor_30_50.copyElement(src, tgt);
        switch(src.getValue()) {
            case DERIVATION:
                tgt.setValue(org.hl7.fhir.r5.model.Provenance.ProvenanceEntityRole.DERIVATION);
                break;
            case REVISION:
                tgt.setValue(org.hl7.fhir.r5.model.Provenance.ProvenanceEntityRole.REVISION);
                break;
            case QUOTATION:
                tgt.setValue(org.hl7.fhir.r5.model.Provenance.ProvenanceEntityRole.QUOTATION);
                break;
            case SOURCE:
                tgt.setValue(org.hl7.fhir.r5.model.Provenance.ProvenanceEntityRole.SOURCE);
                break;
            case REMOVAL:
                tgt.setValue(org.hl7.fhir.r5.model.Provenance.ProvenanceEntityRole.REMOVAL);
                break;
            default:
                tgt.setValue(org.hl7.fhir.r5.model.Provenance.ProvenanceEntityRole.NULL);
                break;
        }
        return tgt;
    }

    static public org.hl7.fhir.dstu3.model.Enumeration<org.hl7.fhir.dstu3.model.Provenance.ProvenanceEntityRole> convertProvenanceEntityRole(org.hl7.fhir.r5.model.Enumeration<org.hl7.fhir.r5.model.Provenance.ProvenanceEntityRole> src) throws FHIRException {
        if (src == null || src.isEmpty())
            return null;
        org.hl7.fhir.dstu3.model.Enumeration<org.hl7.fhir.dstu3.model.Provenance.ProvenanceEntityRole> tgt = new org.hl7.fhir.dstu3.model.Enumeration<>(new org.hl7.fhir.dstu3.model.Provenance.ProvenanceEntityRoleEnumFactory());
        VersionConvertor_30_50.copyElement(src, tgt);
        switch(src.getValue()) {
            case DERIVATION:
                tgt.setValue(org.hl7.fhir.dstu3.model.Provenance.ProvenanceEntityRole.DERIVATION);
                break;
            case REVISION:
                tgt.setValue(org.hl7.fhir.dstu3.model.Provenance.ProvenanceEntityRole.REVISION);
                break;
            case QUOTATION:
                tgt.setValue(org.hl7.fhir.dstu3.model.Provenance.ProvenanceEntityRole.QUOTATION);
                break;
            case SOURCE:
                tgt.setValue(org.hl7.fhir.dstu3.model.Provenance.ProvenanceEntityRole.SOURCE);
                break;
            case REMOVAL:
                tgt.setValue(org.hl7.fhir.dstu3.model.Provenance.ProvenanceEntityRole.REMOVAL);
                break;
            default:
                tgt.setValue(org.hl7.fhir.dstu3.model.Provenance.ProvenanceEntityRole.NULL);
                break;
        }
        return tgt;
    }
}

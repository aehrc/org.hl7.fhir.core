package org.hl7.fhir.r5.model;

/*-
 * #%L
 * org.hl7.fhir.r5
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

// Generated on Thu, Dec 13, 2018 14:07+1100 for FHIR v4.0.0
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.instance.model.api.ICompositeType;

import org.hl7.fhir.instance.model.api.IBaseDatatypeElement;
import org.hl7.fhir.utilities.Utilities;
import ca.uhn.fhir.model.api.annotation.ResourceDef;
import ca.uhn.fhir.model.api.annotation.SearchParamDefinition;
import org.hl7.fhir.instance.model.api.IBaseBackboneElement;
import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.ChildOrder;
import ca.uhn.fhir.model.api.annotation.Description;
import ca.uhn.fhir.model.api.annotation.Block;
/**
 * A sample to be used for analysis.
 */
@ResourceDef(name="Specimen", profile="http://hl7.org/fhir/StructureDefinition/Specimen")
public class Specimen extends DomainResource {

    public enum SpecimenStatus {
        /**
         * The physical specimen is present and in good condition.
         */
        AVAILABLE, 
        /**
         * There is no physical specimen because it is either lost, destroyed or consumed.
         */
        UNAVAILABLE, 
        /**
         * The specimen cannot be used because of a quality issue such as a broken container, contamination, or too old.
         */
        UNSATISFACTORY, 
        /**
         * The specimen was entered in error and therefore nullified.
         */
        ENTEREDINERROR, 
        /**
         * added to help the parsers with the generic types
         */
        NULL;
        public static SpecimenStatus fromCode(String codeString) throws FHIRException {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("available".equals(codeString))
          return AVAILABLE;
        if ("unavailable".equals(codeString))
          return UNAVAILABLE;
        if ("unsatisfactory".equals(codeString))
          return UNSATISFACTORY;
        if ("entered-in-error".equals(codeString))
          return ENTEREDINERROR;
        if (Configuration.isAcceptInvalidEnums())
          return null;
        else
          throw new FHIRException("Unknown SpecimenStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case AVAILABLE: return "available";
            case UNAVAILABLE: return "unavailable";
            case UNSATISFACTORY: return "unsatisfactory";
            case ENTEREDINERROR: return "entered-in-error";
            default: return "?";
          }
        }
        public String getSystem() {
          switch (this) {
            case AVAILABLE: return "http://hl7.org/fhir/specimen-status";
            case UNAVAILABLE: return "http://hl7.org/fhir/specimen-status";
            case UNSATISFACTORY: return "http://hl7.org/fhir/specimen-status";
            case ENTEREDINERROR: return "http://hl7.org/fhir/specimen-status";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case AVAILABLE: return "The physical specimen is present and in good condition.";
            case UNAVAILABLE: return "There is no physical specimen because it is either lost, destroyed or consumed.";
            case UNSATISFACTORY: return "The specimen cannot be used because of a quality issue such as a broken container, contamination, or too old.";
            case ENTEREDINERROR: return "The specimen was entered in error and therefore nullified.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case AVAILABLE: return "Available";
            case UNAVAILABLE: return "Unavailable";
            case UNSATISFACTORY: return "Unsatisfactory";
            case ENTEREDINERROR: return "Entered in Error";
            default: return "?";
          }
        }
    }

  public static class SpecimenStatusEnumFactory implements EnumFactory<SpecimenStatus> {
    public SpecimenStatus fromCode(String codeString) throws IllegalArgumentException {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("available".equals(codeString))
          return SpecimenStatus.AVAILABLE;
        if ("unavailable".equals(codeString))
          return SpecimenStatus.UNAVAILABLE;
        if ("unsatisfactory".equals(codeString))
          return SpecimenStatus.UNSATISFACTORY;
        if ("entered-in-error".equals(codeString))
          return SpecimenStatus.ENTEREDINERROR;
        throw new IllegalArgumentException("Unknown SpecimenStatus code '"+codeString+"'");
        }
        public Enumeration<SpecimenStatus> fromType(Base code) throws FHIRException {
          if (code == null)
            return null;
          if (code.isEmpty())
            return new Enumeration<SpecimenStatus>(this);
          String codeString = ((PrimitiveType) code).asStringValue();
          if (codeString == null || "".equals(codeString))
            return null;
        if ("available".equals(codeString))
          return new Enumeration<SpecimenStatus>(this, SpecimenStatus.AVAILABLE);
        if ("unavailable".equals(codeString))
          return new Enumeration<SpecimenStatus>(this, SpecimenStatus.UNAVAILABLE);
        if ("unsatisfactory".equals(codeString))
          return new Enumeration<SpecimenStatus>(this, SpecimenStatus.UNSATISFACTORY);
        if ("entered-in-error".equals(codeString))
          return new Enumeration<SpecimenStatus>(this, SpecimenStatus.ENTEREDINERROR);
        throw new FHIRException("Unknown SpecimenStatus code '"+codeString+"'");
        }
    public String toCode(SpecimenStatus code) {
      if (code == SpecimenStatus.AVAILABLE)
        return "available";
      if (code == SpecimenStatus.UNAVAILABLE)
        return "unavailable";
      if (code == SpecimenStatus.UNSATISFACTORY)
        return "unsatisfactory";
      if (code == SpecimenStatus.ENTEREDINERROR)
        return "entered-in-error";
      return "?";
      }
    public String toSystem(SpecimenStatus code) {
      return code.getSystem();
      }
    }

    @Block()
    public static class SpecimenCollectionComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * Person who collected the specimen.
         */
        @Child(name = "collector", type = {Practitioner.class, PractitionerRole.class}, order=1, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="Who collected the specimen", formalDefinition="Person who collected the specimen." )
        protected Reference collector;

        /**
         * Time when specimen was collected from subject - the physiologically relevant time.
         */
        @Child(name = "collected", type = {DateTimeType.class, Period.class}, order=2, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="Collection time", formalDefinition="Time when specimen was collected from subject - the physiologically relevant time." )
        protected DataType collected;

        /**
         * The span of time over which the collection of a specimen occurred.
         */
        @Child(name = "duration", type = {Duration.class}, order=3, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="How long it took to collect specimen", formalDefinition="The span of time over which the collection of a specimen occurred." )
        protected Duration duration;

        /**
         * The quantity of specimen collected; for instance the volume of a blood sample, or the physical measurement of an anatomic pathology sample.
         */
        @Child(name = "quantity", type = {Quantity.class}, order=4, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="The quantity of specimen collected", formalDefinition="The quantity of specimen collected; for instance the volume of a blood sample, or the physical measurement of an anatomic pathology sample." )
        protected Quantity quantity;

        /**
         * A coded value specifying the technique that is used to perform the procedure.
         */
        @Child(name = "method", type = {CodeableConcept.class}, order=5, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Technique used to perform collection", formalDefinition="A coded value specifying the technique that is used to perform the procedure." )
        @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/specimen-collection-method")
        protected CodeableConcept method;

        /**
         * Anatomical location from which the specimen was collected (if subject is a patient). This is the target site.  This element is not used for environmental specimens.
         */
        @Child(name = "bodySite", type = {CodeableConcept.class}, order=6, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Anatomical collection site", formalDefinition="Anatomical location from which the specimen was collected (if subject is a patient). This is the target site.  This element is not used for environmental specimens." )
        @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/body-site")
        protected CodeableConcept bodySite;

        /**
         * Abstinence or reduction from some or all food, drink, or both, for a period of time prior to sample collection.
         */
        @Child(name = "fastingStatus", type = {CodeableConcept.class, Duration.class}, order=7, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="Whether or how long patient abstained from food and/or drink", formalDefinition="Abstinence or reduction from some or all food, drink, or both, for a period of time prior to sample collection." )
        @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://terminology.hl7.org/ValueSet/v2-0916")
        protected DataType fastingStatus;

        private static final long serialVersionUID = -193148073L;

    /**
     * Constructor
     */
      public SpecimenCollectionComponent() {
        super();
      }

        /**
         * @return {@link #collector} (Person who collected the specimen.)
         */
        public Reference getCollector() { 
          if (this.collector == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create SpecimenCollectionComponent.collector");
            else if (Configuration.doAutoCreate())
              this.collector = new Reference(); // cc
          return this.collector;
        }

        public boolean hasCollector() { 
          return this.collector != null && !this.collector.isEmpty();
        }

        /**
         * @param value {@link #collector} (Person who collected the specimen.)
         */
        public SpecimenCollectionComponent setCollector(Reference value) { 
          this.collector = value;
          return this;
        }

        /**
         * @return {@link #collected} (Time when specimen was collected from subject - the physiologically relevant time.)
         */
        public DataType getCollected() { 
          return this.collected;
        }

        /**
         * @return {@link #collected} (Time when specimen was collected from subject - the physiologically relevant time.)
         */
        public DateTimeType getCollectedDateTimeType() throws FHIRException { 
          if (this.collected == null)
            this.collected = new DateTimeType();
          if (!(this.collected instanceof DateTimeType))
            throw new FHIRException("Type mismatch: the type DateTimeType was expected, but "+this.collected.getClass().getName()+" was encountered");
          return (DateTimeType) this.collected;
        }

        public boolean hasCollectedDateTimeType() { 
          return this != null && this.collected instanceof DateTimeType;
        }

        /**
         * @return {@link #collected} (Time when specimen was collected from subject - the physiologically relevant time.)
         */
        public Period getCollectedPeriod() throws FHIRException { 
          if (this.collected == null)
            this.collected = new Period();
          if (!(this.collected instanceof Period))
            throw new FHIRException("Type mismatch: the type Period was expected, but "+this.collected.getClass().getName()+" was encountered");
          return (Period) this.collected;
        }

        public boolean hasCollectedPeriod() { 
          return this != null && this.collected instanceof Period;
        }

        public boolean hasCollected() { 
          return this.collected != null && !this.collected.isEmpty();
        }

        /**
         * @param value {@link #collected} (Time when specimen was collected from subject - the physiologically relevant time.)
         */
        public SpecimenCollectionComponent setCollected(DataType value) { 
          if (value != null && !(value instanceof DateTimeType || value instanceof Period))
            throw new Error("Not the right type for Specimen.collection.collected[x]: "+value.fhirType());
          this.collected = value;
          return this;
        }

        /**
         * @return {@link #duration} (The span of time over which the collection of a specimen occurred.)
         */
        public Duration getDuration() { 
          if (this.duration == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create SpecimenCollectionComponent.duration");
            else if (Configuration.doAutoCreate())
              this.duration = new Duration(); // cc
          return this.duration;
        }

        public boolean hasDuration() { 
          return this.duration != null && !this.duration.isEmpty();
        }

        /**
         * @param value {@link #duration} (The span of time over which the collection of a specimen occurred.)
         */
        public SpecimenCollectionComponent setDuration(Duration value) { 
          this.duration = value;
          return this;
        }

        /**
         * @return {@link #quantity} (The quantity of specimen collected; for instance the volume of a blood sample, or the physical measurement of an anatomic pathology sample.)
         */
        public Quantity getQuantity() { 
          if (this.quantity == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create SpecimenCollectionComponent.quantity");
            else if (Configuration.doAutoCreate())
              this.quantity = new Quantity(); // cc
          return this.quantity;
        }

        public boolean hasQuantity() { 
          return this.quantity != null && !this.quantity.isEmpty();
        }

        /**
         * @param value {@link #quantity} (The quantity of specimen collected; for instance the volume of a blood sample, or the physical measurement of an anatomic pathology sample.)
         */
        public SpecimenCollectionComponent setQuantity(Quantity value) { 
          this.quantity = value;
          return this;
        }

        /**
         * @return {@link #method} (A coded value specifying the technique that is used to perform the procedure.)
         */
        public CodeableConcept getMethod() { 
          if (this.method == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create SpecimenCollectionComponent.method");
            else if (Configuration.doAutoCreate())
              this.method = new CodeableConcept(); // cc
          return this.method;
        }

        public boolean hasMethod() { 
          return this.method != null && !this.method.isEmpty();
        }

        /**
         * @param value {@link #method} (A coded value specifying the technique that is used to perform the procedure.)
         */
        public SpecimenCollectionComponent setMethod(CodeableConcept value) { 
          this.method = value;
          return this;
        }

        /**
         * @return {@link #bodySite} (Anatomical location from which the specimen was collected (if subject is a patient). This is the target site.  This element is not used for environmental specimens.)
         */
        public CodeableConcept getBodySite() { 
          if (this.bodySite == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create SpecimenCollectionComponent.bodySite");
            else if (Configuration.doAutoCreate())
              this.bodySite = new CodeableConcept(); // cc
          return this.bodySite;
        }

        public boolean hasBodySite() { 
          return this.bodySite != null && !this.bodySite.isEmpty();
        }

        /**
         * @param value {@link #bodySite} (Anatomical location from which the specimen was collected (if subject is a patient). This is the target site.  This element is not used for environmental specimens.)
         */
        public SpecimenCollectionComponent setBodySite(CodeableConcept value) { 
          this.bodySite = value;
          return this;
        }

        /**
         * @return {@link #fastingStatus} (Abstinence or reduction from some or all food, drink, or both, for a period of time prior to sample collection.)
         */
        public DataType getFastingStatus() { 
          return this.fastingStatus;
        }

        /**
         * @return {@link #fastingStatus} (Abstinence or reduction from some or all food, drink, or both, for a period of time prior to sample collection.)
         */
        public CodeableConcept getFastingStatusCodeableConcept() throws FHIRException { 
          if (this.fastingStatus == null)
            this.fastingStatus = new CodeableConcept();
          if (!(this.fastingStatus instanceof CodeableConcept))
            throw new FHIRException("Type mismatch: the type CodeableConcept was expected, but "+this.fastingStatus.getClass().getName()+" was encountered");
          return (CodeableConcept) this.fastingStatus;
        }

        public boolean hasFastingStatusCodeableConcept() { 
          return this != null && this.fastingStatus instanceof CodeableConcept;
        }

        /**
         * @return {@link #fastingStatus} (Abstinence or reduction from some or all food, drink, or both, for a period of time prior to sample collection.)
         */
        public Duration getFastingStatusDuration() throws FHIRException { 
          if (this.fastingStatus == null)
            this.fastingStatus = new Duration();
          if (!(this.fastingStatus instanceof Duration))
            throw new FHIRException("Type mismatch: the type Duration was expected, but "+this.fastingStatus.getClass().getName()+" was encountered");
          return (Duration) this.fastingStatus;
        }

        public boolean hasFastingStatusDuration() { 
          return this != null && this.fastingStatus instanceof Duration;
        }

        public boolean hasFastingStatus() { 
          return this.fastingStatus != null && !this.fastingStatus.isEmpty();
        }

        /**
         * @param value {@link #fastingStatus} (Abstinence or reduction from some or all food, drink, or both, for a period of time prior to sample collection.)
         */
        public SpecimenCollectionComponent setFastingStatus(DataType value) { 
          if (value != null && !(value instanceof CodeableConcept || value instanceof Duration))
            throw new Error("Not the right type for Specimen.collection.fastingStatus[x]: "+value.fhirType());
          this.fastingStatus = value;
          return this;
        }

        protected void listChildren(List<Property> children) {
          super.listChildren(children);
          children.add(new Property("collector", "Reference(Practitioner|PractitionerRole)", "Person who collected the specimen.", 0, 1, collector));
          children.add(new Property("collected[x]", "dateTime|Period", "Time when specimen was collected from subject - the physiologically relevant time.", 0, 1, collected));
          children.add(new Property("duration", "Duration", "The span of time over which the collection of a specimen occurred.", 0, 1, duration));
          children.add(new Property("quantity", "SimpleQuantity", "The quantity of specimen collected; for instance the volume of a blood sample, or the physical measurement of an anatomic pathology sample.", 0, 1, quantity));
          children.add(new Property("method", "CodeableConcept", "A coded value specifying the technique that is used to perform the procedure.", 0, 1, method));
          children.add(new Property("bodySite", "CodeableConcept", "Anatomical location from which the specimen was collected (if subject is a patient). This is the target site.  This element is not used for environmental specimens.", 0, 1, bodySite));
          children.add(new Property("fastingStatus[x]", "CodeableConcept|Duration", "Abstinence or reduction from some or all food, drink, or both, for a period of time prior to sample collection.", 0, 1, fastingStatus));
        }

        @Override
        public Property getNamedProperty(int _hash, String _name, boolean _checkValid) throws FHIRException {
          switch (_hash) {
          case 1883491469: /*collector*/  return new Property("collector", "Reference(Practitioner|PractitionerRole)", "Person who collected the specimen.", 0, 1, collector);
          case 1632037015: /*collected[x]*/  return new Property("collected[x]", "dateTime|Period", "Time when specimen was collected from subject - the physiologically relevant time.", 0, 1, collected);
          case 1883491145: /*collected*/  return new Property("collected[x]", "dateTime|Period", "Time when specimen was collected from subject - the physiologically relevant time.", 0, 1, collected);
          case 2005009924: /*collectedDateTime*/  return new Property("collected[x]", "dateTime|Period", "Time when specimen was collected from subject - the physiologically relevant time.", 0, 1, collected);
          case 653185642: /*collectedPeriod*/  return new Property("collected[x]", "dateTime|Period", "Time when specimen was collected from subject - the physiologically relevant time.", 0, 1, collected);
          case -1992012396: /*duration*/  return new Property("duration", "Duration", "The span of time over which the collection of a specimen occurred.", 0, 1, duration);
          case -1285004149: /*quantity*/  return new Property("quantity", "SimpleQuantity", "The quantity of specimen collected; for instance the volume of a blood sample, or the physical measurement of an anatomic pathology sample.", 0, 1, quantity);
          case -1077554975: /*method*/  return new Property("method", "CodeableConcept", "A coded value specifying the technique that is used to perform the procedure.", 0, 1, method);
          case 1702620169: /*bodySite*/  return new Property("bodySite", "CodeableConcept", "Anatomical location from which the specimen was collected (if subject is a patient). This is the target site.  This element is not used for environmental specimens.", 0, 1, bodySite);
          case -570577944: /*fastingStatus[x]*/  return new Property("fastingStatus[x]", "CodeableConcept|Duration", "Abstinence or reduction from some or all food, drink, or both, for a period of time prior to sample collection.", 0, 1, fastingStatus);
          case -701550184: /*fastingStatus*/  return new Property("fastingStatus[x]", "CodeableConcept|Duration", "Abstinence or reduction from some or all food, drink, or both, for a period of time prior to sample collection.", 0, 1, fastingStatus);
          case -1153232151: /*fastingStatusCodeableConcept*/  return new Property("fastingStatus[x]", "CodeableConcept|Duration", "Abstinence or reduction from some or all food, drink, or both, for a period of time prior to sample collection.", 0, 1, fastingStatus);
          case -433140916: /*fastingStatusDuration*/  return new Property("fastingStatus[x]", "CodeableConcept|Duration", "Abstinence or reduction from some or all food, drink, or both, for a period of time prior to sample collection.", 0, 1, fastingStatus);
          default: return super.getNamedProperty(_hash, _name, _checkValid);
          }

        }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case 1883491469: /*collector*/ return this.collector == null ? new Base[0] : new Base[] {this.collector}; // Reference
        case 1883491145: /*collected*/ return this.collected == null ? new Base[0] : new Base[] {this.collected}; // Type
        case -1992012396: /*duration*/ return this.duration == null ? new Base[0] : new Base[] {this.duration}; // Duration
        case -1285004149: /*quantity*/ return this.quantity == null ? new Base[0] : new Base[] {this.quantity}; // Quantity
        case -1077554975: /*method*/ return this.method == null ? new Base[0] : new Base[] {this.method}; // CodeableConcept
        case 1702620169: /*bodySite*/ return this.bodySite == null ? new Base[0] : new Base[] {this.bodySite}; // CodeableConcept
        case -701550184: /*fastingStatus*/ return this.fastingStatus == null ? new Base[0] : new Base[] {this.fastingStatus}; // Type
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case 1883491469: // collector
          this.collector = TypeConvertor.castToReference(value); // Reference
          return value;
        case 1883491145: // collected
          this.collected = TypeConvertor.castToType(value); // Type
          return value;
        case -1992012396: // duration
          this.duration = TypeConvertor.castToDuration(value); // Duration
          return value;
        case -1285004149: // quantity
          this.quantity = TypeConvertor.castToQuantity(value); // Quantity
          return value;
        case -1077554975: // method
          this.method = TypeConvertor.castToCodeableConcept(value); // CodeableConcept
          return value;
        case 1702620169: // bodySite
          this.bodySite = TypeConvertor.castToCodeableConcept(value); // CodeableConcept
          return value;
        case -701550184: // fastingStatus
          this.fastingStatus = TypeConvertor.castToType(value); // Type
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("collector")) {
          this.collector = TypeConvertor.castToReference(value); // Reference
        } else if (name.equals("collected[x]")) {
          this.collected = TypeConvertor.castToType(value); // Type
        } else if (name.equals("duration")) {
          this.duration = TypeConvertor.castToDuration(value); // Duration
        } else if (name.equals("quantity")) {
          this.quantity = TypeConvertor.castToQuantity(value); // Quantity
        } else if (name.equals("method")) {
          this.method = TypeConvertor.castToCodeableConcept(value); // CodeableConcept
        } else if (name.equals("bodySite")) {
          this.bodySite = TypeConvertor.castToCodeableConcept(value); // CodeableConcept
        } else if (name.equals("fastingStatus[x]")) {
          this.fastingStatus = TypeConvertor.castToType(value); // Type
        } else
          return super.setProperty(name, value);
        return value;
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 1883491469:  return getCollector();
        case 1632037015:  return getCollected();
        case 1883491145:  return getCollected();
        case -1992012396:  return getDuration();
        case -1285004149:  return getQuantity();
        case -1077554975:  return getMethod();
        case 1702620169:  return getBodySite();
        case -570577944:  return getFastingStatus();
        case -701550184:  return getFastingStatus();
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 1883491469: /*collector*/ return new String[] {"Reference"};
        case 1883491145: /*collected*/ return new String[] {"dateTime", "Period"};
        case -1992012396: /*duration*/ return new String[] {"Duration"};
        case -1285004149: /*quantity*/ return new String[] {"SimpleQuantity"};
        case -1077554975: /*method*/ return new String[] {"CodeableConcept"};
        case 1702620169: /*bodySite*/ return new String[] {"CodeableConcept"};
        case -701550184: /*fastingStatus*/ return new String[] {"CodeableConcept", "Duration"};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("collector")) {
          this.collector = new Reference();
          return this.collector;
        }
        else if (name.equals("collectedDateTime")) {
          this.collected = new DateTimeType();
          return this.collected;
        }
        else if (name.equals("collectedPeriod")) {
          this.collected = new Period();
          return this.collected;
        }
        else if (name.equals("duration")) {
          this.duration = new Duration();
          return this.duration;
        }
        else if (name.equals("quantity")) {
          this.quantity = new Quantity();
          return this.quantity;
        }
        else if (name.equals("method")) {
          this.method = new CodeableConcept();
          return this.method;
        }
        else if (name.equals("bodySite")) {
          this.bodySite = new CodeableConcept();
          return this.bodySite;
        }
        else if (name.equals("fastingStatusCodeableConcept")) {
          this.fastingStatus = new CodeableConcept();
          return this.fastingStatus;
        }
        else if (name.equals("fastingStatusDuration")) {
          this.fastingStatus = new Duration();
          return this.fastingStatus;
        }
        else
          return super.addChild(name);
      }

      public SpecimenCollectionComponent copy() {
        SpecimenCollectionComponent dst = new SpecimenCollectionComponent();
        copyValues(dst);
        return dst;
      }

      public void copyValues(SpecimenCollectionComponent dst) {
        super.copyValues(dst);
        dst.collector = collector == null ? null : collector.copy();
        dst.collected = collected == null ? null : collected.copy();
        dst.duration = duration == null ? null : duration.copy();
        dst.quantity = quantity == null ? null : quantity.copy();
        dst.method = method == null ? null : method.copy();
        dst.bodySite = bodySite == null ? null : bodySite.copy();
        dst.fastingStatus = fastingStatus == null ? null : fastingStatus.copy();
      }

      @Override
      public boolean equalsDeep(Base other_) {
        if (!super.equalsDeep(other_))
          return false;
        if (!(other_ instanceof SpecimenCollectionComponent))
          return false;
        SpecimenCollectionComponent o = (SpecimenCollectionComponent) other_;
        return compareDeep(collector, o.collector, true) && compareDeep(collected, o.collected, true) && compareDeep(duration, o.duration, true)
           && compareDeep(quantity, o.quantity, true) && compareDeep(method, o.method, true) && compareDeep(bodySite, o.bodySite, true)
           && compareDeep(fastingStatus, o.fastingStatus, true);
      }

      @Override
      public boolean equalsShallow(Base other_) {
        if (!super.equalsShallow(other_))
          return false;
        if (!(other_ instanceof SpecimenCollectionComponent))
          return false;
        SpecimenCollectionComponent o = (SpecimenCollectionComponent) other_;
        return true;
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(collector, collected, duration
          , quantity, method, bodySite, fastingStatus);
      }

  public String fhirType() {
    return "Specimen.collection";

  }

  }

    @Block()
    public static class SpecimenProcessingComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * Textual description of procedure.
         */
        @Child(name = "description", type = {StringType.class}, order=1, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Textual description of procedure", formalDefinition="Textual description of procedure." )
        protected StringType description;

        /**
         * A coded value specifying the procedure used to process the specimen.
         */
        @Child(name = "procedure", type = {CodeableConcept.class}, order=2, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Indicates the treatment step  applied to the specimen", formalDefinition="A coded value specifying the procedure used to process the specimen." )
        @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/specimen-processing-procedure")
        protected CodeableConcept procedure;

        /**
         * Material used in the processing step.
         */
        @Child(name = "additive", type = {Substance.class}, order=3, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
        @Description(shortDefinition="Material used in the processing step", formalDefinition="Material used in the processing step." )
        protected List<Reference> additive;

        /**
         * A record of the time or period when the specimen processing occurred.  For example the time of sample fixation or the period of time the sample was in formalin.
         */
        @Child(name = "time", type = {DateTimeType.class, Period.class}, order=4, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Date and time of specimen processing", formalDefinition="A record of the time or period when the specimen processing occurred.  For example the time of sample fixation or the period of time the sample was in formalin." )
        protected DataType time;

        private static final long serialVersionUID = 1180205613L;

    /**
     * Constructor
     */
      public SpecimenProcessingComponent() {
        super();
      }

        /**
         * @return {@link #description} (Textual description of procedure.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
         */
        public StringType getDescriptionElement() { 
          if (this.description == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create SpecimenProcessingComponent.description");
            else if (Configuration.doAutoCreate())
              this.description = new StringType(); // bb
          return this.description;
        }

        public boolean hasDescriptionElement() { 
          return this.description != null && !this.description.isEmpty();
        }

        public boolean hasDescription() { 
          return this.description != null && !this.description.isEmpty();
        }

        /**
         * @param value {@link #description} (Textual description of procedure.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
         */
        public SpecimenProcessingComponent setDescriptionElement(StringType value) { 
          this.description = value;
          return this;
        }

        /**
         * @return Textual description of procedure.
         */
        public String getDescription() { 
          return this.description == null ? null : this.description.getValue();
        }

        /**
         * @param value Textual description of procedure.
         */
        public SpecimenProcessingComponent setDescription(String value) { 
          if (Utilities.noString(value))
            this.description = null;
          else {
            if (this.description == null)
              this.description = new StringType();
            this.description.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #procedure} (A coded value specifying the procedure used to process the specimen.)
         */
        public CodeableConcept getProcedure() { 
          if (this.procedure == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create SpecimenProcessingComponent.procedure");
            else if (Configuration.doAutoCreate())
              this.procedure = new CodeableConcept(); // cc
          return this.procedure;
        }

        public boolean hasProcedure() { 
          return this.procedure != null && !this.procedure.isEmpty();
        }

        /**
         * @param value {@link #procedure} (A coded value specifying the procedure used to process the specimen.)
         */
        public SpecimenProcessingComponent setProcedure(CodeableConcept value) { 
          this.procedure = value;
          return this;
        }

        /**
         * @return {@link #additive} (Material used in the processing step.)
         */
        public List<Reference> getAdditive() { 
          if (this.additive == null)
            this.additive = new ArrayList<Reference>();
          return this.additive;
        }

        /**
         * @return Returns a reference to <code>this</code> for easy method chaining
         */
        public SpecimenProcessingComponent setAdditive(List<Reference> theAdditive) { 
          this.additive = theAdditive;
          return this;
        }

        public boolean hasAdditive() { 
          if (this.additive == null)
            return false;
          for (Reference item : this.additive)
            if (!item.isEmpty())
              return true;
          return false;
        }

        public Reference addAdditive() { //3
          Reference t = new Reference();
          if (this.additive == null)
            this.additive = new ArrayList<Reference>();
          this.additive.add(t);
          return t;
        }

        public SpecimenProcessingComponent addAdditive(Reference t) { //3
          if (t == null)
            return this;
          if (this.additive == null)
            this.additive = new ArrayList<Reference>();
          this.additive.add(t);
          return this;
        }

        /**
         * @return The first repetition of repeating field {@link #additive}, creating it if it does not already exist
         */
        public Reference getAdditiveFirstRep() { 
          if (getAdditive().isEmpty()) {
            addAdditive();
          }
          return getAdditive().get(0);
        }

        /**
         * @return {@link #time} (A record of the time or period when the specimen processing occurred.  For example the time of sample fixation or the period of time the sample was in formalin.)
         */
        public DataType getTime() { 
          return this.time;
        }

        /**
         * @return {@link #time} (A record of the time or period when the specimen processing occurred.  For example the time of sample fixation or the period of time the sample was in formalin.)
         */
        public DateTimeType getTimeDateTimeType() throws FHIRException { 
          if (this.time == null)
            this.time = new DateTimeType();
          if (!(this.time instanceof DateTimeType))
            throw new FHIRException("Type mismatch: the type DateTimeType was expected, but "+this.time.getClass().getName()+" was encountered");
          return (DateTimeType) this.time;
        }

        public boolean hasTimeDateTimeType() { 
          return this != null && this.time instanceof DateTimeType;
        }

        /**
         * @return {@link #time} (A record of the time or period when the specimen processing occurred.  For example the time of sample fixation or the period of time the sample was in formalin.)
         */
        public Period getTimePeriod() throws FHIRException { 
          if (this.time == null)
            this.time = new Period();
          if (!(this.time instanceof Period))
            throw new FHIRException("Type mismatch: the type Period was expected, but "+this.time.getClass().getName()+" was encountered");
          return (Period) this.time;
        }

        public boolean hasTimePeriod() { 
          return this != null && this.time instanceof Period;
        }

        public boolean hasTime() { 
          return this.time != null && !this.time.isEmpty();
        }

        /**
         * @param value {@link #time} (A record of the time or period when the specimen processing occurred.  For example the time of sample fixation or the period of time the sample was in formalin.)
         */
        public SpecimenProcessingComponent setTime(DataType value) { 
          if (value != null && !(value instanceof DateTimeType || value instanceof Period))
            throw new Error("Not the right type for Specimen.processing.time[x]: "+value.fhirType());
          this.time = value;
          return this;
        }

        protected void listChildren(List<Property> children) {
          super.listChildren(children);
          children.add(new Property("description", "string", "Textual description of procedure.", 0, 1, description));
          children.add(new Property("procedure", "CodeableConcept", "A coded value specifying the procedure used to process the specimen.", 0, 1, procedure));
          children.add(new Property("additive", "Reference(Substance)", "Material used in the processing step.", 0, java.lang.Integer.MAX_VALUE, additive));
          children.add(new Property("time[x]", "dateTime|Period", "A record of the time or period when the specimen processing occurred.  For example the time of sample fixation or the period of time the sample was in formalin.", 0, 1, time));
        }

        @Override
        public Property getNamedProperty(int _hash, String _name, boolean _checkValid) throws FHIRException {
          switch (_hash) {
          case -1724546052: /*description*/  return new Property("description", "string", "Textual description of procedure.", 0, 1, description);
          case -1095204141: /*procedure*/  return new Property("procedure", "CodeableConcept", "A coded value specifying the procedure used to process the specimen.", 0, 1, procedure);
          case -1226589236: /*additive*/  return new Property("additive", "Reference(Substance)", "Material used in the processing step.", 0, java.lang.Integer.MAX_VALUE, additive);
          case -1313930605: /*time[x]*/  return new Property("time[x]", "dateTime|Period", "A record of the time or period when the specimen processing occurred.  For example the time of sample fixation or the period of time the sample was in formalin.", 0, 1, time);
          case 3560141: /*time*/  return new Property("time[x]", "dateTime|Period", "A record of the time or period when the specimen processing occurred.  For example the time of sample fixation or the period of time the sample was in formalin.", 0, 1, time);
          case 2135345544: /*timeDateTime*/  return new Property("time[x]", "dateTime|Period", "A record of the time or period when the specimen processing occurred.  For example the time of sample fixation or the period of time the sample was in formalin.", 0, 1, time);
          case 693544686: /*timePeriod*/  return new Property("time[x]", "dateTime|Period", "A record of the time or period when the specimen processing occurred.  For example the time of sample fixation or the period of time the sample was in formalin.", 0, 1, time);
          default: return super.getNamedProperty(_hash, _name, _checkValid);
          }

        }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case -1724546052: /*description*/ return this.description == null ? new Base[0] : new Base[] {this.description}; // StringType
        case -1095204141: /*procedure*/ return this.procedure == null ? new Base[0] : new Base[] {this.procedure}; // CodeableConcept
        case -1226589236: /*additive*/ return this.additive == null ? new Base[0] : this.additive.toArray(new Base[this.additive.size()]); // Reference
        case 3560141: /*time*/ return this.time == null ? new Base[0] : new Base[] {this.time}; // Type
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case -1724546052: // description
          this.description = TypeConvertor.castToString(value); // StringType
          return value;
        case -1095204141: // procedure
          this.procedure = TypeConvertor.castToCodeableConcept(value); // CodeableConcept
          return value;
        case -1226589236: // additive
          this.getAdditive().add(TypeConvertor.castToReference(value)); // Reference
          return value;
        case 3560141: // time
          this.time = TypeConvertor.castToType(value); // Type
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("description")) {
          this.description = TypeConvertor.castToString(value); // StringType
        } else if (name.equals("procedure")) {
          this.procedure = TypeConvertor.castToCodeableConcept(value); // CodeableConcept
        } else if (name.equals("additive")) {
          this.getAdditive().add(TypeConvertor.castToReference(value));
        } else if (name.equals("time[x]")) {
          this.time = TypeConvertor.castToType(value); // Type
        } else
          return super.setProperty(name, value);
        return value;
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case -1724546052:  return getDescriptionElement();
        case -1095204141:  return getProcedure();
        case -1226589236:  return addAdditive(); 
        case -1313930605:  return getTime();
        case 3560141:  return getTime();
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case -1724546052: /*description*/ return new String[] {"string"};
        case -1095204141: /*procedure*/ return new String[] {"CodeableConcept"};
        case -1226589236: /*additive*/ return new String[] {"Reference"};
        case 3560141: /*time*/ return new String[] {"dateTime", "Period"};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("description")) {
          throw new FHIRException("Cannot call addChild on a primitive type Specimen.description");
        }
        else if (name.equals("procedure")) {
          this.procedure = new CodeableConcept();
          return this.procedure;
        }
        else if (name.equals("additive")) {
          return addAdditive();
        }
        else if (name.equals("timeDateTime")) {
          this.time = new DateTimeType();
          return this.time;
        }
        else if (name.equals("timePeriod")) {
          this.time = new Period();
          return this.time;
        }
        else
          return super.addChild(name);
      }

      public SpecimenProcessingComponent copy() {
        SpecimenProcessingComponent dst = new SpecimenProcessingComponent();
        copyValues(dst);
        return dst;
      }

      public void copyValues(SpecimenProcessingComponent dst) {
        super.copyValues(dst);
        dst.description = description == null ? null : description.copy();
        dst.procedure = procedure == null ? null : procedure.copy();
        if (additive != null) {
          dst.additive = new ArrayList<Reference>();
          for (Reference i : additive)
            dst.additive.add(i.copy());
        };
        dst.time = time == null ? null : time.copy();
      }

      @Override
      public boolean equalsDeep(Base other_) {
        if (!super.equalsDeep(other_))
          return false;
        if (!(other_ instanceof SpecimenProcessingComponent))
          return false;
        SpecimenProcessingComponent o = (SpecimenProcessingComponent) other_;
        return compareDeep(description, o.description, true) && compareDeep(procedure, o.procedure, true)
           && compareDeep(additive, o.additive, true) && compareDeep(time, o.time, true);
      }

      @Override
      public boolean equalsShallow(Base other_) {
        if (!super.equalsShallow(other_))
          return false;
        if (!(other_ instanceof SpecimenProcessingComponent))
          return false;
        SpecimenProcessingComponent o = (SpecimenProcessingComponent) other_;
        return compareValues(description, o.description, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(description, procedure, additive
          , time);
      }

  public String fhirType() {
    return "Specimen.processing";

  }

  }

    @Block()
    public static class SpecimenContainerComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * Id for container. There may be multiple; a manufacturer's bar code, lab assigned identifier, etc. The container ID may differ from the specimen id in some circumstances.
         */
        @Child(name = "identifier", type = {Identifier.class}, order=1, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
        @Description(shortDefinition="Id for the container", formalDefinition="Id for container. There may be multiple; a manufacturer's bar code, lab assigned identifier, etc. The container ID may differ from the specimen id in some circumstances." )
        protected List<Identifier> identifier;

        /**
         * Textual description of the container.
         */
        @Child(name = "description", type = {StringType.class}, order=2, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Textual description of the container", formalDefinition="Textual description of the container." )
        protected StringType description;

        /**
         * The type of container associated with the specimen (e.g. slide, aliquot, etc.).
         */
        @Child(name = "type", type = {CodeableConcept.class}, order=3, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Kind of container directly associated with specimen", formalDefinition="The type of container associated with the specimen (e.g. slide, aliquot, etc.)." )
        @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/specimen-container-type")
        protected CodeableConcept type;

        /**
         * The capacity (volume or other measure) the container may contain.
         */
        @Child(name = "capacity", type = {Quantity.class}, order=4, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Container volume or size", formalDefinition="The capacity (volume or other measure) the container may contain." )
        protected Quantity capacity;

        /**
         * The quantity of specimen in the container; may be volume, dimensions, or other appropriate measurements, depending on the specimen type.
         */
        @Child(name = "specimenQuantity", type = {Quantity.class}, order=5, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Quantity of specimen within container", formalDefinition="The quantity of specimen in the container; may be volume, dimensions, or other appropriate measurements, depending on the specimen type." )
        protected Quantity specimenQuantity;

        /**
         * Introduced substance to preserve, maintain or enhance the specimen. Examples: Formalin, Citrate, EDTA.
         */
        @Child(name = "additive", type = {CodeableConcept.class, Substance.class}, order=6, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Additive associated with container", formalDefinition="Introduced substance to preserve, maintain or enhance the specimen. Examples: Formalin, Citrate, EDTA." )
        @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://terminology.hl7.org/ValueSet/v2-0371")
        protected DataType additive;

        private static final long serialVersionUID = -1608132325L;

    /**
     * Constructor
     */
      public SpecimenContainerComponent() {
        super();
      }

        /**
         * @return {@link #identifier} (Id for container. There may be multiple; a manufacturer's bar code, lab assigned identifier, etc. The container ID may differ from the specimen id in some circumstances.)
         */
        public List<Identifier> getIdentifier() { 
          if (this.identifier == null)
            this.identifier = new ArrayList<Identifier>();
          return this.identifier;
        }

        /**
         * @return Returns a reference to <code>this</code> for easy method chaining
         */
        public SpecimenContainerComponent setIdentifier(List<Identifier> theIdentifier) { 
          this.identifier = theIdentifier;
          return this;
        }

        public boolean hasIdentifier() { 
          if (this.identifier == null)
            return false;
          for (Identifier item : this.identifier)
            if (!item.isEmpty())
              return true;
          return false;
        }

        public Identifier addIdentifier() { //3
          Identifier t = new Identifier();
          if (this.identifier == null)
            this.identifier = new ArrayList<Identifier>();
          this.identifier.add(t);
          return t;
        }

        public SpecimenContainerComponent addIdentifier(Identifier t) { //3
          if (t == null)
            return this;
          if (this.identifier == null)
            this.identifier = new ArrayList<Identifier>();
          this.identifier.add(t);
          return this;
        }

        /**
         * @return The first repetition of repeating field {@link #identifier}, creating it if it does not already exist
         */
        public Identifier getIdentifierFirstRep() { 
          if (getIdentifier().isEmpty()) {
            addIdentifier();
          }
          return getIdentifier().get(0);
        }

        /**
         * @return {@link #description} (Textual description of the container.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
         */
        public StringType getDescriptionElement() { 
          if (this.description == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create SpecimenContainerComponent.description");
            else if (Configuration.doAutoCreate())
              this.description = new StringType(); // bb
          return this.description;
        }

        public boolean hasDescriptionElement() { 
          return this.description != null && !this.description.isEmpty();
        }

        public boolean hasDescription() { 
          return this.description != null && !this.description.isEmpty();
        }

        /**
         * @param value {@link #description} (Textual description of the container.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
         */
        public SpecimenContainerComponent setDescriptionElement(StringType value) { 
          this.description = value;
          return this;
        }

        /**
         * @return Textual description of the container.
         */
        public String getDescription() { 
          return this.description == null ? null : this.description.getValue();
        }

        /**
         * @param value Textual description of the container.
         */
        public SpecimenContainerComponent setDescription(String value) { 
          if (Utilities.noString(value))
            this.description = null;
          else {
            if (this.description == null)
              this.description = new StringType();
            this.description.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #type} (The type of container associated with the specimen (e.g. slide, aliquot, etc.).)
         */
        public CodeableConcept getType() { 
          if (this.type == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create SpecimenContainerComponent.type");
            else if (Configuration.doAutoCreate())
              this.type = new CodeableConcept(); // cc
          return this.type;
        }

        public boolean hasType() { 
          return this.type != null && !this.type.isEmpty();
        }

        /**
         * @param value {@link #type} (The type of container associated with the specimen (e.g. slide, aliquot, etc.).)
         */
        public SpecimenContainerComponent setType(CodeableConcept value) { 
          this.type = value;
          return this;
        }

        /**
         * @return {@link #capacity} (The capacity (volume or other measure) the container may contain.)
         */
        public Quantity getCapacity() { 
          if (this.capacity == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create SpecimenContainerComponent.capacity");
            else if (Configuration.doAutoCreate())
              this.capacity = new Quantity(); // cc
          return this.capacity;
        }

        public boolean hasCapacity() { 
          return this.capacity != null && !this.capacity.isEmpty();
        }

        /**
         * @param value {@link #capacity} (The capacity (volume or other measure) the container may contain.)
         */
        public SpecimenContainerComponent setCapacity(Quantity value) { 
          this.capacity = value;
          return this;
        }

        /**
         * @return {@link #specimenQuantity} (The quantity of specimen in the container; may be volume, dimensions, or other appropriate measurements, depending on the specimen type.)
         */
        public Quantity getSpecimenQuantity() { 
          if (this.specimenQuantity == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create SpecimenContainerComponent.specimenQuantity");
            else if (Configuration.doAutoCreate())
              this.specimenQuantity = new Quantity(); // cc
          return this.specimenQuantity;
        }

        public boolean hasSpecimenQuantity() { 
          return this.specimenQuantity != null && !this.specimenQuantity.isEmpty();
        }

        /**
         * @param value {@link #specimenQuantity} (The quantity of specimen in the container; may be volume, dimensions, or other appropriate measurements, depending on the specimen type.)
         */
        public SpecimenContainerComponent setSpecimenQuantity(Quantity value) { 
          this.specimenQuantity = value;
          return this;
        }

        /**
         * @return {@link #additive} (Introduced substance to preserve, maintain or enhance the specimen. Examples: Formalin, Citrate, EDTA.)
         */
        public DataType getAdditive() { 
          return this.additive;
        }

        /**
         * @return {@link #additive} (Introduced substance to preserve, maintain or enhance the specimen. Examples: Formalin, Citrate, EDTA.)
         */
        public CodeableConcept getAdditiveCodeableConcept() throws FHIRException { 
          if (this.additive == null)
            this.additive = new CodeableConcept();
          if (!(this.additive instanceof CodeableConcept))
            throw new FHIRException("Type mismatch: the type CodeableConcept was expected, but "+this.additive.getClass().getName()+" was encountered");
          return (CodeableConcept) this.additive;
        }

        public boolean hasAdditiveCodeableConcept() { 
          return this != null && this.additive instanceof CodeableConcept;
        }

        /**
         * @return {@link #additive} (Introduced substance to preserve, maintain or enhance the specimen. Examples: Formalin, Citrate, EDTA.)
         */
        public Reference getAdditiveReference() throws FHIRException { 
          if (this.additive == null)
            this.additive = new Reference();
          if (!(this.additive instanceof Reference))
            throw new FHIRException("Type mismatch: the type Reference was expected, but "+this.additive.getClass().getName()+" was encountered");
          return (Reference) this.additive;
        }

        public boolean hasAdditiveReference() { 
          return this != null && this.additive instanceof Reference;
        }

        public boolean hasAdditive() { 
          return this.additive != null && !this.additive.isEmpty();
        }

        /**
         * @param value {@link #additive} (Introduced substance to preserve, maintain or enhance the specimen. Examples: Formalin, Citrate, EDTA.)
         */
        public SpecimenContainerComponent setAdditive(DataType value) { 
          if (value != null && !(value instanceof CodeableConcept || value instanceof Reference))
            throw new Error("Not the right type for Specimen.container.additive[x]: "+value.fhirType());
          this.additive = value;
          return this;
        }

        protected void listChildren(List<Property> children) {
          super.listChildren(children);
          children.add(new Property("identifier", "Identifier", "Id for container. There may be multiple; a manufacturer's bar code, lab assigned identifier, etc. The container ID may differ from the specimen id in some circumstances.", 0, java.lang.Integer.MAX_VALUE, identifier));
          children.add(new Property("description", "string", "Textual description of the container.", 0, 1, description));
          children.add(new Property("type", "CodeableConcept", "The type of container associated with the specimen (e.g. slide, aliquot, etc.).", 0, 1, type));
          children.add(new Property("capacity", "SimpleQuantity", "The capacity (volume or other measure) the container may contain.", 0, 1, capacity));
          children.add(new Property("specimenQuantity", "SimpleQuantity", "The quantity of specimen in the container; may be volume, dimensions, or other appropriate measurements, depending on the specimen type.", 0, 1, specimenQuantity));
          children.add(new Property("additive[x]", "CodeableConcept|Reference(Substance)", "Introduced substance to preserve, maintain or enhance the specimen. Examples: Formalin, Citrate, EDTA.", 0, 1, additive));
        }

        @Override
        public Property getNamedProperty(int _hash, String _name, boolean _checkValid) throws FHIRException {
          switch (_hash) {
          case -1618432855: /*identifier*/  return new Property("identifier", "Identifier", "Id for container. There may be multiple; a manufacturer's bar code, lab assigned identifier, etc. The container ID may differ from the specimen id in some circumstances.", 0, java.lang.Integer.MAX_VALUE, identifier);
          case -1724546052: /*description*/  return new Property("description", "string", "Textual description of the container.", 0, 1, description);
          case 3575610: /*type*/  return new Property("type", "CodeableConcept", "The type of container associated with the specimen (e.g. slide, aliquot, etc.).", 0, 1, type);
          case -67824454: /*capacity*/  return new Property("capacity", "SimpleQuantity", "The capacity (volume or other measure) the container may contain.", 0, 1, capacity);
          case 1485980595: /*specimenQuantity*/  return new Property("specimenQuantity", "SimpleQuantity", "The quantity of specimen in the container; may be volume, dimensions, or other appropriate measurements, depending on the specimen type.", 0, 1, specimenQuantity);
          case 261915956: /*additive[x]*/  return new Property("additive[x]", "CodeableConcept|Reference(Substance)", "Introduced substance to preserve, maintain or enhance the specimen. Examples: Formalin, Citrate, EDTA.", 0, 1, additive);
          case -1226589236: /*additive*/  return new Property("additive[x]", "CodeableConcept|Reference(Substance)", "Introduced substance to preserve, maintain or enhance the specimen. Examples: Formalin, Citrate, EDTA.", 0, 1, additive);
          case 1330272821: /*additiveCodeableConcept*/  return new Property("additive[x]", "CodeableConcept|Reference(Substance)", "Introduced substance to preserve, maintain or enhance the specimen. Examples: Formalin, Citrate, EDTA.", 0, 1, additive);
          case -386783009: /*additiveReference*/  return new Property("additive[x]", "CodeableConcept|Reference(Substance)", "Introduced substance to preserve, maintain or enhance the specimen. Examples: Formalin, Citrate, EDTA.", 0, 1, additive);
          default: return super.getNamedProperty(_hash, _name, _checkValid);
          }

        }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case -1618432855: /*identifier*/ return this.identifier == null ? new Base[0] : this.identifier.toArray(new Base[this.identifier.size()]); // Identifier
        case -1724546052: /*description*/ return this.description == null ? new Base[0] : new Base[] {this.description}; // StringType
        case 3575610: /*type*/ return this.type == null ? new Base[0] : new Base[] {this.type}; // CodeableConcept
        case -67824454: /*capacity*/ return this.capacity == null ? new Base[0] : new Base[] {this.capacity}; // Quantity
        case 1485980595: /*specimenQuantity*/ return this.specimenQuantity == null ? new Base[0] : new Base[] {this.specimenQuantity}; // Quantity
        case -1226589236: /*additive*/ return this.additive == null ? new Base[0] : new Base[] {this.additive}; // Type
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case -1618432855: // identifier
          this.getIdentifier().add(TypeConvertor.castToIdentifier(value)); // Identifier
          return value;
        case -1724546052: // description
          this.description = TypeConvertor.castToString(value); // StringType
          return value;
        case 3575610: // type
          this.type = TypeConvertor.castToCodeableConcept(value); // CodeableConcept
          return value;
        case -67824454: // capacity
          this.capacity = TypeConvertor.castToQuantity(value); // Quantity
          return value;
        case 1485980595: // specimenQuantity
          this.specimenQuantity = TypeConvertor.castToQuantity(value); // Quantity
          return value;
        case -1226589236: // additive
          this.additive = TypeConvertor.castToType(value); // Type
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("identifier")) {
          this.getIdentifier().add(TypeConvertor.castToIdentifier(value));
        } else if (name.equals("description")) {
          this.description = TypeConvertor.castToString(value); // StringType
        } else if (name.equals("type")) {
          this.type = TypeConvertor.castToCodeableConcept(value); // CodeableConcept
        } else if (name.equals("capacity")) {
          this.capacity = TypeConvertor.castToQuantity(value); // Quantity
        } else if (name.equals("specimenQuantity")) {
          this.specimenQuantity = TypeConvertor.castToQuantity(value); // Quantity
        } else if (name.equals("additive[x]")) {
          this.additive = TypeConvertor.castToType(value); // Type
        } else
          return super.setProperty(name, value);
        return value;
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case -1618432855:  return addIdentifier(); 
        case -1724546052:  return getDescriptionElement();
        case 3575610:  return getType();
        case -67824454:  return getCapacity();
        case 1485980595:  return getSpecimenQuantity();
        case 261915956:  return getAdditive();
        case -1226589236:  return getAdditive();
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case -1618432855: /*identifier*/ return new String[] {"Identifier"};
        case -1724546052: /*description*/ return new String[] {"string"};
        case 3575610: /*type*/ return new String[] {"CodeableConcept"};
        case -67824454: /*capacity*/ return new String[] {"SimpleQuantity"};
        case 1485980595: /*specimenQuantity*/ return new String[] {"SimpleQuantity"};
        case -1226589236: /*additive*/ return new String[] {"CodeableConcept", "Reference"};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("identifier")) {
          return addIdentifier();
        }
        else if (name.equals("description")) {
          throw new FHIRException("Cannot call addChild on a primitive type Specimen.description");
        }
        else if (name.equals("type")) {
          this.type = new CodeableConcept();
          return this.type;
        }
        else if (name.equals("capacity")) {
          this.capacity = new Quantity();
          return this.capacity;
        }
        else if (name.equals("specimenQuantity")) {
          this.specimenQuantity = new Quantity();
          return this.specimenQuantity;
        }
        else if (name.equals("additiveCodeableConcept")) {
          this.additive = new CodeableConcept();
          return this.additive;
        }
        else if (name.equals("additiveReference")) {
          this.additive = new Reference();
          return this.additive;
        }
        else
          return super.addChild(name);
      }

      public SpecimenContainerComponent copy() {
        SpecimenContainerComponent dst = new SpecimenContainerComponent();
        copyValues(dst);
        return dst;
      }

      public void copyValues(SpecimenContainerComponent dst) {
        super.copyValues(dst);
        if (identifier != null) {
          dst.identifier = new ArrayList<Identifier>();
          for (Identifier i : identifier)
            dst.identifier.add(i.copy());
        };
        dst.description = description == null ? null : description.copy();
        dst.type = type == null ? null : type.copy();
        dst.capacity = capacity == null ? null : capacity.copy();
        dst.specimenQuantity = specimenQuantity == null ? null : specimenQuantity.copy();
        dst.additive = additive == null ? null : additive.copy();
      }

      @Override
      public boolean equalsDeep(Base other_) {
        if (!super.equalsDeep(other_))
          return false;
        if (!(other_ instanceof SpecimenContainerComponent))
          return false;
        SpecimenContainerComponent o = (SpecimenContainerComponent) other_;
        return compareDeep(identifier, o.identifier, true) && compareDeep(description, o.description, true)
           && compareDeep(type, o.type, true) && compareDeep(capacity, o.capacity, true) && compareDeep(specimenQuantity, o.specimenQuantity, true)
           && compareDeep(additive, o.additive, true);
      }

      @Override
      public boolean equalsShallow(Base other_) {
        if (!super.equalsShallow(other_))
          return false;
        if (!(other_ instanceof SpecimenContainerComponent))
          return false;
        SpecimenContainerComponent o = (SpecimenContainerComponent) other_;
        return compareValues(description, o.description, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(identifier, description, type
          , capacity, specimenQuantity, additive);
      }

  public String fhirType() {
    return "Specimen.container";

  }

  }

    /**
     * Id for specimen.
     */
    @Child(name = "identifier", type = {Identifier.class}, order=0, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
    @Description(shortDefinition="External Identifier", formalDefinition="Id for specimen." )
    protected List<Identifier> identifier;

    /**
     * The identifier assigned by the lab when accessioning specimen(s). This is not necessarily the same as the specimen identifier, depending on local lab procedures.
     */
    @Child(name = "accessionIdentifier", type = {Identifier.class}, order=1, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Identifier assigned by the lab", formalDefinition="The identifier assigned by the lab when accessioning specimen(s). This is not necessarily the same as the specimen identifier, depending on local lab procedures." )
    protected Identifier accessionIdentifier;

    /**
     * The availability of the specimen.
     */
    @Child(name = "status", type = {CodeType.class}, order=2, min=0, max=1, modifier=true, summary=true)
    @Description(shortDefinition="available | unavailable | unsatisfactory | entered-in-error", formalDefinition="The availability of the specimen." )
    @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/specimen-status")
    protected Enumeration<SpecimenStatus> status;

    /**
     * The kind of material that forms the specimen.
     */
    @Child(name = "type", type = {CodeableConcept.class}, order=3, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Kind of material that forms the specimen", formalDefinition="The kind of material that forms the specimen." )
    @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://terminology.hl7.org/ValueSet/v2-0487")
    protected CodeableConcept type;

    /**
     * Where the specimen came from. This may be from patient(s), from a location (e.g., the source of an environmental sample), or a sampling of a substance or a device.
     */
    @Child(name = "subject", type = {Patient.class, Group.class, Device.class, Substance.class, Location.class}, order=4, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Where the specimen came from. This may be from patient(s), from a location (e.g., the source of an environmental sample), or a sampling of a substance or a device", formalDefinition="Where the specimen came from. This may be from patient(s), from a location (e.g., the source of an environmental sample), or a sampling of a substance or a device." )
    protected Reference subject;

    /**
     * Time when specimen was received for processing or testing.
     */
    @Child(name = "receivedTime", type = {DateTimeType.class}, order=5, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="The time when specimen was received for processing", formalDefinition="Time when specimen was received for processing or testing." )
    protected DateTimeType receivedTime;

    /**
     * Reference to the parent (source) specimen which is used when the specimen was either derived from or a component of another specimen.
     */
    @Child(name = "parent", type = {Specimen.class}, order=6, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
    @Description(shortDefinition="Specimen from which this specimen originated", formalDefinition="Reference to the parent (source) specimen which is used when the specimen was either derived from or a component of another specimen." )
    protected List<Reference> parent;

    /**
     * Details concerning a service request that required a specimen to be collected.
     */
    @Child(name = "request", type = {ServiceRequest.class}, order=7, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
    @Description(shortDefinition="Why the specimen was collected", formalDefinition="Details concerning a service request that required a specimen to be collected." )
    protected List<Reference> request;

    /**
     * Details concerning the specimen collection.
     */
    @Child(name = "collection", type = {}, order=8, min=0, max=1, modifier=false, summary=false)
    @Description(shortDefinition="Collection details", formalDefinition="Details concerning the specimen collection." )
    protected SpecimenCollectionComponent collection;

    /**
     * Details concerning processing and processing steps for the specimen.
     */
    @Child(name = "processing", type = {}, order=9, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
    @Description(shortDefinition="Processing and processing step details", formalDefinition="Details concerning processing and processing steps for the specimen." )
    protected List<SpecimenProcessingComponent> processing;

    /**
     * The container holding the specimen.  The recursive nature of containers; i.e. blood in tube in tray in rack is not addressed here.
     */
    @Child(name = "container", type = {}, order=10, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
    @Description(shortDefinition="Direct container of specimen (tube/slide, etc.)", formalDefinition="The container holding the specimen.  The recursive nature of containers; i.e. blood in tube in tray in rack is not addressed here." )
    protected List<SpecimenContainerComponent> container;

    /**
     * A mode or state of being that describes the nature of the specimen.
     */
    @Child(name = "condition", type = {CodeableConcept.class}, order=11, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
    @Description(shortDefinition="State of the specimen", formalDefinition="A mode or state of being that describes the nature of the specimen." )
    @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://terminology.hl7.org/ValueSet/v2-0493")
    protected List<CodeableConcept> condition;

    /**
     * To communicate any details or issues about the specimen or during the specimen collection. (for example: broken vial, sent with patient, frozen).
     */
    @Child(name = "note", type = {Annotation.class}, order=12, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
    @Description(shortDefinition="Comments", formalDefinition="To communicate any details or issues about the specimen or during the specimen collection. (for example: broken vial, sent with patient, frozen)." )
    protected List<Annotation> note;

    private static final long serialVersionUID = -1069243129L;

  /**
   * Constructor
   */
    public Specimen() {
      super();
    }

    /**
     * @return {@link #identifier} (Id for specimen.)
     */
    public List<Identifier> getIdentifier() { 
      if (this.identifier == null)
        this.identifier = new ArrayList<Identifier>();
      return this.identifier;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public Specimen setIdentifier(List<Identifier> theIdentifier) { 
      this.identifier = theIdentifier;
      return this;
    }

    public boolean hasIdentifier() { 
      if (this.identifier == null)
        return false;
      for (Identifier item : this.identifier)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public Identifier addIdentifier() { //3
      Identifier t = new Identifier();
      if (this.identifier == null)
        this.identifier = new ArrayList<Identifier>();
      this.identifier.add(t);
      return t;
    }

    public Specimen addIdentifier(Identifier t) { //3
      if (t == null)
        return this;
      if (this.identifier == null)
        this.identifier = new ArrayList<Identifier>();
      this.identifier.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #identifier}, creating it if it does not already exist
     */
    public Identifier getIdentifierFirstRep() { 
      if (getIdentifier().isEmpty()) {
        addIdentifier();
      }
      return getIdentifier().get(0);
    }

    /**
     * @return {@link #accessionIdentifier} (The identifier assigned by the lab when accessioning specimen(s). This is not necessarily the same as the specimen identifier, depending on local lab procedures.)
     */
    public Identifier getAccessionIdentifier() { 
      if (this.accessionIdentifier == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Specimen.accessionIdentifier");
        else if (Configuration.doAutoCreate())
          this.accessionIdentifier = new Identifier(); // cc
      return this.accessionIdentifier;
    }

    public boolean hasAccessionIdentifier() { 
      return this.accessionIdentifier != null && !this.accessionIdentifier.isEmpty();
    }

    /**
     * @param value {@link #accessionIdentifier} (The identifier assigned by the lab when accessioning specimen(s). This is not necessarily the same as the specimen identifier, depending on local lab procedures.)
     */
    public Specimen setAccessionIdentifier(Identifier value) { 
      this.accessionIdentifier = value;
      return this;
    }

    /**
     * @return {@link #status} (The availability of the specimen.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Enumeration<SpecimenStatus> getStatusElement() { 
      if (this.status == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Specimen.status");
        else if (Configuration.doAutoCreate())
          this.status = new Enumeration<SpecimenStatus>(new SpecimenStatusEnumFactory()); // bb
      return this.status;
    }

    public boolean hasStatusElement() { 
      return this.status != null && !this.status.isEmpty();
    }

    public boolean hasStatus() { 
      return this.status != null && !this.status.isEmpty();
    }

    /**
     * @param value {@link #status} (The availability of the specimen.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Specimen setStatusElement(Enumeration<SpecimenStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The availability of the specimen.
     */
    public SpecimenStatus getStatus() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The availability of the specimen.
     */
    public Specimen setStatus(SpecimenStatus value) { 
      if (value == null)
        this.status = null;
      else {
        if (this.status == null)
          this.status = new Enumeration<SpecimenStatus>(new SpecimenStatusEnumFactory());
        this.status.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #type} (The kind of material that forms the specimen.)
     */
    public CodeableConcept getType() { 
      if (this.type == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Specimen.type");
        else if (Configuration.doAutoCreate())
          this.type = new CodeableConcept(); // cc
      return this.type;
    }

    public boolean hasType() { 
      return this.type != null && !this.type.isEmpty();
    }

    /**
     * @param value {@link #type} (The kind of material that forms the specimen.)
     */
    public Specimen setType(CodeableConcept value) { 
      this.type = value;
      return this;
    }

    /**
     * @return {@link #subject} (Where the specimen came from. This may be from patient(s), from a location (e.g., the source of an environmental sample), or a sampling of a substance or a device.)
     */
    public Reference getSubject() { 
      if (this.subject == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Specimen.subject");
        else if (Configuration.doAutoCreate())
          this.subject = new Reference(); // cc
      return this.subject;
    }

    public boolean hasSubject() { 
      return this.subject != null && !this.subject.isEmpty();
    }

    /**
     * @param value {@link #subject} (Where the specimen came from. This may be from patient(s), from a location (e.g., the source of an environmental sample), or a sampling of a substance or a device.)
     */
    public Specimen setSubject(Reference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #receivedTime} (Time when specimen was received for processing or testing.). This is the underlying object with id, value and extensions. The accessor "getReceivedTime" gives direct access to the value
     */
    public DateTimeType getReceivedTimeElement() { 
      if (this.receivedTime == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Specimen.receivedTime");
        else if (Configuration.doAutoCreate())
          this.receivedTime = new DateTimeType(); // bb
      return this.receivedTime;
    }

    public boolean hasReceivedTimeElement() { 
      return this.receivedTime != null && !this.receivedTime.isEmpty();
    }

    public boolean hasReceivedTime() { 
      return this.receivedTime != null && !this.receivedTime.isEmpty();
    }

    /**
     * @param value {@link #receivedTime} (Time when specimen was received for processing or testing.). This is the underlying object with id, value and extensions. The accessor "getReceivedTime" gives direct access to the value
     */
    public Specimen setReceivedTimeElement(DateTimeType value) { 
      this.receivedTime = value;
      return this;
    }

    /**
     * @return Time when specimen was received for processing or testing.
     */
    public Date getReceivedTime() { 
      return this.receivedTime == null ? null : this.receivedTime.getValue();
    }

    /**
     * @param value Time when specimen was received for processing or testing.
     */
    public Specimen setReceivedTime(Date value) { 
      if (value == null)
        this.receivedTime = null;
      else {
        if (this.receivedTime == null)
          this.receivedTime = new DateTimeType();
        this.receivedTime.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #parent} (Reference to the parent (source) specimen which is used when the specimen was either derived from or a component of another specimen.)
     */
    public List<Reference> getParent() { 
      if (this.parent == null)
        this.parent = new ArrayList<Reference>();
      return this.parent;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public Specimen setParent(List<Reference> theParent) { 
      this.parent = theParent;
      return this;
    }

    public boolean hasParent() { 
      if (this.parent == null)
        return false;
      for (Reference item : this.parent)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public Reference addParent() { //3
      Reference t = new Reference();
      if (this.parent == null)
        this.parent = new ArrayList<Reference>();
      this.parent.add(t);
      return t;
    }

    public Specimen addParent(Reference t) { //3
      if (t == null)
        return this;
      if (this.parent == null)
        this.parent = new ArrayList<Reference>();
      this.parent.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #parent}, creating it if it does not already exist
     */
    public Reference getParentFirstRep() { 
      if (getParent().isEmpty()) {
        addParent();
      }
      return getParent().get(0);
    }

    /**
     * @return {@link #request} (Details concerning a service request that required a specimen to be collected.)
     */
    public List<Reference> getRequest() { 
      if (this.request == null)
        this.request = new ArrayList<Reference>();
      return this.request;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public Specimen setRequest(List<Reference> theRequest) { 
      this.request = theRequest;
      return this;
    }

    public boolean hasRequest() { 
      if (this.request == null)
        return false;
      for (Reference item : this.request)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public Reference addRequest() { //3
      Reference t = new Reference();
      if (this.request == null)
        this.request = new ArrayList<Reference>();
      this.request.add(t);
      return t;
    }

    public Specimen addRequest(Reference t) { //3
      if (t == null)
        return this;
      if (this.request == null)
        this.request = new ArrayList<Reference>();
      this.request.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #request}, creating it if it does not already exist
     */
    public Reference getRequestFirstRep() { 
      if (getRequest().isEmpty()) {
        addRequest();
      }
      return getRequest().get(0);
    }

    /**
     * @return {@link #collection} (Details concerning the specimen collection.)
     */
    public SpecimenCollectionComponent getCollection() { 
      if (this.collection == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Specimen.collection");
        else if (Configuration.doAutoCreate())
          this.collection = new SpecimenCollectionComponent(); // cc
      return this.collection;
    }

    public boolean hasCollection() { 
      return this.collection != null && !this.collection.isEmpty();
    }

    /**
     * @param value {@link #collection} (Details concerning the specimen collection.)
     */
    public Specimen setCollection(SpecimenCollectionComponent value) { 
      this.collection = value;
      return this;
    }

    /**
     * @return {@link #processing} (Details concerning processing and processing steps for the specimen.)
     */
    public List<SpecimenProcessingComponent> getProcessing() { 
      if (this.processing == null)
        this.processing = new ArrayList<SpecimenProcessingComponent>();
      return this.processing;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public Specimen setProcessing(List<SpecimenProcessingComponent> theProcessing) { 
      this.processing = theProcessing;
      return this;
    }

    public boolean hasProcessing() { 
      if (this.processing == null)
        return false;
      for (SpecimenProcessingComponent item : this.processing)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public SpecimenProcessingComponent addProcessing() { //3
      SpecimenProcessingComponent t = new SpecimenProcessingComponent();
      if (this.processing == null)
        this.processing = new ArrayList<SpecimenProcessingComponent>();
      this.processing.add(t);
      return t;
    }

    public Specimen addProcessing(SpecimenProcessingComponent t) { //3
      if (t == null)
        return this;
      if (this.processing == null)
        this.processing = new ArrayList<SpecimenProcessingComponent>();
      this.processing.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #processing}, creating it if it does not already exist
     */
    public SpecimenProcessingComponent getProcessingFirstRep() { 
      if (getProcessing().isEmpty()) {
        addProcessing();
      }
      return getProcessing().get(0);
    }

    /**
     * @return {@link #container} (The container holding the specimen.  The recursive nature of containers; i.e. blood in tube in tray in rack is not addressed here.)
     */
    public List<SpecimenContainerComponent> getContainer() { 
      if (this.container == null)
        this.container = new ArrayList<SpecimenContainerComponent>();
      return this.container;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public Specimen setContainer(List<SpecimenContainerComponent> theContainer) { 
      this.container = theContainer;
      return this;
    }

    public boolean hasContainer() { 
      if (this.container == null)
        return false;
      for (SpecimenContainerComponent item : this.container)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public SpecimenContainerComponent addContainer() { //3
      SpecimenContainerComponent t = new SpecimenContainerComponent();
      if (this.container == null)
        this.container = new ArrayList<SpecimenContainerComponent>();
      this.container.add(t);
      return t;
    }

    public Specimen addContainer(SpecimenContainerComponent t) { //3
      if (t == null)
        return this;
      if (this.container == null)
        this.container = new ArrayList<SpecimenContainerComponent>();
      this.container.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #container}, creating it if it does not already exist
     */
    public SpecimenContainerComponent getContainerFirstRep() { 
      if (getContainer().isEmpty()) {
        addContainer();
      }
      return getContainer().get(0);
    }

    /**
     * @return {@link #condition} (A mode or state of being that describes the nature of the specimen.)
     */
    public List<CodeableConcept> getCondition() { 
      if (this.condition == null)
        this.condition = new ArrayList<CodeableConcept>();
      return this.condition;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public Specimen setCondition(List<CodeableConcept> theCondition) { 
      this.condition = theCondition;
      return this;
    }

    public boolean hasCondition() { 
      if (this.condition == null)
        return false;
      for (CodeableConcept item : this.condition)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public CodeableConcept addCondition() { //3
      CodeableConcept t = new CodeableConcept();
      if (this.condition == null)
        this.condition = new ArrayList<CodeableConcept>();
      this.condition.add(t);
      return t;
    }

    public Specimen addCondition(CodeableConcept t) { //3
      if (t == null)
        return this;
      if (this.condition == null)
        this.condition = new ArrayList<CodeableConcept>();
      this.condition.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #condition}, creating it if it does not already exist
     */
    public CodeableConcept getConditionFirstRep() { 
      if (getCondition().isEmpty()) {
        addCondition();
      }
      return getCondition().get(0);
    }

    /**
     * @return {@link #note} (To communicate any details or issues about the specimen or during the specimen collection. (for example: broken vial, sent with patient, frozen).)
     */
    public List<Annotation> getNote() { 
      if (this.note == null)
        this.note = new ArrayList<Annotation>();
      return this.note;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public Specimen setNote(List<Annotation> theNote) { 
      this.note = theNote;
      return this;
    }

    public boolean hasNote() { 
      if (this.note == null)
        return false;
      for (Annotation item : this.note)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public Annotation addNote() { //3
      Annotation t = new Annotation();
      if (this.note == null)
        this.note = new ArrayList<Annotation>();
      this.note.add(t);
      return t;
    }

    public Specimen addNote(Annotation t) { //3
      if (t == null)
        return this;
      if (this.note == null)
        this.note = new ArrayList<Annotation>();
      this.note.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #note}, creating it if it does not already exist
     */
    public Annotation getNoteFirstRep() { 
      if (getNote().isEmpty()) {
        addNote();
      }
      return getNote().get(0);
    }

      protected void listChildren(List<Property> children) {
        super.listChildren(children);
        children.add(new Property("identifier", "Identifier", "Id for specimen.", 0, java.lang.Integer.MAX_VALUE, identifier));
        children.add(new Property("accessionIdentifier", "Identifier", "The identifier assigned by the lab when accessioning specimen(s). This is not necessarily the same as the specimen identifier, depending on local lab procedures.", 0, 1, accessionIdentifier));
        children.add(new Property("status", "code", "The availability of the specimen.", 0, 1, status));
        children.add(new Property("type", "CodeableConcept", "The kind of material that forms the specimen.", 0, 1, type));
        children.add(new Property("subject", "Reference(Patient|Group|Device|Substance|Location)", "Where the specimen came from. This may be from patient(s), from a location (e.g., the source of an environmental sample), or a sampling of a substance or a device.", 0, 1, subject));
        children.add(new Property("receivedTime", "dateTime", "Time when specimen was received for processing or testing.", 0, 1, receivedTime));
        children.add(new Property("parent", "Reference(Specimen)", "Reference to the parent (source) specimen which is used when the specimen was either derived from or a component of another specimen.", 0, java.lang.Integer.MAX_VALUE, parent));
        children.add(new Property("request", "Reference(ServiceRequest)", "Details concerning a service request that required a specimen to be collected.", 0, java.lang.Integer.MAX_VALUE, request));
        children.add(new Property("collection", "", "Details concerning the specimen collection.", 0, 1, collection));
        children.add(new Property("processing", "", "Details concerning processing and processing steps for the specimen.", 0, java.lang.Integer.MAX_VALUE, processing));
        children.add(new Property("container", "", "The container holding the specimen.  The recursive nature of containers; i.e. blood in tube in tray in rack is not addressed here.", 0, java.lang.Integer.MAX_VALUE, container));
        children.add(new Property("condition", "CodeableConcept", "A mode or state of being that describes the nature of the specimen.", 0, java.lang.Integer.MAX_VALUE, condition));
        children.add(new Property("note", "Annotation", "To communicate any details or issues about the specimen or during the specimen collection. (for example: broken vial, sent with patient, frozen).", 0, java.lang.Integer.MAX_VALUE, note));
      }

      @Override
      public Property getNamedProperty(int _hash, String _name, boolean _checkValid) throws FHIRException {
        switch (_hash) {
        case -1618432855: /*identifier*/  return new Property("identifier", "Identifier", "Id for specimen.", 0, java.lang.Integer.MAX_VALUE, identifier);
        case 818734061: /*accessionIdentifier*/  return new Property("accessionIdentifier", "Identifier", "The identifier assigned by the lab when accessioning specimen(s). This is not necessarily the same as the specimen identifier, depending on local lab procedures.", 0, 1, accessionIdentifier);
        case -892481550: /*status*/  return new Property("status", "code", "The availability of the specimen.", 0, 1, status);
        case 3575610: /*type*/  return new Property("type", "CodeableConcept", "The kind of material that forms the specimen.", 0, 1, type);
        case -1867885268: /*subject*/  return new Property("subject", "Reference(Patient|Group|Device|Substance|Location)", "Where the specimen came from. This may be from patient(s), from a location (e.g., the source of an environmental sample), or a sampling of a substance or a device.", 0, 1, subject);
        case -767961010: /*receivedTime*/  return new Property("receivedTime", "dateTime", "Time when specimen was received for processing or testing.", 0, 1, receivedTime);
        case -995424086: /*parent*/  return new Property("parent", "Reference(Specimen)", "Reference to the parent (source) specimen which is used when the specimen was either derived from or a component of another specimen.", 0, java.lang.Integer.MAX_VALUE, parent);
        case 1095692943: /*request*/  return new Property("request", "Reference(ServiceRequest)", "Details concerning a service request that required a specimen to be collected.", 0, java.lang.Integer.MAX_VALUE, request);
        case -1741312354: /*collection*/  return new Property("collection", "", "Details concerning the specimen collection.", 0, 1, collection);
        case 422194963: /*processing*/  return new Property("processing", "", "Details concerning processing and processing steps for the specimen.", 0, java.lang.Integer.MAX_VALUE, processing);
        case -410956671: /*container*/  return new Property("container", "", "The container holding the specimen.  The recursive nature of containers; i.e. blood in tube in tray in rack is not addressed here.", 0, java.lang.Integer.MAX_VALUE, container);
        case -861311717: /*condition*/  return new Property("condition", "CodeableConcept", "A mode or state of being that describes the nature of the specimen.", 0, java.lang.Integer.MAX_VALUE, condition);
        case 3387378: /*note*/  return new Property("note", "Annotation", "To communicate any details or issues about the specimen or during the specimen collection. (for example: broken vial, sent with patient, frozen).", 0, java.lang.Integer.MAX_VALUE, note);
        default: return super.getNamedProperty(_hash, _name, _checkValid);
        }

      }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case -1618432855: /*identifier*/ return this.identifier == null ? new Base[0] : this.identifier.toArray(new Base[this.identifier.size()]); // Identifier
        case 818734061: /*accessionIdentifier*/ return this.accessionIdentifier == null ? new Base[0] : new Base[] {this.accessionIdentifier}; // Identifier
        case -892481550: /*status*/ return this.status == null ? new Base[0] : new Base[] {this.status}; // Enumeration<SpecimenStatus>
        case 3575610: /*type*/ return this.type == null ? new Base[0] : new Base[] {this.type}; // CodeableConcept
        case -1867885268: /*subject*/ return this.subject == null ? new Base[0] : new Base[] {this.subject}; // Reference
        case -767961010: /*receivedTime*/ return this.receivedTime == null ? new Base[0] : new Base[] {this.receivedTime}; // DateTimeType
        case -995424086: /*parent*/ return this.parent == null ? new Base[0] : this.parent.toArray(new Base[this.parent.size()]); // Reference
        case 1095692943: /*request*/ return this.request == null ? new Base[0] : this.request.toArray(new Base[this.request.size()]); // Reference
        case -1741312354: /*collection*/ return this.collection == null ? new Base[0] : new Base[] {this.collection}; // SpecimenCollectionComponent
        case 422194963: /*processing*/ return this.processing == null ? new Base[0] : this.processing.toArray(new Base[this.processing.size()]); // SpecimenProcessingComponent
        case -410956671: /*container*/ return this.container == null ? new Base[0] : this.container.toArray(new Base[this.container.size()]); // SpecimenContainerComponent
        case -861311717: /*condition*/ return this.condition == null ? new Base[0] : this.condition.toArray(new Base[this.condition.size()]); // CodeableConcept
        case 3387378: /*note*/ return this.note == null ? new Base[0] : this.note.toArray(new Base[this.note.size()]); // Annotation
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case -1618432855: // identifier
          this.getIdentifier().add(TypeConvertor.castToIdentifier(value)); // Identifier
          return value;
        case 818734061: // accessionIdentifier
          this.accessionIdentifier = TypeConvertor.castToIdentifier(value); // Identifier
          return value;
        case -892481550: // status
          value = new SpecimenStatusEnumFactory().fromType(TypeConvertor.castToCode(value));
          this.status = (Enumeration) value; // Enumeration<SpecimenStatus>
          return value;
        case 3575610: // type
          this.type = TypeConvertor.castToCodeableConcept(value); // CodeableConcept
          return value;
        case -1867885268: // subject
          this.subject = TypeConvertor.castToReference(value); // Reference
          return value;
        case -767961010: // receivedTime
          this.receivedTime = TypeConvertor.castToDateTime(value); // DateTimeType
          return value;
        case -995424086: // parent
          this.getParent().add(TypeConvertor.castToReference(value)); // Reference
          return value;
        case 1095692943: // request
          this.getRequest().add(TypeConvertor.castToReference(value)); // Reference
          return value;
        case -1741312354: // collection
          this.collection = (SpecimenCollectionComponent) value; // SpecimenCollectionComponent
          return value;
        case 422194963: // processing
          this.getProcessing().add((SpecimenProcessingComponent) value); // SpecimenProcessingComponent
          return value;
        case -410956671: // container
          this.getContainer().add((SpecimenContainerComponent) value); // SpecimenContainerComponent
          return value;
        case -861311717: // condition
          this.getCondition().add(TypeConvertor.castToCodeableConcept(value)); // CodeableConcept
          return value;
        case 3387378: // note
          this.getNote().add(TypeConvertor.castToAnnotation(value)); // Annotation
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("identifier")) {
          this.getIdentifier().add(TypeConvertor.castToIdentifier(value));
        } else if (name.equals("accessionIdentifier")) {
          this.accessionIdentifier = TypeConvertor.castToIdentifier(value); // Identifier
        } else if (name.equals("status")) {
          value = new SpecimenStatusEnumFactory().fromType(TypeConvertor.castToCode(value));
          this.status = (Enumeration) value; // Enumeration<SpecimenStatus>
        } else if (name.equals("type")) {
          this.type = TypeConvertor.castToCodeableConcept(value); // CodeableConcept
        } else if (name.equals("subject")) {
          this.subject = TypeConvertor.castToReference(value); // Reference
        } else if (name.equals("receivedTime")) {
          this.receivedTime = TypeConvertor.castToDateTime(value); // DateTimeType
        } else if (name.equals("parent")) {
          this.getParent().add(TypeConvertor.castToReference(value));
        } else if (name.equals("request")) {
          this.getRequest().add(TypeConvertor.castToReference(value));
        } else if (name.equals("collection")) {
          this.collection = (SpecimenCollectionComponent) value; // SpecimenCollectionComponent
        } else if (name.equals("processing")) {
          this.getProcessing().add((SpecimenProcessingComponent) value);
        } else if (name.equals("container")) {
          this.getContainer().add((SpecimenContainerComponent) value);
        } else if (name.equals("condition")) {
          this.getCondition().add(TypeConvertor.castToCodeableConcept(value));
        } else if (name.equals("note")) {
          this.getNote().add(TypeConvertor.castToAnnotation(value));
        } else
          return super.setProperty(name, value);
        return value;
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case -1618432855:  return addIdentifier(); 
        case 818734061:  return getAccessionIdentifier();
        case -892481550:  return getStatusElement();
        case 3575610:  return getType();
        case -1867885268:  return getSubject();
        case -767961010:  return getReceivedTimeElement();
        case -995424086:  return addParent(); 
        case 1095692943:  return addRequest(); 
        case -1741312354:  return getCollection();
        case 422194963:  return addProcessing(); 
        case -410956671:  return addContainer(); 
        case -861311717:  return addCondition(); 
        case 3387378:  return addNote(); 
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case -1618432855: /*identifier*/ return new String[] {"Identifier"};
        case 818734061: /*accessionIdentifier*/ return new String[] {"Identifier"};
        case -892481550: /*status*/ return new String[] {"code"};
        case 3575610: /*type*/ return new String[] {"CodeableConcept"};
        case -1867885268: /*subject*/ return new String[] {"Reference"};
        case -767961010: /*receivedTime*/ return new String[] {"dateTime"};
        case -995424086: /*parent*/ return new String[] {"Reference"};
        case 1095692943: /*request*/ return new String[] {"Reference"};
        case -1741312354: /*collection*/ return new String[] {};
        case 422194963: /*processing*/ return new String[] {};
        case -410956671: /*container*/ return new String[] {};
        case -861311717: /*condition*/ return new String[] {"CodeableConcept"};
        case 3387378: /*note*/ return new String[] {"Annotation"};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("identifier")) {
          return addIdentifier();
        }
        else if (name.equals("accessionIdentifier")) {
          this.accessionIdentifier = new Identifier();
          return this.accessionIdentifier;
        }
        else if (name.equals("status")) {
          throw new FHIRException("Cannot call addChild on a primitive type Specimen.status");
        }
        else if (name.equals("type")) {
          this.type = new CodeableConcept();
          return this.type;
        }
        else if (name.equals("subject")) {
          this.subject = new Reference();
          return this.subject;
        }
        else if (name.equals("receivedTime")) {
          throw new FHIRException("Cannot call addChild on a primitive type Specimen.receivedTime");
        }
        else if (name.equals("parent")) {
          return addParent();
        }
        else if (name.equals("request")) {
          return addRequest();
        }
        else if (name.equals("collection")) {
          this.collection = new SpecimenCollectionComponent();
          return this.collection;
        }
        else if (name.equals("processing")) {
          return addProcessing();
        }
        else if (name.equals("container")) {
          return addContainer();
        }
        else if (name.equals("condition")) {
          return addCondition();
        }
        else if (name.equals("note")) {
          return addNote();
        }
        else
          return super.addChild(name);
      }

  public String fhirType() {
    return "Specimen";

  }

      public Specimen copy() {
        Specimen dst = new Specimen();
        copyValues(dst);
        return dst;
      }

      public void copyValues(Specimen dst) {
        super.copyValues(dst);
        if (identifier != null) {
          dst.identifier = new ArrayList<Identifier>();
          for (Identifier i : identifier)
            dst.identifier.add(i.copy());
        };
        dst.accessionIdentifier = accessionIdentifier == null ? null : accessionIdentifier.copy();
        dst.status = status == null ? null : status.copy();
        dst.type = type == null ? null : type.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.receivedTime = receivedTime == null ? null : receivedTime.copy();
        if (parent != null) {
          dst.parent = new ArrayList<Reference>();
          for (Reference i : parent)
            dst.parent.add(i.copy());
        };
        if (request != null) {
          dst.request = new ArrayList<Reference>();
          for (Reference i : request)
            dst.request.add(i.copy());
        };
        dst.collection = collection == null ? null : collection.copy();
        if (processing != null) {
          dst.processing = new ArrayList<SpecimenProcessingComponent>();
          for (SpecimenProcessingComponent i : processing)
            dst.processing.add(i.copy());
        };
        if (container != null) {
          dst.container = new ArrayList<SpecimenContainerComponent>();
          for (SpecimenContainerComponent i : container)
            dst.container.add(i.copy());
        };
        if (condition != null) {
          dst.condition = new ArrayList<CodeableConcept>();
          for (CodeableConcept i : condition)
            dst.condition.add(i.copy());
        };
        if (note != null) {
          dst.note = new ArrayList<Annotation>();
          for (Annotation i : note)
            dst.note.add(i.copy());
        };
      }

      protected Specimen typedCopy() {
        return copy();
      }

      @Override
      public boolean equalsDeep(Base other_) {
        if (!super.equalsDeep(other_))
          return false;
        if (!(other_ instanceof Specimen))
          return false;
        Specimen o = (Specimen) other_;
        return compareDeep(identifier, o.identifier, true) && compareDeep(accessionIdentifier, o.accessionIdentifier, true)
           && compareDeep(status, o.status, true) && compareDeep(type, o.type, true) && compareDeep(subject, o.subject, true)
           && compareDeep(receivedTime, o.receivedTime, true) && compareDeep(parent, o.parent, true) && compareDeep(request, o.request, true)
           && compareDeep(collection, o.collection, true) && compareDeep(processing, o.processing, true) && compareDeep(container, o.container, true)
           && compareDeep(condition, o.condition, true) && compareDeep(note, o.note, true);
      }

      @Override
      public boolean equalsShallow(Base other_) {
        if (!super.equalsShallow(other_))
          return false;
        if (!(other_ instanceof Specimen))
          return false;
        Specimen o = (Specimen) other_;
        return compareValues(status, o.status, true) && compareValues(receivedTime, o.receivedTime, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(identifier, accessionIdentifier
          , status, type, subject, receivedTime, parent, request, collection, processing
          , container, condition, note);
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Specimen;
   }

 /**
   * Search parameter: <b>container</b>
   * <p>
   * Description: <b>The kind of specimen container</b><br>
   * Type: <b>token</b><br>
   * Path: <b>Specimen.container.type</b><br>
   * </p>
   */
  @SearchParamDefinition(name="container", path="Specimen.container.type", description="The kind of specimen container", type="token" )
  public static final String SP_CONTAINER = "container";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>container</b>
   * <p>
   * Description: <b>The kind of specimen container</b><br>
   * Type: <b>token</b><br>
   * Path: <b>Specimen.container.type</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam CONTAINER = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_CONTAINER);

 /**
   * Search parameter: <b>identifier</b>
   * <p>
   * Description: <b>The unique identifier associated with the specimen</b><br>
   * Type: <b>token</b><br>
   * Path: <b>Specimen.identifier</b><br>
   * </p>
   */
  @SearchParamDefinition(name="identifier", path="Specimen.identifier", description="The unique identifier associated with the specimen", type="token" )
  public static final String SP_IDENTIFIER = "identifier";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>identifier</b>
   * <p>
   * Description: <b>The unique identifier associated with the specimen</b><br>
   * Type: <b>token</b><br>
   * Path: <b>Specimen.identifier</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam IDENTIFIER = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_IDENTIFIER);

 /**
   * Search parameter: <b>parent</b>
   * <p>
   * Description: <b>The parent of the specimen</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>Specimen.parent</b><br>
   * </p>
   */
  @SearchParamDefinition(name="parent", path="Specimen.parent", description="The parent of the specimen", type="reference", target={Specimen.class } )
  public static final String SP_PARENT = "parent";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>parent</b>
   * <p>
   * Description: <b>The parent of the specimen</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>Specimen.parent</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.ReferenceClientParam PARENT = new ca.uhn.fhir.rest.gclient.ReferenceClientParam(SP_PARENT);

/**
   * Constant for fluent queries to be used to add include statements. Specifies
   * the path value of "<b>Specimen:parent</b>".
   */
  public static final ca.uhn.fhir.model.api.Include INCLUDE_PARENT = new ca.uhn.fhir.model.api.Include("Specimen:parent").toLocked();

 /**
   * Search parameter: <b>container-id</b>
   * <p>
   * Description: <b>The unique identifier associated with the specimen container</b><br>
   * Type: <b>token</b><br>
   * Path: <b>Specimen.container.identifier</b><br>
   * </p>
   */
  @SearchParamDefinition(name="container-id", path="Specimen.container.identifier", description="The unique identifier associated with the specimen container", type="token" )
  public static final String SP_CONTAINER_ID = "container-id";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>container-id</b>
   * <p>
   * Description: <b>The unique identifier associated with the specimen container</b><br>
   * Type: <b>token</b><br>
   * Path: <b>Specimen.container.identifier</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam CONTAINER_ID = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_CONTAINER_ID);

 /**
   * Search parameter: <b>bodysite</b>
   * <p>
   * Description: <b>The code for the body site from where the specimen originated</b><br>
   * Type: <b>token</b><br>
   * Path: <b>Specimen.collection.bodySite</b><br>
   * </p>
   */
  @SearchParamDefinition(name="bodysite", path="Specimen.collection.bodySite", description="The code for the body site from where the specimen originated", type="token" )
  public static final String SP_BODYSITE = "bodysite";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>bodysite</b>
   * <p>
   * Description: <b>The code for the body site from where the specimen originated</b><br>
   * Type: <b>token</b><br>
   * Path: <b>Specimen.collection.bodySite</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam BODYSITE = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_BODYSITE);

 /**
   * Search parameter: <b>subject</b>
   * <p>
   * Description: <b>The subject of the specimen</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>Specimen.subject</b><br>
   * </p>
   */
  @SearchParamDefinition(name="subject", path="Specimen.subject", description="The subject of the specimen", type="reference", providesMembershipIn={ @ca.uhn.fhir.model.api.annotation.Compartment(name="Device"), @ca.uhn.fhir.model.api.annotation.Compartment(name="Patient") }, target={Device.class, Group.class, Location.class, Patient.class, Substance.class } )
  public static final String SP_SUBJECT = "subject";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>subject</b>
   * <p>
   * Description: <b>The subject of the specimen</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>Specimen.subject</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.ReferenceClientParam SUBJECT = new ca.uhn.fhir.rest.gclient.ReferenceClientParam(SP_SUBJECT);

/**
   * Constant for fluent queries to be used to add include statements. Specifies
   * the path value of "<b>Specimen:subject</b>".
   */
  public static final ca.uhn.fhir.model.api.Include INCLUDE_SUBJECT = new ca.uhn.fhir.model.api.Include("Specimen:subject").toLocked();

 /**
   * Search parameter: <b>patient</b>
   * <p>
   * Description: <b>The patient the specimen comes from</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>Specimen.subject</b><br>
   * </p>
   */
  @SearchParamDefinition(name="patient", path="Specimen.subject.where(resolve() is Patient)", description="The patient the specimen comes from", type="reference", target={Patient.class } )
  public static final String SP_PATIENT = "patient";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>patient</b>
   * <p>
   * Description: <b>The patient the specimen comes from</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>Specimen.subject</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.ReferenceClientParam PATIENT = new ca.uhn.fhir.rest.gclient.ReferenceClientParam(SP_PATIENT);

/**
   * Constant for fluent queries to be used to add include statements. Specifies
   * the path value of "<b>Specimen:patient</b>".
   */
  public static final ca.uhn.fhir.model.api.Include INCLUDE_PATIENT = new ca.uhn.fhir.model.api.Include("Specimen:patient").toLocked();

 /**
   * Search parameter: <b>collected</b>
   * <p>
   * Description: <b>The date the specimen was collected</b><br>
   * Type: <b>date</b><br>
   * Path: <b>Specimen.collection.collected[x]</b><br>
   * </p>
   */
  @SearchParamDefinition(name="collected", path="Specimen.collection.collected", description="The date the specimen was collected", type="date" )
  public static final String SP_COLLECTED = "collected";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>collected</b>
   * <p>
   * Description: <b>The date the specimen was collected</b><br>
   * Type: <b>date</b><br>
   * Path: <b>Specimen.collection.collected[x]</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.DateClientParam COLLECTED = new ca.uhn.fhir.rest.gclient.DateClientParam(SP_COLLECTED);

 /**
   * Search parameter: <b>accession</b>
   * <p>
   * Description: <b>The accession number associated with the specimen</b><br>
   * Type: <b>token</b><br>
   * Path: <b>Specimen.accessionIdentifier</b><br>
   * </p>
   */
  @SearchParamDefinition(name="accession", path="Specimen.accessionIdentifier", description="The accession number associated with the specimen", type="token" )
  public static final String SP_ACCESSION = "accession";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>accession</b>
   * <p>
   * Description: <b>The accession number associated with the specimen</b><br>
   * Type: <b>token</b><br>
   * Path: <b>Specimen.accessionIdentifier</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam ACCESSION = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_ACCESSION);

 /**
   * Search parameter: <b>type</b>
   * <p>
   * Description: <b>The specimen type</b><br>
   * Type: <b>token</b><br>
   * Path: <b>Specimen.type</b><br>
   * </p>
   */
  @SearchParamDefinition(name="type", path="Specimen.type", description="The specimen type", type="token" )
  public static final String SP_TYPE = "type";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>type</b>
   * <p>
   * Description: <b>The specimen type</b><br>
   * Type: <b>token</b><br>
   * Path: <b>Specimen.type</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam TYPE = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_TYPE);

 /**
   * Search parameter: <b>collector</b>
   * <p>
   * Description: <b>Who collected the specimen</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>Specimen.collection.collector</b><br>
   * </p>
   */
  @SearchParamDefinition(name="collector", path="Specimen.collection.collector", description="Who collected the specimen", type="reference", providesMembershipIn={ @ca.uhn.fhir.model.api.annotation.Compartment(name="Practitioner") }, target={Practitioner.class, PractitionerRole.class } )
  public static final String SP_COLLECTOR = "collector";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>collector</b>
   * <p>
   * Description: <b>Who collected the specimen</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>Specimen.collection.collector</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.ReferenceClientParam COLLECTOR = new ca.uhn.fhir.rest.gclient.ReferenceClientParam(SP_COLLECTOR);

/**
   * Constant for fluent queries to be used to add include statements. Specifies
   * the path value of "<b>Specimen:collector</b>".
   */
  public static final ca.uhn.fhir.model.api.Include INCLUDE_COLLECTOR = new ca.uhn.fhir.model.api.Include("Specimen:collector").toLocked();

 /**
   * Search parameter: <b>status</b>
   * <p>
   * Description: <b>available | unavailable | unsatisfactory | entered-in-error</b><br>
   * Type: <b>token</b><br>
   * Path: <b>Specimen.status</b><br>
   * </p>
   */
  @SearchParamDefinition(name="status", path="Specimen.status", description="available | unavailable | unsatisfactory | entered-in-error", type="token" )
  public static final String SP_STATUS = "status";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>status</b>
   * <p>
   * Description: <b>available | unavailable | unsatisfactory | entered-in-error</b><br>
   * Type: <b>token</b><br>
   * Path: <b>Specimen.status</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam STATUS = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_STATUS);


}


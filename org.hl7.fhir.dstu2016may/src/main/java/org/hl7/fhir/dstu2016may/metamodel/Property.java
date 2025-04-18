package org.hl7.fhir.dstu2016may.metamodel;

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

import org.hl7.fhir.dstu2016may.formats.FormatUtilities;
import org.hl7.fhir.dstu2016may.model.ElementDefinition;
import org.hl7.fhir.dstu2016may.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.dstu2016may.model.StructureDefinition;
import org.hl7.fhir.dstu2016may.model.StructureDefinition.StructureDefinitionKind;
import org.hl7.fhir.dstu2016may.utils.IWorkerContext;
import org.hl7.fhir.dstu2016may.utils.ToolingExtensions;

@Deprecated
public class Property {

  private IWorkerContext context;
  private ElementDefinition definition;
  private StructureDefinition structure;
  private Boolean canBePrimitive;

  public Property(IWorkerContext context, ElementDefinition definition, StructureDefinition structure) {
    this.context = context;
    this.definition = definition;
    this.structure = structure;
  }

  public String getName() {
    return definition.getPath().substring(definition.getPath().lastIndexOf(".") + 1);
  }

  public ElementDefinition getDefinition() {
    return definition;
  }

  public String getType() {
    if (definition.getType().size() == 0)
      return null;
    else if (definition.getType().size() > 1) {
      String tn = definition.getType().get(0).getCode();
      for (int i = 1; i < definition.getType().size(); i++) {
        if (!tn.equals(definition.getType().get(i).getCode()))
          throw new Error("logic error, gettype when types > 1");
      }
      return tn;
    } else
      return definition.getType().get(0).getCode();
  }

  public String getType(String elementName) {
    if (definition.getType().size() == 0)
      return null;
    else if (definition.getType().size() > 1) {
      String t = definition.getType().get(0).getCode();
      boolean all = true;
      for (TypeRefComponent tr : definition.getType()) {
        if (!t.equals(tr.getCode()))
          all = false;
      }
      if (all)
        return t;
      String tail = definition.getPath().substring(definition.getPath().lastIndexOf(".") + 1);
      if (tail.endsWith("[x]") && elementName != null && elementName.startsWith(tail.substring(0, tail.length() - 3))) {
        String name = elementName.substring(tail.length() - 3);
        return ParserBase.isPrimitive(lowFirst(name)) ? lowFirst(name) : name;
      } else
        throw new Error(
            "logic error, gettype when types > 1, name mismatch for " + elementName + " on at " + definition.getPath());
    } else if (definition.getType().get(0).getCode() == null) {
      return structure.getId();
    } else
      return definition.getType().get(0).getCode();
  }

  public boolean hasType(String elementName) {
    if (definition.getType().size() == 0)
      return false;
    else if (definition.getType().size() > 1) {
      String t = definition.getType().get(0).getCode();
      boolean all = true;
      for (TypeRefComponent tr : definition.getType()) {
        if (!t.equals(tr.getCode()))
          all = false;
      }
      if (all)
        return true;
      String tail = definition.getPath().substring(definition.getPath().lastIndexOf(".") + 1);
      if (tail.endsWith("[x]") && elementName.startsWith(tail.substring(0, tail.length() - 3))) {
        String name = elementName.substring(tail.length() - 3);
        return true;
      } else
        return false;
    } else
      return true;
  }

  public StructureDefinition getStructure() {
    return structure;
  }

  public boolean isPrimitive(String name) {
    return ParserBase.isPrimitive(getType(name));
  }

  private String lowFirst(String t) {
    return t.substring(0, 1).toLowerCase() + t.substring(1);
  }

  public boolean isResource() {
    return definition.getType().size() == 1 && ("Resource".equals(definition.getType().get(0).getCode())
        || "DomainResource".equals(definition.getType().get(0).getCode()));
  }

  public boolean isList() {
    return !definition.getMax().equals("1");
  }

  public String getScopedPropertyName() {
    return definition.getBase().getPath();
  }

  public String getNamespace() {
    if (ToolingExtensions.hasExtension(definition,
        "http://hl7.org/fhir/StructureDefinition/elementdefinition-namespace"))
      return ToolingExtensions.readStringExtension(definition,
          "http://hl7.org/fhir/StructureDefinition/elementdefinition-namespace");
    if (ToolingExtensions.hasExtension(structure,
        "http://hl7.org/fhir/StructureDefinition/elementdefinition-namespace"))
      return ToolingExtensions.readStringExtension(structure,
          "http://hl7.org/fhir/StructureDefinition/elementdefinition-namespace");
    return FormatUtilities.FHIR_NS;
  }

  public boolean IsLogicalAndHasPrimitiveValue(String name) {
    if (canBePrimitive != null)
      return canBePrimitive;

    canBePrimitive = false;
    if (structure.getKind() != StructureDefinitionKind.LOGICAL)
      return false;
    if (!hasType(name))
      return false;
    StructureDefinition sd = context.fetchResource(StructureDefinition.class,
        structure.getUrl().substring(0, structure.getUrl().lastIndexOf("/") + 1) + getType(name));
    if (sd == null || sd.getKind() != StructureDefinitionKind.LOGICAL)
      return false;
    for (ElementDefinition ed : sd.getSnapshot().getElement()) {
      if (ed.getPath().equals(sd.getId() + ".value") && ed.getType().size() == 1
          && ParserBase.isPrimitive(ed.getType().get(0).getCode())) {
        canBePrimitive = true;
        return true;
      }
    }
    return false;
  }

  public boolean isChoice() {
    if (definition.getType().size() <= 1)
      return false;
    String tn = definition.getType().get(0).getCode();
    for (int i = 1; i < definition.getType().size(); i++)
      if (!definition.getType().get(i).getCode().equals(tn))
        return true;
    return false;
  }

}
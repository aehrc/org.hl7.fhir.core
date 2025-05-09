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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hl7.fhir.dstu2016may.model.Base;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;

/**
 * This class represents the reference model of FHIR
 * 
 * A resource is nothing but a set of elements, where every element has a name,
 * maybe a stated type, maybe an id, and either a value or child elements (one
 * or the other, or both (but not neither if it's null)
 * 
 * @author Grahame Grieve
 *
 */
@Deprecated
public class Element extends Base {

  public enum SpecialElement {
    CONTAINED, BUNDLE_ENTRY;
  }

  private List<String> comments;// not relevant for production, but useful in documentation
  private String name;
  private String type;
  private String value;
  private int index = -1;
  private List<Element> children;
  private Property property;
  private int line;
  private int col;
  private SpecialElement special;
  private XhtmlNode xhtml; // if this is populated, then value will also hold the string representation

  public Element(String name) {
    super();
    this.name = name;
  }

  public Element(String name, Property property) {
    super();
    this.name = name;
    this.property = property;
  }

  public Element(String name, Property property, String type, String value) {
    super();
    this.name = name;
    this.property = property;
    this.type = type;
    this.value = value;
  }

  public void updateProperty(Property property, SpecialElement special) {
    this.property = property;
    this.special = special;
  }

  public SpecialElement getSpecial() {
    return special;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    if (type == null)
      return property.getType(name);
    else
      return type;
  }

  public String getValue() {
    return value;
  }

  public boolean hasChildren() {
    return !(children == null || children.isEmpty());
  }

  public List<Element> getChildren() {
    if (children == null)
      children = new ArrayList<Element>();
    return children;
  }

  public boolean hasComments() {
    return !(comments == null || comments.isEmpty());
  }

  public List<String> getComments() {
    if (comments == null)
      comments = new ArrayList<String>();
    return comments;
  }

  public Property getProperty() {
    return property;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public void setType(String type) {
    this.type = type;

  }

  public boolean hasValue() {
    return value != null;
  }

  public List<Element> getChildrenByName(String name) {
    List<Element> res = new ArrayList<Element>();
    if (hasChildren()) {
      for (Element child : children)
        if (name.equals(child.getName()))
          res.add(child);
    }
    return res;
  }

  public void numberChildren() {
    if (children == null)
      return;

    String last = "";
    int index = 0;
    for (Element child : children) {
      if (child.getProperty().isList()) {
        if (last.equals(child.getName())) {
          index++;
        } else {
          last = child.getName();
          index = 0;
        }
        child.index = index;
      } else {
        child.index = -1;
      }
      child.numberChildren();
    }
  }

  public int getIndex() {
    return index;
  }

  public boolean hasIndex() {
    return index > -1;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public String getChildValue(String name) {
    if (children == null)
      return null;
    for (Element child : children) {
      if (name.equals(child.getName()))
        return child.getValue();
    }
    return null;
  }

  public List<Element> getChildren(String name) {
    List<Element> res = new ArrayList<Element>();
    for (Element child : children) {
      if (name.equals(child.getName()))
        res.add(child);
    }
    return res;
  }

  public boolean hasType() {
    if (type == null)
      return property.hasType(name);
    else
      return true;
  }

  @Override
  public String fhirType() {
    return getType();
  }

  @Override
  public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
    if (isPrimitive() && (hash == "value".hashCode()) && !Utilities.noString(value)) {
      String tn = getType();
      throw new Error("not done yet");
    }

    List<Base> result = new ArrayList<Base>();
    for (Element child : children) {
      if (child.getName().equals(name))
        result.add(child);
      if (child.getName().startsWith(name) && child.getProperty().isChoice()
          && child.getProperty().getName().equals(name + "[x]"))
        result.add(child);
    }
    if (result.isEmpty() && checkValid) {
//  		throw new FHIRException("not determined yet");
    }
    return result.toArray(new Base[result.size()]);
  }

  @Override
  protected void listChildren(List<org.hl7.fhir.dstu2016may.model.Property> result) {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean isPrimitive() {
    return type != null ? ParserBase.isPrimitive(type) : property.isPrimitive(name);
  }

  @Override
  public boolean hasPrimitiveValue() {
    return property.isPrimitive(name) || property.IsLogicalAndHasPrimitiveValue(name);
  }

  @Override
  public String primitiveValue() {
    if (isPrimitive())
      return value;
    else {
      if (hasPrimitiveValue()) {
        for (Element c : children) {
          if (c.getName().equals("value"))
            return c.primitiveValue();
        }
      }
      return null;
    }
  }

  // for the validator
  public int line() {
    return line;
  }

  public int col() {
    return col;
  }

  public Element markLocation(int line, int col) {
    this.line = line;
    this.col = col;
    return this;
  }

  public Element getNamedChild(String name) {
    if (children == null)
      return null;
    Element result = null;
    for (Element child : children) {
      if (child.getName().equals(name)) {
        if (result == null)
          result = child;
        else
          throw new Error("Attempt to read a single element when there is more than one present (" + name + ")");
      }
    }
    return result;
  }

  public void getNamedChildren(String name, List<Element> list) {
    if (children != null)
      for (Element child : children)
        if (child.getName().equals(name))
          list.add(child);
  }

  public String getNamedChildValue(String name) {
    Element child = getNamedChild(name);
    return child == null ? null : child.value;
  }

  public void getNamedChildrenWithWildcard(String string, List<Element> values) {
    throw new Error("not done yet");
  }

  public XhtmlNode getXhtml() {
    return xhtml;
  }

  public Element setXhtml(XhtmlNode xhtml) {
    this.xhtml = xhtml;
    return this;
  }

  @Override
  public boolean equalsDeep(Base other) {
    if (!super.equalsDeep(other))
      return false;
    if (isPrimitive() && other.isPrimitive())
      return primitiveValue().equals(other.primitiveValue());
    if (isPrimitive() || other.isPrimitive())
      return false;
    Set<String> processed = new HashSet<String>();
    for (org.hl7.fhir.dstu2016may.model.Property p : children()) {
      String name = p.getName();
      processed.add(name);
      org.hl7.fhir.dstu2016may.model.Property o = other.getChildByName(name);
      if (!equalsDeep(p, o))
        return false;
    }
    for (org.hl7.fhir.dstu2016may.model.Property p : children()) {
      String name = p.getName();
      if (!processed.contains(name)) {
        org.hl7.fhir.dstu2016may.model.Property o = other.getChildByName(name);
        if (!equalsDeep(p, o))
          return false;
      }
    }
    return true;
  }

  private boolean equalsDeep(org.hl7.fhir.dstu2016may.model.Property p, org.hl7.fhir.dstu2016may.model.Property o) {
    if (o == null || p == null)
      return false;
    if (p.getValues().size() != o.getValues().size())
      return false;
    for (int i = 0; i < p.getValues().size(); i++)
      if (!Base.compareDeep(p.getValues().get(i), o.getValues().get(i), true))
        return false;
    return true;
  }

  @Override
  public boolean equalsShallow(Base other) {
    if (!super.equalsShallow(other))
      return false;
    if (isPrimitive() && other.isPrimitive())
      return primitiveValue().equals(other.primitiveValue());
    if (isPrimitive() || other.isPrimitive())
      return false;
    return true; // ?
  }

}
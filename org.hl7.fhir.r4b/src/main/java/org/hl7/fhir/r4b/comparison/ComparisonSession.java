package org.hl7.fhir.r4b.comparison;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.hl7.fhir.exceptions.DefinitionException;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.exceptions.FHIRFormatError;
import org.hl7.fhir.r4b.comparison.CodeSystemComparer.CodeSystemComparison;
import org.hl7.fhir.r4b.comparison.ProfileComparer.ProfileComparison;
import org.hl7.fhir.r4b.comparison.ResourceComparer.ResourceComparison;
import org.hl7.fhir.r4b.comparison.ValueSetComparer.ValueSetComparison;
import org.hl7.fhir.r4b.conformance.ProfileUtilities;
import org.hl7.fhir.r4b.conformance.ProfileUtilities.ProfileKnowledgeProvider;
import org.hl7.fhir.r4b.context.IWorkerContext;
import org.hl7.fhir.r4b.model.CanonicalResource;
import org.hl7.fhir.r4b.model.CodeSystem;
import org.hl7.fhir.r4b.model.Resource;
import org.hl7.fhir.r4b.model.StructureDefinition;
import org.hl7.fhir.r4b.model.ValueSet;
import org.hl7.fhir.utilities.MarkedToMoveToAdjunctPackage;
import org.hl7.fhir.utilities.Utilities;

@MarkedToMoveToAdjunctPackage
public class ComparisonSession {

  private Map<String, ResourceComparison> compares = new HashMap<>();
  private IWorkerContext contextLeft;
  private IWorkerContext contextRight;
  private String sessiondId;
  private int count;
  private boolean debug;
  private String title;
  private ProfileKnowledgeProvider pkp;

  public ComparisonSession(IWorkerContext contextLeft, IWorkerContext contextRight, String title,
      ProfileKnowledgeProvider pkp) {
    super();
    this.contextLeft = contextLeft;
    this.contextRight = contextRight;
    this.sessiondId = UUID.randomUUID().toString().toLowerCase();
    this.title = title;
    this.pkp = pkp;
//    debug = true;
  }

  public IWorkerContext getContextLeft() {
    return contextLeft;
  }

  public IWorkerContext getContextRight() {
    return contextRight;
  }

  public String getTitle() {
    return title;
  }

  public ResourceComparison compare(String left, String right)
      throws DefinitionException, FHIRFormatError, IOException {
    CanonicalResource l = (CanonicalResource) contextLeft.fetchResource(Resource.class, left);
    if (l == null) {
      throw new DefinitionException("Unable to resolve " + left);
    }
    CanonicalResource r = (CanonicalResource) contextRight.fetchResource(Resource.class, right);
    if (r == null) {
      throw new DefinitionException("Unable to resolve " + right);
    }
    return compare(l, r);
  }

  public ResourceComparison compare(CanonicalResource left, CanonicalResource right)
      throws DefinitionException, FHIRFormatError, IOException {
    if (left != null && right != null) {
      String key = key(left.getUrl(), left.getVersion(), right.getUrl(), right.getVersion());
      if (compares.containsKey(key)) {
        // if null then the comparison is in progress.
        // this can happen when profiles refer to each other
        return compares.get(key);
      }
      compares.put(key, null);
      try {
        if (left instanceof CodeSystem && right instanceof CodeSystem) {
          CodeSystemComparer cs = new CodeSystemComparer(this);
          CodeSystemComparison csc = cs.compare((CodeSystem) left, (CodeSystem) right);
          compares.put(key, csc);
          return csc;
        } else if (left instanceof ValueSet && right instanceof ValueSet) {
          ValueSetComparer cs = new ValueSetComparer(this);
          ValueSetComparison csc = cs.compare((ValueSet) left, (ValueSet) right);
          compares.put(key, csc);
          return csc;
        } else if (left instanceof StructureDefinition && right instanceof StructureDefinition) {
          ProfileComparer cs = new ProfileComparer(this, new ProfileUtilities(contextLeft, null, pkp),
              new ProfileUtilities(contextRight, null, pkp));
          ProfileComparison csc = cs.compare((StructureDefinition) left, (StructureDefinition) right);
          compares.put(key, csc);
          return csc;
        } else {
          throw new FHIRException(
              "Unable to compare resources of type " + left.fhirType() + " and " + right.fhirType());
        }
      } catch (Throwable e) {
        ResourceComparer.PlaceHolderComparison csc = new ResourceComparer.PlaceHolderComparison(left, right, e);
        compares.put(key, csc);
        return csc;
      }
    } else if (left != null) {
      String key = key(left.getUrl(), left.getVersion(), left.getUrl(), left.getVersion());
      if (compares.containsKey(key)) {
        return compares.get(key);
      }
      ResourceComparer.PlaceHolderComparison csc = new ResourceComparer.PlaceHolderComparison(left, right);
      compares.put(key, csc);
      return csc;
    } else {
      String key = key(right.getUrl(), right.getVersion(), right.getUrl(), right.getVersion());
      if (compares.containsKey(key)) {
        return compares.get(key);
      }
      ResourceComparer.PlaceHolderComparison csc = new ResourceComparer.PlaceHolderComparison(left, right);
      compares.put(key, csc);
      return csc;
    }
  }

  private String key(String urlL, String verL, String urlR, String verR) {
    return urlL + "|" + verL + "||" + urlR + "|" + verR;
  }

  public void identify(CanonicalResource res) {
    count++;
    res.setId(sessiondId + "-" + count);
    res.setUrl("http://hl7.org/fhir/comparison/" + res.fhirType() + "/" + res.getId());
  }

  public void identify(ResourceComparison res) {
    count++;
  }

  public boolean isDebug() {
    return debug;
  }

  public void setDebug(boolean debug) {
    this.debug = debug;
  }

  public Map<String, ResourceComparison> getCompares() {
    return compares;
  }

  public ProfileKnowledgeProvider getPkp() {
    return pkp;
  }
}
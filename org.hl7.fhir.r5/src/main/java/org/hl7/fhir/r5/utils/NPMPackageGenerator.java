package org.hl7.fhir.r5.utils;

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


import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r5.model.ContactDetail;
import org.hl7.fhir.r5.model.ContactPoint;
import org.hl7.fhir.r5.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.r5.model.Enumeration;
import org.hl7.fhir.r5.model.Enumerations.FHIRVersion;
import org.hl7.fhir.r5.model.ImplementationGuide;
import org.hl7.fhir.r5.model.ImplementationGuide.ImplementationGuideDependsOnComponent;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.cache.PackageGenerator.PackageType;
import org.hl7.fhir.utilities.cache.NpmPackageIndexBuilder;
import org.hl7.fhir.utilities.cache.ToolsVersion;
import org.hl7.fhir.utilities.json.JsonTrackingParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class NPMPackageGenerator {

  public enum Category {
    RESOURCE, EXAMPLE, OPENAPI, SCHEMATRON, RDF, OTHER, TOOL, TEMPLATE, JEKYLL;

    private String getDirectory() {
      switch (this) {
      case RESOURCE: return "/package/";
      case EXAMPLE: return "/example/";
      case OPENAPI: return "/openapi/";
      case SCHEMATRON: return "/xml/";
      case RDF: return "/rdf/";      
      case OTHER: return "/other/";      
      case TEMPLATE: return "/other/";      
      case JEKYLL: return "/jekyll/";      
      case TOOL: return "/bin/";      
      }
      return "/";
    }
  }

  private String destFile;
  private Set<String> created = new HashSet<String>();
  private TarArchiveOutputStream tar;
  private ByteArrayOutputStream OutputStream;
  private BufferedOutputStream bufferedOutputStream;
  private GzipCompressorOutputStream gzipOutputStream;
  private JsonObject packageJ;
  private JsonObject packageManifest;
  private NpmPackageIndexBuilder indexer;


  public NPMPackageGenerator(String destFile, String canonical, String url, PackageType kind, ImplementationGuide ig, Date date) throws FHIRException, IOException {
    super();
    this.destFile = destFile;
    start();
    List<String> fhirVersion = new ArrayList<>();
    for (Enumeration<FHIRVersion> v : ig.getFhirVersion())
      fhirVersion.add(v.asStringValue());
    buildPackageJson(canonical, kind, url, date, ig, fhirVersion);
  }

  public static NPMPackageGenerator subset(NPMPackageGenerator master, String destFile, String id, String name, Date date) throws FHIRException, IOException {
    JsonObject p = master.packageJ.deepCopy();
    p.remove("name");
    p.addProperty("name", id);
    p.remove("type");
    p.addProperty("type", PackageType.SUBSET.getCode());    
    p.remove("title");
    p.addProperty("title", name);

    return new NPMPackageGenerator(destFile, p, date);
  }

  public NPMPackageGenerator(String destFile, String canonical, String url, PackageType kind, ImplementationGuide ig, Date date, List<String> fhirVersion) throws FHIRException, IOException {
    super();
    this.destFile = destFile;
    start();
    buildPackageJson(canonical, kind, url, date, ig, fhirVersion);
  }

  public NPMPackageGenerator(String destFile, JsonObject npm, Date date) throws FHIRException, IOException {
    super();
    String dt = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
    packageJ = npm;
    packageManifest = new JsonObject();
    packageManifest.addProperty("version", npm.get("version").getAsString());
    packageManifest.addProperty("date", dt);
    npm.addProperty("date", dt);
    packageManifest.addProperty("name", npm.get("name").getAsString());
    this.destFile = destFile;
    start();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(npm);
    try {
      addFile(Category.RESOURCE, "package.json", json.getBytes("UTF-8"));
    } catch (UnsupportedEncodingException e) {
    }
  }

  private void buildPackageJson(String canonical, PackageType kind, String web, Date date, ImplementationGuide ig, List<String> fhirVersion) throws FHIRException, IOException {
    String dtHuman = new SimpleDateFormat("EEE, MMM d, yyyy HH:mmZ", new Locale("en", "US")).format(date);
    String dt = new SimpleDateFormat("yyyyMMddHHmmss").format(date);

    CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
    if (!ig.hasPackageId())
      b.append("packageId");
    if (!ig.hasVersion())
      b.append("version");
    if (!ig.hasFhirVersion())
      b.append("fhirVersion");
    if (!ig.hasLicense())
      b.append("license");
    for (ImplementationGuideDependsOnComponent d : ig.getDependsOn()) {
      if (!d.hasVersion()) {
        b.append("dependsOn.version("+d.getUri()+")");
      }
    }

    JsonObject npm = new JsonObject();
    npm.addProperty("name", ig.getPackageId());
    npm.addProperty("version", ig.getVersion());
    npm.addProperty("tools-version", ToolsVersion.TOOLS_VERSION);
    npm.addProperty("type", kind.getCode());
    npm.addProperty("date", dt);
    if (ig.hasLicense())
      npm.addProperty("license", ig.getLicense().toCode());
    npm.addProperty("canonical", canonical);
    npm.addProperty("url", web);
    if (ig.hasTitle())
      npm.addProperty("title", ig.getTitle());
    if (ig.hasDescription())
      npm.addProperty("description", ig.getDescription()+ " (built "+dtHuman+timezone()+")");
    if (kind != PackageType.CORE) {
      JsonObject dep = new JsonObject();
      npm.add("dependencies", dep);
      for (String v : fhirVersion) { // TODO: fix for multiple versions
        dep.addProperty("hl7.fhir.core", v);
      }
      for (ImplementationGuideDependsOnComponent d : ig.getDependsOn()) {
        dep.addProperty(d.getPackageId(), d.getVersion());
      }
    }
    if (ig.hasPublisher())
      npm.addProperty("author", ig.getPublisher());
    JsonArray m = new JsonArray();
    for (ContactDetail t : ig.getContact()) {
      String email = email(t.getTelecom());
      String url = url(t.getTelecom());
      if (t.hasName() & (email != null || url != null)) {
        JsonObject md = new JsonObject();
        m.add(md);
        md.addProperty("name", t.getName());
        if (email != null)
          md.addProperty("email", email);
        if (url != null)
          md.addProperty("url", url);
      }
    }
    if (m.size() > 0)
      npm.add("maintainers", m);
    if (ig.getManifest().hasRendering())
      npm.addProperty("homepage", ig.getManifest().getRendering());
    JsonObject dir = new JsonObject();
    npm.add("directories", dir);
    dir.addProperty("lib", "package");
    dir.addProperty("example", "example");
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(npm);
    try {
      addFile(Category.RESOURCE, "package.json", json.getBytes("UTF-8"));
    } catch (UnsupportedEncodingException e) {
    }
    packageJ = npm;

    packageManifest = new JsonObject();
    packageManifest.addProperty("version", ig.getVersion());
    packageManifest.addProperty("fhirVersion", fhirVersion.toString());
    packageManifest.addProperty("date", dt);
    packageManifest.addProperty("name", ig.getPackageId());

  }


  private String timezone() {
    TimeZone tz = TimeZone.getDefault();  
    Calendar cal = GregorianCalendar.getInstance(tz);
    int offsetInMillis = tz.getOffset(cal.getTimeInMillis());

    String offset = String.format("%02d:%02d", Math.abs(offsetInMillis / 3600000), Math.abs((offsetInMillis / 60000) % 60));
    offset = (offsetInMillis >= 0 ? "+" : "-") + offset;

    return offset;
  }


  private String url(List<ContactPoint> telecom) {
    for (ContactPoint cp : telecom) {
      if (cp.getSystem() == ContactPointSystem.URL)
        return cp.getValue();
    }
    return null;
  }


  private String email(List<ContactPoint> telecom) {
    for (ContactPoint cp : telecom) {
      if (cp.getSystem() == ContactPointSystem.EMAIL)
        return cp.getValue();
    }
    return null;
  }

  private void start() throws IOException {
    OutputStream = new ByteArrayOutputStream();
    bufferedOutputStream = new BufferedOutputStream(OutputStream);
    gzipOutputStream = new GzipCompressorOutputStream(bufferedOutputStream);
    tar = new TarArchiveOutputStream(gzipOutputStream);
    indexer = new NpmPackageIndexBuilder();
    indexer.start();
  }


  public void addFile(Category cat, String name, byte[] content) throws IOException {
    String path = cat.getDirectory()+name;
    if (created.contains(path)) 
      System.out.println("Duplicate package file "+path);
    else {
      created.add(path);
      TarArchiveEntry entry = new TarArchiveEntry(path);
      entry.setSize(content.length);
      tar.putArchiveEntry(entry);
      tar.write(content);
      tar.closeArchiveEntry();
      if(cat == Category.RESOURCE) {
        indexer.seeFile(name, content);
      }
    }
  }

  public void finish() throws IOException {
    buildIndexJson();
    tar.finish();
    tar.close();
    gzipOutputStream.close();
    bufferedOutputStream.close();
    OutputStream.close();
    TextFile.bytesToFile(OutputStream.toByteArray(), destFile);
    // also, for cache management on current builds, generate a little manifest
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(packageManifest);
    TextFile.stringToFile(json, Utilities.changeFileExt(destFile, ".manifest.json"), false);
  }

  private void buildIndexJson() throws IOException {
    byte[] content = indexer.build().getBytes(Charset.forName("UTF-8"));
    addFile(Category.RESOURCE, ".index.json", content); 
  }

  public String filename() {
    return destFile;
  }

  public void loadDir(String rootDir, String name) throws IOException {
    loadFiles(rootDir, new File(Utilities.path(rootDir, name)));
  }

  public void loadFiles(String root, File dir, String... noload) throws IOException {
    for (File f : dir.listFiles()) {
      if (!Utilities.existsInList(f.getName(), noload)) {
        if (f.isDirectory()) {
          loadFiles(root, f);
        } else {
          String path = f.getAbsolutePath().substring(root.length()+1);
          byte[] content = TextFile.fileToBytes(f);
          if (created.contains(path)) 
            System.out.println("Duplicate package file "+path);
          else {
            created.add(path);
            TarArchiveEntry entry = new TarArchiveEntry(path);
            entry.setSize(content.length);
            tar.putArchiveEntry(entry);
            tar.write(content);
            tar.closeArchiveEntry();
          }
        }
      }
    }
  }


}

package org.hl7.fhir.validation.cli.services;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Nonnull;

import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r5.conformance.profile.ProfileUtilities;
import org.hl7.fhir.r5.context.ContextUtilities;
import org.hl7.fhir.r5.context.SimpleWorkerContext;
import org.hl7.fhir.r5.context.SystemOutLoggingService;
import org.hl7.fhir.r5.elementmodel.Element;
import org.hl7.fhir.r5.elementmodel.LanguageUtils;
import org.hl7.fhir.r5.elementmodel.Manager;
import org.hl7.fhir.r5.elementmodel.Manager.FhirFormat;
import org.hl7.fhir.r5.elementmodel.ValidatedFragment;
import org.hl7.fhir.r5.fhirpath.FHIRPathEngine;
import org.hl7.fhir.r5.formats.IParser;
import org.hl7.fhir.r5.formats.IParser.OutputStyle;
import org.hl7.fhir.r5.liquid.BaseTableWrapper;
import org.hl7.fhir.r5.liquid.GlobalObject.GlobalObjectRandomFunction;
import org.hl7.fhir.r5.liquid.LiquidEngine;
import org.hl7.fhir.r5.model.Bundle;
import org.hl7.fhir.r5.model.CanonicalResource;
import org.hl7.fhir.r5.model.CodeSystem;
import org.hl7.fhir.r5.model.ConceptMap;
import org.hl7.fhir.r5.model.DateTimeType;
import org.hl7.fhir.r5.model.OperationOutcome;
import org.hl7.fhir.r5.model.Resource;
import org.hl7.fhir.r5.model.StringType;
import org.hl7.fhir.r5.model.StructureDefinition;
import org.hl7.fhir.r5.model.StructureMap;
import org.hl7.fhir.r5.model.ValueSet;
import org.hl7.fhir.r5.profilemodel.gen.PECodeGenerator;
import org.hl7.fhir.r5.profilemodel.gen.PECodeGenerator.ExtensionPolicy;
import org.hl7.fhir.r5.renderers.spreadsheets.CodeSystemSpreadsheetGenerator;
import org.hl7.fhir.r5.renderers.spreadsheets.ConceptMapSpreadsheetGenerator;
import org.hl7.fhir.r5.renderers.spreadsheets.StructureDefinitionSpreadsheetGenerator;
import org.hl7.fhir.r5.renderers.spreadsheets.ValueSetSpreadsheetGenerator;
import org.hl7.fhir.r5.terminologies.CodeSystemUtilities;
import org.hl7.fhir.r5.terminologies.client.TerminologyClientManager.InternalLogEvent;
import org.hl7.fhir.r5.terminologies.utilities.TerminologyCache;
import org.hl7.fhir.r5.testfactory.TestDataFactory;
import org.hl7.fhir.r5.testfactory.TestDataHostServices;
import org.hl7.fhir.r5.Constants;
import org.hl7.fhir.r5.utils.validation.constants.ReferenceValidationPolicy;
import org.hl7.fhir.utilities.FhirPublication;
import org.hl7.fhir.utilities.SystemExitManager;
import org.hl7.fhir.utilities.FileUtilities;
import org.hl7.fhir.utilities.TimeTracker;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.VersionUtilities;
import org.hl7.fhir.utilities.filesystem.ManagedFileAccess;
import org.hl7.fhir.utilities.i18n.JsonLangFileProducer;
import org.hl7.fhir.utilities.i18n.LanguageFileProducer.LanguageProducerLanguageSession;
import org.hl7.fhir.utilities.i18n.LanguageFileProducer.LanguageProducerSession;
import org.hl7.fhir.utilities.i18n.LanguageFileProducer.TranslationUnit;
import org.hl7.fhir.utilities.i18n.PoGetTextProducer;
import org.hl7.fhir.utilities.i18n.XLIFFProducer;
import org.hl7.fhir.utilities.json.model.JsonObject;
import org.hl7.fhir.utilities.json.parser.JsonParser;
import org.hl7.fhir.utilities.npm.FilesystemPackageCacheManager;
import org.hl7.fhir.utilities.npm.NpmPackage;
import org.hl7.fhir.utilities.validation.ValidationMessage;
import org.hl7.fhir.validation.IgLoader;
import org.hl7.fhir.validation.ResourceChecker;
import org.hl7.fhir.validation.ValidationEngine;
import org.hl7.fhir.validation.ValidationRecord;
import org.hl7.fhir.validation.ValidatorUtils;
import org.hl7.fhir.validation.ValidatorUtils.SourceFile;
import org.hl7.fhir.validation.cli.model.CliContext;
import org.hl7.fhir.validation.cli.model.FileInfo;
import org.hl7.fhir.validation.cli.model.ValidatedFragments;
import org.hl7.fhir.validation.cli.model.ValidationOutcome;
import org.hl7.fhir.validation.cli.model.ValidationRequest;
import org.hl7.fhir.validation.cli.model.ValidationResponse;
import org.hl7.fhir.validation.cli.renderers.CSVRenderer;
import org.hl7.fhir.validation.cli.renderers.CompactRenderer;
import org.hl7.fhir.validation.cli.renderers.DefaultRenderer;
import org.hl7.fhir.validation.cli.renderers.ESLintCompactRenderer;
import org.hl7.fhir.validation.cli.renderers.NativeRenderer;
import org.hl7.fhir.validation.cli.renderers.ValidationOutputRenderer;
import org.hl7.fhir.validation.cli.utils.Common;
import org.hl7.fhir.validation.cli.utils.EngineMode;
import org.hl7.fhir.validation.cli.utils.VersionSourceInformation;
import org.hl7.fhir.validation.instance.advisor.BasePolicyAdvisorForFullValidation;
import org.hl7.fhir.validation.instance.advisor.JsonDrivenPolicyAdvisor;
import org.hl7.fhir.validation.instance.advisor.TextDrivenPolicyAdvisor;

public class ValidationService {

  private final SessionCache sessionCache;
  private String runDate;

  private final Map<String, ValidationEngine> baseEngines = new ConcurrentHashMap<>();

  public void putBaseEngine(String key, CliContext cliContext) throws IOException, URISyntaxException {
    if (cliContext.getSv() == null) {
      throw new IllegalArgumentException("Cannot create a base engine without an explicit version");
    }
    String definitions = VersionUtilities.packageForVersion(cliContext.getSv()) + "#" + VersionUtilities.getCurrentVersion(cliContext.getSv());

    ValidationEngine baseEngine = buildValidationEngine(cliContext, definitions, new TimeTracker());
    baseEngines.put(key, baseEngine);
  }

  public ValidationEngine getBaseEngine(String key) {
    return baseEngines.get(key);
  }

  public Set<String> getBaseEngineKeys() { return baseEngines.keySet(); }

  public boolean hasBaseEngineForKey(String key) { return baseEngines.containsKey(key); }

  public ValidationService() {
    sessionCache = new PassiveExpiringSessionCache();
    runDate = new SimpleDateFormat("hh:mm:ss", new Locale("en", "US")).format(new Date());
  }



  public ValidationService(SessionCache cache) {
    this.sessionCache = cache;
  }

  public ValidationResponse validateSources(ValidationRequest request) throws Exception {

    TimeTracker timeTracker = new TimeTracker();
    String sessionId = initializeValidator(request.getCliContext(), null, timeTracker, request.sessionId);
    ValidationEngine validationEngine = sessionCache.fetchSessionValidatorEngine(sessionId);

    /* Cached validation engines already have expensive setup like loading definitions complete. But it wouldn't make
       sense to rebuild a whole engine to change the language, so we manually change it here.
     */
    validationEngine.setLanguage(request.getCliContext().getLang());
    validationEngine.setLocale(request.getCliContext().getLocale());
    if (request.getCliContext().getProfiles().size() > 0) {
      System.out.println("  .. validate " + request.listSourceFiles() + " against " + request.getCliContext().getProfiles().toString());
    } else {
      System.out.println("  .. validate " + request.listSourceFiles());
    }

    ValidationResponse response = new ValidationResponse().setSessionId(sessionId).setValidationTimes(new HashMap<>());

    for (FileInfo fileToValidate : request.getFilesToValidate()) {
      if (fileToValidate.getFileType() == null) {
        Manager.FhirFormat format = ResourceChecker.checkIsResource(validationEngine.getContext(),
          false,
          fileToValidate.getFileContent().getBytes(),
          fileToValidate.getFileName(),
          false);
        if (format != null) {
          fileToValidate.setFileType(format.getExtension());
        }
      }

      List<ValidationMessage> messages = new ArrayList<>();

      if (fileToValidate.getFileType() == null) {
          ValidationOutcome outcome = getValidationOutcomeForUnknownFileFormat(
            new FileInfo(fileToValidate.getFileName(), fileToValidate.getFileContent(), null));
          response.addOutcome(outcome);
      } else {
        ValidatedFragments validatedFragments = validationEngine.validateAsFragments(fileToValidate.getFileContent().getBytes(), Manager.FhirFormat.getFhirFormat(fileToValidate.getFileType()),
          request.getCliContext().getProfiles(), messages);

        List<ValidationOutcome> validationOutcomes = getValidationOutcomesFromValidatedFragments(fileToValidate, validatedFragments);
        for (ValidationOutcome validationOutcome : validationOutcomes) {
          response.addOutcome(validationOutcome);
        }

        if (request.getCliContext().isShowTimes()) {
          response.getValidationTimes().put(fileToValidate.getFileName(), validatedFragments.getValidationTime());
        }
        
      }
    }

    System.out.println("  Max Memory: "+Runtime.getRuntime().maxMemory());
    return response;
  }

  private List<ValidationOutcome> getValidationOutcomesFromValidatedFragments(FileInfo fileToValidate, ValidatedFragments validatedFragments) {
    List<ValidationOutcome> outcomes = new LinkedList<>();
    if (validatedFragments.getValidatedFragments().size() == 1 && !validatedFragments.getValidatedFragments().get(0).isDerivedContent()) {
      ValidatedFragment validatedFragment = validatedFragments.getValidatedFragments().get(0);
      ValidationOutcome outcome = new ValidationOutcome();
      FileInfo fileInfo = new FileInfo(
        fileToValidate.getFileName(),
        new String(validatedFragment.getContent()),
        validatedFragment.getExtension());
      outcome.setMessages(validatedFragment.getErrors());
      outcome.setFileInfo(fileInfo);
      outcomes.add(outcome);
    } else {
      for (ValidatedFragment validatedFragment : validatedFragments.getValidatedFragments()) {
        ValidationOutcome outcome = new ValidationOutcome();
        FileInfo fileInfo = new FileInfo(
          validatedFragment.getFilename(),
          new String(validatedFragment.getContent()),
          validatedFragment.getExtension());
        outcome.setMessages(validatedFragment.getErrors());
        outcome.setFileInfo(fileInfo);
        outcomes.add(outcome);
      }
    }
    return outcomes;
  }

  private ValidationOutcome getValidationOutcomeForUnknownFileFormat(FileInfo fileInfo) {
    ValidationOutcome outcome = new ValidationOutcome();

    List<ValidationMessage> errorList = new ArrayList<>() {{
      add(new ValidationMessage().setType(ValidationMessage.IssueType.EXCEPTION).setLevel(ValidationMessage.IssueSeverity.FATAL).setMessage("Unable to infer format from file. Please check that your file is in a valid FHIR format."));

    } };
    outcome.setMessages(errorList);
    outcome.setFileInfo(fileInfo);
    return outcome;
  }

  public VersionSourceInformation scanForVersions(CliContext cliContext) throws IOException {
    VersionSourceInformation versions = new VersionSourceInformation();
    IgLoader igLoader = new IgLoader(
      new FilesystemPackageCacheManager.Builder().build(),
      new SimpleWorkerContext.SimpleWorkerContextBuilder().fromNothing(),
      null);
    for (String src : cliContext.getIgs()) {
      igLoader.scanForIgVersion(src, cliContext.isRecursive(), versions);
    }
    igLoader.scanForVersions(cliContext.getSources(), versions);
    return versions;
  }

  public void validateSources(CliContext cliContext, ValidationEngine validator, ValidatorWatchMode watch, int watchScanDelay, int watchSettleTime) throws Exception {
    if (cliContext.getProfiles().size() > 0) {
      System.out.println("  Profiles: " + cliContext.getProfiles());
    }
    IgLoader igLoader = new IgLoader(validator.getPcm(), validator.getContext(), validator.getVersion());
        
    List<ValidationRecord> records = new ArrayList<>();
    List<SourceFile> refs = new ArrayList<>();

    int ec = 0;
    boolean first = true;

    do {
      long start = System.currentTimeMillis();
      Resource r = validator.validate(cliContext.getSources(), cliContext.getProfiles(), refs, records, igLoader, watch == ValidatorWatchMode.ALL, watchSettleTime, first);
      first = false;
      boolean statusNeeded = false;
      if (r != null) {
        statusNeeded = true;
        MemoryMXBean mbean = ManagementFactory.getMemoryMXBean();
        System.out.println("Done. " + validator.getContext().clock().report()+". Memory = "+Utilities.describeSize(mbean.getHeapMemoryUsage().getUsed()+mbean.getNonHeapMemoryUsage().getUsed()));
        System.out.println();

        PrintStream dst = null;
        ValidationOutputRenderer renderer = makeValidationOutputRenderer(cliContext);
        renderer.setCrumbTrails(validator.isCrumbTrails());
        renderer.setShowMessageIds(validator.isShowMessageIds());
        renderer.setRunDate(runDate);
        if (renderer.isSingleFile()) {
          if (cliContext.getOutput() == null) {
            dst = System.out;
          } else {
            dst = new PrintStream(ManagedFileAccess.outStream(Utilities.path(cliContext.getOutput())));
          }
          renderer.setOutput(dst);
        } else {
          File dir = ManagedFileAccess.file(cliContext.getOutput());
          if (!dir.isDirectory()) {
            throw new Error("The output location "+dir.getAbsolutePath()+" must be an existing directory for the output style "+renderer.getStyleCode());
          }
          renderer.setFolder(dir);
        }

        if (r instanceof Bundle) {
          if (renderer.handlesBundleDirectly()) {
            renderer.render((Bundle) r);
          } else {
            renderer.start(((Bundle) r).getEntry().size() > 1);
            for (Bundle.BundleEntryComponent e : ((Bundle) r).getEntry()) {
              OperationOutcome op = (OperationOutcome) e.getResource();
              ec = ec + countErrors(op);
              renderer.render(op);
            }
            renderer.finish();
          }
        } else if (r == null) {
          ec = ec + 1;
          System.out.println("No output from validation - nothing to validate");
        } else {
          renderer.start(false);
          OperationOutcome op = (OperationOutcome) r;
          ec = countErrors(op);
          renderer.render((OperationOutcome) r);
          renderer.finish();
        }

        if (cliContext.getOutput() != null && dst != null) {
          dst.close();
        }

        if (cliContext.getHtmlOutput() != null) {
          String html = new HTMLOutputGenerator(records).generate(System.currentTimeMillis() - start);
          FileUtilities.stringToFile(html, cliContext.getHtmlOutput());
          System.out.println("HTML Summary in " + cliContext.getHtmlOutput());
        }

        if (cliContext.isShowTerminologyRouting()) {
          System.out.println("");
          System.out.println("Terminology Routing Dump ---------------------------------------");
          if (validator.getContext().getTxClientManager().getInternalLog().isEmpty()) {
            System.out.println("(nothing happened)");            
          } else {
            for (InternalLogEvent log : validator.getContext().getTxClientManager().getInternalLog()) {
              System.out.println(log.getMessage()+" -> "+log.getServer()+" (for VS "+log.getVs()+" with systems '"+log.getSystems()+"', choices = '"+log.getChoices()+"')");
            }
          }
          validator.getContext().getTxClientManager().getInternalLog().clear();
        }
      }
      if (watch != ValidatorWatchMode.NONE) {
        if (statusNeeded) {
          System.out.println("Watching for changes ("+Integer.toString(watchScanDelay)+"ms cycle)");
        }
        Thread.sleep(watchScanDelay);
      }
    } while (watch != ValidatorWatchMode.NONE);
    if (ec > 0) {
      SystemExitManager.setError(1);
    }
  }

  private int countErrors(OperationOutcome oo) {
    int error = 0;
    for (OperationOutcome.OperationOutcomeIssueComponent issue : oo.getIssue()) {
      if (issue.getSeverity() == OperationOutcome.IssueSeverity.FATAL || issue.getSeverity() == OperationOutcome.IssueSeverity.ERROR)
        error++;
    }
    return error;
  }

  private ValidationOutputRenderer makeValidationOutputRenderer(CliContext cliContext) {
    String style = cliContext.getOutputStyle();
    // adding to this list?
    // Must document the option at https://confluence.hl7.org/display/FHIR/Using+the+FHIR+Validator#UsingtheFHIRValidator-ManagingOutput
    // if you're going to make a PR, document the link where the outputstyle is documented, along with a sentence that describes it, in the PR notes
    if (Utilities.noString(style)) {
      if (cliContext.getOutput() == null) {
        return new DefaultRenderer();
      } else if (cliContext.getOutput().endsWith(".json")) {
        return new NativeRenderer(FhirFormat.JSON);
      } else {
        return new NativeRenderer(FhirFormat.XML);
      }
    } else if (Utilities.existsInList(style, "eslint-compact")) {
      return new ESLintCompactRenderer();
    } else if (Utilities.existsInList(style, "compact-split")) {
      return new CompactRenderer(true);
    } else if (Utilities.existsInList(style, "compact")) {
      return new CompactRenderer(false);
    } else if (Utilities.existsInList(style, "csv")) {
      return new CSVRenderer();
    } else if (Utilities.existsInList(style, "xml")) {
      return new NativeRenderer(FhirFormat.XML);
    } else if (Utilities.existsInList(style, "json")) {
      return new NativeRenderer(FhirFormat.JSON);
    } else {
      System.out.println("Unknown output style '"+style+"'");
      return new DefaultRenderer();
    }
  }

  public void convertSources(CliContext cliContext, ValidationEngine validator) throws Exception {

      if (!((cliContext.getOutput() == null) ^ (cliContext.getOutputSuffix() == null))) {
        throw new Exception("Convert requires one of {-output, -outputSuffix} parameter to be set");
      }

      List<String> sources = cliContext.getSources();
      if ((sources.size() == 1) && (cliContext.getOutput() != null)) {
        System.out.println(" ...convert");
        validator.convert(sources.get(0), cliContext.getOutput());
      } else {
        if (cliContext.getOutputSuffix() == null) {
          throw new Exception("Converting multiple/wildcard sources requires a -outputSuffix parameter to be set");
        }
        for (int i = 0; i < sources.size(); i++) {
            String output = sources.get(i) + "." + cliContext.getOutputSuffix();
            validator.convert(sources.get(i), output);
            System.out.println(" ...convert [" + i +  "] (" + sources.get(i) + " to " + output + ")");
        }
      }
  }

  public void evaluateFhirpath(CliContext cliContext, ValidationEngine validator) throws Exception {
    System.out.println(" ...evaluating " + cliContext.getFhirpath());
    System.out.println(validator.evaluateFhirPath(cliContext.getSources().get(0), cliContext.getFhirpath()));
  }

  public void generateSnapshot(CliContext cliContext, ValidationEngine validator) throws Exception {

      if (!((cliContext.getOutput() == null) ^ (cliContext.getOutputSuffix() == null))) {
        throw new Exception("Snapshot generation requires one of {-output, -outputSuffix} parameter to be set");
      }

      List<String> sources = cliContext.getSources();
      if ((sources.size() == 1) && (cliContext.getOutput() != null)) {
        StructureDefinition r = validator.snapshot(sources.get(0), cliContext.getSv());
        System.out.println(" ...generated snapshot successfully");
        validator.handleOutput(r, cliContext.getOutput(), cliContext.getSv());
      } else {
        if (cliContext.getOutputSuffix() == null) {
          throw new Exception("Snapshot generation for multiple/wildcard sources requires a -outputSuffix parameter to be set");
        }
        for (int i = 0; i < sources.size(); i++) {
          StructureDefinition r = validator.snapshot(sources.get(i), cliContext.getSv());
          String output = sources.get(i) + "." + cliContext.getOutputSuffix();
          validator.handleOutput(r, output, cliContext.getSv());
          System.out.println(" ...generated snapshot [" + i +  "] successfully (" + sources.get(i) + " to " + output + ")");
        }
      }

  }

  public void generateNarrative(CliContext cliContext, ValidationEngine validator) throws Exception {
    Resource r = validator.generate(cliContext.getSources().get(0), cliContext.getSv());
    System.out.println(" ...generated narrative successfully");
    if (cliContext.getOutput() != null) {
      validator.handleOutput(r, cliContext.getOutput(), cliContext.getSv());
    }
  }

  public void transform(CliContext cliContext, ValidationEngine validator) throws Exception {
    if (cliContext.getSources().size() > 1)
      throw new Exception("Can only have one source when doing a transform (found " + cliContext.getSources() + ")");
    if (cliContext.getTxServer() == null)
      throw new Exception("Must provide a terminology server when doing a transform");
    if (cliContext.getMap() == null)
      throw new Exception("Must provide a map when doing a transform");
    try {
      ContextUtilities cu = new ContextUtilities(validator.getContext());
      List<StructureDefinition> structures =  cu.allStructures();
      for (StructureDefinition sd : structures) {
        if (!sd.hasSnapshot()) {
          cu.generateSnapshot(sd);
        }
      }
      validator.setMapLog(cliContext.getMapLog());
      org.hl7.fhir.r5.elementmodel.Element r = validator.transform(cliContext.getSources().get(0), cliContext.getMap());
      System.out.println(" ...success");
      if (cliContext.getOutput() != null) {
        FileOutputStream s = ManagedFileAccess.outStream(cliContext.getOutput());
        if (cliContext.getOutput() != null && cliContext.getOutput().endsWith(".json"))
          new org.hl7.fhir.r5.elementmodel.JsonParser(validator.getContext()).compose(r, s, IParser.OutputStyle.PRETTY, null);
        else
          new org.hl7.fhir.r5.elementmodel.XmlParser(validator.getContext()).compose(r, s, IParser.OutputStyle.PRETTY, null);
        s.close();
      }
    } catch (Exception e) {
      System.out.println(" ...Failure: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public void compile(CliContext cliContext, ValidationEngine validator) throws Exception {
    if (cliContext.getSources().size() > 0)
      throw new Exception("Cannot specify sources when compling transform (found " + cliContext.getSources() + ")");
    if (cliContext.getMap() == null)
      throw new Exception("Must provide a map when compiling a transform");
    if (cliContext.getOutput() == null)
      throw new Exception("Must provide an output name when compiling a transform");
    try {
      ContextUtilities cu = new ContextUtilities(validator.getContext());
      List<StructureDefinition> structures = cu.allStructures();
      for (StructureDefinition sd : structures) {
        if (!sd.hasSnapshot()) {
          cu.generateSnapshot(sd);
        }
      }
      validator.setMapLog(cliContext.getMapLog());
      StructureMap map = validator.compile(cliContext.getMap());
      if (map == null)
        throw new Exception("Unable to locate map " + cliContext.getMap());
      validator.handleOutput(map, cliContext.getOutput(), validator.getVersion());
      System.out.println(" ...success");
    } catch (Exception e) {
      System.out.println(" ...Failure: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public void transformVersion(CliContext cliContext, ValidationEngine validator) throws Exception {
    if (cliContext.getSources().size() > 1) {
      throw new Exception("Can only have one source when converting versions (found " + cliContext.getSources() + ")");
    }
    if (cliContext.getTargetVer() == null) {
      throw new Exception("Must provide a map when converting versions");
    }
    if (cliContext.getOutput() == null) {
      throw new Exception("Must nominate an output when converting versions");
    }
    try {
      if (cliContext.getMapLog() != null) {
        validator.setMapLog(cliContext.getMapLog());
      }
      byte[] r = validator.transformVersion(cliContext.getSources().get(0), cliContext.getTargetVer(), cliContext.getOutput().endsWith(".json") ? Manager.FhirFormat.JSON : Manager.FhirFormat.XML, cliContext.getCanDoNative());
      System.out.println(" ...success");
      FileUtilities.bytesToFile(r, cliContext.getOutput());
    } catch (Exception e) {
      System.out.println(" ...Failure: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public ValidationEngine initializeValidator(CliContext cliContext, String definitions, TimeTracker tt) throws Exception {
    return sessionCache.fetchSessionValidatorEngine(initializeValidator(cliContext, definitions, tt, null));
  }

  public String initializeValidator(CliContext cliContext, String definitions, TimeTracker tt, String sessionId) throws Exception {
    tt.milestone();

    if (!sessionCache.sessionExists(sessionId)) {
      if (sessionId != null) {
        System.out.println("No such cached session exists for session id " + sessionId + ", re-instantiating validator.");
      }
      sessionCache.cleanUp();
      if (cliContext.getSv() == null) {
        String sv = determineVersion(cliContext);
        cliContext.setSv(sv);
      }
      final String engineDefinitions = definitions != null ? definitions : VersionUtilities.packageForVersion(cliContext.getSv()) + "#" + VersionUtilities.getCurrentVersion(cliContext.getSv());

      ValidationEngine validationEngine = getValidationEngineFromCliContext(cliContext, engineDefinitions, tt);
      sessionId = sessionCache.cacheSession(validationEngine);
      System.out.println("Cached new session. Cache size = " + sessionCache.getSessionIds().size());

    } else {
      System.out.println("Cached session exists for session id " + sessionId + ", returning stored validator session id. Cache size = " + sessionCache.getSessionIds().size());
    }
    return sessionId;
  }

  private ValidationEngine getValidationEngineFromCliContext(CliContext cliContext, String definitions, TimeTracker tt) throws Exception {
    ValidationEngine validationEngine;
    if (cliContext.getBaseEngine() != null && hasBaseEngineForKey(cliContext.getBaseEngine())) {
      validationEngine = new ValidationEngine(getBaseEngine(cliContext.getBaseEngine()));
    } else {
      if (definitions == null) {
        throw new IllegalArgumentException("Cannot create a validator engine (definitions == null)");
      }
      validationEngine = buildValidationEngine(cliContext, definitions, tt);
    }
    return validationEngine;
  }

  protected ValidationEngine.ValidationEngineBuilder getValidationEngineBuilder() {
    return new ValidationEngine.ValidationEngineBuilder();
  }

  @Nonnull
  protected ValidationEngine buildValidationEngine(CliContext cliContext, String definitions, TimeTracker timeTracker) throws IOException, URISyntaxException {
    System.out.print("  Load FHIR v" + cliContext.getSv() + " from " + definitions);
    ValidationEngine validationEngine = getValidationEngineBuilder().withVersion(cliContext.getSv()).withTimeTracker(timeTracker)
        .withUserAgent(Common.getValidatorUserAgent()).withThoVersion(Constants.THO_WORKING_VERSION)
        .withExtensionsVersion(Constants.EXTENSIONS_WORKING_VERSION).fromSource(definitions);

    System.out.println(" - " + validationEngine.getContext().countAllCaches() + " resources (" + timeTracker.milestone() + ")");

    loadIgsAndExtensions(validationEngine, cliContext, timeTracker);
    if (cliContext.getTxCache() != null) {
      TerminologyCache cache = new TerminologyCache(new Object(), cliContext.getTxCache());
      validationEngine.getContext().initTxCache(cache);
    }
    if (validationEngine.getContext().getTxCache() == null || validationEngine.getContext().getTxCache().getFolder() == null) {
      System.out.println("  No Terminology Cache");      
    } else {
      System.out.println("  Terminology Cache at "+validationEngine.getContext().getTxCache().getFolder());
      if (cliContext.isClearTxCache()) {
        System.out.println("  Terminology Cache Entries Cleaned out");
        validationEngine.getContext().getTxCache().clear();
      }
    }
    System.out.print("  Get set... ");
    validationEngine.setQuestionnaireMode(cliContext.getQuestionnaireMode());
    validationEngine.setLevel(cliContext.getLevel());
    validationEngine.setDoNative(cliContext.isDoNative());
    validationEngine.setHintAboutNonMustSupport(cliContext.isHintAboutNonMustSupport());
    for (String s : cliContext.getExtensions()) {
      if ("any".equals(s)) {
        validationEngine.setAnyExtensionsAllowed(true);
      } else {
        validationEngine.getExtensionDomains().add(s);
      }
    }
    validationEngine.setLanguage(cliContext.getLang());
    validationEngine.setLocale(cliContext.getLocale());
    validationEngine.setSnomedExtension(cliContext.getSnomedCTCode());
    validationEngine.setAssumeValidRestReferences(cliContext.isAssumeValidRestReferences());
    validationEngine.setShowMessagesFromReferences(cliContext.isShowMessagesFromReferences());
    validationEngine.setDoImplicitFHIRPathStringConversion(cliContext.isDoImplicitFHIRPathStringConversion());
    validationEngine.setHtmlInMarkdownCheck(cliContext.getHtmlInMarkdownCheck());
    validationEngine.setAllowDoubleQuotesInFHIRPath(cliContext.isAllowDoubleQuotesInFHIRPath());
    validationEngine.setNoExtensibleBindingMessages(cliContext.isNoExtensibleBindingMessages());
    validationEngine.setNoUnicodeBiDiControlChars(cliContext.isNoUnicodeBiDiControlChars());
    validationEngine.setNoInvariantChecks(cliContext.isNoInvariants());
    validationEngine.setDisplayWarnings(cliContext.isDisplayWarnings());
    validationEngine.setBestPracticeLevel(cliContext.getBestPracticeLevel());
    validationEngine.setCheckIPSCodes(cliContext.isCheckIPSCodes());
    validationEngine.setWantInvariantInMessage(cliContext.isWantInvariantsInMessages());
    validationEngine.setSecurityChecks(cliContext.isSecurityChecks());
    validationEngine.setCrumbTrails(cliContext.isCrumbTrails());
    validationEngine.setShowMessageIds(cliContext.isShowMessageIds());
    validationEngine.setForPublication(cliContext.isForPublication());
    validationEngine.setShowTimes(cliContext.isShowTimes());
    validationEngine.setAllowExampleUrls(cliContext.isAllowExampleUrls());
    validationEngine.setAiService(cliContext.getAIService());
    validationEngine.setR5BundleRelativeReferencePolicy(cliContext.getR5BundleRelativeReferencePolicy());
    ReferenceValidationPolicy refpol = ReferenceValidationPolicy.CHECK_VALID;
    if (!cliContext.isDisableDefaultResourceFetcher()) {
      StandAloneValidatorFetcher fetcher = new StandAloneValidatorFetcher(validationEngine.getPcm(), validationEngine.getContext(), validationEngine);
      validationEngine.setFetcher(fetcher);
      validationEngine.getContext().setLocator(fetcher);
      validationEngine.setPolicyAdvisor(fetcher);
      if (cliContext.isCheckReferences()) {
        fetcher.setReferencePolicy(ReferenceValidationPolicy.CHECK_VALID);
      } else {
        fetcher.setReferencePolicy(ReferenceValidationPolicy.IGNORE);        
      }
      fetcher.setResolutionContext(cliContext.getResolutionContext());
    } else {
      DisabledValidationPolicyAdvisor fetcher = new DisabledValidationPolicyAdvisor();
      validationEngine.setPolicyAdvisor(fetcher);
      refpol = ReferenceValidationPolicy.CHECK_TYPE_IF_EXISTS;
    }
    if (cliContext.getAdvisorFile() != null) {
      if (cliContext.getAdvisorFile().endsWith(".json")) {
        validationEngine.getPolicyAdvisor().setPolicyAdvisor(new JsonDrivenPolicyAdvisor(validationEngine.getPolicyAdvisor().getPolicyAdvisor(), ManagedFileAccess.file(cliContext.getAdvisorFile())));
      } else {
        validationEngine.getPolicyAdvisor().setPolicyAdvisor(new TextDrivenPolicyAdvisor(validationEngine.getPolicyAdvisor().getPolicyAdvisor(), ManagedFileAccess.file(cliContext.getAdvisorFile())));          
      }
    } else {
      validationEngine.getPolicyAdvisor().setPolicyAdvisor(new BasePolicyAdvisorForFullValidation(validationEngine.getPolicyAdvisor() == null ? refpol : validationEngine.getPolicyAdvisor().getReferencePolicy()));
    }
    validationEngine.getBundleValidationRules().addAll(cliContext.getBundleValidationRules());
    validationEngine.setJurisdiction(CodeSystemUtilities.readCoding(cliContext.getJurisdiction()));
    validationEngine.setUnknownCodeSystemsCauseErrors(cliContext.isUnknownCodeSystemsCauseErrors());
    validationEngine.setNoExperimentalContent(cliContext.isNoExperimentalContent());
    TerminologyCache.setNoCaching(cliContext.isNoInternalCaching());
    validationEngine.prepare(); // generate any missing snapshots
    System.out.println(" go (" + timeTracker.milestone() + ")");
    return validationEngine;
  }

  protected void loadIgsAndExtensions(ValidationEngine validationEngine, CliContext cliContext, TimeTracker timeTracker) throws IOException, URISyntaxException {
    FhirPublication ver = FhirPublication.fromCode(cliContext.getSv());
    IgLoader igLoader = new IgLoader(validationEngine.getPcm(), validationEngine.getContext(), validationEngine.getVersion(), validationEngine.isDebug());
    igLoader.loadIg(validationEngine.getIgs(), validationEngine.getBinaries(), "hl7.terminology", false);
    if (!VersionUtilities.isR5Ver(validationEngine.getContext().getVersion())) {
      igLoader.loadIg(validationEngine.getIgs(), validationEngine.getBinaries(), "hl7.fhir.uv.extensions", false);
    }
    System.out.print("  Terminology server " + cliContext.getTxServer());
    String txver = validationEngine.setTerminologyServer(cliContext.getTxServer(), cliContext.getTxLog(), ver, !cliContext.getNoEcosystem());
    System.out.println(" - Version " + txver + " (" + timeTracker.milestone() + ")");
    validationEngine.setDebug(cliContext.isDoDebug());
    validationEngine.getContext().setLogger(new SystemOutLoggingService(cliContext.isDoDebug()));
    for (String src : cliContext.getIgs()) {
      igLoader.loadIg(validationEngine.getIgs(), validationEngine.getBinaries(), src, cliContext.isRecursive());
    }
    System.out.println("  Package Summary: "+ validationEngine.getContext().loadedPackageSummary());
  }

  public String determineVersion(CliContext cliContext) throws IOException {
    if (cliContext.getMode() != EngineMode.VALIDATION && cliContext.getMode() != EngineMode.INSTALL) {
      return "5.0";
    }
    System.out.println("Scanning for versions (no -version parameter):");
    VersionSourceInformation versions = scanForVersions(cliContext);
    for (String s : versions.getReport()) {
      if (!s.equals("(nothing found)")) {
        System.out.println("  " + s);
      }
    }
    if (versions.isEmpty()) {
      System.out.println("  No Version Info found: Using Default version R5");
      return "5.0.0";
    }
    if (versions.size() == 1) {
      System.out.println("-> use version " + versions.version());
      return versions.version();
    }
    throw new IllegalArgumentException("-> Multiple versions found. Specify a particular version using the -version parameter");
  }

  public void generateSpreadsheet(CliContext cliContext, ValidationEngine validator) throws Exception {
    CanonicalResource cr = validator.loadCanonicalResource(cliContext.getSources().get(0), cliContext.getSv());
    boolean ok = true;
    if (cr instanceof StructureDefinition) {
      new StructureDefinitionSpreadsheetGenerator(validator.getContext(), false, false).renderStructureDefinition((StructureDefinition) cr, false).finish(ManagedFileAccess.outStream(cliContext.getOutput()));
    } else if (cr instanceof CodeSystem) {
      new CodeSystemSpreadsheetGenerator(validator.getContext()).renderCodeSystem((CodeSystem) cr).finish(ManagedFileAccess.outStream(cliContext.getOutput()));
    } else if (cr instanceof ValueSet) {
      new ValueSetSpreadsheetGenerator(validator.getContext()).renderValueSet((ValueSet) cr).finish(ManagedFileAccess.outStream(cliContext.getOutput()));
    } else if (cr instanceof ConceptMap) {
      new ConceptMapSpreadsheetGenerator(validator.getContext()).renderConceptMap((ConceptMap) cr).finish(ManagedFileAccess.outStream(cliContext.getOutput()));
    } else {
      ok = false;
      System.out.println(" ...Unable to generate spreadsheet for "+cliContext.getSources().get(0)+": no way to generate a spreadsheet for a "+cr.fhirType());
    }

    if (ok) {
      System.out.println(" ...generated spreadsheet successfully");
    }
  }

  public void transformLang(CliContext cliContext, ValidationEngine validator) throws IOException {
    switch (cliContext.getLangTransform()) {
    case "extract":
      transformLangExtract(cliContext, validator);
      break;
    case "inject":
      transformLangInject(cliContext, validator);
      break;
    default:
      System.out.println(" ...Unknown lang transform mode "+cliContext.getLangTransform());        
    }
  }

  private void transformLangExtract(CliContext cliContext, ValidationEngine validator) throws IOException { 
    String dst = cliContext.getOutput();
    FileUtilities.createDirectory(dst);
    PoGetTextProducer po = new PoGetTextProducer(dst, ".", false);
    XLIFFProducer xliff = new XLIFFProducer(dst, ".", false);
    JsonLangFileProducer jl = new JsonLangFileProducer(dst, ".", false);
    
    List<SourceFile> refs = new ArrayList<>();
    ValidatorUtils.parseSources(cliContext.getSources(), refs, validator.getContext());    
    for (SourceFile ref : refs) {
      System.out.println("  Extract Translations from " + ref);
      org.hl7.fhir.validation.Content cnt = validator.getIgLoader().loadContent(ref.getRef(), "translate", false, true);
      Element e = Manager.parseSingle(validator.getContext(), new ByteArrayInputStream(cnt.getFocus().getBytes()), cnt.getCntType());
      LanguageProducerSession ps = po.startSession(e.fhirType()+"-"+e.getIdBase(), cliContext.getSrcLang());
      LanguageProducerLanguageSession psl = ps.forLang(cliContext.getTgtLang());
      new LanguageUtils(validator.getContext()).generateTranslations(e, psl);
      psl.finish();
      ps.finish();
      ps = xliff.startSession(e.fhirType()+"-"+e.getIdBase(), cliContext.getSrcLang());
      psl = ps.forLang(cliContext.getTgtLang());
      new LanguageUtils(validator.getContext()).generateTranslations(e, psl);
      psl.finish();
      ps.finish(); 
      ps = jl.startSession(e.fhirType()+"-"+e.getIdBase(), cliContext.getSrcLang());
      psl = ps.forLang(cliContext.getTgtLang());
      new LanguageUtils(validator.getContext()).generateTranslations(e, psl);
      psl.finish();
      ps.finish(); 
    }
    System.out.println("Done - produced "+(po.fileCount()+xliff.fileCount()) + " files in "+dst);
  }
  
  private void transformLangInject(CliContext cliContext, ValidationEngine validator) throws IOException { 
    String dst = cliContext.getOutput();
    FileUtilities.createDirectory(dst);
    
    List<TranslationUnit> translations = new ArrayList<>();
    for (String input : cliContext.getInputs()) {
      loadTranslationSource(translations, input);
    }
    
    List<SourceFile> refs = new ArrayList<>();
    ValidatorUtils.parseSources(cliContext.getSources(), refs, validator.getContext()); 
    int t = 0;
    for (SourceFile ref : refs) {
      System.out.println("  Inject Translations into " + ref);
      org.hl7.fhir.validation.Content cnt = validator.getIgLoader().loadContent(ref.getRef(), "translate", false, true);
      Element e = Manager.parseSingle(validator.getContext(), new ByteArrayInputStream(cnt.getFocus().getBytes()), cnt.getCntType());      
      t = t + new LanguageUtils(validator.getContext()).importFromTranslations(e, translations);
      Manager.compose(validator.getContext(), e, ManagedFileAccess.outStream(Utilities.path(dst, ManagedFileAccess.file(ref.getRef()).getName())), cnt.getCntType(),
          OutputStyle.PRETTY, null);
    }
    System.out.println("Done - imported "+t+" translations into "+refs.size()+ " in "+dst);
  }
  
  private void loadTranslationSource(List<TranslationUnit> translations, String input) throws IOException {
    File f = ManagedFileAccess.file(input);
    if (f.exists()) {
      if (f.isDirectory()) {
        for (File fd : f.listFiles()) {
          loadTranslationSource(translations, fd.getAbsolutePath());
        }
      } else {
        if (f.getName().endsWith(".po")) {
          try {
            translations.addAll(new PoGetTextProducer().loadSource(ManagedFileAccess.inStream(f)));
          } catch (Exception e) {
            System.out.println("Error reading PO File "+f.getAbsolutePath()+": "+e.getMessage());
          }
        } else if (f.getName().endsWith(".xliff")) {
          try {
            translations.addAll(new XLIFFProducer().loadSource(ManagedFileAccess.inStream(f)));
          } catch (Exception e) {
            System.out.println("Error reading XLIFF File "+f.getAbsolutePath()+": "+e.getMessage());
          }          
        } else {
          try {
            translations.addAll(new PoGetTextProducer().loadSource(ManagedFileAccess.inStream(f)));
          } catch (Exception e) {
            try {
              translations.addAll(new XLIFFProducer().loadSource(ManagedFileAccess.inStream(f)));
            } catch (Exception e2) {
              System.out.println("Error reading File "+f.getAbsolutePath()+" as XLIFF: "+e2.getMessage());
              System.out.println("Error reading File "+f.getAbsolutePath()+" as PO: "+e.getMessage());
            }   
          }
        }
      }
    } else {
      System.out.println("Input not found: "+input);
    }    
  }

  private int cp;
  private int cs;
  public void install(CliContext cliContext, ValidationEngine validator) throws FHIRException, IOException {
    cp = 0;
    cs = 0;
    System.out.println("Generating Snapshots");
    for (String ig : cliContext.getIgs()) {
      processIG(validator, ig);
    }
    System.out.println("Installed/Processed "+cp+" packages, generated "+cs+" snapshots");
  }

  private void processIG(ValidationEngine validator, String ig) throws FHIRException, IOException {
    validator.loadPackage(ig, null);
    NpmPackage npm = validator.getPcm().loadPackage(ig);
    if (!npm.isCore()) {
      for (String d : npm.dependencies()) {
        processIG(validator, d);
      }
      System.out.println("Processing "+ig);
      cp++;
      for (String d : npm.listResources("StructureDefinition")) {
        String filename = npm.getFilePath(d);
        Resource res = validator.loadResource(FileUtilities.fileToBytes(filename), filename);
        if (!(res instanceof StructureDefinition))
          throw new FHIRException("Require a StructureDefinition for generating a snapshot");
        StructureDefinition sd = (StructureDefinition) res;
        if (!sd.hasSnapshot()) {
          StructureDefinition base = validator.getContext().fetchResource(StructureDefinition.class, sd.getBaseDefinition());
          cs++;
          new ProfileUtilities(validator.getContext(), new ArrayList<ValidationMessage>(), null).setAutoFixSliceNames(true).generateSnapshot(base, sd, sd.getUrl(), null, sd.getName());
          validator.handleOutput(sd, filename, validator.getVersion());
        }
      }
    }
  }

  public void codeGen(CliContext cliContext, ValidationEngine validationEngine) throws IOException {
    boolean ok = true;
    if (cliContext.getProfiles().isEmpty()) {
      System.out.println("Must specify at least one profile to generate code for with -profile or -profiles ");
      ok = false;
    }
    if (cliContext.getPackageName() == null) {
      System.out.println("Must provide a Java package name (-package-name)");
      ok = false;
    }
    if (cliContext.getSv() == null) {
      System.out.println("Must specify a version (-version)");
      ok = false;
    } else if (!VersionUtilities.isR4Ver(cliContext.getSv()) && !VersionUtilities.isR5Ver(cliContext.getSv())) {      
      System.out.println("Only versions 4 and 5 are supported (-version)");
      ok = false;
    }
    if (cliContext.getOutput() == null) {
      System.out.println("Must provide an output directory (-output)");
      ok = false;
    }
    FileUtilities.createDirectory(cliContext.getOutput());
    if (ok) {
      PECodeGenerator gen = new PECodeGenerator(validationEngine.getContext());
      gen.setFolder(cliContext.getOutput());
      gen.setExtensionPolicy(ExtensionPolicy.Complexes);
      gen.setNarrative(cliContext.getOptions().contains("narrative"));
      gen.setMeta(cliContext.getOptions().contains("meta"));
      gen.setLanguage(Locale.getDefault().toLanguageTag());
      gen.setContained(cliContext.getOptions().contains("contained"));
      gen.setKeyElementsOnly(!cliContext.getOptions().contains("all-elements"));
      gen.setGenDate(new SimpleDateFormat().format(new Date()));
      gen.setPkgName(cliContext.getPackageName());
      if (VersionUtilities.isR4Ver(cliContext.getSv())) {
        gen.setVersion("r4");
      } else {
        gen.setVersion("r5");
      }

      for (String profile : cliContext.getProfiles()) {
        if (profile.endsWith("*")) {
          for (StructureDefinition sd : validationEngine.getContext().fetchResourcesByType(StructureDefinition.class)) {
            if (sd.getUrl().startsWith(profile.replace("*", ""))) {
              gen.setCanonical(sd.getUrl());
              System.out.print("Generate for "+sd.getUrl());
              String s = gen.execute();
              System.out.println(": "+s);
            }
          }
        } else {
          gen.setCanonical(profile);
          System.out.print("Generate for "+profile);
          String s = gen.execute();
          System.out.println(": "+s);
        }
      }
      System.out.println("Done");
    }
  }

  public void instanceFactory(CliContext cliContext, ValidationEngine validationEngine) throws IOException {
    boolean ok = true;
    if (cliContext.getSource() == null) {
      System.out.println("Must specify a source (-version)");
      ok = false;
    } else if (!ManagedFileAccess.file(cliContext.getSource()).exists()) {      
      System.out.println("Factory source '"+cliContext.getSource()+"' not found");
      ok = false;
    }

    if (ok) {
      System.out.println("Preparing to execute");
              
      FHIRPathEngine fpe = new FHIRPathEngine(validationEngine.getContext());
      TestDataHostServices hs = new TestDataHostServices(validationEngine.getContext(), new DateTimeType(new Date()), new StringType(VersionUtilities.getSpecUrl(validationEngine.getContext().getVersion())));
      hs.registerFunction(new GlobalObjectRandomFunction());
      hs.registerFunction(new BaseTableWrapper.TableColumnFunction());
      hs.registerFunction(new BaseTableWrapper.TableDateColumnFunction());
      hs.registerFunction(new TestDataFactory.CellLookupFunction());
      hs.registerFunction(new TestDataFactory.TableLookupFunction());
      fpe.setHostServices(hs);
      LiquidEngine liquid = new LiquidEngine(validationEngine.getContext(), hs);
      
      String path = FileUtilities.getDirectoryForFile(cliContext.getSource());
      String log = Utilities.path(path, "log");
      FileUtilities.createDirectory(log);
                  
      JsonObject json = JsonParser.parseObjectFromFile(cliContext.getSource());
      for (JsonObject fact : json.forceArray("factories").asJsonObjects()) {
        TestDataFactory tdf = new TestDataFactory(validationEngine.getContext(), fact, liquid, fpe, "http://hl7.org/fhir/test", path, log, new HashMap<>(), new Locale("us"));
        tdf.setTesting(true); // no randomness
        System.out.println("Execute Test Data Factory '"+tdf.getName()+"'. Log in "+Utilities.path(log, tdf.statedLog()));
        tdf.execute();
      }

      System.out.println("Done");
    }
  }

}

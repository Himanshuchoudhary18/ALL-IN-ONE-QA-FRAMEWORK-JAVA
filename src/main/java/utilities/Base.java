package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static utilsWeb.CommonFunctionsWeb.takeScreenShotWeb;


public class Base {
    public static WebDriver driver;
    public static Properties property;
    public static ExtentReports extent;
    public static ThreadLocal<ExtentTest> classLevelReport = new ThreadLocal<ExtentTest>();
    public static ThreadLocal<ExtentTest> testLevelReport = new ThreadLocal<ExtentTest>();
    @Getter
    public static String className;
    public static Map<String, Map<String, Object>> SCENARIO_STATUS_MAP = new HashMap<>();
    public static String isRunningOnLambda;
    public static Logger logger = LoggerFactory.getLogger(Base.class);
    public static String platformName;
    private static String testName;
    @Getter
    private static String testRunId;
    protected String os = System.getProperty("os.name").toLowerCase();
    //    CommonFunctionsMobile common = new CommonFunctionsMobile();
    private String output;
    @Setter
    private boolean shouldKillDriverAfterTest = false;

    public Base() {
        OSValidator.setPropValues(os);
    }

    public static void setScenarioStatusMapToJSON(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(filePath), Base.SCENARIO_STATUS_MAP);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeSuite(alwaysRun = true)
    @Parameters({"env", "type", "runningOnLambda"})
    public void setUpResources(@Optional() String env, @Optional() String type, @Optional("false") String runningOnLambda) {
        property = TestUtilities.loadConfigProperties();
        if (property.getProperty("runningOnJenkins").equalsIgnoreCase("true")) {
            property.setProperty("environment", env);
            property.setProperty("type", type);
            property.setProperty("runningOnLambda", runningOnLambda);
            isRunningOnLambda = runningOnLambda;
            logger.info("Running on Jenkins with parameters type:- {} env:- {} countryCode:- {} runningOnLambda:- {}", type, env, runningOnLambda);
        } else {
            type = property.getProperty("type");
            env = property.getProperty("environment");
            isRunningOnLambda = property.getProperty("runningOnLambda");
            logger.info("Running on local machine with parameters type:- {} env:- {} runningOnLambda:- {}", type, env, isRunningOnLambda);
        }
        extent = ExtentManager.getExtent();
    }

    @BeforeClass(alwaysRun = true)
    @Parameters({"env", "type", "runningOnLambda"})
    public void startClass(@Optional() String env, @Optional() String type, @Optional("false") String runningOnLambda) {
//        property = TestUtilities.addConfigProperties(type, env);
        ExtentTest parent = extent.createTest(getClass().getSimpleName());// + getClass().getAnnotation(Test.class).description());
//        ExtentTest parent = extent.createTest(getClass().getAnnotation(Test.class).description());
        parent.assignCategory("Epic_Level_Report");
        classLevelReport.set(parent);
        classLevelReport.get().log(Status.INFO, "Execution Started for : " + getClass().getAnnotation(Test.class).description());

        //Creates a test Node at class level in the extent report
        className = this.getClass().getSimpleName();
    }

    @Parameters({"platformName"})
    @BeforeMethod(alwaysRun = true)
    public void startMethod(@Optional("platformName") String platformName, Method m, ITestResult result) throws Exception {
        logger.info("Started Execution of Test Case : " + m.getAnnotation(Test.class).description());
        testName = m.getName();
        testRunId = m.getName();
        //Creates a test Node at class level in the extent report
//        ExtentTest test = classLevelReport.get().createNode(m.getName());
        ExtentTest test = classLevelReport.get().createNode(m.getAnnotation(Test.class).description());
        test.assignCategory("Test_Level_Report");
        testLevelReport.set(test);
        testLevelReport.get().log(Status.INFO, "Execution Started for : " + m.getAnnotation(Test.class).description());
    }

    @Parameters({"platformName"})
    @AfterMethod(alwaysRun = true)
    public void killMethod(@Optional("platformName") String platformName, Method m, ITestResult result) throws Exception {
        logger.info("Ended Execution of Test Case : " + m.getAnnotation(Test.class).description());
        testLevelReport.get().log(Status.INFO, "Execution Ended for : " + m.getAnnotation(Test.class).description());
        if (!result.isSuccess()) {
            try {
                testLevelReport.get().addScreenCaptureFromPath(takeScreenShotWeb(result.getMethod().getMethodName()).getPath().substring(26));
            } catch (Exception e) {
                logger.error("Error in taking screenshot");
            }
        }
        extent.flush();
        if (shouldKillDriverAfterTest) {
            logger.warn("Killing Driver Instance");
            DriverManager.killDriverInstance();
            logger.info("Driver Instance Killed resetting value to false to kill after test");
        }
    }

    @AfterClass(alwaysRun = true)
    public void killClass() throws Exception {
        classLevelReport.get().log(Status.INFO, "Execution Started for : " + getClass().getAnnotation(Test.class).description());
        logger.info("Ended Execution of Test Class : " + getClass().getAnnotation(Test.class).description());
        DriverManager.killDriverInstance();
    }

    @AfterSuite(alwaysRun = true)
    public void killResources() {
        TestUtilities.archiveExtentReports();
    }
}
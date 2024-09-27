package com.nysoftusa.configaration;

import com.nysoftusa.reporting.ExtentManager;
import com.nysoftusa.reporting.ExtentTestManager;
import com.nysoftusa.utilities.ReadPropertiesFrom;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class WebTestBase {

    // Configuration code and re-usable code
    public static WebDriver driver = null;

    static Properties readProperties = ReadPropertiesFrom.loadProperties("src/main/resources/Configuration.properties");
    public static String BROWSER_NAME = readProperties.getProperty("BROWSER_NAME");
    public static String BROWSER_VERSION = readProperties.getProperty("BROWSER_VERSION");
    public static String ENVIRONMENT_NAME = readProperties.getProperty("ENVIRONMENT_NAME");
    public static String OS = readProperties.getProperty("OS");
    public static String OS_VERSION = readProperties.getProperty("OS_VERSION");

    public static String envURL = readProperties.getProperty("QA_ENV_URL");
    public static String BROWSERSTACK_USER_NAME = readProperties.getProperty("BROWSERSTACK_USER_NAME");
    public static String BROWSERSTACK_ACCESS_KEY = readProperties.getProperty("BROWSERSTACK_ACCESS_KEY");
    public static String SAUCE_LABS_USERNAME = readProperties.getProperty("SAUCE_LABS_USERNAME");
    public static String SAUCE_LABS_ACCESS_KEY = readProperties.getProperty("SAUCE_LABS_ACCESS_KEY");

    /**
     * **************************************************
     * ********* Start Of Extent Reporting Utilities ****
     * **************************************************
     * **************************************************
     */

    public static ExtentReports extentReports;
    public static ExtentTest extentTest;


    @BeforeSuite
    public void extentSetup(ITestContext context) {
        ExtentManager.setOutputDirectory(context);
        extentReports = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void startExtent(Method method) {
        String className = method.getDeclaringClass().getSimpleName();
        extentTest = ExtentTestManager.startTest(method.getName());
        extentTest = ExtentTestManager.getTest().assignCategory(className);
    }

    protected String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

    @AfterMethod
    public void afterEachTestMethod(ITestResult result) throws Exception {
        ExtentTestManager.getTest().getTest().setStartedTime(getTime(result.getStartMillis()));
        ExtentTestManager.getTest().getTest().setEndedTime(getTime(result.getEndMillis()));

        for (String group : result.getMethod().getGroups()) {
            ExtentTestManager.getTest().assignCategory(group);
        }
        if (result.getStatus() == ITestResult.SUCCESS) {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");
            // extentTest.log(LogStatus.PASS, "Test passed");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            //logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
            //   extentTest.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
            ExtentTestManager.getTest().log(LogStatus.FAIL, getStackTrace(result.getThrowable()));
            //  extentTest.log(LogStatus.FAIL, result.getThrowable());
            //We do pass the path captured by this method in to the extent reports using "logger.addScreenCapture" method.
            String screenshotPath = captureScreenShotWithPath(driver, result.getName());

            //To add it in the extent report
            //    ExtentTestManager.getTest().log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));
            //     ExtentTestManager.getTest().log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath));
            extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath));
        } else if (result.getStatus() == ITestResult.SKIP) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
            //  extentTest.log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
        }
        // ExtentTestManager.endTest();

        // driver.close();
        //driver.quit();
        // ending test
        //endTest(logger) : It ends the current test and prepares to create HTML report
        extentReports.endTest(extentTest);
        extentReports.flush();
    }

    @AfterSuite
    public void generateReport() {
        extentReports.close();
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }


    public static String convertToString(String st) {
        String splitString = "";
        splitString = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(st), ' ');
        return splitString;
    }

    public static void getLog(String message) {
        Reporter.log(message, true);
    }


    public static String captureScreenShotWithPath(WebDriver driver, String screenShotName) {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File file = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String fileName = System.getProperty("user.dir") + "/ScreenShots/" + screenShotName + "_" + dateName + ".png";
        try {
            FileUtils.copyFile(file, new File(fileName));
            getLog("======= ScreenShot Captured =======");
        } catch (IOException e) {
            getLog("Exception while taking ScreenShot " + e.getMessage());
        }
        return fileName;
    }


     @Parameters({"useCloudEnv", "envName", "os", "osVersion", "browserName", "browserVersion"})
    @BeforeMethod

     public static void setUpBrowser(boolean useCloudEnv,String envName, String os, String osVersion, String browserName, String browserVersion){
        //public static void setUpBrowser(){

        // Cloud Infrastructure: BrowserStack or SauceLab
         // boolean useCloudEnv =false;
        if (useCloudEnv==true) {
          getCloudDriver( envName, os, osVersion, browserName, browserVersion);
           //  getCloudDriver("browserStack", "windows", "11", "chrome","120");
        //  getCloudDriver(ENVIRONMENT_NAME, OS, OS_VERSION, BROWSER_NAME, BROWSER_VERSION);
        } else {
            setBrowser(WebTestBase.BROWSER_NAME);
        }
        driverSetUp(envURL);
    }


    public static void setBrowser(String browserName) {
        // Modern latest Approach
        if (Objects.equals(browserName, "chrome")) {
            driver = new ChromeDriver();
        } else if (Objects.equals(browserName, "firefox")) {
            driver = new FirefoxDriver();
        } else if (Objects.equals(browserName, "safari")) {
            driver = new SafariDriver();
        } else if (Objects.equals(browserName, "internetExplorer")) {
            driver = new InternetExplorerDriver();
        } else if (Objects.equals(browserName, "edge")) {
            driver = new EdgeDriver();
        }
    }

    public static void driverSetUp(String envURL) {
        driver.get(envURL);
        driver.manage().window().fullscreen();
        // driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); // indirect wait time :
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
    }

    public static void getCloudDriver(String envName, String os, String osVersion, String browserName, String browserVersion) {
        if (envName.equalsIgnoreCase("browserStack")) {
            try {
                MutableCapabilities capabilities = new MutableCapabilities();
                capabilities.setCapability("browserName",browserName);
                HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
                browserstackOptions.put("os",os);
                browserstackOptions.put("osVersion",osVersion );
               browserstackOptions.put("browserVersion", browserVersion);
                browserstackOptions.put("local", "false");
                browserstackOptions.put("seleniumVersion", "4.15.0");
                capabilities.setCapability("bstack:options", browserstackOptions);
                driver = new RemoteWebDriver(new URL("https://" + BROWSERSTACK_USER_NAME + ":" + BROWSERSTACK_ACCESS_KEY + "@hub.browserstack.com/wd/hub"), capabilities);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        } else if (envName.equalsIgnoreCase("sauceLabs")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("browserName", browserName);
            capabilities.setCapability("browserVersion", browserVersion);
            HashMap<String, Object> options = new HashMap<>();
            options.put("os", os);
            options.put("osVersion", osVersion);
            options.put("seleniumVersion", "4.15.0");
            ChromeOptions browserOptions = new ChromeOptions();
            browserOptions.setPlatformName(os);
            browserOptions.setBrowserVersion(browserVersion);
            options.put("build", "selenium-build-KLW8W");
            browserOptions.setCapability("name", "<your test name>");
            browserOptions.setCapability("sauce:options", options);
            capabilities.setCapability("osVersion", osVersion);
            URL url = null;
            try {
                url = new URL("https://" + SAUCE_LABS_USERNAME + ":" + SAUCE_LABS_ACCESS_KEY + "@ondemand.us-west-1.saucelabs.com:443/wd/hub");
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            driver = new RemoteWebDriver(url, browserOptions);
        }
    }

        @AfterMethod
        public static void closeBrowser () {
// to close Browser
//driver.close();
            driver.quit();
        }
        @BeforeTest
        public static void startAutomation () {
            System.out.println("================Test case Execution Start===================");
        }
        @AfterTest
        public static void endAutomation () {
            System.out.println("================Test case Execution end===================");
        }

        @BeforeMethod
        public static void startExecutingAutomation () {
            System.out.println("================Test case Execution start Using method===================");
        }
        @AfterTest
        public static void endExecutingAutomation () {
            System.out.println("================Test case Execution end Using method===================");
        }


        public static void waitFor ( int seconds){


            try {
                Thread.sleep(1000L * seconds);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }

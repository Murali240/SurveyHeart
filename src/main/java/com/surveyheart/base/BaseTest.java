package com.surveyheart.base;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.surveyheart.listeners.TestListener;
import com.surveyheart.utilities.ConfigReader;
import com.surveyheart.utilities.ExtentReportManager;


/** BaseTest class for centralized test setup, configuration, and teardown across the framework */
@Listeners(TestListener.class)                   /** Linking TestNG listener to BaseTest */
public class BaseTest {

	protected WebDriver driver;                        // WebDriver instance
    protected WebDriverWait wait;                      // WebDriverWait for explicit waits
    protected Logger log;                              // Log4j logger
    protected ExtentReports extent;                    // ExtentReports object
    protected ExtentTest test;                         // Current test report instance


    /** Executes preconditions and setup steps before each @Test method */
    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method) throws IOException {

     // Initialize logger for the current test class
        log = LogManager.getLogger(this.getClass());

     // Read browser name from config file
        String browser = ConfigReader.getProperty("browser");
        

     // Launch browser based on configuration
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                break;

            case "edge":
                driver = new EdgeDriver();
                driver.manage().window().maximize();
                break;

            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        log.info("Browser launched: " + browser);

     // Setup waits
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

     // Navigate to base URL
        driver.get(ConfigReader.getProperty("baseUrl"));
        log.info("Navigated to: " + driver.getCurrentUrl());
        
        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports/TestReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);

     // 👇 Create test with method name
        test = extent.createTest(method.getName());           
    }
      
    
    /** Executes postconditions and cleanup after each @Test method */
    @AfterMethod(alwaysRun = true)
    public void tearDown() throws IOException {       
        
     // Quit browser if WebDriver instance is active
        if (driver != null) {
            driver.quit();
        }

     // Flush all test information to Extent Report
        ExtentReportManager.getReporter().flush();

     // Open the generated Extent Report automatically in Chrome browser
        File reportFile = new File("ExtentReports/TestReport.html");
        if (reportFile.exists()) {
            String chromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
            new ProcessBuilder(chromePath, reportFile.getAbsolutePath()).start();
        }
    }
    
    
    /** Provides WebDriver instance for access in TestListener class (e.g., OnTestFailure for screenshots) */
    public WebDriver getDriver() {
        return driver;
    }
  
    
    
    
    
    
}

    
    
    
    
    
   







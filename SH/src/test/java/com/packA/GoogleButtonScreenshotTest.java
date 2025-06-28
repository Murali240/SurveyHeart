package com.packA;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class GoogleButtonScreenshotTest {
	

	  @Test
	    public void googleButtonLoginMethod() throws IOException {

	        // Extent - Initialize Reporter
	        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports/FormReport.html");
	        ExtentReports extent = new ExtentReports();
	        extent.attachReporter(spark);
	        ExtentTest test = extent.createTest("SurveyHeart - Sign in with Google button Test");

	        WebDriver driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.manage().deleteAllCookies();
	        driver.get("https://surveyheart.com/app#login");
	        test.info("Navigated to SurveyHeart Login page");

	        // System Info
	        String os = System.getProperty("os.name").toUpperCase();
	        String user = System.getProperty("user.name");
	        Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
	        String browserName = caps.getBrowserName();
	        String browserVersion = caps.getBrowserVersion();

	        extent.setSystemInfo("Executed By", user);
	        extent.setSystemInfo("Operating System", os);
	        extent.setSystemInfo("Browser", browserName);
	        extent.setSystemInfo("Browser Version", browserVersion);

	        try {
	            driver.findElement(By.xpath("//span[text()='Sign in with Google']")).click();
	            test.info("Clicked on 'Sign in with Google'");

	            String parentWindow = driver.getWindowHandle();
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

	            // Wait for new popup window
	            wait.until(driver1 -> driver.getWindowHandles().size() > 1);

	            // Switch to popup window
	            for (String window : driver.getWindowHandles()) {
	                if (!window.equals(parentWindow)) {
	                    driver.switchTo().window(window);
	                    break;
	                }
	            }

	            // Enter email
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));
	            driver.findElement(By.xpath("//input[@type='email']")).sendKeys("vilem14826@hosliy.com");
	            driver.findElement(By.xpath("//span[text()='Next']")).click();
	            test.pass("Entered email and clicked Next");

	            try {
	                // Enter password
	                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']")));
	                driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Automation@1");
	                driver.findElement(By.xpath("//span[text()='Next']")).click();
	                test.pass("Entered password and clicked Next");

	            } catch (Exception e) {
	                String screenshotPath = captureScreenshot(driver, "GoogleLoginPasswordError");
	                test.fail("Failed during password input").addScreenCaptureFromPath(screenshotPath);
	                throw new RuntimeException("Google button login failed at password stage.", e);
	            }

	            // Switch back to parent window
	            driver.switchTo().window(parentWindow);

	            // Wait and close dashboard popup
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Close']"))).click();
	            test.pass("Dashboard popup closed successfully");

	            // Verify successful login
	            String currentURL = driver.getCurrentUrl();
	            extent.setSystemInfo("Current URL", currentURL);
	            Assert.assertEquals(currentURL, "https://surveyheart.com/app#dashboard");
	            test.pass("Dashboard URL verified: " + currentURL);

	        } catch (Exception e) {
	            String screenshotPath = captureScreenshot(driver, "GoogleButtonLoginFailure");
	            test.fail("Test failed during Google login process").addScreenCaptureFromPath(screenshotPath);
	            throw new RuntimeException("Google button login failed", e);
	        } finally {
	            driver.quit();
	            test.info("Browser closed");
	            extent.flush();

	            // Open the Extent report in Chrome browser automatically
	            File reportFile = new File("ExtentReports/FormReport.html");
	            String chromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
	            Runtime.getRuntime().exec(new String[]{chromePath, reportFile.getAbsolutePath()});
	        }
	    }

	 /*   // Screenshot method
	    public static String captureScreenshot(WebDriver driver, String fileName) {
	        try {
	            TakesScreenshot ts = (TakesScreenshot) driver;
	            File src = ts.getScreenshotAs(OutputType.FILE);
	            String screenshotDir = "ExtentReports/screenshots/";
	            File dest = new File(screenshotDir + fileName + ".png");
	            dest.getParentFile().mkdirs();
	            Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
	            return dest.getAbsolutePath();
	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }
	        
	        
	                
	   
	}*/
	    
	 // Screenshot method with dynamic name (timestamp-based)
	    public static String captureScreenshot(WebDriver driver, String baseName) {
	        try {
	            TakesScreenshot ts = (TakesScreenshot) driver;
	            File src = ts.getScreenshotAs(OutputType.FILE);

	            // Generate timestamp (e.g., 20240617_194512)
	            String timestamp = java.time.LocalDateTime.now()
	                    .format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

	            // Construct file path with timestamp
	            String screenshotDir = "ExtentReports/screenshots/";
	            String fullFileName = baseName + "_" + timestamp + ".png";
	            File dest = new File(screenshotDir + fullFileName);
	            dest.getParentFile().mkdirs();

	            // Save file
	            Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
	            return dest.getAbsolutePath();

	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

	    	    
}
	
	
	
	
	
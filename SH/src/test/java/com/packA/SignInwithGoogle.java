package com.packA;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class SignInwithGoogle {

    @Test
    public void formMethod() throws IOException {
    	
     // Extent - Initialize Reporter
        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports/FormReport.html");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);
        ExtentTest test = extent.createTest("SurveyHeart - Sign In with Google button Test");
		
        
		 WebDriver driver = new ChromeDriver();
	     driver.get("https://surveyheart.com/app#login");
	     driver.manage().window().maximize();
	     driver.manage().deleteAllCookies();
	     
	  // Get environment details
	     String os = System.getProperty("os.name").toUpperCase();
	     String user = System.getProperty("user.name");
	     Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
	     String browserName = caps.getBrowserName();
	     String browserVersion = caps.getBrowserVersion();
			

	  // Add environment info to Extent Report
	     extent.setSystemInfo("Executed By", user);
	     extent.setSystemInfo("Operating System", os);
	     extent.setSystemInfo("Browser", browserName);
	     extent.setSystemInfo("Browser Version", browserVersion);
	     
	  // Click "Sign in with Google"
	     driver.findElement(By.xpath("//span[text()='Sign in with Google']")).click();
	     test.info("Clicked on Sign in with Google button");
	     
	  // Current window (parent)
	     String parentWindow = driver.getWindowHandle();

	  // Wait for new window to appear (Google login)
	     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	     wait.until(new ExpectedCondition<Boolean>() {
	     public Boolean apply(WebDriver driver) {
	            return driver.getWindowHandles().size() > 1;     // If 2 different windows/popup for email entering for Google button SigIn
	            }												 // Switch to opening new window and enter email and password
	       });

	   // Switch to the new Google login window(child)
	      Set<String> windows = driver.getWindowHandles();
	      for (String window : windows) {
	          if (!window.equals(parentWindow)) {
	              driver.switchTo().window(window);
	              break;
	            }
	        }

	   // Enter email
	      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));
	      WebElement email = driver.findElement(By.xpath("//input[@type='email']"));
	      email.sendKeys("vilem14826@hosliy.com");
	      driver.findElement(By.xpath("//span[text()='Next']")).click();
	      test.pass("Email entered successfully");

	   // Enter password
	      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']")));
	      WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
	      password.sendKeys("Automation@1");
	      driver.findElement(By.xpath("//span[text()='Next']")).click();
	      test.pass("Password entered successfully");

	      
	   // Switch back to the parent window (main app)
	      driver.switchTo().window(parentWindow);

	   // Dashboard - Wait for the Feature Spotlight popup and close it
	      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Close']")));
	      WebElement closeBtn = driver.findElement(By.xpath("//img[@alt='Close']"));
	      closeBtn.click();
	      test.pass("Feature Spotlight Popup is closed successfully");
	      String currentURL = driver.getCurrentUrl();
	      test.info("Current URL after login successfully: " + currentURL);
		  extent.setSystemInfo("Current URL", currentURL);
		     
	      String actualURL = currentURL;
		  String expectedURL="https://surveyheart.com/app#dashboard";
		     
		// Assert that actualURL and expectedURL 
		   Assert.assertEquals(actualURL, expectedURL, "Response text does not match expected value");
		   test.pass("Current URL is matched with expected URL and Dashboard is displayed successfully");

		   
		// Close the browser
		   driver.quit();
		   test.pass("Browser closed");
		     
		// Extent - Write Report and Flush
		   extent.flush();
      	        
		// Extent - Open report automatically in Chrome browser only
		   File reportFile = new File("ExtentReports/FormReport.html");
		   String reportPath = reportFile.getAbsolutePath();

		// Change path below if Chrome is installed in a different location
		   String chromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";

		// Launch Chrome with the report file
		   Runtime.getRuntime().exec(new String[] { chromePath, reportPath });
		     

    }
}

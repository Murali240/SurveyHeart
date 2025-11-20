package com.packA;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class FeedbackFormSubmission {
	
	
	@Test
	public void create_Form_Method() throws IOException, TimeoutException {
		
	 // Extent - Initialize Reporter
        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports/FormReport.html");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);
        ExtentTest test = extent.createTest("SurveyHeart - Feedback Form Submission Test");

		
		 WebDriver driver = new ChromeDriver();
		 test.info("Browser launched");
		 
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

		 
	     driver.get("https://stage.form.heartfullapps.com/app?u=0#login");
	     driver.manage().window().maximize();
	     driver.manage().deleteAllCookies();
	     
	  // Click "Sign in using Email
	     driver.findElement(By.xpath("//span[normalize-space()='Sign in using Email']")).click();
	     driver.findElement(By.xpath("//label[@id='label_field_Email']")).sendKeys("vilem14826@hosliy.com");
	     driver.findElement(By.xpath("//div[@id='Next']")).click();
	     test.info("Navigated to SurveyHeart login page");
	     
	  // Enter Password
	     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	     WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='label_field_Password']")));
	     password.sendKeys("Automation@1");
	     driver.findElement(By.xpath("//div[@id='Sign in']")).click();
	     test.pass("Signed in successfully");
	     
	  // Dashboard
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Close']"))).click();
	     test.pass("Closed dashboard popup");
	     String currentURL = driver.getCurrentUrl();
	     test.info("Current URL after login successfully: " + currentURL);
	     extent.setSystemInfo("Current URL", currentURL);
	     
	  // More page
	     driver.findElement(By.xpath("//span[normalize-space()='More']")).click();
	     driver.findElement(By.xpath("//span[normalize-space()='Feedback']")).click();
	     
	  // Parent window
	     String parentWindow = driver.getWindowHandle();
	     
	  // Switch to new window
	     wait.until(ExpectedConditions.numberOfWindowsToBe(2));
	     Set<String> allWindows = driver.getWindowHandles();
	     for (String win : allWindows) {
	            if (!win.equals(parentWindow)) {
	                driver.switchTo().window(win);
	                break;
	            }
	        }

	  // Feedback Form page
	     WebElement feedbackTextFormField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@field-type='LONG_TEXT']")));
	     feedbackTextFormField.sendKeys("Google");
	     WebElement emailFiled = driver.findElement(By.xpath("//input[@id='input-undefined']"));
	     emailFiled.sendKeys("google@gmail.com");
	     driver.findElement(By.xpath("//div[@id='Submit']")).click();
	     
	  // Submitted page
	     WebElement submittedMessage = wait.until(
	    		    ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@id='submitted_success_text']"))
	    		);
	     String submittedPage = submittedMessage.getText();
	     test.pass("Feedback Form is displayed with : " + submittedPage + " page");

	    
	     
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

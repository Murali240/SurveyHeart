package com.packA;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
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

public class UserCurrentPlan {
	
	
	@Test
	public void create_Form_Method() throws IOException, TimeoutException {
		
	 // Extent - Initialize Reporter
        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports/FormReport.html");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);
        ExtentTest test = extent.createTest("SurveyHeart - Form Creation Test");

		
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

		 
	     driver.get("https://surveyheart.com/app#login");
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
	     driver.navigate().refresh();
	     
	  // User Account icon
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='profile-initial']")));
	     driver.findElement(By.xpath("//span[@class='profile-initial']")).click();
	     String userCurrentPlan = driver.findElement(By.xpath("//span[normalize-space()='FREE']")).getText();       //Plan type
	     test.info("User current plan is : "+userCurrentPlan);

	     String currentPlanStorage =  wait.until(ExpectedConditions.visibilityOfElementLocated(
	         By.xpath("//span[contains(text(),'of 1GB Used')]"))).getText();
	     test.info(userCurrentPlan + " User Storage limit is : " + currentPlanStorage);

	     String currentPlanResponse = wait.until(ExpectedConditions.visibilityOfElementLocated(
	         By.xpath("//span[contains(text(),'of 10000 Used')]"))).getText();
	     test.info(userCurrentPlan + " User Total Responses limit is : " + currentPlanResponse);
	       
	  // Images
	     try {
	    	    String currentPlanImages = wait.until(ExpectedConditions.visibilityOfElementLocated(
	    	        By.xpath("(//span[contains(text(),'of') and contains(text(),'Used') and contains(text(),'50')])[1]")
	    	    )).getText();
	    	    test.info(userCurrentPlan + " User Images limit is : " + currentPlanImages);
	    	} catch (Exception e) {
	    	    test.warning("User Images usage info not visible or not present for this plan.");
	    	}  
	     
	  // Themes
	     try {
	    	    String currentPlanThemes = wait.until(ExpectedConditions.visibilityOfElementLocated(
	    	        By.xpath("//div[contains(text(),'Themes') or contains(.,'Themes')]/span[contains(text(),'of 50 Used')]")
	    	    )).getText();
	    	    test.info(userCurrentPlan + " User Themes limit is : " + currentPlanThemes);
	    	} catch (Exception e) {
	    	    test.warning("User Themes usage info not visible or not present for this plan.");
	    	}

	     
	     
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

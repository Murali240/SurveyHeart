package com.packA;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.jspecify.annotations.Nullable;
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

public class AccountSignoutFunctionality {
	
	@Test
	public void create_Form_Method() throws IOException {
		
	 // Extent - Initialize Reporter
        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports/FormReport.html");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);
        ExtentTest test = extent.createTest("SurveyHeart - Account signout Test");

		
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
	     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
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
	     
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='profile-initial']")));
	     driver.findElement(By.xpath("//span[@class='profile-initial']")).click();
	     
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='Sign Out']")));
	     driver.findElement(By.xpath("//div[@id='Sign Out']")).click();
	     test.info("Before account signout url  :"+currentURL );
	     test.info("After account signout url  :"+driver.getCurrentUrl());
	     test.pass("Account signout successfully");
		 
	     
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

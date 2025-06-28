package com.packA;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

public class FormFreeUserCreatingwithPremiumQuestions {
	
	
	@Test
	public void create_Form_Method() throws IOException {
		
	 // Extent - Initialize Reporter
        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports/FormReport.html");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);
        ExtentTest test = extent.createTest("SurveyHeart – Free User Adding Premium Questions Test");

		
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
	
	  // Generate dynamic form title using timestamp
	     String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
	     String dynamicFormTitle = "Selenium Form - " + timeStamp;
	     
	  // User Account icon
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='profile-initial']")));
	     driver.findElement(By.xpath("//span[@class='profile-initial']")).click();
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='FREE']")));
	     String userCurrentPlan = driver.findElement(By.xpath("//span[normalize-space()='FREE']")).getText();       //Plan type
	     test.info("User current plan is : "+userCurrentPlan);
	     driver.navigate().refresh();

	  // Form Creating
	     driver.findElement(By.xpath("//span[text()='Create Form']")).click();
	     driver.findElement(By.xpath("//input[@maxlength='250']")).sendKeys(dynamicFormTitle);
	     driver.findElement(By.xpath("//span[text()='Add Question']")).click();
	     driver.findElement(By.xpath("//div[contains(text(),'Slider')]")).click();          // Clicking on Slider Question
	     WebElement premiumWarning1 = driver.findElement(By.xpath("//div[contains(text(),'PREMIUM_FEATURES')]"));
	     test.pass("For "+userCurrentPlan +" user Slider Question is displayed with : "+premiumWarning1.getText()+ " Warning popup");
	     //test.pass("For "+userCurrentPlan +" user Slider Question Warning popup is displayed with : " +premiumWarning1.getText());
	     driver.findElement(By.xpath("//label[normalize-space()='CANCEL']")).click();
	     
	     driver.findElement(By.xpath("//span[normalize-space()='Add Question']")).click();
	     driver.findElement(By.xpath("//div[@class='builder-box1']//div[8]")).click();      // Clicking on Picture Choice Question
	     WebElement premiumWarning2 = driver.findElement(By.xpath("//div[contains(text(),'PREMIUM_FEATURES')]"));
	     test.pass("For "+userCurrentPlan +" user Picture Choice Question is displayed with : "+premiumWarning2.getText()+ " Warning popup");
	     //test.pass("For "+userCurrentPlan +" user Picture Choice Question Warning popup is displayed with : " +premiumWarning2.getText());
	     driver.findElement(By.xpath("//label[normalize-space()='CANCEL']")).click();
	     
	     driver.findElement(By.xpath("//span[normalize-space()='Add Question']")).click();
	     driver.findElement(By.xpath("//div[@class='builder-box1']//div[9]")).click();       // Clicking on Ranking Question
	     WebElement premiumWarning3 = driver.findElement(By.xpath("//div[contains(text(),'PREMIUM_FEATURES')]"));
	     test.pass("For "+userCurrentPlan +" user Ranking Question is displayed with : "+premiumWarning3.getText()+ " Warning popup");
	     //test.pass("For "+userCurrentPlan +" user Ranking Question Warning popup is displayed with : " +premiumWarning3.getText());
	     driver.findElement(By.xpath("//label[normalize-space()='CANCEL']")).click();
	     
	     driver.findElement(By.xpath("//span[normalize-space()='Add Question']")).click();
	     driver.findElement(By.xpath("//div[@class='builder-box1']//div[10]")).click();       // Clicking on Agreement Question
	     WebElement premiumWarning4 = driver.findElement(By.xpath("//div[contains(text(),'PREMIUM_FEATURES')]"));
	     test.pass("For "+userCurrentPlan +" user Agreement Question is displayed with : "+premiumWarning4.getText()+ " Warning popup");
	     //test.pass("For "+userCurrentPlan +" user Agreement Question Warning popup is displayed with : " +premiumWarning4.getText());
	     driver.findElement(By.xpath("//label[normalize-space()='CANCEL']")).click();
	     
	     driver.findElement(By.xpath("//span[normalize-space()='Add Question']")).click();
	     driver.findElement(By.xpath("//div[@class='builder-box1']//div[11]")).click();        // Clicking on Signature Question
	     WebElement premiumWarning5 = driver.findElement(By.xpath("//div[contains(text(),'PREMIUM_FEATURES')]"));
	     test.pass("For "+userCurrentPlan +" user Signature Question is displayed with : "+premiumWarning5.getText()+ " Warning popup");
	     //test.pass("For "+userCurrentPlan +" user Signature Question Warning popup is displayed with : " +premiumWarning5.getText());
	 
	  // View Plans button
	     driver.findElement(By.xpath("//label[contains(text(),'VIEW PLANS')]")).click();
	     WebElement premiumPlansPage = driver.findElement(By.xpath("//span[contains(text(),'Premium plans')]"));
	     test.pass("View Plans button is navigated to : "  +premiumPlansPage.getText() +" page");
	     driver.findElement(By.xpath("//img[@class='localization-close-img']")).click();
	     test.pass("Premium plans page is closed successfully");
	     
	     
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

package com.Forms;

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
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class FormChoiceTypeQuestionsTest {
	
	
	@Test
	public void testFormChoiceTypeQuestions() throws IOException {
		
	 // Extent - Initialize Reporter
        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports/FormReport.html");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);
        ExtentTest test = extent.createTest("SurveyHeart – Form Creation with Choice Questions Test");

		
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
	     String currentURL = driver.getCurrentUrl();
	     test.info("Current URL after login successfully : " + currentURL);
	     extent.setSystemInfo("Current URL", currentURL);
	
	  // Generate dynamic form title using timestamp
	     String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
	     String dynamicFormTitle = "Selenium Form - " + timeStamp;


	  // Form Creating
	     driver.findElement(By.xpath("//span[text()='Create Form']")).click();
	     driver.findElement(By.xpath("//input[@maxlength='250']")).sendKeys(dynamicFormTitle);
	     driver.findElement(By.xpath("//span[text()='Add Question']")).click();
	     
	  // adding MCQ Question 
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='question-group-container-1']//div[@class='question-type-grid-container']//div[1]"))).click();   // + Add button
	     driver.findElement(By.xpath("//input[@id='question-text-input-0']")).sendKeys("MCQ Question");
	     driver.findElement(By.xpath("//input[@id='choice-question-text-input-0-0']")).sendKeys("Alpha");
	     driver.findElement(By.xpath("//img[@alt='Add Option']")).click();
	     driver.findElement(By.xpath("//input[@id='choice-question-text-input-0-1']")).sendKeys("Beta");
	     driver.findElement(By.xpath("(//img[@alt='Add Option'])[2]")).click();
	     driver.findElement(By.xpath("//input[@id='choice-question-text-input-0-2']")).sendKeys("Gamma");
	     test.pass("MCQ question is added");
		  
	  // adding Checkbox Question 
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='images/add_blue.svg']"))).click();   // + Add button
	     driver.findElement(By.xpath("//div[@id='question-group-container-1']//div[3]")).click();
	     driver.findElement(By.xpath("//input[@id='question-text-input-1']")).sendKeys("Checkbox Question");
	     driver.findElement(By.xpath("//input[@id='choice-question-text-input-1-0']")).sendKeys("xolo");
	     driver.findElement(By.xpath("//div[@id='star-body-container1']//img[@alt='Add Option']")).click();
	     driver.findElement(By.xpath("//input[@id='choice-question-text-input-1-1']")).sendKeys("yolo");
	     driver.findElement(By.xpath("//div[@id='question_card_1']//div[2]//div[1]//div[1]//img[3]")).click();
	     driver.findElement(By.xpath("//input[@id='choice-question-text-input-1-2']")).sendKeys("zolo");
	     test.pass("Checkbox question is added");
	     
	  // adding Dropdown Question 
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='question_card_1']//img[@alt='Add Question']"))).click();   // + Add button
	     driver.findElement(By.xpath("//div[contains(text(),'Dropdown')]")).click();
	     driver.findElement(By.xpath("//input[@id='question-text-input-2']")).sendKeys("Dropdown Question");
	     driver.findElement(By.xpath("//input[@id='choice-question-text-input-2-0']")).sendKeys("India");
	     driver.findElement(By.xpath("//div[@id='star-body-container2']//img[@alt='Add Option']")).click();
	     driver.findElement(By.xpath("//input[@id='choice-question-text-input-2-1']")).sendKeys("Australia");
	     driver.findElement(By.xpath("//div[@id='question_card_2']//div[2]//div[1]//div[1]//img[2]")).click();
	     driver.findElement(By.xpath("//input[@id='choice-question-text-input-2-2']")).sendKeys("England");
	     test.pass("Dropdown question is added");
	     
	  // Setting screen
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Settings']")));
	     driver.findElement(By.xpath("//span[text()='Settings']")).click();
	     driver.findElement(By.xpath("//span[@class='icon-title'][normalize-space()='Submit']")).click();
	     test.pass("Form created with : " + dynamicFormTitle);
	     
	  // Wait for Share popup to appear and close it
	     WebElement closeSharePopup = wait.until(ExpectedConditions.elementToBeClickable(
	         By.xpath("//div[contains(@class,'popup')]//img[@alt='close']")));
	     closeSharePopup.click();
	     
	  // Identifying the created Form card in Form Dashboard
	     WebElement formCard=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='list-card-title-0']//p[@id='card-form-title']")));
	     String actualFormTitle = formCard.getText();
	     String expectedFormTitle = dynamicFormTitle;
	     
	     
	  // Assert that actualTitle and expectedTitle 
	     Assert.assertEquals(actualFormTitle, expectedFormTitle, "Response text does not match expected value");
	     test.pass("Actual Form title is matched with expected Form title : "+dynamicFormTitle);
	     
	     
	  // Close the browser
	     driver.quit();
	     test.info("Browser closed");
	     
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

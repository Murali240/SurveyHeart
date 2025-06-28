package com.packA;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
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

public class QuizChoiceTypeQuestions {
	
	@Test
	public void quiz_Creation_Method() throws IOException  {
		
	 // Extent - Initialize Reporter
        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports/FormReport.html");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);
        ExtentTest test = extent.createTest("SurveyHeart Quiz Creation with Choice Questions Test");

		
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
	     
	  // Click "Sign in using Email"
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
	     
	     
	  // Generate dynamic quiz title using timestamp
	     String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
	     String dynamicQuizTitle = "Selenium Quiz - " + timeStamp;
	     
	     
	  // Creating Quiz
	     driver.findElement(By.xpath("//span[text()='Quizzes']")).click();
	     driver.findElement(By.xpath("//span[text()='Create Quiz']")).click();
	     driver.findElement(By.xpath("//input[@maxlength='250']")).sendKeys(dynamicQuizTitle);
	     driver.findElement(By.xpath("//span[text()='Add Question']")).click();

	  // adding MCQ Question
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='question-group-container-1']//div[@class='question-type-grid-container']//div[1]"))).click();
	     driver.findElement(By.xpath("//input[@id='question-text-input-0']")).sendKeys("Quiz MCQ Question");
	     driver.findElement(By.xpath("//input[@id='choice-question-text-input-0-0']")).sendKeys("Alpha");
	     driver.findElement(By.xpath("//img[@alt='Add Option']")).click();
	     driver.findElement(By.xpath("//input[@id='choice-question-text-input-0-1']")).sendKeys("Beta");
	     driver.findElement(By.xpath("(//img[@alt='Add Option'])[2]")).click();
	     driver.findElement(By.xpath("//input[@id='choice-question-text-input-0-2']")).sendKeys("Gamma");
	 
	     WebElement gammaOption = driver.findElement(By.xpath("(//img[@alt='Option'])[3]"));     //Answer selection
	     ((JavascriptExecutor) driver).executeScript("arguments[0].click();", gammaOption);
	     test.pass("MCQ Question is added");
	     
	  // adding Dropdown Question
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='images/add_blue.svg']"))).click();
	     driver.findElement(By.xpath("//div[contains(text(),'Dropdown')]")).click();
	     driver.findElement(By.xpath("//input[@id='question-text-input-1']")).sendKeys("Quiz Dropdown Question");
	     driver.findElement(By.xpath("//input[@id='choice-question-text-input-1-0']")).sendKeys("Service");
	     driver.findElement(By.xpath("//div[@id='star-body-container1']//img[@alt='Add Option']")).click();
	     driver.findElement(By.xpath("//input[@id='choice-question-text-input-1-1']")).sendKeys("Product");
	     
	  // This will locate the correct "+" icon to add 3rd option in dropdown question
		 WebElement addOptionBtn3 = wait.until(ExpectedConditions.elementToBeClickable(
		         By.xpath("(//img[@alt='Add Option'])[5]")));
		 addOptionBtn3.click();
		 driver.findElement(By.xpath("//input[@id='choice-question-text-input-1-2']")).sendKeys("Hybrid"); 
	     
	     WebElement hybridOption = driver.findElement(By.xpath("(//img[@alt='Option'])[6]"));     //Answer selection
	     ((JavascriptExecutor) driver).executeScript("arguments[0].click();", hybridOption);
	     test.pass("Dropdown Question is added"); 
	     
	  // Settings  
	     driver.findElement(By.xpath("//span[text()='Settings']")).click();
	     driver.findElement(By.xpath("//span[@class='icon-title'][normalize-space()='Submit']")).click();
	     test.pass("Quiz created with: " + dynamicQuizTitle);
	     
	  // Wait for Share popup to appear and close it
	     WebElement closeSharePopup = wait.until(ExpectedConditions.elementToBeClickable(
	         By.xpath("//div[contains(@class,'popup')]//img[@alt='close']")));
	     closeSharePopup.click();
	     test.pass("Share popup closed successfully");
	     
	     
	  // Identifying the created Quiz card in Quiz Dashboard
	     WebElement quizCard=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='list-card-title-0']//p[@id='card-form-title']")));
	     String actualQuizTitle = quizCard.getText();
	     String expectedQuizTitle = dynamicQuizTitle;
	     
	  // Assert that actualTitle and expectedTitle 
	     Assert.assertEquals(actualQuizTitle, expectedQuizTitle, "Response text does not match expected value");
	     test.pass("Selenium Quiz title is matched");

	     
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

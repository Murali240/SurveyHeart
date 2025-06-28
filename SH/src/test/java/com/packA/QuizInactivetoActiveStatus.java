package com.packA;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

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

public class QuizInactivetoActiveStatus {
	
	
	@Test
	public void create_Form_Method() throws IOException {
		
	 // Extent - Initialize Reporter
        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports/FormReport.html");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);
        ExtentTest test = extent.createTest("SurveyHeart - Toggle Quiz Status: Active → Inactive → Active Test");

		
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
	     String dynamicQuizTitle = "Selenium Quiz - " + timeStamp;

	  // Quiz Creating
	     driver.findElement(By.xpath("//span[text()='Quizzes']")).click();
	     driver.findElement(By.xpath("//span[text()='Create Quiz']")).click();
	     driver.findElement(By.xpath("//input[@maxlength='250']")).sendKeys(dynamicQuizTitle);
	     driver.findElement(By.xpath("//span[text()='Add Question']")).click();
	     driver.findElement(By.xpath("//img[@alt='Short Answer']")).click();
	     driver.findElement(By.xpath("//input[@id='question-text-input-0']")).sendKeys("Short Question");
	     driver.findElement(By.xpath("//input[@maxlength='100']")).sendKeys("Answer");
	     driver.findElement(By.xpath("//span[text()='Settings']")).click();
	     driver.findElement(By.xpath("//span[@class='icon-title'][normalize-space()='Submit']")).click();
	     test.pass("Quiz created with: " + dynamicQuizTitle);
	     
	  // Wait for Share popup to appear and close it
	     WebElement closeSharePopup = wait.until(ExpectedConditions.elementToBeClickable(
	         By.xpath("//div[contains(@class,'popup')]//img[@alt='close']")));
	     closeSharePopup.click();
	     test.pass("Share popup closed successfully");
	     
	  // Identifying the created Form card in Form Dashboard
	     WebElement quizCard=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='list-card-title-0']//p[@id='card-form-title']"))); 
	     driver.findElement(By.xpath("//img[@id='more0']")).click();
	     WebElement statusSwitch = wait.until(ExpectedConditions.elementToBeClickable(
	    		    By.xpath("//img[@alt='status-switch']")));
	     statusSwitch.click();                                                             //Active to In-active status
	     driver.findElement(By.xpath(" //p[normalize-space()='View Quiz']")).click();
	     
	     //Parent window
	     String parentWindow = driver.getWindowHandle();
	     
	     wait.until(ExpectedConditions.numberOfWindowsToBe(2));
	     
	     Set<String> allWindows = driver.getWindowHandles();
	     for (String win : allWindows) {
	         if (!win.equals(parentWindow)) {
	             driver.switchTo().window(win);
	             break;
	         }
	     }
	     
	     String quizClosed = driver.findElement(By.xpath("//p[normalize-space()='CLOSED!']")).getText();
	     String quizClosedMessage = driver.findElement(By.xpath("//p[contains(text(),'This quiz is no longer accepting')]")).getText();
	     test.pass(dynamicQuizTitle+" : "+quizClosed +" : " + quizClosedMessage);
	     
	  // Close old 'closed' form window 
	     for (String win : allWindows) {
	         if (!win.equals(parentWindow)) {
	             driver.switchTo().window(win);
	             driver.close();
	             break;
	         }
	     }
	     
	     driver.switchTo().window(parentWindow);       //Switch to Parent window
	     driver.navigate().refresh();
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Quizzes']")));
	     driver.findElement(By.xpath("//span[text()='Quizzes']")).click();

	  // Make Quiz active again
	     WebElement quizCard2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='list-card-title-0']//p[@id='card-form-title']")));
	     driver.findElement(By.xpath("//img[@id='more0']")).click();
	     WebElement statusSwitch2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='status-switch']")));
	     statusSwitch2.click(); 						// Inactive to Active
	     test.info(dynamicQuizTitle+" : is changing from In-active to Active status");
	     driver.findElement(By.xpath("//p[normalize-space()='View Quiz']")).click();
	     
	  // if 2 windows then switching to new window 
	     wait.until(ExpectedConditions.numberOfWindowsToBe(2));
	     Set<String> allWindows2 = driver.getWindowHandles();
	     for (String win : allWindows2) {
	            if (!win.equals(parentWindow)) {
	                driver.switchTo().window(win);
	                break;
	            }
	        }
	     
	  // Start button in Welcome page, Enter name, Start Quiz button
         wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='Start']"))).click();
         WebElement enterName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='input-Name']")));
         enterName.sendKeys("Sounder");
         driver.findElement(By.xpath("//div[@id='Start Quiz']")).click();

      // Quiz page
         wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@field-type='SHORT_TEXT']")))
                 .sendKeys("Selenium short question quiz answer");
         driver.findElement(By.xpath("//div[@id='Submit']")).click();

      // Submitted page
         WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Submitted']")));
         Assert.assertTrue(successMsg.isDisplayed(), "Quiz not submitted successfully");
         test.pass("Quiz has submitted successfully");

         driver.switchTo().window(parentWindow);
         
      // Navigate Quiz Dashboard 
         driver.navigate().refresh();
         wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Quizzes']")));
         driver.findElement(By.xpath("//span[text()='Quizzes']")).click();  // Quiz tab

      // Click on created Quiz card
         wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='form-card-0']"))).click();     // Answers screen
         wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Individual']"))).click();  // Individual screen

         wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='response-text']")));
         String actualAnswer = driver.findElement(By.xpath("//p[@class='response-text']")).getText();
         String expectedAnswer = "Selenium short question quiz answer";
         Assert.assertEquals(actualAnswer, expectedAnswer, "Answers do not match!");
         test.pass("Quiz Answer matched: " + actualAnswer);


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

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

public class QuizUpdating {
	
	@Test
	public void quiz_Creation_Method() throws IOException  {
		
	 // Extent - Initialize Reporter
        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports/FormReport.html");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);
        ExtentTest test = extent.createTest("SurveyHeart - Update Quiz Test");

		
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
	     
	     
	  // Generate dynamic form title using timestamp
	     String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
	     String dynamicQuizTitle = "Selenium Quiz - " + timeStamp;
	     
	     
	  // Creating Quiz
	     driver.findElement(By.xpath("//span[text()='Quizzes']")).click();
	     driver.findElement(By.xpath("//span[text()='Create Quiz']")).click();
	     driver.findElement(By.xpath("//input[@maxlength='250']")).sendKeys(dynamicQuizTitle);
	     driver.findElement(By.xpath("//span[text()='Add Question']")).click();
	     driver.findElement(By.xpath("//img[@alt='Short Answer']")).click();
	     driver.findElement(By.xpath("//input[@id='question-text-input-0']")).sendKeys("Quiz Short Question");
	     driver.findElement(By.xpath("//input[@maxlength='100']")).sendKeys("Answer");
	     driver.findElement(By.xpath("//span[text()='Settings']")).click();
	     driver.findElement(By.xpath("//span[@class='icon-title'][normalize-space()='Submit']")).click();
	     test.pass("Quiz created with: " + dynamicQuizTitle);
	     
	  // Wait for Share popup to appear and close it
	     WebElement closeSharePopup = wait.until(ExpectedConditions.elementToBeClickable(
	         By.xpath("//div[contains(@class,'popup')]//img[@alt='close']")));
	     closeSharePopup.click();
	     test.pass("Share popup closed successfully");
	     
	  // Updating Quiz from Quiz control
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='list-card-title-0']//p[@id='card-form-title']")));
	     driver.findElement(By.xpath("//img[@id='more0']")).click();
	     driver.findElement(By.xpath("//div//p[text()='Edit Quiz']")).click();
	     test.pass("Builder screen get displayed");
	     
	     // Builder screen
	     WebElement addQuestion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='images/add_blue.svg']")));
	     addQuestion.click();
	     driver.findElement(By.xpath("//div[contains(text(),'Long Answer')]")).click();										//Selecting Long Question
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='question-text-input-1']")));
	     driver.findElement(By.xpath("//input[@id='question-text-input-1']")).sendKeys("Edited Quiz Long Question");
	     driver.findElement(By.xpath("//textarea[@aria-label='Label']")).sendKeys("Quiz answer");
	     driver.findElement(By.xpath("//span[text()='Settings']")).click();
	     driver.findElement(By.xpath("//span[@class='icon-title'][normalize-space()='Submit']")).click();
	     test.pass("Quiz updated with: " + dynamicQuizTitle);
	     
	  // Current window
	     String parentWindow = driver.getWindowHandle();
	     
	  // View icon in Share popup
	     WebElement viewButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='share-card']//div//div//div[@class='mdc-button__touch']")));
	     viewButton.click();

	     wait.until(ExpectedConditions.numberOfWindowsToBe(2));
	     Set<String> allWindows = driver.getWindowHandles();
	     for (String win : allWindows) {
	            if (!win.equals(parentWindow)) {
	                driver.switchTo().window(win);
	                break;
	            }
	        }

	   // Start button in Welcome page, Enter name, Start Quiz
	      WebElement startButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='Start']")));
	      startButton.click();
	      WebElement enterName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='input-Name']")));
		  enterName.sendKeys("Sounder");
		  driver.findElement(By.xpath("//div[@id='Start Quiz']")).click();
	      
	      
	   // Quiz page
	      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='2. Edited Quiz Long Question']")));
	      WebElement editQT = driver.findElement(By.xpath("//span[normalize-space()='2. Edited Quiz Long Question']"));
	      String actualTitle = editQT.getText();
	      String expextedAnswer = "Edited Quiz Long Question";

	   // Assert.assertEquals(actualTitle, expextedAnswer, "Response text does not match with expected value");
	      Assert.assertTrue(actualTitle.contains(expextedAnswer), 
	    		    "Actual title does not match with expected title");
	      test.pass("Quiz edited and new question added successfully: " + actualTitle);
	     
	   
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
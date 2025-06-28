package com.packA;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

public class PremiumQuestionTest {
	
	
	@Test
	public void create_Form_Method() throws IOException {
		
	 // Extent - Initialize Reporter
        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports/FormReport.html");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);
        ExtentTest test = extent.createTest("SurveyHeart - Premium Question Form Creation Test");

		
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
	     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	     WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='label_field_Password']")));
	     password.sendKeys("Automation@1");
	     driver.findElement(By.xpath("//div[@id='Sign in']")).click();
	     test.pass("Signed in successfully");
	     
	  // Dashboard
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Close']"))).click();
	     test.info("Dashboard popup closed successfully");
	     String currentURL = driver.getCurrentUrl();
	     test.info("Current URL after login successfully: " + currentURL);
	     extent.setSystemInfo("Current URL", currentURL);
	
	  // Generate dynamic form title using timestamp
	     String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
	     String dynamicFormTitle = "Premium Question Form - " + timeStamp;

	  // Form Creating
	     driver.findElement(By.xpath("//span[text()='Create Form']")).click();
	     driver.findElement(By.xpath("//input[@maxlength='250']")).sendKeys(dynamicFormTitle);
	     driver.findElement(By.xpath("//span[text()='Add Question']")).click();
	     
	  // Slider Question
	     driver.findElement(By.xpath("//div[@class='builder-box1']//div[7]")).click();     // Slider Question
	     driver.findElement(By.xpath("//input[@id='question-text-input-0']")).sendKeys("Slider Question");
	     test.pass("Slider Question is added");
	     
	     
	  // PCT Question
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='images/add_blue.svg']")));   // Add button
	     driver.findElement(By.xpath("//img[@src='images/add_blue.svg']")).click();     
	     driver.findElement(By.xpath("//div[@class='builder-box1']//div[8]")).click();  
	     driver.findElement(By.xpath("//input[@id='question-text-input-1']")).sendKeys("Picture Choice Question");
	     
	  // Add button  1st picture
	     driver.findElement(By.xpath("//div[@id='Add Picture']")).click();    // Add picture button
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='/images/add_grey.svg']")));
	     driver.findElement(By.xpath("//img[@alt='/images/add_grey.svg']")).click();   // +Add image button
	     wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='popup-file-upload-button']")));
	     driver.findElement(By.xpath("//div[@id='popup-file-upload-button']")).click(); 
	     
	  // Selecting 1st Picture from local device   Upload the file using hidden <input type='file'>
         WebElement fileInput1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));
         fileInput1.sendKeys("C:\\Users\\mural\\Downloads\\Pictureone.jpg");
         
      // Add button for 2nd picture
	     driver.findElement(By.xpath("//div[@id='Add Picture']")).click(); 
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='/images/add_grey.svg']")));
	     driver.findElement(By.xpath("//img[@alt='/images/add_grey.svg']")).click(); 
	     wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='popup-file-upload-button']")));
	     driver.findElement(By.xpath("//div[@id='popup-file-upload-button']")).click(); 
	     
	  // Selecting 2nd Picture from local device   Upload the file using hidden <input type='file'>
         WebElement fileInput2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));
         fileInput2.sendKeys("C:\\Users\\mural\\Downloads\\Picturetwo.jpg");
         
      // Add button for 3rd picture
	     driver.findElement(By.xpath("//div[@id='Add Picture']")).click(); 
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='/images/add_grey.svg']")));
	     driver.findElement(By.xpath("//img[@alt='/images/add_grey.svg']")).click(); 
	     wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='popup-file-upload-button']")));
	     driver.findElement(By.xpath("//div[@id='popup-file-upload-button']")).click(); 
	     
	  // Selecting 3rd Picture from local device   Upload the file using hidden <input type='file'>
         WebElement fileInput3 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));
         fileInput3.sendKeys("C:\\Users\\mural\\Downloads\\Picturethree.jpg");
         
      // Add button for 4th picture
	     driver.findElement(By.xpath("//div[@id='Add Picture']")).click(); 
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='/images/add_grey.svg']")));
	     driver.findElement(By.xpath("//img[@alt='/images/add_grey.svg']")).click(); 
	     wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='popup-file-upload-button']")));
	     driver.findElement(By.xpath("//div[@id='popup-file-upload-button']")).click(); 
	     
	  // Selecting 4th Picture from local device   Upload the file using hidden <input type='file'>
         WebElement fileInput4 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));
         fileInput4.sendKeys("C:\\Users\\mural\\Downloads\\Picturefour.jpg");
         test.pass("Picture Choice Question is added");
       
         
      // Ranking Question
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='question_card_1']//img[@alt='Add Question']")));   // Add button
	     driver.findElement(By.xpath("//div[@id='question_card_1']//img[@alt='Add Question']")).click();     
	     driver.findElement(By.xpath("//div[@class='builder-box1']//div[9]")).click();  
	     driver.findElement(By.xpath("//input[@id='question-text-input-2']")).sendKeys("Ranking Question");
	     driver.findElement(By.xpath("//input[@id='choice-question-text-input-2-0']")).sendKeys("Apple");   
	     driver.findElement(By.xpath("//img[@alt='Add Option']")).click(); 
	     driver.findElement(By.xpath("//input[@id='choice-question-text-input-2-1']")).sendKeys("Nokia");   
	     driver.findElement(By.xpath("(//img[@alt='Add Option'])[2]")).click(); 
	     driver.findElement(By.xpath("//input[@id='choice-question-text-input-2-2']")).sendKeys("Realme");   
	     driver.findElement(By.xpath("(//img[@alt='Add Option'])[3]")).click(); 
	     driver.findElement(By.xpath("//input[@id='choice-question-text-input-2-3']")).sendKeys("Vivo"); 
	     test.pass("Ranking Question is added");
         
         
	  // Agreement Question
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='question_card_2']//img[@alt='Add Question']")));   // Add button
	     driver.findElement(By.xpath("//div[@id='question_card_2']//img[@alt='Add Question']")).click();     
	     driver.findElement(By.xpath("//div[contains(text(),'Agreement')]")).click();  
	     driver.findElement(By.xpath("//input[@id='question-text-input-3']")).sendKeys("Agreement Question");
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@class='mdc-text-field__input']")));
	     driver.findElement(By.xpath("//textarea[@class='mdc-text-field__input']")).sendKeys("By accessing and using this service, you agree to abide by our Terms and Conditions. Please ensure that you have read and understood all the guidelines, including our Privacy Policy. Continuing further implies your acceptance of these terms and your consent to proceed under the stated rules.");
	     test.pass("Agreement Question is added");
	     
	     
	  // Signature Question
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='question_card_3']//img[@alt='Add Question']")));   // Add button
	     driver.findElement(By.xpath("//div[@id='question_card_3']//img[@alt='Add Question']")).click();     
	     driver.findElement(By.xpath("//div[contains(text(),'Signature')]")).click();  
	     driver.findElement(By.xpath("//input[@id='question-text-input-4']")).sendKeys("Signature Question");
	     test.pass("Signature Question is added");
	     
	     
	  // Settings
	     driver.findElement(By.xpath("//span[text()='Settings']")).click();
	     driver.findElement(By.xpath("//span[@class='icon-title'][normalize-space()='Submit']")).click();
	     test.pass("Form created with: " + dynamicFormTitle);
	     
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
	     

	   // Click Start button in Welcome page
	      WebElement startButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='Start']")));
	      startButton.click();
	      
	   // Form page - Wait for the question count element
		  WebElement totalQuestionCount = wait.until(ExpectedConditions.visibilityOfElementLocated(
		                                                       By.xpath("//span[@id='total_question_count']")));

	   // Get the full text like "1 of 10", "Question 1 of 10", etc.
		  String totalQuestionsText = totalQuestionCount.getText();  // e.g., "1 of 10"

	   // Extract the last number (total question count) using regex
		  String totalOnlyDigits = "";
		  Matcher matcher = Pattern.compile("of\\s*(\\d+)").matcher(totalQuestionsText);
		     if (matcher.find()) {
		         totalOnlyDigits = matcher.group(1);  // Gets the number after "of"
		     }

	   // Print in console and report
		  test.info("Total "+totalOnlyDigits+ " Premium questions are added to the form : " + dynamicFormTitle);


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

package com.Forms;

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
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class EditorFormEditingTest {
	
	
	@Test
	public void testEditorFormEditing() throws IOException {
		
	 // Extent - Initialize Reporter
        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports/FormReport.html");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);
        ExtentTest test = extent.createTest("SurveyHeart - Form Update by Editor Test");

		
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
	     test.pass("Owner Signed in successfully");
	     
	  // Dashboard
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Close']"))).click();
	     String currentURL = driver.getCurrentUrl();
	     test.info("Current URL after owner login successfully : " + currentURL);
	     extent.setSystemInfo("Current URL", currentURL);
	
	  // Generate dynamic form title using timestamp
	     String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
	     String dynamicFormTitle = "Selenium Form - " + timeStamp;

	  // Form Creating
	     driver.findElement(By.xpath("//span[text()='Create Form']")).click();
	     driver.findElement(By.xpath("//input[@maxlength='250']")).sendKeys(dynamicFormTitle);
	     driver.findElement(By.xpath("//span[text()='Add Question']")).click();
	     driver.findElement(By.xpath("//img[@alt='Short Answer']")).click();
	     driver.findElement(By.xpath("//input[@id='question-text-input-0']")).sendKeys("Short Question");
	     
	  // Settings screen and Adding Admin collaborator
	     driver.findElement(By.xpath("//span[text()='Settings']")).click();
	     driver.findElement(By.xpath("//span[normalize-space()='Collaboration']")).click();
	     driver.findElement(By.xpath("//div[@id='Add Collaborator']")).click();
	     driver.findElement(By.xpath("//input[@type='email']")).sendKeys("meyap64096@forcrack.com");
	     
	  // Wait for the Role dropdown to be visible
	     WebElement roleDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(
	         By.xpath("//div[@class='mdc-select mdc-select--outlined']//div[@class='mdc-select__anchor']")));

	  // Click the dropdown to show the list of roles
	     roleDropdown.click();

	  // Wait and select the "Editor" option
	     WebElement editorOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
	         By.xpath("//li[@data-value='Editor']") ));
	     editorOption.click();
	     
	  // Add button
	     driver.findElement(By.xpath("//div[@id='Add']")).click();

      // Submit button
	     driver.findElement(By.xpath("//span[@class='icon-title'][normalize-space()='Submit']")).click();
	     test.pass("Form created with : " + dynamicFormTitle);
	     test.info("Owner added an editor collaborator successfully : meyap64096@forcrack.com" );
	     
	  // Wait for Share popup to appear and close it
	     WebElement closeSharePopup = wait.until(ExpectedConditions.elementToBeClickable(
	         By.xpath("//div[contains(@class,'popup')]//img[@alt='close']")));
	     closeSharePopup.click();
	     
         driver.navigate().refresh();        // Form dashboard
	     
      // Account icon clicking
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='profile-initial']")));
	     driver.findElement(By.xpath("//span[@class='profile-initial']")).click();
	     
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='Sign Out']")));
	     driver.findElement(By.xpath("//div[@id='Sign Out']")).click();
	     test.info("Owner account is signout : vilem14826@hosliy.com");
	     
	     
	  // Admin Signin - Click "Sign in using Email
	     driver.findElement(By.xpath("//span[normalize-space()='Sign in using Email']")).click();
	     driver.findElement(By.xpath("//label[@id='label_field_Email']")).sendKeys("meyap64096@forcrack.com");
	     driver.findElement(By.xpath("//div[@id='Next']")).click();
	     test.info("Navigated to SurveyHeart login page");
	     
	  // Enter Password
	     WebElement password2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='label_field_Password']")));
	     password2.sendKeys("Automation@3");
	     driver.findElement(By.xpath("//div[@id='Sign in']")).click();
	     test.pass("Editor Signed in successfully");
	     
	  // Dashboard
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Close']"))).click();
	     String currentURL2 = driver.getCurrentUrl();
	     test.info("Current URL after Editor login successfully : " + currentURL2);
	     
	
      // Shared Form Dashboard
	     driver.findElement(By.xpath("//span[normalize-space()='Shared']")).click();
	     
	  // Shared Form card
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='list-card-title-0']//p[@id='card-form-title']")));
	     driver.findElement(By.xpath("//img[@id='more0']")).click();
	     driver.findElement(By.xpath("//div//p[text()='Edit Form']")).click();
	     test.pass("Builder screen get displayed");
	     
	  // Work on here -To handle + Add button visibility
	     WebElement addQuestion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='images/add_blue.svg']")));
	     addQuestion.click();
	     
	  // Adding Long Question in Builder
	     driver.findElement(By.xpath("//div[contains(text(),'Long Answer')]")).click();
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='question-text-input-1']")));
	     driver.findElement(By.xpath("//input[@id='question-text-input-1']")).sendKeys("Edited Form Long Question");
	     driver.findElement(By.xpath("//span[text()='Settings']")).click();
	     driver.findElement(By.xpath("//span[@class='icon-title'][normalize-space()='Submit']")).click();
	     test.pass("Editor has edited the shared form and added a new question : " + dynamicFormTitle);
	     
	     
	  // Wait for Share popup to appear and close it
	     WebElement closeSharePopup2 = wait.until(ExpectedConditions.elementToBeClickable(
	         By.xpath("//div[contains(@class,'popup')]//img[@alt='close']")));
	     closeSharePopup2.click();
	     
         driver.navigate().refresh();
	     
      // Account icon clicking
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='profile-initial']")));
	     driver.findElement(By.xpath("//span[@class='profile-initial']")).click();
	     
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='Sign Out']")));
	     driver.findElement(By.xpath("//div[@id='Sign Out']")).click();
	     test.info("Editor account is signout : meyap64096@forcrack.com");
	     
	     
	  // Again Owner Signin - Click "Sign in using Email
	     driver.findElement(By.xpath("//span[normalize-space()='Sign in using Email']")).click();
	     driver.findElement(By.xpath("//label[@id='label_field_Email']")).sendKeys("vilem14826@hosliy.com");
	     driver.findElement(By.xpath("//div[@id='Next']")).click();
	     test.info("Navigated to SurveyHeart login page");
	     
	  // Enter Password
	     WebElement password3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='label_field_Password']")));
	     password3.sendKeys("Automation@1");
	     driver.findElement(By.xpath("//div[@id='Sign in']")).click();
	     test.pass("Again Owner Signed in successfully");
	     
	  // Dashboard
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Close']"))).click();
	     String currentURL3 = driver.getCurrentUrl();
	     test.info("Current URL after Owner login successfully : " + currentURL3);
	     
	  // Edit the Form from Form control 
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='list-card-title-0']//p[@id='card-form-title']")));
	     driver.findElement(By.xpath("//img[@id='more0']")).click();
	     driver.findElement(By.xpath("//div//p[text()='View Form']")).click();
	     
	  // Current window
	     String parentWindow = driver.getWindowHandle();
	     
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
	     
	  // Wait for the question count element
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
	     test.info("Total questions verified on the form page : " + totalOnlyDigits);
	     test.pass("Editor successfully updated the form with a new question");
  
	     
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

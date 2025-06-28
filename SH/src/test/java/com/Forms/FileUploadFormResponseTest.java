package com.Forms;

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

public class FileUploadFormResponseTest {
	
	
	@Test
	public void createFileUploadFormWithResponse() throws IOException {
		
	 // Extent - Initialize Reporter
        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports/FormReport.html");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);
        ExtentTest test = extent.createTest("SurveyHeart - File Upload Form Creation Test");

		
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
	     test.info("Current URL after login successfully: " + currentURL);
	     extent.setSystemInfo("Current URL", currentURL);
	
	  // Generate dynamic form title using timestamp
	     String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
	     String dynamicFormTitle = "Selenium File Upload - " + timeStamp;

	  // Form Creating
	     driver.findElement(By.xpath("//span[text()='Create Form']")).click();
	     driver.findElement(By.xpath("//input[@maxlength='250']")).sendKeys(dynamicFormTitle);
	     driver.findElement(By.xpath("//span[text()='Add Question']")).click(); 
	     driver.findElement(By.xpath("//div[@id='question-group-container-4']//div[@class='question-type-grid-container']//div[1]")).click();                                // Short QT
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='question-text-input-0']")));
	     driver.findElement(By.xpath("//input[@id='question-text-input-0']")).sendKeys("File Upload Question");
	     test.pass("File upload question is added");

	  // Setting screen
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Settings']")));
	     driver.findElement(By.xpath("//span[text()='Settings']")).click();
	     driver.findElement(By.xpath("//span[@class='icon-title'][normalize-space()='Submit']")).click();
	     test.pass("Form created with : " + dynamicFormTitle);
	     
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

	  // Start button in Welcome page, Enter name, Start Quiz button
         wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='Start']"))).click();
         WebElement enterName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='input-Name']")));
         enterName.sendKeys("Sounder");
         driver.findElement(By.xpath("//div[@id='Start Form']")).click();
            
      // Form page - Upload section
         WebElement answerBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='Upload']")));
         answerBox.click();

      // Click on Choose File button
         WebElement uploadButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='popup-file-upload-button']")));
         uploadButton.click();

      // Upload the file using hidden <input type='file'>
         WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));
         fileInput.sendKeys("C:\\Users\\mural\\Downloads\\video.mp4");

      // Now submit the form
         driver.findElement(By.xpath("//div[@id='Submit']")).click();
         test.pass("Video file was uploaded successfully, and the form was submitted");

            
	  // Submitted page
	     WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Submitted']")));
	     if (successMsg.isDisplayed()) {
	     test.pass("The form was submitted successfully, and a 'Thank you' message appeared");
	        }
   
	  // Come to Main window
	     driver.switchTo().window(parentWindow);
	       
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
	     test.pass("Actual file form title matched the expected form title : "+dynamicFormTitle);  
	    
	     
	     
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

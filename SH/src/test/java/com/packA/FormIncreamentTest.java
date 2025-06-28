package com.packA;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
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

public class FormIncreamentTest {
	
	
	@Test
    public void form_Creation_With_Response_Method() throws IOException, InterruptedException {
     // Extent - Initialize Reporter
        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports/FormReport.html");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);
        ExtentTest test = extent.createTest("SurveyHeart - Form Increment Test");

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
        test.info("Navigated to SurveyHeart login page");

     // Sign in using Email
        driver.findElement(By.xpath("//span[normalize-space()='Sign in using Email']")).click();
        driver.findElement(By.xpath("//label[@id='label_field_Email']")).sendKeys("vilem14826@hosliy.com");
        driver.findElement(By.xpath("//div[@id='Next']")).click();
        test.info("Entered email and proceeded to password screen");

     // Enter Password
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='label_field_Password']")));
        password.sendKeys("Automation@1");
        driver.findElement(By.xpath("//div[@id='Sign in']")).click();
        test.pass("Signed in successfully");

     // Dashboard
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Close']"))).click();
        String currentURL = driver.getCurrentUrl();
        test.info("Current URL after login successfully : " + currentURL);
        extent.setSystemInfo("Current URL ", currentURL);
        
      // User Account icon
         driver.navigate().refresh();
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='profile-initial']")));
	     driver.findElement(By.xpath("//span[@class='profile-initial']")).click();
	     String userCurrentPlan = driver.findElement(By.xpath("//span[normalize-space()='FREE']")).getText();       //Plan type
	     test.info("User current plan is : "+userCurrentPlan);

	     String currentPlanStorage =  wait.until(ExpectedConditions.visibilityOfElementLocated(
	         By.xpath("//span[contains(text(),'of 1GB Used')]"))).getText();
	     test.info(userCurrentPlan + " user Storage, before form submission : " + currentPlanStorage);

	     String currentPlanResponse = wait.until(ExpectedConditions.visibilityOfElementLocated(
	         By.xpath("//span[contains(text(),'of 10000 Used')]"))).getText();
	     test.info(userCurrentPlan + " user Total Responses, before form submission : " + currentPlanResponse);
	       
	  // Images
	     try {
	    	    String currentPlanImages = wait.until(ExpectedConditions.visibilityOfElementLocated(
	    	        By.xpath("(//span[contains(text(),'of') and contains(text(),'Used') and contains(text(),'50')])[1]")
	    	    )).getText();
	    	    test.info(userCurrentPlan + " user Images, before form submission : " + currentPlanImages);
	    	} catch (Exception e) {
	    	    test.warning("User Images usage info not visible or not present for this plan.");
	    	}  
	     
	  // Themes
	     try {
	    	    String currentPlanThemes = wait.until(ExpectedConditions.visibilityOfElementLocated(
	    	        By.xpath("//div[contains(text(),'Themes') or contains(.,'Themes')]/span[contains(text(),'of 50 Used')]")
	    	    )).getText();
	    	    test.info(userCurrentPlan + " user Themes,  before form submission : " + currentPlanThemes);
	    	} catch (Exception e) {
	    	    test.warning("User Themes usage info not visible or not present for this plan.");
	    	}

	     
	 // After getting before incrementing fetched usage details then refresh the page
	    driver.navigate().refresh();

     // Generate dynamic form title using timestamp
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String dynamicFormTitle = "Selenium Form - " + timeStamp;

     // Creating Form
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Create Form']"))).click();
        driver.findElement(By.xpath("//input[@maxlength='250']")).sendKeys(dynamicFormTitle);
        driver.findElement(By.xpath("//span[text()='Add Question']")).click();
        driver.findElement(By.xpath("//div[@id='question-group-container-4']//div[@class='question-type-grid-container']//div[1]")).click();
        driver.findElement(By.xpath("//input[@id='question-text-input-0']")).sendKeys("File Upload Question");
        
     // IMAGE UPLOAD 
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='attach']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='popup-menu-container']//div//div[1]"))).click(); // Image option
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='/images/add_grey.svg']"))).click(); // +Add image button

     // WAIT for <input type='file'> to appear and upload directly (no Choose button click)
        WebElement fileInputImage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));
        fileInputImage.sendKeys("C:\\Users\\mural\\Downloads\\Pictureone.jpg");

     // Wait for tick mark image confirmation
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@alt='tick mark']"))).click();

     // THEME UPLOAD
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Settings']"))).click();
        driver.findElement(By.xpath("(//img[@alt='classic_new.jpeg'])[1]")).click();        // Default theme 
        driver.findElement(By.xpath("//img[@src='images/add_white.png']")).click();         // +My Themes button
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='/images/add_grey.svg']"))).click(); // +Add image button

     // Again: directly upload file via hidden <input> (DO NOT click Choose Image)
        WebElement fileInputTheme = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));
        fileInputTheme.sendKeys("C:\\Users\\mural\\Downloads\\Picturetwo.jpg");

     // Confirm image selection
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@alt='tick mark']"))).click();

     // SUBMIT FORM
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='icon-title'][normalize-space()='Submit']")));
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


     // Start button in Welcome page, Enter name, Start Form button
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='Start']"))).click();
        WebElement enterName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='input-Name']")));
        enterName.sendKeys("Sounder");
        driver.findElement(By.xpath("//div[@id='Start Form']")).click();
        
        
     // Step 1: Click the Upload area
        WebElement answerBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='Upload']")));
        answerBox.click();

        // Step 2: Upload using hidden input
        WebElement uploadInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));
        uploadInput.sendKeys("C:\\Users\\mural\\Downloads\\video.mp4");

        // Step 3: Wait for uploaded file confirmation (e.g., filename appears)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='text']"))); // text element with filename

        // Step 4: Scroll to Submit button
        WebElement submitBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='Submit']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitBtn);
        submitBtn.click();
        test.pass("Video file uploaded and form submitted successfully");

        
	  // Submitted page
	     WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Submitted']")));
	     if (successMsg.isDisplayed()) {
	     test.pass("Form submitted and Thank you confirmation message got displayed");
	        }
  
	  // Come to Main window
	     driver.switchTo().window(parentWindow);
	       
	  // Wait for Share popup to appear and close it
	     WebElement closeSharePopup = wait.until(ExpectedConditions.elementToBeClickable(
	     By.xpath("//div[contains(@class,'popup')]//img[@alt='close']")));
	     closeSharePopup.click();
	   
	     driver.navigate().refresh();
	     
	  // if Follow Us popup then handle it
	     try {
	    	    WebElement followUs = wait.until(ExpectedConditions.visibilityOfElementLocated(
	    	        By.xpath("(//img[@alt='data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyNCIgaGVpZ2h0PSIyNCIgdmlld0JveD0iMCAwIDI0IDI0Ij4KICAgIDxwYXRoIGQ9Ik0xOSA2LjQxTDE3LjU5IDUgMTIgMTAuNTkgNi40MSA1IDUgNi40MSAxMC41OSAxMiA1IDE3LjU5IDYuNDEgMTkgMTIgMTMuNDEgMTcuNTkgMTkgMTkgMTcuNTkgMTMuNDEgMTJ6Ii8+CiAgICA8cGF0aCBkPSJNMCAwaDI0djI0SDB6IiBmaWxsPSJub25lIi8+Cjwvc3ZnPg=='])[1]")));

	    	    followUs.click();
	    	    test.info("Follow Us popup was present and closed successfully.");
	    	} catch (TimeoutException | NoSuchElementException e) {
	    	    test.info("Follow Us popup did not appear this time. Continuing test execution.");
	    	}

	     
	  // User Account icon
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='profile-initial']")));
	     driver.findElement(By.xpath("//span[@class='profile-initial']")).click();

	     String currentPlanStorage2 =  wait.until(ExpectedConditions.visibilityOfElementLocated(
	         By.xpath("//span[contains(text(),'of 1GB Used')]"))).getText();
	     test.info(userCurrentPlan + " user Storage, after form submission : " + currentPlanStorage2);

	     String currentPlanResponse2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
	         By.xpath("//span[contains(text(),'of 10000 Used')]"))).getText();
	     test.info(userCurrentPlan + " user Total Responses, after form submission : " + currentPlanResponse2);
	       
	  // Images
	     try {
	    	    String currentPlanImages2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
	    	        By.xpath("(//span[contains(text(),'of') and contains(text(),'Used') and contains(text(),'50')])[1]")
	    	    )).getText();
	    	    test.info(userCurrentPlan + " user Images, after form submission : " + currentPlanImages2);
	    	} catch (Exception e) {
	    	    test.warning(" User Images usage info not visible or not present for this plan.");
	    	}  
	     
	  // Themes
	     try {
	    	    String currentPlanThemes = wait.until(ExpectedConditions.visibilityOfElementLocated(
	    	        By.xpath("//div[contains(text(),'Themes') or contains(.,'Themes')]/span[contains(text(),'of 50 Used')]")
	    	    )).getText();
	    	    test.info(userCurrentPlan + " user Themes, after form submission : " + currentPlanThemes);
	    	} catch (Exception e) {
	    	    test.warning("User Themes usage info not visible or not present for this plan.");
	    	}

	     
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

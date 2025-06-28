package com.packA;

import java.awt.Desktop;
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

public class FormwithSingleResponse {
	
	
	@Test
    public void form_Creation_With_Response_Method() throws IOException {
     // Extent - Initialize Reporter
        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports/FormReport.html");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);
        ExtentTest test = extent.createTest("SurveyHeart - Single Response Form Test");

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

     // Creating Form
        driver.findElement(By.xpath("//span[text()='Create Form']")).click();
        driver.findElement(By.xpath("//input[@maxlength='250']")).sendKeys(dynamicFormTitle);
        driver.findElement(By.xpath("//span[text()='Add Question']")).click();
        driver.findElement(By.xpath("//img[@alt='Short Answer']")).click();
        driver.findElement(By.xpath("//input[@id='question-text-input-0']")).sendKeys("Short Question");
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

     // Form page
        WebElement answerBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@field-type='SHORT_TEXT']")));
        answerBox.sendKeys("Selenium short question form response");
        driver.findElement(By.xpath("//div[@id='Submit']")).click();

     // Submitted page
        WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Submitted']")));
        if (successMsg.isDisplayed()) {
            test.pass("Form submitted and Thank you confirmation message got displayed");
        }

        
        driver.switchTo().window(parentWindow);
        driver.navigate().refresh();

     // Form Dashboard
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='list-card-title-0']//p[@id='card-form-title']")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='form-card-0']"))).click();

     // Overview screen
        WebElement responseText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='text-response-card']")));
        String actualAnswer = responseText.getText();
        String expextedAnswer = "Selenium short question form response";

        Assert.assertEquals(actualAnswer, expextedAnswer, "Response text does not match with expected value");
        test.pass("Form Response verified successfully: " + actualAnswer);

        
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



package com.Forms;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Keys;
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

public class SendFormLinkViaWhatsAppTest {
	
	
	@Test
    public void sendFormLinkViaWhatsAppAfterCreation() throws IOException, InterruptedException {

     // Extent Report Setup
        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports/FormReport.html");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);
        ExtentTest test = extent.createTest("SurveyHeart - Send Form Link via WhatsApp Test");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        test.info("Browser launched");

     // System Info
        String os = System.getProperty("os.name").toUpperCase();
        String user = System.getProperty("user.name");
        Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = caps.getBrowserName();
        String browserVersion = caps.getBrowserVersion();

        extent.setSystemInfo("Executed By", user);
        extent.setSystemInfo("Operating System", os);
        extent.setSystemInfo("Browser", browserName);
        extent.setSystemInfo("Browser Version", browserVersion);

    //  Login
        driver.get("https://surveyheart.com/app#login");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        test.info("Navigated to SurveyHeart login page");

     // Enter Email
        driver.findElement(By.xpath("//span[normalize-space()='Sign in using Email']")).click();
        driver.findElement(By.xpath("//label[@id='label_field_Email']")).sendKeys("vilem14826@hosliy.com");
        driver.findElement(By.xpath("//div[@id='Next']")).click();

     // Enter Password
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[@id='label_field_Password']")));
        password.sendKeys("Automation@1");
        driver.findElement(By.xpath("//div[@id='Sign in']")).click();
        test.pass("Signed in successfully");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Close']"))).click();
        String currentURL = driver.getCurrentUrl();
        test.info("Current URL : " + currentURL);
        

     // Dynamic Form title
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String dynamicFormTitle = "Selenium Form - " + timeStamp;

     // Create New Form
        driver.findElement(By.xpath("//span[text()='Create Form']")).click();
        driver.findElement(By.xpath("//input[@maxlength='250']")).sendKeys(dynamicFormTitle);
        driver.findElement(By.xpath("//span[text()='Add Question']")).click();
        driver.findElement(By.xpath("//img[@alt='Short Answer']")).click();
        driver.findElement(By.xpath("//input[@id='question-text-input-0']")).sendKeys("Short Question");

        driver.findElement(By.xpath("//span[text()='Settings']")).click();
        driver.findElement(By.xpath("//span[@class='icon-title'][normalize-space()='Submit']")).click();

     // Get Dynamic Form Link
        WebElement formLinkInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[contains(@value, 'https://surveyheart.com/form/')]")));
        String actualFormLink = formLinkInput.getAttribute("value");
        test.pass("Form created with title : " + dynamicFormTitle);
        test.info("Form Link : " + actualFormLink);

     // Share via WhatsApp
        String parentWindow = driver.getWindowHandle();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='WhatsApp']"))).click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

     // Switch to new window
        Set<String> allWindows = driver.getWindowHandles();
        for (String win : allWindows) {
            if (!win.equals(parentWindow)) {
                driver.switchTo().window(win);
                break;
            }
        }

        
        String contactName = "Dady";           // Choose your required contact name in your WhatsApp

        try {
            // Handle "Fresh Look" popup
            try {
                WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@role='button' and normalize-space()='Continue']")));
                continueBtn.click();
                System.out.println("Intro popup dismissed.");
            } catch (TimeoutException e) {
                System.out.println("WhatsApp popup could not be auto-handled and was closed manually");
                test.info("WhatsApp popup could not be auto-handled and was closed manually");
            }

            
         // Search for contact
            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@contenteditable='true' and @data-tab='3']")));
            searchInput.click();
            searchInput.sendKeys(contactName);
            Thread.sleep(2000);

            WebElement contact = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[@title='" + contactName + "']")));
            contact.click();

         // Send the message
            WebElement messageBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@contenteditable='true' and @data-tab='10']")));
            messageBox.sendKeys(actualFormLink);
            messageBox.sendKeys(Keys.ENTER);

            test.pass("Form link was sent successfully to the WhatsApp contact : " + contactName);

        }   catch (Exception e) {
            test.fail("Failed to send message : " + e.getMessage());
            System.out.println("Error: " + e.getMessage());
        }   finally {
            Thread.sleep(3000);
            driver.quit();
            test.info("Browser closed");
        }

        
        //  Flush Extent Report
        	extent.flush();

        //  Open Report Automatically
	        File reportFile = new File("ExtentReports/FormReport.html");
	        String reportPath = reportFile.getAbsolutePath();
	        String chromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
	
	        Runtime.getRuntime().exec(new String[]{chromePath, reportPath});
        
    
	        
        
    }

}

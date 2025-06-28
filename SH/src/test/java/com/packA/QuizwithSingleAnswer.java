package com.packA;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import org.jspecify.annotations.Nullable;
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

	public class QuizwithSingleAnswer {

	    @Test
	    public void quiz_Creation_With_Answer_Method() throws IOException {

	     // Setup ExtentReport
	        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports/FormReport.html");
	        ExtentReports extent = new ExtentReports();
	        extent.attachReporter(spark);

	        ExtentTest test = extent.createTest("SurveyHeart - Single Response Quiz Test");

	     // Launch Chrome Browser
	        WebDriver driver = new ChromeDriver();
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	        test.info("Chrome browser launched");

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
	       	    
	        
	        try {
	            driver.get("https://surveyheart.com/app#login");
	            driver.manage().window().maximize();
	            driver.manage().deleteAllCookies();
	            test.info("Navigated to SurveyHeart login page");

	         // Click Sign in using Email
	            driver.findElement(By.xpath("//span[normalize-space()='Sign in using Email']")).click();
	            driver.findElement(By.xpath("//label[@id='label_field_Email']")).sendKeys("vilem14826@hosliy.com");
	            driver.findElement(By.xpath("//div[@id='Next']")).click();

	         // Enter Password
	            WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='label_field_Password']")));
	            password.sendKeys("Automation@1");
	            driver.findElement(By.xpath("//div[@id='Sign in']")).click();

	         // Dashboard
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Close']"))).click();
	            test.pass("Logged in and Dashboard loaded");
	            String currentURL = driver.getCurrentUrl();
	            test.info("Current URL after login successfully: " + currentURL);
	            extent.setSystemInfo("Current URL", currentURL);

	         // Generate dynamic quiz title using timestamp
	            String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
	            String quizTitle = "Selenium Quiz - " + timeStamp;

	         // Creating Quiz
	            driver.findElement(By.xpath("//span[text()='Quizzes']")).click();
	            driver.findElement(By.xpath("//span[text()='Create Quiz']")).click();
	            driver.findElement(By.xpath("//input[@maxlength='250']")).sendKeys(quizTitle);
	            driver.findElement(By.xpath("//span[text()='Add Question']")).click();
	            driver.findElement(By.xpath("//img[@alt='Short Answer']")).click();
	            driver.findElement(By.xpath("//input[@id='question-text-input-0']")).sendKeys("Quiz Short Question");
	            driver.findElement(By.xpath("//input[@maxlength='100']")).sendKeys("Answer");
	            driver.findElement(By.xpath("//span[text()='Settings']")).click();
	            driver.findElement(By.xpath("//span[@class='icon-title'][normalize-space()='Submit']")).click();
	            test.pass("Quiz created with: " + quizTitle);

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
	            
	         // Navigate Dashboard Quiz tab
	            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'popup')]//img[@alt='close']"))).click();
	            driver.navigate().refresh();

	         // Click on created Quiz card
	            driver.findElement(By.xpath("//span[text()='Quizzes']")).click();  // Quiz tab
	            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='form-card-0']"))).click();     // Answers screen
	            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Individual']"))).click();  // Individual screen

	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='response-text']")));
	            String actualAnswer = driver.findElement(By.xpath("//p[@class='response-text']")).getText();
	            String expectedAnswer = "Selenium short question quiz answer";
	            Assert.assertEquals(actualAnswer, expectedAnswer, "Answers do not match!");
	            test.pass("Quiz Answer matched: " + actualAnswer);

	        } catch (Exception e) {
	            test.fail("Test failed: " + e.getMessage());
	            e.printStackTrace();
	        } finally {
	            driver.quit();
	            test.info("Browser closed");

	            extent.flush();
	            test.info("Extent report generated successfully.");
	        }

	     // Open report automatically in Chrome browser
	        File reportFile = new File("ExtentReports/FormReport.html");
	        String reportPath = reportFile.getAbsolutePath();

	     // Adjust Chrome path if needed
	        String chromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
	        
	     // Launch Chrome with the report file
	        Runtime.getRuntime().exec(new String[]{chromePath, reportPath});
	        
	        
	        
	        
	    }
	}

	
	
	
	
	
	
	
	
	

	

	   /* @Test
	    public void quiz_Creation_With_Answer_Method() throws IOException {

	        // === Setup ExtentReport ===
	        
	        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports/FormReport.html");
	        ExtentReports extent = new ExtentReports();
	        extent.attachReporter(spark);
	        ExtentTest test = extent.createTest("Quiz Automation", "Create quiz, submit answer, verify response");

	        // === Launch Chrome ===
	        WebDriver driver = new ChromeDriver();
	        test.info("Chrome browser launched");
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

	        try {
	            driver.get("https://surveyheart.com/app#login");
	            driver.manage().window().maximize();
	            driver.manage().deleteAllCookies();
	            test.info("Navigated to SurveyHeart login page");

	            driver.findElement(By.xpath("//span[normalize-space()='Sign in using Email']")).click();
	            driver.findElement(By.xpath("//label[@id='label_field_Email']")).sendKeys("vilem14826@hosliy.com");
	            driver.findElement(By.xpath("//div[@id='Next']")).click();

	            WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='label_field_Password']")));
	            password.sendKeys("Automation@1");
	            driver.findElement(By.xpath("//div[@id='Sign in']")).click();

	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Close']"))).click();
	            test.pass("Logged in and Dashboard loaded");

	            String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
	            String quizTitle = "Selenium Quiz - " + timeStamp;

	            driver.findElement(By.xpath("//span[text()='Quizzes']")).click();
	            driver.findElement(By.xpath("//span[text()='Create Quiz']")).click();
	            driver.findElement(By.xpath("//input[@maxlength='250']")).sendKeys(quizTitle);
	            driver.findElement(By.xpath("//span[text()='Add Question']")).click();
	            driver.findElement(By.xpath("//img[@alt='Short Answer']")).click();
	            driver.findElement(By.xpath("//input[@id='question-text-input-0']")).sendKeys("Quiz Short Question");
	            driver.findElement(By.xpath("//input[@maxlength='100']")).sendKeys("Answer");
	            driver.findElement(By.xpath("//span[text()='Settings']")).click();
	            driver.findElement(By.xpath("//span[@class='icon-title'][normalize-space()='Submit']")).click();
	            test.pass("Quiz created: " + quizTitle);

	            String parentWindow = driver.getWindowHandle();
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

	            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='Start']"))).click();
	            WebElement enterName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='input-Name']")));
	            enterName.sendKeys("Sounder");
	            driver.findElement(By.xpath("//div[@id='Start Quiz']")).click();

	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@field-type='SHORT_TEXT']")))
	                    .sendKeys("Selenium short question quiz answer");
	            driver.findElement(By.xpath("//div[@id='Submit']")).click();

	            WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Submitted']")));
	            Assert.assertTrue(successMsg.isDisplayed(), "Quiz not submitted successfully");
	            test.pass("Quiz submitted successfully");

	            driver.switchTo().window(parentWindow);
	            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'popup')]//img[@alt='close']"))).click();
	            driver.navigate().refresh();

	            driver.findElement(By.xpath("//span[text()='Quizzes']")).click();
	            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='form-card-0']"))).click();
	            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Individual']"))).click();

	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='response-text']")));
	            String actualAnswer = driver.findElement(By.xpath("//p[@class='response-text']")).getText();
	            String expectedAnswer = "Selenium short question quiz answer";
	            Assert.assertEquals(actualAnswer, expectedAnswer, "Answers do not match!");

	            test.pass("Answer matched: " + actualAnswer);

	        } catch (Exception e) {
	            test.fail("Test failed: " + e.getMessage());
	            e.printStackTrace();
	        } finally {
	            driver.quit();
	            test.info("Browser closed");

	            extent.flush();
	            test.info("Report generated: quiz_report.html");
	        }
	            
	         // Extent - Open report automatically in Chrome browser only
	            File reportFile = new File("ExtentReports/FormReport.html");
	            String reportPath = reportFile.getAbsolutePath();

	            // Change path below if Chrome is installed in a different location
	            String chromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";

	            // Launch Chrome with the report file
	            Runtime.getRuntime().exec(new String[] { chromePath, reportPath });

	           
	            
	    }
} */
	    


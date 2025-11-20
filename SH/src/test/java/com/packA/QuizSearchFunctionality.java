package com.packA;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

public class QuizSearchFunctionality {
	
	
	@Test
	public void create_Quiz_Method() throws IOException {
	    
	    // Setup Extent Report
	    ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports/QuizReport.html");
	    ExtentReports extent = new ExtentReports();
	    extent.attachReporter(spark);
	    ExtentTest test = extent.createTest("SurveyHeart - Quiz Creation & Search Test");

	    WebDriver driver = new ChromeDriver();
	    test.info("Browser launched");

	    // Environment Info
	    String os = System.getProperty("os.name").toUpperCase();
	    String user = System.getProperty("user.name");
	    Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
	    String browserName = caps.getBrowserName();
	    String browserVersion = caps.getBrowserVersion();
	    extent.setSystemInfo("Executed By", user);
	    extent.setSystemInfo("OS", os);
	    extent.setSystemInfo("Browser", browserName);
	    extent.setSystemInfo("Version", browserVersion);

	    // Launch SurveyHeart
	    driver.get("https://surveyheart.com/app#login");
	    driver.manage().window().maximize();
	    driver.manage().deleteAllCookies();

	    // Sign in with Email
	    driver.findElement(By.xpath("//span[normalize-space()='Sign in using Email']")).click();
	    driver.findElement(By.xpath("//label[@id='label_field_Email']")).sendKeys("vilem14826@hosliy.com");
	    driver.findElement(By.id("Next")).click();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	    WebElement pwd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("label_field_Password")));
	    pwd.sendKeys("Automation@1");
	    driver.findElement(By.id("Sign in")).click();
	    test.pass("Signed in successfully");

	    // Close Dashboard popup
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Close']"))).click();

	    // Create two quizzes
	    String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
	    String quizTitle1 = "Selenium Quiz 1 - " + timeStamp;
	    String quizTitle2 = "Selenium Quiz 2 - " + timeStamp;

	    createQuiz(driver, wait, quizTitle1, test);
	    createQuiz(driver, wait, quizTitle2, test);

	    // Search for Quiz 1 and verify
	    WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search']")));
	    search.clear();
	    search.sendKeys(quizTitle1);

	    WebElement foundQuiz = wait.until(ExpectedConditions.visibilityOfElementLocated(
	        By.xpath("//p[@id='card-form-title' and text()='" + quizTitle1 + "']")));
	    Assert.assertEquals(foundQuiz.getText(), quizTitle1);
	    test.pass("Quiz 1 found and validated: " + quizTitle1);

	    driver.quit();
	    extent.flush();

	    // Open Report
	    File reportFile = new File("ExtentReports/QuizReport.html");
	    String chromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
	    Runtime.getRuntime().exec(new String[]{chromePath, reportFile.getAbsolutePath()});
	}

	// ====================== Helper Method ======================
	public void createQuiz(WebDriver driver, WebDriverWait wait, String title, ExtentTest test) {
	    try {
	    	driver.findElement(By.xpath("//span[text()='Quizzes']")).click();
	        driver.findElement(By.xpath("//span[text()='Create Quiz']")).click();
	        driver.findElement(By.xpath("//input[@maxlength='250']")).sendKeys(title);
	        driver.findElement(By.xpath("//span[text()='Add Question']")).click();
	        driver.findElement(By.xpath("//img[@alt='Short Answer']")).click();
	        driver.findElement(By.xpath("//input[@id='question-text-input-0']")).sendKeys("Quiz Short  Question");
	        driver.findElement(By.xpath("//input[@maxlength='100']")).sendKeys("Answer");
	        driver.findElement(By.xpath("//span[text()='Settings']")).click();
	        driver.findElement(By.xpath("//span[@class='icon-title'][normalize-space()='Submit']")).click();

	        WebElement closePopup = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//div[contains(@class,'popup')]//img[@alt='close']")));
	        closePopup.click();

	        test.pass("Quiz created: " + title);
	    } catch (Exception e) {
	        test.fail("Quiz creation failed: " + title + " - " + e.getMessage());
	    }
	}


}

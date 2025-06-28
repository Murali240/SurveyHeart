package com.Forms;

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

public class FormSearchFunctionalityTest {
	
	
	@Test
	public void verifyFormSearchFunctionality() throws IOException {

	 // Extent - Initialize Reporter
	    ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports/FormReport.html");
	    ExtentReports extent = new ExtentReports();
	    extent.attachReporter(spark);
	    ExtentTest test = extent.createTest("SurveyHeart – Form Creation and Search Test");

	    WebDriver driver = new ChromeDriver();
	    test.info("Browser launched");

	 // Get environment details
	    String os = System.getProperty("os.name").toUpperCase();
	    String user = System.getProperty("user.name");
	    Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
	    String browserName = caps.getBrowserName();
	    String browserVersion = caps.getBrowserVersion();

	    extent.setSystemInfo("Executed By", user);
	    extent.setSystemInfo("Operating System", os);
	    extent.setSystemInfo("Browser", browserName);
	    extent.setSystemInfo("Browser Version", browserVersion);

	    driver.get("https://surveyheart.com/app#login");
	    driver.manage().window().maximize();
	    driver.manage().deleteAllCookies();

	 // Login
	    driver.findElement(By.xpath("//span[normalize-space()='Sign in using Email']")).click();
	    driver.findElement(By.xpath("//label[@id='label_field_Email']")).sendKeys("vilem14826@hosliy.com");
	    driver.findElement(By.xpath("//div[@id='Next']")).click();

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	    WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='label_field_Password']")));
	    password.sendKeys("Automation@1");
	    driver.findElement(By.xpath("//div[@id='Sign in']")).click();
	    test.pass("Signed in successfully");

	 // Dashboard
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Close']"))).click();
	    

	    String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
	    String formTitle1 = "Selenium Form 1 - " + timeStamp;
	    String formTitle2 = "Selenium Form 2 - " + timeStamp;

	 // Create Form 1
	    createForm(driver, wait, formTitle1, test);
	    test.pass("Form 1 created : " + formTitle1);

	 // Create Form 2
	    createForm(driver, wait, formTitle2, test);
	    test.pass("Form 2 created : " + formTitle2);

	 // Search for Form 1 
	    WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search']")));
	    searchInput.clear();
	    searchInput.sendKeys(formTitle1);

	    WebElement formResult = wait.until(ExpectedConditions.visibilityOfElementLocated(
	        By.xpath("//p[@id='card-form-title' and text()='" + formTitle1 + "']")));
	    Assert.assertEquals(formResult.getText(), formTitle1, "Form 1 not found!");
	    test.pass("Form search successful: Found Form 1 – " + formTitle1);

	 // Close browser
	    driver.quit();
	    test.info("Browser closed");

	 // Flush report
	    extent.flush();

	 // Open Extent report automatically
	    File reportFile = new File("ExtentReports/FormReport.html");
	    String reportPath = reportFile.getAbsolutePath();
	    String chromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
	    Runtime.getRuntime().exec(new String[] { chromePath, reportPath });
	}

	
	// ====================== Helper Method ======================
	public void createForm(WebDriver driver, WebDriverWait wait, String formTitle, ExtentTest test) {
	    try {
	        driver.findElement(By.xpath("//span[text()='Create Form']")).click();
	        driver.findElement(By.xpath("//input[@maxlength='250']")).sendKeys(formTitle);
	        driver.findElement(By.xpath("//span[text()='Add Question']")).click();
	        driver.findElement(By.xpath("//img[@alt='Short Answer']")).click();
	        driver.findElement(By.xpath("//input[@id='question-text-input-0']")).sendKeys("Short Question");
	        driver.findElement(By.xpath("//span[text()='Settings']")).click();
	        driver.findElement(By.xpath("//span[@class='icon-title'][normalize-space()='Submit']")).click();

	        WebElement closePopup = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//div[contains(@class,'popup')]//img[@alt='close']")));
	        closePopup.click();
	        test.pass("Form created and share popup closed : " + formTitle);
	    } catch (Exception e) {
	        test.fail("Failed to create form : " + formTitle + " - " + e.getMessage());
	    }
	
	
	}

}

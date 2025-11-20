package com.packA;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

public class LoginwithMultipleData {
	
	
	// Method to capture screenshot with dynamic name using timestamp
	public static String takeScreenshot(WebDriver driver, String baseFileName) throws IOException {
	    TakesScreenshot ts = (TakesScreenshot) driver;
	    File src = ts.getScreenshotAs(OutputType.FILE);

	    // Generate timestamp string (e.g., 20250617_195512)
	    String timestamp = java.time.LocalDateTime.now()
	                     .format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

	    // Create full screenshot path with timestamp
	    String screenshotDir = "ExtentReports/screenshots/";
	    String screenshotName = baseFileName + "_" + timestamp + ".png";
	    File dest = new File(screenshotDir + screenshotName);
	    dest.getParentFile().mkdirs(); // Ensure directory exists

	    org.openqa.selenium.io.FileHandler.copy(src, dest);
	    return dest.getAbsolutePath();
	}
	

    @Test
    public void create_Form_Method() throws IOException {

        // Initialize Report
        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports/FormReport.html");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);
        ExtentTest test = extent.createTest("SurveyHeart - Data-Driven Email Login Test");

        // Get Excel file
        FileInputStream fis = new FileInputStream("C:\\Users\\mural\\Downloads\\LoginCredentials.xlsx");
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        // Loop through rows
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next(); // skip header

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            String email = row.getCell(0).getStringCellValue();
            String password = row.getCell(1).getStringCellValue();

            // Create a test node per login
            ExtentTest loginNode = test.createNode("Login Attempt for: " + email);

            WebDriver driver = new ChromeDriver();
            loginNode.info("Browser launched");

            // System info
            String user = System.getProperty("user.name");
            extent.setSystemInfo("Executed By", user);
            String os = System.getProperty("os.name").toUpperCase();
            extent.setSystemInfo("Operating System", os);
            Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
            extent.setSystemInfo("Browser", caps.getBrowserName());
            extent.setSystemInfo("Browser Version", caps.getBrowserVersion());

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("https://surveyheart.com/app#login");

            try {
                driver.findElement(By.xpath("//span[normalize-space()='Sign in using Email']")).click();
                driver.findElement(By.xpath("//label[@id='label_field_Email']")).sendKeys(email);
                driver.findElement(By.xpath("//div[@id='Next']")).click();

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
                WebElement passField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//label[@id='label_field_Password']")));
                passField.sendKeys(password);
                driver.findElement(By.xpath("//div[@id='Sign in']")).click();

                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Close']"))).click();
                loginNode.pass("Login success for: " + email);
                loginNode.info("After Login URL: " + driver.getCurrentUrl());

            } catch (Exception e) {
                loginNode.fail("Login failed for: " + email);
                String screenshotPath = takeScreenshot(driver, email.replaceAll("@", "_at_"));
                loginNode.addScreenCaptureFromPath(screenshotPath);
            }

            driver.quit();
        }

        workbook.close();
        extent.flush();

        // Open report automatically in Chrome
        File reportFile = new File("ExtentReports/FormReport.html");
        String reportPath = reportFile.getAbsolutePath();
        String chromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";

        Runtime.getRuntime().exec(new String[]{chromePath, reportPath});
        
        
        
    }
}
	
	
	
	
	
	
	
	
	
	/*
	   Before screenshot added code
		
		@Test
		public void create_Form_Method() throws IOException {
		
	 // Initialize Report
	    ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports/FormReport.html");
	    ExtentReports extent = new ExtentReports();
	    extent.attachReporter(spark);
	    ExtentTest test = extent.createTest("SurveyHeart Login Test using Eamil and Password");
    
	 // Get Excel file
	    FileInputStream fis = new FileInputStream(new String("C:\\Users\\mural\\Downloads\\LoginCredentials.xlsx"));
	    Workbook workbook = new XSSFWorkbook(fis);
	    Sheet sheet = workbook.getSheetAt(0);

	 // Loop through rows
	    Iterator<Row> rowIterator = sheet.iterator();
	    rowIterator.next(); // skip header
	
	    while (rowIterator.hasNext()) {
	    Row row = rowIterator.next();

	    String email = row.getCell(0).getStringCellValue();
	    String password = row.getCell(1).getStringCellValue();
	
	 // Log current credentials in report
	    test.info("Trying login with: " + email);
	
	 // Launch browser
	    WebDriver driver = new ChromeDriver();
	    test.info("Browser launched");

	 // System info
        String user = System.getProperty("user.name");
        extent.setSystemInfo("Executed By", user);
        String os = System.getProperty("os.name").toUpperCase();
        extent.setSystemInfo("Operating System", os);
        Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
        extent.setSystemInfo("Browser", caps.getBrowserName());
        extent.setSystemInfo("Browser Version", caps.getBrowserVersion());

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://surveyheart.com/app#login");

     // Login steps
        try {
            driver.findElement(By.xpath("//span[normalize-space()='Sign in using Email']")).click();
            driver.findElement(By.xpath("//label[@id='label_field_Email']")).sendKeys(email);
            driver.findElement(By.xpath("//div[@id='Next']")).click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            WebElement passField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//label[@id='label_field_Password']")));
            passField.sendKeys(password);
            driver.findElement(By.xpath("//div[@id='Sign in']")).click();

            // Validate login
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Close']"))).click();
            test.pass("Login success for: " + email);
            String afterLoginURL=driver.getCurrentUrl();
            test.info("Before login URL : https://surveyheart.com/app#login");
            test.pass("After successfully login URL :"+afterLoginURL );

        } catch (Exception e) {
            test.fail("Login failed for: " + email);
        }

          driver.quit();
          workbook.close();
      }

    //  Extent - Write Report and Flush
	    extent.flush();
 	        
	 // Extent - Open report automatically in Chrome browser only
	    File reportFile = new File("ExtentReports/FormReport.html");
	    String reportPath = reportFile.getAbsolutePath();
	
	 // Change path below if Chrome is installed in a different location
	    String chromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
	
	 // Launch Chrome with the report file
	    Runtime.getRuntime().exec(new String[] { chromePath, reportPath });
    
    
	    
   }
		
*/



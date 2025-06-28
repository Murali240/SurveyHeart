package com.packA;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FormwithResponseSample {
	
	@Test
	public void form_Creation_With_Response_Method() {
		
		 WebDriver driver = new ChromeDriver();
	     driver.get("https://surveyheart.com/app#login");
	     driver.manage().window().maximize();
	     driver.manage().deleteAllCookies();
	     
	     // Step 1: Click "Sign in using Email"
	     driver.findElement(By.xpath("//span[normalize-space()='Sign in using Email']")).click();
	     driver.findElement(By.xpath("//label[@id='label_field_Email']")).sendKeys("vilem14826@hosliy.com");
	     driver.findElement(By.xpath("//div[@id='Next']")).click();
	     
	     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	     WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='label_field_Password']")));
	     
	     password.sendKeys("Automation@1");
	     driver.findElement(By.xpath("//div[@id='Sign in']")).click();
	     
	     
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Close']"))).click();
	     System.out.println("Dashboard is displaying successfully");
	     
	     
	     // Generate dynamic form title using timestamp
	     String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
	     String dynamicFormTitle = "Selenium Form - " + timeStamp;
	

	     //Form Creating
	     driver.findElement(By.xpath("//span[text()='Create Form']")).click();
	     driver.findElement(By.xpath("//input[@maxlength='250']")).sendKeys(dynamicFormTitle);
	     driver.findElement(By.xpath("//span[text()='Add Question']")).click();
	     driver.findElement(By.xpath("//img[@alt='Short Answer']")).click();
	     driver.findElement(By.xpath("//input[@id='question-text-input-0']")).sendKeys("Short Question");
	     driver.findElement(By.xpath("//span[text()='Settings']")).click();
	     driver.findElement(By.xpath("//span[@class='icon-title'][normalize-space()='Submit']")).click();
	     System.out.println("Form created successfully");
	     
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

	     // Click "Start" button
	     WebElement startButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='Start']")));
	     startButton.click();
	     
	     // Fill response for the short question
	     WebElement answerBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@field-type='SHORT_TEXT']")));
	     answerBox.sendKeys("Selenium short question form response");
	     driver.findElement(By.xpath("//div[@id='Submit']")).click();
	     
	     // Verify success message after Form submit
	     WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Submitted']")));
	     if (successMsg.isDisplayed()) {
	         System.out.println("Form submitted page verified successfully");
	     }
	     
	     // Switch back to original tab
	     driver.switchTo().window(parentWindow);

	     // Refresh the page to see updated response count
	     driver.navigate().refresh();
	     
	     // Identifying the created Form card
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='list-card-title-0']//p[@id='card-form-title']")));
	        
	     //Click on 'View Responses' button
	     wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='form-card-0']"))).click();
	     

	     // Check if the response is displayed (modify according to your response layout)
	     WebElement responseText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='text-response-card']")));
	     String actualAnswer = responseText.getText();
	     String expextedAnswer="Selenium short question form response";
	     
	     // Assert that actual and expected values match
	     Assert.assertEquals(actualAnswer, expextedAnswer, "Response text does not match expected value");
	     System.out.println("Entered response text is matched in Overview screen: " + actualAnswer);
	    
	     //close browser
	     System.out.println("Automated chrome browser has closed successfully");
	     driver.quit();
	}
}

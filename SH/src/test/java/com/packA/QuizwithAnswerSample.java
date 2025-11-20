package com.packA;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class QuizwithAnswerSample {
	
	@Test
	public void quiz_Creation_With_Answer_Method() {
		
		 WebDriver driver = new ChromeDriver();
	     driver.get("https://surveyheart.com/app#login");
	     driver.manage().window().maximize();
	     driver.manage().deleteAllCookies();
	     
	  // Click "Sign in using Email"
	     driver.findElement(By.xpath("//span[normalize-space()='Sign in using Email']")).click();
	     driver.findElement(By.xpath("//label[@id='label_field_Email']")).sendKeys("vilem14826@hosliy.com");
	     driver.findElement(By.xpath("//div[@id='Next']")).click();
	     
	  // Enter Password
	     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	     WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='label_field_Password']")));
	     password.sendKeys("Automation@1");
	     driver.findElement(By.xpath("//div[@id='Sign in']")).click();
	     
	  // Dashboard     
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Close']"))).click();
	     System.out.println("Dashboard is displaying successfully");
	     
	     
	  // Generate dynamic form title using timestamp
	     String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
	     String dynamicQuizTitle = "Selenium Quiz - " + timeStamp;
	     
	     
	  // Creating Quiz
	     driver.findElement(By.xpath("//span[text()='Quizzes']")).click();
	     driver.findElement(By.xpath("//span[text()='Create Quiz']")).click();
	     driver.findElement(By.xpath("//input[@maxlength='250']")).sendKeys(dynamicQuizTitle);
	     driver.findElement(By.xpath("//span[text()='Add Question']")).click();
	     driver.findElement(By.xpath("//img[@alt='Short Answer']")).click();
	     driver.findElement(By.xpath("//input[@id='question-text-input-0']")).sendKeys("Quiz Short Question");
	     driver.findElement(By.xpath("//input[@maxlength='100']")).sendKeys("Answer");
	     driver.findElement(By.xpath("//span[text()='Settings']")).click();
	     driver.findElement(By.xpath("//span[@class='icon-title'][normalize-space()='Submit']")).click();
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
	     
	     WebElement enterName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='input-Name']")));
	     enterName.sendKeys("Sounder");
	     driver.findElement(By.xpath("//div[@id='Start Quiz']")).click();
	     
	     
	  // Fill answer for the short question
	     WebElement answerBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@field-type='SHORT_TEXT']")));
	     answerBox.sendKeys("Selenium short question quiz answer");
	     driver.findElement(By.xpath("//div[@id='Submit']")).click();
	     
	  // Verify success message after Quiz submit
	     WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Submitted']")));
	     if (successMsg.isDisplayed()) {
	         System.out.println("Quiz submitted page verified successfully");
	     }
	     
	     
	  // Switch back to original tab
	     driver.switchTo().window(parentWindow);

	  // Wait for Share popup to appear and close it
	     WebElement closeSharePopup = wait.until(ExpectedConditions.elementToBeClickable(
	         By.xpath("//div[contains(@class,'popup')]//img[@alt='close']")));
	     closeSharePopup.click();
	     System.out.println("Share popup closed successfully");
	     
	     
	  // Refresh the page to see updated response count
	     driver.navigate().refresh();
	     
	     
	  // Navigate to Quizzes tab
	     WebElement quizzesTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Quizzes']")));
	     quizzesTab.click();
	     
	     
	  // Identifying the created Quiz card
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='list-card-title-0']//p[@id='card-form-title']")));
	        
	  // Click on 'View Responses' button
	     wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='form-card-0']"))).click();
	     

	  // Check if the response is displayed (modify according to your response layout)
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Individual']"))).click();
	     String actualAnswer = driver.findElement(By.xpath("//p[@class='response-text']")).getText();
	     String expextedAnswer="Selenium short question quiz answer";
	     
	  // Assert that actual and expected values match
	     Assert.assertEquals(actualAnswer, expextedAnswer, "Response text does not match expected value");
	     System.out.println("Entered answer text is matched in individual screen: " + actualAnswer);
	     
	  // Closing browser
	     driver.quit();
	     
	     
}
}

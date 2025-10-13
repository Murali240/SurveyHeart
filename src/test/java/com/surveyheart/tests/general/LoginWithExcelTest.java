package com.surveyheart.tests.general;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.surveyheart.base.BaseTest;
import com.surveyheart.utilities.ExcelUtil;
import com.surveyheart.utilities.ExtentManager;


@Listeners(com.surveyheart.listeners.TestListener.class)
public class LoginWithExcelTest extends BaseTest {

	
	@Test(dataProvider = "loginCredentials")
	public void validateLoginUsingExcel(String email, String password) throws Exception {
	    try {
	    	ExtentManager.getTest().info("Email from Excel: " + email);
	    	ExtentManager.getTest().info("Password from Excel: " + password);

	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Sign in using Email']"))).click();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("label_field_Email"))).sendKeys(email);
	        driver.findElement(By.xpath("//div[@id='Next']")).click();

	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("label_field_Password"))).sendKeys(password);
	        driver.findElement(By.xpath("//div[@id='Sign in']")).click();

	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Close']"))).click();

	        ExtentManager.getTest().pass("Login successful for: " + email);
	    } catch (Exception e) {
	    	ExtentManager.getTest().fail("Login failed for: " + email + " - " + e.getMessage());
	        throw e;   // ✅ Let TestNG know this test failed
	    }
	}
		
	
	@DataProvider(name = "loginCredentials")
	public Object[][] loginCredentials() {
	    return ExcelUtil.getAllLoginCredentials("LoginCredentials.xlsx", "Sheet1");
	}





}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
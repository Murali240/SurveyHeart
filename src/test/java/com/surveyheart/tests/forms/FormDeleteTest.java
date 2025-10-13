package com.surveyheart.tests.forms;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.surveyheart.base.BaseTest;
import com.surveyheart.enums.QuestionType;
import com.surveyheart.pages.FormBuilderPage;
import com.surveyheart.pages.FormDashboardPage;
import com.surveyheart.pages.FormSettingsPage;
import com.surveyheart.pages.SharePopupPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Smoke test to verify form deletion in Form Dashboard */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class FormDeleteTest extends BaseTest {
	
	    @Test (groups = "sanity", priority = 2)
	    public void verifyFormDeletion()  {
	    	
	     // Login page - Initialize the Login Page object with the current WebDriver instance
			SurveyHeartLoginPage loginPage = new SurveyHeartLoginPage(driver);
					loginPage.clickSignInUsingEmail();
					loginPage.enterEmail("gofaw36836@pacfut.com");
					loginPage.clickNext();
					loginPage.enterPassword("Automation@1");
					loginPage.clickSignIn();
					loginPage.closeFeatureSpotlightIfPresent();              // In case popup appears after login
					ExtentManager.getTest().pass("Login successful with email and password.");
					
		 // Form Dashboard - Initialize the Form Dashboard Page object with the current WebDriver instance
			FormDashboardPage formdashboard = new FormDashboardPage(driver);
					 formdashboard.refreshPage();
					 formdashboard.clickCreateFormButton();       // Click on +Create Form button
	    	
		 // Form Builder - Initialize the Form builder object with the current WebDriver instance
	        FormBuilderPage builder = new FormBuilderPage(driver);

			     // Enter Form title in Builder screen
			        String dynamicFormTitle = "FormDeletion " + System.currentTimeMillis();
			        builder.enterFormTitle(dynamicFormTitle); 
			
			     // 1. Short Answer
			        builder.clickInitialAddQuestionButton();
			        builder.selectQuestionType(QuestionType.SHORT_ANSWER);
			        builder.enterQuestionTitle(0, "What is your name?");

		 // Form Settings - Initialize the Form settings object with the current WebDriver instance
		   	FormSettingsPage settings = new FormSettingsPage(driver);
					settings.clickSettingsButton();
					settings.clickControlTab();
					settings.enableAllowMultipleResponses(true); 
					ExtentManager.getTest().pass("'Allow Multiple Response' checkbox enabled successfully.");
					settings.clickSubmitButton();
					        
						    
		// SharePopup - Initialize the Share popup object with the current WebDriver instance
		   SharePopupPage sharePopup = new SharePopupPage(driver);
		   			sharePopup.clickCloseIcon();                                    

		   		 // Come back to Form Dashboard
		   			formdashboard.refreshPage();
		   			
		   		 // Capture the form title before deletion for verification
		   			String deletedFormTitle = dynamicFormTitle;
		   			
				    formdashboard.clickMoreOptionsForFirstForm();
				    formdashboard.clickDeleteButton();
				    formdashboard.clickConfirmDeleteButton();
				    
				   
				 // Wait until the deleted form is no longer visible on the dashboard
				    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
				    wait.until(ExpectedConditions.invisibilityOfElementLocated(
				        By.xpath("//p[@id='card-form-title' and text()='" + deletedFormTitle + "']")));

				 // After wait, double-check no such form exists
				    List<WebElement> deletedForm = driver.findElements(
				        By.xpath("//p[@id='card-form-title' and text()='" + deletedFormTitle + "']"));

				 // Assert it is completely gone
				    Assert.assertEquals(deletedForm.size(), 0, "Form still exists on the dashboard after deletion!");

				 // Log deletion success
				    ExtentManager.getTest().pass("Form was deleted successfully : " + deletedFormTitle);
	
				    
				    
	}

}


/**
Scenario: Smoke test to verify form deletion in Form Dashboard.

Steps:
1. Login, create a new form with a dynamic title and a Short Answer question titled "What is your name?", enable 'Allow Multiple Responses', and submit the form.
2. Delete the form from the Form Dashboard and wait until it is no longer visible.
3. Verify that the deleted form is completely removed from the Form Dashboard.

Expected Result:
- The form is successfully deleted and no longer appears in the list of forms.
*/



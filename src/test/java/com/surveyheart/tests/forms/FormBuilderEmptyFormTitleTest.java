package com.surveyheart.tests.forms;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.surveyheart.base.BaseTest;
import com.surveyheart.enums.QuestionType;
import com.surveyheart.pages.FormBuilderPage;
import com.surveyheart.pages.FormDashboardPage;
import com.surveyheart.pages.FormSettingsPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Test class to verify error message for empty form title in Form Builder */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class FormBuilderEmptyFormTitleTest extends BaseTest {     
	
	    @Test 
	    public void verifyErrorMessageForEmptyFormTitle() {
	    	
	     // Login page - Initialize the Login Page object with the current WebDriver instance
			SurveyHeartLoginPage loginPage = new SurveyHeartLoginPage(driver);
							loginPage.clickSignInUsingEmail();
							loginPage.enterEmail("gofaw36836@pacfut.com");
							loginPage.clickNext();
							loginPage.enterPassword("Automation@1");
							loginPage.clickSignIn();
							loginPage.closeFeatureSpotlightIfPresent();          // In case popup appears after login
							ExtentManager.getTest().pass("Login successful with email and password.");
					
		 // Form Dashboard - Initialize the Form Dashboard Page object with the current WebDriver instance
			FormDashboardPage formdashboard = new FormDashboardPage(driver);
							formdashboard.refreshPage();
							formdashboard.clickCreateFormButton();          // Click on +Create Form button
	    	
		 // Form Builder - Initialize the Form builder object with the current WebDriver instance
	        FormBuilderPage builder = new FormBuilderPage(driver);

					     // Don't enter Form title in Builder screen
					     // String dynamicFormTitle = "FormEditWithoutResponse " + System.currentTimeMillis();
					     // builder.enterFormTitle(dynamicFormTitle); 
		        
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
							
							String actualMessage=settings.getFormTitleRequiredMessage().getText();
							String expectedMessage="TITLE IS REQUIRED";
	
					     // Assert that Form title required warning popup 
						    Assert.assertEquals(actualMessage, expectedMessage, "Form title required does not displayed");
						    ExtentManager.getTest().pass("Captured error message for empty form title: <b>" + actualMessage + "</b>");

						
						    
	}				        

}


/**
Scenario: Test class to verify the error message for an empty form title in Form Builder.

Steps:
1. Login and click '+Create Form' without entering a form title.
2. Add a Short Answer question, enable 'Allow Multiple Responses' in Settings, and click Submit.
3. Capture and verify that the error message displays "TITLE IS REQUIRED".

Expected Result:
- The form shows the error message "TITLE IS REQUIRED" when the title is empty.
*/




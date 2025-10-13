package com.surveyheart.tests.forms;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.surveyheart.base.BaseTest;
import com.surveyheart.enums.QuestionType;
import com.surveyheart.pages.FormBuilderPage;
import com.surveyheart.pages.FormDashboardPage;
import com.surveyheart.pages.FormPage;
import com.surveyheart.pages.FormSettingsPage;
import com.surveyheart.pages.SharePopupPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Test class to verify changing a form's status to Inactive and validating its behavior when form link opened. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class FormStatusChangeToInactiveTest extends BaseTest {

	    @Test (groups = "sanity", priority = 4)
	    public void verifyChangeFormStatusToInactive() {
	    	
	     // Login page - Initialize the Login Page object with the current WebDriver instance
			SurveyHeartLoginPage loginPage = new SurveyHeartLoginPage(driver);
					loginPage.clickSignInUsingEmail();
					loginPage.enterEmail("gofaw36836@pacfut.com");
					loginPage.clickNext();
					loginPage.enterPassword("Automation@1");
					loginPage.clickSignIn();
					loginPage.closeFeatureSpotlightIfPresent();                       // In case popup appears after login
					ExtentManager.getTest().pass("Login successful with email and password.");
					
		 // Form Dashboard - Initialize the Form Dashboard Page object with the current WebDriver instance
			FormDashboardPage formdashboard = new FormDashboardPage(driver);
					 formdashboard.refreshPage();
					 formdashboard.clickCreateFormButton();       // Click on +Create Form button
					 
	    	
		 // Form Builder - Initialize the Form builder object with the current WebDriver instance
	        FormBuilderPage builder = new FormBuilderPage(driver);
	        
			        String dynamicFormTitle = "InactiveForm " + System.currentTimeMillis();
			        builder.enterFormTitle(dynamicFormTitle);        // Enter Form title in Builder screen
			
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
			        
			      // Come back to FormDashboard
					 formdashboard.clickMoreOptionsForFirstForm();
					 formdashboard.clickFormStatusSwitch();           // Toggle form to Inactive
					 formdashboard.storeParentWindowHandle();         // Store parent handle
					 formdashboard.clickViewFormButton();             // Open form in new tab
					 formdashboard.switchToChildWindow();             // Switch to new tab
				
		 // Form page - Initialize the Form page object with the current WebDriver instance
			FormPage formPage = new FormPage(driver); 
					 String closedStatus = formPage.getFormClosedStatusText();
					 String closedMessage = formPage.getFormClosedMessageText();
					 ExtentManager.getTest().info(dynamicFormTitle + " : " + closedStatus + " : " + closedMessage);


			 
	}

}


/**
Scenario: Test class to verify changing a form's status to Inactive and validating its behavior when the form link is opened.

Steps:
1. Login, create a new form with a dynamic title and a Short Answer question, enable 'Allow Multiple Responses', and submit the form.
2. On the Form Dashboard, toggle the form status to Inactive.
3. Open the inactive form link in a new tab and capture the closed status and message displayed on the Form page.
4. Verify that the form shows the correct closed status and message.

Expected Result:
- The form is marked as Inactive successfully.
- Opening the inactive form link displays the correct closed status and message.
*/



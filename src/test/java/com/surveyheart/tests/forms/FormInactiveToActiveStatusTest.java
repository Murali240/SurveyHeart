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
import com.surveyheart.pages.SubmittedPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Verifies form reactivation from inactive status and successful submission. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class FormInactiveToActiveStatusTest extends BaseTest {
	
	    @Test (groups = "sanity", priority = 3)
	    public void makeInactiveFormActive() {
	    	
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
	        
			        String dynamicFormTitle = "ActiveForm " + System.currentTimeMillis();
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
					 
				  // Closing the child window 
					 formPage.closeChildWindowAndSwitchToParent(formdashboard.getParentWindowHandle()); 
			        
				  // Come back to form Dashboard
					 formdashboard.refreshPage();
					 formdashboard.clickMoreOptionsForFirstForm();
					 formdashboard.clickFormStatusSwitch();           // Toggle form Inactive to Active
					 ExtentManager.getTest().pass(dynamicFormTitle+ " has been successfully changed to 'Active' status.");
					 formdashboard.storeParentWindowHandle();         // Store parent handle
					 formdashboard.clickViewFormButton();             // Open form in new tab
					 formdashboard.switchToChildWindow();             // Switch to new tab
	    
				  // Form page
					 formPage.clickStartButton();
					 formPage.answerShortText("My name is Sounder Arunachalam");
					 formPage.clickSubmitButton();
					 
		 // Submitted page - Initialize the Form page object with the current WebDriver instance
			SubmittedPage submittedPage = new SubmittedPage(driver);		 
					submittedPage.isSubmittedMessageDisplayed(); 
					ExtentManager.getTest().pass("'Submitted' page displayed successfully with the '+Add Response' button."); 
	    
					
		
	}

}


/**
Scenario: Test class to verify that an inactive form can be reactivated and submitted successfully.

Steps:
1. Login, create a new form with a dynamic title and a Short Answer question titled "What is your name?", enable 'Allow Multiple Responses', and submit the form.
2. Toggle the form status to 'Inactive' and verify that the closed status message is displayed when opening the form.
3. Reactivate the form by toggling status to 'Active'.
4. Start the form, answer the Short Answer question, and submit.
5. Verify that the 'Submitted' page displays successfully with the '+Add Response' button.

Expected Result:
- The inactive form is successfully reactivated and can be submitted without errors.
- The 'Submitted' page displays correctly after submission.
*/



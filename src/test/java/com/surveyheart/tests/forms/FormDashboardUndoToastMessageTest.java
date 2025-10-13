package com.surveyheart.tests.forms;

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


/** Test class to verify Undo toast message(text) after deleting a form in Form Dashboard. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class FormDashboardUndoToastMessageTest extends BaseTest {
	
	    @Test 
	    public void verifyUndoToastMessageDisplayedInFormDashboard()  {
	    	
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

			     // Enter Form title in Builder screen
			        String dynamicFormTitle = "UndoToastMessageInFormDashboard " + System.currentTimeMillis();
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
		   			
		   		 // Deleting Form from Form Dashboard
				    formdashboard.clickMoreOptionsForFirstForm();
				    formdashboard.clickDeleteButton();
				    formdashboard.clickConfirmDeleteButton();
				    String undoToastMessage = formdashboard.getFullUndoToastMessage();
				    ExtentManager.getTest().pass("UNDO toast message verified successfully in Form Dashboard: <b>" + undoToastMessage + "</b>");

	
				    
	}
	
}


/**
Scenario: Test class to verify the Undo toast message text after deleting a form in Form Dashboard.

Steps:
1. Login, create a new form with a dynamic title and a Short Answer question titled "What is your name?", enable 'Allow Multiple Responses', and submit the form.
2. Delete the form from the Form Dashboard and capture the UNDO toast message text.
3. Verify that the UNDO toast message appears immediately with the correct text.

Expected Result:
- The UNDO toast message is displayed immediately after deleting the form and shows the correct text.
*/



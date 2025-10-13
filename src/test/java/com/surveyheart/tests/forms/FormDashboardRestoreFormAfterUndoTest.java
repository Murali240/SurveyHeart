package com.surveyheart.tests.forms;

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


/** Test class to verify that a deleted form is restored in Form Dashboard after clicking UNDO toast button. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class FormDashboardRestoreFormAfterUndoTest extends BaseTest {
	
	    @Test 
	    public void verifyDeletedFormRestoredAfterUndoInFormDashboard()  {
	    	
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
			        String dynamicFormTitle = "UndoButtonInFormDashboard " + System.currentTimeMillis();
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
		   			
		   		 // Deleting form
				    formdashboard.clickMoreOptionsForFirstForm();
				    formdashboard.clickDeleteButton();
				    formdashboard.clickConfirmDeleteButton();
				    String undoToastMessage = formdashboard.getFullUndoToastMessage();
				    ExtentManager.getTest().pass("UNDO toast message verified successfully in Form Dashboard: <b>" + undoToastMessage + "</b>");

				 // Click on UNDO Toast message
				    formdashboard.clickUndoToastButton();
				    ExtentManager.getTest().pass("Successfully clicked on <b>Undo</b> toast button");
				    formdashboard.refreshPage();
				    String actualFormTiltle = formdashboard.getFirstFormCardTitle();
				    String expectedFormTitle=dynamicFormTitle;
	
				 // Assert that actualFormTitle and expectedFormTitle 
				    Assert.assertEquals(actualFormTiltle, expectedFormTitle, "Actual form title does not match expected title");
				    ExtentManager.getTest().pass("Deleted form was successfully restored in Form Dashboard: <b>" + dynamicFormTitle + "</b>");

	
				    
	}
	
}


/**
Scenario: Test class to verify that a deleted form is restored in Form Dashboard 
          after clicking the UNDO toast button.

Steps:
1. Login, create a new form with a dynamic title and a Short Answer question titled "What is your name?", enable 'Allow Multiple Responses', and submit the form.
2. Delete the form from the Form Dashboard and verify that the UNDO toast message appears.
3. Click the UNDO button, refresh the dashboard, and confirm that the deleted form is restored with the correct title.

Expected Result:
- The deleted form is successfully restored in the Form Dashboard after clicking the UNDO toast button.
*/


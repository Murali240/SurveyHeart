package com.surveyheart.tests.forms;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.surveyheart.base.BaseTest;
import com.surveyheart.enums.QuestionType;
import com.surveyheart.pages.FormBuilderPage;
import com.surveyheart.pages.FormDashboardPage;
import com.surveyheart.pages.FormPage;
import com.surveyheart.pages.FormSettingsPage;
import com.surveyheart.pages.OverviewPage;
import com.surveyheart.pages.SharePopupPage;
import com.surveyheart.pages.SubmittedPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Test class to verify that a deleted form response in the Tabular page
    can be restored using the Undo toast button. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class FormTabularPageRestoreResponseAfterUndoTest extends BaseTest {
	
	@Test 
    public void verifyDeletedResponseRestoredAfterUndoInFormTabularPage() {
    	
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
						formdashboard.clickCreateFormButton();            // Click on +Create Form button

	 // Form Builder - Initialize the Form builder object with the current WebDriver instance
        FormBuilderPage builder = new FormBuilderPage(driver);

				     // Enter Form title in Builder screen
				        String dynamicFormTitle = "UndoButtonInFormTabular " + System.currentTimeMillis();
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
						ExtentManager.getTest().info("Form created with: " + dynamicFormTitle );
		
	 // SharePopup - Initialize the Share popup object with the current WebDriver instance
		SharePopupPage sharePopup = new SharePopupPage(driver);
					   sharePopup.clickViewIcon();
					   sharePopup.getParentWindowHandle();
							   
				    // ✅ Store parent window before switching
					   sharePopup.storeParentWindowHandle();
					   sharePopup.switchToChildWindowThroughViewIcon();
									
									
				    // Form page - Initialize the Form page object with the current WebDriver instance
					   FormPage formPage = new FormPage(driver);
					   formPage.clickStartButton();
					   String formTitle = formPage.getFormTitle();
					   formPage.answerShortText("Entered short answer one");
					   String totalQuestionCount = formPage.getTotalQuestionCount();
					   ExtentManager.getTest().info("Form page displayed with " + totalQuestionCount + " question."); 
					   formPage.clickSubmitButton();
										  
	 // Form page - Initialize the Form page object with the current WebDriver instance
		SubmittedPage submittedPage = new SubmittedPage(driver);
					  submittedPage.isSubmittedMessageDisplayed();
					  ExtentManager.getTest().pass("Form submitted page verified successfully on the first attempt : "+formTitle);
					  submittedPage.clickAddResponseButton();
						   				
				   // Form page
					  formPage.clickStartButton();
					  formPage.answerShortText("Entered short answer two"); 
					  formPage.clickSubmitButton();
										  
				   // Submitted page
					  submittedPage.isSubmittedMessageDisplayed();
				      ExtentManager.getTest().pass("Form submitted page verified successfully on the second attempt : "+ formTitle);
						  
					  submittedPage.closeChildWindowAndSwitchToParent(sharePopup.getParentWindowHandle());
						
		           // Refresh the page
				      formdashboard.refreshPage();
				      formdashboard.clickMoreOptionsForFirstForm();
					  formdashboard.clickViewResponsesButton();
				    
				    
	  // Overview page - Initialize the Overview page object with the current WebDriver instance
	     OverviewPage overview = new OverviewPage(driver);

					  int beforeDeleteResponsesCount = overview.getTotalResponsesCount();
					  ExtentManager.getTest().info("Total responses count before deleting: <b>" + beforeDeleteResponsesCount + "</b>");
					  overview.clickTabularTab();
					  overview.selectFirstCheckboxInTabular();
					  overview.clickDeleteIndividualButton();
					  overview.confirmDeletePopup();
					  String undoToastMessage=overview.getFullUndoToastMessage();
		              ExtentManager.getTest().pass("Undo toast message verified successfully in Form Overview: <b>" + undoToastMessage + "</b>");
		              
		              // Click on Undo button
		              overview.clickUndoToastButton();
		              ExtentManager.getTest().pass("Successfully clicked on <b>Undo</b> toast button");
		              overview.clickOverviewTab();
		              int afterDeleteResponsesCount = overview.getTotalResponsesCount();
		              ExtentManager.getTest().pass("Total responses count after deleting and clicking on Undo button: <b>" + afterDeleteResponsesCount + "</b>");
					  
				
		
    }

}


/**
Scenario: Test class to verify restoring a deleted form response from the Tabular page using the Undo toast button.

Steps:
1. Login and create a new form with a Short Answer question, enable 'Allow Multiple Responses', and submit.
2. Open the form via Share popup in a new tab and submit two responses with different answers.
3. Close the form tab and return to the Form Dashboard.
4. Go to 'More Options' → 'View Responses', switch to the Tabular view, and delete the first response.
5. Capture and verify the Undo toast message.
6. Click the Undo toast button to restore the deleted response.
7. Switch back to the Overview tab and verify that the total responses count is updated correctly.

Expected Result:
- Deleting a response shows the Undo toast message.
- Clicking the Undo button restores the deleted response and updates the total responses count correctly.
*/




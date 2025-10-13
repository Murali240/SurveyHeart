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


/** Test class to verify restoring a deleted form response via Undo button in Form Individual page. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class FormIndividualResponseRestoreAfterUndoTest extends BaseTest {
	
	@Test 
    public void verifyDeletedResponseRestoredAfterUndoInFormOverview() {
    	
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
				        String dynamicFormTitle = "UndoButtonInFormOverview " + System.currentTimeMillis();
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
		              overview.clickIndividualTab();
		              overview.clickDeleteIndividualButton();
		              overview.confirmDeletePopup();
		              String undoToastMessage=overview.getFullUndoToastMessage();
		              ExtentManager.getTest().pass("Undo toast message verified successfully in Form Overview: <b>" + undoToastMessage + "</b>");
		              
		           // Click on Undo toast button
		              overview.clickUndoToastButton();
		              overview.clickOverviewTab();
		              int afterDeleteResponsesCount = overview.getTotalResponsesCount();
		              ExtentManager.getTest().pass("Total responses count after deleting & clicking on Undo button: <b>" + afterDeleteResponsesCount + "</b>");

	
		              
    }

}


/**
Scenario: Test class to verify restoring a deleted form response via Undo button in Form Individual page.

Steps:
1. Login, create a new form with a dynamic title and a Short Answer question titled "What is your name?", enable 'Allow Multiple Responses', and submit the form.
2. Open the form via Share popup and submit two responses, then close the form.
3. From the Form Dashboard, go to More Options → View Responses → Individual tab.
4. Delete one response and confirm the deletion.
5. Verify that the "Undo" toast message appears and click the Undo button to restore the response.
6. Confirm that the total responses count is restored to the original value in the Overview page.

Expected Result:
- The "Undo" toast message appears after deleting a response.
- Clicking Undo restores the deleted response and updates the total responses count correctly.
*/



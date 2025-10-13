package com.surveyheart.tests.forms;

import org.testng.Assert;
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


/** Test class to verify a form can accept and submit more than ten responses successfully. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class FormAddMoreThanTenResponsesTest extends BaseTest {
	
	@Test 
    public void verifyFormCanSubmitMoreThanTenResponses() {
    	
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
				        String dynamicFormTitle = "MoreThanTenResponsesForm " + System.currentTimeMillis();
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
					   formPage.answerShortText("Entered first response");
					   String totalQuestionCount = formPage.getTotalQuestionCount();
					   ExtentManager.getTest().info("Quiz page displayed with " + totalQuestionCount + " question.");
					   formPage.clickSubmitButton();
					   ExtentManager.getTest().pass("First response submitted successfully: " +formTitle);
										  
	 // Submitted page - Initialize the Submitted page object with the current WebDriver instance
		SubmittedPage submittedPage = new SubmittedPage(driver);
					  submittedPage.isSubmittedMessageDisplayed();
					  submittedPage.clickAddResponseButton();
					
					  
				   // Form page for 2nd attempting 
					  formPage.clickStartButton();
					  formPage.answerShortText("Entered second response"); 
					  formPage.clickSubmitButton();
					  ExtentManager.getTest().pass("Second response submitted successfully.");
										  
				   // Submitted page
					  submittedPage.isSubmittedMessageDisplayed();
				      submittedPage.clickAddResponseButton();
				      
				      
				   // Form page for 3rd attempting 
					  formPage.clickStartButton();
					  formPage.answerShortText("Entered third response"); 
					  formPage.clickSubmitButton();
					  ExtentManager.getTest().pass("Third response submitted successfully.");
										  
				   // Submitted page
					  submittedPage.isSubmittedMessageDisplayed();
					  submittedPage.clickAddResponseButton();
	
				      
				   // Form page for 4th attempting 
					  formPage.clickStartButton();
					  formPage.answerShortText("Entered fourth response"); 
					  formPage.clickSubmitButton();
					  ExtentManager.getTest().pass("Fourth response submitted successfully.");
											  
				   // Submitted page
					  submittedPage.isSubmittedMessageDisplayed();
					  submittedPage.clickAddResponseButton();
					  
					  
				   // Form page for 5th attempting 
					  formPage.clickStartButton();
					  formPage.answerShortText("Entered fifth response"); 
					  formPage.clickSubmitButton();
					  ExtentManager.getTest().pass("Fifth response submitted successfully.");
											  
				   // Submitted page
					  submittedPage.isSubmittedMessageDisplayed();
					  submittedPage.clickAddResponseButton();
					  
					  
				   // Form page for 6th attempting 
					  formPage.clickStartButton();
					  formPage.answerShortText("Entered sixth response"); 
					  formPage.clickSubmitButton();
					  ExtentManager.getTest().pass("Sixth response submitted successfully.");
											  
				   // Submitted page
					  submittedPage.isSubmittedMessageDisplayed();
					  submittedPage.clickAddResponseButton();
					  
					  
				   // Form page for 7th attempting 
					  formPage.clickStartButton();
					  formPage.answerShortText("Entered seventh response"); 
					  formPage.clickSubmitButton();
					  ExtentManager.getTest().pass("Seventh response submitted successfully.");
											  
				   // Submitted page
					  submittedPage.isSubmittedMessageDisplayed();
					  submittedPage.clickAddResponseButton();
					  
					  
				   // Form page for 8th attempting 
					  formPage.clickStartButton();
					  formPage.answerShortText("Entered eighth response"); 
					  formPage.clickSubmitButton();
					  ExtentManager.getTest().pass("Eighth response submitted successfully.");
											  
				   // Submitted page
					  submittedPage.isSubmittedMessageDisplayed();
					  submittedPage.clickAddResponseButton();
					  
					  
				   // Form page for 9th attempting 
					  formPage.clickStartButton();
					  formPage.answerShortText("Entered ninth response"); 
					  formPage.clickSubmitButton();
					  ExtentManager.getTest().pass("Ninth response submitted successfully.");
											  
				   // Submitted page
					  submittedPage.isSubmittedMessageDisplayed();
					  submittedPage.clickAddResponseButton();
					  
					  
				   // Form page for 10th attempting 
					  formPage.clickStartButton();
					  formPage.answerShortText("Entered tenth response"); 
					  formPage.clickSubmitButton();
					  ExtentManager.getTest().pass("Tenth response submitted successfully.");
											  
				   // Submitted page
					  submittedPage.isSubmittedMessageDisplayed();
					  submittedPage.clickAddResponseButton();
					  
					  
				   // Form page for 11th attempting 
					  formPage.clickStartButton();
					  formPage.answerShortText("Entered eleventh response"); 
					  formPage.clickSubmitButton();
					  ExtentManager.getTest().pass("Eleventh response submitted successfully.");
											  
				   // Submitted page
					  submittedPage.isSubmittedMessageDisplayed();
					  submittedPage.clickAddResponseButton();
					  
					  
				   // Form page for 12th attempting 
					  formPage.clickStartButton();
					  formPage.answerShortText("Entered twelth response"); 
					  formPage.clickSubmitButton();
					  ExtentManager.getTest().pass("Twelth response submitted successfully.");
											  
				   // Submitted page
					  submittedPage.isSubmittedMessageDisplayed();
					  ExtentManager.getTest().pass("<b>Submitted</b> page displayed successfully with <b>Add Response</b> button");

				      
		           // Close child window
					  submittedPage.closeChildWindowAndSwitchToParent(sharePopup.getParentWindowHandle());
					  
					  sharePopup.clickCloseIcon();
					  formdashboard.refreshPage();
					  formdashboard.clickMoreOptionsForFirstForm();
					  int numberOfFormResponsesOnFormCard = formdashboard.getTotalResponsesCountInMore();
					  ExtentManager.getTest().info("Total responses count in form card: <b>" + numberOfFormResponsesOnFormCard + "</b>");

					  String actualFormTiltle = formdashboard.getFirstFormCardTitle();
					  String expectedFormTitle=dynamicFormTitle;
		
				   // Assert that actualFormTitle and expectedFormTitle 
					  Assert.assertEquals(actualFormTiltle, expectedFormTitle, "Actual form title does not match expected title");
					  ExtentManager.getTest().pass("Actual Form title is matched with expected Form title: <b>"+ dynamicFormTitle+"</b>");
					  
						
		
	}

}


/**
Scenario: Test class to verify a form can accept and submit more than ten responses successfully.

Steps:
1. Login and create a new form with a dynamic title and a Short Answer question.
2. Enable 'Allow Multiple Responses' in Settings and submit the form.
3. Open the form via the Share popup and submit 12 responses in total, verifying the 'Submitted' page after each submission.
4. Confirm that the Form Dashboard shows the correct total responses count in 'View Responses' from More options 
Expected Result:
- The form accepts more than ten responses successfully and displays accurate response counts.
*/




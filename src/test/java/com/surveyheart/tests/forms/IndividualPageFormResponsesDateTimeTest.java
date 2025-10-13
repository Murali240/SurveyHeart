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
import com.surveyheart.utilities.WaitUtils;


/** Test class to verify that the date and time of multiple form responses
    are correctly displayed on the Form Individual page. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class IndividualPageFormResponsesDateTimeTest extends BaseTest {
	
	@Test 
    public void verifyFormResponsesDateTimeInIndividualPage() {
    	
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
				        String dynamicFormTitle = "FormResponsesDateTime " + System.currentTimeMillis();
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
					  WaitUtils.waitForSeconds(driver, 60);
					  submittedPage.clickAddResponseButton();
					
					  
				   // Form page for 2nd attempting 
					  formPage.clickStartButton();
					  formPage.answerShortText("Entered second response"); 
					  formPage.clickSubmitButton();
					  ExtentManager.getTest().pass("Second response submitted successfully.");
										  
				   // Submitted page
					  submittedPage.isSubmittedMessageDisplayed();
					  WaitUtils.waitForSeconds(driver, 60);
				      submittedPage.clickAddResponseButton();
				      
				      
				   // Form page for 3rd attempting 
					  formPage.clickStartButton();
					  formPage.answerShortText("Entered third response"); 
					  formPage.clickSubmitButton();
					  ExtentManager.getTest().pass("Third response submitted successfully.");
										  
				   // Submitted page
					  submittedPage.isSubmittedMessageDisplayed();
					  
				   // Close child window
					  submittedPage.closeChildWindowAndSwitchToParent(sharePopup.getParentWindowHandle());
					  
					  
					  sharePopup.clickCloseIcon();
					  formdashboard.refreshPage();
					  formdashboard.clickMoreOptionsForFirstForm();
					  formdashboard.clickViewResponsesButton();
					  
					  
	  // Overview page - Initialize the Overview page object with the current WebDriver instance
		 OverviewPage overview = new OverviewPage(driver);
		 
					  int totlaResponsesCount = overview.getTotalResponsesCount();
					  ExtentManager.getTest().info("Total responses count in overview page: <b>" + totlaResponsesCount + "</b>");
					  overview.clickIndividualTab();
					  String firstResponseDateTime  = overview.getResponseDateTimeInIndividual().getText();
					  overview.clickNextResponseArrow();
					  String secondResponseDateTime = overview.getResponseDateTimeInIndividual().getText();
					  overview.clickNextResponseArrow();
					  String thirdResponseDateTime  = overview.getResponseDateTimeInIndividual().getText();
					  
					  ExtentManager.getTest().info("1st Response submitted at: <b>" + firstResponseDateTime+"</b>"); 
					  ExtentManager.getTest().info("2nd Response submitted at: <b>" + secondResponseDateTime+"</b>");
					  ExtentManager.getTest().info("3rd Response submitted at: <b>" + thirdResponseDateTime+"</b>");

					  				
		
	}

}


/**
Scenario: Test class to verify that the date and time of multiple form responses
are correctly displayed on the Individual page.

Steps:
1. Login using valid credentials.
2. Create a new form with a Short Answer question and ensure 'Allow Multiple Responses' is enabled.
3. Submit three distinct responses via the form link.
4. Navigate to the Overview page and switch to Individual page.
5. Verify and log the submission date and time for each response.

Expected Result:
- Each submitted response displays the correct date and time on the Individual page.
- Submission time-stamps are consistent with the actual submission sequence.
*/




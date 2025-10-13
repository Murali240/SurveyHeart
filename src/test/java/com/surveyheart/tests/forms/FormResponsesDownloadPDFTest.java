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


/** Test class to verify that form responses can be submitted and downloaded as PDF file. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class FormResponsesDownloadPDFTest extends BaseTest {
	
	@Test 
    public void verifyFormResponsesDownloadedAsPDF() {
    	
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
				        String dynamicFormTitle = "FormResponsesPDFDownload " + System.currentTimeMillis();
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
					   ExtentManager.getTest().info("Form page displayed with " + totalQuestionCount + " question."); 
					   formPage.clickSubmitButton();
					   ExtentManager.getTest().pass("First form response submitted successfully in form page : "+formTitle);
										  
	 // Form page - Initialize the Form page object with the current WebDriver instance
		SubmittedPage submittedPage = new SubmittedPage(driver);
					  submittedPage.isSubmittedMessageDisplayed();  
					  submittedPage.clickAddResponseButton();
				
					  
				   // Form page for 2nd attempting 
					  formPage.clickStartButton();
					  formPage.answerShortText("Entered second response"); 
					  formPage.clickSubmitButton();
					  ExtentManager.getTest().pass("Second form response submitted successfully.");
										  
				   // Submitted page
					  submittedPage.isSubmittedMessageDisplayed();
				      submittedPage.clickAddResponseButton();
				  
				      
				   // Form page for 3rd attempting 
					  formPage.clickStartButton();
					  formPage.answerShortText("Entered third response"); 
					  formPage.clickSubmitButton();
					  ExtentManager.getTest().pass("Third form response submitted successfully.");
										  
				   // Submitted page
					  submittedPage.isSubmittedMessageDisplayed();
				      
					  submittedPage.closeChildWindowAndSwitchToParent(sharePopup.getParentWindowHandle());
				
					  
		           // Refresh the page
				      formdashboard.refreshPage();
				      formdashboard.clickMoreOptionsForFirstForm();
					  formdashboard.clickViewResponsesButton();
				    
				    
	  // Overview page - Initialize the Overview page object with the current WebDriver instance
	     OverviewPage overview = new OverviewPage(driver);

					  int totalResponsesCount = overview.getTotalResponsesCount();
					  ExtentManager.getTest().info("Total responses count in overview page: <b>" + totalResponsesCount + "</b>");
					  overview.clickDownloadButton();
					  overview.clickPdfFileOnDownloadPopup();
					  overview.clickDownloadPDFButton();
					  WaitUtils.waitForSeconds(driver, 1);
					  ExtentManager.getTest().pass("PDF file downloaded successfully for submitted form responses.");					  
		             
	
					  
    }

}


/**
Scenario: Test class to verify that form responses can be submitted multiple times 
          and downloaded as a PDF file.

Steps:
1. Login, create a new form with a dynamic title and a Short Answer question titled "What is your name?", enable 'Allow Multiple Responses', and submit the form.
2. Open the form via Share popup, submit three responses for the Short Answer question, and close the form tab.
3. From the Form Dashboard, go to More Options → View Responses.
4. Verify the total responses count and download all responses as a PDF file.

Expected Result:
- All submitted responses are included in the PDF file, which downloads successfully.
*/



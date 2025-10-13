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


/** Test class to verify that a form allows multiple responses in form page. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class FormWithMultipleResponsesTest extends BaseTest {
		
	@Test (groups = "regression", priority = 10)
    public void verifyFormWithMultipleResponses() {
    	
     // Login page - Initialize the Login Page object with the current WebDriver instance
		SurveyHeartLoginPage loginPage = new SurveyHeartLoginPage(driver);
						loginPage.clickSignInUsingEmail();
						loginPage.enterEmail("gofaw36836@pacfut.com");
						loginPage.clickNext();
						loginPage.enterPassword("Automation@1");
						loginPage.clickSignIn();
						loginPage.closeFeatureSpotlightIfPresent();                     // In case popup appears after login
						ExtentManager.getTest().pass("Login successful with email and password.");
				
	 // Form Dashboard - Initialize the Form Dashboard Page object with the current WebDriver instance
		FormDashboardPage formdashboard = new FormDashboardPage(driver);
						formdashboard.refreshPage();
						formdashboard.clickCreateFormButton();       // Click on +Create Form button
    	
	 // Form Builder - Initialize the Form builder object with the current WebDriver instance
        FormBuilderPage builder = new FormBuilderPage(driver);

				     // Enter Form title in Builder screen
				        builder.enterFormTitle("MultipleResponsesForm " + System.currentTimeMillis());
				
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
						  
		// Submitted page - Initialize the Submitted page object with the current WebDriver instance
		   SubmittedPage submittedPage = new SubmittedPage(driver);
		   				 submittedPage.isSubmittedMessageDisplayed();
		   				 ExtentManager.getTest().pass("Form submitted page verified successfully on the first attempt: "+formTitle);
		   				 submittedPage.clickAddResponseButton();
		   				
		   			  // Form page
		   				 formPage.clickStartButton();
						 formPage.answerShortText("Entered short answer two"); 
						 formPage.clickSubmitButton();
						  
					  // Submitted page
		   				 submittedPage.isSubmittedMessageDisplayed();
		   				 ExtentManager.getTest().pass("Form submitted page verified successfully on the second attempt: "+ formTitle);
		   				
 				 
		   				 
	}

}


/**
Scenario: Test class to verify that a form allows multiple responses in SurveyHeart.

Steps:
1. Login using valid credentials.
2. Create a new form with a Short Answer question.
3. Enable 'Allow Multiple Responses' in Form Settings.
4. Open the form via Share popup and submit the first response.
5. Submit a second response to confirm multiple submissions are allowed.

Expected Result:
- The form accepts multiple responses successfully.
- All submitted responses are recorded correctly.
*/



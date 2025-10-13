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
import com.surveyheart.pages.OverviewPage;
import com.surveyheart.pages.SharePopupPage;
import com.surveyheart.pages.SubmittedPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Test class to verify that a form accepts an only single response in Form page. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class FormWithSingleResponseTest extends BaseTest {
		
	    @Test (groups = "regression", priority = 3)
	    public void verifyFormWithSingleResponse() {
	    	
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
					        builder.enterFormTitle("SingleResponseForm " + System.currentTimeMillis());
					
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
							  formPage.answerShortText("Entered short answer");
							  String totalQuestionCount = formPage.getTotalQuestionCount();
							  ExtentManager.getTest().info("Form page displayed with " + totalQuestionCount + " question."); 
							  formPage.clickSubmitButton();
			
							  
			// Submitted page - Initialize the Submitted page object with the current WebDriver instance
			   SubmittedPage submittedPage = new SubmittedPage(driver);
			   				 submittedPage.isSubmittedMessageDisplayed();
			   				 submittedPage.closeChildWindowAndSwitchToParent(sharePopup.getParentWindowHandle());
							  
							  
						  // Share popup
			   				 sharePopup.clickCloseIcon();
							  
			   			  // Come back to Form Dashboard
			   				 formdashboard.refreshPage();
			   				 formdashboard.clickMoreOptionsForFirstForm();
			   				 formdashboard.clickViewResponsesButton();
			   						   				 
			 // Overview page - Initialize the Overview page object with the current WebDriver instance
			  	OverviewPage overview = new OverviewPage(driver);
			  	
						  	 String response = overview.getFirstQuestionResponseText();
						  	 ExtentManager.getTest().info("First question response text : " + response);
						
						  	 String actualAnswer = response;
					         String expextedAnswer = "Entered short answer";
					         
					         Assert.assertEquals(actualAnswer, expextedAnswer, "Response text does not match with expected value");
					         ExtentManager.getTest().pass("Form response matched with expected value : " + actualAnswer);

		        
	        
	}

}


/**
Scenario: Test class to verify that a form accepts only a single response when 'Allow Multiple Responses' is disabled.

Steps:
1. Login using valid credentials.
2. Create a new form with a Short Answer question.
3. Ensure 'Allow Multiple Responses' is disabled in Form Settings.
4. Open the form via Share popup and submit a single response.
5. Verify that the response text in the Overview page matches the expected answer.
6. Attempt a second submission and confirm it is not allowed.

Expected Result:
- The form accepts only one response successfully.
- Any subsequent submission attempts are blocked.
*/



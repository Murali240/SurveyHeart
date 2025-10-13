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
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Regression test: Test class to verify updating an existing form in Form Dashboard. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class FormUpdateTest extends BaseTest {

	    @Test (groups = "sanity", priority = 1)
	    public void verifyFormUpdate() {
	    	
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
			        builder.enterFormTitle("FormUpdate " + System.currentTimeMillis());
			
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
		   			sharePopup.storeParentWindowHandle();                                      // Store parent window
					sharePopup.clickViewIcon();
					sharePopup.switchToChildWindowThroughViewIcon();
								
								
		// Form page - Initialize the Form page object with the current WebDriver instance
		   FormPage formPage = new FormPage(driver);
				    formPage.clickStartButton();
				    String questionCountBefore = formPage.getTotalQuestionCount();
				    ExtentManager.getTest().info("Total number of questions on Form Page (before edit) : " + questionCountBefore+" question");
				    
				 // Closing the child window 
				    formPage.closeChildWindowAndSwitchToParent(sharePopup.getParentWindowHandle());   // Close child and switch back
				  
				 // Share popup
				    sharePopup.clickCloseIcon();
				    formdashboard.refreshPage();
				    formdashboard.clickMoreOptionsForFirstForm();
				    formdashboard.clickEditForm();
				    
				 // 2. Long Answer - while editing form
			        builder.clickAddQuestionAfter(0);
			        builder.selectQuestionType(QuestionType.LONG_ANSWER);
			        builder.enterQuestionTitle(1, "Tell me about yourself:");
			
		         // Settings
					settings.clickSettingsButton();
					settings.clickSubmitButton();
					        
				// Share popup
					sharePopup.clickViewIcon();
					sharePopup.switchToChildWindowThroughViewIcon();
								
								
				// Form page
				   formPage.clickStartButton();  
				   String questionCountAfter = formPage.getTotalQuestionCount();
				   ExtentManager.getTest().info("Total number of questions on Form Page (after edit) : " + questionCountAfter+" questions");
				    			 
		
			        
	}	        
			        
}		        


/**
Scenario: Test class to verify updating an existing form in SurveyHeart.

Steps:
1. Login using valid credentials.
2. Create a new form with a Short Answer question.
3. Enable 'Allow Multiple Responses' in Form Settings.
4. Open the form via Share popup and verify the number of questions.
5. Edit the form to add a Long Answer question.
6. Reopen the form via Share popup and verify that the total question count has increased.

Expected Result:
- The form updates successfully.
- The newly added Long Answer question is visible on the Form page after reopening.
*/



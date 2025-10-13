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


/** Test class to verify form creation with Short Answer and MCQ questions. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class CreateFormWithShortAndMcqQuestionTest extends BaseTest {

    @Test (groups = "regression", priority = 3)
    public void verifyCreateFormWithShortAndMcqQuestion() {
    	
    // Initialize the Login Page object with the current WebDriver instance
       SurveyHeartLoginPage loginPage = new SurveyHeartLoginPage(driver);
	    		loginPage.clickSignInUsingEmail();
	    		loginPage.enterEmail("gofaw36836@pacfut.com");
	    		loginPage.clickNext();
	    		loginPage.enterPassword("Automation@1");
	    		loginPage.clickSignIn();
	    		loginPage.closeFeatureSpotlightIfPresent();                       // In case popup appears after login
	    		ExtentManager.getTest().pass("Login successful with email and password.");
	    		
	// Form dashboard - Initialize the Form Dashboard Page object with the current WebDriver instance
	   FormDashboardPage formdashboard = new FormDashboardPage(driver);
	    				 formdashboard.refreshPage();
	    				 formdashboard.clickCreateFormButton();       // Click on +Create Form button
    	
    // Initialize the Login Page object with the current WebDriver instance	
       FormBuilderPage builder = new FormBuilderPage(driver);
       
		     // Enter Form title in Builder screen
		        builder.enterFormTitle("ShortAndMCQForm " + System.currentTimeMillis());
		
		        builder.clickInitialAddQuestionButton();
		        builder.selectQuestionType(QuestionType.SHORT_ANSWER);
		        builder.enterQuestionTitle(0, "What is your name?");
		
		        builder.clickAddQuestionAfter(0);
		        builder.selectQuestionType(QuestionType.MULTIPLE_CHOICE);
		        builder.enterQuestionTitle(1, "Select your city:");
		        builder.addOptionsForChoiceQuestion(1, "Hyderabad", "Bangalore", "Chennai");
    
	 // Form Settings
	    FormSettingsPage settings = new FormSettingsPage(driver);
				settings.clickSettingsButton();
				settings.clickControlTab();
				settings.enableAllowMultipleResponses(true); 
				ExtentManager.getTest().pass("'Allow Multiple Response' checkbox enabled successfully.");
				settings.clickSubmitButton();
				       
		    
	 // SharePopup - Initialize the Share popup object with the current WebDriver instance
		SharePopupPage sharePopup = new SharePopupPage(driver);
				sharePopup.clickViewIcon();
				sharePopup.switchToChildWindowThroughViewIcon();
				
				
	 // Form page - Initialize the Form page object with the current WebDriver instance
		FormPage formPage = new FormPage(driver);
				 formPage.clickStartButton();
				 String formTitle = formPage.getFormTitle();
				 String totalQuestionCount = formPage.getTotalQuestionCount();
				 ExtentManager.getTest().info("Form page displayed with " + totalQuestionCount + " questions.");
				 ExtentManager.getTest().pass("Form created successfully with Short and MCQ questions : " + formTitle);


				 			 
    }

}


/**
Scenario: Test class to verify creation of a form with Short Answer and Multiple Choice questions 
          and validate it on the Form page.

Steps:
1. Login and create a new form with a dynamic title, adding:
   - Short Answer question
   - Multiple Choice question: "Hyderabad", "Bangalore", "Chennai"
2. Enable 'Allow Multiple Responses' in Settings and submit the form.
3. Open the form via the Share popup and verify all questions are displayed with correct total count.

Expected Result:
- Form is created successfully with Short Answer and Multiple Choice questions, 
  and the Form page shows all questions with correct count.
*/




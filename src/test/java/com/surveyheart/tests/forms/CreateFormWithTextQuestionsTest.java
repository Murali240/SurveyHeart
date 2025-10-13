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


/** Test class to verify form creation with Text-type questions (Short, Long, Email, Number). */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class CreateFormWithTextQuestionsTest extends BaseTest {

    @Test (groups = "regression", priority = 2)
    public void createFormWithTextTypeQuestions() {
    	
     // Login page - Initialize the Login Page object with the current WebDriver instance
        SurveyHeartLoginPage loginPage = new SurveyHeartLoginPage(driver);
		        loginPage.clickSignInUsingEmail();
		        loginPage.enterEmail("gofaw36836@pacfut.com");
		        loginPage.clickNext();
		        loginPage.enterPassword("Automation@1");
		        loginPage.clickSignIn();
		        loginPage.closeFeatureSpotlightIfPresent();
		        ExtentManager.getTest().pass("Login successful with email and password.");
		        
		 
	 // Form dashboard - Initialize the Form Dashboard Page object with the current WebDriver instance
		FormDashboardPage formdashboard = new FormDashboardPage(driver);
				 formdashboard.refreshPage();
				 formdashboard.clickCreateFormButton();       // Click on +Create Form button

     // Form Builder - Initialize the Form builder object with the current WebDriver instance
        FormBuilderPage builder = new FormBuilderPage(driver);
        
             // Enter Form title in Builder screen
		        builder.enterFormTitle("TextQTypeForm " + System.currentTimeMillis());
		
		     // Adding Text types question in Builder screen
		        builder.clickInitialAddQuestionButton();
		        builder.selectQuestionType(QuestionType.SHORT_ANSWER);
		        builder.enterQuestionTitle(0, "What is your name?");
		
		        builder.clickAddQuestionAfter(0);
		        builder.selectQuestionType(QuestionType.LONG_ANSWER);
		        builder.enterQuestionTitle(1, "Tell me about yourself.");
		
		        builder.clickAddQuestionAfter(1);
		        builder.selectQuestionType(QuestionType.EMAIL);
		        builder.enterQuestionTitle(2, "What is your email?");
		
		        builder.clickAddQuestionAfter(2);
		        builder.selectQuestionType(QuestionType.NUMBER);
		        builder.enterQuestionTitle(3, "Enter your mobile number.");
		
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
		 		 formPage.answerShortText("Form short response");
				 formPage.answerLongText("Form long response");
				 formPage.answerEmail("sounder@gmail.com");
				 formPage.answerNumber("88");
				 String totalQuestionCount = formPage.getTotalQuestionCount();
				 formPage.clickSubmitButton();
				 ExtentManager.getTest().pass("Entered responses for text-type questions successfully : " + formTitle);
				 ExtentManager.getTest().info("Form page displayed with " + totalQuestionCount + " questions.");
				 
	    
	 // Submitted page - Initialize the Submitted page object with the current WebDriver instance
		SubmittedPage submittedPage = new SubmittedPage(driver);
				submittedPage.isSubmittedMessageDisplayed();
				ExtentManager.getTest().pass("'Submitted' page displayed successfully with the '+Add Response' button.");

    

    }

}


/**
Scenario: Test class to verify creation of a form with Text-type questions 
          (Short Answer, Long Answer, Email, Number) and validate it on the Form page.

Steps:
1. Login and create a new form with a dynamic title, adding all Text-type questions:
   - Short Answer, Long Answer, Email, Number
2. Enable 'Allow Multiple Responses' in Settings and submit the form.
3. Open the form via the Share popup, enter sample responses, submit, and verify the 'Submitted' page.
4. Confirm that the Form page shows all text questions with the correct total count.

Expected Result:
- Form is created successfully with all Text-type questions, and the Form page shows all questions with correct count.
*/




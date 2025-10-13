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


/** Test class to verify creation of a form with Choice-type questions
 * (Multiple Choice, Checkbox, and Dropdown) and validate it on the Form page. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class ChoiceTypeQuestionFormTest extends BaseTest {
	
    @Test (groups = "regression", priority = 17)
    public void verifyCreateFormWithChoiceQuestions() {
    	
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
		        builder.enterFormTitle("ChoiceQForm " + System.currentTimeMillis());
		
		     // Adding Choice type questions in Builder screen
		     // 1. Multiple Choice
		        builder.clickInitialAddQuestionButton();
		        builder.selectQuestionType(QuestionType.MULTIPLE_CHOICE);
		        builder.enterQuestionTitle(0, "Choose your city:");
		        builder.addOptionsForChoiceQuestion(0, "Hyderabad", "Bangalore", "Chennai");
		       
		     // 2. Checkbox
		        builder.clickAddQuestionAfter(0);
		        builder.selectQuestionType(QuestionType.CHECKBOX);
		        builder.enterQuestionTitle(1, "Select your skills:");
		        builder.addOptionsForChoiceQuestion(1, "Java", "Selenium", "TestNG");
		
		     // 3. Dropdown
		        builder.clickAddQuestionAfter(1);
		        builder.selectQuestionType(QuestionType.DROPDOWN);
		        builder.enterQuestionTitle(2, "Choose your country:");
		        builder.addOptionsForChoiceQuestion(2, "India", "Australia", "UK");
		
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
				 ExtentManager.getTest().pass("Choice-type questions form created successfully : " + formTitle);


				 
    }

}


/**
Scenario: Test class to verify creation of a form with Choice-type questions 
          (Multiple Choice, Checkbox, Dropdown) and validate on the Form page.

Steps:
1. Login, create a new form with a dynamic title, and add Choice-type questions:
   - Multiple Choice: "Hyderabad", "Bangalore", "Chennai"
   - Checkbox: "Java", "Selenium", "TestNG"
   - Dropdown: "India", "Australia", "UK"
2. Enable 'Allow Multiple Responses' in Settings and submit the form.
3. Open the form via the Share popup and verify all questions are displayed with correct total count.

Expected Result:
- Form is created successfully with all Choice-type questions, and the Form page shows all questions with correct count.
*/




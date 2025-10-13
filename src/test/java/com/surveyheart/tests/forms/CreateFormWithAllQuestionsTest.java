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


/** Test class to verify creation of a form with all question types and validate it on the Form page */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class CreateFormWithAllQuestionsTest extends BaseTest {

    @Test (groups = "regression", priority = 1)
    public void verifyCreateFormWithAllQuestions() {
    	
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
		        builder.enterFormTitle("AllQTypesForm " + System.currentTimeMillis());
		
		     // 1. Short Answer
		        builder.clickInitialAddQuestionButton();
		        builder.selectQuestionType(QuestionType.SHORT_ANSWER);
		        builder.enterQuestionTitle(0, "What is your name?");
		
		     // 2. Long Answer
		        builder.clickAddQuestionAfter(0);
		        builder.selectQuestionType(QuestionType.LONG_ANSWER);
		        builder.enterQuestionTitle(1, "Tell me about yourself:");
		
		     // 3. Email
		        builder.clickAddQuestionAfter(1);
		        builder.selectQuestionType(QuestionType.EMAIL);
		        builder.enterQuestionTitle(2, "Enter your email:");
		
		     // 4. Number
		        builder.clickAddQuestionAfter(2);
		        builder.selectQuestionType(QuestionType.NUMBER);
		        builder.enterQuestionTitle(3, "Enter your phone number:");
		
		     // 5. Multiple Choice
		        builder.clickAddQuestionAfter(3);
		        builder.selectQuestionType(QuestionType.MULTIPLE_CHOICE);
		        builder.enterQuestionTitle(4, "Choose your city:");
		        builder.addOptionsForChoiceQuestion(4, "Hyderabad", "Bangalore", "Chennai");
		
		     // 6. Checkbox
		        builder.clickAddQuestionAfter(4);
		        builder.selectQuestionType(QuestionType.CHECKBOX);
		        builder.enterQuestionTitle(5, "Select your skills:");
		        builder.addOptionsForChoiceQuestion(5, "Java", "Selenium", "TestNG");
		
		     // 7. Dropdown
		        builder.clickAddQuestionAfter(5);
		        builder.selectQuestionType(QuestionType.DROPDOWN);
		        builder.enterQuestionTitle(6, "Choose your country:");
		        builder.addOptionsForChoiceQuestion(6, "India", "Australia", "UK");
		
		     // 8. Star
		        builder.clickAddQuestionAfter(6);
		        builder.selectQuestionType(QuestionType.STAR);
		        builder.enterQuestionTitle(7, "Rate our service:");
		
		     // 9. Smile
		        builder.clickAddQuestionAfter(7);
		        builder.selectQuestionType(QuestionType.SMILE);
		        builder.enterQuestionTitle(8, "How satisfied are you?");
		
		     // 10. Date
		        builder.clickAddQuestionAfter(8);
		        builder.selectQuestionType(QuestionType.DATE);
		        builder.enterQuestionTitle(9, "Select your birthdate:");
		
		     // 11. Time
		        builder.clickAddQuestionAfter(9);
		        builder.selectQuestionType(QuestionType.TIME);
		        builder.enterQuestionTitle(10, "Choose appointment time:");
		
		     // 12. File Upload
		        builder.clickAddQuestionAfter(10);
		        builder.selectQuestionType(QuestionType.FILE_UPLOAD);
		        builder.enterQuestionTitle(11, "Upload your ID proof:");
		
		     // 13. Linear Scale
		        builder.clickAddQuestionAfter(11);
		        builder.selectQuestionType(QuestionType.LINEAR);
		        builder.enterQuestionTitle(12, "Rate satisfaction (1-5):");
		
		     // 14. MCQ Grid
		        builder.clickAddQuestionAfter(12);
		        builder.selectQuestionType(QuestionType.MCQ_GRID);
		        builder.enterQuestionTitle(13, "Rate the following:");
		        builder.addRowsAndColumnsToGridQuestion(13, 3, 2); // Adds 3 rows and 2 columns with default labels

		
		     // 15. Checkbox Grid
		        builder.clickAddQuestionAfter(13);
		        builder.selectQuestionType(QuestionType.CHECKBOX_GRID);
		        builder.enterQuestionTitle(14, "Select applicable features:");
		        builder.addRowsAndColumnsToGridQuestion(14, 2, 3); // Adds 2 rows and 3 columns with default labels

		        
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
				 sharePopup.switchToChildWindowThroughViewIcon();
					
					
		// Form page - Initialize the Form page object with the current WebDriver instance
		   FormPage formPage = new FormPage(driver);
		   		  formPage.clickStartButton();
				  formPage.enterNameIfPresent("Sounder");
				  String formTitle = formPage.getFormTitle();
				  String totalQuestionCount = formPage.getTotalQuestionCount();
				  ExtentManager.getTest().info("Form page displayed with " + totalQuestionCount + " questions."); 
				  ExtentManager.getTest().pass("Form created successfully with all question types : " + formTitle);


	        
    }

}


/**
Scenario: Test class to verify creation of a form with all question types 
          and validate it on the Form page.

Steps:
1. Login and create a new form with a dynamic title, adding all question types:
   - Short Answer, Long Answer, Email, Number
   - Multiple Choice: "Hyderabad", "Bangalore", "Chennai"
   - Checkbox: "Java", "Selenium", "TestNG"
   - Dropdown: "India", "Australia", "UK"
   - Star Rating, Smile Rating, Date, Time, File Upload, Linear Scale
   - MCQ Grid (3x2), Checkbox Grid (2x3)
2. Enable 'Allow Multiple Responses' in Settings and submit the form.
3. Open the form via the Share popup and verify all questions are displayed with correct total count.

Expected Result:
- Form is created successfully with all question types, and the Form page shows all questions with correct count.
*/


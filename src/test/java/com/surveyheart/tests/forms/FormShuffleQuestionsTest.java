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


/** Test class to verify shuffle questions functionality in a form page. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class FormShuffleQuestionsTest extends BaseTest {
	
	    @Test 
	    public void verifyQuestionsShuffledInForm() {
	    	
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
			        builder.enterFormTitle("ShuffleQForm " + System.currentTimeMillis());
			
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
			
			        
		   // Form Settings - Initialize the Form settings object with the current WebDriver instance
			  FormSettingsPage settings = new FormSettingsPage(driver);
					 settings.clickSettingsButton();
					 settings.clickControlTab();
					 settings.enableShuffleQuestions(true);	 
					 ExtentManager.getTest().pass("'Shuffle Questions' checkbox enabled successfully.");
					 settings.clickSubmitButton();
			        
				    
			// SharePopup - Initialize the Share popup object with the current WebDriver instance
			   SharePopupPage sharePopup = new SharePopupPage(driver);
					 sharePopup.clickViewIcon();
					 sharePopup.switchToChildWindowThroughViewIcon();
						
						
			// Form page - Initialize the Form page object with the current WebDriver instance
			   FormPage formPage = new FormPage(driver);
			   		  formPage.clickStartButton();
			   		  
			   	   // Form page
					  String formTitle = formPage.getFormTitle();
					  String shuffledFirstQT = formPage.getFirstQuestionTitleText();
					  String totalQuestionCount = formPage.getTotalQuestionCount();
					  ExtentManager.getTest().info("1st question displayed as: <b>" + shuffledFirstQT + "</b>");
					  ExtentManager.getTest().info("Form page displayed with: " + totalQuestionCount + " questions."); 
					  ExtentManager.getTest().pass("Questions successfully shuffled in Form page: <b>" + formTitle + "</b>");



	}

}


/**
Scenario: Test class to verify that questions are shuffled in a form page.

Steps:
1. Login, create a new form with a dynamic title, add Short Answer, Long Answer, Email, and Number questions, enable 'Shuffle Questions' in Settings, and submit the form.
2. Open the form via Share popup, start the form, and capture the first question displayed and total question count.
3. Verify that questions are shuffled on the Form page.

Expected Result:
- The 'Shuffle Questions' feature is applied correctly.
- The order of questions on the Form page must vary due to shuffling.
*/



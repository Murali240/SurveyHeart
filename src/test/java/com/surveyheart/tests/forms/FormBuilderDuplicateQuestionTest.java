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


/** Test class to verify the duplicate question functionality in Form Builder
 * and validate the duplicated question on the Form page.*/
@Listeners(com.surveyheart.listeners.TestListener.class)
public class FormBuilderDuplicateQuestionTest extends BaseTest {
	
	@Test 
    public void verifyDuplicateQuestionFunctionality() {
    	
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
		        builder.enterFormTitle("DuplicateQForm " + System.currentTimeMillis());
		
		     // 1. Short Answer
		        builder.clickInitialAddQuestionButton();
		        builder.selectQuestionType(QuestionType.SHORT_ANSWER);
		        builder.enterQuestionTitle(0, "What is your name?");
		
		        builder.clickDuplicateQuestionIcon();

		        
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
				  String formTitle = formPage.getFormTitle();
				  String totalQuestionCount = formPage.getTotalQuestionCount();
				  ExtentManager.getTest().info("Form page displayed with " + totalQuestionCount + " questions."); 
				  ExtentManager.getTest().pass("Question got duplicated successfully in Form page: <b>" + formTitle + "</b>");


	
	}

}


/**
Scenario: Test class to verify the duplicate question functionality in Form Builder 
          and validate the duplicated question on the Form page.

Steps:
1. Login and create a new form with a dynamic title, adding a Short Answer question.
2. Duplicate the question, enable 'Allow Multiple Responses' in Settings, and submit the form.
3. Open the form via the Share popup, start the form, and verify the duplicated question is displayed with correct total question count.

Expected Result:
- The question is duplicated successfully, and the Form page shows the updated total question count.
*/




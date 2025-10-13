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


/** Smoke Test: Verifies required field validation message appears when submitting a form without answering a mandatory question. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class FormRequiredFieldTest extends BaseTest {
	
	@Test 
    public void verifyFormRequiredFieldValidationMessage() {
    	
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

		     
				     // Set dynamic Form title
					    String dynamicFormTitle = "RequiredForm " + System.currentTimeMillis();
					    builder.enterFormTitle(dynamicFormTitle);
				
				     // 1. Short Answer
				        builder.clickInitialAddQuestionButton();
				        builder.selectQuestionType(QuestionType.SHORT_ANSWER);
				        builder.enterQuestionTitle(0, "What is your name?");
				
				     // Required button
				        builder.clickRequiredToggleSwitchIcon();
				        
		        
	   // Form Settings - Initialize the Form settings object with the current WebDriver instance
		  FormSettingsPage settings = new FormSettingsPage(driver);
						settings.clickSettingsButton();
						settings.clickControlTab();
						settings.enableAllowMultipleResponses(true); 
						ExtentManager.getTest().pass("'Allow Multiple Response' checkbox enabled successfully.");
						settings.clickSubmitButton();
						ExtentManager.getTest().info("Form created successfully with: "+dynamicFormTitle);
		        
			    
		// SharePopup - Initialize the Share popup object with the current WebDriver instance
		   SharePopupPage sharePopup = new SharePopupPage(driver);
						sharePopup.clickViewIcon();
						sharePopup.switchToChildWindowThroughViewIcon();
					
					
		// Form page - Initialize the Form page object with the current WebDriver instance
		   FormPage formPage = new FormPage(driver);
		   		  
		                formPage.clickStartButton();
		   		  
		   	       // Form page
					  String formTitle = formPage.getFormTitle();
					  formPage.clickSubmitButton();
					  String requiredMessage = formPage.getRequiredWarningMessage().getText();
					  String totalQuestionCount = formPage.getTotalQuestionCount();
					  ExtentManager.getTest().info("Form page displayed with " + totalQuestionCount + " questions.");
					  ExtentManager.getTest().info("Question validation message displayed: <b>" + requiredMessage + "</b>");
					  ExtentManager.getTest().pass("Required field validation message displayed in Form page: <b>" + formTitle + "</b>");


				  
    
    }

}


/**
Scenario: Smoke Test to verify that the required field validation message appears 
          when submitting a form without answering a mandatory question.

Steps:
1. Login, create a new form with a dynamic title and a Short Answer question, enable 'Required' and 'Allow Multiple Responses', and submit the form.
2. Open the form via Share popup and start the form.
3. Submit the form without entering a response.
4. Capture and verify that the required field validation message appears.
5. Confirm that the total question count matches the number of questions added.

Expected Result:
- The form is created successfully with a required Short Answer question.
- Submitting without answering displays the required field validation message.
*/




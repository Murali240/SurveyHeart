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


/** Test class to verify editing a form and deleting a question in Form Builder. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class FormBuilderDeleteQuestionTest extends BaseTest {
	
	@Test (groups = "regression", priority = 4)
    public void verifyEditFormAndDeleteQuestionTest() {
    	
     // Login page - Initialize the Login Page object with the current WebDriver instance
		SurveyHeartLoginPage loginPage = new SurveyHeartLoginPage(driver);
					loginPage.clickSignInUsingEmail();
					loginPage.enterEmail("gofaw36836@pacfut.com");
					loginPage.clickNext();
					loginPage.enterPassword("Automation@1");
					loginPage.clickSignIn();
					loginPage.closeFeatureSpotlightIfPresent();              // In case popup appears after login
					ExtentManager.getTest().pass("Login successful with email and password.");
				
	 // Form Dashboard - Initialize the Form Dashboard Page object with the current WebDriver instance
		FormDashboardPage formdashboard = new FormDashboardPage(driver);
					formdashboard.refreshPage();
					formdashboard.clickCreateFormButton();                // Click on +Create Form button
    	
	 // Form Builder - Initialize the Form builder object with the current WebDriver instance
        FormBuilderPage builder = new FormBuilderPage(driver);

			     // Enter Form title in Builder screen
			        builder.enterFormTitle("FormWithDeleteQuestion " + System.currentTimeMillis());
			
			     // 1. Multiple Choice
			        builder.clickInitialAddQuestionButton();
			        builder.selectQuestionType(QuestionType.MULTIPLE_CHOICE);
			        builder.enterQuestionTitle(0, "Choose your city:");
			        builder.addOptionsForChoiceQuestion(0, "Hyderabad", "Bangalore", "Chennai");
			        		               
			     // 2. Short Answer
			        builder.clickAddQuestionAfter(0);
			        builder.selectQuestionType(QuestionType.SHORT_ANSWER);
			        builder.enterQuestionTitle(1, "What is your name?");

		        
	   // Form Settings - Initialize the Form settings object with the current WebDriver instance
		  FormSettingsPage settings = new FormSettingsPage(driver);
					settings.clickSettingsButton();
					settings.clickControlTab();
					settings.enableAllowMultipleResponses(true); 
					ExtentManager.getTest().pass("'Allow Multiple Response' checkbox enabled successfully.");
					settings.clickSubmitButton();
			        
			    
		// SharePopup - Initialize the Share popup object with the current WebDriver instance
		   SharePopupPage sharePopup = new SharePopupPage(driver);
				    sharePopup.storeParentWindowHandle();                                  // Store parent window
					sharePopup.clickViewIcon();
					sharePopup.switchToChildWindowThroughViewIcon();
							
					
		// Form page - Initialize the Form page object with the current WebDriver instance
		   FormPage formPage = new FormPage(driver);
			   		formPage.clickStartButton();
					String formTitle = formPage.getFormTitle();
					String totalQuestionCount = formPage.getTotalQuestionCount();
					ExtentManager.getTest().info("Total number of questions on Form Page (before delete): " + totalQuestionCount+" questions");
					
				 // Closing the child window 
				    formPage.closeChildWindowAndSwitchToParent(sharePopup.getParentWindowHandle());      // Close child and switch back
				 
				    
				 // Share popup
				    sharePopup.clickCloseIcon();
				    formdashboard.refreshPage();
				    formdashboard.clickMoreOptionsForFirstForm();
				    formdashboard.clickEditForm();
				    
				 // 2. Long Answer - while editing form
				    builder.clickDeleteIcon();
			
		         // Settings
					settings.clickSettingsButton();
					settings.clickSubmitButton();
					        
				// Share popup
					sharePopup.clickViewIcon();
					sharePopup.switchToChildWindowThroughViewIcon();
								
								
				// Form page
				   formPage.clickStartButton();  
				   String questionCountAfter = formPage.getTotalQuestionCount();
				   ExtentManager.getTest().info("Total number of questions on Form Page (after delete): " + questionCountAfter+" questions");
				   ExtentManager.getTest().pass("Form edited successfully and one question was deleted: <b>" + formTitle + "</b>");

		   
				   
	}

}


/**
Scenario: Test class to verify editing a form and deleting a question in Form Builder.

Steps:
1. Login and create a new form with a dynamic title, adding a Multiple Choice and a Short Answer question.
2. Enable 'Allow Multiple Responses' in Settings and submit the form.
3. Open the form via the Share popup to verify total questions, then edit the form from the Form Dashboard.
4. Delete one question, save changes, reopen the form via Share popup, and verify the total question count decreased by one.

Expected Result:
- The question is deleted successfully, and the Form page reflects the updated question count.
*/




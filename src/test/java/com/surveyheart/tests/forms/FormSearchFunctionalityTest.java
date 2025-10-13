package com.surveyheart.tests.forms;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.surveyheart.base.BaseTest;
import com.surveyheart.enums.QuestionType;
import com.surveyheart.pages.FormBuilderPage;
import com.surveyheart.pages.FormDashboardPage;
import com.surveyheart.pages.FormSettingsPage;
import com.surveyheart.pages.SharePopupPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Test class to verify form search functionality on the Form Dashboard. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class FormSearchFunctionalityTest extends BaseTest {
	
	    @Test (groups = "regression", priority = 1)
	    public void verifySearchFuctionalityForForm() {
	    	
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

				  // === FORM 1 CREATION ===
				  // Form Builder - Initialize
					 FormBuilderPage builder1 = new FormBuilderPage(driver);
					 String formTitle1 = "Alpha " + System.currentTimeMillis();
					 builder1.enterFormTitle(formTitle1);

			      // Add Short Answer Question
					 builder1.clickInitialAddQuestionButton();
					 builder1.selectQuestionType(QuestionType.SHORT_ANSWER);
					 builder1.enterQuestionTitle(0, "What is your name?");
	
				  // Form Settings
					 FormSettingsPage settings1 = new FormSettingsPage(driver);
					 settings1.clickSettingsButton();
					 settings1.clickControlTab();
					 settings1.enableAllowMultipleResponses(true);
					 settings1.clickSubmitButton();
					 ExtentManager.getTest().info("Form 1 created with : " + formTitle1);

				  // Close Share Popup
					 SharePopupPage sharePopup1 = new SharePopupPage(driver);
					 sharePopup1.clickCloseIcon();

				  // Return to Dashboard and click Create Form again
					 FormDashboardPage formDashboard = new FormDashboardPage(driver);
					 formDashboard.clickCreateFormButton();
					 
	
				  // === FORM 2 CREATION ==
				  // Re-initialize Form Builder
					 FormBuilderPage builder2 = new FormBuilderPage(driver);
					 String formTitle2 = "Beta " + System.currentTimeMillis();
					 builder2.enterFormTitle(formTitle2); 

				  // Add Short Answer Question
					 builder2.clickInitialAddQuestionButton();
					 builder2.selectQuestionType(QuestionType.SHORT_ANSWER);
					 builder2.enterQuestionTitle(0, "What is your name?");

				  // Form Settings
					 FormSettingsPage settings2 = new FormSettingsPage(driver);
					 settings2.clickSettingsButton();
					 settings2.clickControlTab();
					 settings2.enableAllowMultipleResponses(true);
					 settings2.clickSubmitButton();
					 ExtentManager.getTest().info("Form 2 created with : " + formTitle2);

				  // Close Share Popup
					 SharePopupPage sharePopup2 = new SharePopupPage(driver);
					 sharePopup2.clickCloseIcon();
					
				  // Searching for 1st form from Form Dashboard
					 formDashboard.searchFormByTitle(formTitle1);
					 ExtentManager.getTest().pass("Searched form '" + formTitle1 + "' was displayed successfully.");		
					 
					 
					 			 
	}

}


/**
Scenario: Test class to verify form search functionality on the Form Dashboard.

Steps:
1. Login and create two forms with dynamic titles, each having a Short Answer question, enable 'Allow Multiple Responses', and submit both forms.
2. On the Form Dashboard, search for the first form.
3. Verify that the searched form is displayed correctly in the search results.

Expected Result:
- Both forms are created successfully.
- The search functionality correctly displays the searched form on the Form Dashboard.
*/



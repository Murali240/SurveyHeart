package com.surveyheart.tests.forms;

import org.testng.Assert;
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


/** Smoke test to verify form creation and open the form page. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class FormCreationTest extends BaseTest {
	
	    @Test (groups = {"smoke"}, priority = 3)
	    public void verifyFormCreation() {
	    	
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
						formdashboard.clickCreateFormButton();         // Click on +Create Form button
		    	
		 // Form Builder - Initialize the Form builder object with the current WebDriver instance
	        FormBuilderPage builder = new FormBuilderPage(driver);

				     // Enter Form title in Builder screen
				        String dynamicFormTitle = "FormCreation " + System.currentTimeMillis();
				        builder.enterFormTitle(dynamicFormTitle); 
				
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
						ExtentManager.getTest().info("Form created with : " + dynamicFormTitle );
					        
						    
		// SharePopup - Initialize the Share popup object with the current WebDriver instance
		   SharePopupPage sharePopup = new SharePopupPage(driver);
			   			sharePopup.storeParentWindowHandle();                               // Store parent window
						sharePopup.clickViewIcon();
						sharePopup.switchToChildWindowThroughViewIcon();
								
								
		// Form page - Initialize the Form page object with the current WebDriver instance
		   FormPage formPage = new FormPage(driver);
					    formPage.clickStartButton();
					    String questionCountBefore = formPage.getTotalQuestionCount();
					    ExtentManager.getTest().info("Form created with : " + questionCountBefore + " question");
				    
				     // Closing the child window 
					    formPage.closeChildWindowAndSwitchToParent(sharePopup.getParentWindowHandle()); // Close child and switch back
				  
					 // Share popup
					    sharePopup.clickCloseIcon();
					    formdashboard.refreshPage();
					    String actualFormTiltle = formdashboard.getFirstFormCardTitle();
					    String expectedFormTitle=dynamicFormTitle;
		
					 // Assert that actualFormTitle and expectedFormTitle 
					    Assert.assertEquals(actualFormTiltle, expectedFormTitle, "Actual form title does not match expected title");
					    ExtentManager.getTest().pass("Actual Form title is matched with expected Form title : "+ dynamicFormTitle);

		
					    
    }

}


/**
Scenario: Smoke test to verify creation of a form with a Short Answer question 
          and that the Form page opens correctly.

Steps:
1. Login and create a new form with a dynamic title, adding a Short Answer question titled "What is your name?".
2. Enable 'Allow Multiple Responses' in Settings and submit the form.
3. Open the form via the Share popup, start the form, and verify the total number of questions.
4. Refresh the Form Dashboard and confirm that the form title matches the dynamic title.

Expected Result:
- Form is created successfully with the Short Answer question, and the Form page opens correctly with the expected question count.
*/



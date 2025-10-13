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


/** Test class to verify that the most recently created form appears first
    in the Form Dashboard after creating multiple forms. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class LatestFormFirstInDashboardTest extends BaseTest {
	
	@Test (groups = "regression", priority = 4)
    public void verifyLatestFormIsFirstInDashboard() {
    	
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
			        String dynamicFormTitle = "AlphaForm " + System.currentTimeMillis();
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
					ExtentManager.getTest().info("Form created with: " + dynamicFormTitle );
				        
					    
	// SharePopup - Initialize the Share popup object with the current WebDriver instance
	   SharePopupPage sharePopup = new SharePopupPage(driver);
		   			sharePopup.storeParentWindowHandle();                                      // Store parent window
					sharePopup.clickViewIcon();
					sharePopup.switchToChildWindowThroughViewIcon();
							
							
	// Form page - Initialize the Form page object with the current WebDriver instance
	   FormPage formPage = new FormPage(driver);
				    formPage.clickStartButton();
				    String formTitle1 = formPage.getFormTitle();
				    String questionCount = formPage.getTotalQuestionCount();
				    ExtentManager.getTest().info("Form created with: " + questionCount + " question");
				    ExtentManager.getTest().pass("Successfully retrieved the form title on the form page: <b>" + formTitle1 + "</b>");

			    
			     // Closing the child window 
				    formPage.closeChildWindowAndSwitchToParent(sharePopup.getParentWindowHandle());   // Step 4: Close child and switch back
			  
				 // Share popup
				    sharePopup.clickCloseIcon();
				    formdashboard.refreshPage();
				   
				    
				 // Creating Beta Form -2nd Form
				    formdashboard.clickCreateFormButton();           // Click on +Create Form button
				    
				 // Enter Form title in Builder screen
			        String dynamicFormTitle2 = "BetaForm " + System.currentTimeMillis();
			        builder.enterFormTitle(dynamicFormTitle2); 
			
			     // 1. Short Answer   
			        builder.clickInitialAddQuestionButton();
			        builder.selectQuestionType(QuestionType.SHORT_ANSWER);
			        builder.enterQuestionTitle(0, "What is your name?");
				   
			     // Settings
			        settings.clickSettingsButton();
					settings.clickControlTab();
					settings.enableAllowMultipleResponses(true); 
					ExtentManager.getTest().pass("'Allow Multiple Response' checkbox enabled successfully.");
					settings.clickSubmitButton();
					ExtentManager.getTest().info("Form created with: " + dynamicFormTitle2 );
				    
			     // Share Popup
					sharePopup.storeParentWindowHandle();                                      // Store parent window
					sharePopup.clickViewIcon();
					sharePopup.switchToChildWindowThroughViewIcon();
					
			     // Form Page
					formPage.clickStartButton();
					String formTitle2 = formPage.getFormTitle();
				    String questionCount2 = formPage.getTotalQuestionCount();
				    ExtentManager.getTest().info("Form created with: " + questionCount2 + " question");
				    ExtentManager.getTest().pass("Successfully retrieved the form title on the form page: <b>" + formTitle2 + "</b>");
			    
			     // Closing the child window 
				    formPage.closeChildWindowAndSwitchToParent(sharePopup.getParentWindowHandle());   // Close child and switch back
			  
				 // Share popup
				    sharePopup.clickCloseIcon(); 
				    
				 // Form Dashboard
				    formdashboard.clickSecondFormMoreOptions();
				    formdashboard.clickEditForm();
				    ExtentManager.getTest().pass("Successfully edited the second form.");

				    
				 // Settings
			        settings.clickSettingsButton();
					settings.clickSubmitButton();
					ExtentManager.getTest().pass("Successfully submitted the second form.");

				    
			     // Share popup
				    sharePopup.clickCloseIcon(); 
				    
				 // Form Dashboard
				    formdashboard.refreshPage();
				    String actualFormTiltle = formdashboard.getFirstFormCardTitle();
				    String expectedFormTitle=dynamicFormTitle;
	
				 // Assert that actualFormTitle and expectedFormTitle 
				    Assert.assertEquals(actualFormTiltle, expectedFormTitle, "Actual form title does not match expected title");
				    ExtentManager.getTest().pass("Actual Form title is matched with expected Form title: <b>"+ dynamicFormTitle+"</b>");

	
				    
	}

}


/**
Scenario: Test class to verify that the most recently created form appears first
in the Form Dashboard after creating multiple forms.

Steps:
1. Login using valid credentials.
2. Create the first form ("AlphaForm") with a Short Answer question and enable 'Allow Multiple Responses'.
3. Open the first form via the Share popup, capture the form title, and close the child window.
4. Create the second form ("BetaForm") with a Short Answer question and enable 'Allow Multiple Responses'.
5. Open and edit the second form to update its timestamp, then save the changes.
6. Refresh the Form Dashboard.
7. Verify that the first created form ("AlphaForm") appears first in the dashboard list.

Expected Result:
- The first created form ("AlphaForm") remains listed first on the Form Dashboard
  even after creating and updating the second form.
*/




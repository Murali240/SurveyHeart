package com.surveyheart.tests.forms;

import org.testng.Assert;
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


/** Test class to verifies multiple form deletion with UNDO toast in Form Dashboard. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class FormDashboardMultipleFormsDeletionTest extends BaseTest {
	
	@Test 
    public void verifyMultipleFormsDeletionWithUndoToastMessage()  {
    	
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
				 formdashboard.clickCreateFormButton();        // Click on +Create Form button
    	
	 // Form Builder - Initialize the Form builder object with the current WebDriver instance
        FormBuilderPage builder = new FormBuilderPage(driver);

		     // Enter Form title in Builder screen
		        String dynamicFormTitle1 = "AlphaForm " + System.currentTimeMillis();
		        builder.enterFormTitle(dynamicFormTitle1); 
		
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
				ExtentManager.getTest().info("First form created with: " + dynamicFormTitle1);
				        
					    
	// SharePopup - Initialize the Share popup object with the current WebDriver instance
	   SharePopupPage sharePopup = new SharePopupPage(driver);
	   			sharePopup.clickCloseIcon(); 
	   			
	   			
	   	     // Creating 2nd Form
	   			formdashboard.clickCreateFormButton();        // Click on +Create Form button
	   			
	   		 // Enter Form title in Builder screen
		        String dynamicFormTitle2 = "BetaForm " + System.currentTimeMillis();
		        builder.enterFormTitle(dynamicFormTitle2); 
		
		     // 1. Short Answer
		        builder.clickInitialAddQuestionButton();
		        builder.selectQuestionType(QuestionType.SHORT_ANSWER);
		        builder.enterQuestionTitle(0, "What is your name?");
		        
		     // Settings screen
		        settings.clickSettingsButton();
				settings.clickControlTab();
				settings.enableAllowMultipleResponses(true); 
				ExtentManager.getTest().pass("'Allow Multiple Response' checkbox enabled successfully.");
				settings.clickSubmitButton();
				ExtentManager.getTest().info("Second form created with: " + dynamicFormTitle2);
				sharePopup.clickCloseIcon();
				
				
		     // Creating 3rd Form
	   			formdashboard.clickCreateFormButton();        // Click on +Create Form button
	   			
	   		 // Enter Form title in Builder screen
		        String dynamicFormTitle3 = "GammaForm " + System.currentTimeMillis();
		        builder.enterFormTitle(dynamicFormTitle3); 
		
		     // 1. Short Answer
		        builder.clickInitialAddQuestionButton();
		        builder.selectQuestionType(QuestionType.SHORT_ANSWER);
		        builder.enterQuestionTitle(0, "What is your name?");
		        
		     // Settings screen
		        settings.clickSettingsButton();
				settings.clickControlTab();
				settings.enableAllowMultipleResponses(true); 
				ExtentManager.getTest().pass("'Allow Multiple Response' checkbox enabled successfully.");
				settings.clickSubmitButton();
				ExtentManager.getTest().info(" Third form created with: " + dynamicFormTitle3);
				sharePopup.clickCloseIcon();
	   			
	   			

	   		 // Come back to Form Dashboard
	   			formdashboard.refreshPage();
	   			
	   		 // Deleting multiple forms
			    formdashboard.clickFirstFormSelectionCircle();
			    formdashboard.clickSecondFormSelectionCircle();
			    formdashboard.clickDeleteButtonFormSelection();
			    formdashboard.clickConfirmDeleteButtonFormSelection();
			    String undoToastMessage = formdashboard.getFullUndoToastMessage();
			    ExtentManager.getTest().pass("UNDO toast message displayed successfully in Form Dashboard for multiple form deletion: <b>" + undoToastMessage + "</b>");
			    
			 // Get latest Form, after 2 forms deleted
			    String actualFormTiltle = formdashboard.getFirstFormCardTitle();
			    String expectedFormTitle=dynamicFormTitle1;
			    
			 // Assert that actualFormTitle and expectedFormTitle 
			    Assert.assertEquals(actualFormTiltle, expectedFormTitle, "Actual form title does not match expected title");
			    ExtentManager.getTest().pass("After successfully deleting 2 forms, the latest form in Form Dashboard is: <b>" + actualFormTiltle + "</b>");

		
			    
    }

}


/**
Scenario: Test class to verify deletion of multiple forms from Form Dashboard 
          and that the UNDO toast message is displayed correctly.

Steps:
1. Login and create three forms with Short Answer questions, enabling 'Allow Multiple Responses' for each.
2. Refresh the Form Dashboard, select the first two forms, delete them, and confirm deletion.
3. Verify that the UNDO toast message appears and that the remaining form's title matches the expected title.

Expected Result:
- Multiple forms are deleted successfully, the UNDO toast message appears, and the remaining form displays the correct title with 1st position in Form dashboard.
*/



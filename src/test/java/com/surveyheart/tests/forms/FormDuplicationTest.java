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


/** Regression test to verify form duplication in Form Dashboard */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class FormDuplicationTest extends BaseTest {
	
	    @Test (groups = "regression", priority = 5)
	    public void verifyFormDuplication() throws InterruptedException  {
	
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
					formdashboard.clickCreateFormButton();             // Click on +Create Form button

	
	 // Initialize the Login Page object with the current WebDriver instance	
		FormBuilderPage builder = new FormBuilderPage(driver);
				       
				 // Enter Form title in Builder screen
		            String dynamicFormTitle = "DuplicateForm " + System.currentTimeMillis();
		            builder.enterFormTitle(dynamicFormTitle); 
					
			     // Short Question adding
					builder.clickInitialAddQuestionButton();
					builder.selectQuestionType(QuestionType.SHORT_ANSWER);
					builder.enterQuestionTitle(0, "What is your name?");
					
					
	 // Form Settings -  Initialize the Settings page object with the current WebDriver instance
		FormSettingsPage settings = new FormSettingsPage(driver);
					settings.clickSettingsButton();
					settings.clickControlTab();
					settings.enableAllowMultipleResponses(true); 
					ExtentManager.getTest().pass("'Allow Multiple Response' checkbox enabled successfully.");
					settings.clickSubmitButton();
					ExtentManager.getTest().info("Form created with: " + dynamicFormTitle);
							       
					    
	 // SharePopup - Initialize the Share popup object with the current WebDriver instance
	    SharePopupPage sharePopup = new SharePopupPage(driver);
					sharePopup.clickCloseIcon();
					
				
				 // Come back to FormDashboard
					formdashboard.refreshPage();
					
				 // Step 1: Get the original form title from the Dashboard BEFORE duplicating
					String originalFormTitle = formdashboard.getFirstFormCardTitle();    // This must be defined

				 // Step 2: Construct expected duplicated form title
					String expectedDuplicatedFormTitle = originalFormTitle + " (Copy)";

				 // Step 3: Duplicate the form
					formdashboard.clickMoreOptionsForFirstForm();
					formdashboard.clickDuplicateFormButton();

				 // Step 4: Get actual duplicated form title from builder screen
					String actualDuplicatedFormTitle = builder.getDuplicatedFormTitle();

				//  Step 5: Assert the actual title matches expected
					Assert.assertEquals(actualDuplicatedFormTitle, expectedDuplicatedFormTitle, 
					    "❌ Duplicated form title does not match expected title.");

				 // Step 6: Log to report
					ExtentManager.getTest().pass("✔ Duplicated form title matched on builder screen: <b>" + actualDuplicatedFormTitle+"</b>");

					
				 // Settings screen for duplication form
					settings.clickSettingsButton();
					settings.clickSubmitButton();
					ExtentManager.getTest().info("Form duplicated with: " + actualDuplicatedFormTitle);
					
				 // Close share popup
					sharePopup.clickCloseIcon();
					
				 // Form Dashboard
					formdashboard.refreshPage();
					String latestFormTitle = formdashboard.getFirstFormCardTitle();
					String expectedFormTitle = originalFormTitle + " (Copy)";
					
				//  Assert the actual title matches expected
					Assert.assertEquals(latestFormTitle, expectedFormTitle, "❌ Actual form title does not match expected title.");    
					ExtentManager.getTest().pass("Actual form title matched with the expected form title: <b>" + actualDuplicatedFormTitle+"</b>");
					
	  			
					
    }		
		 
}


/**
Scenario: Regression test to verify form duplication in Form Dashboard.

Steps:
1. Login, create a new form with a dynamic title and a Short Answer question titled "What is your name?", enable 'Allow Multiple Responses', and submit the form.
2. From the Form Dashboard, duplicate the original form.
3. Verify that the duplicated form title displays as "<Original Title> (Copy)" in the Builder screen.
4. Submit the duplicated form and refresh the dashboard.
5. Confirm that the latest form card shows the duplicated form title correctly.

Expected Result:
- The duplicated form is created successfully with the correct title in both the Builder screen and Form Dashboard.
*/


					
					
					
					
					
	   



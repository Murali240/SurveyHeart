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


/**Test class to verify that editing a form with no responses opens the Form Builder page 
   without showing any edit warning popup. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class WithoutResponseFormEditTest extends BaseTest {
	
	    @Test (groups = "sanity", priority = 5)
	    public void verifyFormEditWithoutResponse() {
	    	
	     // Login page - Initialize the Login Page object with the current WebDriver instance
			SurveyHeartLoginPage loginPage = new SurveyHeartLoginPage(driver);
						loginPage.clickSignInUsingEmail();
						loginPage.enterEmail("gofaw36836@pacfut.com");
						loginPage.clickNext();
						loginPage.enterPassword("Automation@1");
						loginPage.clickSignIn();
						loginPage.closeFeatureSpotlightIfPresent();          // In case popup appears after login
						ExtentManager.getTest().pass("Login successful with email and password.");
					
		 // Form Dashboard - Initialize the Form Dashboard Page object with the current WebDriver instance
			FormDashboardPage formdashboard = new FormDashboardPage(driver);
						formdashboard.refreshPage();
						formdashboard.clickCreateFormButton();       // Click on +Create Form button
	    	
		 // Form Builder - Initialize the Form builder object with the current WebDriver instance
	        FormBuilderPage builder = new FormBuilderPage(driver);

				     // Enter Form title in Builder screen
				        String dynamicFormTitle = "FormEditWithoutResponse " + System.currentTimeMillis();
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
					    String formTitle = formPage.getFormTitle();
					    String totalQuestionCount = formPage.getTotalQuestionCount();
					    ExtentManager.getTest().info("Total number of questions on Form Page: " + totalQuestionCount+" question for: "+formTitle);
				    
					 // Closing the child window 
					    formPage.closeChildWindowAndSwitchToParent(sharePopup.getParentWindowHandle());   // Close child and switch back
					  
					 // Share popup
					    sharePopup.clickCloseIcon();
					    formdashboard.refreshPage();
					    formdashboard.clickMoreOptionsForFirstForm();
					    formdashboard.clickEditForm();
					    
					 // Builder
					    String actualBuilderText=builder.getFormBuilderButton().getText();
					    String expectedBuilderText="Builder";
					    
					 // Assert that actual FormBuilder Text and expected FormBuilder Text 
					    Assert.assertEquals(actualBuilderText, expectedBuilderText, "Actual form title does not match expected title");
					    ExtentManager.getTest().pass("Form builder screen displayed successfully after editing without form responses: <b>"+formTitle+"</b>");

	
					    
	}			    
	    
}


/**
Scenario: Test class to verify editing a form with no responses submitted yet.

Steps:
1. Login to SurveyHeart using valid credentials.
2. Create a new form with a Short Answer question, enable 'Allow Multiple Responses', and submit the form.
3. Without submitting any responses, edit the form from More Options.

Expected Result:
- The Form Builder opens successfully without any edit warning popup.
*/





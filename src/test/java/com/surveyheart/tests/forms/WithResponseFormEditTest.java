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
import com.surveyheart.pages.SubmittedPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Test class to verify that editing a form with existing responses displays a warning popup 
    and allows navigation to the Form Builder page after acknowledging the warning. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class WithResponseFormEditTest extends BaseTest {
	
	    @Test 
	    public void verifyFormEditWithResponse() {
	    	
	     // Login page - Initialize the Login Page object with the current WebDriver instance
			SurveyHeartLoginPage loginPage = new SurveyHeartLoginPage(driver);
							loginPage.clickSignInUsingEmail();
							loginPage.enterEmail("gofaw36836@pacfut.com");
							loginPage.clickNext();
							loginPage.enterPassword("Automation@1");
							loginPage.clickSignIn();
							loginPage.closeFeatureSpotlightIfPresent();                     // In case popup appears after login
							ExtentManager.getTest().pass("Login successful with email and password.");
					
		 // Form Dashboard - Initialize the Form Dashboard Page object with the current WebDriver instance
			FormDashboardPage formdashboard = new FormDashboardPage(driver);
							formdashboard.refreshPage();
							formdashboard.clickCreateFormButton();       // Click on +Create Form button
	    	
		 // Form Builder - Initialize the Form builder object with the current WebDriver instance
	        FormBuilderPage builder = new FormBuilderPage(driver);

					     // Enter Form title in Builder screen
					        String dynamicFormTitle = "FormEditWithResponse " + System.currentTimeMillis();
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
							  sharePopup.clickViewIcon();
							  sharePopup.getParentWindowHandle();
				   
			               // ✅ Store parent window before switching
			                  sharePopup.storeParentWindowHandle();
			                  sharePopup.switchToChildWindowThroughViewIcon();
						
						
			// Form page - Initialize the Form page object with the current WebDriver instance
			   FormPage formPage = new FormPage(driver);
					   		  formPage.clickStartButton();
							  String formTitle = formPage.getFormTitle();
							  formPage.answerShortText("Entered short answer");
							  String totalQuestionCount = formPage.getTotalQuestionCount();
							  ExtentManager.getTest().info("Total number of questions on Form Page: " + totalQuestionCount); 
							  formPage.clickSubmitButton();
							  ExtentManager.getTest().pass("Form response submitted successfully on form page: <b>"+formTitle+"</b>");

			
							  
			// Submitted page - Initialize the Submitted page object with the current WebDriver instance
			   SubmittedPage submittedPage = new SubmittedPage(driver);
			   				 submittedPage.isSubmittedMessageDisplayed();
			   				 
			   				 submittedPage.closeChildWindowAndSwitchToParent(sharePopup.getParentWindowHandle());
							  
							  
						  // Share popup
			   				 sharePopup.clickCloseIcon();
							  
			   			  // Come back to Form Dashboard
			   				 formdashboard.refreshPage();
			   				 formdashboard.clickMoreOptionsForFirstForm();
			   				 formdashboard.clickEditForm();
			   				 
			   			  // Get texts
			   				 String warningPopupTitle = formdashboard.getFormEditWarningPopupTitle().getText();
			   				 String warningPopupMessage = formdashboard.getFormEditWarningMessage().getText();
			   				 ExtentManager.getTest().pass("Form Edit warning popup title with response form: <b>" + warningPopupTitle + "</b>");
			   				 ExtentManager.getTest().pass("Form Edit warning popup message with response form: <b>" + warningPopupMessage + "</b>");

			   			  // Edit Form Warning popup
							 String actualWarningTitle=formdashboard.getFormEditWarningPopupTitle().getText();
							 String expectedWarningTitle="WARNING";
							    
						  // Assert that actual FormBuilder Text and expected FormBuilder Text 
							 Assert.assertEquals(actualWarningTitle, expectedWarningTitle, "Warning popup is not displayed for response form");
							 ExtentManager.getTest().pass("Warning popup verified successfully during form edit with response: <b>" + formTitle + "</b>"); 
			   						   				 
			   				 formdashboard.clickEditButtonOnWarningPopup();
		     
			   			  // Builder
							 String actualBuilderText=builder.getFormBuilderButton().getText();
							 String expectedBuilderText="Builder";
							    
						  // Assert that actual FormBuilder Text and expected FormBuilder Text 
							 Assert.assertEquals(actualBuilderText, expectedBuilderText, "Form builder screen is not displayed");
							 ExtentManager.getTest().pass("Form Builder screen displayed successfully after editing with form response: <b>"+formTitle+"</b>");		   				 
			   				 
	
							 
	}

}


/**
Scenario: Test class to verify editing a form with existing responses shows an edit warning pop-up.

Steps:
1. Login to SurveyHeart using valid credentials.
2. Create a new form with a Short Answer question and enable 'Allow Multiple Responses'.
3. Submit a response via the form link.
4. Edit the form from More Options and verify that the Edit warning popup appears.
5. Click the Edit button on the warning popup and ensure the Form Builder opens successfully.

Expected Result:
- An Edit warning pop-up is displayed for forms with existing responses.
- Clicking the Edit button successfully navigates to the Form Builder.
*/




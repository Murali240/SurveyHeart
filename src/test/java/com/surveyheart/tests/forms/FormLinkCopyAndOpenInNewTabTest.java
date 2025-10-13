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
import com.surveyheart.pages.OverviewPage;
import com.surveyheart.pages.SharePopupPage;
import com.surveyheart.pages.SubmittedPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Test class to verify copied form link opens in new tab, allows response submission, and validate response correctly in Overview page. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class FormLinkCopyAndOpenInNewTabTest extends BaseTest {
	
	    @Test (groups = "sanity", priority = 13)
	    public void verifyFormLinkCopyAndOpenInNewTabFunctionality() {
	    	
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
	                        String dynamicFormTitle = "CopyFormLink " + System.currentTimeMillis();
	                        builder.enterFormTitle(dynamicFormTitle);
					        //builder.enterFormTitle("CopyFormLink " + System.currentTimeMillis());
					
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
							 ExtentManager.getTest().info("Form created successfully with : "+dynamicFormTitle); 
			        
				    
			// SharePopup - Initialize the Share popup object with the current WebDriver instance
			   SharePopupPage sharePopup = new SharePopupPage(driver);
			                  sharePopup.storeParentWindowHandle();
							  sharePopup.openCopiedFormInNewTab();
						
						
			// Form page - Initialize the Form page object with the current WebDriver instance
			   FormPage formPage = new FormPage(driver);
					   		  formPage.clickStartButton();
							  String formTitle = formPage.getFormTitle();
							  formPage.answerShortText("Entered short answer");
							  String totalQuestionCount = formPage.getTotalQuestionCount();
							  ExtentManager.getTest().info("Form page displayed with " + totalQuestionCount + " question."); 
							  formPage.clickSubmitButton();
							  ExtentManager.getTest().pass("Entered response for given question and successfully submitted form : "+formTitle);
							  
			// Form page - Initialize the Form page object with the current WebDriver instance
			   SubmittedPage submittedPage = new SubmittedPage(driver);
			   				 submittedPage.isSubmittedMessageDisplayed();
			   				 submittedPage.closeChildWindowAndSwitchToParent(sharePopup.getParentWindowHandle());
							  
							  
						  // Share popup
			   				 sharePopup.clickCloseIcon();
							  
			   			  // Come back to Form Dashboard
			   				 formdashboard.refreshPage();
			   				 formdashboard.clickMoreOptionsForFirstForm();
			   				 formdashboard.clickViewResponsesButton();
			   				 
			   				 
			   				 
			 // Form page - Initialize the Form page object with the current WebDriver instance
			  	OverviewPage overview = new OverviewPage(driver);
			  	
						  	 String response = overview.getFirstQuestionResponseText();
						  	 ExtentManager.getTest().info("First question response text : " + response);
						
						  	 String actualAnswer = response;
					         String expextedAnswer = "Entered short answer";
					         
					         Assert.assertEquals(actualAnswer, expextedAnswer, "Response text does not match with expected value");
					         ExtentManager.getTest().pass("Form response matched with expected value : " + actualAnswer);

		         
					         
    }

}


/**
Scenario: Test class to verify that a copied form link opens in a new tab, allows response submission, and validates the response correctly in the Overview page.

Steps:
1. Login, create a new form with a dynamic title and a Short Answer question titled "What is your name?", enable 'Allow Multiple Responses', and submit the form.
2. Copy the form link from the Share popup and open it in a new browser tab.
3. Start the form, submit a response for the Short Answer question, and verify that the submission message is displayed.
4. Close the new tab, return to the Form Dashboard, and refresh.
5. Go to More Options → View Responses and confirm that the submitted response is displayed correctly in the Overview page.

Expected Result:
- The copied form link opens successfully, the response is submitted, and the Overview page shows the submitted response correctly.
*/



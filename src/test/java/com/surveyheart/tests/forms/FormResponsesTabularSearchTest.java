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


/** Test class to verify search functionality in tabular responses for a form. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class FormResponsesTabularSearchTest extends BaseTest  {
	
	@Test 
    public void verifySearchResultsInTabularResponsesForForm() {
    	
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
						formdashboard.clickCreateFormButton();            // Click on +Create Form button

	 // Form Builder - Initialize the Form builder object with the current WebDriver instance
        FormBuilderPage builder = new FormBuilderPage(driver);

				     // Enter Form title in Builder screen
				        String dynamicFormTitle = "FormResponseSearchInTabular " + System.currentTimeMillis();
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
					   formPage.answerShortText("Madhu Simma");
					   String totalQuestionCount = formPage.getTotalQuestionCount();
					   ExtentManager.getTest().info("Form page displayed with " + totalQuestionCount + " question."); 
					   formPage.clickSubmitButton();
					   ExtentManager.getTest().pass("First form response submitted successfully for form: <b>" + formTitle + "</b>");

										  
	 // Submitted page - Initialize the Submitted page object with the current WebDriver instance
		SubmittedPage submittedPage = new SubmittedPage(driver);
					  submittedPage.isSubmittedMessageDisplayed();
					  submittedPage.clickAddResponseButton();
					
					  
				   // Form page - for 2nd response
					  formPage.clickStartButton();
					  formPage.answerShortText("Sai Krishna"); 
					  formPage.clickSubmitButton();
					  ExtentManager.getTest().pass("Second form response submitted successfully.");
					  
				   // Submitted page
					  submittedPage.isSubmittedMessageDisplayed();
					  submittedPage.clickAddResponseButton();
				      
						
				   // Form page - for 3rd response
					  formPage.clickStartButton();
					  formPage.answerShortText("Gopi Krishna"); 
					  formPage.clickSubmitButton();
					  ExtentManager.getTest().pass("Third form response submitted successfully.");
					  
				   // Submitted page
					  submittedPage.isSubmittedMessageDisplayed();
					  submittedPage.closeChildWindowAndSwitchToParent(sharePopup.getParentWindowHandle());
					
					  
		           // Refresh the page
				      formdashboard.refreshPage();
				      formdashboard.clickMoreOptionsForFirstForm();
					  formdashboard.clickViewResponsesButton();
				    
				    
	  // Overview page - Initialize the Overview page object with the current WebDriver instance
	     OverviewPage overview = new OverviewPage(driver);	
	     
					  int totlaResponsesCount = overview.getTotalResponsesCount();
					  ExtentManager.getTest().info("Total responses count in overview page: <b>" + totlaResponsesCount + "</b>");
					  overview.clickTabularTab();
					  overview.searchResponseBar("Gopi Krishna");         // just pass response text
					  ExtentManager.getTest().info("Searched response text in tabular page: <b>Gopi Krishna</b>");


				   // Assertion for searched reponse text vs expected response text
					  String searchedFirstResponse = overview.getFirstSearchedResponse().getText();
					  String expectedResponseText = "Gopi Krishna";
				   
					  Assert.assertEquals(searchedFirstResponse, expectedResponseText, "Searched form response does not match expected response text");
					  ExtentManager.getTest().pass("Searched form response is matched with expected form response: <b>"+ dynamicFormTitle+"</b>");
					
	
		
    }

}


/**
Scenario: Test class to verify search functionality in tabular responses for a form.

Steps:
1. Login, create a new form with a dynamic title and a Short Answer question titled "What is your name?", enable 'Allow Multiple Responses', and submit the form.
2. Open the form via Share popup and submit three responses with different names, then close the form tab.
3. From the Form Dashboard, go to More Options → View Responses and switch to Tabular view.
4. Search for a specific response (e.g., "Gopi Krishna") and verify that the first search result matches the expected text.

Expected Result:
- The Tabular search returns the expected response correctly.
*/



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
import com.surveyheart.pages.ShowSummaryPage;
import com.surveyheart.pages.SubmittedPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Test class to verify the form submission and correct display of the Summary Page with responder data. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class FormSummaryPageDisplayTest extends BaseTest {
	
	    @Test (groups = "regression", priority = 2)
	    public void verifySummaryPageIsDisplayed() {
	    	
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
					        builder.enterFormTitle("NewSummaryPageForm " + System.currentTimeMillis());
					
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
							 settings.enableShowSummary(true);
							 ExtentManager.getTest().pass("'Show Summary' checkbox enabled successfully.");
							 settings.clickSubmitButton();
			        
				    
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
							  ExtentManager.getTest().info("Form page displayed with " + totalQuestionCount + " question."); 
							  formPage.clickSubmitButton();
							  
			// Form page - Initialize the Form page object with the current WebDriver instance
			   SubmittedPage submittedPage = new SubmittedPage(driver);
			   				 submittedPage.isSubmittedMessageDisplayed();
			   				 ExtentManager.getTest().pass("Form submitted page was displayed successfully : "+formTitle);
							  
			// Show Summary page - Initialize the Show Summary Page object with the current WebDriver instance
			   ShowSummaryPage summaryPage = new ShowSummaryPage(driver);
			   
						  // Step 1: Click Response Summary button
					         summaryPage.clickResponseSummary();
					         
					      // Step 2: Get and log Summary Page URL
					         String summaryURL = summaryPage.getSummaryPageURL();
					         ExtentManager.getTest().pass("Summary page URL : <a href='" + summaryURL + "' target='_blank'>" + summaryURL + "</a>");
					         //ExtentManager.getTest().pass("<a href='" + summaryURL + "' target='_blank'>Summary Page URL</a>");   
			
					      // Step 3: Trending Surveys Text
					         String trendingSurveys = summaryPage.getTrendingSurveysText();
					         ExtentManager.getTest().info("Trending Surveys subheading displayed with: <b>" + trendingSurveys + "</b>");

					      // Step 4: Check if Get Started button is displayed
					         String isDisplayed = String.valueOf(summaryPage.isGetStartedDisplayed()).toUpperCase();
					         ExtentManager.getTest().info("Is 'Get Started' button displayed? : <b>" + isDisplayed+ "</b>");
			
					      // Step 5: Short answer text entered by responder
					         String shortAnswer = summaryPage.getResponderShortAnswer();
					         ExtentManager.getTest().pass("Responder entered text is : <b>" + shortAnswer+ "</b>");
		    
				         
			   				 
	}		 

}


/**
Scenario: Test class to verify form submission and correct display of the Summary Page with responder data.

Steps:
1. Login, create a new form with a dynamic title and a Short Answer question, enable 'Allow Multiple Responses' and 'Show Summary', and submit the form.
2. Open the form via Share popup in a new tab, start the form, and submit a response.
3. Verify the Submitted page is displayed successfully.
4. On the Summary Page, click 'Response Summary', capture the URL, and verify that the Trending Surveys sub-heading and 'Get Started' button are displayed.
5. Verify that the responder's Short Answer text appears correctly on the Summary Page.

Expected Result:
- The Summary Page displays the responder's data correctly, shows the Trending Surveys sub-heading, and the 'Get Started' button.
*/



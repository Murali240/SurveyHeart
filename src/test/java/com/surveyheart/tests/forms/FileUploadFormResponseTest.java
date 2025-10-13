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
import com.surveyheart.pages.SubmittedPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Test class to verify file upload question response submission in a form. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class FileUploadFormResponseTest extends BaseTest {
	
	    @Test (groups = "regression", priority = 1)
	    public void verifyFileUploadQuestionResponseForForm() {
	
	 // Login page - Initialize the Login Page object with the current WebDriver instance
	    SurveyHeartLoginPage loginPage = new SurveyHeartLoginPage(driver);
					loginPage.clickSignInUsingEmail();
					loginPage.enterEmail("gofaw36836@pacfut.com");
					loginPage.clickNext();
					loginPage.enterPassword("Automation@1");
					loginPage.clickSignIn();
					loginPage.closeFeatureSpotlightIfPresent();             // In case popup appears after login
					ExtentManager.getTest().pass("Login successful with email and password.");
			
     // Form Dashboard - Initialize the Form Dashboard Page object with the current WebDriver instance
	    FormDashboardPage formdashboard = new FormDashboardPage(driver);
					formdashboard.refreshPage();
					formdashboard.clickCreateFormButton();       // Click on +Create Form button
	
	 // Form Builder - Initialize the Form builder object with the current WebDriver instance
        FormBuilderPage builder = new FormBuilderPage(driver);

				 // Enter Form title in Builder screen
				    builder.enterFormTitle("FileUploadForm " + System.currentTimeMillis());
				    
				 // 12. File Upload
				   // builder.clickAddQuestionAfter(0);
			        builder.clickInitialAddQuestion();
			        builder.selectQuestionType(QuestionType.FILE_UPLOAD);
			        builder.enterQuestionTitle(0, "Upload your ID proof:");
			        
	  // Form Settings - Initialize the Form settings object with the current WebDriver instance
		 FormSettingsPage settings = new FormSettingsPage(driver);
					settings.clickSettingsButton();
					settings.clickControlTab();
					settings.enableAllowMultipleResponses(true); 
					ExtentManager.getTest().pass("'Allow Multiple Response' checkbox enabled successfully.");
					settings.clickSubmitButton();
					        
						    
	  // SharePopup - Initialize the Share popup object with the current WebDriver instance
		 SharePopupPage sharePopup = new SharePopupPage(driver);
					sharePopup.clickViewIcon();
					sharePopup.switchToChildWindowThroughViewIcon();
								
								
	  // Form page - Initialize the Form page object with the current WebDriver instance
		 FormPage formPage = new FormPage(driver);
					formPage.clickStartButton();
					formPage.enterNameIfPresent("Sounder");
					String formTitle = formPage.getFormTitle();
					formPage.uploadFile("C:\\Users\\mural\\Downloads\\video.mp4");
					String totalQuestionCount = formPage.getTotalQuestionCount();
					ExtentManager.getTest().info("Form page displayed with " + totalQuestionCount + " questions."); 
					formPage.clickSubmitButton();
					ExtentManager.getTest().pass("File uploaded and form submitted successfully : " + formTitle);		        
				
	  // Submitted page - Initialize the Submitted page object with the current WebDriver instance
		 SubmittedPage submittedPage = new SubmittedPage(driver);
					submittedPage.isSubmittedMessageDisplayed();
					ExtentManager.getTest().pass("'Submitted' page displayed successfully with the '+Add Response' button.");
			

					
	}

}


/**
Scenario: Test class to verify File Upload question response submission in a form.

Steps:
1. Login and create a new form with a dynamic title, adding a File Upload question.
2. Enable 'Allow Multiple Responses' in Settings and submit the form.
3. Open the form via the Share popup, start the form, enter responder details if required, upload a file, and submit.
4. Verify that the 'Submitted' page is displayed.

Expected Result:
- File Upload question is submitted successfully, and the 'Submitted' page is displayed.
*/




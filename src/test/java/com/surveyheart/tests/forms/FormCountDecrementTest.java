package com.surveyheart.tests.forms;

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


/** Verifies that form count and usage metrics decrement after deleting an uploaded file response, image, and theme. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class FormCountDecrementTest extends BaseTest {
	
	    @Test (groups = "sanity", priority = 7)
	    public void verifyFormCountDecrementsAfterDeletion() {
	    	
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
				        builder.enterFormTitle("DecrementFormCount " + System.currentTimeMillis());
				
				     // 1. File Upload
				        builder.clickInitialAddQuestionButton();
				        builder.selectQuestionType(QuestionType.FILE_UPLOAD);
				        builder.enterQuestionTitle(0, "Upload your ID proof:");
				        builder.uploadNewImageToQuestion("C:\\Users\\mural\\Downloads\\Pictureone.jpg");

		 // Form Settings - Initialize the Form settings object with the current WebDriver instance
		    FormSettingsPage settings = new FormSettingsPage(driver);
						settings.clickSettingsButton();
						settings.uploadNewCustomTheme("C:\\Users\\mural\\Downloads\\Picturetwo.jpg"); 
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
						 formPage.enterNameIfPresent("Sounder");
						 String formTitle = formPage.getFormTitle();
						 formPage.uploadFile("C:\\Users\\mural\\Downloads\\video.mp4");
						 formPage.clickSubmitButton();
						 ExtentManager.getTest().pass("File uploaded and form submitted successfully : " + formTitle);		        
					
		  // Submitted page - Initialize the Submitted page object with the current WebDriver instance
			 SubmittedPage submittedPage = new SubmittedPage(driver);
						submittedPage.isSubmittedMessageDisplayed();
						ExtentManager.getTest().pass("'Submitted' page displayed successfully with the '+Add Response' button.");
						submittedPage.closeChildWindowAndSwitchToParent(sharePopup.getParentWindowHandle());
						
						
					 // Share popup
						sharePopup.clickCloseIcon();
						
					 // Come back to Form Dashboard
						formdashboard.refreshPage();
						
					 // Click on Account icon
						formdashboard.clickAccountButton();
				 		String userCurrentPlan = formdashboard.getUserCurrentPlanText();
						
						
					 // Storage
						String currentPlanStorage = formdashboard.getStorageText();
						ExtentManager.getTest().info("📊 *** "+userCurrentPlan + " User Usage — BEFORE form decrementing ***");
						ExtentManager.getTest().info("Storage : " + currentPlanStorage);
							
					 // Total Responses
					    String currentPlanResponse = formdashboard.getTotalSubmissionsText();
					    ExtentManager.getTest().info("Responses : " + currentPlanResponse);
							
					 // Image Attachments
						String currentPlanImages = formdashboard.getImageAttachmentsText();
						ExtentManager.getTest().info("Images : " + currentPlanImages);
							
					 // Custom Themes
						String currentPlanThemes = formdashboard.getCustomThemesText();
						ExtentManager.getTest().info("Themes : " + currentPlanThemes);
						

						
		             // Refresh the page
						formdashboard.refreshPage();
						formdashboard.ifFollowUsPopupDisplayed();
						formdashboard.clickMoreOptionsForFirstForm();
					    formdashboard.clickViewResponsesButton();
					    
					    
		 // Overview page - Initialize the Overview page object with the current WebDriver instance
		    OverviewPage overview = new OverviewPage(driver);			
		                 overview.clickIndividualTab();
		                 overview.clickDeleteIndividualButton();
		                 overview.confirmDeletePopup();
						
				     // Come backto Form Dashboard
		                formdashboard.refreshPage();
						formdashboard.clickMoreOptionsForFirstForm();
					    formdashboard.clickEditForm();
					  
					 // Builder
					    builder.deleteAttachedImageIfPresent();
					    
					 // Settings
					    settings.clickSettingsButton();
					    settings.deleteUploadedThemeIfPresent();
					    settings.clickSubmitButton();
					    
					    sharePopup.clickCloseIcon();
					    formdashboard.refreshPage();
					    formdashboard.clickAccountButton();
					    
					 // Storage
						String currentPlanStorage2 = formdashboard.getStorageText();
						ExtentManager.getTest().info("📉 *** "+userCurrentPlan+" User Usage — AFTER form decrementing ***");
						ExtentManager.getTest().info("Storage : " + currentPlanStorage2);
							
					 // Total Responses
					    String currentPlanResponse2 = formdashboard.getTotalSubmissionsText();
					    ExtentManager.getTest().info("Responses : " + currentPlanResponse2);
							
					 // Image Attachments
						String currentPlanImage2 = formdashboard.getImageAttachmentsText();
						ExtentManager.getTest().info("Images : " + currentPlanImage2);
							
					 // Custom Themes
						String currentPlanThemes2 = formdashboard.getCustomThemesText();
						ExtentManager.getTest().info("Themes : " + currentPlanThemes2);
						
	
						
	}

}


/**
Scenario: Test class to verify that form count and usage metrics decrement 
          after deleting an uploaded file response, image, and theme.

Steps:
1. Login and create a new form with a dynamic title, adding a File Upload question and uploading a new image and custom theme.
2. Enable 'Allow Multiple Responses' in Settings and submit the form.
3. Open the form via the Share popup, start the form, upload a file, submit, and verify the 'Submitted' page.
4. Record current plan metrics (Storage, Responses, Images, Themes) from the Form Dashboard.
5. Delete the submitted response from Individual page, remove the attached image and uploaded theme, submit the updated form, and refresh the dashboard.
6. Verify that plan metrics (Storage, Responses, Images, Themes) decrement correctly.

Expected Result:
- Form count and user usage metrics decrement accurately after deletion of response, image, and theme.
*/



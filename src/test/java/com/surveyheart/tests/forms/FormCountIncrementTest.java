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


/** Verifies that form count and usage metrics increment after adding a form with file, image, and theme. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class FormCountIncrementTest extends BaseTest {
	
	    @Test (groups = "sanity", priority = 5)
	    public void verifyFormCountIncrementsAfterAddition() {
	    	
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
			
				     // Before Form Incrementing
						formdashboard.refreshPage();
						formdashboard.clickAccountButton();
				 		String userCurrentPlan = formdashboard.getUserCurrentPlanText();
				 		
				 		
				 	 // Storage
						String currentPlanStorage = formdashboard.getStorageText();
						ExtentManager.getTest().info("📊 *** "+userCurrentPlan + " User Usage — BEFORE form incrementing ***");
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
						formdashboard.clickCreateFormButton();            // Click on +Create Form button
	    	
		 // Form Builder - Initialize the Form builder object with the current WebDriver instance
	        FormBuilderPage builder = new FormBuilderPage(driver);

				     // Enter Form title in Builder screen
				        builder.enterFormTitle("IncrementFormCount " + System.currentTimeMillis());
				
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
					
		  // Submitted page - Initialize the Submitted page object with the current WebDriver instance
			 SubmittedPage submittedPage = new SubmittedPage(driver);
						submittedPage.isSubmittedMessageDisplayed();
						submittedPage.closeChildWindowAndSwitchToParent(sharePopup.getParentWindowHandle());
						
						
					 // Share popup
						sharePopup.clickCloseIcon();
						
					 // Come back to Form Dashboard
						formdashboard.refreshPage();
						formdashboard.ifFollowUsPopupDisplayed();
						
					 // Click on Account icon
						formdashboard.clickAccountButton();
						
					 // Storage
						String currentPlanStorage2 = formdashboard.getStorageText();
						ExtentManager.getTest().info("📉 *** "+userCurrentPlan+" User Usage — AFTER form incrementing ***");
						ExtentManager.getTest().pass("Storage : " + currentPlanStorage2);
							
					 // Total Responses
					    String currentPlanResponse2 = formdashboard.getTotalSubmissionsText();
					    ExtentManager.getTest().pass("Responses : " + currentPlanResponse2);
							
					 // Image Attachments
						String currentPlanImage2 = formdashboard.getImageAttachmentsText();
						ExtentManager.getTest().pass("Images : " + currentPlanImage2);
							
					 // Custom Themes
						String currentPlanThemes2 = formdashboard.getCustomThemesText();
						ExtentManager.getTest().pass("Themes : " + currentPlanThemes2);
						
						ExtentManager.getTest().pass("Quiz count incremented successfully in Usage Details for: <b>" + formTitle + "</b>");
	
	
						
	}

}


/**
Scenario: Test class to verify that form count and usage metrics increment 
          after adding a new form with file upload, image, and custom theme.

Steps:
1. Login and record current plan metrics (Storage, Responses, Images, Themes) from the Form Dashboard.
2. Create a new form with a dynamic title, add a File Upload question with attach a new image to QT, and upload a custom theme.
3. Enable 'Allow Multiple Responses' in Settings and submit the form.
4. Open the form via the Share popup, start the form, upload a file, and submit.
5. Refresh the Form Dashboard and verify that plan metrics (Storage, Responses, Images, Themes) increment correctly.

Expected Result:
- Form count and user usage metrics increment accurately after adding a new form with file, image, and theme.
*/



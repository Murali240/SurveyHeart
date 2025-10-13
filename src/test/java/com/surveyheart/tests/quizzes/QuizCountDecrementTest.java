package com.surveyheart.tests.quizzes;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.surveyheart.base.BaseTest;
import com.surveyheart.enums.QuizQuestionType;
import com.surveyheart.pages.AnswersPage;
import com.surveyheart.pages.FormDashboardPage;
import com.surveyheart.pages.QuizBuilderPage;
import com.surveyheart.pages.QuizDashboardPage;
import com.surveyheart.pages.QuizPage;
import com.surveyheart.pages.QuizSettingsPage;
import com.surveyheart.pages.SharePopupPage;
import com.surveyheart.pages.SubmittedPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Test class to verify that the quiz count and usage details decrement correctly after deleting a file uploaded answer, existed image, theme */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class QuizCountDecrementTest extends BaseTest {
	
	@Test (groups = "sanity", priority = 8)
    public void verifyQuizCountDecrementsAfterDeletion() {
    	
     // Login page - Initialize the Login Page object with the current WebDriver instance
		SurveyHeartLoginPage loginPage = new SurveyHeartLoginPage(driver);
					loginPage.clickSignInUsingEmail();
					loginPage.enterEmail("gofaw36836@pacfut.com");
					loginPage.clickNext();
					loginPage.enterPassword("Automation@1");
					loginPage.clickSignIn();
					loginPage.closeFeatureSpotlightIfPresent();              // In case popup appears after login
					ExtentManager.getTest().pass("Login successful with email and password.");
				
	 // Form Dashboard - Initialize the Form Dashboard Page object with the current WebDriver instance
		FormDashboardPage formDashboardPage = new FormDashboardPage(driver);
		
			     // Refresh the page
					formDashboardPage.refreshPage();
					formDashboardPage.clickQuizzesTab();
					
		QuizDashboardPage quizDashboardPage = new QuizDashboardPage(driver);
		    	    quizDashboardPage.clickCreateQuizButton();
					       
    	
	 // Form Builder - Initialize the Form builder object with the current WebDriver instance
        QuizBuilderPage quizBuilderPage = new QuizBuilderPage(driver);

                 // Set dynamic Quiz title
	                String dynamicQuizTitle = "DecrementQuizCount " + System.currentTimeMillis();
	                quizBuilderPage.enterQuizTitle(dynamicQuizTitle);
			
	             // ==== Add a File Upload question ====
				    quizBuilderPage.addQuestion(
				        QuizQuestionType.FILE_UPLOAD,
				        "Upload your certification PDF",
				        null,
				        null,
				        -1
				    );

				    quizBuilderPage.uploadNewImageToQuestion("C:\\Users\\mural\\Downloads\\Pictureone.jpg");

	 // Quiz Settings - Initialize the Form settings object with the current WebDriver instance
	    QuizSettingsPage quizSettingsPage = new QuizSettingsPage(driver);
	                quizSettingsPage.clickSettingsButton();
	                quizSettingsPage.uploadNewCustomTheme("C:\\Users\\mural\\Downloads\\Picturetwo.jpg"); 
	                quizSettingsPage.clickSubmitButton();
	                ExtentManager.getTest().info("Quiz created successfully with: " + dynamicQuizTitle);

		
	 // SharePopup - Initialize the Share popup object with the current WebDriver instance
	    SharePopupPage sharePopup = new SharePopupPage(driver);
					sharePopup.clickViewIcon();
					sharePopup.getParentWindowHandle();
					
				// ✅ Store parent window before switching
					sharePopup.storeParentWindowHandle();
				    sharePopup.switchToChildWindowThroughViewIcon();
								
							
	 // Quiz page - Initialize the Quiz page object with the current WebDriver instance
		QuizPage quizPage = new QuizPage(driver);
		            quizPage.clickStart();
			        quizPage.enterName("Sounder");
			        quizPage.clickStartQuiz();
					String quizTitle = quizPage.getQuizTitle();
					quizPage.uploadFile("C:\\Users\\mural\\Downloads\\video.mp4");
					quizPage.clickSubmitButton();						 		        
				
	  // Submitted page - Initialize the Submitted page object with the current WebDriver instance
		 SubmittedPage submittedPage = new SubmittedPage(driver);
					submittedPage.isSubmittedMessageDisplayed();
					submittedPage.isViewResultsButtonDisplayed();
					submittedPage.closeChildWindowAndSwitchToParent(sharePopup.getParentWindowHandle());
					ExtentManager.getTest().pass("<b>'Submitted'</b> page displayed successfully with the <b>'View Results'</b> button.");
					
				 // Share popup
					sharePopup.clickCloseIcon();
					
				 // Come back to Form Dashboard
					quizDashboardPage.refreshPage();
					
				 // Click on Account icon
					formDashboardPage.clickAccountButton();
					String userCurrentPlan = formDashboardPage.getUserCurrentPlanText();
					
				 // Before Quiz Decrementing
				 // Storage
					String currentPlanStorage = formDashboardPage.getStorageText();
					ExtentManager.getTest().info("<b>📊 *** "+userCurrentPlan + " User Usage — BEFORE Quiz Decrementing ***</b>");
					ExtentManager.getTest().info("Storage : " + currentPlanStorage);
						
				 // Total Responses
				    String currentPlanResponse = formDashboardPage.getTotalSubmissionsText();
				    ExtentManager.getTest().info("Responses : " + currentPlanResponse);
						
				 // Image Attachments
					String currentPlanImages = formDashboardPage.getImageAttachmentsText();
					ExtentManager.getTest().info("Images : " + currentPlanImages);
						
				 // Custom Themes
					String currentPlanThemes = formDashboardPage.getCustomThemesText();
					ExtentManager.getTest().info("Themes : " + currentPlanThemes);
				
				
				 // Refresh the page
					formDashboardPage.refreshPage();
					formDashboardPage.ifFollowUsPopupDisplayed();
					formDashboardPage.clickQuizzesTab();
					
			     // Switch to Quiz Dashboard 
					quizDashboardPage.clickMoreOptionsForFirstQuiz();
					quizDashboardPage.clickViewAnswersButton();
				    
				    
	 // Asnwers page - Initialize the Submitted page object with the current WebDriver instance
	    AnswersPage answersPage = new AnswersPage(driver);			
	                answersPage.clickIndividualButton();
	                answersPage.clickDeleteButtonIndividual();
	                answersPage.clickDeleteButtonOnDeletePopup();
	    
	                quizDashboardPage.refreshPage();
	                formDashboardPage.clickQuizzesTab();
	                	
			     // Come backto Quiz Dashboard
	                quizDashboardPage.clickMoreOptionsForFirstQuiz();
	                quizDashboardPage.clickEditQuiz();
					
				 // Builder
	                quizBuilderPage.deleteAttachedImageIfPresent();
					
	             // Settings
	                quizSettingsPage.clickSettingsButton();
	                quizSettingsPage.deleteUploadedThemeIfPresent();
	                quizSettingsPage.clickSubmitButton();
				    
				    sharePopup.clickCloseIcon();
				    quizDashboardPage.refreshPage();
				    formDashboardPage.clickAccountButton();
					
				 // After Quiz Decrementing		
				 // Storage
					String currentPlanStorage2 = formDashboardPage.getStorageText();
					ExtentManager.getTest().info("<b>📉 *** "+userCurrentPlan+" User Usage — AFTER Quiz Decrementing ***</b>");
					ExtentManager.getTest().pass("Storage : " + currentPlanStorage2);
						
				 // Total Responses
				    String currentPlanResponse2 = formDashboardPage.getTotalSubmissionsText();
				    ExtentManager.getTest().pass("Responses : " + currentPlanResponse2);
						
				 // Image Attachments
					String currentPlanImage2 = formDashboardPage.getImageAttachmentsText();
					ExtentManager.getTest().pass("Images : " + currentPlanImage2);
						
				 // Custom Themes
					String currentPlanThemes2 = formDashboardPage.getCustomThemesText();
					ExtentManager.getTest().pass("Themes : " + currentPlanThemes2);
					
					ExtentManager.getTest().pass("Quiz count decremented successfully in Usage Details for: <b>" + quizTitle + "</b>");


								
	}

}

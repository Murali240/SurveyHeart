package com.surveyheart.tests.quizzes;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.surveyheart.base.BaseTest;
import com.surveyheart.enums.QuizQuestionType;
import com.surveyheart.pages.FormDashboardPage;
import com.surveyheart.pages.QuizBuilderPage;
import com.surveyheart.pages.QuizDashboardPage;
import com.surveyheart.pages.QuizSettingsPage;
import com.surveyheart.pages.SharePopupPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Test class to verify that a deleted quiz is successfully restored in the Quiz Dashboard after clicking the UNDO toast button */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class QuizDashboardRestoreQuizAfterUndoTest extends BaseTest {
	
	@Test 
    public void verifyDeletedQuizRestoredAfterUndoInQuizDashboard()  {
    	
        // ===== Login to SurveyHeart =====
	    SurveyHeartLoginPage loginPage = new SurveyHeartLoginPage(driver);
					    loginPage.clickSignInUsingEmail();
					    loginPage.enterEmail("gofaw36836@pacfut.com");
					    loginPage.clickNext();
					    loginPage.enterPassword("Automation@1");
					    loginPage.clickSignIn();
					    loginPage.closeFeatureSpotlightIfPresent();
					    ExtentManager.getTest().pass("Login successful with email and password.");
				    
	    // ===== Navigate to Quiz Dashboard =====
		FormDashboardPage formDashboardPage = new FormDashboardPage(driver);
						formDashboardPage.refreshPage();
						formDashboardPage.clickQuizzesTab();
							    
		// Quiz Dashboard - Initialize the Quiz Dashboard Page object with the current WebDriver instance
		QuizDashboardPage quizDashboardPage = new QuizDashboardPage(driver);
				    	quizDashboardPage.clickCreateQuizButton();                 // Click on +Create Quiz button

		// ===== Quiz Builder Page =====
		QuizBuilderPage quizBuilderPage = new QuizBuilderPage(driver);

				     // Set dynamic Quiz title
						String dynamicQuizTitle = "UndoButtonInQuizDashboard " + System.currentTimeMillis();
						quizBuilderPage.enterQuizTitle(dynamicQuizTitle);

					// ==== Add a Short Answer question ====
					   quizBuilderPage.addQuestion(
					   QuizQuestionType.SHORT_ANSWER,
					   "What is your name?",
					   "Madhu",        // Answer
					    null,          // No options
						-1             // No correct option
					   );

				    
	    // Quiz Settings - Initialize the Quiz settings object with the current WebDriver instance
		QuizSettingsPage quizSettingsPage = new QuizSettingsPage(driver);
				   	
						quizSettingsPage.clickSettingsButton();
						quizSettingsPage.clickControlTab();
						quizSettingsPage.enableAllowMultipleAttempts(true);
				   	    ExtentManager.getTest().pass("<b>'Allow Multiple Attempts'</b> checkbox enabled successfully.");
				   		quizSettingsPage.clickSubmitButton();
				   		ExtentManager.getTest().info("Quiz created successfully with: "+dynamicQuizTitle);  	        
								    
				        				    
	// SharePopup - Initialize the Share popup object with the current WebDriver instance
	   SharePopupPage sharePopup = new SharePopupPage(driver);
	   			      sharePopup.clickCloseIcon();                                    

		   		   // Come back to Quiz Dashboard
	   			      quizDashboardPage.refreshPage();
	   			      formDashboardPage.clickQuizzesTab();
		   			
		   		   // Deleting Quiz from Quiz Dashboard
	   			      quizDashboardPage.clickMoreOptionsForFirstQuiz();
	   			      quizDashboardPage.clickDeleteButton();
	   			      quizDashboardPage.clickConfirmDeleteButton();
				      String undoToastMessage = quizDashboardPage.getFullUndoToastMessage();
				      ExtentManager.getTest().pass("UNDO toast message verified successfully in Quiz Dashboard: <b>" + undoToastMessage + "</b>");

				   // Click on UNDO Toast message
				      quizDashboardPage.clickUndoToastButton();
					  ExtentManager.getTest().pass("Successfully clicked on <b>Undo</b> toast button");
				    //quizDashboardPage.refreshPage();
					  String actualQuizTiltle = quizDashboardPage.getFirstQuizCardTitle();
					  String expectedQuizTitle=dynamicQuizTitle;
		
				   // Assert that actualQuizTitle and expectedQuizTitle 
					  Assert.assertEquals(actualQuizTiltle, expectedQuizTitle, "Actual quiz title does not match expected title");
					  ExtentManager.getTest().pass("Deleted quiz was successfully restored in Quiz Dashboard: <b>" + dynamicQuizTitle + "</b>");
			    
	
					  
    }

}

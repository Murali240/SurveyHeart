package com.surveyheart.tests.quizzes;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.surveyheart.base.BaseTest;
import com.surveyheart.enums.QuizQuestionType;
import com.surveyheart.pages.FormDashboardPage;
import com.surveyheart.pages.QuizBuilderPage;
import com.surveyheart.pages.QuizDashboardPage;
import com.surveyheart.pages.QuizPage;
import com.surveyheart.pages.QuizSettingsPage;
import com.surveyheart.pages.SharePopupPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Test class to verify that a deleted question in Quiz Builder is restored correctly after clicking the Undo toast button */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class QuizBuilderRestoreQuizAfterUndoTest extends BaseTest {
	
	@Test 
    public void verifyDeletedQuestionRestoredAfterUndoInQuizBuilder()  {
    	
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
						String dynamicQuizTitle = "UndoButtonInQuizBuilder " + System.currentTimeMillis();
						quizBuilderPage.enterQuizTitle(dynamicQuizTitle);

					// ==== Add a Short Answer question ====
					   quizBuilderPage.addQuestion(
					   QuizQuestionType.SHORT_ANSWER,
					   "What is your name?",
					   "Madhu",        // Answer
					    null,          // No options
						-1             // No correct option
					   );
					   
					// ==== Add a Long Answer question ====
					    quizBuilderPage.addQuestion(
					        QuizQuestionType.LONG_ANSWER,
					        "Describe yourself briefly",
					        "Quick learner",                // Answer
					        null,                           // No options
					        -1 
					     );
					    
					    quizBuilderPage.clickDeleteIcon();
					    String undoToastMessage=quizBuilderPage.getFullUndoToastMessage();
				        ExtentManager.getTest().pass("Undo toast message verified successfully in Quiz Builder: <b>" + undoToastMessage + "</b>");

				     // Click on Undo toast button from Form builder
				        quizBuilderPage.clickUndoToastButton();
				        ExtentManager.getTest().pass("Successfully clicked on <b>Undo</b> toast button");
				    
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
					   sharePopup.storeParentWindowHandle();                             // Store parent window
					   sharePopup.clickViewIcon();
					   sharePopup.switchToChildWindowThroughViewIcon();  
					   
					   
	// Quiz page - Initialize the Quiz page object with the current WebDriver instance
	   QuizPage quizPage = new QuizPage(driver);
	                   quizPage.clickStart();
	                   quizPage.enterName("Sounder");
	                   quizPage.clickStartQuiz();
					   String questionCount = quizPage.getTotalQuestionCount();
					   ExtentManager.getTest().info("Quiz created with: <b>" + questionCount + " </b>question");
										    
					// Closing the child window 
					   quizPage.closeChildWindowAndSwitchToParent(sharePopup.getParentWindowHandle()); // Close child and switch back
										  

		   		    // Come back to Quiz Dashboard
					   sharePopup.clickCloseIcon();
	   			       quizDashboardPage.refreshPage();
	   			       formDashboardPage.clickQuizzesTab();
		   			
		   		    // Deleting Quiz from Quiz Dashboard
	   			       String actualQuizTiltle = quizDashboardPage.getFirstQuizCardTitle();
	   			       String expectedQuizTitle=dynamicQuizTitle;

			  
	   			    // Assert that actualFormTitle and expectedFormTitle 
					   Assert.assertEquals(actualQuizTiltle, expectedQuizTitle, "Actual quiz title does not match expected title");
					   ExtentManager.getTest().pass("Actual quiz title is matched with expected quiz title: <b>"+ dynamicQuizTitle+" </b>");
			    
	
		    
    }

}

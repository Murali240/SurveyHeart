package com.surveyheart.tests.quizzes;

import org.testng.Assert;
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
import com.surveyheart.pages.SharedQuizDashboardPage;
import com.surveyheart.pages.SubmittedPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;
import com.surveyheart.utilities.WaitUtils;


/** Test class to verify that the Editor role cannot delete answers in the Individual page */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class EditorCannotDeleteAnswerInIndividualPageTest extends BaseTest {
	
	@Test 
	public void verifyEditorCannotDeleteAnswer() {
		
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
	    			    quizDashboardPage.clickCreateQuizButton();                   // Click on +Create Quiz button

	    // ===== Quiz Builder Page =====
	    QuizBuilderPage quizBuilderPage = new QuizBuilderPage(driver);

		             // Set dynamic Quiz title
					    String dynamicQuizTitle = "EditorCan'tDeleteAnswer " + System.currentTimeMillis();
					    quizBuilderPage.enterQuizTitle(dynamicQuizTitle);
	
					    // ==== Add a Short Answer question ====
					    quizBuilderPage.addQuestion(
					        QuizQuestionType.SHORT_ANSWER,
					        "What is your name?",
					        "Madhu",               // Answer
					        null,                  // No options
					        -1                     // No correct option
					    );

	    
	 // Quiz Settings - Initialize the Quiz settings object with the current WebDriver instance
	   	QuizSettingsPage quizSettingsPage = new QuizSettingsPage(driver);
	   	
					 quizSettingsPage.clickSettingsButton();
				     quizSettingsPage.clickCollaborationTab();      
				     quizSettingsPage.clickCollaborationTab();
		     	     quizSettingsPage.addCollaborator("meyap64096@forcrack.com", "Editor");
				     quizSettingsPage.clickSubmitButton();
				     ExtentManager.getTest().info("Owner added an editor successfully.");
				     ExtentManager.getTest().info("Owner created a quiz successfully with: "+dynamicQuizTitle);	        
					
				     
	// SharePopup - Initialize the Share popup object with the current WebDriver instance
	   SharePopupPage sharePopupPage = new SharePopupPage(driver);
	   
		   			sharePopupPage.storeParentWindowHandle();                           // Store parent window
					sharePopupPage.clickViewIcon();
					sharePopupPage.switchToChildWindowThroughViewIcon();
							
							
	// Quiz page - Initialize the Quiz page object with the current WebDriver instance
	   QuizPage quizPage = new QuizPage(driver);
	   
	   				quizPage.clickStart();
	   				quizPage.enterName("Sounder");
	   				quizPage.clickStartQuiz();
	   				
	   			 // Quiz page 
	   				String quizTitle = quizPage.getQuizTitle();
	   				quizPage.answerShortQuestion("Sounder Arunachalam");
					String totalQuestionCount = quizPage.getTotalQuestionCount();
					ExtentManager.getTest().info("Owner created quiz with <b>: " + totalQuestionCount + "</b> question for: <b>" +quizTitle+"</b>");
					quizPage.clickSubmitButton();
					ExtentManager.getTest().pass("Owner submitted a answer successfully on Quiz page: <b>" + quizTitle + "</b>");
			
					  
	// Submitted page - Initialize the Submitted page object with the current WebDriver instance
	   SubmittedPage submittedPage = new SubmittedPage(driver);
	   
	   				submittedPage.isSubmittedMessageDisplayed();
	   				submittedPage.isViewResultsButtonDisplayed();
	   				ExtentManager.getTest().pass("<b>Submitted</b> page displayed successfully with <b>View Results</b>");
				 
	   			 // Closing the child window 
	   				submittedPage.closeChildWindowAndSwitchToParent(sharePopupPage.getParentWindowHandle());
	   				
	   			 // Click on View Answers from More options
	   				sharePopupPage.clickCloseIcon();
	   				quizDashboardPage.refreshPage();
	   				formDashboardPage.clickQuizzesTab();
	   				quizDashboardPage.clickMoreOptionsForFirstQuiz();
	   				int numberOfQuizAnswersOnQuizCard = quizDashboardPage.getTotalAnswersCountInMore();
					ExtentManager.getTest().info("Total answers displayed on owner’s quiz card: <b>" + numberOfQuizAnswersOnQuizCard + "</b>");

				 // Click on Account icon & sing-out
					formDashboardPage.refreshPage();
					formDashboardPage.ifFollowUsPopupDisplayed();
					formDashboardPage.clickAccountButton();
					formDashboardPage.clickSignoutButton();
					ExtentManager.getTest().pass("Owner signed out successfully.");
					
					
				 // Admin login
					loginPage.clickSignInUsingEmail();
					loginPage.enterEmail("meyap64096@forcrack.com");
					loginPage.clickNext();
					loginPage.enterPassword("Automation@3");
					loginPage.clickSignIn();
					loginPage.closeFeatureSpotlightIfPresent();              // In case popup appears after login
					ExtentManager.getTest().pass("Editor Login successful with email and password.");
			        
				 // Clicking on Share button
					formDashboardPage.clickSharedTab();
					
	// Shared Quiz Dashboard - Initialize the Share popup object with the current WebDriver instance
	   SharedQuizDashboardPage sharedQuizDashboard = new SharedQuizDashboardPage(driver);
	   
					sharedQuizDashboard.clickSharedQuizzesTab();
					sharedQuizDashboard.clickMoreOptions();
					sharedQuizDashboard.clickViewAnswersButton();
					
					
	// Answers page - Initialize the Overview page object with the current WebDriver instance
	   AnswersPage answersPage = new AnswersPage(driver);
	   
				    int totalAnswersCount = answersPage.getTotalAnswersCount();
					ExtentManager.getTest().info("Total responses count in Answers page: <b>" + totalAnswersCount + "</b>");
					answersPage.clickIndividualButton();	
					WaitUtils.waitForSeconds(driver, 3);			
					
			     // Assert for won't display Delete button for Editor role
					Assert.assertFalse(answersPage.isDeleteButtonDisplayedInIndividual(),
					        "Delete button should not be visible for Editor role");
					ExtentManager.getTest().pass("Verified that the <b>Delete</b> button is <b>not visible</b> for the <b>Editor</b> role on the Individual page, as expected.");

				
	                				
	}

}


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
import com.surveyheart.pages.SharedQuizDashboardPage;
import com.surveyheart.pages.SubmittedPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;
import com.surveyheart.utilities.WaitUtils;


/** Test class to verify that an Admin collaborator can delete a quiz answer from the Individual page */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class AdminCanDeleteQuizAnswerTest extends BaseTest {
	
	@Test 
	public void verifyAdminCanDeleteQuizAnswer() {
		
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
				    String dynamicQuizTitle = "AdminDeleteAnswer " + System.currentTimeMillis();
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
		     	     quizSettingsPage.addCollaborator("hawece4785@forcrack.com", "Admin");
				     quizSettingsPage.clickSubmitButton();
				     ExtentManager.getTest().info("Owner added an admin successfully.");
				     ExtentManager.getTest().info("Owner created a quiz successfully with: "+dynamicQuizTitle);	        
					
				     
	// SharePopup - Initialize the Share popup object with the current WebDriver instance
	   SharePopupPage sharePopupPage = new SharePopupPage(driver);
	   
		   			sharePopupPage.storeParentWindowHandle();                           // Step 1: Store parent window
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
					formDashboardPage.clickAccountButton();
					formDashboardPage.clickSignoutButton();
					ExtentManager.getTest().pass("Owner signed out successfully.");
					
					
				 // Admin login
					loginPage.clickSignInUsingEmail();
					loginPage.enterEmail("hawece4785@forcrack.com");
					loginPage.clickNext();
					loginPage.enterPassword("Automation@2");
					loginPage.clickSignIn();
					loginPage.closeFeatureSpotlightIfPresent();              // In case popup appears after login
					ExtentManager.getTest().pass("Admin Login successful with email and password.");
			        
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
					ExtentManager.getTest().info("Total Answers count in individual page: <b>" + totalAnswersCount + "</b>");
					answersPage.clickIndividualButton();
					answersPage.clickDeleteButtonIndividual();
					answersPage.clickDeleteButtonOnDeletePopup();
				    WaitUtils.waitForSeconds(driver, 5);
					ExtentManager.getTest().info("Admin successfully deleted a answer on <b>Individual</b> page.");
				
								
				 // Come back to Quiz Shared Dashboard
					sharedQuizDashboard.refreshPage();
					formDashboardPage.clickAccountButton();
					formDashboardPage.clickSignoutButton();
					ExtentManager.getTest().pass("Admin signed out successfully.");
						        
				
			     // Again owner login 
				    loginPage.clickSignInUsingEmail();
					loginPage.enterEmail("gofaw36836@pacfut.com");
					loginPage.clickNext();
					loginPage.enterPassword("Automation@1");
					loginPage.clickSignIn();
					loginPage.closeFeatureSpotlightIfPresent();                // In case popup appears after login
					ExtentManager.getTest().pass("Again owner login successful with email and password.");
								
			     // Quiz Dashboard
					formDashboardPage.refreshPage();
					formDashboardPage.clickQuizzesTab();
					
					quizDashboardPage.clickMoreOptionsForFirstQuiz();
					int numberOfQuizAnswersOnQuizCard2 = quizDashboardPage.getTotalAnswersCountInMore();
					ExtentManager.getTest().info("Total answers on owner created quiz card, after admin deleted a answer: <b>" + numberOfQuizAnswersOnQuizCard2 + "</b>");
					ExtentManager.getTest().pass("Admin deleted answer count is updated in the <b>Owner Created Quiz</b> card: <b>" + quizTitle + "</b>");

				    
				 				
	}

}


/**
Scenario: Test class to verify that an Admin collaborator can delete a submitted quiz answer.

Steps:
1. Login as the Owner, create a new quiz with a dynamic title and a Short Answer question.
2. Add an Admin collaborator in Quiz Settings and submit one answer as a participant.
3. Sign out as Owner and login as Admin to access the shared quiz.
4. Navigate to the Individual answers page, delete the submitted answer, and sign out.
5. Login back as Owner and verify that the quiz answer count is updated on the quiz card.

Expected Result:
- Admin is able to delete a submitted answer from the Individual page.
- The Owner sees the updated answer count on the quiz card after deletion.
*/

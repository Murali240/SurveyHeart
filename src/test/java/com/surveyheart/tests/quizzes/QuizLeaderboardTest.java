package com.surveyheart.tests.quizzes;

import java.util.Arrays;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.surveyheart.base.BaseTest;
import com.surveyheart.enums.QuizQuestionType;
import com.surveyheart.pages.FormDashboardPage;
import com.surveyheart.pages.QuizBuilderPage;
import com.surveyheart.pages.QuizDashboardPage;
import com.surveyheart.pages.QuizPage;
import com.surveyheart.pages.QuizResultsPage;
import com.surveyheart.pages.QuizSettingsPage;
import com.surveyheart.pages.SharePopupPage;
import com.surveyheart.pages.SubmittedPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Test class to verify Quiz Leaderboard page functionality */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class QuizLeaderboardTest extends BaseTest {
	
	@Test 
	public void verifyQuizLeaderboardPage() {
		
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
				    String dynamicQuizTitle = "QuizLeaderboard " + System.currentTimeMillis();
				    quizBuilderPage.enterQuizTitle(dynamicQuizTitle);

				 // ==== Add an MCQ question ====
				    quizBuilderPage.addQuestion(
				        QuizQuestionType.MULTIPLE_CHOICE,
				        "Select Selenium tool type",
				        null,
				        Arrays.asList("IDE", "WebDriver", "Grid"),
				        1                               // WebDriver is the correct answer
				    );
				    
	    
	 // Quiz Settings - Initialize the Quiz settings object with the current WebDriver instance
	   	QuizSettingsPage quizSettingsPage = new QuizSettingsPage(driver);
	   	
				   	quizSettingsPage.clickSettingsButton();
				   	quizSettingsPage.clickControlTab();
				   	quizSettingsPage.enableAllowMultipleAttempts(true);
				   	quizSettingsPage.enableShowQuizLeaderboard(true);
				    ExtentManager.getTest().pass("Successfully enabled the checkboxes: <b>'Allow Multiple Attempts'</b> and <b>'Show Quiz Leaderboard'</b>.");
	   				quizSettingsPage.clickSubmitButton();
	   				ExtentManager.getTest().info("Quiz created successfully with: "+dynamicQuizTitle);  	        
					    
	// SharePopup - Initialize the Share popup object with the current WebDriver instance
	   SharePopupPage sharePopupPage = new SharePopupPage(driver);
	   
		   			sharePopupPage.storeParentWindowHandle();                             // Step 1: Store parent window
					sharePopupPage.clickViewIcon();
					sharePopupPage.switchToChildWindowThroughViewIcon();
							
							
	// Quiz page - Initialize the Quiz page object with the current WebDriver instance
	   QuizPage quizPage = new QuizPage(driver);
	   
	             // Welcome page
	   				quizPage.clickStart();
	   				quizPage.enterName("Sounder");
	   				quizPage.clickStartQuiz();
	   				
	   			 // Quiz page
	   				String quizTitle = quizPage.getQuizTitle();
	   				quizPage.selectMCQOption(2);
					String totalQuestionCount = quizPage.getTotalQuestionCount();
					ExtentManager.getTest().info("Quiz page displayed with <b>" + totalQuestionCount + " </b>question."); 
					quizPage.clickSubmitButton();
					ExtentManager.getTest().pass("Option choosen for given question and successfully submitted the Quiz: <b>" + quizTitle+"</b>");
					  
					  
	// Submitted page - Initialize the Submitted page object with the current WebDriver instance
	   SubmittedPage submittedPage = new SubmittedPage(driver);
	   
	   				 submittedPage.clickViewLeaderboardButton();
	   				 ExtentManager.getTest().pass("Successfully clicked on the <b>'View Leaderboard'</b> button in the <b>'Submitted'</b> page.");

	   				 
	 // Quiz Results page - Initialize the Quiz Results page object with the current WebDriver instance
	   	QuizResultsPage quizResultsPage = new QuizResultsPage(driver);
	   			   
	   				 String leaderboardQuizTitle = quizResultsPage.getLeaderboardQuizTitle().getText();
	   				 String userName = quizResultsPage.getLeaderboardUserName().getText();
	   				 String userRank = quizResultsPage.getUserRank().getAttribute("alt");
	   				 String userPercentage = quizResultsPage.getLeaderboardScoreCardPercentage().getText();
	   				 ExtentManager.getTest().pass("Retrieved quiz title name from <b>Leaderboard</b> page: <b>" + leaderboardQuizTitle + "</b>");
	   				 ExtentManager.getTest().info("User: <b>" + userName + "</b> | Rank: <b>" + userRank + "</b> | Percentage: <b>" + userPercentage + "</b>");

	   				 
			         
	}

}

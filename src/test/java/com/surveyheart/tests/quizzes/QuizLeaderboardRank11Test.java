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
import com.surveyheart.utilities.WaitUtils;


/** Test class to verify Leaderboard ranking for the 11th user in a quiz */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class QuizLeaderboardRank11Test extends BaseTest {
	
	@Test 
	public void verifyRank11For11thUser() {
		
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
				    String dynamicQuizTitle = "LeaderboardRank11 " + System.currentTimeMillis();
				    quizBuilderPage.enterQuizTitle(dynamicQuizTitle);

				 // ==== Add an MCQ question ====
				    quizBuilderPage.addQuestion(
				        QuizQuestionType.MULTIPLE_CHOICE,
				        "Select Selenium tool type",
				        null,
				        Arrays.asList("IDE", "WebDriver", "Grid"),
				        1             // WebDriver is the correct answer
				    );
				    
	    
	 // Quiz Settings - Initialize the Quiz settings object with the current WebDriver instance
	   	QuizSettingsPage quizSettingsPage = new QuizSettingsPage(driver);
	   	
				   	quizSettingsPage.clickSettingsButton();
				   	quizSettingsPage.clickControlTab();
				   	quizSettingsPage.enableAllowMultipleAttempts(true);
				   	quizSettingsPage.enableShowQuizLeaderboard(true);
				   	ExtentManager.getTest().pass("<b>'Allow Multiple Attempts'</b> and <b>'Show Quiz Leaderboard'</b> checkboxes were enabled successfully.");
	   				quizSettingsPage.clickSubmitButton();
	   				ExtentManager.getTest().info("Quiz created successfully with: "+dynamicQuizTitle);  	        
					    
	// SharePopup - Initialize the Share popup object with the current WebDriver instance
	   SharePopupPage sharePopupPage = new SharePopupPage(driver);
	   
		   			sharePopupPage.storeParentWindowHandle();                             // Store parent window
					sharePopupPage.clickViewIcon();
					sharePopupPage.switchToChildWindowThroughViewIcon();
							
							
	// Quiz page - Initialize the Quiz page object with the current WebDriver instance
	   QuizPage quizPage = new QuizPage(driver);
	   
	             // Welcome page
	   				quizPage.clickStart();
	   				quizPage.enterName("Alice");
	   				quizPage.clickStartQuiz();
	   				
	   			 // Quiz page
	   				String quizTitle = quizPage.getQuizTitle();
	   				quizPage.selectMCQOption(2);
					String totalQuestionCount = quizPage.getTotalQuestionCount();
					ExtentManager.getTest().info("Quiz page displayed with <b>" + totalQuestionCount + " </b>question."); 
					quizPage.clickSubmitButton();
					ExtentManager.getTest().pass("1st user chose an option for the given question and successfully submitted the quiz: <b>" + quizTitle + "</b>");				  
		
					
	// Submitted page - Initialize the Submitted page object with the current WebDriver instance
	   SubmittedPage submittedPage = new SubmittedPage(driver);
	   
	   			  // Click View Leaderboard button
	                 submittedPage.clickViewLeaderboardButton();
	                 ExtentManager.getTest().pass("Successfully clicked on the <b>'View Leaderboard'</b> button in the <b>'Submitted'</b> page.");
	                 
	                 
	 // Quiz Results page - Initialize the Quiz Results page object with the current WebDriver instance
	    QuizResultsPage quizResultsPage = new QuizResultsPage(driver);
	                 
	                
	                 /** 2nd user attempt */             
				     quizResultsPage.refreshPage();
	   				    
	   			  // Welcome page
	                 quizPage.clickStart();
		   		     quizPage.enterName("Bob");
		   		     quizPage.clickStartQuiz();
		   				
		   		  // Quiz page
		   			 quizPage.selectMCQOption(2);
		   			 WaitUtils.waitForSeconds(driver, 1);
					 quizPage.clickSubmitButton();
					 ExtentManager.getTest().pass("2nd user chose an option for the question and successfully submitted the quiz.");
				  
					// Click View Leaderboard button
	                 submittedPage.clickViewLeaderboardButton();
			       
	                 
	                 
				     /** 3rd user attempt */
	                 quizResultsPage.refreshPage();
	   				    
	   			  // Welcome page
	                 quizPage.clickStart();
		   		     quizPage.enterName("Charlie");
		   		     quizPage.clickStartQuiz();
		   				
		   		  // Quiz page
		   			 quizPage.selectMCQOption(2);
		   			 WaitUtils.waitForSeconds(driver, 2);
					 quizPage.clickSubmitButton();	 
					 ExtentManager.getTest().pass("3rd user chose an option for the question and successfully submitted the quiz.");
	
				  // Click View Leaderboard button
	                 submittedPage.clickViewLeaderboardButton();
					 
					 
	                 
	                 /** 4th user attempt */
	                 quizResultsPage.refreshPage();
	   				    
	   			  // Welcome page
	                 quizPage.clickStart();
		   		     quizPage.enterName("David");
		   		     quizPage.clickStartQuiz();
		   				
		   		  // Quiz page
		   			 quizPage.selectMCQOption(2);
		   			 WaitUtils.waitForSeconds(driver, 3);
					 quizPage.clickSubmitButton();
					 ExtentManager.getTest().pass("4th user chose an option for the question and successfully submitted the quiz.");
	
				  // Click View Leaderboard button
	                 submittedPage.clickViewLeaderboardButton();
	                 
	                  
					 
					 /** 5th user attempt */
	                 quizResultsPage.refreshPage();
	   				    
	   			  // Welcome page
	                 quizPage.clickStart();
		   		     quizPage.enterName("Emma");
		   		     quizPage.clickStartQuiz();
		   				
		   		  // Quiz page
		   			 quizPage.selectMCQOption(2);
		   			 WaitUtils.waitForSeconds(driver, 4);
					 quizPage.clickSubmitButton();
					 ExtentManager.getTest().pass("5th user chose an option for the question and successfully submitted the quiz.");
	
				  // Click View Leaderboard button
	                 submittedPage.clickViewLeaderboardButton();
	                 
	                 
					 
					 /** 6th user attempt */
	                 quizResultsPage.refreshPage();
	   				    
	   			  // Welcome page
	                 quizPage.clickStart();
		   		     quizPage.enterName("Frank");
		   		     quizPage.clickStartQuiz();
		   				
		   		  // Quiz page
		   			 quizPage.selectMCQOption(2);
		   			 WaitUtils.waitForSeconds(driver, 5);
					 quizPage.clickSubmitButton();
					 ExtentManager.getTest().pass("6th user chose an option for the question and successfully submitted the quiz.");
	
				  // Click View Leaderboard button
	                 submittedPage.clickViewLeaderboardButton();
	                 
	                 	 
					
					 /** 7th user attempt */
	                 quizResultsPage.refreshPage();
	   				    
	   			  // Welcome page
	                 quizPage.clickStart();
		   		     quizPage.enterName("Grace");
		   		     quizPage.clickStartQuiz();
		   				
		   		  // Quiz page
		   			 quizPage.selectMCQOption(2);
		   			 WaitUtils.waitForSeconds(driver, 6);
					 quizPage.clickSubmitButton();
					 ExtentManager.getTest().pass("7th user chose an option for the question and successfully submitted the quiz.");
	
				  // Click View Leaderboard button
	                 submittedPage.clickViewLeaderboardButton();
	                 
					 
					 
					 /** 8th user attempt */
	                 quizResultsPage.refreshPage();
	   				    
	   			  // Welcome page
	                 quizPage.clickStart();
		   		     quizPage.enterName("Henry");
		   		     quizPage.clickStartQuiz();
		   				
		   		  // Quiz page
		   			 quizPage.selectMCQOption(2);
		   			 WaitUtils.waitForSeconds(driver, 7);
					 quizPage.clickSubmitButton();
					 ExtentManager.getTest().pass("8th user chose an option for the question and successfully submitted the quiz.");
	
				  // Click View Leaderboard button
	                 submittedPage.clickViewLeaderboardButton();
	                 
					 
					 
					 /** 9th user attempt */
	                 quizResultsPage.refreshPage();
	   				    
	   			  // Welcome page
	                 quizPage.clickStart();
		   		     quizPage.enterName("Isabella");
		   		     quizPage.clickStartQuiz();
		   				
		   		  // Quiz page
		   			 quizPage.selectMCQOption(2);
		   			 WaitUtils.waitForSeconds(driver, 8);
					 quizPage.clickSubmitButton();
					 ExtentManager.getTest().pass("9th user chose an option for the question and successfully submitted the quiz.");
	
				  // Click View Leaderboard button
	                 submittedPage.clickViewLeaderboardButton();
	                 

					 
					 /** 10th user attempt */
	                 quizResultsPage.refreshPage();
	   				    
	   			  // Welcome page
	                 quizPage.clickStart();
		   		     quizPage.enterName("Jack");
		   		     quizPage.clickStartQuiz();
		   				
		   		  // Quiz page
		   			 quizPage.selectMCQOption(2);
		   			 WaitUtils.waitForSeconds(driver, 9);
					 quizPage.clickSubmitButton();
					 ExtentManager.getTest().pass("10th user chose an option for the question and successfully submitted the quiz.");
	
				  // Click View Leaderboard button
	                 submittedPage.clickViewLeaderboardButton();
	                 
					 
						
					 /** 11th user attempt */
	                 quizResultsPage.refreshPage();
	   				    
	   			  // Welcome page
	                 quizPage.clickStart();
		   		     quizPage.enterName("Sudheer");
		   		     quizPage.clickStartQuiz();
		   				
		   		  // Quiz page
		   			 quizPage.selectMCQOption(3);
		   			 WaitUtils.waitForSeconds(driver, 11);
					 quizPage.clickSubmitButton();
					 ExtentManager.getTest().pass("11th user chose an option for the question and successfully submitted the quiz.");
	
					 
				  // Click on View Leaderboard button
		   			 submittedPage.clickViewLeaderboardButton();
		   			 

		          // Quiz Leaderboard page on Submitted page
					 String leaderboardQuizTitle = quizResultsPage.getLeaderboardQuizTitle().getText();
					 ExtentManager.getTest().pass("Retrieved quiz title name from <b>Leaderboard</b> page: <b>" + leaderboardQuizTitle + "</b>");
		
					 QuizResultsPage.LeaderboardData scorecardDetails = quizResultsPage.getLeaderboardScorecardDetails(10);
					 ExtentManager.getTest().info("Leaderboard Scorecard10 details → "+ scorecardDetails);
				   
		   				 
			         
	}

}

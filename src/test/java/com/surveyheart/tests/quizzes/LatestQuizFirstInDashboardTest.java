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


/** Test class to verify the latest created quiz appears first in the Quiz dashboard. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class LatestQuizFirstInDashboardTest extends BaseTest {
	
	@Test (groups = "regression", priority = 23)
    public void verifyLatestQuizIsFirstInDashboard() {
    	
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
		FormDashboardPage formDashboardPage = new FormDashboardPage(driver);
					formDashboardPage.refreshPage();
					formDashboardPage.clickQuizzesTab();
					
	 // Quiz Dashboard - Initialize the Quiz Dashboard Page object with the current WebDriver instance
		QuizDashboardPage quizDashboardPage = new QuizDashboardPage(driver);
				    quizDashboardPage.clickCreateQuizButton();                   // Click on +Create Quiz button

     // ===== Quiz Builder Page =====
	    QuizBuilderPage quizBuilderPage = new QuizBuilderPage(driver);

				 // Set dynamic Quiz title
					String dynamicQuizTitle = "AlphaQuiz " + System.currentTimeMillis();
					quizBuilderPage.enterQuizTitle(dynamicQuizTitle);

				 // ==== Add a Short Answer question ====
					quizBuilderPage.addQuestion(
					QuizQuestionType.SHORT_ANSWER,
				    "What is your name?",
					"Madhu",              // Answer
					null,                 // No options
					-1                    // No correct option
					);

	  // Quiz Settings - Initialize the Quiz settings object with the current WebDriver instance
	   	 QuizSettingsPage quizSettingsPage = new QuizSettingsPage(driver);
				   	
					quizSettingsPage.clickSettingsButton();
				   	quizSettingsPage.clickSubmitButton();
				   	ExtentManager.getTest().info("Quiz created successfully with: "+dynamicQuizTitle);  	        
								    
	  // SharePopup - Initialize the Share popup object with the current WebDriver instance
		 SharePopupPage sharePopupPage = new SharePopupPage(driver);
				   
					sharePopupPage.storeParentWindowHandle();                                      // Store parent window
					sharePopupPage.clickViewIcon();
					sharePopupPage.switchToChildWindowThroughViewIcon();
										
										
	  // Quiz page - Initialize the Quiz page object with the current WebDriver instance
		 QuizPage quizPage = new QuizPage(driver);
				   
				   	quizPage.clickStart();
				   	quizPage.enterName("Sounder");
				   	quizPage.clickStartQuiz();
				   				
				 // Quiz page
				   	String quizTitle1 = quizPage.getQuizTitle();
					String questionCount = quizPage.getTotalQuestionCount();
					ExtentManager.getTest().info("Quiz created with : <b>" + questionCount + " question</b>");
					ExtentManager.getTest().info("Quiz title retrieved from the Quiz Page: <b>" + quizTitle1 + "</b>");
				    
				 // Closing the child window 
				    quizPage.closeChildWindowAndSwitchToParent(sharePopupPage.getParentWindowHandle());   // Close child and switch back
						  
				 // Share popup
				    sharePopupPage.clickCloseIcon();
				 
				   
				    
				    /** Creating Beta Quiz -2nd Quiz */
				    quizDashboardPage.clickCreateQuizButton();           // Click on +Create Quiz button
				    
				 // Enter Quiz title in Builder screen
			        String dynamicQuizTitle2 = "BetaQuiz " + System.currentTimeMillis();
			        quizBuilderPage.enterQuizTitle(dynamicQuizTitle2); 
			

			        // ==== Add a Short Answer question ====
					quizBuilderPage.clickInitialAddQuestion();
					quizBuilderPage.addShortQuestion("What is your name?","Madhu");
						
			     // Settings
					quizSettingsPage.clickSettingsButton();
					quizSettingsPage.clickSubmitButton();
					ExtentManager.getTest().info("Quiz created successfully with: " + dynamicQuizTitle2 );
				    
			     // Share Popup
					sharePopupPage.storeParentWindowHandle();                                      // Store parent window
					sharePopupPage.clickViewIcon();
					sharePopupPage.switchToChildWindowThroughViewIcon();
					
			     // Welcome page
					quizPage.clickStart();
				   	quizPage.enterName("Sounder");
				   	quizPage.clickStartQuiz();
				   				
				 // Quiz page
				   	String quizTitle2 = quizPage.getQuizTitle();
					String questionCount2 = quizPage.getTotalQuestionCount();
					ExtentManager.getTest().info("Quiz created with : <b>" + questionCount2 + " question</b>");
					ExtentManager.getTest().info("Quiz title retrieved from the Quiz Page: <b>" + quizTitle2 + "</b>");
				    
				 // Closing the child window 
				    quizPage.closeChildWindowAndSwitchToParent(sharePopupPage.getParentWindowHandle());   // Close child and switch back
						  
				 // Share popup
				    sharePopupPage.clickCloseIcon();
				    quizDashboardPage.clickSecondQuizMoreOptions();
				    quizDashboardPage.clickEditQuiz();
				    ExtentManager.getTest().pass("Successfully edited the second quiz.");
				    
				 // Settings
				    quizSettingsPage.clickSettingsButton();
				    quizSettingsPage.clickSubmitButton();
					ExtentManager.getTest().pass("Successfully submitted the second quiz.");

				    
			     // Share popup
					sharePopupPage.clickCloseIcon(); 
				    
				 // Quiz Dashboard
					quizDashboardPage.refreshPage();
					formDashboardPage.ifFollowUsPopupDisplayed();
					formDashboardPage.clickQuizzesTab();
					
				    String actualQuizTiltle = quizDashboardPage.getFirstQuizCardTitle();
				    String expectedQuizTitle=dynamicQuizTitle;
	
				 // Assert that actualQuizTitle and expectedQuizTitle 
				    Assert.assertEquals(actualQuizTiltle, expectedQuizTitle, "Actual quiz title does not match expected title");
				    ExtentManager.getTest().pass("Actual quiz title is matched with expected quiz title: <b>"+ dynamicQuizTitle+"</b>");


				    
	}

}

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
import com.surveyheart.pages.QuizSettingsPage;
import com.surveyheart.pages.SharePopupPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Test class to verify editing a quiz and deleting a question from Quiz Builder */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class QuizBuilderDeleteQuestionTest extends BaseTest {
	
	@Test (groups = "regression", priority = 15)
    public void verifyEditQuizAndDeleteQuestionTest() {
    	
     // Login page - Initialize the Login Page object with the current WebDriver instance
		SurveyHeartLoginPage loginPage = new SurveyHeartLoginPage(driver);
					loginPage.clickSignInUsingEmail();
					loginPage.enterEmail("gofaw36836@pacfut.com");
					loginPage.clickNext();
					loginPage.enterPassword("Automation@1");
					loginPage.clickSignIn();
					loginPage.closeFeatureSpotlightIfPresent();             // In case popup appears after login
					ExtentManager.getTest().pass("Login successful with email and password.");
				
	 // Form Dashboard - Initialize the Form Dashboard Page object with the current WebDriver instance
		FormDashboardPage formDashboardPage = new FormDashboardPage(driver);
					formDashboardPage.refreshPage();
					formDashboardPage.clickQuizzesTab();      
					
	    QuizDashboardPage quizDashboardPage = new QuizDashboardPage(driver);
				    quizDashboardPage.clickCreateQuizButton();

	 // ===== Quiz Builder Page =====
	    QuizBuilderPage quizBuilderPage = new QuizBuilderPage(driver);

			     // Set dynamic Quiz title
					String dynamicQuizTitle = "QuizWithDeleteQuestion " + System.currentTimeMillis();
					quizBuilderPage.enterQuizTitle(dynamicQuizTitle);
   
					  // ==== Add a Short Answer question ====
				    quizBuilderPage.addQuestion(
				        QuizQuestionType.SHORT_ANSWER,
				        "What is your name?",
				        "Madhu",                       // Answer
				        null,                          // No options
				        -1                             // No correct option
				    );
			
				    // ==== Add an MCQ question ====
				    quizBuilderPage.addQuestion(
				        QuizQuestionType.MULTIPLE_CHOICE,
				        "Select Selenium tool type",
				        null,
				        Arrays.asList("IDE", "WebDriver", "Grid"),
				        1                              // WebDriver is the correct answer
				    );
				    
		        
	  // Quiz Settings - Initialize the Quiz settings object with the current WebDriver instance
		 QuizSettingsPage quizSettingsPage = new QuizSettingsPage(driver);
				   	
					quizSettingsPage.clickSettingsButton();
				   	quizSettingsPage.clickSubmitButton();
				   	ExtentManager.getTest().info("Quiz created successfully with: "+dynamicQuizTitle);
							        
								    
	  // SharePopup - Initialize the Share popup object with the current WebDriver instance
		 SharePopupPage sharePopupPage = new SharePopupPage(driver);
				   
					sharePopupPage.storeParentWindowHandle();                             // Store parent window
					sharePopupPage.clickViewIcon();
					sharePopupPage.switchToChildWindowThroughViewIcon();
										
										
	 // Quiz page - Initialize the Quiz page object with the current WebDriver instance
		QuizPage quizPage = new QuizPage(driver);
				   
				   	quizPage.clickStart();
				   	quizPage.enterName("Sounder");
				   	quizPage.clickStartQuiz();
				   				
		         // Quiz page
					String questionCountBefore = quizPage.getTotalQuestionCount();
					ExtentManager.getTest().info("Total number of questions on Quiz Page (before delete): " + questionCountBefore+" questions");
						    
			     // Closing the child window 
					quizPage.closeChildWindowAndSwitchToParent(sharePopupPage.getParentWindowHandle());   // Close child and switch back
						  
				 // Share popup
					sharePopupPage.clickCloseIcon();
					quizDashboardPage.refreshPage();
					formDashboardPage.clickQuizzesTab();
				
				 // Quiz Dashboard
					quizDashboardPage.clickMoreOptionsForFirstQuiz();
					quizDashboardPage.clickEditQuiz();
							    
				 // ==== Add an MCQ question ====
					quizBuilderPage.clickDeleteIcon();
							 
				 // Settings
					quizSettingsPage.clickSettingsButton();
					quizSettingsPage.clickSubmitButton();
								
				 // Share popup
					sharePopupPage.storeParentWindowHandle();                              // Store parent window
					sharePopupPage.clickViewIcon();
					sharePopupPage.switchToChildWindowThroughViewIcon();
								
				 // Quiz page
					quizPage.clickStart();
				   	quizPage.enterName("Sai Krishna");
				   	quizPage.clickStartQuiz();
				   				
				 // Quiz page
				   	String quizTitle = quizPage.getQuizTitle();
					String questionCountAfter = quizPage.getTotalQuestionCount();
					ExtentManager.getTest().info("Total number of questions on Quiz Page (after delete): " + questionCountAfter+" questions");
					ExtentManager.getTest().pass("Quiz edited successfully and one question was deleted: <b>" + quizTitle + "</b>");

	
					
	}

}

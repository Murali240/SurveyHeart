package com.surveyheart.tests.quizzes;

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
import com.surveyheart.pages.SubmittedPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Test class to verify creating a quiz with text-type questions (Short Answer and Long Answer) */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class CreateQuizWithTextQuestionsTest extends BaseTest {
	
	@Test (groups = "regression", priority = 14)
	public void createQuizWithTextTypeQuestions() {
		
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

	    QuizDashboardPage quizDashboardPage = new QuizDashboardPage(driver);
	    			quizDashboardPage.clickCreateQuizButton();

	    // ===== Quiz Builder Page =====
	    QuizBuilderPage quizBuilderPage = new QuizBuilderPage(driver);

	             // Set dynamic Quiz title
				    String dynamicQuizTitle = "TextQTypeQuiz " + System.currentTimeMillis();
				    quizBuilderPage.enterQuizTitle(dynamicQuizTitle);

				    // ==== Add a Short Answer question ====
				    quizBuilderPage.addQuestion(
				        QuizQuestionType.SHORT_ANSWER,
				        "What is your name?",
				        "Madhu",                       // Answer
				        null,                          // No options
				        -1                             // No correct option
				    );
				    
				    // ==== Add a Long Answer question ====
				    quizBuilderPage.addQuestion(
				        QuizQuestionType.LONG_ANSWER,
				        "Describe yourself briefly",
				        "Quick learner",                // Answer
				        null,                           // No options
				        -1 
				     );
			
				   	 // Quiz Settings - Initialize the Quiz settings object with the current WebDriver instance
	   	QuizSettingsPage quizSettingsPage = new QuizSettingsPage(driver);
	   	
				   	quizSettingsPage.clickSettingsButton();
	   				quizSettingsPage.clickSubmitButton();
	   				ExtentManager.getTest().info("Quiz created successfully with : "+dynamicQuizTitle);
				        
					    
	// SharePopup - Initialize the Share popup object with the current WebDriver instance
	   SharePopupPage sharePopupPage = new SharePopupPage(driver);
	   
		   			sharePopupPage.storeParentWindowHandle();                                      // Step 1: Store parent window
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
	   				quizPage.answerLongQuestion("Honest behaviour");
	 
	   		     // Fetch Total number of questions
				    String questionCount = quizPage.getTotalQuestionCount();
				    ExtentManager.getTest().info("Quiz created with : <b>" + questionCount + " questions</b>");
				    quizPage.clickSubmitButton();
				    ExtentManager.getTest().pass("Answered all text-type questions and submitted the quiz : <b>" + quizTitle + "</b>");

			   
     // Submitted page - Initialize the Submitted page object with the current WebDriver instance
		SubmittedPage submittedPage = new SubmittedPage(driver);
					submittedPage.isSubmittedMessageDisplayed();
					submittedPage.isViewResultsButtonDisplayed();
					ExtentManager.getTest().pass("<b>'Submitted'</b> page displayed successfully with the <b>'View Results'</b> button.");

				    
					
    }

}

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
import com.surveyheart.pages.SubmittedPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Test class to verify creating a quiz with choice-type questions (MCQ and Dropdown) */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class ChoiceTypeQuestionsQuizTest extends BaseTest {
	
	@Test (groups = "regression", priority = 16)
	public void verifyCreateQuizWithChoiceQuestions() {
		
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
				    String dynamicQuizTitle = "ChoiceQQuiz " + System.currentTimeMillis();
				    quizBuilderPage.enterQuizTitle(dynamicQuizTitle);
			
				    // ==== Add an MCQ question ====
				    quizBuilderPage.addQuestion(
				        QuizQuestionType.MULTIPLE_CHOICE,
				        "Select Selenium tool type",
				        null,
				        Arrays.asList("IDE", "WebDriver", "Grid"),
				        1                               // WebDriver is the correct answer
				    );
				    
				    // ==== Add an Dropdown question ====
				    quizBuilderPage.addQuestion(
				        QuizQuestionType.DROPDOWN,
				        "Which is a platform-independent language?",
				        null,
				        Arrays.asList("Python", ".Net", "Java"),
				        2                                // Java is the correct answer
				    );
	    
	 // Quiz Settings - Initialize the Quiz settings object with the current WebDriver instance
	   	QuizSettingsPage quizSettingsPage = new QuizSettingsPage(driver);
	   	
				   	quizSettingsPage.clickSettingsButton();
	   				quizSettingsPage.clickSubmitButton();
	   				ExtentManager.getTest().info("Quiz created successfully with: "+dynamicQuizTitle);
				        
					    
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
	   				quizPage.selectMCQOption(2);						 // Selects 2nd mcq option (1-based input)		
	   				quizPage.selectDropdownOption(3);                    // Selects 3rd dropdown option (2-based input)
				    String questionCount = quizPage.getTotalQuestionCount();
				    ExtentManager.getTest().info("Quiz created with : <b>" + questionCount + " questions</b>");
				    quizPage.clickSubmitButton();
				    ExtentManager.getTest().pass("Answered all choice-type questions and submitted the quiz : <b>" + quizTitle + "</b>");

			   
     // Submitted page - Initialize the Submitted page object with the current WebDriver instance
		SubmittedPage submittedPage = new SubmittedPage(driver);
					submittedPage.isSubmittedMessageDisplayed();
					submittedPage.isViewResultsButtonDisplayed();
					ExtentManager.getTest().pass("<b>'Submitted'</b> page displayed successfully with the <b>'View Results'</b> button.");

					
		    
	}

}


/**
Scenario: Test class to verify creating a quiz with choice-type questions (MCQ and Dropdown).

Steps:
1. Login and create a new quiz with a dynamic title.
2. Add an MCQ question with options and set a correct answer.
3. Add a Dropdown question with options and set a correct answer.
4. Save and open the quiz, attempt it as a participant, and submit the responses.
5. Verify that the Submitted page is displayed with the 'View Results' button.

Expected Result:
- The quiz is created successfully with MCQ and Dropdown questions.
- A participant can answer both questions and submit the quiz.
- The Submitted page appears with a confirmation message and 'View Results' button.
*/





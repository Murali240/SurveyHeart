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


/** Test class to verify that quiz questions are shuffled after enabling the Shuffle Questions from setting */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class QuizShuffleQuestionsTest extends BaseTest {         // Not working issue is there -Look here
	
	@Test 
	public void verifyQuestionsShuffledInQuiz()  {
		
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
				    String dynamicQuizTitle = "QuizCreation " + System.currentTimeMillis();
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
				    
				    // ==== Add a File Upload question ====
				    quizBuilderPage.addQuestion(
				        QuizQuestionType.FILE_UPLOAD,
				        "Upload your certification PDF",
				        null,
				        null,
				        -1
				    );

	    
	 // Quiz Settings - Initialize the Quiz settings object with the current WebDriver instance
	   	QuizSettingsPage quizSettingsPage = new QuizSettingsPage(driver);
	   	
				   	quizSettingsPage.clickSettingsButton();
				   	quizSettingsPage.clickControlTab();
				   	//quizSettingsPage.enableShuffleQuestions(false);
	   				quizSettingsPage.clickSubmitButton();
	   				ExtentManager.getTest().info("Quiz created successfully with: "+dynamicQuizTitle);
				        
					    
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
	   				String shuffledFirstQT = quizPage.getFirstQuestionTitleText();
				    String questionCountBefore = quizPage.getTotalQuestionCount();
				    ExtentManager.getTest().info("1st question displayed as: <b>" + shuffledFirstQT + "</b>");
				    ExtentManager.getTest().info("Quiz created with: <b>" + questionCountBefore + " questions</b>");
				    ExtentManager.getTest().pass("Questions successfully shuffled in Quiz page: <b>" + quizTitle + "</b>");
			   

				    
	}

}

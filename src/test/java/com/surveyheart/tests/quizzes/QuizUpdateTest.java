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
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Test class to verify that a quiz can be updated successfully by adding new questions */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class QuizUpdateTest extends BaseTest {
	
	@Test (groups = "sanity", priority = 2)
	public void verifyQuizUpdate() {
		
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
				    String dynamicQuizTitle = "QuizUpdate " + System.currentTimeMillis();
				    quizBuilderPage.enterQuizTitle(dynamicQuizTitle);

				    // ==== Add a Short Answer question ====
				    quizBuilderPage.addQuestion(
				        QuizQuestionType.SHORT_ANSWER,
				        "What is your name?",
				        "Madhu",       // Short Answer
				        null,          // No options
				        -1             // No correct option
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
				    String questionCountBefore = quizPage.getTotalQuestionCount();
				    ExtentManager.getTest().info("Total number of questions on Quiz Page (before edit): <b>" + questionCountBefore + " question</b>");
			    
			     // Closing the child window 
				    quizPage.closeChildWindowAndSwitchToParent(sharePopupPage.getParentWindowHandle());   // Step 4: Close child and switch back
			  
				 // Share popup
				    sharePopupPage.clickCloseIcon();
				    quizDashboardPage.refreshPage();
				    formDashboardPage.clickQuizzesTab();
				    
				 // Quiz Dashboard
				    quizDashboardPage.clickMoreOptionsForFirstQuiz();
				    quizDashboardPage.clickEditQuiz();
				    
				    // ==== Add an MCQ question ====
				    quizBuilderPage.addQuestion(
				        QuizQuestionType.LONG_ANSWER,
				        "Describe yourself briefly",
				        "Quick learner",       // Long Answer
				        null,                  // No options
				        -1                     // No correct option
				    );
				 
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
				    ExtentManager.getTest().info("Total number of questions on Quiz Page (after edit): <b>" + questionCountAfter +" questions</b>");
				    ExtentManager.getTest().pass("Successfully edited the quiz and added a new question: <b>" + quizTitle+"</b>");

					
				    
	}

}


/**
 * Test class to verify that an existing quiz can be updated in the Quiz Dashboard.
 *
 * Steps:
 * 1. Login to SurveyHeart with valid email and password.
 * 2. Navigate to the Quiz Dashboard and create a new quiz.
 * 3. Add a Short Answer question and submit the quiz.
 * 4. Open the quiz in View mode via the Share popup and verify total question count.
 * 5. Close the child window and return to the parent window.
 * 6. Re-open the quiz in Edit mode from the Quiz Dashboard.
 * 7. Add a Long Answer question and submit the updated quiz.
 * 8. Open the quiz again via Share popup and verify the updated total question count.
 *
 * Expected Result:
 * - The quiz should be created successfully with the initial question.
 * - After editing, the new question should be added and reflected in the total question count.
 */

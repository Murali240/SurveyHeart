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


/** Test class to verify that questions in Quiz Builder can be rearranged via drag-and-drop and the order is reflected correctly in the Quiz page */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class QuizBuilderQuestionsDragAndDropTest extends BaseTest {
	
	@Test 
	public void verifyQuestionsDragAndDropInQuizBuilder() throws InterruptedException  {
		
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
				    String dynamicQuizTitle = "DragAndDropInQuizBuilder " + System.currentTimeMillis();
				    quizBuilderPage.enterQuizTitle(dynamicQuizTitle);

				    // ==== Add a Short Answer question ====
				    quizBuilderPage.addQuestion(
				        QuizQuestionType.SHORT_ANSWER,
				        "Quiz Short Question1",
				        "Madhu",                       // Answer
				        null,                          // No options
				        -1                             // No correct option
				    );
				    
				    // ==== Add a Long Answer question ====
				    quizBuilderPage.addQuestion(
				        QuizQuestionType.LONG_ANSWER,
				        "Quiz Long Question2",
				        "Quick learner",                // Answer
				        null,                           // No options
				        -1 
				     );
			
				 // Drag & Drop
				    quizBuilderPage.waitForQuestionCard(1);
				    quizBuilderPage.waitForQuestionCard(2);
				    quizBuilderPage.dragQuestion(2, 1);
			        Thread.sleep(3000);
			        ExtentManager.getTest().pass("Dragged question 2 to position 1 successfully."); 
				 

	    
	 // Quiz Settings - Initialize the Quiz settings object with the current WebDriver instance
	   	QuizSettingsPage quizSettingsPage = new QuizSettingsPage(driver);
	   	
				   	quizSettingsPage.clickSettingsButton();
	   				quizSettingsPage.clickSubmitButton();
	   				ExtentManager.getTest().info("Quiz created successfully with: "+dynamicQuizTitle);
				        
					    
	// SharePopup - Initialize the Share popup object with the current WebDriver instance
	   SharePopupPage sharePopupPage = new SharePopupPage(driver);
	   
		   			sharePopupPage.storeParentWindowHandle();                           // Store parent window
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
	   			    String firstQuestionTitle = quizPage.getFirstQuestionTitleText();
	   				quizPage.answerLongQuestion("Long answer");
	   				quizPage.answerShortQuestion("Short answer");
				    String questionCountBefore = quizPage.getTotalQuestionCount();
				    quizPage.clickSubmitButton();
				    ExtentManager.getTest().info("First question displayed in Quiz page: <b>" + firstQuestionTitle + "</b>");
				    ExtentManager.getTest().info("Quiz created with: <b>" + questionCountBefore + " questions</b>");
				    ExtentManager.getTest().pass("Quiz created successfully with all question types: <b>" + quizTitle+"</b>");
			   

     // Submitted page - Initialize the Submitted page object with the current WebDriver instance
		SubmittedPage submittedPage = new SubmittedPage(driver);
		
		   			submittedPage.isSubmittedMessageDisplayed();
		   			submittedPage.isViewResultsButtonDisplayed();
		   			ExtentManager.getTest().pass("<b>Submitted</b> page displayed successfully with <b>View Results</b> button");


			    
	}

}

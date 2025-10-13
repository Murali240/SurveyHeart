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
import com.surveyheart.pages.SubmittedPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Test class to verify deleting multiple answers and Undo toast message in Quiz Tabular page */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class QuizTabularPageDeletingMultipleAnswersTest extends BaseTest {
	
	@Test 
	public void verifyUndoToastMessageDisplayedInQuizTabularPage() {
		
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
				    String dynamicQuizTitle = "DeleteMultipleAnswersInQuizTabular " + System.currentTimeMillis();
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
					quizSettingsPage.clickControlTab();
				    quizSettingsPage.enableAllowMultipleAttempts(true);
	   				ExtentManager.getTest().pass("<b>'Allow Multiple Attempts'</b> checkbox enabled successfully.");
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
	   				
	   			 // Quiz page - 1st attempt
	   				String quizTitle = quizPage.getQuizTitle();
	   				quizPage.answerShortQuestion("Sounder Arunachalam");
					String totalQuestionCount = quizPage.getTotalQuestionCount();
					ExtentManager.getTest().info("Quiz page displayed with " + totalQuestionCount + " question."); 
					quizPage.clickSubmitButton();
					
					  
	// Submitted page - Initialize the Submitted page object with the current WebDriver instance
	   SubmittedPage submittedPage = new SubmittedPage(driver);
	   				 submittedPage.isSubmittedMessageDisplayed();
	   				 submittedPage.isViewResultsButtonDisplayed();
	   				 ExtentManager.getTest().pass("<b>'Submitted'</b> page displayed successfully on the first attempt with the <b>'View Results'</b> button: <b>"+quizTitle+"</b>");
				
	   			 
	   			 // Refresh the Submitted page - for Quiz 2nd attempt
	   				submittedPage.refreshPage();
	   				
	   			 // Welcome page
	   				quizPage.clickStart();
	   				quizPage.enterName("Gopi");
	   				quizPage.clickStartQuiz();
	
	   			 // Quiz page - 2nd attempt
	   				String quizTitle2 = quizPage.getQuizTitle();
	   				quizPage.answerShortQuestion("Gopi krishna"); 
					quizPage.clickSubmitButton();
					
				 // Submitted page
	   				submittedPage.isSubmittedMessageDisplayed();
	   				submittedPage.isViewResultsButtonDisplayed();
	   				ExtentManager.getTest().pass("<b>'Submitted'</b> page displayed successfully on the second attempt with the <b>'View Results'</b> button: <b>"+quizTitle2+"</b>");
				
	   			// Refresh the Submitted page - for Quiz 2nd attempt
	   				submittedPage.refreshPage();
	   				
	   			 // Welcome page
	   				quizPage.clickStart();
	   				quizPage.enterName("Madhu");
	   				quizPage.clickStartQuiz();
	
	   			 // Quiz page - 2nd attempt
	   				String quizTitle3 = quizPage.getQuizTitle();
	   				quizPage.answerShortQuestion("Madhu Simma"); 
					quizPage.clickSubmitButton();
					
				 // Submitted page
	   				submittedPage.isSubmittedMessageDisplayed();
	   				submittedPage.isViewResultsButtonDisplayed();
	   				ExtentManager.getTest().pass("<b>'Submitted'</b> page displayed successfully on the second attempt with the <b>'View Results'</b> button: <b>"+quizTitle3+"</b>");
	   				
	   		     // Switch to Parent window
	   				submittedPage.closeChildWindowAndSwitchToParent(sharePopupPage.getParentWindowHandle());
	   				
	   			  // Click on View Answers from More options
	   				sharePopupPage.clickCloseIcon();
	   				quizDashboardPage.refreshPage();
	   				formDashboardPage.clickQuizzesTab();
	   				quizDashboardPage.clickMoreOptionsForFirstQuiz();
	   				quizDashboardPage.clickViewAnswersButton();
	   				
				    
				    
	  // Answers page - Initialize the Overview page object with the current WebDriver instance
	     AnswersPage answersPage = new AnswersPage(driver);	
					  int beforeDeleteAnswersCount = answersPage.getTotalAnswersCount();
					  ExtentManager.getTest().info("Total answers count before deleting: <b>" + beforeDeleteAnswersCount + "</b>");
					  answersPage.clickTabularButton();
					  answersPage.selectResponses(1, 2);
					  answersPage.clickDeleteButtonIndividual();
					  answersPage.clickDeleteButtonOnDeletePopup();
		              String undoToastMessage=answersPage.getFullUndoToastMessage();
		              ExtentManager.getTest().pass("Undo toast message verified successfully in Quiz Tabular page: <b>" + undoToastMessage + "</b>");
		              answersPage.clickAnswersButton();
		              int afterDeleteAnswersCount = answersPage.getTotalAnswersCount();
		              ExtentManager.getTest().pass("Total answers count after deleting: <b>" + afterDeleteAnswersCount + "</b>");

		              
   				
	}

}

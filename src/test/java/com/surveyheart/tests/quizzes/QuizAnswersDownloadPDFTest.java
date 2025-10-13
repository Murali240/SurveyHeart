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


/** Test class to verify quiz answers can be downloaded as PDF file. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class QuizAnswersDownloadPDFTest extends BaseTest {
	
	@Test 
	public void verifyQuizAnswersDownloadedAsPDF() throws InterruptedException {
		
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
				    String dynamicQuizTitle = "QuizAnswersPDFDownload " + System.currentTimeMillis();
				    quizBuilderPage.enterQuizTitle(dynamicQuizTitle);

				    // ==== Add a Short Answer question ====
				    quizBuilderPage.addQuestion(
				        QuizQuestionType.SHORT_ANSWER,
				        "What is your name?",
				        "Madhu Simma",               // Answer
				        null,                        // No options
				        -1                           // No correct option
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
	   
		   			sharePopupPage.storeParentWindowHandle();                           // Step 1: Store parent window
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
					 ExtentManager.getTest().pass("First quiz answer submitted successfully in quiz page : "+quizTitle);
					
					  
	// Submitted page - Initialize the Submitted page object with the current WebDriver instance
	   SubmittedPage submittedPage = new SubmittedPage(driver);
	   				 submittedPage.isSubmittedMessageDisplayed();
	   				 submittedPage.isViewResultsButtonDisplayed();
				
	   			 
	   			 // Refresh the Submitted page - for Quiz 2nd attempt
	   				submittedPage.refreshPage();
	   				
	   			 // Welcome page
	   				quizPage.clickStart();
	   				quizPage.enterName("Gopi");
	   				quizPage.clickStartQuiz();
	
	   			 // Quiz page - 2nd attempt
	   				quizPage.answerShortQuestion("Gopi krishna"); 
					quizPage.clickSubmitButton();
					ExtentManager.getTest().pass("Second quiz answer submitted successfully.");
					
				 // Submitted page
	   				submittedPage.isSubmittedMessageDisplayed();
	   				submittedPage.isViewResultsButtonDisplayed();
	   				
				
	   			 // Refresh the Submitted page - for Quiz 3rd attempt
	   				submittedPage.refreshPage();
	   				
	   			 // Welcome page
	   				quizPage.clickStart();
	   				quizPage.enterName("Madhu");
	   				quizPage.clickStartQuiz();
	
	   			 // Quiz page - 3rd attempt
	   				quizPage.answerShortQuestion("Madhu Simma"); 
					quizPage.clickSubmitButton();
					
				 // Submitted page
	   				submittedPage.isSubmittedMessageDisplayed();
	   				submittedPage.isViewResultsButtonDisplayed();
					ExtentManager.getTest().pass("Third quiz answer submitted successfully.");
	   				
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
					  int totalAnswersCount = answersPage.getTotalAnswersCount();
					  ExtentManager.getTest().info("Total answers count in answers page: <b>" + totalAnswersCount + "</b>");
					  answersPage.clickDownloadButton();
					  answersPage.clickPdfFileOnDownloadPopup();
					  answersPage.clickDownloadPDFButton();
					  Thread.sleep(1000);
					  ExtentManager.getTest().pass("PDF file downloaded successfully for submitted quiz answers.");					  
		             
					  
	 				
	}

}

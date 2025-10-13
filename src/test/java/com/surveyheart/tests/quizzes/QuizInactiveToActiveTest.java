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


/** Test class to verify changing a quiz from Inactive to Active and submitting a response */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class QuizInactiveToActiveTest extends BaseTest {
	
	@Test (groups = "sanity", priority = 10)
    public void makeInactiveQuizActive() {
    	
     // Login page - Initialize the Login Page object with the current WebDriver instance
		SurveyHeartLoginPage loginPage = new SurveyHeartLoginPage(driver);
					loginPage.clickSignInUsingEmail();
					loginPage.enterEmail("gofaw36836@pacfut.com");
					loginPage.clickNext();
					loginPage.enterPassword("Automation@1");
					loginPage.clickSignIn();
					loginPage.closeFeatureSpotlightIfPresent();              // In case popup appears after login
					ExtentManager.getTest().pass("Login successful with email and password.");
				
	 // Form Dashboard - Initialize the Form Dashboard Page object with the current WebDriver instance
		FormDashboardPage formDashboardPage = new FormDashboardPage(driver);
					formDashboardPage.refreshPage();
					formDashboardPage.clickQuizzesTab();			 
	
		
		QuizDashboardPage quizDashboardPage = new QuizDashboardPage(driver);
					quizDashboardPage.clickCreateQuizButton();					// Click on +Create Quiz button
					 
    	
				    // ===== Quiz Builder Page =====
		QuizBuilderPage quizBuilderPage = new QuizBuilderPage(driver);

				    // Set dynamic Quiz title
					   String dynamicQuizTitle = "ActiveQuiz " + System.currentTimeMillis();
					   quizBuilderPage.enterQuizTitle(dynamicQuizTitle);

					// ==== Add a Short Answer question ====
					   quizBuilderPage.addQuestion(
					   QuizQuestionType.SHORT_ANSWER,
					   "What is your name?",
					   "Madhu",                // Answer
					    null,                  // No options
					   -1                      // No correct option
					   ); 

				    
	  // Quiz Settings - Initialize the Quiz settings object with the current WebDriver instance
		 QuizSettingsPage quizSettingsPage = new QuizSettingsPage(driver);
				   	
						quizSettingsPage.clickSettingsButton();
				   	    quizSettingsPage.clickSubmitButton();
				   		ExtentManager.getTest().info("Quiz created successfully with: "+dynamicQuizTitle);  	        
		        
					    
	 // SharePopup - Initialize the Share popup object with the current WebDriver instance
		SharePopupPage sharePopup = new SharePopupPage(driver);
				     sharePopup.clickCloseIcon();      
		        
			      // Come back to Quiz Dashboard
				     quizDashboardPage.clickMoreOptionsForFirstQuiz();
				     quizDashboardPage.clickQuizStatusSwitch();            // Toggle form to Inactive
				     quizDashboardPage.storeParentWindowHandle();          // Store parent handle
				     quizDashboardPage.clickViewQuizButton();              // Open form in new tab
				     quizDashboardPage.switchToChildWindow();              // Switch to new tab
			
	 // Quiz page - Initialize the Quiz page object with the current WebDriver instance
		QuizPage quizPage = new QuizPage(driver); 
					 String closedStatus = quizPage.getQuizClosedStatusText();
					 String closedMessage = quizPage.getQuizClosedMessageText();
					 ExtentManager.getTest().pass(dynamicQuizTitle + " : " + closedStatus + " : " + closedMessage);
					 
				  // Closing the child window 
					 quizPage.closeChildWindowAndSwitchToParent(quizDashboardPage.getParentWindowHandle()); 
			        
				  // Come back to Quiz Dashboard
					 quizDashboardPage.refreshPage();
					 formDashboardPage.clickQuizzesTab();
					 quizDashboardPage.clickMoreOptionsForFirstQuiz();
					 quizDashboardPage.clickQuizStatusSwitch();           // Toggle Quiz Inactive to Active
					 ExtentManager.getTest().info(dynamicQuizTitle+ " has been successfully changed to 'Active' status.");
					 quizDashboardPage.storeParentWindowHandle();         // Store parent handle
					 quizDashboardPage.clickViewQuizButton();             // Open quiz in new tab
					 quizDashboardPage.switchToChildWindow();             // Switch to new tab
    
				  // Welcome page
					 quizPage.clickStart();
					 quizPage.enterName("Sounder");
					 quizPage.clickStartQuiz();
					 
				  // Quiz page
					 String quizTitle = quizPage.getQuizTitle();
					 quizPage.answerShortQuestion("My name is Sounder Arunachalam");
					 quizPage.clickSubmitButton();
					 ExtentManager.getTest().pass("Successfully submitted quiz answers for: <b>" + quizTitle + "</b>");

					 			 
	 // Submitted page - Initialize the Quiz page object with the current WebDriver instance
		SubmittedPage submittedPage = new SubmittedPage(driver);		 
					submittedPage.isSubmittedMessageDisplayed(); 
					submittedPage.isViewResultsButtonDisplayed();
					ExtentManager.getTest().pass("<b>'Submitted'</b> page displayed successfully with the <b>'View Results'</b> button."); 
	    
				
	
    }

}

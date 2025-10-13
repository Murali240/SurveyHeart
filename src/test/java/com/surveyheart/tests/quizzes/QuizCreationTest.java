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


/** Test class to verify creation of a quiz and its title consistency in Quiz Page & Quiz Dashboard */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class QuizCreationTest extends BaseTest {

	@Test (groups = {"smoke"}, priority = 4)
	public void verifyQuizCreation() {
		
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
				    String dynamicQuizTitle = "QuizCreation " + System.currentTimeMillis();
				    quizBuilderPage.enterQuizTitle(dynamicQuizTitle);

				    // ==== Add a Short Answer question ====
				    quizBuilderPage.addQuestion(
				        QuizQuestionType.SHORT_ANSWER,
				        "What is your name?",
				        "Madhu",       // Answer
				        null,          // No options
				        -1             // No correct option
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
	   
		   			sharePopupPage.storeParentWindowHandle();                             // Store parent window
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
				    String questionCountBefore = quizPage.getTotalQuestionCount();
				    ExtentManager.getTest().info("Quiz created with : <b>" + questionCountBefore + " question</b>");
				    ExtentManager.getTest().info("Quiz title retrieved from the Quiz Page: <b>" + quizTitle + "</b>");


			    
			     // Closing the child window 
				    quizPage.closeChildWindowAndSwitchToParent(sharePopupPage.getParentWindowHandle());   // Close child and switch back
			  
				    
				 // Share popup
				    sharePopupPage.clickCloseIcon();
				    quizDashboardPage.refreshPage();
				    formDashboardPage.clickQuizzesTab();
				    
				    String actualQuizTitle = quizDashboardPage.getFirstQuizCardTitle();
				    String expectedQuizTitle=dynamicQuizTitle;
	
				 // Assert that actualFormTitle and expectedFormTitle 
				    Assert.assertEquals(actualQuizTitle, expectedQuizTitle, "Actual quiz title does not match expected title");
				    ExtentManager.getTest().pass("Quiz title verified successfully. Expected: <b>" + expectedQuizTitle + "</b>, Actual: <b>" + actualQuizTitle + "</b>");


				    
	}

}

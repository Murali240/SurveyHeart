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


/** Test class to verify that a quiz without any answers can be edited directly and opens the builder successfully */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class WithoutAnswerQuizEditTest extends BaseTest {
	
	@Test 
	public void verifyQuizEditWithoutAnswer() {
		
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
				    String dynamicQuizTitle = "QuizEditWithoutAnswer " + System.currentTimeMillis();
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
				    String questionCountBefore = quizPage.getTotalQuestionCount();
				    ExtentManager.getTest().info("Quiz created with : <b>" + questionCountBefore + " question</b>");
				    ExtentManager.getTest().info("Quiz title retrieved from the Quiz Page: <b>" + quizTitle + "</b>");


			    
			     // Closing the child window 
				    quizPage.closeChildWindowAndSwitchToParent(sharePopupPage.getParentWindowHandle());   // Close child and switch back
			  
				    
				 // Share popup
				    sharePopupPage.clickCloseIcon();
				    quizDashboardPage.refreshPage();
				    formDashboardPage.clickQuizzesTab();
				    
				    quizDashboardPage.clickMoreOptionsForFirstQuiz();
				    quizDashboardPage.clickEditQuiz();
				    
				 // Builder
				    String actualBuilderText=quizBuilderPage.getQuizBuilderButton().getText();
				    String expectedBuilderText="Builder";
				    
				 // Assert that actual QuizBuilder Text and expected QuizBuilder Text 
				    Assert.assertEquals(actualBuilderText, expectedBuilderText, "Actual quiz title does not match expected title");
				    ExtentManager.getTest().pass("Quiz builder screen displayed successfully after editing without quiz answers: <b>"+quizTitle+"</b>");

			    
				    
	}	

}

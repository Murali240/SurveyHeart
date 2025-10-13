package com.surveyheart.tests.quizzes;

import org.testng.Assert;
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


/** Test class to verify quiz link copy and open in a new tab/window functionality */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class QuizLinkCopyAndOpenInNewTabTest extends BaseTest {
	
	@Test (groups = "sanity", priority = 14)
	public void verifyQuizLinkCopyAndOpenInNewTabFunctionality() {
		
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
				    String dynamicQuizTitle = "CopyQuizLink " + System.currentTimeMillis();
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
	   				quizSettingsPage.clickSubmitButton();
	   				ExtentManager.getTest().info("Quiz created successfully with: "+dynamicQuizTitle);  	        
					    
	// SharePopup - Initialize the Share popup object with the current WebDriver instance
	   SharePopupPage sharePopupPage = new SharePopupPage(driver);
	   
	                sharePopupPage.storeParentWindowHandle(); 
	                sharePopupPage.openCopiedQuizInNewTab();
							
							
	// Quiz page - Initialize the Quiz page object with the current WebDriver instance
	   QuizPage quizPage = new QuizPage(driver);
	   
	   				quizPage.clickStart();
	   				quizPage.enterName("Sounder");
	   				quizPage.clickStartQuiz();
	   				
	   			 // Quiz page
	   				String quizTitle = quizPage.getQuizTitle();
	   				quizPage.answerShortQuestion("Sounder Arunachalam");
					String totalQuestionCount = quizPage.getTotalQuestionCount();
					ExtentManager.getTest().info("Quiz page displayed with " + totalQuestionCount + " question."); 
					quizPage.clickSubmitButton();
					ExtentManager.getTest().pass("Entered answer for given question and successfully submitted the Quiz : <b>" + quizTitle+"</b>");
					  
					  
	// Submitted page - Initialize the Submitted page object with the current WebDriver instance
	   SubmittedPage submittedPage = new SubmittedPage(driver);
	   				 submittedPage.isSubmittedMessageDisplayed();
	   				 submittedPage.isViewResultsButtonDisplayed();
	   				 submittedPage.closeChildWindowAndSwitchToParent(sharePopupPage.getParentWindowHandle());
	   				 ExtentManager.getTest().pass("<b>'Submitted'</b> page displayed successfully with the <b>'View Results'</b> button.");
				
	   				 
				  // Share popup
	   				 sharePopupPage.clickCloseIcon();
					  
	   			  // Come back to Quiz Dashboard
	   				 quizDashboardPage.refreshPage();
	   				 formDashboardPage.clickQuizzesTab();
	   				 quizDashboardPage.clickMoreOptionsForFirstQuiz();
	   				 quizDashboardPage.clickViewAnswersButton();
	   				 
	   				 
	 // Form page - Initialize the Answers page object with the current WebDriver instance
	  	AnswersPage answersPage = new AnswersPage(driver);
	  	
	  	             answersPage.clickIndividualButton();
	  				 String answer = answersPage.getFirstQuestionAnswerText();
				  	 ExtentManager.getTest().info("First question answer text : " + answer);
				
				  	 String actualAnswer = answer;
			         String expextedAnswer = "Sounder Arunachalam";
			         
			         Assert.assertEquals(actualAnswer, expextedAnswer, "Answer text does not match with expected answer");
			         ExtentManager.getTest().pass("Successfully verified actual quiz answer with the expected answer: <b>" + actualAnswer+"</b>");


		         
	}

}

package com.surveyheart.tests.quizzes;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.surveyheart.base.BaseTest;
import com.surveyheart.enums.QuizQuestionType;
import com.surveyheart.pages.FormDashboardPage;
import com.surveyheart.pages.QuizBuilderPage;
import com.surveyheart.pages.QuizDashboardPage;
import com.surveyheart.pages.QuizSettingsPage;
import com.surveyheart.pages.SharePopupPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Test class to verify quiz duplication and title consistency in Builder and Quiz Dashboard */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class QuizDuplicationTest extends BaseTest {
	
	@Test 
	public void verifyQuizDuplication() {
		
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
				    String dynamicQuizTitle = "DuplicateQuiz " + System.currentTimeMillis();
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
	   
		   			sharePopupPage.clickCloseIcon();
					
		   	     // Quiz Dashboard
		   			quizDashboardPage.clickMoreOptionsForFirstQuiz();
		   			quizDashboardPage.clickDuplicateQuizButton();
		   		
		   		 // Builder screen
		   			String actualDuplicatedQuizTitle=quizBuilderPage.getDuplicatedQuizTitle();
		   			String expectedDuplicatedQuizTitle = dynamicQuizTitle + " (Copy)";
		   			
		   	     // Assert the actual title matches expected
		   			Assert.assertEquals(actualDuplicatedQuizTitle, expectedDuplicatedQuizTitle, "❌ Duplicated Quiz title does not match expected title.");		    
					ExtentManager.getTest().pass("✔ Duplicated quiz title matched on builder screen: <b>" + actualDuplicatedQuizTitle+"</b>");
		
				 // Settings screen for duplication form
					quizSettingsPage.clickSettingsButton();
					quizSettingsPage.clickSubmitButton();
					ExtentManager.getTest().info("Quiz duplicated with: " + actualDuplicatedQuizTitle);
					
				 // Close share popup
					sharePopupPage.clickCloseIcon();
					quizDashboardPage.refreshPage();
					formDashboardPage.clickQuizzesTab();
					
				 // Quiz Dashboard 
				    String actualQuizTitle = quizDashboardPage.getFirstQuizCardTitle();
				    String expectedQuizTitle=expectedDuplicatedQuizTitle;
	
				 // Assert that actualQuizTitle and expectedQuizTitle in Quiz Dashboard
				    Assert.assertEquals(actualQuizTitle, expectedQuizTitle, "Actual quiz title does not match expected title");
				    ExtentManager.getTest().pass("Actual quiz title verified successfully with the expected title: <b>" + actualQuizTitle + "</b>");

    
				    
	}

}

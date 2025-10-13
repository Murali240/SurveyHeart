package com.surveyheart.tests.quizzes;

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


/** Test class to verify search functionality for quizzes in the Quiz Dashboard */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class QuizSearchFunctionalityTest extends BaseTest {
	
	    @Test (groups = "regression", priority = 19)
	    public void verifySearchFuctionalityForQuiz() {
	    	
	     // Login page - Initialize the Login Page object with the current WebDriver instance
			SurveyHeartLoginPage loginPage = new SurveyHeartLoginPage(driver);
					loginPage.clickSignInUsingEmail();
					loginPage.enterEmail("gofaw36836@pacfut.com");
					loginPage.clickNext();
					loginPage.enterPassword("Automation@1");
					loginPage.clickSignIn();
					loginPage.closeFeatureSpotlightIfPresent();            // In case popup appears after login
					ExtentManager.getTest().pass("Login successful with email and password.");
					
		 // Form Dashboard - Initialize the Form Dashboard Page object with the current WebDriver instance
			FormDashboardPage formDashboardPage = new FormDashboardPage(driver);
			        formDashboardPage.refreshPage();
			        formDashboardPage.clickQuizzesTab();
			 
			     // ===== Quiz Dashboard Page =====
			QuizDashboardPage quizDashboardPage = new QuizDashboardPage(driver);
	    			quizDashboardPage.clickCreateQuizButton();                   // Click on +Create Quiz button
					 

	    		 // ===== Quiz Builder Page =====
	        QuizBuilderPage quizBuilderPage = new QuizBuilderPage(driver);

	    		         // Set dynamic Quiz title
	    					String dynamicQuizTitle1 = "Alpha " + System.currentTimeMillis();
	    					quizBuilderPage.enterQuizTitle(dynamicQuizTitle1);

	    		 // ==== Add a Short Answer question ====
	    					quizBuilderPage.addQuestion(
	    					QuizQuestionType.SHORT_ANSWER,
	    					"What is your name?",
	    					"Madhu",               // Answer
	    					 null,                 // No options
	    					 -1                    // No correct option
	    					 );
	    					
				  // Quiz Settings
					 QuizSettingsPage settings1 = new QuizSettingsPage(driver);
					 settings1.clickSettingsButton();
					 settings1.clickSubmitButton();
					 ExtentManager.getTest().info("Quiz 1 created with: " + dynamicQuizTitle1);

				  // Close Share Popup
					 SharePopupPage sharePopup1 = new SharePopupPage(driver);
					 sharePopup1.clickCloseIcon();

				  // Return to Quiz Dashboard and click Create Quiz button again for Second Quiz creation
					 QuizDashboardPage quizDashboardPage2 = new QuizDashboardPage(driver);
					 quizDashboardPage2.clickCreateQuizButton();
					 
	
				  // === Quiz 2 CREATION ==
				  // Re-initialize Quiz Builder
					 QuizBuilderPage builder2 = new QuizBuilderPage(driver);
					 String dynamicQuizTitle2 = "Beta " + System.currentTimeMillis();
					 builder2.enterQuizTitle(dynamicQuizTitle2);

 				 // ==== Add a Short Answer question ====
					 builder2.addQuestion(
					QuizQuestionType.SHORT_ANSWER,
					"What is your name?",
					"Sounder",               // Answer
					 null,                   // No options
					 -1                      // No correct option
					 );
				
				  // Quiz Settings
					 QuizSettingsPage settings2 = new QuizSettingsPage(driver);
					 settings2.clickSettingsButton();
					 settings2.clickSubmitButton();
					 ExtentManager.getTest().info("Quiz 2 created with: " + dynamicQuizTitle2);

				  // Close Share Popup
					 SharePopupPage sharePopup2 = new SharePopupPage(driver);
					 sharePopup2.clickCloseIcon();
					
				  // Searching for 1st created quiz from Quiz Dashboard
					 quizDashboardPage.searchFormByTitle(dynamicQuizTitle1);
					 ExtentManager.getTest().pass("Searched Quiz <b>'" + dynamicQuizTitle1 + "'</b> was displayed successfully.");		
					 
	
					 
	}

}

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


/** Test class to verify deletion of multiple quizzes and display of UNDO toast message in Quiz Dashboard */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class QuizDashboardMultipleQuizzesDeletionTest extends BaseTest {
	
	@Test 
    public void verifyMultipleQuizzesDeletionWithUndoToastMessage()  {
    	
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
						String dynamicQuizTitle1 = "AlphaQuiz " + System.currentTimeMillis();
						quizBuilderPage.enterQuizTitle(dynamicQuizTitle1);

					// ==== Add a Short Answer question ====
					   quizBuilderPage.addQuestion(
					   QuizQuestionType.SHORT_ANSWER,
					   "What is your name?",
					   "Madhu",        // Answer
					    null,          // No options
						-1             // No correct option
					   );

				    
	    // Quiz Settings - Initialize the Quiz settings object with the current WebDriver instance
		QuizSettingsPage quizSettingsPage = new QuizSettingsPage(driver);
				   	
						quizSettingsPage.clickSettingsButton();
				   		quizSettingsPage.clickSubmitButton();
				   		ExtentManager.getTest().info("First quiz created successfully with: "+dynamicQuizTitle1);  	        
								    
				        				    
	// SharePopup - Initialize the Share popup object with the current WebDriver instance
	   SharePopupPage sharePopupPage = new SharePopupPage(driver);
	   			      sharePopupPage.clickCloseIcon();
	   			      
	   			      
	   			   // Creating 2nd Quiz
	   			      quizDashboardPage.clickCreateQuizButton();
	   			    
	   			   // Set dynamic Quiz title
					  String dynamicQuizTitle2 = "BetaQuiz " + System.currentTimeMillis();
					  quizBuilderPage.enterQuizTitle(dynamicQuizTitle2);

					// ==== Add a Short Answer question ====
					  quizBuilderPage.clickInitialAddQuestion();
					  quizBuilderPage.addShortQuestion("What is your name?","Madhu");
					   
					// Settings screen
					   quizSettingsPage.clickSettingsButton();
				   	   quizSettingsPage.clickSubmitButton();
				   	   ExtentManager.getTest().info("Second quiz created successfully with: "+dynamicQuizTitle2); 
				   	   sharePopupPage.clickCloseIcon();
	   			      
	   		
				    // Creating 3rd Quiz
		   			   quizDashboardPage.clickCreateQuizButton();
		   			      
		   		    // Set dynamic Quiz title
					   String dynamicQuizTitle3 = "GammaQuiz " + System.currentTimeMillis();
					   quizBuilderPage.enterQuizTitle(dynamicQuizTitle3);

					// ==== Add a Short Answer question ====
					   quizBuilderPage.clickInitialAddQuestion();
					   quizBuilderPage.addShortQuestion("What is your name?","Madhu");
						   
					 // Settings screen
					    quizSettingsPage.clickSettingsButton();
					   	quizSettingsPage.clickSubmitButton();
					   	ExtentManager.getTest().info("Third quiz created successfully with: "+dynamicQuizTitle3); 
					   	sharePopupPage.clickCloseIcon();
				       

		   		    //  Come back to Quiz Dashboard
	   			        quizDashboardPage.refreshPage();
	   			        formDashboardPage.ifFollowUsPopupDisplayed();
	   			        formDashboardPage.clickQuizzesTab();
		   			
	   			     // Deleting multiple quizzes
	   			        quizDashboardPage.clickFirstQuizSelectionCircle();
	   			        quizDashboardPage.clickSecondQuizSelectionCircle();
	   			        quizDashboardPage.clickDeleteButtonQuizSelection();
	   			        quizDashboardPage.clickConfirmDeleteButtonQuizSelection();
	  			        String undoToastMessage = quizDashboardPage.getFullUndoToastMessage();
	  			        ExtentManager.getTest().pass("UNDO toast message displayed successfully in Quiz Dashboard for multiple quiz deletion: <b>" + undoToastMessage + "</b>");
	  			    
	  			     // Get latest Quiz, after 2 quizzes deleted
	  			        String actualQuizTiltle = quizDashboardPage.getFirstQuizCardTitle();
	  			        String expectedQuizTitle=dynamicQuizTitle1;
	  			    
	  			     // Assert that actualQuizTitle and expectedQuizTitle 
	  			        Assert.assertEquals(actualQuizTiltle, expectedQuizTitle, "Actual quiz title does not match expected title");
	  			        ExtentManager.getTest().pass("After successfully deleting 2 quizzes, the latest quiz in Quiz Dashboard is: <b>" + expectedQuizTitle + "</b>");

					  
			    
     }

}

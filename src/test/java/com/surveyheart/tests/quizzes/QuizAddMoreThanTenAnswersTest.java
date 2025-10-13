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
import com.surveyheart.pages.SubmittedPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Test class to verify a quiz can submit more than ten answers. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class QuizAddMoreThanTenAnswersTest extends BaseTest {
	
	@Test 
	public void verifyQuizCanSubmitMoreThanTenAnswers() {
		
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
				    String dynamicQuizTitle = "MoreThanTenAnswersQuiz " + System.currentTimeMillis();
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
	   				ExtentManager.getTest().info("Quiz created successfully with : "+dynamicQuizTitle);  	        
					    
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
					ExtentManager.getTest().pass("First answer submitted successfully: "+quizTitle);
					
					  
	// Submitted page - Initialize the Submitted page object with the current WebDriver instance
	   SubmittedPage submittedPage = new SubmittedPage(driver);
	   				 submittedPage.isSubmittedMessageDisplayed();
	   				 submittedPage.isViewResultsButtonDisplayed();
	   				 ExtentManager.getTest().pass("<b>'Submitted'</b> page displayed successfully on the first attempt with the <b>'View Results'</b> button : <b>"+quizTitle+"</b>");
				
	   			 
	   			 // Refresh page
	   				submittedPage.refreshPage();
	   				
	   			 // Welcome page
	   				quizPage.clickStart();
	   				quizPage.enterName("Gopi");
	   				quizPage.clickStartQuiz();
	
	   			 // Quiz page - 2nd attempt
	   				quizPage.answerShortQuestion("Gopi krishna"); 
					quizPage.clickSubmitButton();
					ExtentManager.getTest().pass("Second answer submitted successfully.");	
					
				 // Submitted page
	   				submittedPage.isSubmittedMessageDisplayed();
	   				submittedPage.isViewResultsButtonDisplayed();
	   				
				
	   			 // Refresh page
	   				submittedPage.refreshPage();
	   				
	   			 // Welcome page
	   				quizPage.clickStart();
	   				quizPage.enterName("Madhu");
	   				quizPage.clickStartQuiz();
	
	   			 // Quiz page - 3rd attempt
	   				quizPage.answerShortQuestion("Madhu Simma"); 
					quizPage.clickSubmitButton();
					ExtentManager.getTest().pass("Third answer submitted successfully.");
					
				 // Submitted page
	   				submittedPage.isSubmittedMessageDisplayed();
	   				submittedPage.isViewResultsButtonDisplayed();
	   				
	   				
	   			 // Refresh page
	   				submittedPage.refreshPage();
	   				
	   			 // Welcome page
	   				quizPage.clickStart();
	   				quizPage.enterName("Avinash");
	   				quizPage.clickStartQuiz();
	
	   			 // Quiz page - 4th attempt
	   				quizPage.answerShortQuestion("Avinash Rowthu"); 
					quizPage.clickSubmitButton();
					ExtentManager.getTest().pass("Fourth answer submitted successfully.");
					
				 // Submitted page
	   				submittedPage.isSubmittedMessageDisplayed();
	   				submittedPage.isViewResultsButtonDisplayed();
	   				
	   				
	   			 // Refresh page
	   				submittedPage.refreshPage();
	   				
	   			 // Welcome page
	   				quizPage.clickStart();
	   				quizPage.enterName("Mani");
	   				quizPage.clickStartQuiz();
	
	   			 // Quiz page - 5th attempt
	   				quizPage.answerShortQuestion("Manikanta Flutter"); 
					quizPage.clickSubmitButton();
					ExtentManager.getTest().pass("Fifth answer submitted successfully.");
					
				 // Submitted page
	   				submittedPage.isSubmittedMessageDisplayed();
	   				submittedPage.isViewResultsButtonDisplayed();
	   				
	   				
	   			 // Refresh page
	   				submittedPage.refreshPage();
	   				
	   			 // Welcome page
	   				quizPage.clickStart();
	   				quizPage.enterName("Deepak");
	   				quizPage.clickStartQuiz();
	
	   			 // Quiz page - 6th attempt
	   				quizPage.answerShortQuestion("Deepak Kumar"); 
					quizPage.clickSubmitButton();
					ExtentManager.getTest().pass("Sixth answer submitted successfully.");
					
				 // Submitted page
	   				submittedPage.isSubmittedMessageDisplayed();
	   				submittedPage.isViewResultsButtonDisplayed();
	
	   				
	   			 // Refresh page
	   				submittedPage.refreshPage();
	   				
	   			 // Welcome page
	   				quizPage.clickStart();
	   				quizPage.enterName("Mohan");
	   				quizPage.clickStartQuiz();
	
	   			 // Quiz page - 7th attempt
	   				quizPage.answerShortQuestion("Mohan Krishna"); 
					quizPage.clickSubmitButton();
					ExtentManager.getTest().pass("Seventh answer submitted successfully.");
					
				 // Submitted page
	   				submittedPage.isSubmittedMessageDisplayed();
	   				submittedPage.isViewResultsButtonDisplayed();
	   				
	   				
	   			 // Refresh page
	   				submittedPage.refreshPage();
	   				
	   			 // Welcome page
	   				quizPage.clickStart();
	   				quizPage.enterName("Rohit");
	   				quizPage.clickStartQuiz();
	
	   			 // Quiz page - 8th attempt
	   				quizPage.answerShortQuestion("Rohit Kumar"); 
					quizPage.clickSubmitButton();
					ExtentManager.getTest().pass("Eighth answer submitted successfully.");
					
				 // Submitted page
	   				submittedPage.isSubmittedMessageDisplayed();
	   				submittedPage.isViewResultsButtonDisplayed();
	   				
	   				
	   			 // Refresh page
	   				submittedPage.refreshPage();
	   				
	   			 // Welcome page
	   				quizPage.clickStart();
	   				quizPage.enterName("Sai");
	   				quizPage.clickStartQuiz();
	
	   			 // Quiz page - 9th attempt
	   				quizPage.answerShortQuestion("Sai Krishna"); 
					quizPage.clickSubmitButton();
					ExtentManager.getTest().pass("Ninth answer submitted successfully.");
					
				 // Submitted page
	   				submittedPage.isSubmittedMessageDisplayed();
	   				submittedPage.isViewResultsButtonDisplayed();
	   				
	   				
	   			 // Refresh page
	   				submittedPage.refreshPage();
	   				
	   			 // Welcome page
	   				quizPage.clickStart();
	   				quizPage.enterName("Chandu");
	   				quizPage.clickStartQuiz();
	
	   			 // Quiz page - 10th attempt
	   				quizPage.answerShortQuestion("Chandu Gujjarlapudi"); 
					quizPage.clickSubmitButton();
					ExtentManager.getTest().pass("Tenth answer submitted successfully.");
					
				 // Submitted page
	   				submittedPage.isSubmittedMessageDisplayed();
	   				submittedPage.isViewResultsButtonDisplayed();
	   				
	   				
	   			 // Refresh page
	   				submittedPage.refreshPage();
	   				
	   			 // Welcome page
	   				quizPage.clickStart();
	   				quizPage.enterName("Charan");
	   				quizPage.clickStartQuiz();
	
	   			 // Quiz page - 11th attempt
	   				quizPage.answerShortQuestion("Charan Energy"); 
					quizPage.clickSubmitButton();
					ExtentManager.getTest().pass("Eleventh answer submitted successfully.");
					
				 // Submitted page
	   				submittedPage.isSubmittedMessageDisplayed();
	   				submittedPage.isViewResultsButtonDisplayed();
	   				
	   				
	   			 // Refresh page
	   				submittedPage.refreshPage();
	   				
	   			 // Welcome page
	   				quizPage.clickStart();
	   				quizPage.enterName("Naveen");
	   				quizPage.clickStartQuiz();
	
	   			 // Quiz page - 12th attempt
	   				quizPage.answerShortQuestion("Naveen Cherukuri"); 
					quizPage.clickSubmitButton();
					ExtentManager.getTest().pass("Twelth answer submitted successfully.");
					
				 // Submitted page
	   				submittedPage.isSubmittedMessageDisplayed();
	   				submittedPage.isViewResultsButtonDisplayed();
	   				
	   				submittedPage.closeChildWindowAndSwitchToParent(sharePopupPage.getParentWindowHandle());
	   				
	   				sharePopupPage.clickCloseIcon();
	   				quizDashboardPage.refreshPage();
	   				formDashboardPage.clickQuizzesTab();
	   				
	   			 // Click on 1st Quiz More
	   				quizDashboardPage.clickMoreOptionsForFirstQuiz();
	   				int numberOfQuizAnswersOnQuizCard = quizDashboardPage.getTotalAnswersCountInMore();
	   			    ExtentManager.getTest().info("Total responses count in form card: <b>" + numberOfQuizAnswersOnQuizCard + "</b>");

				  String actualQuizTiltle = quizDashboardPage.getFirstQuizCardTitle();
				  String expectedQuizTitle=dynamicQuizTitle;
	
			   // Assert that actualFormTitle and expectedFormTitle 
				  Assert.assertEquals(actualQuizTiltle, expectedQuizTitle, "Actual quiz title does not match expected title");
				  ExtentManager.getTest().pass("Actual Quiz title is matched with expected Quiz title: <b>"+ dynamicQuizTitle+"</b>");
			
	
				  
	}

}

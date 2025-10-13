package com.surveyheart.tests.quizzes;

import java.util.Arrays;

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


/** Test class to verify that a created quiz can be published successfully and the published icon is displayed on the Quiz Dashboard */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class VerifyQuizPublishTest extends BaseTest {
	
	@Test 
	public void verifyQuizPublishedSuccessfully()  {
		
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

	    QuizDashboardPage quizDashboardPage = new QuizDashboardPage(driver);
	    			quizDashboardPage.clickCreateQuizButton();

	    // ===== Quiz Builder Page =====
	    QuizBuilderPage quizBuilderPage = new QuizBuilderPage(driver);

	             // Set dynamic Quiz title
				    String dynamicQuizTitle = "QuizPublish " + System.currentTimeMillis();
				    quizBuilderPage.enterQuizTitle(dynamicQuizTitle);

				    // ==== Add a Short Answer question ====
				    quizBuilderPage.addQuestion(
				        QuizQuestionType.SHORT_ANSWER,
				        "What is your name?",
				        "Madhu",                       // Answer
				        null,                          // No options
				        -1                             // No correct option
				    );
				    
				    // ==== Add a Long Answer question ====
				    quizBuilderPage.addQuestion(
				        QuizQuestionType.LONG_ANSWER,
				        "Describe yourself briefly",
				        "Quick learner",                // Answer
				        null,                           // No options
				        -1 
				     );
			
				    // ==== Add an MCQ question ====
				    quizBuilderPage.addQuestion(
				        QuizQuestionType.MULTIPLE_CHOICE,
				        "Select Selenium tool type",
				        null,
				        Arrays.asList("IDE", "WebDriver", "Grid"),
				        1                               // WebDriver is the correct answer
				    );
				    
				    // ==== Add an Dropdown question ====
				    quizBuilderPage.addQuestion(
				        QuizQuestionType.DROPDOWN,
				        "Which is a platform-independent language?",
				        null,
				        Arrays.asList("Python", ".Net", "Java"),
				        2                                // Java is the correct answer
				    );
				    
				    // ==== Add a File Upload question ====
				    quizBuilderPage.addQuestion(
				        QuizQuestionType.FILE_UPLOAD,
				        "Upload your certification PDF",
				        null,
				        null,
				        -1
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
	   				quizPage.enterName("mad");
	   				quizPage.clickStartQuiz();
	   				
	   			 // Quiz page
	   				String quizTitle = quizPage.getQuizTitle();
				    String totalQuestionCount = quizPage.getTotalQuestionCount();
				    ExtentManager.getTest().info("Quiz created with : <b>" + totalQuestionCount + " questions</b>");
				    
				 // Enter answers for all questions
				    quizPage.answerShortQuestion("Madhu");
				    quizPage.answerLongQuestion("Quick learner");
				    quizPage.selectMCQOption(2);
				    quizPage.selectDropdownOption(3);
				    quizPage.uploadFile("C:\\Users\\mural\\Downloads\\video.mp4");
				    quizPage.clickSubmitButton();
				    ExtentManager.getTest().pass("All quiz answers submitted successfully on Quiz page: <b>"+quizTitle+"</b>");

				    
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
	   				 
	   				 
	 // Answers page - Initialize the Answers page object with the current WebDriver instance
	  	AnswersPage answersPage = new AnswersPage(driver);
	  	
	  	            answersPage.clickIndividualButton();
	  	                 
	  	         // Example: enter 1 mark - Long Answer
	  	            answersPage.clickSecondAnswerEditIcon();
	  	            answersPage.enterMarks("1");
	  	            answersPage.clickSaveButton();
	  	           
	  	         // Example: enter 1 mark - File Upload 
	  	            //answersPage.clickFifthAnswerEditIcon();
	  	            answersPage.enterMarks("1");
	  	            answersPage.clickSaveButton();
	  	
				 // Click on Publish Button
	  	            answersPage.clickPublishButton();
	  	            
	  	         // Click Publish button on Publish popup
	  	            answersPage.clickPublishButtonOnPublishPopup();
	  	            
	  	         // Get success message
	  	            String successMessage = answersPage.getPublishSuccessfullyPopup().getText();
	  	            ExtentManager.getTest().pass("Publish Popup Message: <b>" + successMessage+"</b>");
	  	            
	  	         // Close Publish popup
	  	            answersPage.clickCloseButtonOnPublishSuccessfullyPopup();
	  	            
	  	         // Refresh the page
	  	            answersPage.pageRefresh();
	  	          
	  	            
	  	         // Switch to Quiz Dashboard
	  	            formDashboardPage.clickQuizzesTab();
	  	           
	  	         // Assertion
	  	            Assert.assertTrue(
	  	               quizDashboardPage.isPublishedIconVisible(),
	  	               "Quiz Published icon is not visible on Quiz Card!" ); 
	  	            ExtentManager.getTest().pass("Quiz Published icon is successfully visible on Quiz Card: <b>" + quizTitle + "</b>");


					    
	}

}

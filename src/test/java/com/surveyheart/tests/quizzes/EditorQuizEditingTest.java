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
import com.surveyheart.pages.SharedQuizDashboardPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Test class to verify that an Editor can edit a shared quiz */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class EditorQuizEditingTest extends BaseTest {
	
	@Test
    public void verifyEditorQuizEditing()  {
    	
     // Login page - Initialize the Login Page object with the current WebDriver instance
		SurveyHeartLoginPage loginPage = new SurveyHeartLoginPage(driver);
						loginPage.clickSignInUsingEmail();
						loginPage.enterEmail("gofaw36836@pacfut.com");
						loginPage.clickNext();
						loginPage.enterPassword("Automation@1");
						loginPage.clickSignIn();
						loginPage.closeFeatureSpotlightIfPresent();                       // In case popup appears after login
						ExtentManager.getTest().pass("Owner login successful with email and password.");
				
	 // ===== Navigate to Quiz Dashboard =====
	    FormDashboardPage formDashboardPage = new FormDashboardPage(driver);
				    
						formDashboardPage.refreshPage();
					    formDashboardPage.clickQuizzesTab();

		QuizDashboardPage quizDashboardPage = new QuizDashboardPage(driver);
				    	  quizDashboardPage.clickCreateQuizButton();

	 // ===== Quiz Builder Page =====
		QuizBuilderPage quizBuilderPage = new QuizBuilderPage(driver);

				     // Set dynamic Quiz title
						String dynamicQuizTitle = "EditorQuizEditing " + System.currentTimeMillis();
						quizBuilderPage.enterQuizTitle(dynamicQuizTitle);

					 // ==== Add a Short Answer question ====
					    quizBuilderPage.addQuestion(
						QuizQuestionType.SHORT_ANSWER,
						"What is your name?",
					    "Madhu",                        // Answer
					     null,                          // No options
					     -1                             // No correct option
						);

	 // Quiz Settings - Initialize the Quiz settings object with the current WebDriver instance
		QuizSettingsPage quizSettingsPage = new QuizSettingsPage(driver);
		                 quizSettingsPage.clickSettingsButton();
		                 quizSettingsPage.clickCollaborationTab();      
					     quizSettingsPage.clickCollaborationTab();
					     quizSettingsPage.addCollaborator("meyap64096@forcrack.com", "Editor");
					     quizSettingsPage.clickSubmitButton();
					     ExtentManager.getTest().info("Owner added an editor successfully.");
					     ExtentManager.getTest().info("Owner quiz created successfully with: "+dynamicQuizTitle);
					
	  // SharePopup - Initialize the Share popup object with the current WebDriver instance
		 SharePopupPage sharePopupPage = new SharePopupPage(driver);
					    sharePopupPage.clickCloseIcon();
					
			        //  Come back to Form Dashboard
					    quizDashboardPage.refreshPage();
					    formDashboardPage.clickAccountButton();
					    formDashboardPage.clickSignoutButton();
					    ExtentManager.getTest().pass("Owner signed out successfully.");
					
			         // Admin login
					    loginPage.clickSignInUsingEmail();
					    loginPage.enterEmail("meyap64096@forcrack.com");
					    loginPage.clickNext();
					    loginPage.enterPassword("Automation@3");
					    loginPage.clickSignIn();
					    loginPage.closeFeatureSpotlightIfPresent();                       // In case popup appears after login
					    ExtentManager.getTest().pass("Editor Login successful with email and password.");
			        
				     // Clicking on Share button
					    formDashboardPage.clickSharedTab();
			        
	 // Shared Quiz Dashboard - Initialize the Share popup object with the current WebDriver instance
		SharedQuizDashboardPage sharedQuizDashboard = new SharedQuizDashboardPage(driver);
		                sharedQuizDashboard.clickSharedQuizzesTab();
		                sharedQuizDashboard.clickMoreOptions();
		                sharedQuizDashboard.clickEditQuizButton();
			        
		                // ==== Add a Long Answer question ====
					    quizBuilderPage.addQuestion(
					        QuizQuestionType.LONG_ANSWER,
					        "Describe yourself briefly",
					        "Quick learner",                // Answer
					        null,                           // No options
					        -1 
					     );
			        
			         // Settings
					    quizSettingsPage.clickSettingsButton();
					    quizSettingsPage.clickSubmitButton();
			            ExtentManager.getTest().pass("Editor successfully edited the shared quiz and added a new question.");
			        
			         // Share popup 
			            sharePopupPage.clickCloseIcon();
					
				     // Come back to Form Dashboard
			            quizDashboardPage.refreshPage();
			            formDashboardPage.clickAccountButton();
			            formDashboardPage.clickSignoutButton();
					    ExtentManager.getTest().pass("Editor signed out successfully.");
			        
			         // Again owner login 
					    loginPage.clickSignInUsingEmail();
					    loginPage.enterEmail("gofaw36836@pacfut.com");
					    loginPage.clickNext();
					    loginPage.enterPassword("Automation@1");
					    loginPage.clickSignIn();
					    loginPage.closeFeatureSpotlightIfPresent();                       // In case popup appears after login
					    ExtentManager.getTest().pass("Again owner login successful with email and password.");
					
				     // Quiz Dashboard
					    formDashboardPage.clickQuizzesTab();
					    quizDashboardPage.clickMoreOptionsForFirstQuiz();
					    quizDashboardPage.storeParentWindowHandle(); 
					    quizDashboardPage.clickViewQuizButton();
					    quizDashboardPage.switchToChildWindow();
			        
	  // Quiz page - Initialize the Form page object with the current WebDriver instance
		 QuizPage quizPage = new QuizPage(driver);
		                quizPage.clickStart();
			            quizPage.enterName("Sounder");
			            quizPage.clickStartQuiz();
		 
			         // Quiz page
					    String quizTitle = quizPage.getQuizTitle();
					    String totalQuestionCount = quizPage.getTotalQuestionCount();
					    ExtentManager.getTest().info("Quiz page displayed with <b>" + totalQuestionCount + " questions</b>.");
					    ExtentManager.getTest().pass("Editor's newly added question was displayed on the Quiz page : <b>" + quizTitle+"</b>");
  
			        
		        
	}	

}




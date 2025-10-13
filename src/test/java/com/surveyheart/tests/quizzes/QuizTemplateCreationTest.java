package com.surveyheart.tests.quizzes;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.surveyheart.base.BaseTest;
import com.surveyheart.pages.FormDashboardPage;
import com.surveyheart.pages.QuizPage;
import com.surveyheart.pages.QuizSettingsPage;
import com.surveyheart.pages.QuizzesTemplatesPage;
import com.surveyheart.pages.SharePopupPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Test class to verify that a quiz template can be created and quiz page opened successfully */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class QuizTemplateCreationTest extends BaseTest {
	
	@Test (groups = "regression", priority = 20)
    public void verifyQuizTemplateCreation() {
    	
     // Login page - Initialize the Login Page object with the current WebDriver instance
		SurveyHeartLoginPage loginPage = new SurveyHeartLoginPage(driver);
					loginPage.clickSignInUsingEmail();
					loginPage.enterEmail("gofaw36836@pacfut.com");
					loginPage.clickNext();
					loginPage.enterPassword("Automation@1");
					loginPage.clickSignIn();
					loginPage.closeFeatureSpotlightIfPresent();               // In case popup appears after login
					ExtentManager.getTest().pass("Login successful with email and password.");
				
	 // Form Dashboard - Initialize the Form Dashboard Page object with the current WebDriver instance
		FormDashboardPage formDashboardPage = new FormDashboardPage(driver);
					formDashboardPage.refreshPage();
					formDashboardPage.clickTemplatesTab();
					 
	    	
	 // Quiz Template - Initialize the Quiz builder object with the current WebDriver instance
        QuizzesTemplatesPage quizTemplatesPage = new QuizzesTemplatesPage(driver);
			        quizTemplatesPage.clickQuizzesTab();
			        quizTemplatesPage.clickFirstQuizTemplateCard();
	

     // Quiz Settings - Initialize the Quiz settings object with the current WebDriver instance
       	QuizSettingsPage quizSettingsPage = new QuizSettingsPage(driver);
			       	quizSettingsPage.clickSettingsButton();
			       	quizSettingsPage.clickSubmitButton();
       		        
       			    
     // SharePopup - Initialize the Share popup object with the current WebDriver instance
       	SharePopupPage sharePopup = new SharePopupPage(driver);
       				   sharePopup.clickViewIcon();
       				   sharePopup.getParentWindowHandle();
       				   
					// ✅ Store parent window before switching
					   sharePopup.storeParentWindowHandle();
					   sharePopup.switchToChildWindowThroughViewIcon();
       					
     // Quiz page - Initialize the Quiz page object with the current WebDriver instance
        QuizPage quizPage = new QuizPage(driver);
       		   		 quizPage.clickStart();
       		   	     quizPage.enterName("Sounder");
       		   	     quizPage.clickStartQuiz();
  
       		   	  // Quiz page
       				 String quizTitle = quizPage.getQuizTitle();
       				 String totalQuestionCount = quizPage.getTotalQuestionCount();
       				 ExtentManager.getTest().info("Selected quiz template is displayed with <b>" + totalQuestionCount + " questions.</b>");
       				 ExtentManager.getTest().pass("Quiz template was created successfully with the title : <b>" + quizTitle+"</b>");
       				 
       				 
       				 
	}

}

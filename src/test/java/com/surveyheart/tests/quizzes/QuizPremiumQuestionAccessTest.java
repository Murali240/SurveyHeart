package com.surveyheart.tests.quizzes;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.surveyheart.base.BaseTest;
import com.surveyheart.pages.FormDashboardPage;
import com.surveyheart.pages.PremiumPlansPage;
import com.surveyheart.pages.QuizBuilderPage;
import com.surveyheart.pages.QuizDashboardPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Test class to verify free user access restriction when trying to create a quiz with premium question types. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class QuizPremiumQuestionAccessTest extends BaseTest {
	
	@Test (groups = "regression", priority = 24)
	public void verifyFreeUserTryingToCreateQuizWithPremiumQuestion() {
		
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
	    				quizDashboardPage.clickCreateQuizButton();                // Click on +Create Quiz button

	    // ===== Quiz Builder Page =====
	    QuizBuilderPage quizBuilderPage = new QuizBuilderPage(driver);

		             // Set dynamic Quiz title
					    String dynamicQuizTitle = "PremiumQ " + System.currentTimeMillis();
					    quizBuilderPage.enterQuizTitle(dynamicQuizTitle);
	
					 // ==== Try to adding Picture Choice question ====
					    
					    quizBuilderPage.clickInitialAddQuestion();
					    quizBuilderPage.clickPictureChoiceQuestion();
					    String premiumWarningText = quizBuilderPage.getPremiumWarningPopup().getText();
					    ExtentManager.getTest().pass("Clicked 'Picture Choice' and displayed premium warning: <b>" + premiumWarningText + "</b> page");

					 // Click on View Plans button
					    quizBuilderPage.clickViewPlansButton();
					   

					    
					 // ===== Navigate to Premium plans page =====
					    PremiumPlansPage premiumPlansPage = new PremiumPlansPage(driver);
					    String premiumPlansHeaderText = premiumPlansPage.getPremiumPlansTitle().getText();
					    ExtentManager.getTest().pass("Clicked 'View Plans' and navigated to: <b>" + premiumPlansHeaderText + "</b>");
					    premiumPlansPage.clickCloseIcon();
					    ExtentManager.getTest().pass("Successfully clicked the <b>close icon</b> on the Premium Plans page.");

					    
	
	}			    

}

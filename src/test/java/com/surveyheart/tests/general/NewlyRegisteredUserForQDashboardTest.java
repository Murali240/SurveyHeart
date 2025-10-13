package com.surveyheart.tests.general;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.surveyheart.base.BaseTest;
import com.surveyheart.pages.FormDashboardPage;
import com.surveyheart.pages.QuizDashboardPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


@Listeners(com.surveyheart.listeners.TestListener.class)
public class NewlyRegisteredUserForQDashboardTest extends BaseTest {
	
	@Test
    public void verifyForQDashboardForNewlyRegisteredUser() {

	 // Initialize the Login Page object with the current WebDriver instance
		SurveyHeartLoginPage loginPage = new SurveyHeartLoginPage(driver);
						loginPage.clickSignInUsingEmail();
						loginPage.enterEmail("damiliv780@mardiek.com");
						loginPage.clickNext();
						loginPage.enterPassword("Automation@6");
						loginPage.clickSignIn();
						loginPage.closeFeatureSpotlightIfPresent();          // In case popup appears after login
						ExtentManager.getTest().pass("Login successful with email and password.");	
					
     // Initialize the Form Dashboard Page object with the current WebDriver instance
		FormDashboardPage formDashboardPage = new FormDashboardPage(driver);
		
						String formMsg=formDashboardPage.getNoFormAvailable().getText();			  
						ExtentManager.getTest().pass("<b>" + formMsg + "</b> displayed in Form Dashboard for new user.");
						

	 // Initialize the Quiz Dashboard Page object with the current WebDriver instance
		QuizDashboardPage quizDashboardPage = new QuizDashboardPage(driver);	
		
						formDashboardPage.clickQuizzesTab();
						String quizMsg=quizDashboardPage.getNoQuizAvailable().getText();			  
						ExtentManager.getTest().pass("<b>" + quizMsg + "</b> displayed in Quiz Dashboard for new user.");

	    
				
						
						
		
	 }

}

package com.surveyheart.tests.quizzes;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.surveyheart.base.BaseTest;
import com.surveyheart.pages.FormDashboardPage;
import com.surveyheart.pages.QuizDashboardPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Test class to verify the total number of quizzes displayed in the Quiz Dashboard */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class TotalQuizzesCountInQuizDashboardTest extends BaseTest {
	
	    @Test (groups = "regression", priority = 6)
	    public void verifyTotalQuizzesCountInDashboard() {
	    	
	    // Initialize the Login Page object with the current WebDriver instance
	       SurveyHeartLoginPage loginPage = new SurveyHeartLoginPage(driver);
			    		loginPage.clickSignInUsingEmail();
			    		loginPage.enterEmail("gofaw36836@pacfut.com");
			    		loginPage.clickNext();
			    		loginPage.enterPassword("Automation@1");
			    		loginPage.clickSignIn();
			    		loginPage.closeFeatureSpotlightIfPresent();           // In case popup appears after login
			    		ExtentManager.getTest().pass("Login successful with email and password.");	    		
		   
			    		
		 // Form dashboard - Initialize the Form Dashboard Page object with the current WebDriver instance
		    FormDashboardPage formDashboardPage = new FormDashboardPage(driver);
					    formDashboardPage.refreshPage();
					    formDashboardPage.clickQuizzesTab();
		    
		    
		 // Quiz Dashboard - Initialize the Quiz Dashboard Page object with the current WebDriver instance
		    QuizDashboardPage quizDashboardPage = new QuizDashboardPage(driver);
		    		
		    		int totalQuizzes = quizDashboardPage.getTotalQuizzesCount();
		    		if (totalQuizzes != 0) {
		    			ExtentManager.getTest().info("Total number of quizzes found in the Quiz Dashboard: <b>" + totalQuizzes+"</b>");
		    		} else {
		    			ExtentManager.getTest().warning("There are no quizzes available in the Quiz Dashboard.");
		    		}

		    		
		    		
	}

}

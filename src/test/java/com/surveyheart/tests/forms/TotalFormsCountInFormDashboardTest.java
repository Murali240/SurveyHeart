package com.surveyheart.tests.forms;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.surveyheart.base.BaseTest;
import com.surveyheart.pages.FormDashboardPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Test class to verify the total number of forms displayed in the Form Dashboard. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class TotalFormsCountInFormDashboardTest extends BaseTest {
	
	    @Test (groups = "regression", priority = 5)
	    public void verifyTotalFormsCountInDashboard() {
	    	
	    // Initialize the Login Page object with the current WebDriver instance
	       SurveyHeartLoginPage loginPage = new SurveyHeartLoginPage(driver);
		    		loginPage.clickSignInUsingEmail();
		    		loginPage.enterEmail("gofaw36836@pacfut.com");
		    		loginPage.clickNext();
		    		loginPage.enterPassword("Automation@1");
		    		loginPage.clickSignIn();
		    		loginPage.closeFeatureSpotlightIfPresent();                                // In case popup appears after login
		    		ExtentManager.getTest().pass("Login successful with email and password.");	    		
		    		 
		 // Form dashboard - Initialize the Form Dashboard Page object with the current WebDriver instance
		    FormDashboardPage formdashboard = new FormDashboardPage(driver);
		    		formdashboard.refreshPage();
		    		formdashboard.getTotalFormsCount();
		    		
		    		int totalForms = formdashboard.getTotalFormsCount();
		    		if (totalForms != 0) {
		    			ExtentManager.getTest().info("Total number of forms found in the Form Dashboard : " + totalForms);
		    		} else {
		    			ExtentManager.getTest().warning("There are no forms available in the Form Dashboard.");
		    		}

	
	
	}

}


/**
Scenario: Test class to verify the total number of forms displayed in the Form Dashboard.

Steps:
1. Login to SurveyHeart using valid credentials.
2. Refresh the Form Dashboard page.
3. Retrieve the total count of forms displayed in the dashboard.
4. Log the total number of forms:
   - If forms exist, log the count.
   - If no forms are available, log a warning message.

Expected Result:
- The dashboard displays the correct total number of forms or an appropriate message if none exist.
*/



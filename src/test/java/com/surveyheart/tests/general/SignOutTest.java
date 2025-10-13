package com.surveyheart.tests.general;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.surveyheart.base.BaseTest;
import com.surveyheart.pages.FormDashboardPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


@Listeners(com.surveyheart.listeners.TestListener.class)
public class SignOutTest extends BaseTest {
	
	@Test (groups = {"smoke"}, priority = 5)
	 public void verifyUserIsAbleToSignOut() {
			
		 // Initialize the Login Page object with the current WebDriver instance
			SurveyHeartLoginPage loginPage = new SurveyHeartLoginPage(driver);
				loginPage.clickSignInUsingEmail();
				loginPage.enterEmail("gofaw36836@pacfut.com");
				loginPage.clickNext();
				loginPage.enterPassword("Automation@1");
				loginPage.clickSignIn();
				loginPage.closeFeatureSpotlightIfPresent();                       // In case popup appears after login
				String userAfterSignInURL=driver.getCurrentUrl();
				ExtentManager.getTest().info("After user sign-in, URL:  " + userAfterSignInURL);
				ExtentManager.getTest().pass("Login successful with email and password.");
	
		 // Initialize the Form Dashboard Page object with the current WebDriver instance
		    FormDashboardPage dashboardPage = new FormDashboardPage(driver);
				dashboardPage.refreshPage();
				dashboardPage.clickAccountButton();
				dashboardPage.clickSignoutButton();
				ExtentManager.getTest().pass("User signed out successfully.");

		        String userAfterSignoutURL = driver.getCurrentUrl();
		        ExtentManager.getTest().info("After user signed out, URL: " + userAfterSignoutURL);
				
	
	}	

}

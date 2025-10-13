package com.surveyheart.tests.general;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.surveyheart.base.BaseTest;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


@Listeners(com.surveyheart.listeners.TestListener.class)
public class SignInWithEmailTest extends BaseTest {
	
	 @Test (groups = {"smoke"}, priority = 1)
	    public void loginWithValidCredentialsTest() {
	
		 // Initialize the Login Page object with the current WebDriver instance
			SurveyHeartLoginPage loginPage = new SurveyHeartLoginPage(driver);
				loginPage.clickSignInUsingEmail();
				loginPage.enterEmail("gofaw36836@pacfut.com");
				loginPage.clickNext();
				loginPage.enterPassword("Automation@1");
				loginPage.clickSignIn();
				loginPage.closeFeatureSpotlightIfPresent();                       // In case popup appears after login
				ExtentManager.getTest().pass("Login successful with email and password.");
	
		
    }

}

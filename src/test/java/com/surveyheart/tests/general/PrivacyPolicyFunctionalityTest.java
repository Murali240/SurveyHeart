package com.surveyheart.tests.general;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.surveyheart.base.BaseTest;
import com.surveyheart.pages.FormDashboardPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


@Listeners(com.surveyheart.listeners.TestListener.class)
public class PrivacyPolicyFunctionalityTest extends BaseTest {
	
	@Test
    public void verifyPrivacyPolicyURL() {

	 // Initialize the Login Page object with the current WebDriver instance
		SurveyHeartLoginPage loginPage = new SurveyHeartLoginPage(driver);
			loginPage.clickSignInUsingEmail();
			loginPage.enterEmail("gofaw36836@pacfut.com");
			loginPage.clickNext();
			loginPage.enterPassword("Automation@1");
			loginPage.clickSignIn();
			loginPage.closeFeatureSpotlightIfPresent();                       // In case popup appears after login
			ExtentManager.getTest().pass("Login successful with email and password.");
	
     // Initialize the Form Dashboard Page object with the current WebDriver instance	
		FormDashboardPage dashboardPage = new FormDashboardPage(driver);
			String privacyPolicyURL = dashboardPage.clickPrivacyPolicyAndGetURL();      //Storing Privacy policy URL in String variable
			ExtentManager.getTest().pass("Privacy Policy URL : " + privacyPolicyURL);
	
		
		
	 }
		
}

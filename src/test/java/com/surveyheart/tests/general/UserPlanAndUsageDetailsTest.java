package com.surveyheart.tests.general;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.surveyheart.base.BaseTest;
import com.surveyheart.pages.FormDashboardPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


@Listeners(com.surveyheart.listeners.TestListener.class)
public class UserPlanAndUsageDetailsTest extends BaseTest {
	
	 @Test (groups = "regression", priority = 3)
	    public void verifyUserPlanAndUsageDetails() {
	
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
				dashboardPage.refreshPage();
				dashboardPage.clickAccountButton();
				
			 // User Current Plan
				String userCurrentPlan = dashboardPage.getUserCurrentPlanText();
				ExtentManager.getTest().info("User current plan is: " + userCurrentPlan);

			 // Storage Info
			    String storageUsage = dashboardPage.getStorageText();
			    ExtentManager.getTest().info(userCurrentPlan + " User Storage limit is: " + storageUsage);

			 // Total Submitted Responses
			    String responseUsage = dashboardPage.getTotalSubmissionsText();
			    ExtentManager.getTest().info(userCurrentPlan + " User Total Responses limit is: " + responseUsage);

			 // Image Attachments Info
			    String imageUsage = dashboardPage.getImageAttachmentsText();
			    if (imageUsage != null)
			        ExtentManager.getTest().info(userCurrentPlan + " User Images limit is: " + imageUsage);
			    else
			        ExtentManager.getTest().warning("User Images usage info not visible or not present for this plan.");

			 // Custom Themes Info
			    String themeUsage = dashboardPage.getCustomThemesText();
			    if (themeUsage != null)
			        ExtentManager.getTest().info(userCurrentPlan + " User Themes limit is: " + themeUsage);
			    else
			    	ExtentManager.getTest().warning("User Themes usage info not visible or not present for this plan.");
			    
				
				
     }
 
}

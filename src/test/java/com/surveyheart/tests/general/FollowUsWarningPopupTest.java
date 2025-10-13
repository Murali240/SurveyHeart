package com.surveyheart.tests.general;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.surveyheart.base.BaseTest;
import com.surveyheart.pages.FormDashboardPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


@Listeners(com.surveyheart.listeners.TestListener.class)
public class FollowUsWarningPopupTest extends BaseTest {
	
	@Test
    public void verifyFollowUsWarningPopupAppearsAfterFiveRefreshes() {

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
		
				 // 🔄 Refresh page 5 times
			        for (int i = 0; i < 5; i++) {
			            driver.navigate().refresh();
			        }
	
				    String followUsPopupText = dashboardPage.getFollowUsPopupTitle().getText();
				    ExtentManager.getTest().pass("<b>" + followUsPopupText + "</b> popup is displayed successfully after page refreshed 5 times.");
   

				    
	
				    
				    
		
	 }

}

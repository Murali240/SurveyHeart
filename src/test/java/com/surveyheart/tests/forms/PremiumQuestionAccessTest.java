package com.surveyheart.tests.forms;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.surveyheart.base.BaseTest;
import com.surveyheart.pages.FormBuilderPage;
import com.surveyheart.pages.FormDashboardPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Test class to verify that a free user cannot add premium questions while creating a form. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class PremiumQuestionAccessTest extends BaseTest {

    @Test
    public void verifyFreeUserTryingToCreateFormWithPremiumQuestions() {
    	
    	// Login page - Initialize the Login Page object with the current WebDriver instance
    	   SurveyHeartLoginPage loginPage = new SurveyHeartLoginPage(driver);
	    					loginPage.clickSignInUsingEmail();
	    					loginPage.enterEmail("gofaw36836@pacfut.com");
	    					loginPage.clickNext();
	    					loginPage.enterPassword("Automation@1");
	    					loginPage.clickSignIn();
	    					loginPage.closeFeatureSpotlightIfPresent();                       // In case popup appears after login
	    					ExtentManager.getTest().pass("Login successful with email and password.");
	    	
    	
    	 // Form Dashboard - Initialize the Form Dashboard Page object with the current WebDriver instance
    		FormDashboardPage formdashboard = new FormDashboardPage(driver);
    						formdashboard.refreshPage();
    						formdashboard.clickAccountButton();
    						String userPlan = formdashboard.getUserCurrentPlanText();
    						formdashboard.refreshPage();
    						formdashboard.clickCreateFormButton();                       // Click on +Create Form button
    			    	
    	 // Form Dashboard - Initialize the Form Dashboard Page object with the current WebDriver instance				
            FormBuilderPage builder = new FormBuilderPage(driver);
            
				         // Enter Form title in Builder screen
					        builder.enterFormTitle("PremiumQ " + System.currentTimeMillis());
					        
					     // Slider
					        builder.addPremiumQuestion("Slider", "//div[contains(text(),'Slider')]", "//label[normalize-space()='CANCEL']");
					        ExtentManager.getTest().pass("Slider question blocked as expected for '"+userPlan + "' user.");

					     // Picture Choice
					        builder.addPremiumQuestion("Picture Choice", "//div[@class='builder-box1']//div[8]", "//label[normalize-space()='CANCEL']");
					        ExtentManager.getTest().pass("Picture Choice question blocked as expected for '"+userPlan + "' user.");

					     // Ranking
					        builder.addPremiumQuestion("Ranking", "//div[@class='builder-box1']//div[9]", "//label[normalize-space()='CANCEL']");
					        ExtentManager.getTest().pass("Ranking question blocked as expected for '"+userPlan + "' user.");

					     // Agreement
					        builder.addPremiumQuestion("Agreement", "//div[@class='builder-box1']//div[10]", "//label[normalize-space()='CANCEL']");
					        ExtentManager.getTest().pass("Agreement question blocked as expected for '"+userPlan + "' user.");

					     // Signature
					        builder.addPremiumQuestion("Signature", "//div[@class='builder-box1']//div[11]", "//label[normalize-space()='VIEW PLANS']");
					        ExtentManager.getTest().pass("Signature question blocked and View Plans clicked as expected for '"+userPlan + "' user.");
					        ExtentManager.getTest().pass("Navigated to Premium Plans page via View Plans button");
					     
					        
					        // View Plans   -- Temporary solution without closing premium plans page by click method
					       // builder.viewPremiumPlans();
					        //ExtentManager.getTest().pass("Premium Plans popup closed successfully.");
					   
					        

    }
    					    
}


/**
Scenario: Test class to verify that a free user cannot add premium questions while creating a form.

Steps:
1. Login using a free user account.
2. Verify the current plan displayed on the Form Dashboard.
3. Attempt to add premium questions (Slider, Picture Choice, Ranking, Agreement, Signature) in a new form.
4. Verify that each premium question is restricted with the correct warning pop-up or action:
   - Cancel button for Slider, Picture Choice, Ranking, Agreement.
   - "View Plans" button for Signature question.
5. Click "View Plans" for the Signature question and validate navigation to the Premium Plans page.

Expected Result:
- Free users are prevented from adding premium questions.
- Each restricted question displays the appropriate pop-up or navigates to the Premium Plans page correctly.
*/




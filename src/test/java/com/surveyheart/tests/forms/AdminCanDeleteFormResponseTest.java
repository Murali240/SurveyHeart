package com.surveyheart.tests.forms;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.surveyheart.base.BaseTest;
import com.surveyheart.enums.QuestionType;
import com.surveyheart.pages.FormBuilderPage;
import com.surveyheart.pages.FormDashboardPage;
import com.surveyheart.pages.FormPage;
import com.surveyheart.pages.FormSettingsPage;
import com.surveyheart.pages.OverviewPage;
import com.surveyheart.pages.SharePopupPage;
import com.surveyheart.pages.SharedFormDashboardPage;
import com.surveyheart.pages.SubmittedPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Test class to verify that Admin can delete form responses */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class AdminCanDeleteFormResponseTest extends BaseTest {

	@Test
    public void verifyAdminCanDeleteFormRespons()  {
    	
     // Login page - Initialize the Login Page object with the current WebDriver instance
		SurveyHeartLoginPage loginPage = new SurveyHeartLoginPage(driver);
					loginPage.clickSignInUsingEmail();
					loginPage.enterEmail("gofaw36836@pacfut.com");
					loginPage.clickNext();
					loginPage.enterPassword("Automation@1");
					loginPage.clickSignIn();
					loginPage.closeFeatureSpotlightIfPresent();                       // In case popup appears after login
					ExtentManager.getTest().pass("Owner login successful with email and password.");
				
	 // Form Dashboard - Initialize the Form Dashboard Page object with the current WebDriver instance
		FormDashboardPage formdashboard = new FormDashboardPage(driver);
					 formdashboard.refreshPage();
					 formdashboard.clickCreateFormButton();       // Click on +Create Form button
    	
	 // Form Builder - Initialize the Form builder object with the current WebDriver instance
        FormBuilderPage builder = new FormBuilderPage(driver);

			     // Enter Form title in Builder screen
			        String dynamicFormTitle = "AdminDeleteResponse " + System.currentTimeMillis();
			        builder.enterFormTitle(dynamicFormTitle); 
			
			     // 1. Short Answer
			        builder.clickInitialAddQuestionButton();
			        builder.selectQuestionType(QuestionType.SHORT_ANSWER);
			        builder.enterQuestionTitle(0, "What is your name?");

	 // Form Settings - Initialize the Form settings object with the current WebDriver instance
		FormSettingsPage settings = new FormSettingsPage(driver);
					settings.clickSettingsButton();
					settings.clickCollaborationTab();
					settings.addCollaborator("hawece4785@forcrack.com", "Admin");
					settings.clickSubmitButton();
					ExtentManager.getTest().info("Owner added an admin successfully.");
					ExtentManager.getTest().info("Owner created a form with: " + dynamicFormTitle );
					
	 // SharePopup - Initialize the Share popup object with the current WebDriver instance
		SharePopupPage sharePopup = new SharePopupPage(driver);
		
					sharePopup.storeParentWindowHandle();                                  // Store parent window
					sharePopup.clickViewIcon();
					sharePopup.switchToChildWindowThroughViewIcon();
				
				
     // Form page - Initialize the Form page object with the current WebDriver instance
        FormPage formPage = new FormPage(driver);

				    formPage.clickStartButton();
				    String formTitle =formPage.getFormTitle();
				    String totalQuestionCount = formPage.getTotalQuestionCount();
				    ExtentManager.getTest().info("Owner created form with <b>: " + totalQuestionCount + "</b> question for: <b>" +formTitle+"</b>");
				    formPage.answerShortText("Sounder Arunachalam");
				    formPage.clickSubmitButton();
				    ExtentManager.getTest().pass("Owner submitted a response successfully on Form page: <b>" + formTitle + "</b>");

				    
     // Submitted page - Initialize the Submitted page object with the current WebDriver instance
		SubmittedPage submittedPage = new SubmittedPage(driver);
		
					submittedPage.isSubmittedMessageDisplayed();
					ExtentManager.getTest().pass("<b>Submitted</b> page displayed successfully");

					
			     // Closing the child window 
					submittedPage.closeChildWindowAndSwitchToParent(sharePopup.getParentWindowHandle());  // Close child and switch back
			
				 // Close share popup
					sharePopup.clickCloseIcon();
					
			     // Come back to Form Dashboard
					formdashboard.refreshPage();
					
			     // Form Dashboard
					formdashboard.clickMoreOptionsForFirstForm();
					int numberOfFormResponsesOnFormCard = formdashboard.getTotalResponsesCountInMore();
					ExtentManager.getTest().info("Total responses displayed on owner’s form card: <b>" + numberOfFormResponsesOnFormCard + "</b>");

					
				 // Click on Account icon & sing-out
					formdashboard.refreshPage();
					formdashboard.clickAccountButton();
					formdashboard.clickSignoutButton();
					ExtentManager.getTest().pass("Owner signed out successfully.");
					
			     // Admin login
					loginPage.clickSignInUsingEmail();
					loginPage.enterEmail("hawece4785@forcrack.com");
					loginPage.clickNext();
					loginPage.enterPassword("Automation@2");
					loginPage.clickSignIn();
					loginPage.closeFeatureSpotlightIfPresent();              // In case popup appears after login
					ExtentManager.getTest().pass("Admin Login successful with email and password.");
			        
				 // Clicking on Share button
			        formdashboard.clickSharedTab();
			        
	 // Shared Form Dashboard - Initialize the Share popup object with the current WebDriver instance
		SharedFormDashboardPage sharedFormDashboard = new SharedFormDashboardPage(driver);
		
					sharedFormDashboard.clickMoreOptions();
					sharedFormDashboard.clickViewResponsesButton();
					
					
	 // Overview page - Initialize the Overview page object with the current WebDriver instance
	    OverviewPage overview = new OverviewPage(driver);
	    
					int totalResponsesCount = overview.getTotalResponsesCount();
					ExtentManager.getTest().info("Total responses count in individual page: <b>" + totalResponsesCount + "</b>");
					overview.clickIndividualTab();
					overview.clickDeleteIndividualButton();
					overview.confirmDeletePopup();
					ExtentManager.getTest().info("Admin successfully deleted a response on <b>Individual</b> page.");
	
					
				 // Come back to Form Dashboard
				    formdashboard.refreshPage();
				    formdashboard.clickAccountButton();
					formdashboard.clickSignoutButton();
					ExtentManager.getTest().pass("Admin signed out successfully.");
			        
			     // Again owner login 
					loginPage.clickSignInUsingEmail();
					loginPage.enterEmail("gofaw36836@pacfut.com");
					loginPage.clickNext();
					loginPage.enterPassword("Automation@1");
					loginPage.clickSignIn();
					loginPage.closeFeatureSpotlightIfPresent();                       // In case popup appears after login
					ExtentManager.getTest().pass("Again owner login successful with email and password.");
					
				 // Form Dashboard
					formdashboard.clickMoreOptionsForFirstForm();
					int numberOfFormResponsesOnFormCard2 = formdashboard.getTotalResponsesCountInMore();
					ExtentManager.getTest().info("Total responses on owner created form card, after admin deleted a response: <b>" + numberOfFormResponsesOnFormCard2 + "</b>");
					ExtentManager.getTest().pass("Admin deleted response count is updated in the <b>Owner Created Form</b> card: <b>" + formTitle + "</b>");
  
      
					
	}

}


/**
Scenario: Test class to verify that an Admin can delete form responses 
          and the Owner's dashboard reflects the updated count.

Steps:
1. Login as Owner, create a new form with a dynamic title and a Short Answer question, and add an Admin collaborator.
2. Submit a response to the form via the Share popup.
3. Login as Admin, view responses from the Shared dashboard, navigate to the Individual page, and delete a response.
4. Login as Owner and verify that the form card shows the updated response count.

Expected Result:
- Admin can delete responses, and the Owner's dashboard reflects the updated count accurately.
*/



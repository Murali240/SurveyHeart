package com.surveyheart.tests.forms;

import org.testng.Assert;
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
import com.surveyheart.utilities.WaitUtils;


/** Test class to verify that an Editor role cannot delete responses from Individual page. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class EditorCannotDeleteResponseInIndividualPageTest extends BaseTest {
	
	@Test
    public void verifyEditorCannotDeleteResponse()  {
    	
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
			        String dynamicFormTitle = "EditorCan'tDeleteResponse " + System.currentTimeMillis();
			        builder.enterFormTitle(dynamicFormTitle); 
			
			     // 1. Short Answer
			        builder.clickInitialAddQuestionButton();
			        builder.selectQuestionType(QuestionType.SHORT_ANSWER);
			        builder.enterQuestionTitle(0, "What is your name?");

	 // Form Settings - Initialize the Form settings object with the current WebDriver instance
		FormSettingsPage settings = new FormSettingsPage(driver);
					settings.clickSettingsButton();
					settings.clickCollaborationTab();
					settings.addCollaborator("meyap64096@forcrack.com", "Editor");
					settings.clickSubmitButton();
					ExtentManager.getTest().info("Owner added an editor successfully.");
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
					formdashboard.ifFollowUsPopupDisplayed();
					formdashboard.clickAccountButton();
					formdashboard.clickSignoutButton();
					ExtentManager.getTest().pass("Owner signed out successfully.");
					
			     // Admin login
					loginPage.clickSignInUsingEmail();
					loginPage.enterEmail("meyap64096@forcrack.com");
					loginPage.clickNext();
					loginPage.enterPassword("Automation@3");
					loginPage.clickSignIn();
					loginPage.closeFeatureSpotlightIfPresent();              // In case popup appears after login
					ExtentManager.getTest().pass("Editor Login successful with email and password.");
			        
				 // Clicking on Share button
			        formdashboard.clickSharedTab();
			        
	 // Shared Form Dashboard - Initialize the Share popup object with the current WebDriver instance
		SharedFormDashboardPage sharedFormDashboard = new SharedFormDashboardPage(driver);
		
					sharedFormDashboard.clickMoreOptions();
					sharedFormDashboard.clickViewResponsesButton();
					
					
	 // Overview page - Initialize the Overview page object with the current WebDriver instance
	    OverviewPage overview = new OverviewPage(driver);
	               
					int totalResponsesCount = overview.getTotalResponsesCount();
					ExtentManager.getTest().info("Total responses count in Overview page: <b>" + totalResponsesCount + "</b>");
					overview.clickIndividualTab();	
					WaitUtils.waitForSeconds(driver, 3);			
					
			     // Assert for won't display Delete button for Editor role
					Assert.assertFalse(overview.isDeleteButtonDisplayedInIndividual(),
					        "Delete button should not be visible for Editor role");
					ExtentManager.getTest().pass("Verified that the <b>Delete</b> button is <b>not visible</b> for the <b>Editor</b> role on the Individual page, as expected.");

				
							        
	}

}


/**
Scenario: Test class to verify that an Editor role cannot delete responses from the Individual page.

Steps:
1. Login as Owner, create a new form with a dynamic title and a Short Answer question, and add an Editor collaborator.
2. Submit a response via the Share popup and verify the 'Submitted' page.
3. Login as Editor, open the form's responses in Overview page, navigate to the Individual page, and verify the Delete button is not visible.

Expected Result:
- Editor cannot delete responses, and the Delete button is not visible on the Individual page.
*/




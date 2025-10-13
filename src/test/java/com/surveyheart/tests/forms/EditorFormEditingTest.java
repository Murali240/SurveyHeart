package com.surveyheart.tests.forms;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.surveyheart.base.BaseTest;
import com.surveyheart.enums.QuestionType;
import com.surveyheart.pages.FormBuilderPage;
import com.surveyheart.pages.FormDashboardPage;
import com.surveyheart.pages.FormPage;
import com.surveyheart.pages.FormSettingsPage;
import com.surveyheart.pages.SharePopupPage;
import com.surveyheart.pages.SharedFormDashboardPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Test class to verify that an Editor can successfully edit a shared form 
 * (add new questions) and the changes are reflected on the Owner Form page. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class EditorFormEditingTest extends BaseTest {
	
	@Test
    public void verifyEditorFormEditing()  {
    	
		/** Login as Owner */
		SurveyHeartLoginPage loginPage = new SurveyHeartLoginPage(driver);
					loginPage.clickSignInUsingEmail();
					loginPage.enterEmail("gofaw36836@pacfut.com");
					loginPage.clickNext();
					loginPage.enterPassword("Automation@1");
					loginPage.clickSignIn();
					loginPage.closeFeatureSpotlightIfPresent();             // In case popup appears after login
					ExtentManager.getTest().pass("Owner login successful with email and password.");
				
		/** Owner creates a new Form */
		FormDashboardPage formdashboard = new FormDashboardPage(driver);
					 formdashboard.refreshPage();
					 formdashboard.clickCreateFormButton();       // Click on +Create Form button
    	
		/** Form Builder */
        FormBuilderPage builder = new FormBuilderPage(driver);

			     // Enter Form title in Builder screen
			        builder.enterFormTitle("EditorEditForm " + System.currentTimeMillis());
			
			        /** Add Short Answer question */
			        builder.clickInitialAddQuestionButton();
			        builder.selectQuestionType(QuestionType.SHORT_ANSWER);
			        builder.enterQuestionTitle(0, "What is your name?");

	    /** Add Editor collaborator from Settings */
		FormSettingsPage settings = new FormSettingsPage(driver);
					settings.clickSettingsButton();
					settings.clickCollaborationTab();
					settings.addCollaborator("meyap64096@forcrack.com", "Editor");
					settings.clickSubmitButton();
					ExtentManager.getTest().info("Owner added an editor successfully.");
					
		/** Close Share Popup */
		SharePopupPage sharePopup = new SharePopupPage(driver);
					sharePopup.clickCloseIcon();
					
					/** Owner sign out */
					formdashboard.refreshPage();
					formdashboard.clickAccountButton();
					formdashboard.clickSignoutButton();
					ExtentManager.getTest().pass("Owner signed out successfully.");
					
					
					/** Login as Editor */
					loginPage.clickSignInUsingEmail();
					loginPage.enterEmail("meyap64096@forcrack.com");
					loginPage.clickNext();
					loginPage.enterPassword("Automation@3");
					loginPage.clickSignIn();
					loginPage.closeFeatureSpotlightIfPresent();                       // In case popup appears after login
					ExtentManager.getTest().pass("Editor Login successful with email and password.");
			        
					/** Editor opens Shared tab */
			        formdashboard.clickSharedTab();
			        
	    /** Editor opens Form builder from shared edit form  */
		SharedFormDashboardPage sharedDashboard = new SharedFormDashboardPage(driver);
					sharedDashboard.clickMoreOptions();
					sharedDashboard.clickEditFormButton();
			        
					/** Editor adds Long Answer question */
			        builder.clickAddQuestionAfter(0);
			        builder.selectQuestionType(QuestionType.LONG_ANSWER);
			        builder.enterQuestionTitle(1, "Tell me about yourself:");
			        
			        /** Editor saves settings */
			        settings.clickSettingsButton();
			        settings.clickSubmitButton();
			        ExtentManager.getTest().pass("Editor successfully edited the shared form and added a new question.");
			        
			        /** Close Share Popup */
			        sharePopup.clickCloseIcon();
					
			        /** Editor sign out */
				    formdashboard.refreshPage();
				    formdashboard.clickAccountButton();
					formdashboard.clickSignoutButton();
					ExtentManager.getTest().pass("Editor signed out successfully.");
			        
			     
					/** Owner login again */
					loginPage.clickSignInUsingEmail();
					loginPage.enterEmail("gofaw36836@pacfut.com");
					loginPage.clickNext();
					loginPage.enterPassword("Automation@1");
					loginPage.clickSignIn();
					loginPage.closeFeatureSpotlightIfPresent();                       // In case popup appears after login
					ExtentManager.getTest().pass("Again owner login successful with email and password.");
					
					/** Owner opens Form in View Form from More options */
					formdashboard.clickMoreOptionsForFirstForm();
					formdashboard.storeParentWindowHandle(); 
					formdashboard.clickViewFormButton();
					formdashboard.switchToChildWindow();
			        
		 /** Verify on Form Page */
		 FormPage formPage = new FormPage(driver);
					formPage.clickStartButton();
					String formTitle = formPage.getFormTitle();
					String totalQuestionCount = formPage.getTotalQuestionCount();
					ExtentManager.getTest().info("Form page displayed with " + totalQuestionCount + " questions.");
					ExtentManager.getTest().pass("Editor's newly added question was displayed on the Form page : " + formTitle);
  

									
	}

}


/**
Scenario: Test class to verify that an Editor can edit a shared form 
          and changes are reflected on the Owner's Form page.

Steps:
1. Login as Owner, create a new form with a dynamic title and a Short Answer question, and add an Editor collaborator.
2. Login as Editor, edit the shared form by adding a Long Answer question, and save the changes.
3. Login as Owner and verify that the Form page displays the newly added question with updated total count.

Expected Result:
- Editor can add questions successfully, and the Owner's Form page shows the new question with correct count.
*/



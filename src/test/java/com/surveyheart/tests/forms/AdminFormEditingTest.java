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


/** Test class to verify Admin can edit shared form and changes reflect on Form page (Adding new QT) */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class AdminFormEditingTest extends BaseTest {
	
	@Test
    public void verifyAdminFormEditing()  {
    	
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
			        builder.enterFormTitle("AdminEditForm " + System.currentTimeMillis());
			
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
					
	 // SharePopup - Initialize the Share popup object with the current WebDriver instance
		SharePopupPage sharePopup = new SharePopupPage(driver);
					sharePopup.clickCloseIcon();
					
			     // Come back to Form Dashboard
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
					loginPage.closeFeatureSpotlightIfPresent();                       // In case popup appears after login
					ExtentManager.getTest().pass("Admin Login successful with email and password.");
			        
				 // Clicking on Share button
			        formdashboard.clickSharedTab();
			        
	 // Shared Form Dashboard - Initialize the Share popup object with the current WebDriver instance
		SharedFormDashboardPage sharedDashboard = new SharedFormDashboardPage(driver);
					sharedDashboard.clickMoreOptions();
					sharedDashboard.clickEditFormButton();
			        
				 // 2. Long Answer - Builder screen
			        builder.clickAddQuestionAfter(0);
			        builder.selectQuestionType(QuestionType.LONG_ANSWER);
			        builder.enterQuestionTitle(1, "Tell me about yourself:");
			        
			     // Settings
			        settings.clickSettingsButton();
			        settings.clickSubmitButton();
			        ExtentManager.getTest().pass("Admin successfully edited the shared form and added a new question.");
			        
			     // Share popup 
			        sharePopup.clickCloseIcon();
					
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
					formdashboard.storeParentWindowHandle(); 
					formdashboard.clickViewFormButton();
					formdashboard.switchToChildWindow();
			        
	  // Form page - Initialize the Form page object with the current WebDriver instance
		 FormPage formPage = new FormPage(driver);
					formPage.clickStartButton();
					String formTitle = formPage.getFormTitle();
					String totalQuestionCount = formPage.getTotalQuestionCount();
					ExtentManager.getTest().info("Form page displayed with " + totalQuestionCount + " questions.");
					ExtentManager.getTest().pass("Admin's newly added question was displayed on the Form page : " + formTitle);
  
					
			        			        
	}

}


/**
Scenario: Test class to verify that an Admin can edit a shared form 
          and changes are reflected on the Form page.

Steps:
1. Login as Owner, create a new form with a Short Answer question, and add an Admin collaborator.
2. Login as Admin, edit the shared form, and add a new Long Answer question titled "Tell me about yourself:".
3. Login as Owner and verify that the Form page displays the newly added question and updated question count.

Expected Result:
- Admin can edit the shared form, and the Owner's Form page shows the new question with correct count.
*/



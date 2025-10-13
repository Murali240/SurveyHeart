package com.surveyheart.tests.forms;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.surveyheart.base.BaseTest;
import com.surveyheart.pages.FormDashboardPage;
import com.surveyheart.pages.FormPage;
import com.surveyheart.pages.FormSettingsPage;
import com.surveyheart.pages.FormsTemplatePage;
import com.surveyheart.pages.SharePopupPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Test class to verify the creation of a new template form from 'Form Templates' folder. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class FormTemplateCreationTest extends BaseTest {
	
	@Test (groups = "regression", priority = 2)
    public void verifyFormTemplateCreation() {
    	
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
					 formdashboard.clickTemplatesTab();
	    	
	 // Forms Template - Initialize the Form builder object with the current WebDriver instance
        FormsTemplatePage formTemplate = new FormsTemplatePage(driver);
        		 	 formTemplate.clickFirstFormTemplateCard();
	

     // Form Settings - Initialize the Form settings object with the current WebDriver instance
       	FormSettingsPage settings = new FormSettingsPage(driver);
	       			 settings.clickSettingsButton();
	       			 settings.clickControlTab();
	       			 settings.enableAllowMultipleResponses(true); 
	       			 ExtentManager.getTest().pass("'Allow Multiple Response' checkbox enabled successfully.");
	       			 settings.clickSubmitButton();
       		        
       			    
     // SharePopup - Initialize the Share popup object with the current WebDriver instance
       	SharePopupPage sharePopup = new SharePopupPage(driver);
       				   sharePopup.clickViewIcon();
       				   sharePopup.getParentWindowHandle();
       				   
					// ✅ Store parent window before switching
					   sharePopup.storeParentWindowHandle();
					   sharePopup.switchToChildWindowThroughViewIcon();
       					
     // Form page - Initialize the Form page object with the current WebDriver instance
        FormPage formPage = new FormPage(driver);
       		   		 formPage.clickStartSurveyTemplateButton();
  
       				 String formTitle = formPage.getFormTitle();
       				 String totalQuestionCount = formPage.getTotalQuestionCount();
       				 ExtentManager.getTest().info("Selected form template is displayed with " + totalQuestionCount + " questions.");
       				 ExtentManager.getTest().pass("Form template was created successfully with the title: " + formTitle);
        		 
     				  
        		 
	}	

}


/**
Scenario: Test class to verify creating a new form from a template in SurveyHeart.

Steps:
1. Login using valid credentials.
2. Navigate to the Form Dashboard and switch to the Templates tab.
3. Select the first available form template.
4. Enable 'Allow Multiple Responses' in the Form Settings.
5. Open the selected template via the Share popup.
6. Verify that all questions are displayed correctly on the Form page.

Expected Result:
- The form is created successfully from the template.
- All template questions appear correctly on the Form page.
*/


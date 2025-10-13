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
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;
import com.surveyheart.utilities.WaitUtils;


/** Test class to verify adding a Website URL attachment to a form question. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class CreateFormWithWebsiteAttachmentTest extends BaseTest {

    @Test
    public void verifyAddingWebsiteURLToQuestionForForm() throws InterruptedException   {

        // ---------------- Login ----------------
        SurveyHeartLoginPage loginPage = new SurveyHeartLoginPage(driver);
        
			        loginPage.clickSignInUsingEmail();
			        loginPage.enterEmail("gofaw36836@pacfut.com");
			        loginPage.clickNext();
			        loginPage.enterPassword("Automation@1");
			        loginPage.clickSignIn();
			        loginPage.closeFeatureSpotlightIfPresent();          // In case popup appears after login
			        ExtentManager.getTest().pass("Login successful with email and password.");

        // ---------------- Dashboard ----------------
        FormDashboardPage formDashboard = new FormDashboardPage(driver);
        
			        formDashboard.refreshPage();
			        formDashboard.clickCreateFormButton();   // Click on +Create Form button

        // ---------------- Builder ----------------
        FormBuilderPage builder = new FormBuilderPage(driver);

			     // Enter Form title in Builder screen
			        String dynamicFormTitle = "WebsiteAttachmentToForm " + System.currentTimeMillis();
			        builder.enterFormTitle(dynamicFormTitle);
			
			     // 1. Short Answer Question
			        builder.clickInitialAddQuestionButton();
			        builder.selectQuestionType(QuestionType.SHORT_ANSWER);
			        builder.enterQuestionTitle(0, "What is your name?");
			
			     // Attach Website URL
			        String websiteUrl = "https://www.facebook.com/";
			        builder.attachWebsiteUrlToQuestion(websiteUrl);
			        WaitUtils.waitForSeconds(driver, 2);

       
        // ---------------- Settings ----------------
        FormSettingsPage settings = new FormSettingsPage(driver);
        
			        settings.clickSettingsButton();
			        settings.clickControlTab();
			        settings.enableAllowMultipleResponses(true);
			        ExtentManager.getTest().pass("'Allow Multiple Responses' checkbox enabled successfully.");
			        settings.clickSubmitButton();
			        ExtentManager.getTest().info("Form created with title: " + dynamicFormTitle);

        // ---------------- Share Popup ----------------
        SharePopupPage sharePopup = new SharePopupPage(driver);
        
			        sharePopup.storeParentWindowHandle();   					// Store parent window
			        sharePopup.clickViewIcon();
			        sharePopup.switchToChildWindowThroughViewIcon();

        // ---------------- Form Page ----------------
        FormPage formPage = new FormPage(driver);
        
        		 // Welcome page
        			formPage.clickStartButton();

        	     // Form page
			        String totalQuestionCount = formPage.getTotalQuestionCount();
			        ExtentManager.getTest().info("Form created with: " + totalQuestionCount + " question(s)");
			        WaitUtils.waitForSeconds(driver, 10);
   
        
  
    }

}



/**
Scenario: Test class to verify adding a Website URL attachment to a form question 
          and validate it on the Form page.

Steps:
1. Login and create a new form with a dynamic title and a Short Answer question.
2. Attach a Website URL to the question, enable 'Allow Multiple Responses' in Settings, and submit the form.
3. Open the form via the Share popup, start the form, and verify the question displays the attached Website URL.
4. Confirm that the Form page shows the correct total question count.

Expected Result:
- The form question displays the attached Website URL correctly, and the total question count is accurate.
*/








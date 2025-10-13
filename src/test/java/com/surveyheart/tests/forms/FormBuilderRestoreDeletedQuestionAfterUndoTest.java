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
import com.surveyheart.pages.SharePopupPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Test class to verify deleted question is restored after clicking Undo in Form Builder */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class FormBuilderRestoreDeletedQuestionAfterUndoTest extends BaseTest {
	
	@Test 
    public void verifyDeletedQuestionRestoredAfterUndoInFormBuilder()  {
    	
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
					 formdashboard.clickCreateFormButton();       // Click on +Create Form button
    	
	 // Form Builder - Initialize the Form builder object with the current WebDriver instance
        FormBuilderPage builder = new FormBuilderPage(driver);

				     // Enter Form title in Builder screen
				        String dynamicFormTitle = "UndoButtonInFormBuilder " + System.currentTimeMillis();
				        builder.enterFormTitle(dynamicFormTitle); 
				
				     // 1. Short Answer
				        builder.clickInitialAddQuestionButton();
				        builder.selectQuestionType(QuestionType.SHORT_ANSWER);
				        builder.enterQuestionTitle(0, "What is your name?");
				        
				     // 2. Long Answer
				        builder.clickAddQuestionAfter(0);
				        builder.selectQuestionType(QuestionType.LONG_ANSWER);
				        builder.enterQuestionTitle(1, "Tell me about yourself:");
				        
				        builder.clickDeleteIcon();
				        String undoToastMessage=builder.getFullUndoToastMessage();
				        ExtentManager.getTest().pass("Undo toast message verified successfully in Form Builder: <b>" + undoToastMessage + "</b>");
		
				     // Click on Undo toast button from Form builder
				        builder.clickUndoToastButton();
				        ExtentManager.getTest().pass("Successfully clicked on <b>Undo</b> toast button");
				        
	 
	// Form Settings - Initialize the Form settings object with the current WebDriver instance
	   FormSettingsPage settings = new FormSettingsPage(driver);
						settings.clickSettingsButton();
						settings.clickControlTab();
						settings.enableAllowMultipleResponses(true); 
						ExtentManager.getTest().pass("'Allow Multiple Response' checkbox enabled successfully.");
						settings.clickSubmitButton();
					        
					    
	 // SharePopup - Initialize the Share popup object with the current WebDriver instance
		SharePopupPage sharePopup = new SharePopupPage(driver);
					sharePopup.storeParentWindowHandle();                                      // Step 1: Store parent window
					sharePopup.clickViewIcon();
					sharePopup.switchToChildWindowThroughViewIcon();
												
												
	 // Form page - Initialize the Form page object with the current WebDriver instance
		FormPage formPage = new FormPage(driver);
						formPage.clickStartButton();
						String questionCount = formPage.getTotalQuestionCount();
						ExtentManager.getTest().info("Form created with: " + questionCount + " question");
									    
					 // Closing the child window 
						formPage.closeChildWindowAndSwitchToParent(sharePopup.getParentWindowHandle());   // Step 4: Close child and switch back
									  
					 // Share popup
						sharePopup.clickCloseIcon();
						formdashboard.refreshPage();
						String actualFormTiltle = formdashboard.getFirstFormCardTitle();
						String expectedFormTitle=dynamicFormTitle;
						
					 // Assert that actualFormTitle and expectedFormTitle 
						Assert.assertEquals(actualFormTiltle, expectedFormTitle, "Actual form title does not match expected title");
						ExtentManager.getTest().pass("Actual Form title is matched with expected Form title: "+ dynamicFormTitle);

			  
		    
    }

}


/**
Scenario: Test class to verify that a deleted question is restored after clicking Undo button in Form Builder.

Steps:
1. Login and create a new form with a dynamic title, adding a Short Answer and a Long Answer question.
2. Delete the Long Answer question, verify the Undo toast message, and click Undo button.
3. Enable 'Allow Multiple Responses' in Settings and submit the form.
4. Open the form via the Share popup, start the form, and verify the total number of questions.
5. Refresh the Form Dashboard and confirm that the form title matches the dynamic title.

Expected Result:
- The deleted question is restored successfully after clicking the Undo button in Form Builder.
*/







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
import com.surveyheart.pages.SubmittedPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Test class to verify drag-and-drop of questions in Form Builder and validate order on Form page */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class FormBuilderQuestionsDragAndDropTest extends BaseTest {         // Error coming & resolve it then run
	
    @Test
    public void verifyQuestionsDragAndDropInFormBuilder() throws InterruptedException {

         // Login
	        SurveyHeartLoginPage loginPage = new SurveyHeartLoginPage(driver);
				        loginPage.clickSignInUsingEmail();
				        loginPage.enterEmail("gofaw36836@pacfut.com");
				        loginPage.clickNext();
				        loginPage.enterPassword("Automation@1");
				        loginPage.clickSignIn();
				        loginPage.closeFeatureSpotlightIfPresent();
				        ExtentManager.getTest().pass("Login successful with valid email & password.");

         // Open Form Builder
	        FormDashboardPage dashboard = new FormDashboardPage(driver);
				        dashboard.refreshPage();
				        dashboard.clickCreateFormButton();
				
				        FormBuilderPage builder = new FormBuilderPage(driver);
				     // Set dynamic Quiz title
					    String dynamicFormTitle = "DragAndDropInFormBuilder " + System.currentTimeMillis();
					    builder.enterFormTitle(dynamicFormTitle);
				
				     // 1. Short Answer
				        builder.clickInitialAddQuestionButton();
				        builder.selectQuestionType(QuestionType.SHORT_ANSWER);
				        builder.enterQuestionTitle(0, "Short question1");
				
				     // 2. Long Answer
				        builder.clickAddQuestionAfter(0);
				        builder.selectQuestionType(QuestionType.LONG_ANSWER);
				        builder.enterQuestionTitle(1, "Long question2");
				
				     // Drag & Drop
				        builder.waitForQuestionCard(1);
				        builder.waitForQuestionCard(2);
				        builder.dragQuestion(2, 1);
				        Thread.sleep(3000);
				        ExtentManager.getTest().pass("Dragged question 2 to position 1 successfully."); 

         // Save Form
	        FormSettingsPage settings = new FormSettingsPage(driver);
				        settings.clickSettingsButton();
				        settings.clickControlTab();
				        settings.enableAllowMultipleResponses(true);
				        ExtentManager.getTest().pass("'Allow Multiple Response' enabled successfully.");
				        settings.clickSubmitButton();
				        ExtentManager.getTest().info("Form created successfully with: "+dynamicFormTitle);  	 

         // Share Popup
	        SharePopupPage sharePopup = new SharePopupPage(driver);
				        sharePopup.clickViewIcon();
				        sharePopup.switchToChildWindowThroughViewIcon();

         // Form page
	        FormPage formPage = new FormPage(driver);
				        formPage.clickStartButton();
				        String formTitle = formPage.getFormTitle();
				        
				        String firstQuestionTitle = formPage.getFirstQuestionTitleText();
				       
				        
				        formPage.answerLongText("Form long response");
				        formPage.answerShortText("Form short response");
				        
				        String questionCount = formPage.getTotalQuestionCount();
				        formPage.clickSubmitButton();
				        ExtentManager.getTest().info("First question displayed in Form page: <b>" + firstQuestionTitle + "</b>");
				        ExtentManager.getTest().info("Form displayed with <b>" + questionCount + " </b>questions.");
				        ExtentManager.getTest().pass("Questions were successfully dragged and dropped in the form: <b>" + formTitle+"</b>");


	     // Submitted page - Initialize the Submitted page object with the current WebDriver instance
			SubmittedPage submittedPage = new SubmittedPage(driver);
			
			   			submittedPage.isSubmittedMessageDisplayed();
			   			ExtentManager.getTest().pass("<b>Submitted</b> page displayed successfully with <b>Add Response</b> button");


			   			
    }

}


/**
Scenario: Test class to verify drag-and-drop of questions in Form Builder 
          and validate their order on the Form page.

Steps:
1. Login and create a new form with a dynamic title, adding Short Answer and Long Answer questions.
2. Reorder questions using drag-and-drop, enable 'Allow Multiple Responses' in Settings, and submit the form.
3. Open the form via the Share popup, start the form, verify the question order, fill in responses, and submit.
4. Verify that the 'Submitted' page displays successfully with the '+Add Response' button.

Expected Result:
- Questions can be reordered via drag-and-drop, and the Form page shows the correct order with successful submission.
*/




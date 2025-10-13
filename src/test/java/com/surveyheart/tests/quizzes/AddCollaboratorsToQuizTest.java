package com.surveyheart.tests.quizzes;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.surveyheart.base.BaseTest;
import com.surveyheart.enums.QuizQuestionType;
import com.surveyheart.pages.FormDashboardPage;
import com.surveyheart.pages.QuizBuilderPage;
import com.surveyheart.pages.QuizDashboardPage;
import com.surveyheart.pages.QuizSettingsPage;
import com.surveyheart.pages.SharePopupPage;
import com.surveyheart.pages.SharedQuizDashboardPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Test class to verify adding collaborators with different roles to a quiz (Admin, Editor, Viewer) */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class AddCollaboratorsToQuizTest extends BaseTest {
	
	@Test 
    public void verifyAddCollaboratorsWithDifferentRolesToQuiz() {
    	
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
		FormDashboardPage formDashboardPage = new FormDashboardPage(driver);
					formDashboardPage.refreshPage();
					formDashboardPage.clickQuizzesTab();
					
	 // Quiz Dashboard - Initialize the Quiz Dashboard Page object with the current WebDriver instance
		QuizDashboardPage quizDashboardPage = new QuizDashboardPage(driver);
				    quizDashboardPage.clickCreateQuizButton();                   // Click on +Create Quiz button

	    	
	 // Quiz Builder - Initialize the Quiz builder object with the current WebDriver instance
        QuizBuilderPage quizBuilderPage = new QuizBuilderPage(driver);

			     // Enter Quiz title in Builder screen
			        String dynamicQuizTitle = "AddCollaboratorsQuiz " + System.currentTimeMillis();
			        quizBuilderPage.enterQuizTitle(dynamicQuizTitle); 
			
			        // ==== Add a Short Answer question ====
				    quizBuilderPage.addQuestion(
				        QuizQuestionType.SHORT_ANSWER,
				        "What is your name?",
				        "Madhu",       // Answer
				        null,          // No options
				        -1             // No correct option
				    );
				    
				    
	 // Quiz Settings - Initialize the Quiz settings object with the current WebDriver instance
	   	QuizSettingsPage quizSettingsPage = new QuizSettingsPage(driver);
	   	
				   	quizSettingsPage.clickSettingsButton();
				   	quizSettingsPage.clickCollaborationTab();
					
				 // Prepare dynamic collaborators
			        Map<String, String> collaborators = new HashMap<>();
			        collaborators.put("hawece4785@forcrack.com", "Admin");
			        collaborators.put("meyap64096@forcrack.com", "Editor");
			        collaborators.put("nedapi9780@forcrack.com", "Viewer");

			     // Call method (order can be anything)
			        quizSettingsPage.addCollaborators(collaborators);
			        quizSettingsPage.clickSubmitButton();
			        ExtentManager.getTest().info("Owner created quiz successfully with: "+dynamicQuizTitle);  
				        
					    
	// SharePopup - Initialize the Share popup object with the current WebDriver instance
	   SharePopupPage sharePopupPage = new SharePopupPage(driver);
				    sharePopupPage.clickCloseIcon();
				    quizDashboardPage.refreshPage();
				    formDashboardPage.clickAccountButton();
				    formDashboardPage.clickSignoutButton();
		   			
		   			
		   			
				    /** Signin with Admin role */
		   			loginPage.clickSignInUsingEmail();
					loginPage.enterEmail("hawece4785@forcrack.com");
					loginPage.clickNext();
					loginPage.enterPassword("Automation@2");
					loginPage.clickSignIn();
					loginPage.closeFeatureSpotlightIfPresent();                       // In case popup appears after login
					ExtentManager.getTest().pass("Admin login successfully with email and password.");
							
				 // Shared Dashboard
					formDashboardPage.clickSharedTab();
					
	  // Shared Quiz Dashboard - Initialize the Shared quiz dashboard object with the current WebDriver instance
		 SharedQuizDashboardPage sharedQuizDashboardPage = new SharedQuizDashboardPage(driver);	
		 
		             sharedQuizDashboardPage.clickSharedQuizzesTab();
					 String actualSharedQuizTiltle = sharedQuizDashboardPage.getSharedQuizCard().getText();
					 String expectedSharedQuizTitle=dynamicQuizTitle;		
	
				  // Assert that actualFormTitle and expectedFormTitle 
				     Assert.assertEquals(actualSharedQuizTiltle, expectedSharedQuizTitle, "Actual shared quiz title does not match expected title");
				     ExtentManager.getTest().pass("Actual shared quiz title is matched with expected quiz title: <b>"+ dynamicQuizTitle+"</b>");

				  // Signout
				     formDashboardPage.clickAccountButton();
				     formDashboardPage.clickSignoutButton();
				     
			   		 
				     
			   		 /** Signin with Editor role */
			   		  loginPage.clickSignInUsingEmail();
					  loginPage.enterEmail("meyap64096@forcrack.com");
					  loginPage.clickNext();
					  loginPage.enterPassword("Automation@3");
					  loginPage.clickSignIn();
					  loginPage.closeFeatureSpotlightIfPresent();                       // In case popup appears after login
					  ExtentManager.getTest().pass("Editor login successfully with email and password.");
								
				   // Shared Dashboard
					  formDashboardPage.clickSharedTab();
						
	 // Shared Quiz Dashboard - Initialize the Shared quiz dashboard object with the current WebDriver instance
		SharedQuizDashboardPage sharedQuizDashboardPage2 = new SharedQuizDashboardPage(driver);	
		      
		              sharedQuizDashboardPage2.clickSharedQuizzesTab();
					  String actualSharedQuizTiltle2 = sharedQuizDashboardPage2.getSharedQuizCard().getText();
					  String expectedSharedQuizTitle2=dynamicQuizTitle;		
		
				   // Assert that actualFormTitle and expectedFormTitle 
					  Assert.assertEquals(actualSharedQuizTiltle2, expectedSharedQuizTitle2, "Actual shared quiz title does not match expected title");
					  ExtentManager.getTest().pass("Actual shared quiz title is matched with expected quiz title: <b>"+ dynamicQuizTitle+"</b>");

				   // Signout
					  formDashboardPage.clickAccountButton();
					  formDashboardPage.clickSignoutButton();
				   		 
					
				   		 
				   	  /** Signin with Viewer role */
				   	  loginPage.clickSignInUsingEmail();
					  loginPage.enterEmail("nedapi9780@forcrack.com");
					  loginPage.clickNext();
					  loginPage.enterPassword("Automation@4");
					  loginPage.clickSignIn();
					  loginPage.closeFeatureSpotlightIfPresent();            // In case popup appears after login
					  ExtentManager.getTest().pass("Viewer login successfully with email and password.");
									
				   // Shared Dashboard
					  formDashboardPage.clickSharedTab();
							
	  // Shared Form Dashboard - Initialize the Shared form dashboard object with the current WebDriver instance
		 SharedQuizDashboardPage sharedQuizDashboardPage3 = new SharedQuizDashboardPage(driver);
		 
		 			  sharedQuizDashboardPage3.clickSharedQuizzesTab();
					  String actualSharedQuizTiltle3 = sharedQuizDashboardPage3.getSharedQuizCard().getText();
					  String expectedSharedQuizTitle3=dynamicQuizTitle;		
			
				   // Assert that actualFormTitle and expectedFormTitle 
					  Assert.assertEquals(actualSharedQuizTiltle3, expectedSharedQuizTitle3, "Actual shared quiz title does not match expected title");
					  ExtentManager.getTest().pass("Actual shared quiz title is matched with expected quiz title: <b>"+ dynamicQuizTitle+"</b>");


					  
	}

}


/**
Scenario: Test class to verify adding collaborators with different roles (Admin, Editor, Viewer) 
          and validate their access to the shared quiz.

Steps:
1. Login as the Owner, create a new quiz with a dynamic title and a Short Answer question.
2. Add collaborators with roles: Admin, Editor, and Viewer via the Collaboration tab, then close the Share popup.
3. Sign in as each collaborator and verify that the shared quiz is visible in their dashboard.

Expected Result:
- All collaborators (Admin, Editor, Viewer) can access the shared quiz in their dashboards.
*/

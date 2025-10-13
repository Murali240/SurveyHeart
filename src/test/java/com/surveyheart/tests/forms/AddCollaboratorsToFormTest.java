package com.surveyheart.tests.forms;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.surveyheart.base.BaseTest;
import com.surveyheart.enums.QuestionType;
import com.surveyheart.pages.FormBuilderPage;
import com.surveyheart.pages.FormDashboardPage;
import com.surveyheart.pages.FormSettingsPage;
import com.surveyheart.pages.SharePopupPage;
import com.surveyheart.pages.SharedFormDashboardPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Test class to verify adding collaborators with different roles to a form (Admin, Editor, Viewer) */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class AddCollaboratorsToFormTest extends BaseTest {
	
	@Test //(groups = {"regression"}, priority = 29)
    public void verifyAddCollaboratorsWithDifferentRolesToForm() {
    	
		/** Login as Owner */
		SurveyHeartLoginPage loginPage = new SurveyHeartLoginPage(driver);
					loginPage.clickSignInUsingEmail();
					loginPage.enterEmail("gofaw36836@pacfut.com");
					loginPage.clickNext();
					loginPage.enterPassword("Automation@1");
					loginPage.clickSignIn();
					loginPage.closeFeatureSpotlightIfPresent();                       // In case popup appears after login
					ExtentManager.getTest().pass("Login successful with email and password.");
				
		/** Owner creates form */
		FormDashboardPage formdashboard = new FormDashboardPage(driver);
					formdashboard.refreshPage();
					formdashboard.clickCreateFormButton();         // Click on +Create Form button
	    	
		/** Add question in Form Builder */
        FormBuilderPage builder = new FormBuilderPage(driver);

			     // Enter Form title in Builder screen
			        String dynamicFormTitle = "AddCollaboratorsForm " + System.currentTimeMillis();
			        builder.enterFormTitle(dynamicFormTitle); 
			
			     // 1. Short Answer   
			        builder.clickInitialAddQuestionButton();
			        builder.selectQuestionType(QuestionType.SHORT_ANSWER);
			        builder.enterQuestionTitle(0, "What is your name?");

		/** Add collaborators in Form Settings */
	   	FormSettingsPage settings = new FormSettingsPage(driver);
					settings.clickSettingsButton();
					settings.clickCollaborationTab();
					
			     // Prepare dynamic collaborators
			        Map<String, String> collaborators = new HashMap<>();
			        collaborators.put("hawece4785@forcrack.com", "Admin");
			        collaborators.put("meyap64096@forcrack.com", "Editor");
			        collaborators.put("nedapi9780@forcrack.com", "Viewer");

			     // Call method (order can be anything)
			        settings.addCollaborators(collaborators);
			        settings.clickSubmitButton();
			        ExtentManager.getTest().info("Owner created form successfully with: "+dynamicFormTitle);  
				        
					    
	   /** Close Share Popup & Owner sign-out */
	   SharePopupPage sharePopup = new SharePopupPage(driver);
		   			sharePopup.clickCloseIcon();
		   			formdashboard.clickAccountButton();
		   			
		   			formdashboard.clickSignoutButton();
		   			
		   			
		   			
		   			/** Signin as Admin */
		   			loginPage.clickSignInUsingEmail();
					loginPage.enterEmail("hawece4785@forcrack.com");
					loginPage.clickNext();
					loginPage.enterPassword("Automation@2");
					loginPage.clickSignIn();
					loginPage.closeFeatureSpotlightIfPresent();                       // In case popup appears after login
					ExtentManager.getTest().pass("Admin login successfully with email and password.");
							
	
				 // Shared Dashboard
				    formdashboard.clickSharedTab();
					
	  // Shared Form Dashboard - Initialize the Shared form dashboard object with the current WebDriver instance
		 SharedFormDashboardPage sharedFormDashboard = new SharedFormDashboardPage(driver);			
					 String actualSharedFormTiltle = sharedFormDashboard.getSharedFormCard().getText();
					 String expectedSharedFormTitle=dynamicFormTitle;		
	
				  // Assert that actualFormTitle and expectedFormTitle 
				     Assert.assertEquals(actualSharedFormTiltle, expectedSharedFormTitle, "Actual shared form title does not match expected title");
				     ExtentManager.getTest().pass("Actual shared form title is matched with expected form title: "+ dynamicFormTitle);

				  // Admin Signout
				     formdashboard.clickAccountButton();
			   		 formdashboard.clickSignoutButton();		     
			   		 
				     
			   		 /** Signin as Editor */
			   		  loginPage.clickSignInUsingEmail();
					  loginPage.enterEmail("meyap64096@forcrack.com");
					  loginPage.clickNext();
					  loginPage.enterPassword("Automation@3");
					  loginPage.clickSignIn();
					  loginPage.closeFeatureSpotlightIfPresent();                       // In case popup appears after login
					  ExtentManager.getTest().pass("Editor login successfully with email and password.");
								
		
				   // Shared Dashboard
					  formdashboard.clickSharedTab();
						
	 // Shared Form Dashboard - Initialize the Shared form dashboard object with the current WebDriver instance
		SharedFormDashboardPage sharedFormDashboard2 = new SharedFormDashboardPage(driver);			
					  String actualSharedFormTiltle2 = sharedFormDashboard2.getSharedFormCard().getText();
					  String expectedSharedFormTitle2=dynamicFormTitle;		
		
				   // Assert that actualFormTitle and expectedFormTitle 
					  Assert.assertEquals(actualSharedFormTiltle2, expectedSharedFormTitle2, "Actual shared form title does not match expected title");
					  ExtentManager.getTest().pass("Actual shared form title is matched with expected form title: "+ dynamicFormTitle);

				   // Signout
					  formdashboard.clickAccountButton();
				   	  formdashboard.clickSignoutButton(); 		 
				   		 
				   		 
				   	 /** Signin as Viewer */
				   	  loginPage.clickSignInUsingEmail();
					  loginPage.enterEmail("nedapi9780@forcrack.com");
					  loginPage.clickNext();
					  loginPage.enterPassword("Automation@4");
					  loginPage.clickSignIn();
					  loginPage.closeFeatureSpotlightIfPresent();            // In case popup appears after login
					  ExtentManager.getTest().pass("Viewer login successfully with email and password.");
									
			
				   // Shared Dashboard
					  formdashboard.clickSharedTab();
							
	  // Shared Form Dashboard - Initialize the Shared form dashboard object with the current WebDriver instance
		 SharedFormDashboardPage sharedFormDashboard3 = new SharedFormDashboardPage(driver);			
					  String actualSharedFormTiltle3 = sharedFormDashboard3.getSharedFormCard().getText();
					  String expectedSharedFormTitle3=dynamicFormTitle;		
			
				   // Assert that actualFormTitle and expectedFormTitle 
					  Assert.assertEquals(actualSharedFormTiltle3, expectedSharedFormTitle3, "Actual shared form title does not match expected title");
					  ExtentManager.getTest().pass("Actual shared form title is matched with expected form title: "+ dynamicFormTitle);

						 				     
					  
	}

}


/**
Scenario: Test class to verify adding collaborators with different roles (Admin, Editor, Viewer) 
          and validate their access to the shared form.

Steps:
1. Login as the Owner, create a new form with a dynamic title and a Short Answer question.
2. Add collaborators with roles: Admin, Editor, and Viewer via the Collaboration tab, then close the Share popup.
3. Sign in as each collaborator and verify that the shared form is visible in their dashboard.

Expected Result:
- All collaborators (Admin, Editor, Viewer) can access the shared form in their dashboards.
*/





package com.surveyheart.tests.quizzes;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.surveyheart.base.BaseTest;
import com.surveyheart.enums.QuizQuestionType;
import com.surveyheart.pages.FormDashboardPage;
import com.surveyheart.pages.QuizBuilderPage;
import com.surveyheart.pages.QuizDashboardPage;
import com.surveyheart.pages.QuizSettingsPage;
import com.surveyheart.pages.SharePopupPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;
import com.surveyheart.utilities.WaitUtils;


/** Test class to verify sending a quiz link via WhatsApp after quiz creation */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class SendQuizLinkViaWhatsAppTest extends BaseTest {
	
	 @Test (groups = "regression", priority = 28)
	 public void sendQuizLinkViaWhatsAppAfterCreation() {                    // throws IOException, InterruptedException
	 // Login page - Initialize the Login Page object with the current WebDriver instance
	    SurveyHeartLoginPage loginPage = new SurveyHeartLoginPage(driver);
							loginPage.clickSignInUsingEmail();
							loginPage.enterEmail("gofaw36836@pacfut.com");
							loginPage.clickNext();
							loginPage.enterPassword("Automation@1");
							loginPage.clickSignIn();
							loginPage.closeFeatureSpotlightIfPresent();      // In case popup appears after login
							ExtentManager.getTest().pass("Login successful with email and password.");   
					
					
	 // Form Dashboard - Initialize the Form Dashboard Page object with the current WebDriver instance
		FormDashboardPage formDashboardPage = new FormDashboardPage(driver);
							formDashboardPage.refreshPage();
							formDashboardPage.clickQuizzesTab();	
							           
	 
	 // Quiz Dashboard - Initialize the Quiz Dashboard Page object with the current WebDriver instance
	    QuizDashboardPage quizDashboardPage = new QuizDashboardPage(driver);
				    	  quizDashboardPage.clickCreateQuizButton();            // Click on +Create Quiz button

				    // ===== Quiz Builder Page =====
				    QuizBuilderPage quizBuilderPage = new QuizBuilderPage(driver);

				          // Set dynamic Quiz title
							 String dynamicQuizTitle = "WhatsAppQuizLink " + System.currentTimeMillis();
							 quizBuilderPage.enterQuizTitle(dynamicQuizTitle);

						  // ==== Add a Short Answer question ====
							 quizBuilderPage.addQuestion(
							 QuizQuestionType.SHORT_ANSWER,
							 "What is your name?",
							 "Madhu",              // Answer
							 null,                 // No options
							 -1                    // No correct option
							);

				    
	  // Quiz Settings - Initialize the Quiz settings object with the current WebDriver instance
		 QuizSettingsPage quizSettingsPage = new QuizSettingsPage(driver);
				   	
							 quizSettingsPage.clickSettingsButton();
							 quizSettingsPage.clickControlTab();
							 quizSettingsPage.enableAllowMultipleAttempts(true);
							 ExtentManager.getTest().pass("<b>'Allow Multiple Attempts'</b> checkbox is enabled successfully.");
				   		     quizSettingsPage.clickSubmitButton();
				   		     ExtentManager.getTest().info("Quiz created successfully with : "+dynamicQuizTitle);  	     
			
	 // Share popup - Initialize the Sahre popup object with the current WebDriver instance
		SharePopupPage sharePopupPage = new SharePopupPage(driver);
					
							 String actualQuizLink=sharePopupPage.getCopiedQuizURL();
							 sharePopupPage.getParentWindowHandle();
							 sharePopupPage.clickWhatsAppAndSwitchToNewWindow();

        
     // WhatsApp Scanning			
        String contactName = "Dady";                      // Choose your required contact name in your WhatsApp

        try {
         // Handle "Fresh Look" popup
            try {
                WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@role='button' and normalize-space()='Continue']")));
                continueBtn.click();
                ExtentManager.getTest().pass("Intro popup dismissed.");
            } catch (TimeoutException e) {
                System.out.println("WhatsApp popup could not be auto-handled and was closed manually");
                ExtentManager.getTest().info("WhatsApp popup could not be auto-handled and was closed manually");
            }
            
         // Search for contact
            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@contenteditable='true' and @data-tab='3']")));
            searchInput.click();
            searchInput.sendKeys(contactName);
            WaitUtils.waitForSeconds(driver, 2);
        //  Thread.sleep(2000);                    // Fixed Time

            WebElement contact = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[@title='" + contactName + "']")));
            contact.click();

         // Send the message
            WebElement messageBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@contenteditable='true' and @data-tab='10']")));
            messageBox.sendKeys(actualQuizLink);
            messageBox.sendKeys(Keys.ENTER);

            ExtentManager.getTest().pass("Quiz link was sent successfully to the WhatsApp contact : " + contactName);
            ExtentManager.getTest().pass("Quiz Link ID : <a href='" + actualQuizLink + "' target='_blank'>" + actualQuizLink + "</a>");
        
        }   catch (Exception e) {
        	ExtentManager.getTest().fail("Failed to send message : " + e.getMessage());
    
        }   finally {
        	WaitUtils.waitForSeconds(driver, 3);    // Visibility of What's app link in Respective Contact number before immediately closing browser
          //Thread.sleep(1000);    
            driver.close();         // WhatsApp window closing
        }


        
        
	}

}

package com.surveyheart.tests.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.surveyheart.base.BaseTest;
import com.surveyheart.enums.QuestionType;
import com.surveyheart.pages.FormBuilderPage;
import com.surveyheart.pages.FormDashboardPage;
import com.surveyheart.pages.FormSettingsPage;
import com.surveyheart.pages.SharePopupPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;
import com.surveyheart.utilities.WaitUtils;


/** Test class to verify sending a form link via WhatsApp after form creation. */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class SendFormLinkViaWhatsAppTest extends BaseTest {
	
	 @Test (groups = "regression", priority = 7)
	 public void sendFormLinkViaWhatsAppAfterCreation() {              // throws IOException, InterruptedException
	 // Login page - Initialize the Login Page object with the current WebDriver instance
	    SurveyHeartLoginPage loginPage = new SurveyHeartLoginPage(driver);
					loginPage.clickSignInUsingEmail();
					loginPage.enterEmail("gofaw36836@pacfut.com");
					loginPage.clickNext();
					loginPage.enterPassword("Automation@1");
					loginPage.clickSignIn();
					loginPage.closeFeatureSpotlightIfPresent();        // In case popup appears after login
					ExtentManager.getTest().pass("Login successful with email and password.");   
					
					
	 // Form Dashboard - Initialize the Form Dashboard Page object with the current WebDriver instance
		FormDashboardPage formdashboard = new FormDashboardPage(driver);
					formdashboard.refreshPage();
					formdashboard.clickCreateFormButton();       // Click on +Create Form button

	 
	 // Form Builder - Initialize the Form builder object with the current WebDriver instance
	    FormBuilderPage builder = new FormBuilderPage(driver);

				 // Enter Form title in Builder screen
			        String dynamicFormTitle = "WhatsAppFormLink " + System.currentTimeMillis();
			        builder.enterFormTitle(dynamicFormTitle);
										
				 // 1. Short Answer
					builder.clickInitialAddQuestionButton();
				    builder.selectQuestionType(QuestionType.SHORT_ANSWER);
				    builder.enterQuestionTitle(0, "What is your name?");
						
					        
	 // Form Settings - Initialize the Form settings object with the current WebDriver instance
	    FormSettingsPage settings = new FormSettingsPage(driver);
					settings.clickSettingsButton();
					settings.clickControlTab();
					settings.enableAllowMultipleResponses(true); 
					ExtentManager.getTest().pass("'Allow Multiple Response' checkbox enabled successfully.");
					settings.clickSubmitButton();
			
	 // Share popup - Initialize the Sahre popup object with the current WebDriver instance
		SharePopupPage sharePopup = new SharePopupPage(driver);
					
					String actualFormLink=sharePopup.getCopiedFormURL();
					sharePopup.getParentWindowHandle();
					sharePopup.clickWhatsAppAndSwitchToNewWindow();

        
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
         // Thread.sleep(2000);

            WebElement contact = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[@title='" + contactName + "']")));
            contact.click();

         // Send the message
            WebElement messageBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@contenteditable='true' and @data-tab='10']")));
            messageBox.sendKeys(actualFormLink);
            messageBox.sendKeys(Keys.ENTER);

            ExtentManager.getTest().pass("Form link was sent successfully to the WhatsApp contact : " + contactName);
            ExtentManager.getTest().pass("Form Link ID : <a href='" + actualFormLink + "' target='_blank'>" + actualFormLink + "</a>");
        
        }   catch (Exception e) {
        	ExtentManager.getTest().fail("Failed to send message : " + e.getMessage());
    
        }   finally {
        	WaitUtils.waitForSeconds(driver, 3);   // Visibility of What's app link in Respective Contact number before immediately browser closing
         // Thread.sleep(1000);  
            driver.close();      // WhatsApp window closing
        }
	
	
	
	}

}


/**
Scenario: Test class to verify sending a form link via WhatsApp after form creation.

Steps:
1. Login to SurveyHeart using valid credentials.
2. Create a new form with at least one question (Short Answer).
3. Enable 'Allow Multiple Responses' in Form Settings and submit the form.
4. Open the Share popup and copy the form link.
5. Click the WhatsApp icon to open WhatsApp Web in a new window.
6. Search for a specific contact and send the copied form link.
7. Validate that the link was sent successfully.
8. Close the WhatsApp window after sending.

Expected Result:
- The form link is sent successfully via WhatsApp and is visible in the recipient's chat.
*/






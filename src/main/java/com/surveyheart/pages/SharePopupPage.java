package com.surveyheart.pages;


import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SharePopupPage {

    private WebDriver driver;
    private WebDriverWait wait;

    /** Constructor initializes PageFactory elements and explicit wait */
    public SharePopupPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
       
    }

    
    // ---------- WebElements Locators ----------
    
    /** Share view (eye) icon */
    private By viewIcon = By.xpath("//div[@id='share-card']//div//div//div[@class='mdc-button__touch']");
    
    /** Close (X) icon in share popup */
    private By closeIcon = By.xpath("//img[@alt='close']");
    
    /** Copy link button */
    private By copyLinkButton = By.xpath("//div[@id='share-copy-button']");
    
    /** Form link input field */
    private By formLinkInputBy = By.xpath("//input[contains(@value, 'https://surveyheart.com/form/')]");
    
    /** Quiz link input field */
    private By quizLinkInputBy = By.xpath("//input[contains(@value,'https:')]");
    
    /** WhatsApp share icon */
    private By whatsAppIconBy = By.xpath("//img[@alt='WhatsApp']");
    
    
    
 // ========== Action Methods with WebDriverWait  ==========

    /** Clicks the View icon to open the Share popup */
    public void clickViewIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(viewIcon)).click();
    }

    /** Clicks the Close icon on the Share popup */
    public void clickCloseIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(closeIcon)).click();
    }

    /*  Don't delete it - this copied form link, paste it in new window
 // Clicks the Copy Link button and returns the copied form URL from clipboard
    public String getCopiedFormURL() {
        WebElement copyBtn = wait.until(ExpectedConditions.elementToBeClickable(copyLinkButton));
        copyBtn.click();

        try {
            String copiedUrl = Toolkit.getDefaultToolkit().getSystemClipboard()
                    .getData(DataFlavor.stringFlavor).toString();
            
            return copiedUrl;
        } catch (Exception e) {
            throw new RuntimeException("Failed to get URL from clipboard: " + e.getMessage());
        }
    }*/  
    
    /** Switches to child window opened through the View icon */
    public void switchToChildWindowThroughViewIcon() {
     // Get the parent window handle
        String parentWindow = driver.getWindowHandle();

     // Get all window handles and switch to the one that is not the parent
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(parentWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }
   
    /** Clicks Copy Link, opens new tab, pastes the form URL, and navigates to it */
    public void openCopiedFormInNewTab() {
    // Step 1: Get Parent Window
       String parentWindow = getParentWindowHandle();

     // Step 2: Copy the URL
        String formUrl = getCopiedFormURL();

     // Step 3: Open new tab using JavaScript
        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

     // Step 4: Switch to the new tab
        for (String handle : tabs) {
            if (!handle.equals(parentWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }

     // Step 5: Open the copied URL in new tab
        driver.get(formUrl); 
    }    
    
    /** Clicks Copy Link, opens new tab, pastes the quiz URL, and navigates to it */
    public void openCopiedQuizInNewTab() {
     // Step 1: Get Parent Window
       String parentWindow = getParentWindowHandle();

     // Step 2: Copy the URL
        String quizUrl = getCopiedQuizURL();

     // Step 3: Open new tab using JavaScript
        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

     // Step 4: Switch to the new tab
        for (String handle : tabs) {
            if (!handle.equals(parentWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }

     // Step 5: Open the copied URL in new tab
        driver.get(quizUrl); 
    }
    
    /** Parent Window Handle here */
    private String parentWindowHandle;

    /** Stores the current (parent) window handle */
    public void storeParentWindowHandle() {
     parentWindowHandle = driver.getWindowHandle();
 }

    /** Returns the stored parent window handle */
    public String getParentWindowHandle() {
     return parentWindowHandle;
 }

    /** Returns the copied form URL from the input field */
    public String getCopiedFormURL() {
        WebElement formLinkInput = wait.until(ExpectedConditions.visibilityOfElementLocated(formLinkInputBy));
        return formLinkInput.getAttribute("value");
    }
      
    /** Returns the copied quiz URL from the input field */
    public String getCopiedQuizURL() {
        WebElement quizLinkInput = wait.until(ExpectedConditions.visibilityOfElementLocated(quizLinkInputBy));
        return quizLinkInput.getAttribute("value");
    }    
      
    /** Clicks the WhatsApp icon and switches to the new WhatsApp window */
    public String clickWhatsAppAndSwitchToNewWindow() {
     // Store the current window handle
        String parentWindow = driver.getWindowHandle();

     // Click the WhatsApp icon
        wait.until(ExpectedConditions.elementToBeClickable(whatsAppIconBy)).click();

     // Wait until a new window is opened
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

     // Get all window handles
        Set<String> allWindows = driver.getWindowHandles();
        for (String win : allWindows) {
            if (!win.equals(parentWindow)) {
                driver.switchTo().window(win);
                return win;                        // Return the new window handle
            }
        }

        throw new RuntimeException("New WhatsApp window not found after clicking the icon.");
    }


   
  
    
    
    
    
    
}

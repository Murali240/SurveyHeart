package com.surveyheart.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;




public class SubmittedPage {

    private WebDriver driver;
    private WebDriverWait wait;
    
    /** Constructor initializes PageFactory elements and explicit wait */
    public SubmittedPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        PageFactory.initElements(driver, this);       
    }

   
    
    // ======= Private WebElements =======
    
    /** Success message displayed after submission */
    private By submittedMessage = By.xpath("//h3[text()='Submitted']");

    /** '+ Add Response' button */
    private By addResponseButton = By.xpath("//div[@id='Add Response']");

    /** 'Response Summary' button if enabled */
    private By responseSummaryButton = By.xpath("//div[@id='Response Summary']");
    
    
 // ======= Web Elements with @FindBy =======
    
    /** 'View Results' button after quiz submission */
    @FindBy(xpath = "//div[@id='View Results']")
    private WebElement viewResultsButton;
    
    /** 'View Leaderboard' button */
    @FindBy(xpath = "//div[@id='View Leaderboard']")
    private WebElement viewLeaderboardButton;
    


    // ========== Action Methods with WebDriverWait  ==========
  
    /** Checks if the "Submitted" success message is displayed */
    public boolean isSubmittedMessageDisplayed() {
        try {
            WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(submittedMessage));
            return message.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }   
   
    /** Clicks the "+ Add Response" button */
    public void clickAddResponseButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addResponseButton));
        button.click();
    }
    
    /** Checks if the "+ Add Response" button is visible */
    public boolean isAddResponseButtonDisplayed() {
        try {
            WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(addResponseButton));
            return button.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
     
    /** Clicks the "Response Summary" button if enabled */
    public void clickResponseSummaryButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(responseSummaryButton));
        button.click();
    }
 
    /** Closes the child window and switches back to the parent */
    public void closeChildWindowAndSwitchToParent(String parentWindowHandle) {
        try {
            System.out.println("Closing current (child) window: " + driver.getTitle());
            driver.close();                                		                // Close the current window (child)
            driver.switchTo().window(parentWindowHandle);                       // Switch to parent window
            System.out.println("Switched back to parent window: " + driver.getTitle());
        } catch (Exception e) {
            throw new RuntimeException("Failed to close child window or switch back to parent.", e);
        }
    }

    /** Clicks the "View Results" button after quiz submission */
    public void clickViewResultsButton() {
        WebElement resultsBtn = wait.until(ExpectedConditions.visibilityOf(viewResultsButton));
        wait.until(ExpectedConditions.elementToBeClickable(resultsBtn)).click();
    }
    
    /** Checks if the "View Results" button is visible */
    public boolean isViewResultsButtonDisplayed() {
           try {
               WebElement resultsBtn = wait.until(ExpectedConditions.visibilityOf(viewResultsButton));
               return resultsBtn.isDisplayed();
           } catch (Exception e) {
               return false;
           }
       }    
    
    /** Clicks the "View Leaderboard" button */
    public void clickViewLeaderboardButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(viewLeaderboardButton));
        button.click();
    }
    
    /** Refreshes the browser page */
    public void refreshPage() {
        driver.navigate().refresh();
    }
    
  
    
  
    
    
    
    
}

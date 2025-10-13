package com.surveyheart.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SharedFormDashboardPage {

    private WebDriver driver;
    private WebDriverWait wait;

    /** Constructor initializes PageFactory elements and explicit wait */
    public SharedFormDashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }
    

 // ===================== Web Elements with @FindBy =====================
    
    /** Locator for the shared form card title (visible after login or when form is shared). */
    @FindBy(xpath = "//div[@id='list-card-title-0']//p[@id='card-form-title']")
    private WebElement sharedFormCard;

    /** Locator for the More Options (3 dots) icon on the form card. */
    @FindBy(xpath = "//img[@id='more0']")
    private WebElement moreOptions;

    /** Locator for the "Edit Form" option in the dropdown More. */
    @FindBy(xpath = "//div//p[text()='Edit Form']")
    private WebElement editFormButton;

    /** Locator for the "View Responses" button in the form dashboard from More options. */
    @FindBy(xpath = "//p[@id='dashboard-view-responses']")
    private WebElement viewResponsesButton;
    
    
    
    // ========== Action Methods with WebDriverWait  ==========

    /** Returns the shared form card after waiting for visibility. */
    public WebElement getSharedFormCard() {
        wait.until(ExpectedConditions.visibilityOf(sharedFormCard));
        return sharedFormCard;
    }

    /** Clicks the More Options (3-dot) icon after waiting for clickability. */
    public void clickMoreOptions() {
        wait.until(ExpectedConditions.elementToBeClickable(moreOptions)).click();
    }

    /** Clicks the 'Edit Form' option from More options. */
    public void clickEditFormButton() {
        wait.until(ExpectedConditions.elementToBeClickable(editFormButton)).click();
    }
    
    /** Clicks the 'View Responses' button from More options. */
    public void clickViewResponsesButton() {
            wait.until(ExpectedConditions.elementToBeClickable(viewResponsesButton)).click();
    }
    
    /** Refreshes the current browser page. */
    public void refreshPage() {
          driver.navigate().refresh();
    }
    
      
 
    
    
    
      
      
    
}

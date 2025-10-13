package com.surveyheart.pages;



import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;


public class ShowSummaryPage {

    WebDriver driver;
    WebDriverWait wait;

    /** Constructor initializes PageFactory elements and explicit wait */
    public ShowSummaryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }
    
    
    // ===================== Web Elements with @FindBy =====================

    /** Response Summary button */
    @FindBy(xpath = "//div[@id='Response Summary']")
    WebElement responseSummaryButton;

    /** Trending Surveys heading */
    @FindBy(xpath = "//h2[normalize-space()='Trending Surveys']")
    WebElement trendingSurveysButton;

    /** GET STARTED button */
    @FindBy(xpath = "//div[@id='GET STARTED']")
    WebElement getStartedButton;

    /** Entered short answer text */
    @FindBy(xpath = "//p[normalize-space()='Entered short answer']")
    WebElement answerText;
    

    
    // ========== Action Methods with WebDriverWait  ==========
    
    /** Clicks the Response Summary button */
    public void clickResponseSummary() {
        wait.until(ExpectedConditions.visibilityOf(responseSummaryButton)).click();
    }

    /** Returns the current(Summary) page URL */
    public String getSummaryPageURL() {
        return driver.getCurrentUrl();
    }

    /** Returns the text of the Trending Surveys heading */
    public String getTrendingSurveysText() {
        wait.until(ExpectedConditions.visibilityOf(trendingSurveysButton));
        return trendingSurveysButton.getText();
    }

    /** Checks if the GET STARTED button is displayed */
    public boolean isGetStartedDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(getStartedButton));
        return getStartedButton.isDisplayed();
    }

    /** Returns the entered short answer text from the responder */
    public String getResponderShortAnswer() {
        wait.until(ExpectedConditions.visibilityOf(answerText));
        return answerText.getText();
    }



    
    



}

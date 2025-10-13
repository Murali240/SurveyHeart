package com.surveyheart.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;




public class SharedQuizDashboardPage {

    private WebDriver driver;
    private WebDriverWait wait;

    /** Constructor initializes PageFactory elements and explicit wait */
    public SharedQuizDashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    

 // ===================== Web Elements with @FindBy =====================

    /** Shared Quizzes tab button */
    @FindBy(xpath = "//div[@class='template-button quiz-template-button']")
    private WebElement sharedQuizzesTab;

    /** First shared quiz card title */
    @FindBy(xpath = "//div[@id='list-card-title-0']//p[@id='card-form-title']")
    private WebElement sharedQuizCard;

    /** More options icon on the first shared quiz */
    @FindBy(xpath = "//img[@id='more0']")
    private WebElement moreOptions;

    /** Edit Quiz button from More options */
    @FindBy(xpath = "//p[normalize-space()='Edit Quiz']")
    private WebElement editQuizButton;
    
    /** View Answers button from More options */
    @FindBy(xpath = "//p[@id='dashboard-view-responses']")
    private WebElement viewAnswersButton;
    
    

    // ========== Action Methods with WebDriverWait  ==========

    /** Clicks the Shared Quizzes tab */
    public void clickSharedQuizzesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(sharedQuizzesTab)).click();
    }
    
    /** Returns the first Shared Quiz card */
    public WebElement getSharedQuizCard() {
        wait.until(ExpectedConditions.visibilityOf(sharedQuizCard));
        return sharedQuizCard;
    }

    /** Clicks the first Shared Quiz card */
    public void clickSharedQuizCard() {
        wait.until(ExpectedConditions.elementToBeClickable(sharedQuizCard)).click();
    }

    /** Clicks the More Options (3-dot) icon */
    public void clickMoreOptions() {
        wait.until(ExpectedConditions.elementToBeClickable(moreOptions)).click();
    }

    /** Clicks the Edit Quiz button from More options */
    public void clickEditQuizButton() {
        wait.until(ExpectedConditions.elementToBeClickable(editQuizButton)).click();
    }
    
    /** Clicks the View Answers button from More options */
    public void clickViewAnswersButton() {
          wait.until(ExpectedConditions.elementToBeClickable(viewAnswersButton)).click();
    }
    
    /** Refreshes the current browser page */
    public void refreshPage() {
        driver.navigate().refresh();
    }

    
    
    
    



}

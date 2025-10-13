package com.surveyheart.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class QuizzesTemplatesPage {

    private WebDriver driver;
    private WebDriverWait wait;

    /** Constructor initializes PageFactory elements and explicit wait */
    public QuizzesTemplatesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);                         // Initialize @FindBy elements
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    
    // ===================== Web Elements with @FindBy =====================

    /** Locator for the Quizzes tab button in the template section. */
    @FindBy(xpath = "//div[@class='template-button quiz-template-button']")
    private WebElement quizzesTab;

    /** Locator for the first quiz template card. */
    @FindBy(xpath = "(//div[@id='form-card-0'])[1]")
    private WebElement firstQuizTemplateCard;
    
    

    // ========== Action Methods with WebDriverWait  ==========

    /** Returns the visible Quizzes tab WebElement */
    public WebElement getQuizzesTab() {
        return wait.until(ExpectedConditions.visibilityOf(quizzesTab));
    }
    
    /** Waits for the Quizzes tab to be visible and performs a click action on it. */
    public void clickQuizzesTab() {
        getQuizzesTab().click();
    }

    /** Returns the first quiz template card WebElement */
    public WebElement getFirstQuizTemplateCard() {
        return wait.until(ExpectedConditions.visibilityOf(firstQuizTemplateCard));
    }

    /** Clicks the first quiz template card after it becomes visible */
    public void clickFirstQuizTemplateCard() {
        getFirstQuizTemplateCard().click();
    }



    
    




}

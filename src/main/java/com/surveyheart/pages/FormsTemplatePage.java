package com.surveyheart.pages;



import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FormsTemplatePage {

    private WebDriver driver;
    private WebDriverWait wait;

    /** Constructor to initialize driver and wait for FormsTemplatePage. */
    public FormsTemplatePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

 // ===================== Web Elements with @FindBy =====================
    
    /** WebElement for the first form card (form-card-0) */
    @FindBy(xpath = "(//div[@id='form-card-0'])[1]")
    private WebElement firstFormTemplateCard;
    
    
    // ========== Action Methods with WebDriverWait  ==========
    
    /** Returns the first visible form template card. */
    public WebElement getFirstFormTemplateCard() {
        return wait.until(ExpectedConditions.visibilityOf(firstFormTemplateCard));
    }

    /** Clicks the first form template card after waiting for its visibility.  */
    public void clickFirstFormTemplateCard() {
        getFirstFormTemplateCard().click();
    }


    

    




}

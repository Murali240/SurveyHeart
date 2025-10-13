package com.surveyheart.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PremiumPlansPage {

    private WebDriver driver;
    private WebDriverWait wait;

    /** Constructor initializes PageFactory elements and explicit wait */
    public PremiumPlansPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }
    
    
 // ===================== Web Elements with @FindBy =====================
    
    /** Premium Plans title text */
    @FindBy(xpath = "//span[normalize-space()='Premium plans']")
    private WebElement premiumPlansTitle;

    /** Close icon on the Premium Plans popup */
    @FindBy(xpath = "//img[@class='localization-close-img']")
    private WebElement closeIcon;
    
    
    
 // ========== Action Methods with WebDriverWait  ==========

    /** Returns Premium Plans title element after waiting */
    public WebElement getPremiumPlansTitle() {
        return wait.until(ExpectedConditions.visibilityOf(premiumPlansTitle));
    }

    /** Clicks the Close icon after waiting */
    public void clickCloseIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(closeIcon)).click();
    }



    
    
    
    


}

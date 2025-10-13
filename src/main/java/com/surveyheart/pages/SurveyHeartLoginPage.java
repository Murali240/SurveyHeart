package com.surveyheart.pages;



import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/** Page Object Model for SurveyHeart Login Page */
public class SurveyHeartLoginPage {

	/** WebDriver instance for performing login-related browser actions */
    private WebDriver driver;
    private WebDriverWait wait;
    
    /** Constructor initializes WebDriver and WebDriverWait */
    public SurveyHeartLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }
    

 // ===================== Web Elements with @FindBy =====================

    /** 'Sign in using Email' button on the first screen */
    @FindBy(xpath = "//span[normalize-space()='Sign in using Email']")
    private WebElement signInUsingEmailButton;            // Sign in using Email button on first screen

    /** Email input field */
    @FindBy(xpath = "//label[@id='label_field_Email']")
    private WebElement emailInputBox;                     // Email input field
    
    /** Next button after entering email */
    @FindBy(xpath = "//div[@id='Next']")
    private WebElement nextButton;                        // Next button after entering email
    
    /** Password input field */
    @FindBy(xpath = "//label[@id='label_field_Password']")
    private WebElement passwordInputBox;                  // Password field

    /** Final Sign-In button */
    @FindBy(xpath = "//div[@id='Sign in']")
    private WebElement signInButton;                      // Final Sign-In button

    /** Feature spotlight popup close button (if it appears) */
    @FindBy(xpath = "//img[@alt='Close']") 
    private WebElement featureSpotlightClose;             // Feature spotlight popup close button (if it appears)
    
    
    
 // ========== Action Methods with WebDriverWait  ==========

    /** Clicks the "Sign in using Email" button */
    public void clickSignInUsingEmail() {
        wait.until(ExpectedConditions.elementToBeClickable(signInUsingEmailButton)).click();
    }

    /** Enters the user's email */
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(emailInputBox)).sendKeys(email);
    }
    
    /** Clicks the "Next" button */
    public void clickNext() {
        nextButton.click();
    }

    /** Enters the user's password */
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordInputBox)).sendKeys(password);
    }

    /** Clicks the "Sign in" button */
    public void clickSignIn() {
        signInButton.click();
    }

    /** Closes the Feature Spotlight popup if it appears */
    public void closeFeatureSpotlightIfPresent() {
        try {
            if (featureSpotlightClose.isDisplayed()) {
                wait.until(ExpectedConditions.elementToBeClickable(featureSpotlightClose)).click();
            }
        } catch (Exception e) {
            // Safe to ignore if popup not present
        }
    }








}
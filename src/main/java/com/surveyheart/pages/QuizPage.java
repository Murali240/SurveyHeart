package com.surveyheart.pages;


import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class QuizPage {

    private WebDriver driver;
    private WebDriverWait wait;

    /** Constructor initializes PageFactory elements and explicit wait */
    public QuizPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);                         // Initialize @FindBy elements
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    
    // -------------------- Web Elements with @FindBy --------------------

    /** Quiz Title at the top of the quiz page */
    @FindBy(xpath = "//h5[@id='form-header-title']")
    private WebElement quizTitle;

    /** Start button on the quiz Welcome screen */
    @FindBy(xpath = "//div[@id='Start']")
    private WebElement startButton;

    /** Input field for entering participant's name */
    @FindBy(xpath = "//input[@id='input-Name']")
    private WebElement enterNameField;

    /** Button to start quiz after entering name */
    @FindBy(xpath = "//div[@id='Start Quiz']")
    private WebElement startQuizButton;
    
    // ----------------- Quiz Question Fields -----------------

    /** Short Answer text field element */
    @FindBy(xpath = "//textarea[@field-type='SHORT_TEXT']")
    private WebElement shortAnswerField;

    /** Long Answer text field element */
    @FindBy(xpath = "//textarea[@field-type='LONG_TEXT']")
    private WebElement longAnswerField;
    
    /** Multiple-choice options list (radio buttons) */
    @FindBy(xpath = "//input[contains(@id, '68')]")
    private List<WebElement> mcqOptions;

    /** Dropdown field for Dropdown questions */
    @FindBy(xpath = "//div[@id='drop-down-select']")
    private WebElement dropdown;
     
    /** All dropdown options (dynamic list, e.g., option-0, option-1) */
    @FindBy(xpath = "//div[starts-with(@id,'option-')]")
    private List<WebElement> dropdownOptions; 
    
    /** Upload button element */
    private By uploadButton = By.xpath("//div[@id='Upload']");
    
    /** File input element for choosing a file */
    private By fileInput = By.xpath("//input[@type='file']");
    
    /** Confirmation element shown after successful file upload */
    private By fileUploadConfirmation = By.xpath("//span[@class='text']");
    
    // ----------------- Quiz Meta / Status -----------------
    
    /** Total question count label (e.g., "Q3 of 10") */
    @FindBy(xpath = "//span[@id='total_question_count']")
    private WebElement totalQuestionCount;
    
    /** Quiz Closed status label */
    private By quizClosedStatus = By.xpath("//p[normalize-space()='CLOSED!']");
    
    /** Quiz Closed message text */
    private By quizClosedMessageText = By.xpath("//p[contains(text(),'This quiz is no longer accepting')]");
    
    // ----------------- Submit & Validation -----------------
    
    /** Submit button element */
    private By submitButton = By.xpath("(//div[@id='Submit'])");
    
    /** Required field warning message element */
    private By requiredWarningMessage = By.xpath("//div[@class='PXkf1WdWWy35wzAR0hu6']");
    
    /** First question title element (e.g., "1. Question text") */
    private By firstQuestionTitle = By.xpath("//span[starts-with(normalize-space(), '1. ')]");
    
    
    

    // -------------------- Action Methods with WebDriverWait --------------------

    /** Returns Quiz title text */
    public String getQuizTitle() {
        return wait.until(ExpectedConditions.visibilityOf(quizTitle)).getText();
    }

    /** Clicks Start button on Welcome screen */
    public void clickStart() {
        wait.until(ExpectedConditions.elementToBeClickable(startButton)).click();
    }

    /** Enters participant's name */
    public void enterName(String name) {
        wait.until(ExpectedConditions.visibilityOf(enterNameField)).sendKeys(name);
    }

    /** Clicks Start Quiz after entering name */
    public void clickStartQuiz() {
        wait.until(ExpectedConditions.elementToBeClickable(startQuizButton)).click();
    }
    
    /** Answers Short Question */
    public void answerShortQuestion(String answer) {
        wait.until(ExpectedConditions.visibilityOf(shortAnswerField)).sendKeys(answer);
    }

    /** Answers Long Question */
    public void answerLongQuestion(String answer) {
        wait.until(ExpectedConditions.visibilityOf(longAnswerField)).sendKeys(answer);
    }
    
    /** Selects MCQ option by number (1-based) */
    public void selectMCQOption(int optionNumber) {
        int index = optionNumber - 1;                       // Convert to 0-based index

        if (index >= 0 && index < mcqOptions.size()) {
            WebElement mcqOption = mcqOptions.get(index);

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", mcqOption);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", mcqOption);
        } else {
            throw new IllegalArgumentException("Invalid MCQ option number: " + optionNumber);
        }
    } 
     
    /** Selects Dropdown option by number (1-based) */
    public void selectDropdownOption(int optionNumber) {
    	
        // Open the dropdown first
           wait.until(ExpectedConditions.elementToBeClickable(dropdown)).click();

           int index = optionNumber - 1;          // Convert to 0-based index

           if (index >= 0 && index < dropdownOptions.size()) {
               WebElement option = dropdownOptions.get(index);
               ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
               ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
           } else {
               throw new IllegalArgumentException("Invalid dropdown option number: " + optionNumber);
           }
       } 
   
    /** Uploads a file */    
    public void uploadFile(String filePath) {
        WebElement upload = wait.until(ExpectedConditions.visibilityOfElementLocated(uploadButton));
        upload.click();

        WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(fileInput));
        input.sendKeys(filePath);

        wait.until(ExpectedConditions.visibilityOfElementLocated(fileUploadConfirmation));
    }
    
    /** Returns total Quiz question count */
    public String getTotalQuestionCount() {
        String countText = wait.until(ExpectedConditions.visibilityOf(totalQuestionCount)).getText();
        Matcher matcher = Pattern.compile("of\\s*(\\d+)").matcher(countText);
        return matcher.find() ? matcher.group(1) : "0";
    }
         
    /** Returns 'CLOSED!' status text */
    public String getQuizClosedStatusText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(quizClosedStatus)).getText();
    }

    /** Returns quiz closed detailed message */
    public String getQuizClosedMessageText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(quizClosedMessageText)).getText();
    }
    
    /** Closes child window and switches to parent */
    public void closeChildWindowAndSwitchToParent(String parentWindowHandle) {
        driver.close();                                                           // Close current (child) window
        driver.switchTo().window(parentWindowHandle);                             // Switch back to parent
    }
    
    /** Clicks Submit button */
    public void clickSubmitButton() {
        WebElement submitBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(submitButton));

     // Scroll to center of view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", submitBtn);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
        } catch (ElementClickInterceptedException e) {
            // Fallback: JavaScript click if intercepted
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitBtn);
        }
    }

    /** Returns Required warning message element */
    public WebElement getRequiredWarningMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(requiredWarningMessage));
    }  
   
    /** Returns first question title text */
    public String getFirstQuestionTitleText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(firstQuestionTitle)).getText();               
    }
    
    
  
    
    
    
    
    
   
}

package com.surveyheart.pages;


import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class FormPage {

    private WebDriver driver;
    private WebDriverWait wait;

    /** Constructor to initialize driver and wait for FormPage. */
    public FormPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    

    // ========== Web Elements Locators ========== 
    
    private By formTitle = By.xpath("//h5[@id='form-header-title']");
    private By startButton = By.xpath("//div[@id='Start']");
    private By enterNameField = By.xpath("//input[@id='input-Name']");
    private By startFormButton = By.xpath("//div[@id='Start Form']");
    private By shortAnswerField = By.xpath("//textarea[@field-type='SHORT_TEXT']");
    private By longAnswerField = By.xpath("//textarea[@field-type='LONG_TEXT']");
    private By emailField = By.xpath("//input[@type='email']");
    private By numberField = By.xpath("//input[@type='number']");
    private By checkbox = By.xpath("(//div[@id='mdc-checkbox'])[3]");
    private By dropdown = By.xpath("//div[@id='drop-down-select']");
    private By dropdownOption = By.xpath("//div[@id='option-0']");
    private By smileRating = By.xpath("//img[@alt='Smile 5']");
    private By starRating = By.xpath("//img[@alt='Star 5']");
    private By dateInput = By.xpath("//input[@type='date']");
    private By timeInput = By.xpath("//input[@type='time']");
    private By uploadButton = By.xpath("//div[@id='Upload']");
    private By fileInput = By.xpath("//input[@type='file']");
    private By fileUploadConfirmation = By.xpath("//span[@class='text']");
    private By checkboxGridOption = By.xpath("(//input[@type='checkbox'])[5]");
    private By totalQuestionCount = By.xpath("//span[@id='total_question_count']");
    private By submitButton = By.xpath("(//div[@id='Submit'])");   
    private By requiredWarningMessage = By.xpath("//div[@class='PXkf1WdWWy35wzAR0hu6']");
    private By firstQuestionTitle = By.xpath("//span[starts-with(normalize-space(), '1. ')]");
    
    @FindBy(xpath = "//input[contains(@id, '68')]") 
    private List<WebElement> mcqOptions;
    

    
    // ========== Action Methods with WebDriverWait ==========

    /** Returns the Form title text. */
    public String getFormTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(formTitle)).getText();
    }

    /** Clicks the Start button on Welcome page. */
    public void clickStartButton() {
        wait.until(ExpectedConditions.elementToBeClickable(startButton)).click();
    }

    /** Enters name if the field is present, otherwise skips. */
    public void enterNameIfPresent(String name) {
        try {
            WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(enterNameField));
            nameField.sendKeys(name);
            wait.until(ExpectedConditions.elementToBeClickable(startFormButton)).click();
        } catch (TimeoutException e) {
            System.out.println("Name input not required for this form.");
        }
    }

    /** Answers a short text question. */
    public void answerShortText(String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(shortAnswerField)).sendKeys(text);
    }

    /** Answers a long text question. */
    public void answerLongText(String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(longAnswerField)).sendKeys(text);
    }

    /** Answers an email question. */
    public void answerEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
    }

    /** Answers a number question. */
    public void answerNumber(String number) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(numberField)).sendKeys(number);
    }
    
    /** Selects an MCQ option by its 1-based index. */
    public void selectMCQOption(int optionNumber) {
        int index = optionNumber - 1;                     // Convert to 0-based index

        if (index >= 0 && index < mcqOptions.size()) {
            WebElement mcqOption = mcqOptions.get(index);

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", mcqOption);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", mcqOption);
        } else {
            throw new IllegalArgumentException("Invalid MCQ option number: " + optionNumber);
        }
    }  
    
    /** Selects a checkbox option. */
    public void selectCheckboxOption() {
        WebElement checkboxElement = wait.until(ExpectedConditions.presenceOfElementLocated(checkbox));
        scrollToElement(checkboxElement);
        wait.until(ExpectedConditions.elementToBeClickable(checkboxElement)).click();
    }

    /** Selects a dropdown option. */
    public void selectDropdownOption() {
        WebElement dropdownElement = wait.until(ExpectedConditions.presenceOfElementLocated(dropdown));
        scrollToElement(dropdownElement);
        dropdownElement.click();
        wait.until(ExpectedConditions.elementToBeClickable(dropdownOption)).click();
    }

    /** Selects a smile rating option. */
    public void selectSmileRating() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(smileRating)).click();
    }

    /** Selects a star rating option. */
    public void selectStarRating() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(starRating)).click();
    }

    /** Selects today's date in the date input. */
    public void selectDateToday() {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(dateInput)).sendKeys(date);
    }

    /** Selects the current time in the time input. */
    public void selectCurrentTime() {
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(timeInput)).sendKeys(time);
    }

    /** Uploads a file from the given path. */
    public void uploadFile(String filePath) {
        WebElement upload = wait.until(ExpectedConditions.visibilityOfElementLocated(uploadButton));
        upload.click();

        WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(fileInput));
        input.sendKeys(filePath);

        wait.until(ExpectedConditions.visibilityOfElementLocated(fileUploadConfirmation));
    }

    /** Selects a checkbox option in the grid. */
    public void selectCheckboxGridOption() {
        WebElement gridOption = wait.until(ExpectedConditions.presenceOfElementLocated(checkboxGridOption));
        scrollToElement(gridOption);
        wait.until(ExpectedConditions.elementToBeClickable(gridOption)).click();
    }

    /** Returns total number of questions from 'x of y' text. */
    public String getTotalQuestionCount() {
        String countText = wait.until(ExpectedConditions.visibilityOfElementLocated(totalQuestionCount)).getText();
        Matcher matcher = Pattern.compile("of\\s*(\\d+)").matcher(countText);
        return matcher.find() ? matcher.group(1) : "0";
    }
 
    /** Clicks the Submit button (with JS fallback). */
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

    // ========== Utility ==========
    /** Scrolls the page to bring the element into view. */
    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /** Returns 'CLOSED!' status text. */
    public String getFormClosedStatusText() {
        By closedStatus = By.xpath("//p[normalize-space()='CLOSED!']");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(closedStatus)).getText();
    }

    /** Returns message text shown below 'CLOSED!'. */
    public String getFormClosedMessageText() {
        By closedMessage = By.xpath("//p[contains(text(),'This form is no longer accepting')]");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(closedMessage)).getText();
    }
       
    /** Closes child window and switches back to parent window. */
    public void closeChildWindowAndSwitchToParent(String parentWindowHandle) {
        driver.close();                                                           // Close current (child) window
        driver.switchTo().window(parentWindowHandle);                             // Switch back to parent
    }   
    
    /** Clicks 'Start Survey' button in Form Template. */
    public void clickStartSurveyTemplateButton() {
        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//div[@id='Start Survey']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
    }
  
    /** Returns the Required warning message element. */
    public WebElement getRequiredWarningMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(requiredWarningMessage));
    }
    
    /** Returns the first question title text. */ 
    public String getFirstQuestionTitleText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(firstQuestionTitle)) .getText();               
    }
    
    
    
    
    
    
    
    
    
}

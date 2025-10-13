package com.surveyheart.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AnswersPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    /** Constructor to initialize WebElements and explicit wait */
    public AnswersPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    

    // ===================== Web Elements with @FindBy =====================

    /** Answer Button to navigate to the Answers screen */
    @FindBy(xpath = "//span[@class='icon-title'][normalize-space()='Answers']")
    private WebElement answersButton;

    /** Displays total response count in the Answers screen */
    @FindBy(xpath = "//div[@id='summary-response-count-box']")
    private WebElement answersCountInAnswersScreen;

    /** Title of the quiz displayed on the Answers screen */
    @FindBy(xpath = "//span[@id='response-form-title']")
    private WebElement quizTitleInAnswersScreen;

    /** Individual Button switch to Individual screen */
    @FindBy(xpath = "//span[normalize-space()='Individual']")
    private WebElement individualButton;

    /** Delete button inside Individual screen */
    @FindBy(xpath = "//div[@id='Delete']")
    private WebElement deleteButtonIndividual;

    /** Delete button on the confirmation Delete popup */
    @FindBy(xpath = "//label[normalize-space()='DELETE']")
    private WebElement deleteButtonOnDeletePopup;

    /** Tabular Button to switch to Tabular screen */
    @FindBy(xpath = "//span[normalize-space()='Tabular']")
    private WebElement tabularButton;

    /** Download button to open download options popup for PDF, Excel, CSV files */
    @FindBy(xpath = "//span[normalize-space()='Download']")
    private WebElement downloadButton;

    /** Publish button to publish quiz results */
    @FindBy(xpath = "//span[normalize-space()='Publish']")
    private WebElement publishButton;

    /** Search box available in Tabular screen */
    @FindBy(xpath = "//input[@placeholder='Search']")
    private WebElement searchBoxInTabular;
       
    /** First answer text of the first question in Individual screen */
    @FindBy(xpath = "//p[@class='response-text']")
    private WebElement firstQuestionAnswer;
    
    /** Undo button shown in toast message after answer deletion */
    @FindBy(xpath = "//a[normalize-space()='Undo']")
    private WebElement undoToastButton;
    
    /** Text displayed in Undo toast message */
    @FindBy(xpath = "//p[@class='response-undo-message']")
    private WebElement undoToastMessageText;
    
    /** First answer checkbox of the first row in Tabular screen */
    @FindBy(xpath = "//tbody/tr[1]/td[1]/div[1]/div[1]/input[1]")
    private WebElement firstAnswerCheckbox;
    
    /** Search bar in Tabular screen */
    @FindBy(xpath = "//input[@placeholder='Search']")
    private WebElement tabularSearchBar;
    
    /** First answer displayed after performing search in Tabular screen */
    @FindBy(xpath = "//p[@id='table-text-cell-container-p']")
    private WebElement searchedFirstAnswer;
    
    /** PDF file icon inside the Download as popup */
    @FindBy(xpath = "//img[@alt='pdf']")
    private WebElement pdfFileOnDownloadPopup;
    
    /** Download PDF Button after clicked on the .pdf file */
    @FindBy(xpath = "//div[@id='Download PDF']")
    private WebElement downloadPDFButton; 
    
    /** Answer submitted Date and time of the individual answer */
    @FindBy(xpath = "//div[@class='right-aligned-container']//span[@class='responded-time']")
    private WebElement answerDateTime;
    
    /** Next Arrow icon to navigate for next screen answer */
    @FindBy(xpath = "//img[@alt='next-button']")
    private WebElement nextAnswerArrow;
    
    /** User name displayed in Individual screen */
    @FindBy(xpath = "//span[@class='user-info-title'][contains(text(),':')]")
    private WebElement userNameInIndividual;
    
    /** Edit icon for Long Answer question (if positioned 2nd) */
    @FindBy(xpath = "//div[@class='individual-question-card-outer-wrapper']//div[3]//div[2]//div[2]//img[1]")
    private WebElement secondAnswerEditIcon;

    /** Edit icon for File Upload question (if positioned 5th) */ 
    @FindBy(xpath = "//div[@class='individual-question-card-outer-wrapper']//div[6]//div[2]//div[2]//img[1]")
    private WebElement fifthAnswerEditIcon;
    
    /** Input box to enter marks */
    @FindBy(xpath = "//input[@class='mdc-text-field__input']")
    private WebElement marksBox;

    /** Save button to confirm entered marks */
    @FindBy(xpath = "//div[@id='Save']")
    private WebElement saveButton;
    
    /** Publish button on Publish Confirmation popup */
    @FindBy(xpath = "//label[normalize-space()='Publish']")
    private WebElement publishButtonOnPublishPopup;

    /** Published Successfully Popup title message */
    @FindBy(css = "div[id='quiz-publish-popup-title'] span")
    private WebElement publishSuccessfullyPopup;

    /** Close button on Publish Successfully popup */
    @FindBy(xpath = "//div[@id='Close']")
    private WebElement closeButtonOnPublishSuccessfullyPopup;
    
    
    
    

    // ===================== Action Methods with WebDriverWait =====================

    /** Clicks the Answers button to open the Answers screen */
    public void clickAnswersButton() {
        wait.until(ExpectedConditions.elementToBeClickable(answersButton)).click();
    }

    /** Gets the total answers count from Answers screen as an integer */
    public int getTotalAnswersCount() {
        String countText = wait.until(ExpectedConditions.visibilityOf(answersCountInAnswersScreen)).getText();
        return Integer.parseInt(countText.trim());
    }

    /** Gets the Quiz title displayed on the Answers screen */
    public String getQuizTitle() {
        return wait.until(ExpectedConditions.visibilityOf(quizTitleInAnswersScreen)).getText();
    }

    /** Clicks the Individual button in Answers screen */
    public void clickIndividualButton() {
        wait.until(ExpectedConditions.elementToBeClickable(individualButton)).click();
    }

    /** Clicks Delete button inside the Individual screen */
    public void clickDeleteButtonIndividual() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteButtonIndividual)).click();
    }

    /** Confirms delete action by clicking DELETE button on Delete popup */
    public void clickDeleteButtonOnDeletePopup() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteButtonOnDeletePopup)).click();
    }

    /** Clicks the Tabular button in Answers screen */
    public void clickTabularButton() {
        wait.until(ExpectedConditions.elementToBeClickable(tabularButton)).click();
    }

    /** Clicks the Download button to open download options for PDF, Excel, CSV files */
    public void clickDownloadButton() {
        wait.until(ExpectedConditions.elementToBeClickable(downloadButton)).click();
    }

    /** Clicks the Publish button in Answers screen */
    public void clickPublishButton() {
        wait.until(ExpectedConditions.elementToBeClickable(publishButton)).click();
    }

    /** Enters given text in Tabular search box */
    public void enterTextInSearchBox(String text) {
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOf(searchBoxInTabular));
        searchBox.clear();
        searchBox.sendKeys(text);
    }

    /** Returns the first answer element in Individual screen */
    public WebElement getFirstQuestionAnswer() {
        return wait.until(ExpectedConditions.visibilityOf(firstQuestionAnswer));
    }

    /** Returns text of the first answer in Individual screen */
    public String getFirstQuestionAnswerText() {
        return getFirstQuestionAnswer().getText();
    }

    /** Clicks Undo button inside the toast message */
    public void clickUndoToastButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(undoToastButton));
        button.click();
    }
    
    /** Returns combined Undo toast message text (message + UNDO) */
    public String getFullUndoToastMessage() {
        String message = wait.until(ExpectedConditions.visibilityOf(undoToastMessageText)).getText();
        String undoText = wait.until(ExpectedConditions.visibilityOf(undoToastButton)).getText();
        return message + " " + undoText;
    } 
    
    /** Selects the first answer checkbox in Tabular screen */
    public void selectFirstCheckboxInTabular() {
        WebElement checkbox = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody/tr[1]/td[1]/div[1]/div[1]/input[1]"))
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
    }
    
    
    /** Selects answers dynamically in Tabular screen by row numbers (e.g., 1, 2, 3) */
    public void selectResponses(int... rowNumbers) {
    for (int row : rowNumbers) {
        String dynamicXpath = "//tbody/tr[" + row + "]/td[1]/div[1]/div[1]/input[1]";
        WebElement checkbox = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath(dynamicXpath))
        );
     // Scroll and click using JS in case of hidden styled checkbox
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
        }
     }
  
    
    /** Searches for a given answer in Tabular search bar and presses ENTER */
    public void searchAnswerBar(String responseText) {
        WebElement searchBar = wait.until(ExpectedConditions.visibilityOf(tabularSearchBar));
        searchBar.clear();
        searchBar.sendKeys(responseText);
        searchBar.sendKeys(Keys.ENTER);      // Press ENTER to trigger filtering
    }
    
    /** Returns the first searched answer element in Tabular screen */
    public WebElement getFirstSearchedAnswer() {
        return wait.until(ExpectedConditions.visibilityOf(searchedFirstAnswer));
    } 
    
    
    /** Clicks the PDF file icon in download popup */
    public void clickPdfFileOnDownloadPopup() {
        wait.until(ExpectedConditions.elementToBeClickable(pdfFileOnDownloadPopup)).click();
    }
    
    /** Clicks the Download PDF button in download as popup */
     public void clickDownloadPDFButton() {
        wait.until(ExpectedConditions.elementToBeClickable(downloadPDFButton)).click();
     } 
        
     /** Gets the date-time element of the current answer in Individual screen */
     public WebElement getAnswerDateTimeInIndividual() {
         return wait.until(ExpectedConditions.visibilityOf(answerDateTime));
     }
    
     /** Clicks the Next arrow icon to view the next answer in Individual screen */
     public void clickNextAnswerArrow() {
         wait.until(ExpectedConditions.elementToBeClickable(nextAnswerArrow)).click();
     }

     /** Gets the User name element from Individual screen */
     public WebElement getUserNameInIndividual() {
         wait.until(ExpectedConditions.visibilityOf(userNameInIndividual));
         return userNameInIndividual;
     }
        
     /** Scrolls into view and clicks an Edit icon (utility for edit actions) */
     private void scrollAndClick(WebElement element) {
         WebElement visibleElement = wait.until(ExpectedConditions.visibilityOf(element));
         js.executeScript("arguments[0].scrollIntoView({block: 'center'});", visibleElement);
         wait.until(ExpectedConditions.elementToBeClickable(visibleElement)).click();
     }

     /** Clicks the 2nd answer's Edit icon (Long Answer question) */
     public void clickSecondAnswerEditIcon() {
         scrollAndClick(secondAnswerEditIcon);
     }

     /** Clicks the 5th answer's Edit icon (File Upload question) */
     public void clickFifthAnswerEditIcon() {
         scrollAndClick(fifthAnswerEditIcon);
     }
     
     /** Enters marks into the marks box (clears existing value before typing new values) */
     public void enterMarks(String marks) {
         WebElement box = wait.until(ExpectedConditions.visibilityOf(marksBox));
         box.sendKeys(Keys.chord(Keys.CONTROL, "a"));     // Select all existing text
         box.sendKeys(Keys.DELETE);                       // Clear it
         box.sendKeys(marks);                             // Enter new marks
     }

     /** Clicks the Save button after entering marks */
     public void clickSaveButton() {
         WebElement save = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
         save.click();
     }
     
     /** Clicks Publish button inside Publish popup */
     public void clickPublishButtonOnPublishPopup() {
         wait.until(ExpectedConditions.elementToBeClickable(publishButtonOnPublishPopup)).click();
     }

     /** Gets the Publish success popup element */
     public WebElement getPublishSuccessfullyPopup() {
         return wait.until(ExpectedConditions.visibilityOf(publishSuccessfullyPopup));
     }

     /** Clicks the Close button on Publish Successful popup */
     public void clickCloseButtonOnPublishSuccessfullyPopup() {
         wait.until(ExpectedConditions.elementToBeClickable(closeButtonOnPublishSuccessfullyPopup)).click();
     }
     
     /** Checks if Delete button is visible in Individual screen (used for role-based validation) */
     public boolean isDeleteButtonDisplayedInIndividual() {	 
         try {
          // Temporarily set implicit wait to 0
             driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
             boolean isDisplayed = deleteButtonIndividual.isDisplayed();
             return isDisplayed;
         } catch (NoSuchElementException e) {
             return false;                       // safely handle when element is not present
         } finally {
          // Restore implicit wait back to original value (e.g., 10 seconds)
             driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
         }
     }
      
     /** Refreshes the browser page */
     public void pageRefresh() {
         driver.navigate().refresh();
     }
     
     
     
     
     
     
    

}

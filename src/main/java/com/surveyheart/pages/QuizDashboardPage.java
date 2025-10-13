package com.surveyheart.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import com.surveyheart.utilities.ExtentManager;

import java.time.Duration;
import java.util.Set;


public class QuizDashboardPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private String parentWindowHandle;

    /** Constructor initializes PageFactory elements and explicit wait */
    public QuizDashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    

    // ===================== Web Elements with @FindBy =====================

    /** "+Create Quiz" button */
    @FindBy(xpath = "//span[normalize-space()='Create Quiz']")
    private WebElement createQuizButton;
    
    /** First quiz card title */
    @FindBy(xpath = "//div[@id='list-card-title-0']//p[@id='card-form-title']")
    private WebElement firstQuizCard;

    /** 3-dot menu (More Options) for first Quiz */
    @FindBy(xpath = "//img[@id='more0']")
    private WebElement firstQuizMoreOptions;

    /** Active/Inactive status toggle from More options */
    @FindBy(xpath = "//img[@alt='status-switch']")
    private WebElement quizStatusSwitchButton;
    
    /** "View Answers" button from More options  */
    @FindBy(xpath = "//p[@id='dashboard-view-responses']")
    private WebElement viewAnswersButton;

    /** "Edit Quiz" button from More options */
    @FindBy(xpath = "//div//p[text()='Edit Quiz']")
    private WebElement editQuizButton;

    /** "View Quiz" button from More options */
    @FindBy(xpath = "//p[normalize-space()='View Quiz']")
    private WebElement viewQuizButton;

    /** "Duplicate Quiz" button from More options */
    private By duplicateQuizButton = By.xpath("//p[normalize-space()='Duplicate Quiz']");   

    /** "Delete Quiz" button from More options */
    @FindBy(xpath = "//div//p[text()='Delete Quiz']")
    private WebElement deleteButton;

    /** "DELETE" confirmation button on Delete popup */
    @FindBy(xpath = "//label[normalize-space()='DELETE']")
    private WebElement confirmDeleteButton;

    /** Search input box for entering Quiz title */
    @FindBy(xpath = "//input[@placeholder='Search']")
    private WebElement searchInput;

    /** Quiz count text (e.g., "Quiz (12)") */
    @FindBy(xpath = "//span[contains(text(),'(') and contains(text(),')')]")
    private WebElement totalQuizzesCountText;
    
    /** Second Quiz card title */
    @FindBy(xpath = "//div[@id='list-card-title-1']//p[@id='card-form-title']")
    private WebElement secondQuizCard;
    
    /** 3-dot More options for second quiz */
    @FindBy(xpath = "//img[@id='more1']")
    private WebElement secondQuizMoreOptions;
    
    /** "Undo" toast button */
    @FindBy(xpath = "//a[normalize-space()='Undo']")
    private WebElement undoToastButton;
    
    /** "Undo" toast message text */
    @FindBy(xpath = "//p[@class='response-undo-message']")
    private WebElement undoToastMessageText;
    
    /** Total answers count inside More options */
    @FindBy(xpath = "//div[@id='dashboard-view-responses-count']")
    private WebElement totalAnswersInMore;

    /** First Quiz multi-select checkbox */
    @FindBy(xpath = "//div[@id='form-card-0']//div//div[@class='multiple-select-tag-for-delete-in-hover-list-view']")
    private WebElement firstQuizSelection;

    /** Second Quiz multi-select checkbox */
    @FindBy(xpath = "//div[@id='form-card-1']//div[@class='multiple-select-layer']")
    private WebElement secondQuizSelection;
    
    /** Delete button in multi-select mode */
    @FindBy(xpath = "//div[@id='Delete']")
    private WebElement deleteButtonQuizSelection;

    /** Confirm delete button for Multi deletion (DELETE (...)) */
    @FindBy(xpath = "//label[starts-with(normalize-space(),'DELETE (')]")
    private WebElement confirmDeleteButtonQuizSelection;

    /** "No Quiz Available!" message */
    @FindBy(xpath = "//h3[normalize-space()='No Quiz Available!']")
    private WebElement noQuizAvailable;
    
    /** Quiz edit warning popup title (With answers quiz) */
    @FindBy(xpath = "//div[@class='modal-dialog-header']")
    private WebElement quizEditWarningPopupTitle;

    /** Quiz edit warning popup message */
    @FindBy(xpath = "//div[@class='modal-dialog-body-line']")
    private WebElement quizEditWarningMessage;

    /** "EDIT" button on warning popup */
    @FindBy(xpath = "//label[normalize-space()='EDIT']")
    private WebElement editButtonOnWarningPopup;
    
    /** Published quiz cloud icon */
    @FindBy(xpath = "//img[@src='/images/cloud_success.svg']")
    private WebElement publishedIconOnFirstQuizCard; 
    
    
    
    // -------------------- Action Methods with WebDriverWait --------------------

    /** Clicks the "+Create Quiz" button */
    public void clickCreateQuizButton() {
        wait.until(ExpectedConditions.elementToBeClickable(createQuizButton)).click();
    }
    
    /** Returns the first quiz card title */
    public String getFirstQuizCardTitle() {
        return wait.until(ExpectedConditions.visibilityOf(firstQuizCard)).getText();
    }

    /** Clicks the first quiz card */
    public void clickFirstQuizCardTitle() {
        wait.until(ExpectedConditions.visibilityOf(firstQuizCard)).click();
    } 

    /** Opens More Options for the first quiz */
    public void clickMoreOptionsForFirstQuiz() {
        wait.until(ExpectedConditions.elementToBeClickable(firstQuizMoreOptions)).click();
    }
    
    /** Opens More Options menu for the second quiz */ 
    public void clickSecondQuizMoreOptions() {
        wait.until(ExpectedConditions.elementToBeClickable(secondQuizMoreOptions)).click();
    }

    /** Toggles quiz status from More options (Active ↔ Inactive) */
    public void clickQuizStatusSwitch() {
        wait.until(ExpectedConditions.elementToBeClickable(quizStatusSwitchButton)).click();
    }
       
    /** Clicks "View Answers" button */
    public void clickViewAnswersButton() {
        wait.until(ExpectedConditions.visibilityOf(viewAnswersButton));
        wait.until(ExpectedConditions.elementToBeClickable(viewAnswersButton)).click();
    }

    /** Clicks "Edit Quiz" button */
    public void clickEditQuiz() {
        wait.until(ExpectedConditions.elementToBeClickable(editQuizButton)).click();
    }

    /** Clicks "View Quiz" button */
    public void clickViewQuizButton() {
        wait.until(ExpectedConditions.elementToBeClickable(viewQuizButton)).click();
    }

    /** Stores current window handle */
    public void storeParentWindowHandle() {
        this.parentWindowHandle = driver.getWindowHandle();
    }

    /** Returns stored parent window handle */
    public String getParentWindowHandle() {
        return this.parentWindowHandle;
    }

    /** Switches to child window */
    public void switchToChildWindow() {
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> allWindows = driver.getWindowHandles();
        for (String handle : allWindows) {
            if (!handle.equals(parentWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }
    
    /** Clicks "Duplicate Quiz" button */
    public void clickDuplicateQuizButton() {
        wait.until(ExpectedConditions.elementToBeClickable(duplicateQuizButton)).click();
    }
    
    /** Clicks "Delete Quiz" button */
    public void clickDeleteButton() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton)).click();
    }
      
    /** Clicks the 'Delete' button in the confirmation Delete popup */
    public void clickConfirmDeleteButton() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton)).click();
    }

    /** Searches quiz by title */
    public void searchFormByTitle(String quizTitle) {
        wait.until(ExpectedConditions.visibilityOf(searchInput)).clear();
        searchInput.sendKeys(quizTitle);
    }

    /** Returns total quizzes count */
    public int getTotalQuizzesCount() {
        try {
            wait.until(ExpectedConditions.visibilityOf(totalQuizzesCountText));
            String text = totalQuizzesCountText.getText();    // e.g., "Quiz (12)"
            String count = text.replaceAll("[^0-9]", "");     // Extract "12"
            return Integer.parseInt(count);
        } catch (Exception e) {
            return -1;             // Return -1 if text not found or parsing fails
        }
    }
    
    /** Closes "Follow Us" popup if present */
    public void ifFollowUsPopupDisplayed() {
        try {
            WebElement followUs = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//img[@alt='data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyNCIgaGVpZ2h0PSIyNCIgdmlld0JveD0iMCAwIDI0IDI0Ij4KICAgIDxwYXRoIGQ9Ik0xOSA2LjQxTDE3LjU5IDUgMTIgMTAuNTkgNi40MSA1IDUgNi40MSAxMC41OSAxMiA1IDE3LjU5IDYuNDEgMTkgMTIgMTMuNDEgMTcuNTkgMTkgMTkgMTcuNTkgMTMuNDEgMTJ6Ii8+CiAgICA8cGF0aCBkPSJNMCAwaDI0djI0SDB6IiBmaWxsPSJub25lIi8+Cjwvc3ZnPg=='])[1]")));

            followUs.click();
            ExtentManager.getTest().info("Follow Us popup was present and closed successfully.");
        } catch (TimeoutException | NoSuchElementException e) {
            ExtentManager.getTest().info("Follow Us popup did not appear. Continuing test execution.");
        }
    }
    
    /** Returns Second Quiz card element */
    public WebElement getSecondQuizCard() {
        return wait.until(ExpectedConditions.visibilityOf(secondQuizCard));
    }  

    /** Clicks "Undo" toast button after deleting the Quiz */
    public void clickUndoToastButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(undoToastButton));
        button.click();
    }
    
    /** Returns full Undo toast (message + button) */
    public String getFullUndoToastMessage() {
        String message = wait.until(ExpectedConditions.visibilityOf(undoToastMessageText)).getText();
        String undoText = wait.until(ExpectedConditions.visibilityOf(undoToastButton)).getText();
        return message + " " + undoText;
    }
     
    /** Returns total responses count from More options */
    public int getTotalAnswersCountInMore() {
     // Wait until the element is visible
        WebElement element = wait.until(ExpectedConditions.visibilityOf(totalAnswersInMore));
        
     // Get the text (e.g., "12") and trim spaces
        String countText = element.getText().trim();
        
     // Convert text to integer and return
        return Integer.parseInt(countText);
    }
       
    /** Selects first quiz - circle */
    public void clickFirstQuizSelectionCircle() {
        wait.until(ExpectedConditions.elementToBeClickable(firstQuizSelection)).click();
    }

    /** Selects second quiz - circle */
    public void clickSecondQuizSelectionCircle() {
        wait.until(ExpectedConditions.elementToBeClickable(secondQuizSelection)).click();
    } 
    
    /** Clicks Delete in selection mode */
    public void clickDeleteButtonQuizSelection() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteButtonQuizSelection)).click();
    }

    /** Confirms Delete button in selection mode */
    public void clickConfirmDeleteButtonQuizSelection() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButtonQuizSelection)).click();
    }
    
    /** Returns "No Quiz Available" element */
    public WebElement getNoQuizAvailable() {
        wait.until(ExpectedConditions.visibilityOf(noQuizAvailable));
        return noQuizAvailable;
    }
    
    /** Returns quiz edit warning popup title (With Answers Quiz) */
    public WebElement getQuizEditWarningPopupTitle() {
        wait.until(ExpectedConditions.visibilityOf(quizEditWarningPopupTitle));
        return quizEditWarningPopupTitle;
    }

    /** Returns quiz edit warning popup message */
    public WebElement getQuizEditWarningMessage() {
        wait.until(ExpectedConditions.visibilityOf(quizEditWarningMessage));
        return quizEditWarningMessage;
    }

    /** Clicks Edit button on warning popup */
    public void clickEditButtonOnWarningPopup() {
        wait.until(ExpectedConditions.elementToBeClickable(editButtonOnWarningPopup)).click();
    } 
    
    /** Returns true if Published icon is visible */
    public boolean isPublishedIconVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(publishedIconOnFirstQuizCard));
            return publishedIconOnFirstQuizCard.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
     
    /** Refreshes the browser */
    public void refreshPage() {
        driver.navigate().refresh();
    }


    
  
    
    
    


}

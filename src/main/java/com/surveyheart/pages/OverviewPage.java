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


public class OverviewPage {
    private WebDriver driver;
    private WebDriverWait wait;

    /** Constructor initializes PageFactory elements and explicit wait */
    public OverviewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }
    
    
 // ===================== Web Elements with @FindBy =====================

    /** Overview button on Overview page */
    @FindBy(xpath = "//span[normalize-space()='Overview']")
    private WebElement overViewButton;
    
    /** Total responses count displayed in Overview page */
    @FindBy(xpath = "//div[@id='summary-response-count-box']")
    private WebElement totalResponsesCountInOverview;

    /** Individual button */
    @FindBy(xpath = "//span[normalize-space()='Individual']")
    private WebElement individualButton;

    /** Tabular button */
    @FindBy(xpath = "//span[normalize-space()='Tabular']")
    private WebElement tabularButton;

    /** Download button */
    @FindBy(xpath = "//span[normalize-space()='Download']")
    private WebElement downloadButton;

    /** Delete button in Individual page */
    @FindBy(xpath = "//div[@id='Delete']")
    private WebElement deleteButtonIndividual;

    /** Delete button in confirmation Delete popup  */
    @FindBy(xpath = "//label[normalize-space()='DELETE']")
    private WebElement deleteButtonOnDeletePopup;
      
    /** First text response in Overview page */
    @FindBy(xpath = "//p[@class='summary-text-response-text']")
    private WebElement firstQuestionResponse;
    
    /** Undo button in toast message after deleting Form response */
    @FindBy(xpath = "//a[normalize-space()='Undo']")
    private WebElement undoToastButton;
    
    /** Undo toast message text */
    @FindBy(xpath = "//p[@class='response-undo-message']")
    private WebElement undoToastMessageText;
    
    /** First response checkbox in the first row of the Tabular page */
    @FindBy(xpath = "//tbody/tr[1]/td[1]/div[1]/div[1]/input[1]")
    private WebElement firstResponseCheckbox;
    
    /** Search bar in Tabular tab */
    @FindBy(xpath = "//input[@placeholder='Search']")
    private WebElement tabularSearchBar;
    
    /** First searched response text in Tabular page */
    @FindBy(xpath = "//p[@id='table-text-cell-container-p']")
    private WebElement searchedFirstResponse;
    
    /** PDF file icon in Download as popup */
    @FindBy(xpath = "//img[@alt='pdf']")
    private WebElement pdfFileOnDownloadPopup;
    
    /** Download PDF button after clicked on PDF file */
    @FindBy(xpath = "//div[@id='Download PDF']")
    private WebElement downloadPDFButton; 
    
    /** Date and time of a single response in Individual page */
    @FindBy(xpath = "//div[@class='response-count-container']//span[@class='responded-time']")
    private WebElement responseDateTime;
    
    /** Next response arrow icon in Individual page */
    @FindBy(xpath = "//img[@alt='next-button']")
    private WebElement nextResponseArrow;
    
    
    
 // ========== Action Methods with WebDriverWait  ==========
    
    /** Clicks the Overview button after waiting for it to be clickable. */
    public void clickOverviewTab() {
        wait.until(ExpectedConditions.elementToBeClickable(overViewButton)).click();
    }
    
    /** Gets the total responses count in the Overview page as an integer. */
    public int getTotalResponsesCount() {
        String countText = wait.until(ExpectedConditions.visibilityOf(totalResponsesCountInOverview)).getText();
        return Integer.parseInt(countText.trim());
    }

    /** Clicks the Individual button */
    public void clickIndividualTab() {
        wait.until(ExpectedConditions.elementToBeClickable(individualButton)).click();
    }

    /** Clicks the Tabular button. */
    public void clickTabularTab() {
        wait.until(ExpectedConditions.elementToBeClickable(tabularButton)).click();
    }

    /** Clicks the Download button in Overview page. */
    public void clickDownloadButton() {
        wait.until(ExpectedConditions.elementToBeClickable(downloadButton)).click();
    }

    /** Clicks the Delete button in Individual page. */
    public void clickDeleteIndividualButton() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteButtonIndividual)).click();
    }

    /** Confirms the Delete popup by clicking Delete button. */
    public void confirmDeletePopup() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteButtonOnDeletePopup)).click();
    }
    
    /** Waits until the first question response is visible and returns it. */
    public WebElement getFirstQuestionResponse() {
        return wait.until(ExpectedConditions.visibilityOf(firstQuestionResponse));
    }

    /** Gets the text of the first question response. */
    public String getFirstQuestionResponseText() {
        return getFirstQuestionResponse().getText();
    }       // Till here first question method

    /** Clicks Undo Toast button after deleting form response. */
    public void clickUndoToastButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(undoToastButton));
        button.click();
    }
    
    /** Returns full Undo Toast message (toast text + UNDO button text). */
    public String getFullUndoToastMessage() {
        String message = wait.until(ExpectedConditions.visibilityOf(undoToastMessageText)).getText();
        String undoText = wait.until(ExpectedConditions.visibilityOf(undoToastButton)).getText();
        return message + " " + undoText;
    }
    
    /** Selects the first checkbox in the Tabular page. */
    public void selectFirstCheckboxInTabular() {
        WebElement checkbox = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody/tr[1]/td[1]/div[1]/div[1]/input[1]"))
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
    }   
    
    /** Selects multiple responses by row numbers in Tabular screen (e.g., selectResponses(1,2,3) selects rows 1, 2, 3) */
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
    
    /** Enters response text in Tabular search bar and presses ENTER. */
    public void searchResponseBar(String responseText) {
        WebElement searchBar = wait.until(ExpectedConditions.visibilityOf(tabularSearchBar));
        searchBar.clear();
        searchBar.sendKeys(responseText);
        searchBar.sendKeys(Keys.ENTER);           // Press ENTER to trigger filtering
    }
    
    /** Gets the first response displayed after searching in Tabular page. */
    public WebElement getFirstSearchedResponse() {
        return wait.until(ExpectedConditions.visibilityOf(searchedFirstResponse));
    } 
   
    /** Clicks PDF file option in Download as popup. */
    public void clickPdfFileOnDownloadPopup() {
        wait.until(ExpectedConditions.elementToBeClickable(pdfFileOnDownloadPopup)).click();
    }
    
    /** Clicks Download PDF button after clicked on PDF file */
     public void clickDownloadPDFButton() {
        wait.until(ExpectedConditions.elementToBeClickable(downloadPDFButton)).click();
     }    
     
     /** Gets the response Date-Time element in Individual page. */
     public WebElement getResponseDateTimeInIndividual() {
         return wait.until(ExpectedConditions.visibilityOf(responseDateTime));
     }
    
     /** Clicks the next response arrow in Individual page. */
     public void clickNextResponseArrow() {
         wait.until(ExpectedConditions.elementToBeClickable(nextResponseArrow)).click();
     }       

     /** Checks if Delete button is displayed on Individual page (for Editor role). */
     public boolean isDeleteButtonDisplayedInIndividual() {	 
         try {
          // Temporarily set implicit wait to 0
             driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
             boolean isDisplayed = deleteButtonIndividual.isDisplayed();
             return isDisplayed;
         } catch (NoSuchElementException e) {
             return false;                                // safely handle when element is not present
         } finally {
          // Restore implicit wait back to original value (e.g., 10 seconds)
             driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
         }
     }
     
     
  
     
     
     
     
     
    
}

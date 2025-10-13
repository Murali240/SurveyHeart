package com.surveyheart.pages;


import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.surveyheart.utilities.ExtentManager;


public class FormDashboardPage {
	

    /** WebDriver instance to interact with the browser */
	private WebDriver driver;

    /** WebDriverWait instance for applying explicit waits on elements */
	private WebDriverWait wait;
	
    /** Add this line for - while clicking on View Form button from More page */
    private String parentWindowHandle;

    /** Initializes driver, wait, and @FindBy elements for FormDashboardPage. */
	public FormDashboardPage(WebDriver driver) {
	 // Assigning the WebDriver instance received from the test class to the local driver variable
	    this.driver = driver;

	 // Initializing WebDriverWait with a timeout of 60 seconds for waiting on specific conditions (like element visibility or clickability)
	    this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	 // Initializing all @FindBy annotated WebElements in this page class using PageFactory
	    PageFactory.initElements(driver, this);
	}
	
	
	// ===================== Web Elements with @FindBy =====================
    
	/** 1st Form card title */
    @FindBy(xpath = "//div[@id='list-card-title-0']//p[@id='card-form-title']")
    private WebElement firstFormTitle;

    /** +Create Form button */
    @FindBy(xpath = "//span[text()='Create Form']")
    private WebElement formsButton;

    /** Quizzes tab for navigated to Quiz Dashboard */
    @FindBy(xpath = "//span[text()='Quizzes']")
    private WebElement quizzesTab;

    /** Shared tab */
    @FindBy(xpath = "//span[normalize-space()='Shared']")
    private WebElement sharedTab;

    /** Templates tab */
    @FindBy(xpath = "//span[contains(text(),'Templates')]")
    private WebElement templatesTab;

    /** More dropdown toggle */
    @FindBy(xpath = "//span[normalize-space()='More']")
    private WebElement moreDropdownToggle;

    /** Header - Account/Profile button */
    @FindBy(xpath = "//span[@class='profile-initial']")
    private WebElement accountButton;

    /** Header - Sign-out button */
    @FindBy(xpath = "//div[@id='Sign Out']")
    private WebElement signoutButton;

    /** Header - SurveyHeart Logo */
    @FindBy(xpath = "//img[@alt='survey-heart-icon']")
    private WebElement surveyHeartLogo;

    /** Header - Notification icon */
    @FindBy(xpath = "//img[@src='./images/notification.svg']")
    private WebElement notificationButton;

    /** Header - Language/localization icon */
    @FindBy(xpath = "//span[@class='localization-icon-span']")
    private WebElement languageButton;
  
    /** Current plan label - Account level */
    @FindBy(xpath = "//span[normalize-space()='FREE']")
    private WebElement userCurrentPlan;

    /** Storage used info */
    @FindBy(xpath = "//span[contains(text(),'of 1GB Used')]")
    private WebElement storage;

    /** Total submissions info */
    @FindBy(xpath = "//span[contains(text(),'of 10000 Used')]")
    private WebElement totalSubmissions;

    /** Image attachments usage */
    @FindBy(xpath = "(//span[contains(text(),'of') and contains(text(),'Used') and contains(text(),'50')])[1]")
    private WebElement imageAttachments;

    /** Custom themes usage */
    @FindBy(xpath = "//div[contains(text(),'Themes') or contains(.,'Themes')]/span[contains(text(),'of 50 Used')]")
    private WebElement customThemes;

    /** Upgrade your plan button - Account inside only */
    @FindBy(xpath = "//div[@id='UPGRADE YOUR PLAN']")
    private WebElement upgradeButton;   
    
    /** More menu - Manage Storage */
    @FindBy(xpath = "//span[normalize-space()='Manage Storage']")
    private WebElement manageStorageButton;

    /** More menu - Email Support */
    @FindBy(xpath = "//span[normalize-space()='Email Support']")
    private WebElement mailSupport;

    /** More menu - Call Support */
    @FindBy(xpath = "//span[normalize-space()='Call Support']")
    private WebElement callSupport;

    /** More menu - Responses Add-on */
    @FindBy(xpath = "//span[normalize-space()='Responses Add-on']")
    private WebElement responseAddOnButton;

    /** More menu - Feedback button */
    @FindBy(xpath = "//span[normalize-space()='Feedback']")
    private WebElement feedbackButton;

    /** More menu - Privacy Policy */
    @FindBy(xpath = "//span[normalize-space()='Privacy Policy']")
    private WebElement privacyPolicyButton;

    /** More menu - Follow Us button */
    @FindBy(xpath = "//span[normalize-space()='Follow Us']")  
    private WebElement followUsButton;
    
    /** Follow Us popup (title after 5 times refresh the page) */
    @FindBy(xpath = "//div[@class='localization-header']//div[1]")  
    private WebElement followUsPopupTitle;
    
  
    /** Premium warning popup */
    @FindBy(xpath = "//div[contains(text(),'PREMIUM_FEATURES')]")
    private WebElement premiumWarningPopup;

    /** Premium popup - View Plans button */
    @FindBy(xpath = "//label[normalize-space()='VIEW PLANS']")
    private WebElement viewPlansButton;  

    /** Premium popup - Close button */
    @FindBy(xpath = "//img[@class='localization-close-img']")
    private WebElement closePremiumPlansPopup;

    /** Premium plans page title */
    @FindBy(xpath = "//span[contains(text(),'Premium plans')]")
    private WebElement premiumPlansPageTitle;
    
    /** Form Dashboard - Total forms count */
    @FindBy(xpath = "//span[contains(text(),'(') and contains(text(),')')]")
    private WebElement totalFormsCountText;
    
    /** Form card - Title of 1st form card */
    @FindBy(xpath = "//div[@id='list-card-title-0']//p[@id='card-form-title']")
    private WebElement firstFormCardTitle;

    /** More options (3-dots menu) for first Form card */
    @FindBy(xpath = "//img[@id='more0']")
    private WebElement firstFormMoreOptions;
    
    /** More options (3-dots menu) for second Form card */
    @FindBy(xpath = "//img[@id='more1']")
    private WebElement secondFormMoreOptions;
    
    /** Status switch button (Active/Inactive toggle) */
    @FindBy(xpath = "//img[@alt='status-switch']")
    private WebElement formStatusSwitchButton;
    
    /** Form card - View Responses button */
    @FindBy(xpath = "//p[@id='dashboard-view-responses']")
    private WebElement viewResponsesButton;
    
    /** Form card - Edit Form button from More options */
    @FindBy(xpath = "//div//p[text()='Edit Form']")
    private WebElement editFormButton;

    /** Form card - View Form button from More options */
    @FindBy(xpath = "//p[normalize-space()='View Form']")
    private WebElement viewFormButton;

    /** Form card - Duplicate Form button from More options */
    private By duplicateFormButton = By.xpath("//p[normalize-space()='Duplicate Form']");
    
    /** Form card - Delete Form button from More options */
    @FindBy(xpath = "//div//p[text()='Delete Form']")
    private WebElement deleteButton;

    /** Delete Form - Confirm delete button */
    @FindBy(xpath = "//label[normalize-space()='DELETE']")
    private WebElement confirmDeleteButton;
   
    /** Dashboard - Search input box */
    @FindBy(xpath = "//input[@placeholder='Search']")
    private WebElement searchInput;

    /** Form card - Title of 2nd Form card */
    @FindBy(xpath = "//div[@id='list-card-title-1']//p[@id='card-form-title']")
    private WebElement secondFormCard;
    
    /** Undo toast message - Undo button after deleting the Form card */
    @FindBy(xpath = "//a[normalize-space()='Undo']")
    private WebElement undoToastButton;
    
    /** Undo toast message - Text */
    @FindBy(xpath = "//p[@class='response-undo-message']")
    private WebElement undoToastMessageText;
    
    /** Form More options - Total responses count inside more */
    @FindBy(xpath = "//div[@id='dashboard-view-responses-count']")
    private WebElement totalResponsesInMore;
    
    /** 1st Form card - First Form selection checkbox */
    @FindBy(xpath = "//div[@id='form-card-0']//div//div[@class='multiple-select-tag-for-delete-in-hover-list-view']")
    private WebElement firstFormSelection;

    /** 2nd Form card - Second Form selection checkbox */
    @FindBy(xpath = "//div[@id='form-card-1']//div[@class='multiple-select-layer']")
    private WebElement secondFormSelection;
    
    /** Multi-form selection - Delete button */
    @FindBy(xpath = "//div[@id='Delete']")
    private WebElement deleteButtonFormSelection;

    /** Multi-form selection - Confirm delete button on Delete popup */
    @FindBy(xpath = "//label[starts-with(normalize-space(),'DELETE (')]")
    private WebElement confirmDeleteButtonFormSelection;
    
    /** No form available (first time login user) */
    @FindBy(xpath = "//h3[normalize-space()='No Form Available!']")
    private WebElement noFormAvailable;
    
    /** Form Edit Warning popup - Title (With Form Responses) */
    @FindBy(xpath = "//div[@class='modal-dialog-header']")
    private WebElement formEditWarningPopupTitle;

    /** Form Edit Warning popup - Message (With Form Responses) */
    @FindBy(xpath = "//div[@class='modal-dialog-body-line']")
    private WebElement formEditWarningMessage;

    /** Form Edit Warning popup - Edit button (With Form Responses) */
    @FindBy(xpath = "//label[normalize-space()='EDIT']")
    private WebElement editButtonOnWarningPopup;



    // ========== Action Methods with WebDriverWait ==========
    
    /** Clicks first Form card */
    public void clickFirstFormCard() {
        wait.until(ExpectedConditions.elementToBeClickable(firstFormTitle)).click();
    }
    
    /** Clicks +Create Form button */
    public void clickCreateFormButton() {
        wait.until(ExpectedConditions.elementToBeClickable(formsButton)).click();
    }

    /** Clicks Quizzes tab */
    public void clickQuizzesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(quizzesTab)).click();
    }

    /** Clicks Shared tab */
    public void clickSharedTab() {
        wait.until(ExpectedConditions.elementToBeClickable(sharedTab)).click();
    }

    /** Clicks Templates tab */
    public void clickTemplatesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(templatesTab)).click();
    }

    /** Clicks More dropdown */
    public void clickMoreDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(moreDropdownToggle)).click();
    }

    /** Clicks Account button */
    public void clickAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(accountButton)).click();
    }

    /** Clicks Signout button */
    public void clickSignoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signoutButton)).click();
    }

    /** Logs SurveyHeart Logo in Extent Report */
    public void logSurveyHeartLogo() {
     // Wait for logo to be visible
        wait.until(ExpectedConditions.visibilityOf(surveyHeartLogo));

     // Fetch the logo image source URL
        String logoSrc = surveyHeartLogo.getAttribute("src");

     // Log the logo as an embedded image in Extent Report
        ExtentManager.getTest().pass(
            "SurveyHeart Logo is displayed below:<br><img src='" + logoSrc + "' width='100' height='100'>"
        );
    } 

    /** Clicks Notification button */
    public void clickNotificationButton() {
        wait.until(ExpectedConditions.elementToBeClickable(notificationButton)).click();
    }

    /** Clicks Language button */
    public void clickLanguageButton() {
        wait.until(ExpectedConditions.elementToBeClickable(languageButton)).click();
    }

    /** Clicks Upgrade button - Account inside only */
    public void clickUpgradeButton() {
        wait.until(ExpectedConditions.elementToBeClickable(upgradeButton)).click();
    }

    /** Returns current user plan text */
    public String getUserCurrentPlanText() {
        return wait.until(ExpectedConditions.visibilityOf(userCurrentPlan)).getText();
    }

    /** Returns storage info text */
    public String getStorageText() {
        return wait.until(ExpectedConditions.visibilityOf(storage)).getText();
    }

    /** Returns total submissions text */
    public String getTotalSubmissionsText() {
        return wait.until(ExpectedConditions.visibilityOf(totalSubmissions)).getText();
    }

    /** Returns image attachments text */
    public String getImageAttachmentsText() {
        return wait.until(ExpectedConditions.visibilityOf(imageAttachments)).getText();
    }

    /** Returns custom themes text */
    public String getCustomThemesText() {
        return wait.until(ExpectedConditions.visibilityOf(customThemes)).getText();
    }

    /** Clicks Manage Storage */
    public void clickManageStorage() {
        wait.until(ExpectedConditions.elementToBeClickable(manageStorageButton)).click();
    }

    /** Clicks Mail Support */
    public void clickMailSupport() {
        wait.until(ExpectedConditions.elementToBeClickable(mailSupport)).click();
    }

    /** Clicks Call Support */
    public void clickCallSupport() {
        wait.until(ExpectedConditions.elementToBeClickable(callSupport)).click();
    }

    /** Verifies Premium Popup flow */
    public void verifyPremiumPopupFlow(String userType, String featureName) {
        ExtentTest test = ExtentManager.getTest();

        String warningText = wait.until(ExpectedConditions.visibilityOf(premiumWarningPopup)).getText();
        test.pass("For " + userType + " user, " + featureName + " is displayed with: " + warningText + " warning popup");

        wait.until(ExpectedConditions.elementToBeClickable(viewPlansButton)).click();

        String title = wait.until(ExpectedConditions.visibilityOf(premiumPlansPageTitle)).getText();
        test.pass("View Plans button navigated to: " + title + " page");

        wait.until(ExpectedConditions.elementToBeClickable(closePremiumPlansPopup)).click();
        test.pass("Premium Plans page closed successfully.");    							  // till from here, Mail & Call Support
    }
    
    /** Clicks Follow Us */
    public void clickFollowUs() {      
        wait.until(ExpectedConditions.elementToBeClickable(followUsButton)).click();
    }

    /** Clicks social icon and returns URL */
    public String clickAndFetchSocialURL(String iconXPath) {
        String parentWindow = driver.getWindowHandle();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(iconXPath))).click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        Set<String> allWindows = driver.getWindowHandles();
        for (String win : allWindows) {
            if (!win.equals(parentWindow)) {
                driver.switchTo().window(win);
                break;
            }
        }

        String url = driver.getCurrentUrl();
        driver.close();                             // Close child
        driver.switchTo().window(parentWindow);     // Return to parent

        return url;
    }						
       
    /** Returns Follow Us popup title */
    public WebElement getFollowUsPopupTitle() {
        wait.until(ExpectedConditions.visibilityOf(followUsPopupTitle));
        return followUsPopupTitle;
    }
    
    /** Clicks Response Add-On */
    public void clickResponseAddOn() {
        wait.until(ExpectedConditions.elementToBeClickable(responseAddOnButton)).click();
    }

    /** Clicks Feedback */
    public void clickFeedback() {
        wait.until(ExpectedConditions.elementToBeClickable(feedbackButton)).click();
    }
    
    /** Clicks Privacy Policy and returns URL */
    public String clickPrivacyPolicyAndGetURL() {
        wait.until(ExpectedConditions.visibilityOf(moreDropdownToggle)).click();
        wait.until(ExpectedConditions.visibilityOf(privacyPolicyButton)).click();

     // Handle new tab
        String parentWindow = driver.getWindowHandle();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> allWindows = driver.getWindowHandles();
        for (String win : allWindows) {
            if (!win.equals(parentWindow)) {
                driver.switchTo().window(win);
                break;
            }
        }

        return driver.getCurrentUrl();
    }					  				   // till here, Privacy policy   
    
    /** Returns total number of forms (e.g., from "Forms (12)" → 12) */
    public int getTotalFormsCount() {
        try {
            wait.until(ExpectedConditions.visibilityOf(totalFormsCountText));
            String text = totalFormsCountText.getText();                        // e.g., "Forms (12)"
            String count = text.replaceAll("[^0-9]", "");                       // Extract digits only
            return Integer.parseInt(count);
        } catch (Exception e) {
            return -1;                       // Return -1 if element not found or parsing fails
        }
    }
  
    /** Clicks First Form card title */
    public void clickFirstFormCardTitle() {
        wait.until(ExpectedConditions.visibilityOf(firstFormCardTitle)).click();
    }    
    
    /** Clicks More Options for First Form */
    public void clickMoreOptionsForFirstForm() {
        wait.until(ExpectedConditions.elementToBeClickable(firstFormMoreOptions)).click();
    }
    
    /** Clicks More Options for Second Form */ 
    public void clickSecondFormMoreOptions() {
        wait.until(ExpectedConditions.elementToBeClickable(secondFormMoreOptions)).click();
    }
    
    /** Clicks View Responses from More options */
    public void clickViewResponsesButton() {
        wait.until(ExpectedConditions.elementToBeClickable(viewResponsesButton)).click();
    }

    /** Clicks Edit Form from More options */
    public void clickEditForm() {
        wait.until(ExpectedConditions.elementToBeClickable(editFormButton)).click();
    }  
    
    /** Clicks Duplicate Form from More options */
    public void clickDuplicateFormButton() {
        wait.until(ExpectedConditions.elementToBeClickable(duplicateFormButton)).click();
    }  
    
    /** Clicks Delete Form from More options */
    public void clickDeleteButton() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton)).click();
    }

    /** Clicks Confirm Delete button on Delete popup */
    public void clickConfirmDeleteButton() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton)).click();
    }
    
    /** Returns First Form card title */ 
    public String getFirstFormCardTitle() {
      return wait.until(ExpectedConditions.visibilityOf(firstFormCardTitle)).getText();
    } 
    
    /** Returns and validates Duplicated Form title after Form duplicated */
    public String getFirstFormCardTitleAndValidate(String expectedTitle) {
        String actualTitle = wait.until(ExpectedConditions.visibilityOf(firstFormCardTitle)).getText();

        if (expectedTitle != null && !expectedTitle.isEmpty()) {
            Assert.assertEquals(actualTitle, expectedTitle, "Duplicated form title does not match expected title");
            ExtentManager.getTest().pass("✔ The duplicated form title matches on the form dashboard : " + actualTitle);
        }
        
        return actualTitle;
    }   
    
    /** Toggles form status switch from More options ( Active <-> Inactive) */
    public void clickFormStatusSwitch() {
        wait.until(ExpectedConditions.elementToBeClickable(formStatusSwitchButton)).click();
    }

    /** Clicks View Form from More options */
    public void clickViewFormButton() {
        wait.until(ExpectedConditions.elementToBeClickable(viewFormButton)).click();
    }

    /** Stores parent window handle */
    public void storeParentWindowHandle() {
        this.parentWindowHandle = driver.getWindowHandle();
    }

    /** Returns parent window handle */
    public String getParentWindowHandle() {
        return this.parentWindowHandle;
    }

    /** Switches to child window after clicking View Form */
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

    /** Searches Form by title */
    public void searchFormByTitle(String formTitle) {
        wait.until(ExpectedConditions.visibilityOf(searchInput)).clear();
        searchInput.sendKeys(formTitle);
    } 
    
    /** Closes Follow Us popup if present */
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
   
    /** Returns Second Form card */
    public WebElement getSecondFormCard() {
        return wait.until(ExpectedConditions.visibilityOf(secondFormCard));
    }
    
    /** Clicks Undo toast button after deleting the Form */
    public void clickUndoToastButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(undoToastButton));
        button.click();
    }
    
    /** Returns Undo toast message */
    public String getFullUndoToastMessage() {
        String message = wait.until(ExpectedConditions.visibilityOf(undoToastMessageText)).getText();
        String undoText = wait.until(ExpectedConditions.visibilityOf(undoToastButton)).getText();
        return message + " " + undoText;
    }  
    
    /** Returns total responses count from More options */
    public int getTotalResponsesCountInMore() {
     // Wait until the element is visible
        WebElement element = wait.until(ExpectedConditions.visibilityOf(totalResponsesInMore));
        
     // Get the text (e.g., "12") and trim spaces
        String countText = element.getText().trim();
        
     // Convert text to integer and return
        return Integer.parseInt(countText);
    } 
    
    /** Selects first form - circle */
    public void clickFirstFormSelectionCircle() {
        wait.until(ExpectedConditions.elementToBeClickable(firstFormSelection)).click();
    }

    /** Selects second form - circle */
    public void clickSecondFormSelectionCircle() {
        wait.until(ExpectedConditions.elementToBeClickable(secondFormSelection)).click();
    } 
    
    /** Clicks Delete (Form selection) */
    public void clickDeleteButtonFormSelection() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteButtonFormSelection)).click();
    }

    /** Clicks Confirm Delete button (Form selection) */    
    public void clickConfirmDeleteButtonFormSelection() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButtonFormSelection)).click();
    }   
    
    /** Returns 'No Form Available' element */
    public WebElement getNoFormAvailable() {
        wait.until(ExpectedConditions.visibilityOf(noFormAvailable));
        return noFormAvailable;
    }  
    
    /** Returns Form Edit warning popup title from More options (With Form Responses) */
    public WebElement getFormEditWarningPopupTitle() {
        wait.until(ExpectedConditions.visibilityOf(formEditWarningPopupTitle));
        return formEditWarningPopupTitle;
    }

    /** Returns Form Edit warning message */
    public WebElement getFormEditWarningMessage() {
        wait.until(ExpectedConditions.visibilityOf(formEditWarningMessage));
        return formEditWarningMessage;
    }

    /** Clicks Edit button in warning popup */
    public void clickEditButtonOnWarningPopup() {
        wait.until(ExpectedConditions.elementToBeClickable(editButtonOnWarningPopup)).click();
    }  
    
    /** Refreshes browser */
    public void refreshPage() {
        driver.navigate().refresh();
    }
    
 

    
    
 
    

}
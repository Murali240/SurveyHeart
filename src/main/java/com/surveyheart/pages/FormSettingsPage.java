package com.surveyheart.pages;



import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.surveyheart.utilities.ExtentManager;


public class FormSettingsPage {

	private WebDriver driver;
    private WebDriverWait wait;

    /** Constructor to initialize driver and wait for FormSettingsPage. */
    public FormSettingsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    
    // ----------- Settings Web Elements -----------

    private By settingsButton = By.xpath("//span[normalize-space()='Settings']");
    private By showLogoCheckbox = By.xpath("//div[@id='settings-view-box9']//input[@type='checkbox']");
    private By welcomePageCheckbox = By.xpath("//div[@id='settings-view-box17']//input[@type='checkbox']");
    private By estimatedTimeCheckbox = By.xpath("//div[@id='settings-view-box23']//input[@type='checkbox']");
    private By showQuestionCountCheckbox = By.xpath("//div[@id='settings-view-box26']//input[@type='checkbox']");
    private By passcodeProtectionCheckbox = By.xpath("//div[@id='settings-view-box29']//input[@type='checkbox']");
    private By confirmationMessage = By.xpath("//div[contains(text(),'Confirmation Message')]");
    private By redirectToDesiredLink = By.xpath("//div[contains(text(),'Redirect to desired link')]");

    // ----------- Control Tab Elements -----------

    private By controlTab = By.xpath("//span[normalize-space()='Control']");
    private By userInfoCheckbox = By.xpath("//div[@id='settings-quiz-control-wrapper']//input[@type='checkbox']");
    @FindBy(xpath = "//div[@id='settings-responses-box5']")
    private WebElement allowMultipleResponsesCheckbox;
    private By showSummaryCheckbox = By.xpath("//div[@id='settings-responses-box11']//input[@type='checkbox']");
    private By collectEmailCheckbox = By.xpath("//div[@id='settings-responses-box14']//input[@type='checkbox']");
    private By allowEditResponseCheckbox = By.xpath("//div[@id='settings-responses-box17']//div[@id='mdc-checkbox']");
    private By showQuestionNumberCheckbox = By.xpath("//div[3]//div[2]//div[1]//div[1]//div[1]//input[1]");
    private By shuffleQuestionsCheckbox = By.xpath("//div[@id='settings-responses-and-questions-box5']//input[@type='checkbox']");

    // ----------- Collaboration Tab Elements -----------

    private By collaborationTab = By.xpath("//span[normalize-space()='Collaboration']");
    private By addCollaboratorButton = By.xpath("//div[@id='Add Collaborator']");
    private By emailBox = By.xpath("//input[@type='email']");
    private By roleDropdown = By.xpath("//div[@class='mdc-select mdc-select--outlined']//div[@class='mdc-select__anchor']");
    private By adminRoleOption = By.xpath("//li[@class='mdc-list-item mdc-list-item--selected']");
    private By editorRoleOption = By.xpath("//li[@data-value='Editor']");
    private By viewerRoleOption = By.xpath("//li[@data-value='Viewer']");
    private By addButton = By.xpath("//div[@id='Add']");
    
   // private By roleDropdownInModal = By.xpath("//div[@class='mdc-select mdc-select--outlined']//div[@class='mdc-select__anchor']");  
    
 // === THEME SECTION ELEMENTS ===

    /** Default theme image in Themes section - View tab */
    @FindBy(xpath = "(//img[@alt='classic_new.jpeg'])[1]")
    private WebElement defaultThemeImage;

    /** + My Themes button */
    @FindBy(xpath = "//img[@src='images/add_white.png']")
    private WebElement addMyThemesButton;

    /** + Add image button inside Themes */
    @FindBy(xpath = "//img[@alt='/images/add_grey.svg']")
    private WebElement addImageButton;

    /** File input for uploading theme image */
    @FindBy(xpath = "//input[@type='file']")
    private WebElement fileInputImage;

    /** Confirm tick icon after uploading */
    @FindBy(xpath = "//img[@alt='tick mark']")
    private WebElement confirmTickIcon; 
    
    // ================== Deleting custom theme ==================
    
    /** Added custom theme button - View tab */
    @FindBy(xpath = "//div[@id='theme_card_undefined']//img[@class='theme-image']")
    private WebElement addedThemeButton;
    
    /** Added My Themes button */
    @FindBy(css = "div[id='theme_card_0'] div div[class='theme-round center']")
    private WebElement addedMyThemesButton;
    
    /** Delete icon for existed custom theme */
    @FindBy(xpath = "//body/div[@id='app']/div/div[@id='app-container']/div[@class='body-container']/div[@class='builder-wrapper']/div[@id='theme-container']/div[@id='default-theme-container']/div[2]/div[1]/img[1]")
    private WebElement addedDeleteThemeIcon;

    /** Confirm delete button on Delete theme popup */
    @FindBy(xpath = "//label[normalize-space()='Delete']")
    private WebElement confirmDeleteButton;

    /** Close theme popup button */
    @FindBy(xpath = "//img[@src='images/close_black.svg']")
    private WebElement closeThemePopupButton;
    
 // ================== Validation messages ==================
    
    /** Form title required message (Without entering Form title) */
    @FindBy(xpath = "//div[@class='modal-dialog-header']")
    private WebElement formTitleRequiredMessage;

    /** Question title required message (Without entering Question title) */
    @FindBy(xpath = "//div[@class='modal-dialog-header']")
    private WebElement questionTitleRequiredMessage;

    //  ------------ Submit button -------------
    private By submitButton = By.xpath("//span[@class='icon-title'][normalize-space()='Submit']");


    
    // ----------- Action Methods with WebDriverWait -----------
    
    /** Clicks the Settings button using JS for reliability */
    public void clickSettingsButton() {
        WebElement settingsButtonElement = wait.until(ExpectedConditions.elementToBeClickable( (settingsButton)));
           
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", settingsButtonElement);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", settingsButtonElement);
    }

    /** Toggles a checkbox based on desired state */
    public void toggleCheckbox(By checkboxLocator, boolean enable) {
        WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(checkboxLocator));
        boolean isSelected = checkbox.isSelected();
        if ((enable && !isSelected) || (!enable && isSelected)) {
            checkbox.click();
        }
    }

    /** Clicks the Control tab in settings */
    public void clickControlTab() {
        wait.until(ExpectedConditions.elementToBeClickable(controlTab)).click();
    }

    /** Clicks the Collaboration tab in settings */
    public void clickCollaborationTab() {
        wait.until(ExpectedConditions.elementToBeClickable(collaborationTab)).click();
    }

    /** Enables/disables the Show Logo toggle */
    public void enableShowLogo(boolean enable) {
        toggleCheckbox(showLogoCheckbox, enable);
    }

    /** Enables/disables the Welcome Page toggle */
    public void enableWelcomePage(boolean enable) {
        toggleCheckbox(welcomePageCheckbox, enable);
    }

    /** Enables/disables the Estimated Time toggle */
    public void enableEstimatedTime(boolean enable) {
        toggleCheckbox(estimatedTimeCheckbox, enable);
    }

    /** Enables/disables the Show Question Count toggle */
    public void enableShowQuestionCount(boolean enable) {
        toggleCheckbox(showQuestionCountCheckbox, enable);
    }

    /** Enables/disables Passcode Protection toggle */
    public void enablePasscodeProtection(boolean enable) {
        toggleCheckbox(passcodeProtectionCheckbox, enable);
    }

    /** Enables/disables User Info toggle */
    public void enableUserInfo(boolean enable) {
        toggleCheckbox(userInfoCheckbox, enable);
    }
    
    /** "Allow Multiple Responses" checkbox, Enables or disables */

    public void enableAllowMultipleResponses(boolean enable) {
     // Wait until the checkbox is visible
        wait.until(ExpectedConditions.visibilityOf(allowMultipleResponsesCheckbox));

     // Get the 'class' attribute of the checkbox's wrapper element (which shows current toggle state)
        String classValue = allowMultipleResponsesCheckbox.getAttribute("class");

     // Check whether the toggle is currently ON (enabled) by seeing if 'checked' is in the class
        boolean isChecked = classValue.contains("checked");

     // Decide whether we need to click based on the current state vs desired state
     // If we want to enable but it's not enabled → click it
     // If we want to disable but it's currently enabled → click it
        if ((enable && !isChecked) || (!enable && isChecked)) {
         // Click the toggle switch to change its state
            allowMultipleResponsesCheckbox.click();
        }
    }
    
    /** Enables/disables Show Summary toggle */
    public void enableShowSummary(boolean enable) {
        toggleCheckbox(showSummaryCheckbox, enable);
    }

    /** Enables/disables Collect Email toggle */
    public void enableCollectEmail(boolean enable) {
        toggleCheckbox(collectEmailCheckbox, enable);
    }

    /** Enables the Allow Edit Response option */
    public void enableAllowEditResponse() {
        wait.until(ExpectedConditions.elementToBeClickable(allowEditResponseCheckbox)).click();
    }

    /** Enables/disables Show Question Number toggle */
    public void enableShowQuestionNumber(boolean enable) {
        toggleCheckbox(showQuestionNumberCheckbox, enable);
    }

    /** Enables/disables Shuffle Questions toggle */
    public void enableShuffleQuestions(boolean enable) {
        toggleCheckbox(shuffleQuestionsCheckbox, enable);
    }

    /** Opens the Confirmation Message option */
    public void clickConfirmationMessage() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmationMessage)).click();
    }

    /** Opens the Redirect to Desired Link option */
    public void clickRedirectToDesiredLink() {
        wait.until(ExpectedConditions.elementToBeClickable(redirectToDesiredLink)).click();
    }

    /** Adds a collaborator with the specified email and role (Admin, Editor, or Viewer) */
    public void addCollaborator(String email, String role) {
        wait.until(ExpectedConditions.elementToBeClickable(addCollaboratorButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailBox)).sendKeys(email);
        wait.until(ExpectedConditions.elementToBeClickable(roleDropdown)).click();

        switch (role.toLowerCase()) {
            case "admin":
                wait.until(ExpectedConditions.elementToBeClickable(adminRoleOption)).click();
                break;
            case "editor":
                wait.until(ExpectedConditions.elementToBeClickable(editorRoleOption)).click();
                break;
            case "viewer":
                wait.until(ExpectedConditions.elementToBeClickable(viewerRoleOption)).click();
                break;
            default:
                throw new IllegalArgumentException("Invalid role: " + role);
        }

        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
    }   
  
    /** Uploads and confirms a New Custom Theme */
    public void uploadNewCustomTheme(String themeImagePath) {
        
        // Select a default theme to make sure the theme panel is focused
           wait.until(ExpectedConditions.elementToBeClickable(defaultThemeImage)).click();

        // Click '+My Themes' button
           wait.until(ExpectedConditions.elementToBeClickable(addMyThemesButton)).click();

        // Click '+Add Image' button to upload custom theme
           wait.until(ExpectedConditions.elementToBeClickable(addImageButton)).click();

        // Upload file using hidden file input
           wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")))
                .sendKeys(themeImagePath);

        // Confirm upload by clicking tick icon
           wait.until(ExpectedConditions.visibilityOf(confirmTickIcon)).click();
       }
     
    /** Deletes an existed custom theme if present */
    public void deleteUploadedThemeIfPresent() {
        try {
         // Click Settings tab (wait and click)
            wait.until(ExpectedConditions.elementToBeClickable(addedThemeButton)).click();

         // Click +My Themes button
            wait.until(ExpectedConditions.elementToBeClickable(addedMyThemesButton)).click();

         // Click the X (delete) icon on uploaded theme
            wait.until(ExpectedConditions.elementToBeClickable(addedDeleteThemeIcon)).click();

         // Click DELETE on confirmation popup
            wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton)).click();

         // Close theme popup/folder
            wait.until(ExpectedConditions.elementToBeClickable(closeThemePopupButton)).click();
            
        } catch (TimeoutException | NoSuchElementException e) {
            ExtentManager.getTest().info("No uploaded theme image found to delete — continuing.");
        }
    } 

    /** Adds multiple collaborators in Admin → Editor → Viewer order.  collaborators map of email → role  */
    public void addCollaborators(Map<String, String> collaborators) {
        // Open Collaboration tab
        wait.until(ExpectedConditions.elementToBeClickable(collaborationTab)).click();

     // Force order regardless of Map iteration
        String[] roleOrder = { "Admin", "Editor", "Viewer" };

     // Scoped locators
        By emailInDialog      = By.xpath("//div[@id='add-collaborator']//input[@type='email']");
        By roleAnchorInDialog = By.xpath("//div[@id='add-collaborator']//div[contains(@class,'mdc-select__anchor')]");
        By openMenuSurface    = By.xpath("//div[contains(@class,'mdc-menu-surface--open')]");

        for (String targetRole : roleOrder) {
            for (Map.Entry<String, String> entry : collaborators.entrySet()) {
                String email = entry.getKey();
                String role  = entry.getValue() == null ? "" : entry.getValue().trim();

                if (!role.equalsIgnoreCase(targetRole)) {
                    continue; // skip until matching role
                }

             // --- Click Add Collaborator quickly ---
                driver.findElement(addCollaboratorButton).click();

             // --- Enter email quickly ---
                WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInDialog));
                emailInput.clear();
                emailInput.sendKeys(email);

             // --- Open dropdown ---
                driver.findElement(roleAnchorInDialog).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(openMenuSurface));

             // --- Pick role option ---
                String roleLabel = role.equalsIgnoreCase("admin") ? "Admin"
                                 : role.equalsIgnoreCase("editor") ? "Editor"
                                 : "Viewer";
                By optionLocator = By.xpath(
                    "//div[contains(@class,'mdc-menu-surface--open')]//span[normalize-space()='" + roleLabel + "']"
                );

                try {
                    driver.findElement(optionLocator).click();
                } catch (ElementClickInterceptedException e) {
                    WebElement opt = driver.findElement(optionLocator);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", opt);
                }

             // --- Click Add button ---
                driver.findElement(addButton).click();

             // --- Wait just enough until dialog resets (fast) ---
                try {
                    wait.until(ExpectedConditions.textToBePresentInElementValue(emailInDialog, ""));
                } catch (TimeoutException ignore) {
                    // ignore if dialog closes automatically
                }
            }
        }
    }  
    
    /** Waits for Form Title Required message and returns it */
    public WebElement getFormTitleRequiredMessage() {
        return wait.until(ExpectedConditions.visibilityOf(formTitleRequiredMessage));
    }

    /** Waits for Question Title Required message and returns it */
    public WebElement getQuestionTitleRequiredMessage() {
        return wait.until(ExpectedConditions.visibilityOf(questionTitleRequiredMessage));
    }
    
    /** Clicks the Submit button at the bottom of the settings page */
    public void clickSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }


    
    
      
    
    

}

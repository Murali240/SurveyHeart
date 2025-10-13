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


public class QuizSettingsPage {
	
    private WebDriver driver;
    private WebDriverWait wait;
  
    /** Constructor initializes PageFactory elements and explicit wait */
    public QuizSettingsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    
    }

    
    // ===================== Web Elements with @FindBy =====================
    
    // ===== Settings Tabs =====
    
    /** Settings main button */
    @FindBy(xpath = "//span[normalize-space()='Settings']")
    private WebElement settingsButton;

    /** Control tab inside Settings */
    @FindBy(xpath = "//span[normalize-space()='Control']")
    private WebElement controlTab;

    /** Collaboration tab inside Settings */
    @FindBy(xpath = "//span[normalize-space()='Collaboration']")
    private WebElement collaborationTab;
    

    // ===== Checkboxes in Control tab ===== 
    
    /** Checkbox: Allow multiple attempts in quiz */
    @FindBy(xpath = "//div[@id='settings-quiz-control-box11']//input[@type='checkbox']")
    private WebElement allowMultipleAttemptsCheckbox;

    /** Checkbox: Enable quiz duration */
    @FindBy(xpath = "//div[@id='settings-quiz-control-box7']//input[@type='checkbox']")
    private WebElement durationCheckbox;

    /** Checkbox: Collect email address from users */
    @FindBy(xpath = "//div[@id='settings-quiz-control-box14']//input[@type='checkbox']")
    private WebElement collectEmailAddressCheckbox;

    // ======== Theme ========
    
    /** Default theme card (Classic theme) */
    @FindBy(xpath = "//div[@id='']//img[@alt='classic_new.jpeg']")
    private WebElement defaultThemeCard;

    // ====== View tab checkboxes ======
    
    /** Checkbox: Show Logo on quiz */
    @FindBy(xpath = "//div[@id='settings-view-box9']//input[@type='checkbox']")
    private WebElement showLogoCheckbox;

    /** Checkbox: Show welcome page */
    @FindBy(xpath = "//div[@id='settings-view-box17']//input[@type='checkbox']")
    private WebElement welcomePageCheckbox;

    /** Checkbox: Show question count */
    @FindBy(xpath = "//div[@id='settings-view-box26']//input[@type='checkbox']")
    private WebElement showQuestionCountCheckbox;

    /** Checkbox: Enable passcode protection */
    @FindBy(xpath = "//div[@id='settings-view-box29']//input[@type='checkbox']")
    private WebElement passcodeProtectionCheckbox;

    // ===== Advanced Options =====
    
    /** Option: Custom confirmation message after quiz submission */
    @FindBy(xpath = "//div[contains(text(),'Confirmation Message')]")
    private WebElement confirmationMessage;

    /** Option: Redirect to desired link after quiz submission */
    @FindBy(xpath = "//div[contains(text(),'Redirect to desired link')]")
    private WebElement redirectToDesiredLink;

    /** Checkbox: Show quiz results after submission */
    @FindBy(xpath = "//div[@id='settings-allow-summary-view-checkbox']//input[@type='checkbox']")
    private WebElement showQuizResultsCheckbox;

    /** Checkbox: Show correct answers after submission */
    @FindBy(xpath = "//div[@id='settings-show-correct-answers-checkbox']//input[@type='checkbox']")
    private WebElement showCorrectAnswerCheckbox;

    /** Checkbox: Show question numbers */
    @FindBy(xpath = "//div[@id='settings-show-question-number-checkbox']//input[@type='checkbox']")
    private WebElement showQuestionNumberCheckbox;

    /** Checkbox: Show marks for each question */
    @FindBy(xpath = "//div[@id='settings-show-question-marks-checkbox']//input[@type='checkbox']")
    private WebElement showQuestionMarksCheckbox;

    /** Checkbox: Shuffle questions order */
    @FindBy(xpath = "//div[@id='settings-shuffled']//input[@type='checkbox']")
    private WebElement shuffleQuestionsCheckbox;

    /** Checkbox: Enable scheduling for quiz opening date & time */
    @FindBy(xpath = "//div[@id='settings-responses-and-questions-box1']//input[@type='checkbox']")
    private WebElement scheduleOpeningDateTimeCheckbox;

    /** Checkbox: Enable scheduling for quiz closing date & time */
    @FindBy(xpath = "//div[@class='settings-responses-and-questions-box9']//input[@type='checkbox']")
    private WebElement scheduleClosingDateTimeCheckbox;

    
    // ----------- Collaboration Tab Elements -----------
    
    private By addCollaboratorButton = By.xpath("//div[@id='Add Collaborator']");
    private By emailBox = By.xpath("//input[@type='email']");
    private By roleDropdown = By.xpath("//div[@class='mdc-select mdc-select--outlined']//div[@class='mdc-select__anchor']");
    private By adminRoleOption = By.xpath("//li[@class='mdc-list-item mdc-list-item--selected']");
    private By editorRoleOption = By.xpath("//li[@data-value='Editor']");
    private By viewerRoleOption = By.xpath("//li[@data-value='Viewer']");
    private By addButton = By.xpath("//div[@id='Add']");
    
    
    // === THEME SECTION ELEMENTS ===

    /** Default theme image (Classic theme) */
    @FindBy(xpath = "(//img[@alt='classic_new.jpeg'])[1]")
    private WebElement defaultThemeImage;

    /** Button to add custom themes (My Themes) */
    @FindBy(xpath = "//img[@src='images/add_white.png']")
    private WebElement addMyThemesButton;

    /** Button to add an image inside My Themes */
    @FindBy(xpath = "//img[@alt='/images/add_grey.svg']")
    private WebElement addImageButton;

    /** File input for uploading custom theme image */
    @FindBy(xpath = "//input[@type='file']")
    private WebElement fileInputImage;

    /** Confirm tick icon after uploading theme */
    @FindBy(xpath = "//img[@alt='tick mark']")
    private WebElement confirmTickIcon;
    
    
 // ======= Deleting Custom Theme =======
    
    /** Button for recently added theme (image preview card) */
    @FindBy(xpath = "//div[@id='theme_card_undefined']//img[@class='theme-image']")
    private WebElement addedThemeButton;
      
    /** Button inside My Themes card (to select custom theme) */
    @FindBy(css = "div[id='theme_card_0'] div div[class='theme-round center']")
    private WebElement addedMyThemesButton;
     
    /** Delete icon for removing existed custom theme */
    @FindBy(xpath = "(//img[@alt='/images/close.png'])[1]")
    private WebElement addedDeleteThemeIcon;

    /** Confirm delete button inside theme deletion popup */
    @FindBy(xpath = "//label[normalize-space()='Delete']")
    private WebElement confirmDeleteButton;

    /** Close button for theme popup */
    @FindBy(xpath = "//img[@src='images/close_black.svg']")
    private WebElement closeThemePopupButton;
    
 // ======= Validation Messages =======
    
    /** Popup message: Quiz Title is required */
    @FindBy(xpath = "//div[@class='modal-dialog-header']")
    private WebElement quizTitleRequiredMessage;

    /** Popup message: Question Title is required */
    @FindBy(xpath = "//div[@class='modal-dialog-header']")
    private WebElement questionTitleRequiredMessage;
    
    /** Header/title of Share popup */
    @FindBy(xpath = "//div[@class='modal-dialog-header']")
    private WebElement sharePopupHeader;
    
    /** Checkbox: Show Quiz Leaderboard */
    private By showQuizLeaderboardCheckbox = 
            By.xpath("//div[@id='settings-quiz-result']//div[1]//div[1]//div[1]//input[1]"); 
    
    //  ------------ Submit button -------------
    
    /** Submit button inside Quiz/Settings */
    @FindBy(xpath = "//span[@class='icon-title'][normalize-space()='Submit']")
    private WebElement submitButton;
    
    /** Submit button inside Quiz Title Empty warning popup */
    private By QuizEmptyTitleSubmitButton = By.xpath("//span[@class='icon-title'][normalize-space()='Submit']");
   
    
    
  // ========== Action Methods with WebDriverWait  ==========

     /** Clicks the 'Settings' button */
	 public void clickSettingsButton() {
	     wait.until(ExpectedConditions.elementToBeClickable(settingsButton)).click();
	 }
	
	 /** Clicks the 'Control' sub-tab under Settings */
	 public void clickControlTab() {
	     wait.until(ExpectedConditions.elementToBeClickable(controlTab)).click();
	 }
	
	 /** Clicks the 'Collaboration' sub-tab under Settings */
	 public void clickCollaborationTab() {
	     wait.until(ExpectedConditions.elementToBeClickable(collaborationTab)).click();
	 }
	
	 /** Enables or disables 'Allow Multiple Attempts' checkbox */
	 public void enableAllowMultipleAttempts(boolean enable) {
		    By checkboxLocator = By.xpath("//div[@id='settings-quiz-control-box11']//input[@type='checkbox']");
		    WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(checkboxLocator));
		    boolean isSelected = checkbox.isSelected();
		    if (enable != isSelected) {
		        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
		    }
		} 
	
	 /** Enables 'Duration' checkbox */
	 public void enableDuration() {
	     toggleCheckbox(durationCheckbox);
	 }
	
	 /** Enables 'Collect Email Address' checkbox */
	 public void enableCollectEmailAddress() {
	     toggleCheckbox(collectEmailAddressCheckbox);
	 }
	
	 /** Clicks on the 'Classic' theme card (Default Theme) */
	 public void selectDefaultThemeCard() {
	     wait.until(ExpectedConditions.elementToBeClickable(defaultThemeCard)).click();
	 }
	
	 /** Enables 'Show Logo' checkbox */
	 public void enableShowLogo() {
	     toggleCheckbox(showLogoCheckbox);
	 }
	
	 /** Enables 'Welcome Page' checkbox */
	 public void enableWelcomePage() {
	     toggleCheckbox(welcomePageCheckbox);
	 }
	
	 /** Enables 'Show Question Count' checkbox */
	 public void enableShowQuestionCount() {
	     toggleCheckbox(showQuestionCountCheckbox);
	 }
	
	 /** Enables 'Passcode Protection' checkbox */
	 public void enablePasscodeProtection() {
	     toggleCheckbox(passcodeProtectionCheckbox);
	 }
	
	 /** Clicks on the 'Confirmation Message' option */
	 public void clickConfirmationMessageOption() {
	     wait.until(ExpectedConditions.elementToBeClickable(confirmationMessage)).click();
	 }
	
	 /** Clicks on the 'Redirect to desired link' option */
	 public void clickRedirectToDesiredLinkOption() {
	     wait.until(ExpectedConditions.elementToBeClickable(redirectToDesiredLink)).click();
	 }
	
	 /** Enables 'Show Quiz Results' checkbox */
	 public void enableShowQuizResults() {
	     toggleCheckbox(showQuizResultsCheckbox);
	 }
	
	 /** Enables 'Show Correct Answer' checkbox */
	 public void enableShowCorrectAnswer() {
	     toggleCheckbox(showCorrectAnswerCheckbox);
	 }
	
	 /** Enables 'Show Question Number' checkbox */
	 public void enableShowQuestionNumber() {
	     toggleCheckbox(showQuestionNumberCheckbox);
	 }
	
	 /** Enables 'Show Question Marks' checkbox */
	 public void enableShowQuestionMarks() {
	     toggleCheckbox(showQuestionMarksCheckbox);
	 }
	
	 /** Scrolls to 'Shuffle Questions' checkbox and enables it if not already selected */
	 public void enableShuffleQuestions() {
		    By shuffleCheckboxLocator = By.xpath("//div[@id='settings-shuffled']//input[@type='checkbox']");
	
		    // Wait until present in DOM
		    WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(shuffleCheckboxLocator));
	
		    // Scroll into center view
		    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", checkbox);
	
		    // Wait until clickable
		    wait.until(ExpectedConditions.elementToBeClickable(checkbox));
	
		    // Click with JavaScript if not already selected
		    if (!checkbox.isSelected()) {
		        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
		    }
		} 	
	
	 /** Enables 'Schedule Opening Date/Time' checkbox */
	 public void enableScheduleOpeningDateTime() {
	     toggleCheckbox(scheduleOpeningDateTimeCheckbox);
	 }
	
	 /** Enables 'Schedule Closing Date/Time' checkbox */
	 public void enableScheduleClosingDateTime() {
	     toggleCheckbox(scheduleClosingDateTimeCheckbox);
	 }
	
	 /** Adds a single collaborator with given email and role -> Roles supported: Admin, Editor, Viewer */
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
	 	 
	 /** Clicks a checkbox if not already selected using JavaScript */
	 private void toggleCheckbox(WebElement checkbox) {
	     wait.until(ExpectedConditions.visibilityOf(checkbox));
	     if (!checkbox.isSelected()) {
	         ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
	     }
	 }
	 
	 /** Uploads a new custom theme image into 'My Themes' section */
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
	
	 /** Deletes an uploaded theme image from 'My Themes' section if present */
  public void deleteUploadedThemeIfPresent() {
     try {
      // Step 1: Click Settings tab (wait and click)
         wait.until(ExpectedConditions.elementToBeClickable(addedThemeButton)).click();

      // Step 2: Click +My Themes button
         wait.until(ExpectedConditions.elementToBeClickable(addedMyThemesButton)).click();

      // Step 3: Click the X (delete) icon on uploaded theme
         wait.until(ExpectedConditions.elementToBeClickable(addedDeleteThemeIcon)).click();

      // Step 4: Click DELETE on confirmation popup
         wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton)).click();

      // Step 5: Close theme popup/folder
         wait.until(ExpectedConditions.elementToBeClickable(closeThemePopupButton)).click();
    
     } catch (TimeoutException | NoSuchElementException e) {
         ExtentManager.getTest().info("No uploaded theme image found to delete — continuing.");
     }
 }
  
  /** Clicks the Submit button (retries up to 2 times).
   * Ensures Share popup loads after successful click. */
   public void clickSubmitButton() {
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    for (int attempt = 1; attempt <= 2; attempt++) {
	        try {
	         // Step 1: Re-fetch Submit button fresh each time
	            WebElement submitButton = wait.until(ExpectedConditions.presenceOfElementLocated(
	                By.xpath("//span[text()='Submit']/parent::div")));

	         // Step 2: Scroll and wait for visibility + clickability
	            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", submitButton);
	            wait.until(ExpectedConditions.visibilityOf(submitButton));
	            wait.until(ExpectedConditions.elementToBeClickable(submitButton));

	         // Step 3: Small wait in case of background operations (theme apply, etc.)
	            Thread.sleep(500);

	         // Step 4: JS click with DOM confirmation logic
	            js.executeScript("arguments[0].click();", submitButton);

	         // Step 5: Wait for Share Popup
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("share-card")));

	         // Optional: wait for clickable button inside
	            wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//div[@id='share-card']//div[@class='mdc-button__touch']")));

	            System.out.println("✅ Share popup loaded successfully after submit click");
	            return;

	        } catch (Exception e) {
	            System.err.println("❌ Attempt " + attempt + " failed to click Submit: " + e.getMessage());

	         // Wait before retry
	            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
	        }
	    }

	 // Final failure after 2 attempts
	    throw new RuntimeException("Submit button click failed. Share popup not visible after retries.");
	} 
       
   /** Adds multiple collaborators in fixed order: Admin → Editor → Viewer.
    * Faster version that supports dynamic Map input. */
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
                   continue;     // skip until matching role
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

   /** Waits for Quiz Title Required message and returns WebElement */
   public WebElement getQuizTitleRequiredMessage() {
       return wait.until(ExpectedConditions.visibilityOf(quizTitleRequiredMessage));
   }

   /** Waits for Quiz Question Title Required message and returns WebElement */
   public WebElement getQuestionTitleRequiredMessage() {
       return wait.until(ExpectedConditions.visibilityOf(questionTitleRequiredMessage));
   }

   /** Waits for Share popup to be visible and returns WebElement */
   public WebElement getSharePopup() {
       return wait.until(ExpectedConditions.visibilityOf(sharePopupHeader));
   }
  
   /** Clicks on Submit button inside Quiz Empty Title warning popup */
   public void clickQuizEmptyTitleSubmitButton() {
       wait.until(ExpectedConditions.elementToBeClickable(QuizEmptyTitleSubmitButton)).click();
   }

   /** Enables or disables the 'Show Quiz Leaderboard' checkbox */
   public void enableShowQuizLeaderboard(boolean enable) {
    // Wait until the element is present in DOM
       WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(showQuizLeaderboardCheckbox));

       boolean isSelected = checkbox.isSelected();

       if (enable != isSelected) {
        // First scroll into view
           ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox);

        // Then click using JavaScript (to avoid hidden/overlapped issues)
           ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
       }
   }

   
   
   
   
       
   
   
 
  
}

































/*
//Clicks the Submit button at the bottom of the settings page using JavaScript
public void clickSubmitButton() {
wait.until(ExpectedConditions.visibilityOf(submitButton)); // Ensure it's visible first
((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
}*/

/*
//Clicks the Submit button at the bottom of the settings page
public void clickSubmitButton() {
 wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
}*/

/*
public void clickSubmitButton() {
  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
  wait.until(ExpectedConditions.visibilityOf(submitButton));
  wait.until(ExpectedConditions.elementToBeClickable(submitButton));
  ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
}

public void clickSubmitButtonWithRetry() {
  int attempts = 0;
  while (attempts < 2) {
      try {
          clickSubmitButton(); // Now this will work fine
          break;
      } catch (Exception e) {
          System.out.println("Retrying click attempt " + (attempts + 1));
          attempts++;
      }
  }
}
*/

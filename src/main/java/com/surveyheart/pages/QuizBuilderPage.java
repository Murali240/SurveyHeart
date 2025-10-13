package com.surveyheart.pages;

import com.surveyheart.enums.QuizQuestionType;
import com.surveyheart.utilities.ExtentManager;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class QuizBuilderPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private int questionIndex = 0;

    /** Constructor initializes PageFactory elements and explicit wait */
    public QuizBuilderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));    
    }
   
    
    // ===================== Web Elements with @FindBy =====================   
    
    /** Quiz title input (after duplication) */
    @FindBy(xpath = "//label[@id='label_field_Title']//input[@type='text']")
    private WebElement quizTitleInput;
    
    /** Attach icon for new image */
    @FindBy(xpath = "//img[@alt='attach']")
    private WebElement attachIcon;

    /** Option to select image from popup */
    @FindBy(xpath = "//div[@class='popup-menu-container']//div//div[1]")
    private WebElement imageOption;

    /** Add image button */
    @FindBy(xpath = "//img[@alt='/images/add_grey.svg']")
    private WebElement addImageButton;

    /** File input for uploading image */
    @FindBy(xpath = "//input[@type='file']")
    private WebElement fileInputImage;

    /** Confirm tick icon after image upload */
    @FindBy(xpath = "//img[@alt='tick mark']")
    private WebElement confirmTickIcon;
   
    /** Delete image icon - My images popup */
    @FindBy(xpath = "//div[@class='builder-box1']//div[@id='default-theme-container']//div[2]//div[1]//img[1]")
	private WebElement deleteImageIcon;

    /** Confirm Delete button on Delete popup */
    @FindBy(xpath = "//label[normalize-space()='Delete']")
	private WebElement confirmDeleteButton;

    /** Close image popup button - My images popup */
    @FindBy(xpath = "//div[@id='theme-close-button']//img")
	private WebElement closeImagePopupButton;   
 
    /** Picture choice question selection */
    @FindBy(xpath = "//div[@id='question-group-container-1']//div[3]")
    private WebElement pictureChoiceQuestion;

    /** Premium warning popup header */
    @FindBy(xpath = "//div[@class='modal-dialog-header']")
    private WebElement premiumWarningPopup;

    /** VIEW PLANS button in premium popup */
    @FindBy(xpath = "//label[normalize-space()='VIEW PLANS']")
    private WebElement viewPlansButton;

    /** CANCEL button in premium popup */
    @FindBy(xpath = "//label[normalize-space()='CANCEL']")
    private WebElement cancelButton;   
    
    /** Delete question icon on QT card */
    @FindBy(xpath = "//div[@id='question_card_0']//img[@alt='Delete']")
    private WebElement deleteIcon;
     
    /** Duplicate question icon on QT card */
    @FindBy(xpath = "//img[@alt='duplicate']")
    private WebElement duplicateQuestionIcon;
    
    /** Required switch icon on QT card */
    private By requiredSwitchIcon  = By.xpath("//input[@value='switch-0']");
    
    /** Undo toast button after QT deleted */
    @FindBy(xpath = "//a[normalize-space()='Undo']")
    private WebElement undoToastButton;

    /** Undo toast message text */
    @FindBy(xpath = "//p[@class='response-undo-message']")
    private WebElement undoToastMessageText;

    /** Quiz Builder button */
    @FindBy(xpath = "//span[normalize-space()='Builder']")
    private WebElement quizBuilderButton;
    
    
    
 // ========= Action Methods with WebDriverWait  ========== // 
    
    /** Enters quiz title in the title input field */
    public void enterQuizTitle(String quizTitle) {
        WebElement titleField = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//label[@id='label_field_Title']//input[@type='text']")));
        titleField.clear();
        titleField.sendKeys(quizTitle);
    }
    
    /** Clicks the initial "Add Question" button */
    public void clickInitialAddQuestion() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Add Question']"))).click();
    }

    /** Clicks "Add Question" after the given index */
    public void clickAddQuestionAfter(int index) {
        String addButtonXpath = "//div[@id='question_card_" + index + "']//img[@alt='Add Question']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(addButtonXpath))).click();
    }

    /** Adds a question based on type, title, and answer/options */
    public void addQuestion(QuizQuestionType type, String title, String answerOrFileLabel, List<String> options, int correctOptionIndex) {
        if (questionIndex == 0) {
            clickInitialAddQuestion();
        } else {
            clickAddQuestionAfter(questionIndex - 1);
        }

        switch (type) {
            case SHORT_ANSWER:
                addShortAnswerQuestion(title, answerOrFileLabel);
                break;
            case LONG_ANSWER:
                addLongAnswerQuestion(title, answerOrFileLabel);
                break;
            case MULTIPLE_CHOICE:
                addMCQQuestion(title, options, correctOptionIndex);
                break;
            case DROPDOWN:
                addDropdownQuestion(title, options, correctOptionIndex);
                break;
            case FILE_UPLOAD:
                addFileQuestion(title);
                break;
        }
        questionIndex++;
    }

    /** Adds a Short Answer question */
    private void addShortAnswerQuestion(String titleText, String answerText) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Short Answer']"))).click();
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='question-text-input-" + questionIndex + "']")));
        title.sendKeys(titleText);
        WebElement answer = driver.findElement(By.xpath("//input[@maxlength='100']"));
        answer.sendKeys(answerText);
    }

    /** Adds a Long Answer question */
    private void addLongAnswerQuestion(String titleText, String answerText) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Long Answer')]"))).click();
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='question-text-input-" + questionIndex + "']")));
        title.sendKeys(titleText);
        WebElement answer = driver.findElement(By.xpath("//textarea[@aria-label='Label']"));
        answer.sendKeys(answerText);
    }

    /** Adds a Multiple Choice Question */
    private void addMCQQuestion(String titleText, List<String> options, int correctOptionIndex) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='question-group-container-1']//div[@class='question-type-grid-container']//div[1]"))).click();
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='question-text-input-" + questionIndex + "']")));
        title.sendKeys(titleText);

        for (int i = 0; i < options.size(); i++) {
            if (i == 0) {
                driver.findElement(By.xpath("//input[@id='choice-question-text-input-" + questionIndex + "-0']")).sendKeys(options.get(0));
            } else {
                driver.findElements(By.xpath("//img[@alt='Add Option']")).get(i - 1).click();
                String optionXpath = "//input[@id='choice-question-text-input-" + questionIndex + "-" + i + "']";
                driver.findElement(By.xpath(optionXpath)).sendKeys(options.get(i));
            }
        }

        WebElement correctOption = driver.findElement(By.xpath("(//img[@alt='Option'])[" + (correctOptionIndex + 1) + "]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", correctOption);
    }

    /** Adds a Dropdown Question */
    private void addDropdownQuestion(String titleText, List<String> options, int correctOptionIndex) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Dropdown')]"))).click();
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='question-text-input-" + questionIndex + "']")));
        title.sendKeys(titleText);

        for (int i = 0; i < options.size(); i++) {
            if (i == 0) {
                driver.findElement(By.xpath("//input[@id='choice-question-text-input-" + questionIndex + "-0']")).sendKeys(options.get(0));
            } else {
                String addOptionXpath = "//div[@id='star-body-container" + questionIndex + "']//img[@alt='Add Option']";
                driver.findElements(By.xpath(addOptionXpath)).get(i - 1).click();
                String optionXpath = "//input[@id='choice-question-text-input-" + questionIndex + "-" + i + "']";
                driver.findElement(By.xpath(optionXpath)).sendKeys(options.get(i));
            }
        }

        WebElement correctOption = driver.findElement(By.xpath("(//img[@alt='Option'])[" + (correctOptionIndex + 4) + "]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", correctOption);
    }

    /** Adds a File Upload Question */
    private void addFileQuestion(String titleText) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='question-group-container-2']//div[@class='question-type-grid-container']//div[1]"))).click();
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='question-text-input-" + questionIndex + "']")));
        title.sendKeys(titleText);
    }

    /** Uploads a new image to the question */
    public void uploadNewImageToQuestion(String imagePath) {
    	// Click on attach icon
           wait.until(ExpectedConditions.visibilityOf(attachIcon)).click();

        // Click on Image option
           wait.until(ExpectedConditions.visibilityOf(imageOption)).click();

        // Click on '+ Add image' button
           wait.until(ExpectedConditions.visibilityOf(addImageButton)).click();

        // Upload image file using <input type='file'>
           WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(
           By.xpath("//input[@type='file']")));  // You can also reuse fileInputImage if using @FindBy
           fileInput.sendKeys(imagePath);

        // Wait for tick mark icon to confirm upload
           wait.until(ExpectedConditions.visibilityOf(confirmTickIcon)).click();
    }

    /** Deletes existing image if present - My images popup */
    public void deleteAttachedImageIfPresent() {
         try {
      // Step 1: Click attach icon (already declared)
         wait.until(ExpectedConditions.elementToBeClickable(attachIcon)).click();

      // Step 2: Select 'Image' option (already declared)
         wait.until(ExpectedConditions.elementToBeClickable(imageOption)).click();

      // Step 3: Click the 'X' icon to delete the image
         wait.until(ExpectedConditions.elementToBeClickable(deleteImageIcon)).click();

      // Step 4: Click DELETE button on confirmation popup
         wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton)).click();

      // Step 5: Close the image folder/popup
         wait.until(ExpectedConditions.elementToBeClickable(closeImagePopupButton)).click();

      } catch (TimeoutException | NoSuchElementException e) {
          ExtentManager.getTest().info("No image was present to delete — continuing without error.");
      }
  }
    
    /** Clicks Picture Choice Question */
    public void clickPictureChoiceQuestion() {
        wait.until(ExpectedConditions.elementToBeClickable(pictureChoiceQuestion)).click();
    }

    /** Returns Premium Warning Popup */
    public WebElement getPremiumWarningPopup() {
        return wait.until(ExpectedConditions.visibilityOf(premiumWarningPopup));
    }

    /** Clicks VIEW PLANS button */
    public void clickViewPlansButton() {
        wait.until(ExpectedConditions.elementToBeClickable(viewPlansButton)).click();
    }

    /** Clicks CANCEL button */
    public void clickCancelButton() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton)).click();
    }

    /** Returns duplicated quiz title */
    public String getDuplicatedQuizTitle()  {
        wait.until(ExpectedConditions.visibilityOf(quizTitleInput));
        return quizTitleInput.getAttribute("value");
    } 
       
    /** Clicks Delete Icon in the first question card */
    public void clickDeleteIcon() {
        wait.until(ExpectedConditions.visibilityOf(deleteIcon)).click();
    }
    
    /** Adds a Short Question with title and answer */
    public void addShortQuestion(String questionTitle, String answer) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='question-group-container-0']//div[@class='question-type-grid-container']//div[1]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='question-text-input-0']"))).sendKeys(questionTitle);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='label_field_Answer']//input[@type='text']"))).sendKeys(answer);
    }

    /** Clicks Duplicate Question icon */
    public void clickDuplicateQuestionIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(duplicateQuestionIcon)).click();
    }
    
    /** Clicks Required Toggle Switch (via JavaScript) */
    public void clickRequiredToggleSwitchIcon() {
        WebElement toggle = wait.until(ExpectedConditions.presenceOfElementLocated(requiredSwitchIcon));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", toggle);
    }
  
    /** Clicks Undo Toast Button after QT deleted */
    public void clickUndoToastButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(undoToastButton));
        button.click();
    }
    
    /** Returns full Undo Toast message (text + UNDO) */
    public String getFullUndoToastMessage() {
        String message = wait.until(ExpectedConditions.visibilityOf(undoToastMessageText)).getText();
        String undoText = wait.until(ExpectedConditions.visibilityOf(undoToastButton)).getText();
        return message + " " + undoText;
    }
    
    /** Waits for Question Card Drag handle */
    public void waitForQuestionCard(int positionIndex) {
     // positionIndex starts from 0 for first card
        By dragHandleLocator = By.xpath("(//img[@alt='Drag'])[" + (positionIndex + 1) + "]");
        
        WebElement dragHandle = wait.until(ExpectedConditions.visibilityOfElementLocated(dragHandleLocator));
        
     // Scroll into view to ensure it's interactable
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", dragHandle);
    }
    
    /** Drags a question from one position to another */
    public void dragQuestion(int fromIndex, int toIndex) {
     // Convert to zero-based (SurveyHeart IDs are 0,1,2...)
        int from = fromIndex - 1;
        int to = toIndex - 1;

     // Source handle (drag image)
        WebElement sourceHandle = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[@id='question_card_" + from + "']//img[@alt='Drag']")
        ));

     // Target card (drop destination)
        WebElement targetCard = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[@id='question_card_" + to + "']")
        ));

        Actions actions = new Actions(driver);

     // Hover ensures the drag icon is visible
        actions.moveToElement(sourceHandle).pause(Duration.ofMillis(500)).perform();

     // Drag and drop
        actions.clickAndHold(sourceHandle)
               .moveToElement(targetCard, 0, 50)      // small offset to drop inside
               .pause(Duration.ofMillis(500))
               .release()
               .build()
               .perform();
    }
      
    /** Returns Quiz Builder button */
    public WebElement getQuizBuilderButton() {
        wait.until(ExpectedConditions.visibilityOf(quizBuilderButton));
        return quizBuilderButton;
    }
   
    
  
    
    
    
    

}

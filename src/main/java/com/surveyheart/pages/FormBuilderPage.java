package com.surveyheart.pages;

	


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.surveyheart.enums.PremiumQuestionType;
import com.surveyheart.enums.QuestionType;
import com.surveyheart.utilities.ExtentManager;
import com.surveyheart.utilities.WaitUtils;

    /** Page Object class representing the Form Builder screen in SurveyHeart */
	public class FormBuilderPage {
	   
		private WebDriver driver;
	    private WebDriverWait wait;

	    /** Constructor initializes PageFactory elements and explicit wait */
	    public FormBuilderPage(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	        PageFactory.initElements(driver, this);
	    }
	    
	    
	 // ===================== Web Elements with @FindBy =====================
	    
	    /** Form title input field on builder screen (after duplication) */
	    @FindBy(xpath = "//label[@id='label_field_Title']//input[@type='text']")
	    private WebElement formTitleInput;
	    
	    /** Short question title input field (for 0th question) */
	    @FindBy(xpath = "//input[@id='question-text-input-0']")
	    private WebElement shortQuestionTitleInput;
	    
	    /** Attachment icon for adding media (image/video/website) */
	    @FindBy(xpath = "//img[@alt='attach']")
	    private WebElement attachIcon;

	    /** Option inside popup menu → Select Image */
	    @FindBy(xpath = "//div[@class='popup-menu-container']//div//div[1]")
	    private WebElement imageOption;

	    /** +Add Image button inside My images  */
	    @FindBy(xpath = "//img[@alt='/images/add_grey.svg']")
	    private WebElement addImageButton;

	    /** File input for selecting image from local system */
	    @FindBy(xpath = "//input[@type='file']")
	    private WebElement fileInputImage;

	    /** Confirm tick icon after selecting image */
	    @FindBy(xpath = "//img[@alt='tick mark']")
	    private WebElement confirmTickIcon;
	    
	    /** Delete image icon for existing 1st image - My images popup */
	    @FindBy(xpath = "//div[@class='builder-box1']//div[@id='default-theme-container']//div[2]//div[1]//img[1]")
	    private WebElement deleteImageIcon;

	    /** Confirm delete button inside Delete popup */
	    @FindBy(xpath = "//label[normalize-space()='Delete']")
	    private WebElement confirmDeleteButton;

	    /** Close button inside My images popup(whole folder) */
	    @FindBy(xpath = "//div[@id='theme-close-button']//img")
	    private WebElement closeImagePopupButton;   
	     
	    /** Attach options inside popup menu → Select Video */
	    @FindBy(xpath = "//div[@class='popup-menu-container']//div[2]")
	    private WebElement videoOption;

	    /** "Video Link" button inside video popup */
	    @FindBy(xpath = "//div[@class='button-base ']")
	    private WebElement videoLinkButton;

	    /** Input field to paste video URL (check actual input tag) */
	    @FindBy(xpath = "//body/div[@id='app']/div/div[@id='app-container']/div[@class='body-container']/div[@class='builder-wrapper']/div[@class='builder-box1']/div[@class='form-builder-body-container']/div[@class='builder-forms-container']/div[@data-rbd-droppable-id='characters']/div[@class='builder-cards-container']/div[@role='button']/div[@id='question_card_0']/div[3]")
	 // make sure this is actually <input> or inside contains <input>
	    private WebElement pasteYourVideoURLLinkHere;
	    
	    
	 // Premium Questions -  till from here to 

	    /** Add Question button to insert new question in builder */
	    @FindBy(xpath = "//span[normalize-space()='Add Question']")
	    private WebElement addQuestionButton;

	    /** Premium question → Slider */
	    @FindBy(xpath = "//div[contains(text(),'Slider')]")
	    private WebElement sliderQuestion;

	    /** Premium question → Picture Choice */
	    @FindBy(xpath = "//div[@class='builder-box1']//div[8]")
	    private WebElement pictureChoiceQuestion;

	    /** Premium question → Ranking */
	    @FindBy(xpath = "//div[@class='builder-box1']//div[9]")
	    private WebElement rankingQuestion;

	    /** Premium question → Agreement */
	    @FindBy(xpath = "//div[@class='builder-box1']//div[10]")
	    private WebElement agreementQuestion;

	    /** Premium question → Signature */
	    @FindBy(xpath = "//div[@class='builder-box1']//div[11]")
	    private WebElement signatureQuestion;

	    /** Premium popup warning text */
	    @FindBy(xpath = "//div[contains(text(),'PREMIUM_FEATURES')]")
	    private WebElement premiumWarning;

	    /** Cancel button in Premium popup */
	    @FindBy(xpath = "//label[normalize-space()='CANCEL']")
	    private WebElement cancelButton;

	    /** View Plans button in Premium popup */
	    @FindBy(xpath = "//label[contains(text(),'VIEW PLANS')]")
	    private WebElement viewPlansButton;

	    /** Page Title text on Premium Plans page */
	    @FindBy(xpath = "//span[contains(text(),'Premium plans')]")
	    private WebElement premiumPlansPageTitle;

	    /** Close button for Premium Plans page */
	    @FindBy(xpath = "//img[@class='localization-close-img']")
	    private WebElement closePlansPageButton;
	    
	    /** Delete icon for a question (example: first question card) */
	    @FindBy(xpath = "//div[@id='question_card_0']//img[@alt='Delete']")
	    private WebElement deleteIcon;    
	    
	    /** Drag-and-drop icon for the 6th question */
	    @FindBy(xpath = "(//img[@alt='Drag'])[6]")
	    private WebElement sixthDragAndDropIcon;
	    
	    /** Duplicate icon for duplicating question */
	    @FindBy(xpath = "//img[@alt='duplicate']")
	    private WebElement duplicateQuestionIcon;
	    
	    /** Required toggle switch for marking question as mandatory */
	    private By requiredToggleSwitch =By.xpath("//span[@class='MuiButtonBase-root MuiSwitch-switchBase MuiSwitch-colorPrimary PrivateSwitchBase-root MuiSwitch-switchBase MuiSwitch-colorPrimary css-17z6nsr']//input[@value='switch-0']");
	    
	    /** Undo button in toast message after question deleted */
	    @FindBy(xpath = "//a[normalize-space()='Undo']")
	    private WebElement undoToastButton;
	    
	    /** Toast message text shown after question deleted */
	    @FindBy(xpath = "//p[@class='response-undo-message']")
	    private WebElement undoToastMessageText;
	    
	    /** "Builder" button (navigation to form builder screen) */
	    @FindBy(xpath = "//span[normalize-space()='Builder']")
	    private WebElement formBuilderButton;    
	  
	    
	    
	    // ========== Action Methods with WebDriverWait  ==========
	    
	    /** Clicks the "Add Question" button on the builder screen. */
	    public void clickAddQuestion() {
	        wait.until(ExpectedConditions.elementToBeClickable(addQuestionButton)).click();
	    }      // till here Premium questions related only

	    /** Clicks the "+Create Form" button to start a new form. */
	    public void clickCreateForm() {
	        WebElement createFormButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Create Form']")));
	        createFormButton.click();
	    }
	      
	    /** Enters the form title in the title input field. */
	    public void enterFormTitle(String title) {
	        WebElement titleField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@maxlength='250']")));
	        titleField.sendKeys(title);
	    }

	    /** Clicks the first "Add Question" button on a new form (before any question exists). */
	    public void clickInitialAddQuestion() {
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Add Question']"))).click();
	    }

	    /** Clicks the "Add Question" button for the question at the given index */
	    public void clickAddQuestionAfter(int questionIndex) {
	        String addButtonXpath = "//div[@id='question_card_" + questionIndex + "']//img[@alt='Add Question']";
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(addButtonXpath))).click();
	    }   

	    /** ========== Question Type Selection ========== */
	    public void selectQuestionType(QuestionType type) {

	    	switch (type) {
	        case SHORT_ANSWER:
	            clickQuestionByAlt("Short Answer");
	            break;
	        case LONG_ANSWER:
	            clickQuestionByText("Long Answer");
	            break;
	        case EMAIL:
	            clickQuestionByText("Email");
	            break;
	        case NUMBER:
	            clickQuestionByText("Number");
	            break;
	        case MULTIPLE_CHOICE:
	            clickByGridGroup("question-group-container-1", 1);
	            break;
	        case CHECKBOX:
	            clickByGridGroup("question-group-container-1", 3);
	            break;
	        case DROPDOWN:
	            clickQuestionByText("Dropdown");
	            break;
	        case STAR:
	            clickByGridGroup("question-group-container-2", 1);
	            break;
	        case SMILE:
	            clickByGridGroup("question-group-container-2", 2);
	            break;
	        case DATE:
	            clickQuestionByText("Date");
	            break;
	        case TIME:
	            clickQuestionByText("Time");
	            break;
	        case FILE_UPLOAD:
	            clickByGridGroup("question-group-container-4", 1);
	            break;
	        case LINEAR:
	            wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//div[@id='questionTypePopup']//div[6]"))).click();
	            break;
	        case MCQ_GRID:
	            clickByGridGroup("question-group-container-1", 2);
	            break;
	        case CHECKBOX_GRID:
	            clickQuestionByText("Checkboxes Grid");
	            break;
	    }
	 }   	

	    
	    private void clickQuestionByAlt(String altText) {
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='" + altText + "']"))).click();
	    }

	    private void clickQuestionByText(String text) {
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'" + text + "')]"))).click();
	    }

	    private void clickByGridGroup(String containerId, int position) {
	        String xpath = "//div[@id='" + containerId + "']//div[@class='question-type-grid-container']//div[" + position + "]";
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
	    }

	    // ========== Input Methods ==========
	    /** Enters question title text for a given question index. */
	    public void enterQuestionTitle(int questionIndex, String title) {
	        String xpath = "//input[@id='question-text-input-" + questionIndex + "']";
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(title);
	    }

	    /** Enters a choice option text for a multiple-choice question. */
	    public void enterChoiceOption(int questionIndex, int optionIndex, String optionText) {
	        String xpath = "//input[@id='choice-question-text-input-" + questionIndex + "-" + optionIndex + "']";
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(optionText);
	    }

	    /** Clicks the "Add Option" button for a choice question. */
	    public void clickAddOption(int index) {
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@alt='Add Option'])[" + index + "]"))).click();
	    }

	    /** Adds multiple MCQ options by typing and clicking "Add Option" sequentially. */
	    public void addMCQOptions(int questionIndex, String... options) {
	        for (int i = 0; i < options.length; i++) {
	            enterChoiceOption(questionIndex, i, options[i]);
	            if (i < options.length - 1) {
	                clickAddOption(i + 1);
	            }
	        }
	    }

	    // ========== Grid Question Methods ==========
	    /** Adds rows and columns for MCQ Grid question. */
	    public void enterMCQGridRowsAndColumns(int questionIndex, String[] rows, String[] columns) {
	        for (int i = 0; i < rows.length; i++) {
	            String rowXpath = "//input[@id='row-text-" + questionIndex + "-input-" + i + "']";
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(rowXpath))).sendKeys(rows[i]);
	            if (i < rows.length - 1) {
	                driver.findElement(By.xpath("//div[@id='row-text-" + questionIndex + "-" + i + "]//img[@alt='Add Line']")).click();
	            }
	        }

	        for (int i = 0; i < columns.length; i++) {
	            String colXpath = "//input[@id='col-text-" + questionIndex + "-input-" + i + "']";
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(colXpath))).sendKeys(columns[i]);
	        }
	    }
	
	    /** Clicks the initial "Add Question" button on a new form */
	    public void clickInitialAddQuestionButton() {
	        By addQuestionBtn = By.xpath("//span[text()='Add Question']");
	        wait.until(ExpectedConditions.elementToBeClickable(addQuestionBtn)).click();
	    } 
	
	    /** Adds multiple options to a choice question. */
	    public void addOptionsForChoiceQuestion(int questionIndex, String... options) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        for (int i = 0; i < options.length; i++) {
	            String inputXpath = "//input[@id='choice-question-text-input-" + questionIndex + "-" + i + "']";

	            if (i > 0) {
	             // Click on "Add Option" button before accessing new input
	                String addOptionBtnXpath = "(//div[@id='question_card_" + questionIndex + "']//img[@alt='Add Option'])[" + i + "]";
	                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(addOptionBtnXpath))).click();
	            }

	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(inputXpath)))
	                .sendKeys(options[i]);
	        }
	    }

	    /** Adds rows and columns to a grid question. */
	    public void addRowsAndColumnsToGridQuestion(int questionIndex, int rowCount, int colCount) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	     // Add Rows
	        for (int i = 1; i < rowCount; i++) {
	            String rowAddButtonXpath = "//div[@id='row-text-" + questionIndex + "-" + (i - 1) + "']//img[@alt='Add Line']";
	            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(rowAddButtonXpath))).click();
	        }

	     // Add Columns
	        for (int j = 1; j < colCount; j++) {
	            String colAddButtonXpath = "//div[@id='col-text-" + questionIndex + "-" + (j - 1) + "']//img[@alt='Add Line']";
	            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(colAddButtonXpath))).click();
	        }
	    }
   
	    /** Upload a new image to the question. */
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
	    
	      /** Deletes the existed image if it exists in My images screen. */
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
	      
	      /** Attaches a YouTube or video URL to the question. - this not working correctly(workon it) */
	      public void enterVideoUrl(String videoUrl) {
	       // Click on attach icon
	          wait.until(ExpectedConditions.elementToBeClickable(attachIcon)).click();

	       // Select Video option
	          wait.until(ExpectedConditions.elementToBeClickable(videoOption)).click();

	       // Click on 'Video Link' button
	          wait.until(ExpectedConditions.elementToBeClickable(videoLinkButton)).click();

	       // Target the contenteditable div instead of treating it like an input
	          WebElement urlInput = wait.until(ExpectedConditions.visibilityOf(pasteYourVideoURLLinkHere));

	       // Use Actions to click+type into contenteditable
	          Actions actions = new Actions(driver);
	          actions.moveToElement(urlInput).click().pause(Duration.ofMillis(5000))
	                 .sendKeys(videoUrl).perform();

	       // Press ENTER to confirm paste
	          urlInput.sendKeys(Keys.ENTER);

	       // Wait until video link actually appears (e.g. link tag rendered inside the card)
	          wait.until(ExpectedConditions.visibilityOfElementLocated(
	                  By.xpath("//a[contains(@href,'youtu') or contains(text(),'http')]")
	          ));

	       // Small buffer wait to stabilize
	          WaitUtils.waitForSeconds(driver, 8);
	      }
	      
	      /** Attaches a website URL to the question. */
	      public void attachWebsiteUrlToQuestion(String websiteUrl) throws InterruptedException {
	    	 // Click on attach icon
	    	    wait.until(ExpectedConditions.elementToBeClickable(attachIcon)).click();

	    	 // Click Website option
	    	    WebElement websiteOption = wait.until(ExpectedConditions.elementToBeClickable(
	    	            By.xpath("//div[@class='popup-menu-container']//div[3]")));
	    	    websiteOption.click();

	    	 // Locate the contenteditable div
	    	    WebElement urlInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
	    	            By.xpath("//div[@id='builder-question-body']")));  

	    	 // Use Actions to type into contenteditable field
	    	    Actions actions = new Actions(driver);
	    	    actions.moveToElement(urlInput)
	    	           .click()
	    	           .pause(Duration.ofMillis(500))
	    	           .sendKeys(websiteUrl)
	    	           .sendKeys(Keys.ENTER)         // confirm paste
	    	           .perform();
	    	}
  
	    /** Returns the duplicated form title text. */
	    public String getDuplicatedFormTitle()  {
	        wait.until(ExpectedConditions.visibilityOf(formTitleInput));
	        return formTitleInput.getAttribute("value");
	    }  
	    
	    /** Validates that duplicated question title matches the expected title. */
	    public void validateDuplicatedQuestionTitle(String expectedQuestionTitle) {
	        wait.until(ExpectedConditions.visibilityOf(shortQuestionTitleInput));
	        String actualQuestionTitle = shortQuestionTitleInput.getAttribute("value");

	        System.out.println("Short Question Title: " + actualQuestionTitle);

	        Assert.assertEquals(actualQuestionTitle, expectedQuestionTitle, "Question title does not match expected");

	        System.out.println("✔ Question title validated successfully on the builder screen.");
	    }

	    /** Selects a premium question type and clicks it. */
	    public void selectPremiumQuestionType(PremiumQuestionType type) {
	        WebElement elementToClick = null;

	        switch (type) {
	            case SLIDER:
	                elementToClick = wait.until(ExpectedConditions.visibilityOf(sliderQuestion));
	                break;
	            case PICTURE_CHOICE:
	                elementToClick = wait.until(ExpectedConditions.visibilityOf(pictureChoiceQuestion));
	                break;
	            case RANKING:
	                elementToClick = wait.until(ExpectedConditions.visibilityOf(rankingQuestion));
	                break;
	            case AGREEMENT:
	                elementToClick = wait.until(ExpectedConditions.visibilityOf(agreementQuestion));
	                break;
	            case SIGNATURE:
	                elementToClick = wait.until(ExpectedConditions.visibilityOf(signatureQuestion));
	                break;
	        }

	       if (elementToClick != null) {
	           ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementToClick);
	            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", elementToClick);
	        }
	    }

	    /** Gets Premium warning popup text. */
	    public String getPremiumWarningText() {
	        return wait.until(ExpectedConditions.visibilityOf(premiumWarning)).getText();
	    }

	    /** Clicks Cancel button on Premium popup. */
	    public void clickCancelPremiumPopup() {
	        wait.until(ExpectedConditions.elementToBeClickable(cancelButton)).click();
	    }

	    /** Clicks View Plans button on Premium popup. */
	    public void clickViewPlans() {
	        wait.until(ExpectedConditions.elementToBeClickable(viewPlansButton)).click();
	    }

	    /** Gets the premium plans page title. */
	    public String getPremiumPlansTitle() {
	        return wait.until(ExpectedConditions.visibilityOf(premiumPlansPageTitle)).getText();
	    }

	    /** Close(X) icon on premium plans page. */
	    public void closePremiumPlansPage() {
	        wait.until(ExpectedConditions.elementToBeClickable(closePlansPageButton)).click();
	    }
	
	    /** Logs premium popup message in Extent Report. */ 
	    public void verifyPremiumPopupMessage(int questionIndex, PremiumQuestionType type) {
	        String warning = getPremiumWarningText();
	        ExtentManager.getTest().pass("For FREE user, Premium Question #" + (questionIndex + 1) + 
	            " (" + type.name().replace("_", " ") + ") shows popup: " + warning);
	    }
	    
	    /** Adds a premium question and cancels popup. */
	    public void addPremiumQuestion(String questionName, String xpath, String cancelXpath) {
	        addQuestionButton.click();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'PREMIUM_FEATURES')]")));
	        driver.findElement(By.xpath(cancelXpath)).click();
	    }
	      
	    /** Clicks delete icon of first question card. */
	    public void clickDeleteIcon() {
	        wait.until(ExpectedConditions.visibilityOf(deleteIcon)).click();
	    }   

	    /** Gets the 6th drag-and-drop icon. */ 
	    public WebElement getSixthDragAndDropIcon() {
	        return wait.until(ExpectedConditions.visibilityOf(sixthDragAndDropIcon));
	    }
   
	    /** Waits for question card drag handle by index. */
	    public void waitForQuestionCard(int positionIndex) {
	     // positionIndex starts from 0 for first card
	        By dragHandleLocator = By.xpath("(//img[@alt='Drag'])[" + (positionIndex + 1) + "]");
	        
	        WebElement dragHandle = wait.until(ExpectedConditions.visibilityOfElementLocated(dragHandleLocator));
	        
	     // Scroll into view to ensure it's interactable
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", dragHandle);
	    }

	    /** Drags a question from one position to another. */
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
	        actions.moveToElement(sourceHandle).pause(Duration.ofMillis(200)).perform();

	     // Drag and drop
	        actions.clickAndHold(sourceHandle)
	               .moveToElement(targetCard, 0, 50)       // small offset to drop inside
	               .pause(Duration.ofMillis(300))
	               .release()
	               .build()
	               .perform();
	    }
	    
	    /** Clicks Duplicate question icon - on QT card. */
	    public void clickDuplicateQuestionIcon() {
	        wait.until(ExpectedConditions.elementToBeClickable(duplicateQuestionIcon)).click();
	    }
	        
	    /** Required Toggle switch using JS. */
	    public void clickRequiredToggleSwitchIcon() {
	        WebElement toggle = wait.until(ExpectedConditions.presenceOfElementLocated(requiredToggleSwitch));
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", toggle);
	    }
	   
	    /** Clicks Undo button on toast message. */
	    public void clickUndoToastButton() {
	        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(undoToastButton));
	        button.click();
	    }
	    
	    /** Gets full Undo toast message text. */
	    public String getFullUndoToastMessage() {
	        String message = wait.until(ExpectedConditions.visibilityOf(undoToastMessageText)).getText();
	        String undoText = wait.until(ExpectedConditions.visibilityOf(undoToastButton)).getText();
	        return message + " " + undoText;
	    }
	        
	    /** Gets Form Builder button. */
	    public WebElement getFormBuilderButton() {
	        wait.until(ExpectedConditions.visibilityOf(formBuilderButton));
	        return formBuilderButton;
	    }
	    
	    
	
	    
	    
	 
	    
	        
	    
  }
	
	

	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
package com.surveyheart.tests.quizzes;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.surveyheart.base.BaseTest;
import com.surveyheart.enums.QuizQuestionType;
import com.surveyheart.pages.FormDashboardPage;
import com.surveyheart.pages.QuizBuilderPage;
import com.surveyheart.pages.QuizDashboardPage;
import com.surveyheart.pages.QuizSettingsPage;
import com.surveyheart.pages.SharePopupPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


/** Test class to verify deletion of a quiz from the Quiz Dashboard */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class QuizDeleteTest extends BaseTest {
	
	@Test (groups = "sanity", priority = 4)
	public void verifyQuizDeletion() {
		
	    // ===== Login to SurveyHeart =====
	    SurveyHeartLoginPage loginPage = new SurveyHeartLoginPage(driver);
	    loginPage.clickSignInUsingEmail();
	    loginPage.enterEmail("gofaw36836@pacfut.com");
	    loginPage.clickNext();
	    loginPage.enterPassword("Automation@1");
	    loginPage.clickSignIn();
	    loginPage.closeFeatureSpotlightIfPresent();
	    ExtentManager.getTest().pass("Login successful with email and password.");

	    // ===== Navigate to Quiz Dashboard =====
	    FormDashboardPage formDashboardPage = new FormDashboardPage(driver);
	    
				    formDashboardPage.refreshPage();
				    formDashboardPage.clickQuizzesTab();

	    QuizDashboardPage quizDashboardPage = new QuizDashboardPage(driver);
	    quizDashboardPage.clickCreateQuizButton();

	    // ===== Quiz Builder Page =====
	    QuizBuilderPage quizBuilderPage = new QuizBuilderPage(driver);

	             // Set dynamic Quiz title
				    String dynamicQuizTitle = "QuizDeletion " + System.currentTimeMillis();
				    quizBuilderPage.enterQuizTitle(dynamicQuizTitle);

				    // ==== Add a Short Answer question ====
				    quizBuilderPage.addQuestion(
				        QuizQuestionType.SHORT_ANSWER,
				        "What is your name?",
				        "Madhu",       // Answer
				        null,          // No options
				        -1             // No correct option
				    );
	    
	 // Quiz Settings - Initialize the Quiz settings object with the current WebDriver instance
	   	QuizSettingsPage quizSettingsPage = new QuizSettingsPage(driver);
	   	
				   	quizSettingsPage.clickSettingsButton();
	   				quizSettingsPage.clickSubmitButton();
	   				ExtentManager.getTest().info("Quiz created successfully with: "+dynamicQuizTitle);
				        
					    
	// SharePopup - Initialize the Share popup object with the current WebDriver instance
	   SharePopupPage sharePopupPage = new SharePopupPage(driver);
	   
		   			sharePopupPage.clickCloseIcon();
		   			formDashboardPage.refreshPage();
		   			
		   		 // Come back to Quiz Dashboard
		   			formDashboardPage.clickQuizzesTab();
		   			
		   		// Capture the Quiz title before deletion for verification
		   			String deletedQuizTitle = dynamicQuizTitle;
		   			
		   			quizDashboardPage.clickMoreOptionsForFirstQuiz();
		   			quizDashboardPage.clickDeleteButton();
		   			quizDashboardPage.clickConfirmDeleteButton();
		   			
		   			
		   		//  Wait until the deleted Quiz is no longer visible on the dashboard
				    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
				    wait.until(ExpectedConditions.invisibilityOfElementLocated(
				        By.xpath("//p[@id='card-form-title' and text()='" + deletedQuizTitle + "']")));

				 // After wait, double-check no such Quiz exists
				    List<WebElement> deletedQuiz = driver.findElements(
				        By.xpath("//p[@id='card-form-title' and text()='" + deletedQuizTitle + "']"));

				 // Assert it is completely gone
				    Assert.assertEquals(deletedQuiz.size(), 0, "Quiz still exists on the dashboard after deletion!");

				 // Log deletion success
				    ExtentManager.getTest().pass("Quiz was deleted successfully: <b>" + deletedQuizTitle+"<b>");
		   		
							
		   			
	}

}

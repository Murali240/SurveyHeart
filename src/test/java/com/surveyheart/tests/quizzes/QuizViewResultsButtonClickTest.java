package com.surveyheart.tests.quizzes;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.surveyheart.base.BaseTest;
import com.surveyheart.enums.QuizQuestionType;
import com.surveyheart.pages.FormDashboardPage;
import com.surveyheart.pages.QuizBuilderPage;
import com.surveyheart.pages.QuizDashboardPage;
import com.surveyheart.pages.QuizPage;
import com.surveyheart.pages.QuizResultsPage;
import com.surveyheart.pages.QuizSettingsPage;
import com.surveyheart.pages.SharePopupPage;
import com.surveyheart.pages.SubmittedPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;
import com.surveyheart.utilities.WaitUtils;


/** Test class to verify that clicking 'View Results' button opens the answer sheet correctly */
@Listeners(com.surveyheart.listeners.TestListener.class)
public class QuizViewResultsButtonClickTest extends BaseTest {

    @Test 
    public void verifyViewResultsButtonOpensAnswerSheet() {
        
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

        // ===== Quiz Dashboard =====
        QuizDashboardPage quizDashboardPage = new QuizDashboardPage(driver);
        			quizDashboardPage.clickCreateQuizButton();                // Click on +Create Quiz button

        // ===== Quiz Builder Page =====
        QuizBuilderPage quizBuilderPage = new QuizBuilderPage(driver);

			     // Set dynamic Quiz title
			        String dynamicQuizTitle = "QuizViewResults " + System.currentTimeMillis();
			        quizBuilderPage.enterQuizTitle(dynamicQuizTitle);
			
			     // Add a Short Answer question
			        quizBuilderPage.addQuestion(
			            QuizQuestionType.SHORT_ANSWER,
			            "What is your name?",
			            "Madhu",     // Answer
			            null,        // No options
			            -1           // No correct option
			        );

        // ===== Quiz Settings =====
        QuizSettingsPage quizSettingsPage = new QuizSettingsPage(driver);
        
			        quizSettingsPage.clickSettingsButton();
			        quizSettingsPage.clickControlTab();
			        quizSettingsPage.enableAllowMultipleAttempts(true);
			        ExtentManager.getTest().pass("<b>'Allow Multiple Attempts'</b> checkbox enabled successfully.");
			        quizSettingsPage.clickSubmitButton();
			        ExtentManager.getTest().info("Quiz created successfully with: " + dynamicQuizTitle);

        // ===== SharePopup =====
        SharePopupPage sharePopupPage = new SharePopupPage(driver);
        
			        String parentWindow = driver.getWindowHandle();   // Store parent
			        sharePopupPage.clickViewIcon();

			     // Switch to Quiz Window
			        for (String windowHandle : driver.getWindowHandles()) {
			            if (!windowHandle.equals(parentWindow)) {
			                driver.switchTo().window(windowHandle);
			                break;
			            }
			        }

        // ===== Quiz Page =====
        QuizPage quizPage = new QuizPage(driver);
        
			        quizPage.clickStart();
			        quizPage.enterName("mad");
			        quizPage.clickStartQuiz();
			
			        String quizTitle = quizPage.getQuizTitle();
			        quizPage.answerShortQuestion("Madhu");
			        String totalQuestionCount = quizPage.getTotalQuestionCount();
			        ExtentManager.getTest().info("Quiz page displayed with " + totalQuestionCount + " question."); 
			        quizPage.clickSubmitButton();
			        ExtentManager.getTest().pass("Entered answer for given question and successfully submitted the Quiz: <b>" + quizTitle + "</b>");

        // ===== Submitted Page =====
        SubmittedPage submittedPage = new SubmittedPage(driver);
        
			        String submittedPageWindow = driver.getWindowHandle();   // Store Submitted page window
			
			     // Store all handles before clicking "View Results"
			        Set<String> handlesBefore = driver.getWindowHandles();
			
			     // Click 'View Results' button
			        submittedPage.clickViewResultsButton();
			        ExtentManager.getTest().pass("Successfully clicked the <b>‘View Results’</b> button on the <b>‘Submitted’</b> page.");
			
			     // Wait until a new window appears
			        new WebDriverWait(driver, Duration.ofSeconds(10)).until(d -> driver.getWindowHandles().size() > handlesBefore.size());
			
			     // Identify new window (Answer Sheet)
			        Set<String> handlesAfter = driver.getWindowHandles();
			        handlesAfter.removeAll(handlesBefore);  // Only new handle remains
			        String answerSheetWindow = handlesAfter.iterator().next();
			
			     // Switch to Answer Sheet window
			        driver.switchTo().window(answerSheetWindow);
			        ExtentManager.getTest().info("Switched to <b>View Results</b> window successfully.");

        // ===== Quiz Results Page =====
        QuizResultsPage quizResultsPage = new QuizResultsPage(driver);

			        String viewResultsQuizTitle = quizResultsPage.getViewResultsQuizTitle().getText();
			        ExtentManager.getTest().pass("Retrieved quiz title name from <b>View Results</b> page: <b>" + viewResultsQuizTitle + "</b>");
			
			        WaitUtils.waitForSeconds(driver, 2);
			        String viewResultsUserName = quizResultsPage.getViewResultsUserName().getText();
			        String viewResultsSubmittedTime = quizResultsPage.getViewResultsSubmittedTime().getText();
			        String viewResultsScorePercentage = quizResultsPage.getViewResultsScorePercentage().getText();
			        
			        
			        ExtentManager.getTest().info("View Results → User - <b>" + viewResultsUserName 
			                + " |</b> Submitted Time - <b>" + viewResultsSubmittedTime 
			                + " |</b> Score Percentage - <b>" + viewResultsScorePercentage+"</b>");
			
			        String userSubmittedAnswer = quizResultsPage.getViewResultsUserSubmittedAnswer().getText();
			        ExtentManager.getTest().info("User submitted answer text in Answer sheet page: " + userSubmittedAnswer);
			
			        String actualAnswer = userSubmittedAnswer;
			        String expectedAnswer = "Madhu";
			
			        Assert.assertEquals(actualAnswer, expectedAnswer, "Answer text does not match with expected answer");
			        ExtentManager.getTest().pass("Verified quiz answer on <b>Answer Sheet</b> page: <b>" + actualAnswer + "</b>");

			    
  
    }

}


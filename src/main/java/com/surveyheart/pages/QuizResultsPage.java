package com.surveyheart.pages;



import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QuizResultsPage {

    private WebDriver driver;
    private WebDriverWait wait;
    
    /** Constructor initializes PageFactory elements and explicit wait */
    public QuizResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }
    

 // ===================== Web Elements with @FindBy =====================
    
    /** Quiz title text in View Results */
    @FindBy(xpath = "//span[@class='quiz-title-text']")
    private WebElement viewResultsquizTitle;
    
    /** Username in View Results */
    @FindBy(xpath = "//span[@class='user-info-title']")
    private WebElement viewResultsUserName;

    /** Answer submitted time */
    @FindBy(xpath = "//span[@class='response-time']")
    private WebElement submittedTime;
    
    /** Score percentage in View Results */
    @FindBy(xpath = "//div[contains(text(),'%')]")
    private WebElement viewRultsScorePercentage;

    /** Marks evaluation (1/1 or 0/1) */
    @FindBy(xpath = "//span[@class='mark-data']")
    private WebElement marksEvaluation;

    /** User submitted answer text */
    @FindBy(xpath = "//p[@class='response-text']")
    private WebElement userSubmittedAnswer; 
    
    // ====== Quiz Leaderboard ======= //
    
    /** Leaderboard quiz title */
    @FindBy(xpath = "//div[@class='leaderboard-title']")
    private WebElement quizLeaderboardQuizTitle;
    
    /** User rank icon */
    @FindBy(xpath = "//img[contains(@alt,'Rank')]")
    private WebElement userRank;

    /** Score percentage on leaderboard card */
    @FindBy(xpath = "//div[@class='leaderboard-score']")
    private WebElement leaderboardScoreCardPercentage;

    /** Username on leaderboard */
    @FindBy(xpath = "//div[@class='leaderboard-username']")
    private WebElement leaderboardUserName;

    /** Total number of participants */
    @FindBy(xpath = "//div[@class='participant-count-text']")
    private WebElement totalNumberOfParticipants;

    /** "Popular Quizzes" heading */
    @FindBy(xpath = "//h2[normalize-space()='Popular Quizzes']")
    private WebElement popularQuizzesHeading;

    /** Leaderboard share icon */
    @FindBy(xpath = "//img[@class='leaderboard-share']")
    private WebElement leaderboardShareIcon;

    /** "Get Started" button */
    @FindBy(xpath = "//div[@id='GET STARTED']")
    private WebElement getStartedButton;

    /** Scorecard arrow icon */
    @FindBy(xpath = "//img[@alt='Arrow']")
    private WebElement scoreCardArrowIcon;

    /** Logged-in user's position card */
    @FindBy(xpath = "//div[@class='leaderboard-rank-cards responder-card ']")
    private WebElement myPositionCard;  
    

    // --- Getters with WebDriverWait ---
    
    /** Returns the Quiz Title WebElement on View Results page */
    public WebElement getViewResultsQuizTitle() {
        return wait.until(ExpectedConditions.visibilityOf(viewResultsquizTitle));
    }

    /** Returns the User Name WebElement on View Results page */
    public WebElement getViewResultsUserName() {
        return wait.until(ExpectedConditions.visibilityOf(viewResultsUserName));
    }

    /** Returns the Submitted Time WebElement on View Results page */
    public WebElement getViewResultsSubmittedTime() {
        return wait.until(ExpectedConditions.visibilityOf(submittedTime));
    }
    
    /** Returns the Score Percentage WebElement on View Results page */
    public WebElement getViewResultsScorePercentage() {
        return wait.until(ExpectedConditions.visibilityOf(viewRultsScorePercentage));
    }

    /** Returns the Marks Evaluation WebElement (e.g., 1/1 or 0/1) */
    public WebElement getMarksEvaluation() {
        return wait.until(ExpectedConditions.visibilityOf(marksEvaluation));
    }

    /** Returns the User Submitted Answer WebElement */
    public WebElement getViewResultsUserSubmittedAnswer() {
        return wait.until(ExpectedConditions.visibilityOf(userSubmittedAnswer));
    }


 // ------------------- Quiz Leaderboard Methods ------------------- //
    
    /** Returns the Quiz Leaderboard Title WebElement */
    public WebElement getLeaderboardQuizTitle() {
        return wait.until(ExpectedConditions.visibilityOf(quizLeaderboardQuizTitle));
    }
    
    /** Returns the User Rank WebElement in Leaderboard */
    public WebElement getUserRank() {
        return wait.until(ExpectedConditions.visibilityOf(userRank));
    }

    /** Returns the Score Percentage WebElement in Leaderboard */
    public WebElement getLeaderboardScoreCardPercentage() {
        return wait.until(ExpectedConditions.visibilityOf(leaderboardScoreCardPercentage));
    }

    /** Returns the Username WebElement in Leaderboard */
    public WebElement getLeaderboardUserName() {
        return wait.until(ExpectedConditions.visibilityOf(leaderboardUserName));
    }

    /** Returns the Total Participants WebElement in Leaderboard */
    public WebElement getTotalNumberOfParticipants() {
        return wait.until(ExpectedConditions.visibilityOf(totalNumberOfParticipants));
    }

    /** Returns the "Popular Quizzes" Heading WebElement */
    public WebElement getPopularQuizzesHeading() {
        return wait.until(ExpectedConditions.visibilityOf(popularQuizzesHeading));
    }

    /** Returns the "Get Started" Button WebElement */
    public WebElement getGetStartedButton() {
        return wait.until(ExpectedConditions.visibilityOf(getStartedButton));
    }

    /** Clicks on the Leaderboard Share Icon */
    public void clickLeaderboardShareIcon() {
        WebElement icon = wait.until(ExpectedConditions.elementToBeClickable(leaderboardShareIcon));
        icon.click();
    }

    /** Clicks on the Scorecard Arrow Icon */
    public void clickScoreCardArrowIcon() {
        WebElement icon = wait.until(ExpectedConditions.elementToBeClickable(scoreCardArrowIcon));
        icon.click();
    }
    
 // ------------------- Leaderboard Data Model ------------------- //
 
    /** Data class to hold leaderboard details: username, rank, scorePercentage */
    public static class LeaderboardData {
        public String username;
        public String rank;
        public String scorePercentage;

        public LeaderboardData(String username, String rank, String scorePercentage) {
            this.username = username;
            this.rank = rank;
            this.scorePercentage = scorePercentage;
        }

        @Override
        public String toString() {
            return "Username: <b>" + username + "</b> | Rank: <b>" + rank + "</b> | Score: <b>" + scorePercentage+"</b>";
        }
    }  
    
    /** Fetches Leaderboard details (username, rank, score) for a given position. 
     * @param position leaderboard rank card index (1 = Top Ranker, 2-10 = other positions)  */
    public LeaderboardData getLeaderboardScorecardDetails(int position) {
        String cardXpath;

        if (position == 1) {
            cardXpath = "//div[@class='leaderboard-rank-cards responder-card leaderboard-top-ranker']";
        } else {
            cardXpath = "(//div[contains(@class,'leaderboard-ranks-container')]/div)[" + position + "]";
        }

        WebElement card = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(cardXpath)));

        String username = card.findElement(By.xpath(".//div[@class='leaderboard-username']")).getText();

        String rank;
        try {
            rank = card.findElement(By.xpath(".//img[contains(@alt,'Rank')]")).getAttribute("alt");
        } catch (Exception e) {
            rank = card.findElement(By.xpath(".//span[contains(@class,'leaderboard-rank')]")).getText();
        }

        String scorePercentage = card.findElement(By.xpath(".//div[@class='leaderboard-score']")).getText();

        return new LeaderboardData(username, rank, scorePercentage);
    }
  
    /** Refreshes the current page */
    public void refreshPage() {
        driver.navigate().refresh();
    }
    
    

    
    
    
    


}

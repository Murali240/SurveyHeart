package com.surveyheart.tests.general;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.surveyheart.base.BaseTest;
import com.surveyheart.pages.FormDashboardPage;
import com.surveyheart.pages.SurveyHeartLoginPage;
import com.surveyheart.utilities.ExtentManager;


@Listeners(com.surveyheart.listeners.TestListener.class)
public class FollowUsTestInMoreOptions extends BaseTest {
	
	@Test
    public void verifyFollowUsSocialLinks() {

	 // Initialize the Login Page object with the current WebDriver instance
		SurveyHeartLoginPage loginPage = new SurveyHeartLoginPage(driver);
			loginPage.clickSignInUsingEmail();
			loginPage.enterEmail("gofaw36836@pacfut.com");
			loginPage.clickNext();
			loginPage.enterPassword("Automation@1");
			loginPage.clickSignIn();
			loginPage.closeFeatureSpotlightIfPresent();                       // In case popup appears after login
			ExtentManager.getTest().pass("Login successful with email and password.");	

     // Initialize the Form Dashboard Page object with the current WebDriver instance
		FormDashboardPage dashboardPage = new FormDashboardPage(driver);
		    dashboardPage.clickMoreDropdown();
		    dashboardPage.clickFollowUs();
	
		    String popupText = dashboardPage.getFollowUsPopupTitle().getText();
		    ExtentManager.getTest().pass("'" + popupText + "' popup is displayed.");   // Retrieving Follow Us popup text 

		 // Facebook
		    String facebookURL = dashboardPage.clickAndFetchSocialURL("//img[@src='../../images/followUs/facebook.svg']");
		    ExtentManager.getTest().info("Facebook URL: " + facebookURL);
	
		 // Instagram
		    String instagramURL = dashboardPage.clickAndFetchSocialURL("//img[@src='../../images/followUs/instagram.svg']");
		    ExtentManager.getTest().info("Instagram URL: " + instagramURL);
	
		 // Twitter
		    String twitterURL = dashboardPage.clickAndFetchSocialURL("//img[@src='../../images/followUs/x-social-media.svg']");
		    ExtentManager.getTest().info("Twitter URL: " + twitterURL);
	
		 // LinkedIn
		    String linkedInURL = dashboardPage.clickAndFetchSocialURL("//img[@src='../../images/followUs/linkedin.svg']");
		    ExtentManager.getTest().info("LinkedIn URL: " + linkedInURL);
	
		 // YouTube
		    String youTubeURL = dashboardPage.clickAndFetchSocialURL("//img[@src='../../images/followUs/youtube.svg']");
		    ExtentManager.getTest().info("YouTube URL: " + youTubeURL);

		
		
	   }
  
  }
	
	
	
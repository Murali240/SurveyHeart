package com.surveyheart.utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/** Utility class for custom wait methods */
public class WaitUtils {

	/** Pauses execution for given seconds using WebDriverWait */
	public static void waitForSeconds(WebDriver driver, int seconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(seconds))
                    .until(d -> false);      // just waits until timeout
        } catch (Exception e) {
            // do nothing - intentional wait
        }
    }







}


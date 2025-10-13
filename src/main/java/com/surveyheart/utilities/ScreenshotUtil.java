package com.surveyheart.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

/** Utility class for capturing and saving screenshots */
public class ScreenshotUtil {
	
	 /** Captures screenshot, saves with timestamp, and returns file path */
	public static String takeScreenshot(WebDriver driver, String baseFileName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);

     // Create "screenshots" folder if it doesn't exist
        String folderPath = System.getProperty("user.dir") + "/screenshots";
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs(); // ✅ Create missing folders
        }

     // Create unique file name with timestamp
        String filePath = folderPath + "/" + baseFileName + "_" + getCurrentTimestamp() + ".png";
        File dest = new File(filePath);
        FileHandler.copy(src, dest);
        return filePath;
    }

    /** Returns current timestamp in yyyyMMdd_HHmmss format */
	private static String getCurrentTimestamp() {
        return new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    }



	



}
	
package com.surveyheart.utilities;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/** Utility class for creating and managing ExtentReports instance */
public class ExtentReportManager {

	/** Holds single ExtentReports instance */
	private static ExtentReports extent;

	/** Returns ExtentReports instance (creates if not already initialized) */
	public static ExtentReports getReporter() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports/TestReport.html");
            extent = new ExtentReports();
            extent.attachReporter(spark);
            
            extent.setSystemInfo("QA Tester", "Murali Krishna Kolusu");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Browser", ConfigReader.getProperty("browser"));
            extent.setSystemInfo("Base URL", ConfigReader.getProperty("baseUrl"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        }
        return extent; 
    }           
   
	/** Returns HTML tag to embed screenshot in Extent Report */
    public static String getScreenshotHtml(String filePath) {
        return "<br><img src='" + filePath + "' width='100%' height='600px'>";
    }

    
    
    
    
    

}   

	






	
	

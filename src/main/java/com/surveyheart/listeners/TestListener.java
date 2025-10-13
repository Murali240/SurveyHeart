package com.surveyheart.listeners;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.surveyheart.base.BaseTest;
import com.surveyheart.utilities.ExtentManager;
import com.surveyheart.utilities.ExtentReportManager;
import com.surveyheart.utilities.ScreenshotUtil;


/** Listener class to handle and log all test events (start, pass, fail, skip, finish) */
public class TestListener extends BaseTest implements ITestListener {

     /** Shared ExtentReports instance to generate a single report across all tests */
	 private static ExtentReports extent = ExtentReportManager.getReporter();

	    @Override
	    public void onTestStart(ITestResult result) {	
	        /** Create a new test entry in ExtentReport for the current test method */
	        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
	        ExtentManager.setTest(test);       // Store test instance in ThreadLocal to support parallel execution
	    }      
	    
	    @Override
	    public void onTestSuccess(ITestResult result) {	
	        /** Log PASS status in ExtentReport when test completes successfully */
	        ExtentManager.getTest().log(Status.PASS, "Test Passed");
	    }    
	    
	    @Override
	    public void onTestFailure(ITestResult result) {
	        String screenshotPath = null;
	        try {
	        	/** Get driver instance from BaseTest to capture screenshot */
	            BaseTest base = (BaseTest) result.getInstance();
	            screenshotPath = ScreenshotUtil.takeScreenshot(base.getDriver(), "Failed_" + result.getMethod().getMethodName());

	            /** Log failure details and attach screenshot in ExtentReport */
	            ExtentManager.getTest().fail("Test Failed: " + result.getThrowable(),
	                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

	            /** Embed screenshot image directly in report (large preview) */
	            ExtentManager.getTest().fail("<br><img src='" + screenshotPath + "' width='100%' height='500'>");
	        } catch (Exception e) {
	        	/** Print stack trace if screenshot or logging fails */
	            e.printStackTrace();
	        }
	    }    
	    
	    @Override
	    public void onTestSkipped(ITestResult result) {
	        /** Log SKIP status in ExtentReport when test is skipped */
	        ExtentManager.getTest().log(Status.SKIP, "Test Skipped: " + result.getThrowable());
	    }

	    @Override
	    public void onFinish(ITestContext context) {
	    	/** Flush all logs and finalize the ExtentReport after test execution ends */
	        extent.flush();     
	    }
	







}


	
	




	
	
	
	
	


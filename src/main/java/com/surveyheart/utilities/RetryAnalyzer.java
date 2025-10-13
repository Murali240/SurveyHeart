package com.surveyheart.utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/** Retry Analyzer to re-run failed tests once */
public class RetryAnalyzer implements IRetryAnalyzer {

	/** Counter to track retries */
	private int retryCount = 0;                 // How many times retried
    
	 /** Maximum retry attempts */
	private static final int maxRetry = 1;      // Retry only once

	/** Retries test if retry count is less than maxRetry */
	@Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetry) {
            retryCount++;
            return true;                        // Retry the test
        }
        return false;
    }







}
	
	
	



package com.surveyheart.utilities;

import com.aventstack.extentreports.ExtentTest;

   /** Manages ExtentTest instances for thread-safe reporting */
   public class ExtentManager {

	   /** Holds ExtentTest instance for each thread */
	   private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	   /** Returns current thread's ExtentTest */    
	   public static ExtentTest getTest() {
	           return extentTest.get();
	       }

	   /** Assigns ExtentTest to current thread */    
	   public static void setTest(ExtentTest test) {
	           extentTest.set(test);
	       }

	   /** Removes ExtentTest from current thread */    
	   public static void removeTest() {
	          extentTest.remove();
	       }
	   
   
	   
   
   
   
   
   
   }



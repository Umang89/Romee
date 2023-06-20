package com.qa.ecs.listeners;

import com.qa.ecs.factory.DriverFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


/**
 * This Class allows you to rerun a failed test method a set amount of times before declaring it as failed.
 *
 * @author Nahian Omar Faruqe
 * @version 1.0
 * @since 2022-09-28
 */
public class Retry implements IRetryAnalyzer {
    private int count = 0;
    private int maxTry = Integer.parseInt(new DriverFactory().init_prop().getProperty("maxFailRun"));
    public static boolean inRetryState = false;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) { // Check if test not succeed        	
            if (count < maxTry) { // Check if maxtry count is reached
                inRetryState = true;
                count++; // Increase the maxTry count by 1
                iTestResult.setStatus(ITestResult.FAILURE); // Mark test as failed
                return true; // Tells TestNG to re-run the test
            } else {
                inRetryState = false;
                iTestResult.setStatus(ITestResult.FAILURE); // If maxCount reached,test marked as failed
            }
        } else {
            inRetryState = false;
            iTestResult.setStatus(ITestResult.SUCCESS); // If test passes, TestNG marks it as passed
        }
        return false;
    }
}
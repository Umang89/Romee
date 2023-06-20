package com.qa.ecs.utils;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.qa.ecs.listeners.ExtentReportListener;

/**
 * This Class is used to provide Customize Assertion with Extent Report Loggers
 *
 * @author Nahian Omar Faruqe
 * @version 1.0
 * @since 2022-09-28
 */
public class AssertionUtil {

    /**
     * This method is used to verify whether two String values are Equal
     *
     * @param actualValue   Actual Value in String Format
     * @param expectedValue Expected Value in String Format
     * @param message       Message which tells the Assertion description
     */
    public static void verifyEqual(String actualValue, String expectedValue, String message) {
        try {
            Assert.assertEquals(actualValue, expectedValue);
            ExtentReportListener.test.get().log(Status.PASS, "Successful : " + message);
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL, "Failed : " + message + "\n" + "\"" + actualValue
                    + "\" and \"" + expectedValue + "\" is not equal.Should be equal");
            Assert.fail("\"" + actualValue + "\" and \"" + expectedValue + "\" is not equal.Should be equal");
        }
    }

    /**
     * This method is used to verify whether two Lists of String are Equal
     *
     * @param actualList   Actual List of String
     * @param expectedList Expected List of String
     * @param message      Message which tells the Assertion description
     */
    public static void verifyEqual(List<String> actualList, List<String> expectedList, String message) {
        try {
            Assert.assertEquals(actualList, expectedList);
            ExtentReportListener.test.get().log(Status.PASS, "Successful : " + message);
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL, "Failed : " + message + ".List should be equal");
            Assert.fail("Actual List and Expected List are not equal. It should be equal");
        }
    }

    /**
     * This method is used to verify whether two Lists of Objects are Equal
     *
     * @param actualList   Actual List of Object
     * @param expectedList Expected List of Object
     * @param message      Message which tells the Assertion description
     */
    public static void verifyEqual(Object actualList, Object expectedList, String message) {
        try {
            Assert.assertEquals(actualList, expectedList);
            ExtentReportListener.test.get().log(Status.PASS, "Successful : " + message);
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL, "Failed : " + message + ".List should be equal");
            Assert.fail("Actual List and Expected List are not equal. It should be equal");
        }
    }


    /**
     * This method is used to verify whether two Numeric Values are Equal
     *
     * @param actualValue   Actual Value in int Format
     * @param expectedValue Expected Value in int Format
     * @param message       Message which tells the Assertion description
     */
    public static void verifyEqual(int actualValue, int expectedValue, String message) {
        try {
            Assert.assertEquals(actualValue, expectedValue);
            ExtentReportListener.test.get().log(Status.PASS, "Successful : " + message);
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL, "Failed : " + message + "\n" + "\"" + actualValue
                    + "\" and \"" + expectedValue + "\" is not equal.Should be equal");
            Assert.fail("\"" + actualValue + "\" and \"" + expectedValue + "\" is not equal.Should be equal");
        }
    }

    /**
     * This method is used to verify whether two Boolean Values are Equal
     *
     * @param actualValue   Actual Value in boolean Format
     * @param expectedValue Expected Value in boolean Format
     * @param message       Message which tells the Assertion description
     */
    public static void verifyEqual(boolean actualValue, boolean expectedValue, String message) {
        try {
            Assert.assertEquals(actualValue, expectedValue);
            ExtentReportListener.test.get().log(Status.PASS, "Successful : " + message);
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL, "Failed : " + message + "\n" + "\"" + actualValue
                    + "\" and \"" + expectedValue + "\" is not equal.Should be equal");
            Assert.fail("\"" + actualValue + "\" and \"" + expectedValue + "\" is not equal.Should be equal");
        }
    }

    /**
     * This method is used to verify whether First Value is Greater than Second
     * Value
     *
     * @param expectedGreaterValue Value Expected to be Greater in int Format
     * @param expectedSmallerValue Value Expected to be Smaller in int Format
     * @param message              Message which tells the Assertion description
     */
    public static void verifyGreaterThan(int expectedGreaterValue, int expectedSmallerValue, String message) {
        try {
            Assert.assertEquals(expectedGreaterValue > expectedSmallerValue, true);
            ExtentReportListener.test.get().log(Status.PASS, "Successful : " + message);
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL,
                    "Failed : " + message + "\n" + "\"" + expectedGreaterValue + "\" is not greater than \""
                            + expectedSmallerValue + "\". 1st value should be Greater than 2nd value");
            Assert.fail("\"" + expectedGreaterValue + "\" is not grater than \"" + expectedSmallerValue
                    + "\". 1st value should be Greater than 2nd value");
        }
    }

    /**
     * This method is used to verify whether two String values are not Equal
     *
     * @param actualValue   Actual Value in String Format
     * @param expectedValue Expected Value in String Format
     * @param message       Message which tells the Assertion description
     */
    public static void verifyNotEqual(String actualValue, String expectedValue, String message) {
        try {
            Assert.assertNotEquals(actualValue, expectedValue);
            ExtentReportListener.test.get().log(Status.PASS, "Successful : " + message);
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL, "Failed : " + message + "\n" + "\"" + actualValue
                    + "\" and \"" + expectedValue + "\" is equal.Should not be equal");
            Assert.fail("\"" + actualValue + "\" and \"" + expectedValue + "\" is equal.Should not be equal");
        }
    }

    /**
     * This method is used to verify whether two Numeric values are not Equal
     *
     * @param actualValue   Actual Value in int Format
     * @param expectedValue Expected Value in int Format
     * @param message       Message which tells the Assertion description
     */
    public static void verifyNotEqual(int actualValue, int expectedValue, String message) {
        try {
            Assert.assertNotEquals(actualValue, expectedValue);
            ExtentReportListener.test.get().log(Status.PASS, "Successful : " + message);
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL, "Failed : " + message + "\n" + "\"" + actualValue
                    + "\" and \"" + expectedValue + "\" is equal.Should not be equal");
            Assert.fail("\"" + actualValue + "\" and \"" + expectedValue + "\" is equal.Should not be equal");
        }
    }

    /**
     * This method is used to intentionally Fail and Log the Failure Message
     *
     * @param message Message which tells the Assertion description
     */
    public static void fail(String message) {
        ExtentReportListener.test.get().log(Status.FAIL, "Failed : " + message);
        Assert.fail(message);
    }

    /**
     * This method is used to verify whether First Value contains the Second Value
     *
     * @param actualText      Value in which Sub value verify its existence
     * @param expectedSubText SubString
     * @param message         Message which tells the Assertion description
     */
    public static void verifyContainsText(String actualText, String expectedSubText, String message) {
        try {
            Assert.assertEquals(actualText.contains(expectedSubText), true);
            ExtentReportListener.test.get().log(Status.PASS, "Successful : " + message);
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL, "Failed : " + message + "\n" + "\"" + actualText
                    + " is not contain " + expectedSubText + "." + actualText + " should contains " + expectedSubText);

            Assert.fail(actualText + " is not contain " + expectedSubText + "." + actualText + " should contains "
                    + expectedSubText);
        }
    }

    /**
     * This method is used to verify whether First Value is Greater than or equal to Second
     * Value
     *
     * @param expectedGreaterValue Value Expected to be Greater in int Format
     * @param expectedSmallerValue Value Expected to be Smaller in int Format
     * @param message              Message which tells the Assertion description
     */
    public static void verifyGreaterThanOrEqualTo(int expectedGreaterValue, int expectedSmallerValue, String message) {
        try {
            Assert.assertEquals(expectedGreaterValue >= expectedSmallerValue, true);
            ExtentReportListener.test.get().log(Status.PASS, "Successful : " + message);
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL,
                    "Failed : " + message + "\n" + "\"" + expectedGreaterValue + "\" is not greater than \""
                            + expectedSmallerValue + "\". 1st value should be Greater than 2nd value");
            Assert.fail("\"" + expectedGreaterValue + "\" is not grater than \"" + expectedSmallerValue
                    + "\". 1st value should be Greater than 2nd value");
        }
    }

}

package utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import listeners.TestListener;

public class LoggerUtil {

    private static ExtentTest getSafeTest() {
        ExtentTest test = TestListener.getTest();
        if (test == null) {
            System.out.println("[LoggerUtil] WARNING: ExtentTest is null");
        }
        return test;
    }

    public static void info(String message) {
        ExtentTest test = getSafeTest();
        if (test != null) test.log(Status.INFO, message);
    }

    public static void pass(String message) {
        ExtentTest test = getSafeTest();
        if (test != null) test.log(Status.PASS, message);
    }

    public static void fail(String message) {
        ExtentTest test = getSafeTest();
        if (test != null) test.log(Status.FAIL, message);
    }

    public static void skip(String message) {
        ExtentTest test = getSafeTest();
        if (test != null) test.log(Status.SKIP, message);
    }
}

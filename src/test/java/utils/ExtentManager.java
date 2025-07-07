package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * ExtentManager is a utility class that manages the creation and configuration of the ExtentReports instance.
 * It sets up the report with a document title and report name.
 */

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports createInstance(String fileName) {
        ExtentSparkReporter spark = new ExtentSparkReporter(fileName);
        spark.config().setDocumentTitle("API Automation Report");
        spark.config().setReportName("API Test Results");

        extent = new ExtentReports();
        extent.attachReporter(spark);
        return extent;
    }
}

package commons;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.util.HashMap;
import java.util.Map;

public class ExtentManager {
    private static final ExtentReports extentReports = new ExtentReports();
    private static final Map<Integer, ExtentTest> extentTestMap = new HashMap<>();

    static {
        ExtentSparkReporter reporter = new ExtentSparkReporter("target/extent-report/Orange-HRM-Report.html");
        reporter.config().setDocumentTitle("Orange HRM HTML Report");
        reporter.config().setReportName("Orange HRM Automation Report");
        reporter.config().setTheme(Theme.STANDARD);
        reporter.config().setEncoding("utf-8");

        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Project", "Orange HRM");
        extentReports.setSystemInfo("Author", "Tan Nong");
        extentReports.setSystemInfo("JDK", System.getProperty("java.version"));
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
    }

    public static synchronized ExtentTest startTest(String name) {
        ExtentTest test = extentReports.createTest(name);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }

    public static synchronized void flushReports() {
        extentReports.flush();
    }
}

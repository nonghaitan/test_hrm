package commons.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class UIExtentManager {

    private static ExtentReports extent;

    public static synchronized ExtentReports getReport() {
        if (extent == null) {
            String fileName = "target/extent-report/UI-Report.html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(fileName);
            reporter.config().setDocumentTitle("UI HTML Report");
            reporter.config().setReportName("UI Automation Report");
            reporter.config().setTheme(Theme.STANDARD);
            reporter.config().setEncoding("utf-8");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Project", "Orange HRM");
            extent.setSystemInfo("Author", "Tan Nong");
            extent.setSystemInfo("JDK", System.getProperty("java.version"));
            extent.setSystemInfo("OS", System.getProperty("os.name"));
        }
        return extent;
    }

    public static synchronized void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}





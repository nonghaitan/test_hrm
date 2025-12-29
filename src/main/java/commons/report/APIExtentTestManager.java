package commons.report;

import com.aventstack.extentreports.ExtentTest;

public class APIExtentTestManager {

    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static synchronized ExtentTest getTest() {
        return extentTest.get();
    }

    public static synchronized void setTest(ExtentTest test) {
        extentTest.set(test);
    }

    public static synchronized ExtentTest createTest(String name) {
        ExtentTest test = APIExtentManager.getReport().createTest(name);
        setTest(test);
        return test;
    }

    public static synchronized void removeTest() {
        extentTest.remove();
    }
}




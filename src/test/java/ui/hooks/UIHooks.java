package ui.hooks;

import commons.base.GlobalConstants;
import commons.report.UIExtentTestManager;
import commons.report.UIExtentManager;
import io.cucumber.java.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.log4testng.Logger;

import java.time.Duration;
import java.util.Base64;

public class UIHooks {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger log = Logger.getLogger(UIHooks.class);

    public static WebDriver getDriver() {
        return driver.get();
    }


    @Before(order = 0)
    public void setupReport(Scenario scenario) {
        UIExtentTestManager.createTest(scenario.getName())
                .info("Feature: " + scenario.getUri());
    }


    @Before(order = 1)
    public void openBrowser() {
        ChromeOptions chrome = new ChromeOptions();
        chrome.addArguments("--remote-allow-origins=*", "--start-maximized");
        WebDriver webDriver = new ChromeDriver(chrome);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.UI_LONG_TIMEOUT));
        driver.set(webDriver);
        log.info("Browser Started");
    }


    @After(order = 0)
    public void afterScenario(Scenario scenario) {
        WebDriver webDriver = getDriver();
        if (webDriver == null) return;

        try {
            if (scenario.isFailed()) {
                byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
                UIExtentTestManager.getTest()
                        .fail("Scenario Failed")
                        .addScreenCaptureFromBase64String(Base64.getEncoder().encodeToString(screenshot));
            } else {
                UIExtentTestManager.getTest().pass("Scenario Passed");
            }
        } catch (Exception e) {
            log.info("Failed to log scenario result: " + e.getMessage());
        } finally {
            webDriver.quit();
            driver.remove();
            UIExtentTestManager.removeTest();
            log.info("Browser Closed");
        }
    }

    @AfterAll
    public static void flushReport() {
        UIExtentManager.flushReport();
    }
}




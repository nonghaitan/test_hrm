package commons;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.testng.log4testng.Logger;

import java.time.Duration;
import java.util.Base64;

public class Hooks {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger log = Logger.getLogger(Hooks.class);

    public static WebDriver getDriver() {
        return driver.get();
    }

    @Before(order = 0)
    public void setupReport(Scenario scenario) {
        ExtentTestManager.createTest(scenario.getName());
    }

    @Before(order = 1)
    public void openBrowser() {
        String browser = System.getProperty("BROWSER");
        if (browser == null || browser.trim().isEmpty()) browser = System.getenv("BROWSER");
        if (browser == null || browser.trim().isEmpty()) browser = "chrome";

        log.info("Browser = " + browser);
        WebDriver webDriver;
        try {
            switch (browser.toLowerCase()) {
                case "edge":
                    EdgeOptions edge = new EdgeOptions();
                    edge.addArguments("--start-maximized");
                    webDriver = new EdgeDriver(edge);
                    break;
                case "firefox":
                    FirefoxOptions ff = new FirefoxOptions();
                    webDriver = new FirefoxDriver(ff);
                    webDriver.manage().window().maximize();
                    break;
                case "chrome":
                default:
                    ChromeOptions chrome = new ChromeOptions();
                    chrome.addArguments("--remote-allow-origins=*");
                    chrome.addArguments("--start-maximized");
                    webDriver = new ChromeDriver(chrome);
                    break;
            }
        } catch (Exception e) {
            log.info("Browser start failed. Fallback chrome");
            webDriver = new ChromeDriver();
        }

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.set(webDriver);
        log.info("------------- Browser Started -------------");
    }

    @After
    public void afterScenario(Scenario scenario) {
        WebDriver webDriver = getDriver();

        if (scenario.isFailed() && webDriver != null) {
            byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
            ExtentTestManager.getTest()
                    .fail("Scenario Failed")
                    .addScreenCaptureFromBase64String(Base64.getEncoder().encodeToString(screenshot));
        } else {
            ExtentTestManager.getTest().pass("Scenario Passed");
        }

        if (webDriver != null) {
            try {
                webDriver.quit();
                driver.remove();
                log.info("------------- Browser Closed -------------");
            } catch (UnreachableBrowserException e) {
                log.info("Browser already closed.");
            }
        }

        ExtentManager.flushReports();
    }
}

package commons;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks extends BaseTest{
    public static ScenarioContext context;

//    @Before
//    public void beforeScenario(){
//        String browser = System.getProperty("browser", "chrome");
//        WebDriver driver = getBrowserDriver(browser);
//        context = new ScenarioContext();
//    }
//
//    @After
//    public void cleanUp(){
//        DriverManager.quitDriver();
//    }

    @Before
    public void beforeScenario(Scenario scenario) {
        WebDriver driver = getBrowserDriver.initDriver(System.getProperty("browser", "chrome"));

        DriverManager.setDriver(driver);
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failed Screenshot");
        }
        DriverManager.quit();
    }
}

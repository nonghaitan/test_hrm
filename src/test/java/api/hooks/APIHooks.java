package api.hooks;

import commons.report.APIExtentTestManager;
import commons.report.APIExtentManager;
import io.cucumber.java.*;
import org.testng.log4testng.Logger;

public class APIHooks {

    private static final Logger log = Logger.getLogger(APIHooks.class);

    @Before(order = 0)
    public void setupReport(Scenario scenario) {
        APIExtentTestManager.createTest(scenario.getName())
                .info("Feature: " + scenario.getUri());
    }


    @After(order = 0)
    public void afterScenario(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                APIExtentTestManager.getTest().fail("Scenario Failed");
            } else {
                APIExtentTestManager.getTest().pass("Scenario Passed");
            }
        } catch (Exception e) {
            log.info("Failed to log scenario result: " + e.getMessage());
        } finally {
            APIExtentTestManager.removeTest();  // Clear ThreadLocal
        }
    }


    @AfterAll
    public static void flushReport() {
        APIExtentManager.flushReport();
    }
}




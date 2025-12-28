package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/api",
        glue = {"steps.api", "commons"},
        plugin = {"pretty", "html:target/cucumber-api-report.html"}
)
public class ApiTestRunner extends AbstractTestNGCucumberTests {
}

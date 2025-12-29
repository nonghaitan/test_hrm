package api.runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/api",
        glue = {"api.steps", "api.hooks"},
        plugin = {"pretty"}
)
public class APITestRunner extends AbstractTestNGCucumberTests {
}

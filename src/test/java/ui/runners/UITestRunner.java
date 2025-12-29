package ui.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "src/test/resources/features/ui",
        glue = {"ui.steps", "ui.hooks"},
        plugin = {"pretty"}
)
public class UITestRunner extends AbstractTestNGCucumberTests {

}

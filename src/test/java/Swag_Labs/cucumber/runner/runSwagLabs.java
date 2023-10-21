package Swag_Labs.cucumber.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/Swag_Labs/cucumber/features",
        glue = "Swag_Labs.cucumber.stepDef",
        plugin = {"html:target/HTML_report.html"}
)

public class runSwagLabs {
}

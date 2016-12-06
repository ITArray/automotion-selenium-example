package net.itarray.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty",
        "html:target/site/cucumber-pretty",
        "json:target/cucumber.json" },
        glue = "net/itarray/tests",
        features = "src/test/resources/features")
public class RunnerAutomotionTest {
}
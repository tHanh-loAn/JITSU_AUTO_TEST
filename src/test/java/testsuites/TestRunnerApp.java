package testsuites;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import utilities.CommonWebController;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = {"steps"},
        plugin = {"pretty", "html:target/cucumber-report.html"},
        tags = "@app_route"
)
public class TestRunnerApp {
    @AfterClass
    public static void tearDown() {
        CommonWebController.quitDriver();
    }
}

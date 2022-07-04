package Runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features ="src/test/java/Feature",
        glue = "stepDefinition",
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                    "html:target/cucumber.html",
                    "me.jvt.cucumber.report.PrettyReports:target/cucumber"})
public class TestRunner {


}

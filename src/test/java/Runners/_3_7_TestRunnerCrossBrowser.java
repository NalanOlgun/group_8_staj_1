package Runners;


import Utilities.Driver;
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

@CucumberOptions(
        tags = {"@RegressionTest"},
        features = {"src\\test\\java\\FeatureFiles"},
        glue = {"StepDefinitions"},
        dryRun = false,
        plugin = {
                "com.cucumber.listener.ExtentCucumberFormatter:target/ExtentReport/ExtentReportRegression.html"

        }
)
public class _3_7_TestRunnerCrossBrowser extends AbstractTestNGCucumberTests {

    @BeforeClass
    @Parameters("browser")
    public static void beforeClass(String browser)
    {
        Driver.threadBrowserName.set(browser);
    }

    @AfterClass
    public static void afterClass()
    {

        Reporter.loadXMLConfig("src/test/java/XMLFiles/extendReportSet.xml");
        Reporter.setSystemInfo("User Name", "Group 8");
        Reporter.setSystemInfo("Application Name", "Basqar");
        Reporter.setSystemInfo("Operating System Info", System.getProperty("os.name"));
        Reporter.setSystemInfo("Department", "QA");
        Reporter.setSystemInfo("Gorev:", "Proje Bölüm-3.7.");
        Reporter.setTestRunnerOutput("Test excetuion Cucumber report");

    }

}

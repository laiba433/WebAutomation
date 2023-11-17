package TestRunner;

import StepDefinitions.GeneralControls.Browser;
import StepDefinitions.GeneralControls.General;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/java/Features/login.feature"},
        glue = {"StepDefinitions"},
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
        //tags = "@reg"
)
public class testrunner extends AbstractTestNGCucumberTests {

    @BeforeSuite(alwaysRun = true)
    public void setup()
    {

        main.java.Manager.GlobalVariables.SetGlobalVariables();
        Browser.Instance().open_browser("Chrome");
        Browser.Instance().go_to_the_url("https://www.saucedemo.com/");
    }

    @AfterSuite(alwaysRun = true)
    public void quit()
    {
        General.Instance().Wait(5000);
        Browser.Instance().CloseBrowser();

    }
}
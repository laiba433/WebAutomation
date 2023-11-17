package TestRunner;

import StepDefinitions.GeneralControls.Browser;
import StepDefinitions.GeneralControls.General;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks
{
    @Before
    public void setup()
    {
        main.java.Manager.GlobalVariables.SetGlobalVariables();
        Browser.Instance().open_browser("Chrome");
        Browser.Instance().go_to_the_url("https://www.saucedemo.com/");
    }

    @After
    public void quit()
    {
        General.Instance().Wait(5000);
        Browser.Instance().CloseBrowser();
    }
}

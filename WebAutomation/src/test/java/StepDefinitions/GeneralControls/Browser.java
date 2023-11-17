package StepDefinitions.GeneralControls;

import main.java.Factory.factory;
import main.java.Helper.GeneralHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.net.MalformedURLException;
import java.net.URL;

public class Browser {
	public static Browser Instance=null;
	public static Browser Instance()
	{
		{
			if(Instance== null)
			{
				Instance = new Browser();
			}
			return Instance;
		}
	}
	@Given("Open Browser {string}")
	public void open_browser(String Browser) {
	   factory.getDriver(Browser);
	   MaximizeWindow();
	}


	@Given("Go to the Url {string}")
	public void go_to_the_url(String url) {
		  factory.driver.get(url);
		  System.out.println(factory.driver.getWindowHandle());
	}

	@And("Maximize Window")
	public void MaximizeWindow()
	{
		GeneralHelper.MaximizeWindow(factory.driver);
	}

	@And("Switch to another window {string}")
	public static void SwitchToNewWindow(String WindowName)
	{
		GeneralHelper.SwitchToNewWindow(WindowName);
	}
	@And("Switch to another frame {string}")
	public static void SwitchToFrame(String FrameName)
	{
		GeneralHelper.SwitchToFrame(factory.driver,FrameName);
	}
@And("Navigate to the url {string}")
public static void NavigateToUrl(String Url)
{
	try
	{
		factory.driver.navigate().to(new URL(Url));
	}
	catch (MalformedURLException e)
	{
		throw new RuntimeException(e);
	}
}
	@Then("Close Browser")
	public void CloseBrowser()
	{
		  factory.driver.quit();
	}
	public static void SwitchTab(int position)
	{
		factory.driver.getWindowHandles().forEach(tab-> factory.driver.switchTo().window(tab));
	}

}

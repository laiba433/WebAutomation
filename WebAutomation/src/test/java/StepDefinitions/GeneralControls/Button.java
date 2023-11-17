package StepDefinitions.GeneralControls;

import io.cucumber.java.en.And;
import main.java.Factory.factory;
import main.java.Helper.ElementFinder;
import main.java.Logger.MyLogger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Button
{
    public static Button Instance=null;
    public static Button Instance()
    {
        {
            if(Instance== null)
            {
                Instance = new Button();
            }
            return Instance;
        }
    }
    @And("Get Button Text {string}")
    public String GetButtonText(String Key)
    {   String FoundText="";
        WebElement element= ElementFinder.Instance().FindElement(Key, factory.driver);
        if(element == null)
        {
            Assert.fail();
        }
        else
        {
            FoundText= element.getText();
        }

        return FoundText;
    }

    @And("Click Button {string}")
    public void ClickButton(String Key)
    {
        WebElement element= ElementFinder.Instance().FindElement(Key, factory.driver);
        element.click();
        MyLogger.log.info(Key+ "Button has been clicked");
    }

}


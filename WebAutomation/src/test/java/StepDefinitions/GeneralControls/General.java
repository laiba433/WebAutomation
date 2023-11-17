package StepDefinitions.GeneralControls;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import main.java.Helper.GeneralHelper;

public class General
{
    public static General Instance=null;
    public static General Instance(){
        {
            if(Instance== null)
            {
                Instance = new General();

            }
            return Instance;
        }
    }
    @And("Wait for {int}")
    public void Wait(int TimeInMilliseconds)
    {
        GeneralHelper.Instance().Wait(TimeInMilliseconds);
    }

    @And("Wait for Visibilty till {int}")
    public  void WaitForVisibility(int TimeInSeconds, String ElementKey)
    {
        GeneralHelper.Instance().WaitForVisibility(TimeInSeconds,ElementKey);
    }

    @And("Wait Until Alert is present")
    public  void WaitUntilAlertIsPresent(){
        GeneralHelper.Instance().WaitUntilAlertIsPresent();
    }

    @And("Log Message {string} {string}")
    public void LogMessage(String LoggingType, String Message)
    {
        GeneralHelper.Instance().LogMessage(LoggingType,Message);
    }

    @Then("Validate the Presence of element {string}")
    public void CheckPresenceOfElement(String Key)
    {
        GeneralHelper.Instance().CheckPresenceOfElement(Key);
    }

}

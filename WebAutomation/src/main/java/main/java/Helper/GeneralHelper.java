package main.java.Helper;

import main.java.Factory.factory;
import main.java.Logger.MyLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


    public class GeneralHelper
    {
        public static GeneralHelper Instance=null;
        public static GeneralHelper Instance(){
            {
                if(Instance== null)
                {
                    Instance = new GeneralHelper();

                }
                return Instance;
            }
        }

        public void Wait(int TimeInMilliseconds){
            try
            {
                Thread.sleep(TimeInMilliseconds);
            }
            catch (InterruptedException e)
            {
                MyLogger.log.error(e);
                throw new RuntimeException(e);
            }
        }
        public void SwipeUP() {
            JavascriptExecutor js = (JavascriptExecutor) factory.driver;
            js.executeScript("window.scrollBy(0,350)", "");
        }
        public void GoToTheTopOfPage()
        {
            System.out.println("Going to the top of the page");
            ((JavascriptExecutor) factory.driver)
                    .executeScript("window.scrollTo(0, -document.body.scrollHeight)");
        }
        public  void WaitForVisibility(int TimeInSeconds, String ElementKey)
        {
            WebDriverWait wait = new WebDriverWait(factory.driver, Duration.ofSeconds(TimeInSeconds));
            try
            {
                wait.until(ExpectedConditions.visibilityOfElementLocated(ElementFinder.GetLocator(ElementKey)));
            }
            catch(Exception ex)
            {
                MyLogger.log.error(ex);
            }
        }
        public  void WaitForVisibilityByLocator(int TimeInSeconds, By ByLocator)
        {
            WebDriverWait wait = new WebDriverWait(factory.driver, Duration.ofSeconds(TimeInSeconds));
            try
            {
                wait.until(ExpectedConditions.visibilityOfElementLocated(ByLocator));
            }
            catch(Exception ex)
            {
                MyLogger.log.error(ex);
            }
        }

        public  void WaitForVisibilityofElements(int TimeInSeconds, String ElementKey)
        {
            WebDriverWait wait = new WebDriverWait(factory.driver, Duration.ofSeconds(TimeInSeconds));
            try
            {
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ElementFinder.GetLocator(ElementKey)));
            }
            catch(Exception ex)
            {
                MyLogger.log.error(ex);
            }
        }
        public void ScrollDown()
        {
            JavascriptExecutor js = (JavascriptExecutor) factory.driver;
            js.executeScript("window.scrollBy(0,600)", "");
        }
        public  void WaitUntilAlertIsPresent()
        {
            WebDriverWait wait = new WebDriverWait(factory.driver, Duration.ofSeconds(5));
            try
            {
                wait.until(ExpectedConditions.alertIsPresent());
            }
            catch (Exception ex)
            {
                MyLogger.log.error(ex);
            }
        }



        public void LogMessage(String LoggingType, String Message){
            switch (LoggingType)
            {
                case "Debug":
                {
                    MyLogger.log.debug(Message);
                    break;
                }
                case "Info":
                {
                    MyLogger.log.info(Message);
                    break;
                }
                case "Error":
                {
                    MyLogger.log.error(Message);
                    break;
                }
                case "Warn":
                {
                    MyLogger.log.warn(Message);
                    break;
                }
                default:
                {
                    MyLogger.log.error("Invalid Log type requested i.e. " +LoggingType+"");
                }
            }
        }

        public void CheckPresenceOfElement(String Key)
        {
            String Result;
            WebElement element = ElementFinder.Instance().FindElement(Key, factory.driver);
            WaitForVisibility(10,Key);
            boolean IsPresent = element.isDisplayed();
            if (IsPresent == true)
            {
                Result= "Success";
                MyLogger.log.info(Key+" Control is present");
            }
            else
            {
                Result="Fail";
                MyLogger.log.info(Key+" Control is not present");
            }

            Assert.assertEquals("Success",Result);
        }

        public boolean IsControlEnabled(String Key)
        {
            WebElement element = ElementFinder.Instance().FindElement(Key, factory.driver);
            boolean isEnabled = element.isEnabled();

            if(isEnabled == true)
            {
                MyLogger.log.info("Control is enabled");
                return true;
            }

            else
            {
                MyLogger.log.info("Control is disabled");
                return false;
            }


        }

        public String[] ConvertStringToStringArray(String Value, String SplitBy)
        {
            String[] ConvertedArray = Value.split(SplitBy);
            return ConvertedArray;
        }
        public static void navigate(WebDriver driver, String URL)
        {
            driver.navigate().to(URL);;
        }
        public static void SwitchToNewWindow(String WindowName)
        {
            factory.driver.switchTo().window(WindowName);
        }

        public static void MaximizeWindow(WebDriver driver)
        {
            driver.manage().window().maximize();
        }



        public static void SwitchToFrame(WebDriver driver, String name)
        {
            driver.switchTo().frame(name);
        }

    }



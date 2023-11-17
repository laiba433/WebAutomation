package main.java.Helper;


import main.java.Factory.factory;
import main.java.Logger.MyLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ElementFinder
{



    public static ElementFinder Instance= null;

    public static ElementFinder Instance()
    {
        {
            if(Instance == null)
            {
                Instance = new ElementFinder();

            }

            return Instance;
        }
    }
    public static WebElement FindElement(String Key, WebDriver driver)
    {
        String PageSource= driver.getPageSource();
        return FindElement(Key, driver,2,false,false,PageSource);
    }

    public static WebElement FindElement(String Key, WebElement ParentElement)
    {
        String PageSource= factory.driver.getPageSource();
        return FindElement(Key,ParentElement,2,false,false,PageSource);
    }

    private static WebElement FindElement(String Key, WebDriver driver,int attempts, boolean endOfPage, boolean topOfthePage, String previousPageSource)
    {
        WebElement element = null;
        try
        {
            GeneralHelper.Instance().WaitForVisibility(5,Key);
            element = driver.findElement(GetLocator(Key));
            Actions action= new Actions(factory.driver);
            action.moveToElement(element);
            action.perform();

            if (element != null)
            {
                MyLogger.log.info(Key + " Element has found");
            }
            return element;
        }
        catch (Exception e)
        {
            if (element == null)
            {
                attempts--;
                if (attempts > 0)
                {
                    MyLogger.log.info("Second Attempt to find the element");
                    return FindElement(Key, driver, attempts, endOfPage,topOfthePage,previousPageSource);

                }
                else
                {
                    if (!endOfPage && topOfthePage==false)
                    {
                        GeneralHelper.Instance().ScrollDown();
                        endOfPage = previousPageSource.equals(factory.driver.getPageSource());
                        previousPageSource = factory.driver.getPageSource();
                        return FindElement(Key, driver, attempts, endOfPage,topOfthePage,previousPageSource);
                    }
                    else if (!topOfthePage && endOfPage==true)
                    {
                        GeneralHelper.Instance().SwipeUP();
                        topOfthePage = previousPageSource.equals(factory.driver.getPageSource());
                        previousPageSource = factory.driver.getPageSource();
                        return FindElement(Key, driver, attempts, endOfPage,topOfthePage,previousPageSource);
                    }
                    else
                    {
                        MyLogger.log.error("Cannot find an element " + Key + " Locator strategy even after scroll the window");
                        return element;
                    }
                }
            }
            GeneralHelper.Instance().GoToTheTopOfPage();
            return element;
        }

    }
    private static WebElement FindElement(String Key, WebElement ParentElement,int attempts, boolean endOfPage,boolean topOfthePage, String previousPageSource)
    {
        WebElement element = null;
        try
        {
            GeneralHelper.Instance().WaitForVisibility(5,Key);
            element = ParentElement.findElement(GetLocator(Key));
            Actions action= new Actions(factory.driver);
            action.moveToElement(element);
            action.perform();

            if (element != null)
            {
                MyLogger.log.info(Key + " Element has found");
            }
            return element;
        }
        catch (Exception e)
        {
            if (element == null)
            {
                attempts--;
                if (attempts > 0)
                {
                    MyLogger.log.info("Second Attempt to find the element");
                    return FindElement(Key, ParentElement, attempts, endOfPage,topOfthePage,previousPageSource);

                }
                else
                {
                    if (!endOfPage && topOfthePage==false)
                    {
                        GeneralHelper.Instance().ScrollDown();
                        endOfPage = previousPageSource.equals(factory.driver.getPageSource());
                        previousPageSource = factory.driver.getPageSource();
                        return FindElement(Key, ParentElement, attempts, endOfPage,topOfthePage,previousPageSource);
                    }
                    else if (!topOfthePage && endOfPage==true)
                    {
                        GeneralHelper.Instance().SwipeUP();
                        topOfthePage = previousPageSource.equals(factory.driver.getPageSource());
                        previousPageSource = factory.driver.getPageSource();
                        return FindElement(Key, ParentElement, attempts, endOfPage,topOfthePage,previousPageSource);
                    }
                    else
                    {
                        MyLogger.log.error("Cannot find an element " + Key + " Locator strategy even after scroll the window");
                        return element;
                    }
                }
            }
            GeneralHelper.Instance().GoToTheTopOfPage();
            return element;
        }
    }

    public List<WebElement> FindElementList(String Key, WebDriver driver)
    {
        List<WebElement> element= null;
        try
        {
            GeneralHelper.Instance().WaitForVisibility(5,Key);
            element = driver.findElements(GetLocator(Key));
            if (element != null)
            {
                MyLogger.log.info(Key + " Element list has found");
            }
            return element;
        }

        catch(Exception ex)
        {
            System.out.println("element list not fount");
        }
        return element;
    }

    public List<WebElement> FindElementListByScrolling(String ListKey,WebDriver driver){
        List<WebElement> List;
        boolean endOfPage = false;
        String previousPageSource = driver.getPageSource();
        Set<WebElement> emptyList = new HashSet<>();
        while (!endOfPage)
        {
            List = FindElementList(ListKey, driver);
            for (WebElement ele : List)
            {
                emptyList.add(ele);
            }
            GeneralHelper.Instance().ScrollDown();
            endOfPage = previousPageSource.equals(driver.getPageSource());
            previousPageSource = driver.getPageSource();
        }
        List<WebElement> itemList = new ArrayList<>(emptyList);
        return itemList;
    }
    public List<WebElement> FindElementList(String Key, WebElement ParentElement)
    {
        List<WebElement> element= null;
        try
        {
            GeneralHelper.Instance().WaitForVisibility(5,Key);
            element = ParentElement.findElements(GetLocator(Key));

            if (element != null)
            {
                MyLogger.log.info(Key + " Element list has found");
            }
            return element;
        }

        catch(Exception ex)
        {
            System.out.println("element list not fount");
        }
        return element;
    }

    public static By GetLocator(String Key) {
        By Locator = null;
        ParseLocators KeyObject = ParseLocators.GetValues(Key);
        try {

            switch (KeyObject.locator)
            {
                case "Name": {
                    Locator = By.name(KeyObject.value);
                    break;
                }
                case "XPath": {
                    Locator = By.xpath(KeyObject.value);
                    break;
                }
                case "Class": {
                    Locator = By.className(KeyObject.value);
                    break;
                }
                case "Id": {
                    Locator = By.id(KeyObject.value);
                    break;
                }
                case "CssSelector": {
                    Locator = By.cssSelector(KeyObject.value);
                    break;
                }
                case "TagName": {
                    Locator = By.tagName(KeyObject.value);
                    break;
                }
                case "LinkText": {
                    Locator = By.linkText(KeyObject.value);
                    break;
                }
                default: {
                    MyLogger.log.error("Given Locate Searching Strategy i.e. " + KeyObject.locator + "is not defined in the scope");
                }

            }
        }
        catch (Exception ex)
        {
            MyLogger.log.error(ex);
        }
        return Locator;
    }

}

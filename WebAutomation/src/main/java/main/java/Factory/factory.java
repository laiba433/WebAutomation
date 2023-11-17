package main.java.Factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import main.java.Logger.MyLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class factory {
	
	public static WebDriver driver;
	
	public static WebDriver getDriver(String Browser)
	{
try{
    switch (Browser) {
        case "Chrome" -> {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized"); // open Browser in maximized mode
            options.addArguments("disable-infobars"); // disabling infobars
            options.addArguments("--disable-extensions"); // disabling extensions
            options.addArguments("--disable-gpu"); // applicable to Windows os only
            options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
            options.addArguments("--no-sandbox"); // Bypass OS security model
            options.addArguments("--disable-in-process-stack-traces");
            options.addArguments("--disable-logging");
            options.addArguments("--log-level=3");
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
        }
        case "Edge" -> {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        case "Firefox" -> {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        default -> {
            System.out.println("you have not selected correct browser name " + Browser);
        }
    }
}
catch(Exception ex)
{
    MyLogger.log.error(ex);
}
        return driver;
	}



}

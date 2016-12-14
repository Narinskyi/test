package core;

import enums.ConfiguredBrowsers;
import org.openqa.selenium.WebDriver;
import utils.DataProvider;

public class WebDriverFactory {


    private static WebDriver driver;

    public static WebDriver getDriver(){
        return driver;
    }

    public static void startDriver (ConfiguredBrowsers browser) {
            driver = browser.getDriver();
    }

    public static void quitDriver(){
        if (driver!=null) {
            driver.quit();
        }
    }

}

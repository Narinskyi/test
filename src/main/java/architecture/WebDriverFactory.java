package architecture;

import enums.ConfiguredBrowsers;
import org.openqa.selenium.WebDriver;

public class WebDriverFactory {


    private static WebDriver driver;

    public static WebDriver getDriver(){
        return driver;
    }

    public static void startDriver (ConfiguredBrowsers browser) {
        driver=browser.getDriver();
    }

    public static void quitDriver(){
        if (driver!=null) {
            driver.quit();
        }
    }

}

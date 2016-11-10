package utils;

import enums.ConfiguredBrowsers;

import java.util.ResourceBundle;
import java.util.logging.Logger;

public class DataProvider {

    private static Logger log = Logger.getAnonymousLogger();

    private static ResourceBundle resources = ResourceBundle.getBundle("ConfigBundle");

    public static String getBaseUrl(){
        return resources.getString("base.url");
    }

    //return ConfiguredBrowser object by its name. Chrome is returned by default
    public static ConfiguredBrowsers getBrowser() {

        ConfiguredBrowsers browser;

        String browserName = resources.getString("browser");
        try {
            browser = ConfiguredBrowsers.valueOf(browserName);
        } catch (IllegalArgumentException e) {
            log.warning("Illegal browser name in properties file. Chrome will be launched");
            browser = ConfiguredBrowsers.chrome;
        }
        return browser;
    }

}

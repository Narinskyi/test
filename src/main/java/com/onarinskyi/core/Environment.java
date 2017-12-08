package com.onarinskyi.core;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Logger;

public class Environment {

    private static Logger log = Logger.getAnonymousLogger();
    private static ResourceBundle resources = ResourceBundle.getBundle("application");

    public static String getBaseUrl() {
        return resources.getString("base.url");
    }

    static boolean shouldUseGrid() {
        return Boolean.valueOf(getParameterValue("grid"));
    }

    static URL getHubURL() {
        String url = getParameterValue("hub");
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Please set correct URL");
        }
    }

    //return ConfiguredBrowser object by its name. Chrome is returned by default
    public static ConfiguredBrowsers getBrowser() {

        ConfiguredBrowsers browser;
        String browserName;

        //try to extract browser name from command line parameter; if no - from .properties file
        browserName = getParameterValue("browser");
        try {
            browser = ConfiguredBrowsers.valueOf(browserName);
        } catch (IllegalArgumentException e) {
            log.warning("Illegal browser name in properties file. Chrome will be launched");
            browser = ConfiguredBrowsers.chrome;
        }
        return browser;
    }

    //try to extract parameter from session. If no such - get it from .properties file
    private static String getParameterValue(String parameter) {
        if (System.getProperty(parameter) != null) {
            return System.getProperty(parameter);
        } else
            return resources.getString(parameter);
    }
}

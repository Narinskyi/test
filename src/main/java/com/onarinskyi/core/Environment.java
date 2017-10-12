package com.onarinskyi.core;

import com.onarinskyi.spring.UserData;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Logger;

public class Environment {

    private static Logger log = Logger.getAnonymousLogger();
    private static ResourceBundle resources = ResourceBundle.getBundle("application");
    private static UserData userData;

    public static void setUserData(UserData userData) {
        Environment.userData = userData;
    }

    public static UserData getUserData() {
        return userData;
    }

    public static String getBaseUrl(){
        return resources.getString("base.url");
    }

    public static boolean shouldUseGrid() { return Boolean.valueOf(getParameterValue("grid")); }

    public static URL getHubURL() {
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

    public static String getRandomUsername () {
        return UUID.randomUUID().toString().substring(0,7);
    }

    //try to extract parameter from session. If no such - get it from .properties file
    private static String getParameterValue(String parameter) {
        if (System.getProperty(parameter)!=null) {
            return System.getProperty(parameter);
        } else
            return resources.getString(parameter);
    }

    /**---------------------------- DateUtils class ----------------------------*/
    public static class DateUtils {

        public static String getCurrentDay(){
            return new SimpleDateFormat("dd").format(new Date());
        }

        public static String getCurrentMonth(){
            return new SimpleDateFormat("MM").format(new Date());
        }

        public static String getCurrentYear(){
            return new SimpleDateFormat("yyyy").format(new Date());
        }

        public static String getSomeYearsAgo(int howMany){
            Date today = new Date();
            String todayFormatted = new SimpleDateFormat("MM.dd.").format(today);
            int someYearsAgo = Integer.valueOf(new SimpleDateFormat("yyyy").format(today))-howMany;
            return todayFormatted+Integer.toString(someYearsAgo);
        }

    }

}

package utils;

import enums.ConfiguredBrowsers;
import springConstructors.UserData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Logger;

public class DataProvider {

    private static Logger log = Logger.getAnonymousLogger();
    private static ResourceBundle resources = ResourceBundle.getBundle("ConfigBundle");
    private static UserData userData;

    public static void setUserData(UserData userData) {
        DataProvider.userData = userData;
    }

    public static UserData getUserData() {
        return userData;
    }

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

    public static String getRandomUsername () {
        return UUID.randomUUID().toString().substring(0,7);
    }

    /**---------------------------- DateUtils utils ----------------------------*/
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

        public static String getCurrentDate() {
            return new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        }

        public static String getSomeYearsAgo(int howMany){
            Date today = new Date();
            String todayFormatted = new SimpleDateFormat("MM.dd.").format(today);
            int someYearsAgo = Integer.valueOf(new SimpleDateFormat("yyyy").format(today))-howMany;
            return todayFormatted+Integer.toString(someYearsAgo);
        }

    }


}

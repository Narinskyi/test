package utils;

import java.util.ResourceBundle;

public class DataProvider {

    private static ResourceBundle resources = ResourceBundle.getBundle("ConfigBundle");

    public static String getBaseUrl(){
        return resources.getString("base.url");
    }

}

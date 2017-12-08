package com.onarinskyi.framework.driver;

import com.onarinskyi.framework.core.Environment;
import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static ThreadLocal<WebDriver> driverThreadLocal =
            ThreadLocal.withInitial(() -> Environment.getBrowser().getDriver());

    static WebDriver getDriver(){
        return driverThreadLocal.get();
    }

    public static void quit() {
        driverThreadLocal.get().quit();
        driverThreadLocal.remove();
    }
}

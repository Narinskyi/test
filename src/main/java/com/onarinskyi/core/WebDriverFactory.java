package com.onarinskyi.core;

import org.openqa.selenium.WebDriver;

public class WebDriverFactory {

    //private constructor
    private WebDriverFactory(){}

    private static WebDriverFactory instance = new WebDriverFactory();

    public static WebDriverFactory getInstance(){
        return instance;
    }

    //which browser is used now
    private static ConfiguredBrowsers browser = Environment.getBrowser();

    // thread local driver object for webdriver
    private ThreadLocal<WebDriver> driver = ThreadLocal.withInitial(() -> browser.getDriver());

    public WebDriver getDriver(){
        return driver.get();
    }

    void quitDriver(){
        driver.get().quit();
        driver.remove();
    }

}

package com.onarinskyi.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public enum ConfiguredBrowsers {

    firefox,
    chrome,
    mobileEmulatorChrome,
    tabletEmulatorChrome,
    phantomJS;

    public WebDriver getDriver(){

        //if grid = true, use RemoteWebDriver
        boolean useGrid = Environment.shouldUseGrid();

        //get host of the grid hub
        URL host = Environment.getHubURL();

        //if grid is turned on - use RemoteWebDriver
        if (useGrid) {
            switch (this) {
                case firefox:
                    new RemoteWebDriver(host,getFirefoxCapabilities());
                case chrome:
                    return new RemoteWebDriver(host,DesiredCapabilities.chrome());
                case mobileEmulatorChrome:
                    return new RemoteWebDriver(host,getChromeMobileCapabilities("Google Nexus 5"));
                case tabletEmulatorChrome:
                    return new RemoteWebDriver(host,getChromeTabletCapabilities());
                case phantomJS:
                    return new RemoteWebDriver(host, DesiredCapabilities.phantomjs());
                default:
                    throw new RuntimeException("Incorrect browser was specified in .properties file");
            }
        }

        //if grid is turned off - use local instance of WebDriver
        else {

            //local drivers initialization (for remote set in selenium server run config)
            initDrivers();

            switch (this) {
                case firefox:
                    return new FirefoxDriver(getFirefoxCapabilities());
                case chrome:
                    return new ChromeDriver();
                case mobileEmulatorChrome:
                    return new ChromeDriver(getChromeMobileCapabilities("Google Nexus 5"));
                case tabletEmulatorChrome:
                    return new ChromeDriver(getChromeTabletCapabilities());
                default:
                    throw new RuntimeException("Incorrect browser was specified in .properties file");
            }
        }
    }

    //set path values for configured drivers
    private static void initDrivers() {

        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            System.setProperty("webdriver.gecko.driver", "src/drivers/windows/geckodriver.exe");
            System.setProperty("webdriver.chrome.driver", "src/drivers/windows/chromedriver.exe");
            System.setProperty("phantomjs.binary.path", "src/drivers/windows/phantomjs.exe");
        }
        else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
            System.setProperty("webdriver.chrome.driver", "src/drivers/macos/chromedriver");
        }
    }

    private DesiredCapabilities getChromeTabletCapabilities(){
        String userAgent = "Mozilla/5.0 (iPad; U; CPU OS 3_2 like Mac OS X; en-us) AppleWebKit/531.21.10 " +
                "(KHTML, like Gecko) Version/4.0.4 Mobile/7B334b Safari/531.21.10";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-agent=" + userAgent);
        options.addArguments("window-size=1039,859");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        return capabilities;
    }

    private DesiredCapabilities getChromeMobileCapabilities(String deviceName){
        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", deviceName);

        Map<String, Object> chromeOptions = new HashMap<>();
        chromeOptions.put("mobileEmulation", mobileEmulation);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        return capabilities;
    }

    private DesiredCapabilities getFirefoxCapabilities() {
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
        FirefoxProfile profile = new FirefoxProfile();
        desiredCapabilities.setCapability(FirefoxDriver.PROFILE, profile);
        return desiredCapabilities;
    }
}

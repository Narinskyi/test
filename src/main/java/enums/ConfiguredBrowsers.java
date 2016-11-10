package enums;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public enum ConfiguredBrowsers {

    firefox,
    chrome,
    ie,
    mobileEmulatorChrome,
    tabletEmulatorChrome;


    public WebDriver getDriver(){

        switch (this) {
            case firefox: return new FirefoxDriver();
            case chrome: return new ChromeDriver();
            case ie: return new InternetExplorerDriver();
            case mobileEmulatorChrome: return getChromeDriverMobile("Google Nexus 5");
            case tabletEmulatorChrome: return getChromeDriverMobile("iPad");
            default: return new ChromeDriver();
        }

    }

    private static ChromeDriver getChromeDriverMobile(String deviceName){
        Map<String, String> mobileEmulation = new HashMap<String, String>();
        mobileEmulation.put("deviceName", deviceName);

        Map<String, Object> chromeOptions = new HashMap<String, Object>();
        chromeOptions.put("mobileEmulation", mobileEmulation);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return new ChromeDriver(capabilities);
    }

    public static void main(String[] args) {
        mobileEmulatorChrome.getDriver();
    }

}

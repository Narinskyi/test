package com.onarinskyi.driver;

import com.onarinskyi.environment.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Component
@PropertySource("driver.properties")
public class WebDriverFactory {

    @Value("device.name")
    private String deviceName;

    public WebDriver getDriverOf(BrowserType browserType) {
        switch (browserType) {
            case CHROME:
                return new ChromeDriver();
            case FIREFOX:
                return new FirefoxDriver(getFirefoxOptions());
            case MOBILE_EMULATOR_CHROME:
                return new ChromeDriver(getChromeMobileOptions(deviceName));
            case TABLET_EMULATOR_CHROME:
                return new ChromeDriver(getChromeTabletOptions());
            default:
                return new ChromeDriver();
        }
    }

    public WebDriver getDriverOf(BrowserType browserType, URL hubHost) {
        switch (browserType) {
            case CHROME:
                return new RemoteWebDriver(hubHost, DesiredCapabilities.chrome());
            case FIREFOX:
                return new RemoteWebDriver(hubHost, getFirefoxOptions());
            case MOBILE_EMULATOR_CHROME:
                return new RemoteWebDriver(hubHost, getChromeMobileOptions(deviceName));
            case TABLET_EMULATOR_CHROME:
                return new RemoteWebDriver(hubHost, getChromeTabletOptions());
            default:
                return new RemoteWebDriver(hubHost, DesiredCapabilities.chrome());
        }
    }

    private ChromeOptions getChromeTabletOptions() {
        String userAgent = "Mozilla/5.0 (iPad; U; CPU OS 3_2 like Mac OS X; en-us) AppleWebKit/531.21.10 " +
                "(KHTML, like Gecko) Version/4.0.4 Mobile/7B334b Safari/531.21.10";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-agent=" + userAgent);
        options.addArguments("window-size=1039,859");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        return new ChromeOptions().merge(capabilities);
    }

    private ChromeOptions getChromeMobileOptions(String deviceName) {
        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", deviceName);

        Map<String, Object> chromeOptions = new HashMap<>();
        chromeOptions.put("mobileEmulation", mobileEmulation);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        return new ChromeOptions().merge(capabilities);
    }

    private FirefoxOptions getFirefoxOptions() {
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
        FirefoxProfile profile = new FirefoxProfile();
        desiredCapabilities.setCapability(FirefoxDriver.PROFILE, profile);
        return new FirefoxOptions().merge(desiredCapabilities);
    }
}

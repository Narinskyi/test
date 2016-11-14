package enums;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public enum ConfiguredBrowsers {

    firefox,
    chrome,
    edge,
    ie,
    mobileEmulatorChrome,
    tabletEmulatorChrome,
    phantomJS;

    public WebDriver getDriver(){

        initDrivers();


        switch (this) {

            case firefox: return new FirefoxDriver();
            case chrome: return new ChromeDriver();
            case edge: return new EdgeDriver();
            case ie: return getIEDriver();
            case mobileEmulatorChrome: return getChromeDriverMobile("Google Nexus 5");
            case tabletEmulatorChrome: return getChromeDriverMobile("Apple iPad");
            case phantomJS: return new PhantomJSDriver();
            default: return new PhantomJSDriver();
        }

    }

    //set path values for configured drivers
    private static void initDrivers(){

        System.setProperty("webdriver.gecko.driver", "src/drivers/geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
        System.setProperty("webdriver.edge.driver", "src/drivers/MicrosoftWebDriver.exe");
        System.setProperty("webdriver.ie.driver", "src/drivers/IEDriverServer.exe");
        System.setProperty("phantomjs.binary.path", "src/drivers/phantomjs.exe");

    }

    private static ChromeDriver getChromeDriverMobile(String deviceName){
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", deviceName);

        Map<String, Object> chromeOptions = new HashMap<>();
        chromeOptions.put("mobileEmulation", mobileEmulation);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return new ChromeDriver(capabilities);
    }

    private static InternetExplorerDriver getIEDriver() {

        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setCapability("nativeEvents", false);
        capabilities.setCapability("ignoreZoomSetting", true);
        capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);

        return new InternetExplorerDriver(capabilities);
    }

    public static void main(String[] args) {
        WebDriver driver=tabletEmulatorChrome.getDriver();
        driver.navigate().to("http://wpl-licensee25-admin.ptdev.eu");
    }

}

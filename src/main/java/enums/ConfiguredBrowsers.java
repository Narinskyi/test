package enums;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import sun.security.krb5.internal.crypto.Des;
import utils.DataProvider;

import java.net.MalformedURLException;
import java.net.URL;
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

        //if grid = true, use RemoteWebDriver
        boolean useGrid = DataProvider.shouldUseGrid();

        //get host of the grid hub
        URL host = DataProvider.getHubURL();

        initDrivers();

        switch (this) {

            case firefox: return useGrid ? new RemoteWebDriver(host,DesiredCapabilities.firefox())
                    : new FirefoxDriver();
            case chrome: return useGrid ? new RemoteWebDriver(host,DesiredCapabilities.chrome())
                    : new ChromeDriver();
            case edge: return useGrid ? new RemoteWebDriver(host,DesiredCapabilities.edge())
                    : new EdgeDriver();
            case ie: return useGrid ? new RemoteWebDriver(host,getIECapabilities())
                    : new InternetExplorerDriver(getIECapabilities());
            case mobileEmulatorChrome: return useGrid ? new RemoteWebDriver(host,getChromeMobileCapabilities("Google Nexus 5"))
                    : new ChromeDriver(getChromeMobileCapabilities("Google Nexus 5"));
            case tabletEmulatorChrome: return useGrid ? new RemoteWebDriver(host,getChromeMobileCapabilities("Apple iPad"))
                    : new ChromeDriver(getChromeMobileCapabilities("Apple iPad"));
            case phantomJS: return useGrid ? new RemoteWebDriver(host, DesiredCapabilities.phantomjs())
                    : new PhantomJSDriver();
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

    private DesiredCapabilities getChromeMobileCapabilities(String deviceName){

        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", deviceName);

        Map<String, Object> chromeOptions = new HashMap<>();
        chromeOptions.put("mobileEmulation", mobileEmulation);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        return capabilities;
    }

    private DesiredCapabilities getIECapabilities(){
        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setCapability("nativeEvents", false);
        capabilities.setCapability("ignoreZoomSetting", true);
        capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);

        return capabilities;
    }

    public static void main(String[] args) {

        try {
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();

            WebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);
            driver.navigate().to("http://wpl-licensee25-admin.ptdev.eu");
            driver.quit();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

}

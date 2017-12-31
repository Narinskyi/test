package com.onarinskyi.config;

import com.onarinskyi.driver.WebDriverDecorator;
import com.onarinskyi.driver.WebDriverFactory;
import com.onarinskyi.environment.BrowserType;
import com.onarinskyi.environment.OperatingSystem;
import com.onarinskyi.environment.Timeout;
import com.onarinskyi.utils.UrlResolver;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import java.net.URL;

@Configuration
@PropertySource("classpath:driver.properties")
public class DriverConfig {

    private static ThreadLocal<WebDriverDecorator> threadLocalDriver;

    @Value("${browser.type}")
    private String browserType;

    @Value("${implicit.wait}")
    private long implicitWait;

    @Value("${explicit.wait}")
    private long explicitWait;

    @Value("${use.grid}")
    private boolean useGrid;

    @Value("${grid.hub}")
    private URL hubHost;

    @Value("${system.os}")
    private String systemOs;

    @Value("${base.url}")
    private String applicationBaseUrl;

    @Autowired
    public DriverConfig(WebDriverFactory webDriverFactory, UrlResolver urlResolver) {
        initDrivers();

        WebDriver webDriver = useGrid ? webDriverFactory.getDriverOf(browserType(), hubHost) :
                webDriverFactory.getDriverOf(browserType());

        threadLocalDriver = ThreadLocal.withInitial(() -> new WebDriverDecorator(webDriver, timeout(), urlResolver));
    }

    @Bean
    public BrowserType browserType() {
        return BrowserType.of(browserType);
    }

    @Bean
    public Timeout timeout() {
        return new Timeout(implicitWait, explicitWait);
    }

    @Bean
    @Scope("prototype")
    public WebDriverDecorator driver() {
        return threadLocalDriver.get();
    }

    public static void quitDriver() {
        threadLocalDriver.get().quit();
        threadLocalDriver.remove();
    }

    private void initDrivers() {
        switch (OperatingSystem.of(systemOs)) {
            case WINDOWS:
                System.setProperty("webdriver.gecko.driver", "src/drivers/windows/geckodriver.exe");
                System.setProperty("webdriver.chrome.driver", "src/drivers/windows/chromedriver.exe");
                System.setProperty("phantomjs.binary.path", "src/drivers/windows/phantomjs.exe");
            case MACOS:
                System.setProperty("webdriver.chrome.driver", "src/drivers/macos/chromedriver");
        }
    }
}
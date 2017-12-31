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
    private OperatingSystem operatingSystem;

    @Value("${browser.type}")
    private String browserType;

    @Value("${implicit.wait}")
    private String implicitWait;

    @Value("${explicit.wait}")
    private String explicitWait;

    @Value("${use.grid}")
    private boolean useGrid;

    @Value("${grid.hub}")
    private String hubHostString;

    @Value("${system.os}")
    private String systemOs;

    @Value("${base.url}")
    private String applicationBaseUrl;

    @Autowired
    public DriverConfig(WebDriverFactory webDriverFactory, UrlResolver urlResolver, @Value("${system.os}") String systemOs,
                        @Value("${grid.hub}") URL hubHostUrl, @Value("${use.grid}") boolean useGrid,
                        @Value("${implicit.wait}")
                             String implicitWait,
                                    @Value("${explicit.wait}") String explicitWait, @Value("${browser.type}")
                             String browserType) {
        initDrivers(OperatingSystem.of(systemOs));

        WebDriver webDriver = useGrid ? webDriverFactory.getDriverOf(BrowserType.of(browserType), hubHostUrl) :
                webDriverFactory.getDriverOf(BrowserType.of(browserType));

        threadLocalDriver = ThreadLocal.withInitial(() -> new WebDriverDecorator(webDriver,
                new Timeout(Long.valueOf(implicitWait), Long.valueOf(explicitWait)), urlResolver));
    }

    @Bean
    public BrowserType browserType() {
        return BrowserType.of(browserType);
    }

    @Bean
    public Timeout timeout() {
        return new Timeout(Long.valueOf(implicitWait), Long.valueOf(explicitWait));
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

    private void initDrivers(OperatingSystem systemOs) {
        switch (systemOs) {
            case WINDOWS:
                System.setProperty("webdriver.gecko.driver", "src/drivers/windows/geckodriver.exe");
                System.setProperty("webdriver.chrome.driver", "src/drivers/windows/chromedriver.exe");
                System.setProperty("phantomjs.binary.path", "src/drivers/windows/phantomjs.exe");
            case MACOS:
                System.setProperty("webdriver.chrome.driver", "src/drivers/macos/chromedriver");
        }
    }
}
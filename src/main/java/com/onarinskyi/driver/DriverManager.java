package com.onarinskyi.driver;

import com.onarinskyi.environment.Timeout;
import com.onarinskyi.utils.UrlResolver;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:driver.properties")
public class DriverManager {

    private static ThreadLocal<WebDriver> threadLocalDriver;

    private static boolean failOnException;
    private static Timeout timeout;
    private static UrlResolver urlResolver;

    @Autowired
    public DriverManager(ThreadLocal<WebDriver> threadLocal,
                         Timeout timeout,
                         UrlResolver urlResolver,
                         @Value("${exception.fail}") boolean failOnException)

    {
        threadLocalDriver = threadLocal;
        DriverManager.timeout = timeout;
        DriverManager.urlResolver = urlResolver;
        DriverManager.failOnException = failOnException;
    }

    static WebDriver getThreadLocalDriver() {
        return threadLocalDriver.get();
    }

    public static WebDriverDecorator getDriver() {
        return new WebDriverDecorator(timeout, urlResolver, failOnException);
    }

    static void removeDriver() {
        threadLocalDriver.remove();
    }
}

package com.onarinskyi.config;

import com.onarinskyi.environment.BrowserType;
import com.onarinskyi.environment.Timeout;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:driver.properties")
public class DriverConfig {

    @Bean
    public BrowserType browserType(@Value("${browser}") String value) {
        return BrowserType.of(value);
    }

    @Bean
    public Timeout timeout(@Value("${implicit.wait}") long implicitWait, @Value("${explicit.wait}") long explicitWait) {
        return new Timeout(implicitWait, explicitWait);
    }

    @Bean
    public WebDriver driver() {

        return null;
    }
}

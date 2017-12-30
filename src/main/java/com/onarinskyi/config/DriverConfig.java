package com.onarinskyi.config;

import com.onarinskyi.environment.BrowserType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DriverConfig {

    @Bean
    public BrowserType browserType(@Value("${webdriver.driver}") String value) {
        return BrowserType.of(value);
    }
}

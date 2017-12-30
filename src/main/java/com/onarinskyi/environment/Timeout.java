package com.onarinskyi.environment;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class Timeout {

    @Value("${implicit.wait}")
    private long implicitWait;

    @Value("${explicit.wait}")
    private long explicitWait;

    public long implicitWait() {
        return implicitWait;
    }

    public long explicitWait() {
        return explicitWait;
    }
}

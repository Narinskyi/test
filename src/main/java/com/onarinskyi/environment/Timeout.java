package com.onarinskyi.environment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class Timeout {

    private long implicitWait;
    private long explicitWait;

    @Autowired
    public Timeout(@Value("${implicit.wait}") long implicitWait,  @Value("${explicit.wait}") long explicitWait) {
        this.implicitWait = implicitWait;
        this.explicitWait = explicitWait;
    }

    public long implicitWait() {
        return implicitWait;
    }

    public long explicitWait() {
        return explicitWait;
    }
}

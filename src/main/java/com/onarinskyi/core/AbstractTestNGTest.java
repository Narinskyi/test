package com.onarinskyi.core;

import com.onarinskyi.config.AppConfig;
import com.onarinskyi.config.DriverConfig;
import com.onarinskyi.driver.WebDriverDecorator;
import com.onarinskyi.listeners.TestNGOnTestFailureListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

import javax.annotation.PostConstruct;

@Listeners(TestNGOnTestFailureListener.class)
@ContextConfiguration(classes = AppConfig.class)
public abstract class AbstractTestNGTest extends AbstractTestNGSpringContextTests {

    protected WebDriverDecorator driver;

    protected SoftAssert softly = new SoftAssert();

    @PostConstruct
    public void init() {
        Reflection.instantiateAnnotatedFields(this);
        driver = DriverConfig.getDriver();
    }

    @AfterClass(alwaysRun = true)
    public void quitDriver() {
        driver.quit();
    }
}


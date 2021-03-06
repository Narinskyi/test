package com.onarinskyi.core;

import com.onarinskyi.config.AppConfig;
import com.onarinskyi.driver.DriverManager;
import com.onarinskyi.driver.WebDriverDecorator;
import com.onarinskyi.listeners.TestNGExecutionListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

import javax.annotation.PostConstruct;

@Listeners(TestNGExecutionListener.class)
@TestExecutionListeners(inheritListeners = false, listeners =
        {DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class})
@ContextConfiguration(classes = AppConfig.class)
public abstract class AbstractTestNGTest extends AbstractTestNGSpringContextTests {

    protected WebDriverDecorator driver;

    protected SoftAssert softly = new SoftAssert();

    @PostConstruct
    public void init() {
        Reflection.instantiateAnnotatedFields(this);
        driver = DriverManager.getDriver();
    }

    @AfterClass(alwaysRun = true)
    public void quitDriver() {
        driver.quit();
    }
}


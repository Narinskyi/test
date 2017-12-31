package com.onarinskyi.core;

import com.onarinskyi.annotations.PageComponent;
import com.onarinskyi.annotations.PageObject;
import com.onarinskyi.config.AppConfig;
import com.onarinskyi.driver.WebDriverDecorator;
import com.onarinskyi.listeners.OnTestFailureListener;
import com.onarinskyi.reflection.Reflection;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

import javax.annotation.PostConstruct;

@Component
@Listeners({OnTestFailureListener.class})
@ContextConfiguration(classes = AppConfig.class)
public abstract class AbstractTest extends AbstractTestNGSpringContextTests {

    @Autowired
    protected WebDriverDecorator driver;

    protected SoftAssert softly = new SoftAssert();

    //on first test listener
    @PostConstruct
    public void initializeAnnotatedFields() {
        Reflection.instantiateAnnotatedField(this, PageObject.class);
        Reflection.instantiateAnnotatedField(this, PageComponent.class);
    }

    @AfterClass(alwaysRun = true)
    public void stop() {
        driver.quit();
    }
}


package com.onarinskyi.core;

import com.onarinskyi.annotations.PageComponent;
import com.onarinskyi.annotations.PageObject;
import com.onarinskyi.config.AppConfig;
import com.onarinskyi.driver.WebDriverFacade;
import com.onarinskyi.listeners.OnTestFailureListener;
import com.onarinskyi.reflection.Reflection;
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

    protected final WebDriverFacade driver;

    protected SoftAssert softly = new SoftAssert();

    @Autowired
    public AbstractTest(WebDriverFacade driver) {
        this.driver = driver;
    }

    //on first test listener
    @PostConstruct
    public void initializeAnnotatedFields() {
        Reflection.instantiateAnnotatedField(this, PageObject.class);
        Reflection.instantiateAnnotatedField(this, PageComponent.class);
    }

//    {
//        Reflection.instantiateAnnotatedField(this, PageObject.class);
//        Reflection.instantiateAnnotatedField(this, PageComponent.class);
//    }

    @AfterClass(alwaysRun = true)
    public void stop() {
        driver.quit();
    }
}


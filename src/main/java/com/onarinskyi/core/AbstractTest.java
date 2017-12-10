package com.onarinskyi.core;

import com.onarinskyi.annotations.Component;
import com.onarinskyi.annotations.PageObject;
import com.onarinskyi.driver.DriverManager;
import com.onarinskyi.listeners.OnTestFailureListener;
import com.onarinskyi.reflection.Reflection;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

@Listeners({OnTestFailureListener.class})
@ContextConfiguration(locations = {"/spring-config.xml"})
public abstract class AbstractTest extends AbstractTestNGSpringContextTests {

    protected SoftAssert softly = new SoftAssert();

    {
        Reflection.instantiateAnnotatedField(this, PageObject.class);
        Reflection.instantiateAnnotatedField(this, Component.class);
    }

    @AfterClass(alwaysRun = true)
    public void stop() {
        DriverManager.quit();
    }
}


package com.onarinskyi.core;

import com.onarinskyi.annotations.PageObject;
import com.onarinskyi.listeners.OnTestFailureListener;
import com.onarinskyi.reflection.Reflection;
import org.apache.log4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

@Listeners({OnTestFailureListener.class})
@ContextConfiguration(locations={"/spring-config.xml"})
public abstract class AbstractTest extends AbstractTestNGSpringContextTests {

    private Logger log = Logger.getLogger(this.getClass());

    protected SoftAssert softly = new SoftAssert();

    @BeforeClass(alwaysRun = true)
    public void start(){
        Reflection.instantiateAnnotatedField(this, PageObject.class);
    }

    @AfterClass(alwaysRun = true)
    public void stop(){
        WebDriverFactory.getInstance().quitDriver();
    }

    public void failTest(String message) {
        log.fatal(message);
        Assert.fail(message);
    }
}


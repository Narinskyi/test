package com.onarinskyi.core;

import com.onarinskyi.listeners.OnTestFailureListener;
import com.onarinskyi.reflection.ReflectionUtils;
import com.onarinskyi.spring.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

import java.util.logging.Logger;

@Listeners({OnTestFailureListener.class})
@ContextConfiguration(locations={"/spring-config.xml"})
public abstract class AbstractTest extends AbstractTestNGSpringContextTests {

    private static Logger log = Logger.getAnonymousLogger();

    protected SoftAssert softly = new SoftAssert();

    @Autowired
    @Qualifier("userData")
    private UserData springUserData;

    @BeforeClass(alwaysRun = true)
    public void start(){
        ReflectionUtils.instantiatePageObjectFields(this);
    }

    @AfterClass(alwaysRun = true)
    public void stop(){
        WebDriverFactory.getInstance().quitDriver();
    }

    public static void failTest(String message) {
        log.severe(message);
        Assert.fail(message);
    }
}


package com.onarinskyi.core;

import com.onarinskyi.enums.Platform;
import com.onarinskyi.listeners.ConfigurationListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import com.onarinskyi.listeners.OnTestFailureListener;
import com.onarinskyi.springConstructors.IMSData;
import com.onarinskyi.springConstructors.UserData;
import com.onarinskyi.utils.DataProvider;
import java.lang.reflect.Method;
import java.util.logging.Logger;

@Listeners({OnTestFailureListener.class, ConfigurationListener.class})
@ContextConfiguration(locations={"/spring-config.xml"})
public abstract class AbstractTest extends AbstractTestNGSpringContextTests{

    private static Logger log = Logger.getAnonymousLogger();
    protected static boolean isMobile = DataProvider.getCurrentPlatform().equals(Platform.mobile);
    protected UserData userData;

    @Autowired
    @Qualifier("userData")
    private UserData springUserData;

    @Autowired
    @Qualifier("imsData")
    private IMSData imsData;

    @BeforeClass(alwaysRun = true)
    public void start(){
        //clone user data for each test class
        userData = getClonedUserData();
        //save its default state
        DataProvider.setUserData(userData);
        //start browser, specified in .properties file (redundant since also called in WebDriverUtils - left for clarity)
        WebDriverFactory.getInstance().getDriver();
       // log.info("Browser started");

    }

    @AfterClass(alwaysRun = true)
    public void stop(){
        WebDriverFactory.getInstance().quitDriver();
        //log.info("Browser stopped");
    }

    @BeforeMethod(alwaysRun = true)
    public void displayCurrentTestName(Method method){
        //log.info("**---------------------------- "+method.getName()+" ----------------------------**");
    }

    protected void restart() {
        this.stop();
        this.start();
    }

    public static void failTest(String message) {
        log.severe(message);
        Assert.fail(message);
    }

    //provide a cloned object for every test class
    public UserData getClonedUserData() {
        return springUserData.clone();
    }

}


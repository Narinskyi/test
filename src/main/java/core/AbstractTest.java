package core;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import springConstructors.UserData;
import utils.DataProvider;

import java.lang.reflect.Method;
import java.util.logging.Logger;

@ContextConfiguration(locations={"/spring-config.xml"})
public class AbstractTest extends AbstractTestNGSpringContextTests{

    private static Logger log = Logger.getAnonymousLogger();

    @Autowired
    @Qualifier("userData")
    private UserData userData;

    @BeforeClass(alwaysRun = true)
    public void start(){

        DataProvider.setUserData(userData);
        //start browser, specified in .properties file
        WebDriverFactory.startDriver(DataProvider.getBrowser());
        log.info("Browser started");
    }

    @AfterClass(alwaysRun = true)
    public void stop(){
        WebDriverFactory.quitDriver();
        log.info("Browser stopped");
    }

    protected void restart() {
        this.stop();
        this.start();
    }

    public static void failTest(String message) {
        log.severe(message);
        Assert.fail(message);
    }

    @BeforeMethod(alwaysRun = true)
    public void displayCurrentTestName(Method method){
        log.info("**---------------------------- "+method.getName()+" ----------------------------**");
    }

}


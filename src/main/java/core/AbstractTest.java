package core;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import springConstructors.UserData;
import utils.DataProvider;

import java.lang.reflect.Method;
import java.util.logging.Logger;

@ContextConfiguration(locations={"/spring-config.xml"})
public abstract class AbstractTest extends AbstractTestNGSpringContextTests{

    private static Logger log = Logger.getAnonymousLogger();

    @Autowired
    @Qualifier("userData")
    private UserData userData;

    @BeforeClass(alwaysRun = true)
    public void start(){

        DataProvider.setUserData(userData);
        //start browser, specified in .properties file
        //WebDriverFactory.getInstance().getDriver();
        log.info("Browser started");

    }

    @AfterMethod(alwaysRun = true)
    public void stop(){
        WebDriverFactory.getInstance().quitDriver();
        log.info("Browser stopped");
    }

    @BeforeMethod(alwaysRun = true)
    public void displayCurrentTestName(Method method){
        log.info("**---------------------------- "+method.getName()+" ----------------------------**");
    }

    protected void restart() {
        this.stop();
        this.start();
    }

    public static void failTest(String message) {
        log.severe(message);
        Assert.fail(message);
    }

}


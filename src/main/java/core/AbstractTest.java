package core;

import enums.Platform;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import reporting.CustomTestListener;
import springConstructors.IMSData;
import springConstructors.UserData;
import utils.DataProvider;
import java.lang.reflect.Method;
import java.util.logging.Logger;

@Listeners({CustomTestListener.class})
@ContextConfiguration(locations={"/spring-config.xml"})
public abstract class AbstractTest extends AbstractTestNGSpringContextTests{

    private static Logger log = Logger.getAnonymousLogger();

    //used to distinguish mobile tests
    protected static boolean isMobile = DataProvider.getCurrentPlatform().equals(Platform.mobile);

    @Autowired
    @Qualifier("userData")
    private UserData userData;

    @Autowired
    @Qualifier("imsData")
    private IMSData imsData;

    @BeforeClass(alwaysRun = true)
    public void start(){

        DataProvider.setUserData(userData);
        DataProvider.setIMSData(imsData);
        //start browser, specified in .properties file (redundant since also called in WebDriverUtils - left for clarity)
        WebDriverFactory.getInstance().getDriver();
        log.info("Browser started");

    }

    @AfterClass(alwaysRun = true)
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


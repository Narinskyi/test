package utils;

import architecture.WebDriverFactory;
import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.logging.Logger;

public class AbstractTest {

    private static Logger log = Logger.getAnonymousLogger();

    @BeforeClass(alwaysRun = true)
    public static void start(){

        //start browser, specified in .properties file
        WebDriverFactory.startDriver(DataProvider.getBrowser());
        log.info("Browser started");
    }

    @AfterClass(alwaysRun = true)
    public static void stop(){
        WebDriverFactory.quitDriver();
        log.info("Browser stopped");
    }

    protected static void restart() {
        stop();
        start();
    }

    public static void failTest(String message) {
        log.severe(message);
        Assert.fail(message);
    }



}


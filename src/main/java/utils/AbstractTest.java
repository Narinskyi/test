package utils;

import architecture.WebDriverFactory;
import enums.ConfiguredBrowsers;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.util.logging.Logger;

public class AbstractTest {

    protected static Logger log = Logger.getAnonymousLogger();

    @BeforeClass
    public static void start(){
        WebDriverFactory.startDriver(ConfiguredBrowsers.chrome);
        log.info("Browser started");
    }

    @AfterClass
    public static void stop(){
        WebDriverFactory.quitDriver();
        log.info("Browser stopped");
    }

}


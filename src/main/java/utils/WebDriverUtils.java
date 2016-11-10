package utils;

import architecture.WebDriverFactory;
import org.openqa.selenium.WebDriver;

public class WebDriverUtils {

    private static WebDriver driver(){
        return WebDriverFactory.getDriver();
    }

    public static void openPage(String url) {
        driver().navigate().to(url);
    }

}

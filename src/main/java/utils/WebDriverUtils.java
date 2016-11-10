package utils;

import architecture.WebDriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

public class WebDriverUtils {

    //timeout constants
    private static final int TIMEOUT = 30;
    private static final int WAIT = 1000;

    //current driver
    private static WebDriver driver(){
        return WebDriverFactory.getDriver();
    }

    //get web element
    private static WebElement findElement(String selector){
       return driver().findElement(getSelector(selector));
    }

    //verify element presence on the page
    private static boolean isElementPresent(By selector) {
        WebDriverWait wait = new WebDriverWait(driver(), TIMEOUT);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(selector));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    //get proper selector from argument string
    private static By getSelector (String identity) {
        By[] selectors = {
                By.id(identity),
                By.name(identity),
                By.cssSelector(identity),
                By.xpath(identity)};
        for (By selector : selectors) {
            if (isElementPresent(selector)) {
                return selector;
            }
        }
        throw new NoSuchElementException(
                "Could not find any visible element with identity - "
                        + identity
        );
    }

    public static void waitFor() {
        waitFor(WAIT);
    }

    public static void waitFor(long millisec) {
        System.out.println("Waiting for " + millisec + " ms");
        if (millisec > 0) {
            try {
                Thread.sleep(millisec);
            } catch (InterruptedException e) {
                AbstractTest.failTest("Sleep failed");
            }
        } else {
            AbstractTest.failTest("Please set correct wait time");
        }
    }

    //click
    public static void click (String selector) {
        try {
            findElement(selector).click();
        } catch (WebDriverException e) {
            AbstractTest.failTest("Was not possible to click element");
        }
    }


    //navigate to URL
    public static void openPage(String url) {
        driver().navigate().to(url);
    }

}

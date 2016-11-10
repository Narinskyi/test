package utils;

import architecture.WebDriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;

public class WebDriverUtils {

    private static Logger log = Logger.getAnonymousLogger();
    //timeout constants
    private static final int TIMEOUT = 30;
    private static final int WAIT = 1000;

    /**---------------------------- Waiters ----------------------------*/

    //regular sleep with default timeout
    public static void waitFor() {
        waitFor(WAIT);
    }

    //regular sleep with configurable timeout
    public static void waitFor(long millisec) {
        log.info("Waiting for " + millisec + " ms");
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

    //wait for element visibility with default timeout
    public static void waitForElementVisibility (String selector) {
        waitForElementVisibility(selector, TIMEOUT);
    }

    //wait for element visibility
    public static void waitForElementVisibility (String selector, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver(), timeoutInSeconds);

        log.info("Waiting for visibility of element " + selector);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(getElementSelector(selector)));
        } catch (TimeoutException e) {
            AbstractTest.failTest("Element: " + selector + " did was not visible after: " + timeoutInSeconds + " s");
        }
    }

    /**---------------------------- Input ----------------------------*/

    //click
    public static void click (String selector) {
        try {
            findElement(selector).click();
        } catch (WebDriverException e) {
            AbstractTest.failTest("It was not possible to click element " + selector);
        }
    }

    public static void clearField(String selector) {
        findElement(selector).clear();
    }

    public static void inputTextToField(String selector, String text) {
        for (int i = 0; i < text.length(); i++) {
            findElement(selector).sendKeys(text.charAt(i) + "");
        }
    }

    public static void clearAndInputTextToField(String selector, String text) {
        clearField(selector);
        inputTextToField(selector, text);
    }


    /**---------------------------- Navigation ----------------------------*/

    //navigate to URL
    public static void openPage(String url) {
        log.info("Navigating to URL: "+ url);
        driver().navigate().to(url);
    }

    //refresh page
    public static void refreshPage() {
        log.info("Refreshing page...");
        driver().navigate().refresh();
    }

    //go back to previous page
    public static void backToPreviousPage(){
        log.info("Navigating to previous page");
        driver().navigate().back();
    }


    /**---------------------------- Getters ----------------------------*/

    //get text of the element
    public static String getElementText(String selector) {
        log.info("Getting text of " + selector + " element");
        return findElement(selector).getText();
    }


    /**---------------------------- Booleans ----------------------------*/

    //is element visible?
    public static boolean isVisible(String selector) {
        log.info("Checking if " + selector + " visible");
        return driver().findElement(getElementSelector(selector)).isDisplayed();
    }

    //is element present in DOM?
    public static boolean isElementPresent(String selector) {
        log.info("Checking if " + selector + " present");
        return driver().findElements(getElementSelector(selector)).size()!=0;
    }


    /**---------------------------- Private service methods ----------------------------*/
    //current driver
    private static WebDriver driver(){
        return WebDriverFactory.getDriver();
    }

    //get web element
    private static WebElement findElement(String byString){
        return driver().findElement(getElementSelector(byString));
    }

    //verify element presence on the page
    private static boolean isElementVisible(By selector) {
        try {
            return driver().findElement(selector).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    //get proper selector from argument string
    private static By getElementSelector(String identity) {
        By[] selectors = {
                By.id(identity),
                By.name(identity),
                By.cssSelector(identity),
                By.xpath(identity)};
        for (By selector : selectors) {
            if (isElementVisible(selector)) {
                return selector;
            }
        }
        AbstractTest.failTest("Could not find any visible element with identity - "
                + identity);
        return null;
    }

}

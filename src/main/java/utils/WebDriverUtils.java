package utils;

import architecture.WebDriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class WebDriverUtils {

    private static Logger log = Logger.getAnonymousLogger();
    //timeout constants
    private static final int TIMEOUT = 10;




    /**---------------------------- Waiters ----------------------------*/

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
    public static void waitForElementVisibility (By locator) {
        waitForElementVisibility(locator, TIMEOUT);
    }

    //wait for element visibility
    public static void waitForElementVisibility (By locator, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver(),timeoutInSeconds);

        log.info("Waiting for visibility of element " + locator);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            AbstractTest.failTest("Element: " + locator + " was not visible after: " + timeoutInSeconds + " s");
        }
    }


    /**---------------------------- Input ----------------------------*/

    //click
    public static void click (By locator) {
        try {
            findElement(locator).click();
        } catch (WebDriverException e) {
            AbstractTest.failTest("It was not possible to click element " + locator);
        }
    }

    public static void clearField(By locator) {
        findElement(locator).clear();
    }

    public static void inputTextToField(By locator, String text) {
        findElement(locator).sendKeys(text);
    }

    public static void clearAndInputTextToField(By locator, String text) {
        clearField(locator);
        inputTextToField(locator, text);
    }

    public static void inputTextToTextArea(By locator, String text) {
        findElement(locator).sendKeys(text);
    }

    public static void setDropdownOptionByValue(By locator, String value) {
        Select select = new Select(findElement(locator));
        select.selectByValue(value);
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
    public static String getElementText(By locator) {
        log.info("Getting text of " + locator + " element");
        return findElement(locator).getText();
    }

    public static boolean getCheckBoxState(By locator) {
        log.info("Getting text of " + locator + " element");
        return findElement(locator).isSelected();
    }

    public static String getAttribute(By locator, String attribute) {
        log.info("Getting "+ attribute + " value of " + locator + " element");
        return driver().findElement(locator).getAttribute(attribute);
    }

    public static String getCurrentUrl(){
        return driver().getCurrentUrl();
    }

    /**---------------------------- Booleans ----------------------------*/

    //is element visible?
    public static boolean isElementVisible(By locator) {
        log.info("Checking if " + locator + " is visible");
        return findElement(locator).isDisplayed();
    }

    //are multiple element visible?
    public static boolean areSeveralElementsVisible(By locator, int expectedElementsCount) {
        boolean result;

        log.info("Checking if multiple elements:" + locator + " are visible");
        List<WebElement> elements = findElements(locator);
        result = (elements.size() == expectedElementsCount);

        for (WebElement element: elements) {
            result&=element.isDisplayed();
        }

        return result;
    }

    public static boolean isNewTabOpened(String expectedUrlEnding){

        //get window handlers as list
        List<String> browserTabs = new ArrayList<String>(driver().getWindowHandles());

        //switch to new tab
        driver().switchTo().window(browserTabs .get(1));

        //check is it correct page opened or not (e.g. check page's title)
        String actualUrl=WebDriverUtils.getCurrentUrl();

        boolean isOpened = actualUrl.contains(expectedUrlEnding);
        // then close tab and get back
        driver().close();
        driver().switchTo().window(browserTabs.get(0));
        return isOpened;
    }


    /**---------------------------- Private service methods ----------------------------*/
    //current driver
    private static WebDriver driver(){
        return WebDriverFactory.getDriver();
    }

    //findElement element
    private static WebElement findElement(By locator) {

        WebDriverWait wait = new WebDriverWait(driver(), TIMEOUT);

        log.info("Waiting for presence of element " + locator);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            AbstractTest.failTest("Element: " + locator + " was not present in DOM after: " + TIMEOUT + " s");
        }
        return driver().findElement(locator);
    }

    private static List<WebElement> findElements(By locator) {

        WebDriverWait wait = new WebDriverWait(driver(), TIMEOUT);

        log.info("Waiting for presence of elements " + locator);
        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        } catch (TimeoutException e) {
            AbstractTest.failTest("Elements: " + locator + " were not present in DOM after: " + TIMEOUT + " s");
        }
        return driver().findElements(locator);
    }



}

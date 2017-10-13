package com.onarinskyi.utils;

import com.onarinskyi.core.AbstractTest;
import com.onarinskyi.core.Environment;
import com.onarinskyi.core.WebDriverFactory;
import com.onarinskyi.interfaces.Page;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static com.onarinskyi.time.Timeout.EXPLICIT_WAIT;

public class Driver {

    private WebDriver driver;

    public Driver() {
        this.driver = WebDriverFactory.getInstance().getDriver();
    }

    private final Logger log = Logger.getLogger(Driver.class);

    /**
     * ---------------------------- Waiters ----------------------------
     */

    //regular sleep with configurable timeout
    public void waitFor(long millisec) {
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
    public void waitForElementVisibility(By locator) {
        waitForElementVisibility(locator, EXPLICIT_WAIT);
    }

    //wait for element visibility
    public void waitForElementVisibility(By locator, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);

        log.info("Waiting for visibility of element " + locator);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            AbstractTest.failTest("Element: " + locator + " was not visible after: " + timeoutInSeconds + " s");
        }
    }

    //wait for element text to change
    public boolean isElementTextChangedTo(By locator, String newText) {
        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT);
        log.info("Waiting for element change " + locator);
        try {
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return (driver.findElement(locator).getText()).equalsIgnoreCase(newText);
                }
            });
        } catch (TimeoutException timeIsOut) {
            return false;
        }
        return true;
    }


    /**
     * ---------------------------- Input ----------------------------
     */

    //click
    public void click(By locator) {
        try {
            findVisibleElement(locator).click();
        } catch (WebDriverException e) {
            AbstractTest.failTest("It was not possible to click element " + locator);
        }
    }

    public void tap(String selector) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        //String js = "var event = $.Event( 'touchstart', { pageX:200, pageY:200 } );";
        executor.executeScript("var event = $.Event( 'touchstart', { pageX:200, pageY:200 } );"
                + "$('" + selector + "').trigger( event );");
    }

    public void clearField(By locator) {
        findVisibleElement(locator).clear();
    }

    public void inputTextToField(By locator, String text) {
        findVisibleElement(locator).sendKeys(String.valueOf(text));
    }

    //when unable to focus the field
    public void inputTextToInvisibleField(By locator, String text) {
        findElement(locator).sendKeys(String.valueOf(text));
    }

    public void clearAndInputTextToField(By locator, String text) {
        clearField(locator);
        inputTextToField(locator, text);
    }

    public void inputTextToTextArea(By locator, String text) {
        findElement(locator).sendKeys(text);
    }

    public void setDropdownOptionByValue(By locator, String value) {
        Select select = new Select(findElement(locator));
        select.selectByValue(value);
    }

    public void executeJavascript(String javascript) {
        ((JavascriptExecutor) driver).executeScript(javascript);
    }

    public void clickJS(By locator) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        log.info("Clicking the element via Javascript");
        executor.executeScript("arguments[0].click();", findElement(locator));
    }


    /**
     * ---------------------------- Navigation ----------------------------
     */

    //navigate to URL
    public void openPage(String url) {
        log.info("Navigating to URL: " + url);
        driver.navigate().to(url);
    }

    public void openPage(Page page) {
        String url = UrlResolver.resolveUrlFor(page);
        log.info("Navigating to URL: " + url);
        driver.navigate().to(url);
    }

    //refresh page
    public void refreshPage() {
        log.info("Refreshing page...");
        driver.navigate().refresh();
    }

    //go back to previous page
    public void backToPreviousPage() {
        log.info("Navigating to previous page");
        driver.navigate().back();
    }


    /**
     * ---------------------------- Getters ----------------------------
     */

    //get text of the element
    public String getElementText(By locator) {
        log.info("Getting text of " + locator + " element");
        return findElement(locator).getText();
    }

    public boolean isElementText(By locator, String text) {
        log.info("Getting asynchronous text of " + locator + " element");
        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT);
        return wait.until(ExpectedConditions.textToBePresentInElementValue(findElement(locator), text));
    }

    public boolean isCheckboxChecked(By locator) {
        log.info("Getting text of " + locator + " element");
        return findElement(locator).isSelected();
    }

    public String getAttribute(By locator, String attribute) {
        log.info("Getting " + attribute + " value of " + locator + " element");
        return findElement(locator).getAttribute(attribute);
    }

    public String getCssValue(By locator, String cssKey) {
        log.info("Getting css " + cssKey + " value of " + locator + " element");
        return findElement(locator).getCssValue(cssKey);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getURLSuffix() {
        return driver.getCurrentUrl().
                replaceAll(Environment.getBaseUrl(), "");
    }

    public String getSelectedOption(By locator) {
        log.info("Getting selected option of: " + locator + " dropdown");

        Select select = new Select(findElement(locator));
        WebElement selectedOption = select.getFirstSelectedOption();
        return selectedOption.getText();
    }

    public byte[] makeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    /**
     * ---------------------------- Booleans ----------------------------
     */

    //is element visible?
    public boolean isElementVisible(By locator) {
        log.info("Checking if " + locator + " is visible");
        return findVisibleElement(locator) != null;
    }

    //is element present?
    public boolean isElementPresent(By locator) {
        log.info("Checking if " + locator + " is present");
        return findElement(locator) != null;
    }

    //are multiple element visible?
    public boolean areSeveralElementsVisible(By locator, int expectedElementsCount) {
        boolean result;

        log.info("Checking if multiple elements:" + locator + " are visible");
        List<WebElement> elements = findElements(locator);
        result = (elements.size() == expectedElementsCount);

        for (WebElement element : elements) {
            result &= element.isDisplayed();
        }

        return result;
    }

    public boolean isNewTabOpened(String expectedURLSuffix) {

        //get window handlers as list
        List<String> browserTabs = new ArrayList<>(driver.getWindowHandles());

        //try to switch to new tab (wait until it's opened)
        for (int i = 0; i < 3; i++) {
            try {
                driver.switchTo().window(browserTabs.get(1));
                break;
            } catch (IndexOutOfBoundsException e) {
                if (i == 2) return false;
                waitFor(500);
            }
        }

        boolean isOpened = getURLSuffix().equals(expectedURLSuffix);
        // then close tab and get back
        driver.close();
        driver.switchTo().window(browserTabs.get(0));
        return isOpened;
    }

    public boolean isNewTabOpened() {
        //get window handlers as list
        List<String> browserTabs = new ArrayList<>(driver.getWindowHandles());

        //try to switch to new tab (wait until it's opened)
        for (int i = 0; i < 3; i++) {
            try {
                driver.switchTo().window(browserTabs.get(1));
                break;
            } catch (IndexOutOfBoundsException e) {
                if (i == 2) return false;
                waitFor(1000);
            }
        }

        // then close tab and get back
        driver.close();
        driver.switchTo().window(browserTabs.get(0));
        return true;
    }


    /**
     * ---------------------------- Private service methods ----------------------------
     */


    //findElement element
    private WebElement findElement(By locator) {

        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT);

        log.info("Waiting for presence of element " + locator);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            AbstractTest.failTest("Element: " + locator + " was not present in DOM after: " + EXPLICIT_WAIT + " s");
        }
        return driver.findElement(locator);
    }

    private List<WebElement> findElements(By locator) {

        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT);

        log.info("Waiting for presence of elements " + locator);
        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        } catch (TimeoutException e) {
            AbstractTest.failTest("Elements: " + locator + " were not present in DOM after: " + EXPLICIT_WAIT + " s");
        }
        return driver.findElements(locator);
    }

    private WebElement findVisibleElement(By locator) {

        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT);

        log.info("Waiting for visibility of element " + locator);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            AbstractTest.failTest("Element: " + locator + " was not visible after: " + EXPLICIT_WAIT + " s");
        }
        return driver.findElement(locator);

    }


}

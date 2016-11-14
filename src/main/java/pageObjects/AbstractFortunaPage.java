package pageObjects;

import org.openqa.selenium.By;
import utils.WebDriverUtils;

public abstract class AbstractFortunaPage extends AbstractPage {

    private static final By ERROR_MESSAGE = By.cssSelector(".message.error");
    private static final By SUCCESS_MESSAGE = By.cssSelector(".message.success");

    public boolean areSuccessMessagesDisplayed(int howMany){
        return WebDriverUtils.areSeveralElementsVisible(SUCCESS_MESSAGE, howMany);
    }

    public boolean areErrorMessagesDisplayed(int howMany){
        return WebDriverUtils.areSeveralElementsVisible(ERROR_MESSAGE, howMany);
    }

    public boolean isFieldValid (By locator) {

        return WebDriverUtils.getAttribute(locator, "class").contains("valid");

    }

    public boolean isFieldInvalid (By locator) {
        return WebDriverUtils.getAttribute(locator, "class").contains("invalid");
    }



}

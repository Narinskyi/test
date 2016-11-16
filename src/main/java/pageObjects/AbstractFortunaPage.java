package pageObjects;

import org.openqa.selenium.By;
import utils.WebDriverUtils;

public abstract class AbstractFortunaPage extends AbstractPage {

    private static final By ERROR_MESSAGE = By.cssSelector(".message.error");
    private static final By SUCCESS_MESSAGE = By.cssSelector(".message.success");
    private static final By POPUP_MOBILE = By.cssSelector(".popup");
    private static final By BUTTON_CLOSE_POPUP_MOBILE = By.cssSelector(".fn-close");
    private static final By BUTTON_SUBMIT = By.cssSelector("button[type='submit']");

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

    public boolean isMobilePopupDisplayed(){
        return WebDriverUtils.isElementVisible(POPUP_MOBILE);
    }

    public void closeMobilePopup(){
        WebDriverUtils.click(BUTTON_CLOSE_POPUP_MOBILE);
    }

    public void clickSubmit(){
        WebDriverUtils.click(BUTTON_SUBMIT);
    }



}

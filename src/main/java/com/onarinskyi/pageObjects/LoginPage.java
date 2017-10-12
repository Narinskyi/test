package com.onarinskyi.pageObjects;

import com.onarinskyi.core.AbstractPage;
import org.openqa.selenium.By;
import com.onarinskyi.utils.Driver;

public class LoginPage extends AbstractPage {

    private static final By INPUT_USERNAME = By.cssSelector("input.fn-user-name");
    private static final By INPUT_PASSWORD = By.name("password");
    private static final By EYE_ICON = By.cssSelector(".fn-toggle-password-visibility");
    private static final String EYE_ICON_CSS = ".fn-toggle-password-visibility";
    private static final By CHECKBOX_REMEMBER_ME = By.cssSelector("label[for=rememberme]");
    private static final By BUTTON_LOGIN = By.cssSelector(".fn-login-btn");
    private static final By LINK_FORGOTTEN_PASSWORD = By.xpath("//a[@href='/forgotten-password']");
    private static final By LINK_REGISTER = By.xpath("//a[@href='/register-step-1']");

    private static final By VALIDATION_USERNAME = By.cssSelector(".field_name_username");
    private static final By VALIDATION_PASSWORD = By.cssSelector(".field_name_password");

    private static final By BUTTON_ACCEPT_TC = By.cssSelector("span.fn-accept");


    public void enterUsername(String username) {
        Driver.inputTextToField(INPUT_USERNAME, username);
    }

    public void clearUsername() {
        Driver.clearField(INPUT_USERNAME);
    }

    public String getUsernameInputText() {
        return Driver.getAttribute(INPUT_USERNAME, "value");
    }

    public void enterPassword(String password) {
        Driver.inputTextToField(INPUT_PASSWORD, password);
    }

    public boolean isRememberMeChecked() {
        return Driver.isCheckboxChecked(CHECKBOX_REMEMBER_ME);
    }

    public void clickRememberMe() {
        Driver.click(CHECKBOX_REMEMBER_ME);
    }

    public void clickLogin() {
        Driver.click(BUTTON_LOGIN);
    }

    public void clickForgottenPassword() {
        Driver.click(LINK_FORGOTTEN_PASSWORD);
    }

    public void clickRegister() {
        Driver.click(LINK_REGISTER);
    }

    public void clickAcceptTC() { Driver.click(BUTTON_ACCEPT_TC);}

    public String getUsernamePlaceholder() {
        return Driver.getAttribute(INPUT_USERNAME, "placeholder");
    }

    public String getPasswordPlaceholder() {
        return Driver.getAttribute(INPUT_PASSWORD, "placeholder");
    }

    public boolean isPasswordVisible() {

        String passwordVisibilityType = Driver.getAttribute(INPUT_PASSWORD, "type");

        switch (passwordVisibilityType) {

            case "password":
                return false;
            case "text":
                return true;
            default:
                return false;
        }
    }

    public boolean isForgottenPasswordPageOpened(){
        return Driver.getURLSuffix().equals("forgotten-password");
    }

    public boolean isRegisterPageOpened() {
        return Driver.isNewTabOpened("register-step-1");
    }

}

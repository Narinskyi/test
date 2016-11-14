package pageObjects;


import org.openqa.selenium.By;
import utils.WebDriverUtils;

public class LoginPage extends AbstractFortunaPage {

    private static final By INPUT_USERNAME = By.name("userName");
    private static final By INPUT_PASSWORD = By.name("password");
    private static final By EYE_ICON = By.cssSelector(".fn-password-visibility");
    private static final By CHECKBOX_REMEMBER_ME = By.id("rememberme");
    private static final By BUTTON_LOGIN = By.cssSelector(".fn-login-btn");
    private static final By LINK_FORGOTTEN_PASSWORD = By.xpath("//a[@href='/forgotten-password']");
    private static final By LINK_REGISTER = By.xpath("//a[@href='/register-step-1']");

    private static final By VALIDATION_USERNAME = By.cssSelector(".field_name_username");
    private static final By VALIDATION_PASSWORD = By.cssSelector(".field_name_password");

    public void enterUsername(String username) {
        WebDriverUtils.inputTextToField(INPUT_USERNAME, username);
    }

    public void clearUsername() {
        WebDriverUtils.clearField(INPUT_USERNAME);
    }

    public String getUsernameInputText() {
        return WebDriverUtils.getElementText(INPUT_USERNAME);
    }

    public void enterPassword(String password) {
        WebDriverUtils.inputTextToField(INPUT_PASSWORD, password);
    }

    public boolean isRememberMeChecked() {
        return WebDriverUtils.getCheckBoxState(CHECKBOX_REMEMBER_ME);
    }

    public void clickRememberMe() {
        WebDriverUtils.click(CHECKBOX_REMEMBER_ME);
    }

    public void clickLogin() {
        WebDriverUtils.click(BUTTON_LOGIN);
    }

    public void clickForgottenPassword() {
        WebDriverUtils.click(LINK_FORGOTTEN_PASSWORD);
    }

    public void clickRegister() {
        WebDriverUtils.click(LINK_REGISTER);
    }

    public String getUsernamePlaceholder() {
        return WebDriverUtils.getAttribute(INPUT_USERNAME, "placeholder");
    }

    public String getPasswordPlaceholder() {
        return WebDriverUtils.getAttribute(INPUT_PASSWORD, "placeholder");
    }

    public void clickEyeIcon() {
        WebDriverUtils.click(EYE_ICON);
    }

    public boolean isUsernameInvalid() {
        return isFieldInvalid(VALIDATION_USERNAME);
    }

    public boolean isPasswordInvalid() {
        return isFieldInvalid(VALIDATION_PASSWORD);
    }

    public boolean isPasswordVisible() {

        String passwordVisibilityType = WebDriverUtils.getAttribute(INPUT_PASSWORD, "type");

        switch (passwordVisibilityType) {

            case "password":
                return false;
            case "text":
                return true;

            default:
                return false;
        }
    }

}

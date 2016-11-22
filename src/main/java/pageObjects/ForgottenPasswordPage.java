package pageObjects;

import org.openqa.selenium.By;
import utils.DataProvider;
import utils.WebDriverUtils;

public class ForgottenPasswordPage extends AbstractFortunaPage{

    private static final By ROOT = By.cssSelector(".forgotten-password-page");
    private static final By FORTUNA_LOGO = By.cssSelector(".main-header__logo");
    private static final By SELECT_DAY = By.id("day");
    private static final By SELECT_MONTH = By.id("month");
    private static final By SELECT_YEAR = By.id("year");
    private static final By SELECT_MOBILE = By.id("date");
    private static final By INPUT_USERNAME = By.name("username");
    private static final By LINK_LOGIN = By.cssSelector(".forgotten-password-page__login-link a");
    private static final By LINK_REGISTER = By.cssSelector(".forgotten-password-page__register-link a");
    private static final By LINK_CONTACT_US_MOBILE = By.cssSelector(".fn-wc-container a");
    private static final By CONFIRMATION_MESSAGE = By.cssSelector(".fn-confirmation-information");
    private static final By RECAPTCHA = By.cssSelector(".fn-recaptcha>div");

    private static final By VALIDATION_BIRTHDAY = By.cssSelector(".fieldset_name_birthdate");
    private static final By VALIDATION_USERNAME = By.cssSelector(".fn-username");

    //today is 18
    public void enterValidBirthday() {
        int year = Integer.valueOf(DataProvider.DateUtils.getCurrentYear())-18;

        WebDriverUtils.setDropdownOptionByValue(SELECT_DAY, DataProvider.DateUtils.getCurrentDay());
        WebDriverUtils.setDropdownOptionByValue(SELECT_MONTH, DataProvider.DateUtils.getCurrentMonth());
        WebDriverUtils.setDropdownOptionByValue(SELECT_YEAR, Integer.toString(year));
    }

    //too young
    public void enterInvalidBirthday() {
        int year = Integer.valueOf(DataProvider.DateUtils.getCurrentYear())-17;

        WebDriverUtils.setDropdownOptionByValue(SELECT_DAY, DataProvider.DateUtils.getCurrentDay());
        WebDriverUtils.setDropdownOptionByValue(SELECT_MONTH, DataProvider.DateUtils.getCurrentMonth());
        WebDriverUtils.setDropdownOptionByValue(SELECT_YEAR, Integer.toString(year));
    }

    //today is 18
    public void enterValidBirthdayMobile() {
        WebDriverUtils.inputTextToField(SELECT_MOBILE, DataProvider.DateUtils.getSomeYearsAgo(18));
    }

    //too young
    public void enterInvalidBirthdayMobile() {
        WebDriverUtils.inputTextToField(SELECT_MOBILE, DataProvider.DateUtils.getSomeYearsAgo(17));
    }

    public void enterUsername(String username) {
        WebDriverUtils.inputTextToField(INPUT_USERNAME, username);
    }

    public void clickLoginLink() {
        WebDriverUtils.click(LINK_LOGIN);
    }

    public void clickRegisterLink() {
        WebDriverUtils.click(LINK_REGISTER);
    }

    public void clickContactUsMobileLink() {
        WebDriverUtils.click(LINK_CONTACT_US_MOBILE);
    }


    public boolean isConfirmationInfoVisible(){
        return WebDriverUtils.isElementVisible(CONFIRMATION_MESSAGE);
    }

    public boolean isReCaptchaVisible(){
        return WebDriverUtils.isElementVisible(RECAPTCHA);
    }

    public boolean isBirthdayInvalid(){
        return isFieldInvalid(VALIDATION_BIRTHDAY);
    }

    public boolean isUsernameInvalid(){
        return isFieldInvalid(VALIDATION_USERNAME);
    }

    public boolean isLoginPageOpened(){
        return WebDriverUtils.getCurrentUrl().contains("login");
    }

    public boolean isContactUsMobilePageOpened(){
        return WebDriverUtils.getCurrentUrl().contains("contact-us-mobile");
    }

    public boolean isRegistrationPage() {
        return WebDriverUtils.getCurrentUrl().contains("register-step-1");
    }

    public void openForgottenPasswordTabletPage(){
        WebDriverUtils.openPage(DataProvider.getBaseUrl()+"forgotten-password-tablet");
    }

    public boolean isForgottenPasswordTabletPage(){
        return WebDriverUtils.isElementVisible(ROOT)&&
                WebDriverUtils.isElementVisible(FORTUNA_LOGO);
    }

}

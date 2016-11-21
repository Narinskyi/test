package Tests;

import core.PageFactory;
import enums.AvailablePages;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ForgottenPasswordPage;
import core.AbstractTest;

public class ForgottenPasswordTest extends AbstractTest{

    private static ForgottenPasswordPage forgottenPasswordPage = PageFactory.getPage(AvailablePages.forgottenPassword);

    @Test (groups = {"desktop"})
    public void validHardcodedInputTest(){
        forgottenPasswordPage.open();

        forgottenPasswordPage.enterValidBirthday();
        forgottenPasswordPage.enterUsername("SUCCESS");
        forgottenPasswordPage.clickSubmit();

        Assert.assertTrue(forgottenPasswordPage.areSuccessMessagesDisplayed(1), "Success message was not displayed");
        Assert.assertTrue(forgottenPasswordPage.isConfirmationInfoVisible(), "Confirmation info was not displayed");
    }

    @Test (groups = {"tablet"})
    public void validHardcodedInputTestT(){
        forgottenPasswordPage.open();

        forgottenPasswordPage.enterValidBirthdayMobile();
        forgottenPasswordPage.enterUsername("SUCCESS");
        forgottenPasswordPage.clickSubmit();

        Assert.assertTrue(forgottenPasswordPage.areSuccessMessagesDisplayed(1), "Success message was not displayed");
        Assert.assertTrue(forgottenPasswordPage.isConfirmationInfoVisible(), "Confirmation info was not displayed");
    }

    @Test (groups = {"mobile"})
    public void validHardcodedInputTestM(){
        forgottenPasswordPage.open();

        forgottenPasswordPage.enterValidBirthdayMobile();
        forgottenPasswordPage.enterUsername("SUCCESS");
        forgottenPasswordPage.clickSubmit();

        Assert.assertTrue(forgottenPasswordPage.isMobilePopupDisplayed(), "Success popup was not displayed");
    }


    @Test (groups = {"desktop", "tablet"})
    public void loginLinkTest(){

        forgottenPasswordPage.open();

        //verify login link
        forgottenPasswordPage.clickLoginLink();
        Assert.assertTrue(forgottenPasswordPage.isLoginPageOpened(), "Login page was not opened");
    }

    @Test (groups = {"mobile"})
    public void contactUsLinkTest(){

        forgottenPasswordPage.open();

        //verify login link
        forgottenPasswordPage.clickContactUsMobileLink();
        Assert.assertTrue(forgottenPasswordPage.isContactUsMobilePageOpened(), "Contact us mobile page was not opened");
    }

    @Test (groups = {"desktop", "tablet"})
    public void registrationLinkTest(){

        forgottenPasswordPage.open();

        //verify registration link
        forgottenPasswordPage.clickRegisterLink();
        Assert.assertTrue(forgottenPasswordPage.isRegistrationpage(), "Registration page was not opened");
    }

    @Test(groups = {"desktop"})
    public void emptyFieldsTest() {

        forgottenPasswordPage.open();

        //verify empty fields submission
        forgottenPasswordPage.clickSubmit();
        Assert.assertTrue(forgottenPasswordPage.isBirthdayInvalid(), "Birthday validation failed");
        Assert.assertTrue(forgottenPasswordPage.isUsernameInvalid(), "Username validation failed");

        //verify empty username and invalid birthday
        forgottenPasswordPage.enterInvalidBirthday();
        forgottenPasswordPage.clickSubmit();
        Assert.assertTrue(forgottenPasswordPage.isBirthdayInvalid(), "Birthday validation failed");
        Assert.assertTrue(forgottenPasswordPage.isUsernameInvalid(), "Username validation failed");

        //verify
        forgottenPasswordPage.enterUsername("INVALID");
        forgottenPasswordPage.clickSubmit();
        Assert.assertTrue(forgottenPasswordPage.isBirthdayInvalid(), "Birthday validation failed");
    }

    @Test(groups = {"tablet", "mobile"})
    public void emptyFieldsTestTM() {

        forgottenPasswordPage.open();

        //verify empty fields submission
        forgottenPasswordPage.clickSubmit();
        Assert.assertTrue(forgottenPasswordPage.isBirthdayInvalid(), "Birthday validation failed");
        Assert.assertTrue(forgottenPasswordPage.isUsernameInvalid(), "Username validation failed");

        //verify empty username and invalid birthday
        forgottenPasswordPage.enterInvalidBirthdayMobile();
        forgottenPasswordPage.clickSubmit();
        Assert.assertTrue(forgottenPasswordPage.isBirthdayInvalid(), "Birthday validation failed");
        Assert.assertTrue(forgottenPasswordPage.isUsernameInvalid(), "Username validation failed");

        //verify
        forgottenPasswordPage.enterUsername("INVALID");
        forgottenPasswordPage.clickSubmit();
        Assert.assertTrue(forgottenPasswordPage.isBirthdayInvalid(), "Birthday validation failed");
    }


    //invalid username - reCaptcha appears
    @Test(groups = {"desktop"})
    public void invalidInputTest() {

        forgottenPasswordPage.open();

        forgottenPasswordPage.enterValidBirthday();
        forgottenPasswordPage.enterUsername("INVALID");
        forgottenPasswordPage.clickSubmit();

        Assert.assertTrue(forgottenPasswordPage.isReCaptchaVisible(), "ReCaptcha was not displayed");
        Assert.assertTrue(forgottenPasswordPage.areErrorMessagesDisplayed(1), "Error message was not displayed");

        //restart browser to ensure Captcha does not remain displayed
        restart();
    }

    //invalid username - reCaptcha appears
    @Test(groups = {"tablet"})
    public void invalidInputTestT() {

        forgottenPasswordPage.open();

        forgottenPasswordPage.enterValidBirthdayMobile();
        forgottenPasswordPage.enterUsername("INVALID");
        forgottenPasswordPage.clickSubmit();

        Assert.assertTrue(forgottenPasswordPage.isReCaptchaVisible(), "ReCaptcha was not displayed");
        Assert.assertTrue(forgottenPasswordPage.areErrorMessagesDisplayed(1), "Error message was not displayed");

        //restart browser to ensure Captcha does not remain displayed
        restart();
    }

    //invalid username - reCaptcha appears
    @Test(groups = {"mobile"})
    public void invalidInputTestM() {

        forgottenPasswordPage.open();

        forgottenPasswordPage.enterValidBirthdayMobile();
        forgottenPasswordPage.enterUsername("INVALID");
        forgottenPasswordPage.clickSubmit();

        Assert.assertTrue(forgottenPasswordPage.isReCaptchaVisible(), "ReCaptcha was not displayed");
        Assert.assertTrue(forgottenPasswordPage.isMobilePopupDisplayed(), "Mobile error popup was not displayed");

        //restart browser to ensure Captcha does not remain displayed
        restart();
    }

    //verify that forgotten-password-tablet page can be opened
    @Test(groups = {"tablet"})
    public void forgottenPasswordTabletTest() {
        forgottenPasswordPage.openForgottenPasswordTabletPage();
        Assert.assertTrue(forgottenPasswordPage.isForgottenPasswordTabletPage(), "Page is not forgotten-password-tablet page");
    }

}

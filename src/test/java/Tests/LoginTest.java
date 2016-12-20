package Tests;

import Preconditions.PreconditionalSteps;
import core.AbstractTest;
import core.PageFactory;
import enums.AvailablePages;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import utils.DataProvider;

public class LoginTest extends AbstractTest {

    private static LoginPage loginPage = PageFactory.getPage(AvailablePages.login);

    private static final String INVALID_USERNAME = "invalid";
    private static final String INVALID_PASSWORD = "invalidPassword";
    private static final String VALID_PASSWORD = "Password1";

    @BeforeClass
    public void prepareUser(){
        PreconditionalSteps.createUser();
    }

    @Test (groups = "desktop")
    public void uiElementsTestD(){

        loginPage.open();
        loginPage.clickLogin();

        //verify header, placeholders and that remember me is unchecked by default
        Assert.assertTrue(loginPage.getUsernamePlaceholder().contains("username/email"), "Username placeholder failed");
        Assert.assertTrue(loginPage.getPasswordPlaceholder().contains("password"), "Password placeholder failed");
        //Assert.assertTrue(loginPage.isUsernameInvalid(), "Username validation failed");
        Assert.assertTrue(loginPage.isPasswordInvalid(), "Password validation failed");
        Assert.assertTrue(!loginPage.isRememberMeChecked(), "Remember me unchecked by default failed");
    }

    @Test (groups = {"tablet", "mobile"})
    public void uiElementsTestTM(){

        loginPage.open();
        loginPage.clickLogin();

        //verify header, placeholders and that remember me is unchecked by default
        Assert.assertTrue(loginPage.isUsernameInvalid(), "Username validation failed");
        Assert.assertTrue(loginPage.isPasswordInvalid(), "Password validation failed");
        Assert.assertTrue(!loginPage.isRememberMeChecked(), "Remember me unchecked by default failed");
    }

    @Test (groups = {"desktop", "tablet", "mobile"})
    public void passwordVisibilityIconTest(){

        loginPage.open();

        //verify that password is invisible by default
        loginPage.enterPassword(INVALID_PASSWORD);
        Assert.assertFalse(loginPage.isPasswordVisible(), "Password masking verification failed");

        //verify that password visibility function works
        loginPage.clickEyeIcon();
        Assert.assertTrue(loginPage.isPasswordVisible(), "Password visibility verification failed");

    }

    @Test(groups = {"desktop", "tablet", "mobile"})
    public void forgottenPasswordLinkTest(){

        loginPage.open();

        //verify forgotten password link
        loginPage.clickForgottenPassword();
        Assert.assertTrue(loginPage.isForgottenPasswordPageOpened(), "Forgotten password link verification failed");

    }

    @Test(groups = {"desktop", "tablet"})
    public void registrationLinkTest(){

        loginPage.open();

        //verify registration link
        loginPage.clickRegister();
        Assert.assertTrue(loginPage.isRegisterPageOpened(), "Register link verification failed");

    }


    @Test (groups = {"desktop", "tablet", "mobile"})
    public void emptyFieldsTest() {

        loginPage.open();

        //empty fields
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isUsernameInvalid(), "Username validation failed");
        Assert.assertTrue(loginPage.isPasswordInvalid(), "Password validation failed");

        //empty password
        loginPage.enterUsername(INVALID_USERNAME);
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isPasswordInvalid(), "Password validation failed");

        //empty username
        loginPage.clearUsername();
        loginPage.enterPassword(INVALID_PASSWORD);
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isUsernameInvalid(), "Username validation failed");

    }

    @Test (groups = {"desktop", "tablet"})
    public void invalidBothFieldsTest(){

        loginPage.open();

        loginPage.enterUsername("invalid");
        loginPage.enterPassword(INVALID_PASSWORD);
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.areErrorMessagesDisplayed(1), "Error message was not displayed");
    }

    @Test (groups = {"mobile"})
    public void invalidBothFieldsTestM(){

        loginPage.open();

        loginPage.enterUsername("invalid");
        loginPage.enterPassword(INVALID_PASSWORD);
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isMobilePopupDisplayed(), "Error message was not displayed");
    }

    @Test (groups = {"desktop", "tablet"})
    public void invalidPasswordTest() {

        loginPage.open();
        loginPage.enterUsername(DataProvider.getUserData().getUsername());
        loginPage.enterPassword(INVALID_PASSWORD);
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.areErrorMessagesDisplayed(1), "Error message was not displayed");
    }

    @Test (groups = {"mobile"})
    public void invalidPasswordTestM(){

        loginPage.open();

        loginPage.enterUsername(DataProvider.getUserData().getUsername());
        loginPage.enterPassword(INVALID_PASSWORD);
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isMobilePopupDisplayed(), "Error message was not displayed");
    }

    @Test (groups = {"desktop", "tablet", "mobile"})
    public void validInputTest() {

        loginPage.open();

        loginPage.enterUsername(DataProvider.getUserData().getUsername());
        loginPage.enterPassword(VALID_PASSWORD);
        loginPage.clickLogin();

        //verify that user is logged-in successfully
        Assert.assertTrue(loginPage.isDashboardPageOpened());

        //after test-logout
        loginPage.logout();
    }
//
//    @Test (priority = 3)
//    public void rememberMeTest(){
//
//        loginPage = (LoginPage)NavigationUtils.navigateToPage(PlayerCondition.guest, ConfiguredPages.login);
//
//        loginPage.enterUsername(username);
//        loginPage.enterPassword(VALID_PASSWORD);
//        loginPage.clickRememberMe();
//        loginPage.clickLogin();
//
//        //verify that user is logged-in successfully (with remember me checked)
//        Assert.assertTrue(loginPage.isMyAccountPage());
//
//        loginPage.fortunaLogout();
//
//        Assert.assertTrue(loginPage.getUsernameInputText().equals(username)&&
//                loginPage.isRememberMeChecked());
//    }
}

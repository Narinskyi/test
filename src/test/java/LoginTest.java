import architecture.PageFactory;
import enums.AvailablePages;
import org.junit.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import utils.AbstractTest;

public class LoginTest extends AbstractTest {

    private static LoginPage loginPage = PageFactory.getPage(AvailablePages.login);

    private static final String INVALID_USERNAME = "invalid";
    private static final String INVALID_PASSWORD = "jdss&@^n#342";
    private static final String VALID_PASSWORD = "Password1";

    private String username;


    //reason to create user in test body is to save the username (could have been done through AbstractMyAccount test)
//    @BeforeClass
//    public void createUser(){
//
//        UserData userData = DataContainer.getUserData();
//
//        //generate a random username and set it
//        username= UUID.randomUUID().toString().substring(0,7);
//        userData.setUsername(username);
//
//        BackEndUtils.createUser(this,userData);
//
//    }

    @Test (groups = "desktop")
    public void uiElementsVerificationD(){

        loginPage.open();
        loginPage.clickLogin();

        //verify header, placeholders and that remember me is unchecked by default
        Assert.assertTrue("Username placeholder failed", loginPage.getUsernamePlaceholder().contains("username/email"));
        Assert.assertTrue("Password placeholder failed", loginPage.getPasswordPlaceholder().contains("password"));
        Assert.assertTrue("Username validation failed", loginPage.isUsernameInvalid());
        Assert.assertTrue("Password validation failed",loginPage.isPasswordInvalid());
        Assert.assertTrue("Remember me unchecked by default failed",!loginPage.isRememberMeChecked());
    }

    @Test (groups = {"tablet", "mobile"})
    public void uiElementsVerificationTM(){

        loginPage.open();
        loginPage.clickLogin();

        //verify header, placeholders and that remember me is unchecked by default
        Assert.assertTrue("Username validation failed", loginPage.isUsernameInvalid());
        Assert.assertTrue("Password validation failed",loginPage.isPasswordInvalid());
        Assert.assertTrue("Remember me unchecked by default failed",!loginPage.isRememberMeChecked());
    }

    @Test (groups = {"desktop", "tablet", "mobile"})
    public void passwordVisibilityIconVerification(){

        loginPage.open();

        //verify that password is invisible by default
        loginPage.enterPassword(INVALID_PASSWORD);
        Assert.assertFalse("Password masking verification failed", loginPage.isPasswordVisible());

        //verify that password visibility function works
        loginPage.clickEyeIcon();
        Assert.assertTrue("Password visibility verification failed", loginPage.isPasswordVisible());

    }

    @Test(groups = {"desktop", "tablet", "mobile"})
    public void forgottenPasswordLinkVerification(){

        loginPage.open();

        //verify forgotten password link
        loginPage.clickForgottenPassword();
        Assert.assertTrue("Forgotten password link verification failed", loginPage.isForgottenPasswordPageOpened());

    }

    @Test(groups = {"desktop", "tablet", "mobile"})
    public void registrationLinkVerification(){

        loginPage.open();

        //verify registration link
        loginPage.clickRegister();
        Assert.assertTrue("Register link verification failed", loginPage.isRegisterPageOpened());

    }


    @Test (groups = {"desktop", "tablet", "mobile"})
    public void emptyFieldsVerification() {

        loginPage.open();

        //empty fields
        loginPage.clickLogin();
        Assert.assertTrue("Username validation failed", loginPage.isUsernameInvalid());
        Assert.assertTrue("Password validation failed",loginPage.isPasswordInvalid());

        //empty password
        loginPage.enterUsername(INVALID_USERNAME);
        loginPage.clickLogin();
        Assert.assertTrue("Password validation failed",loginPage.isPasswordInvalid());

        //empty username
        loginPage.clearUsername();
        loginPage.enterPassword(INVALID_PASSWORD);
        loginPage.clickLogin();
        Assert.assertTrue("Username validation failed", loginPage.isUsernameInvalid());

    }

    @Test (groups = {"desktop", "tablet"})
    public void invalidBothFieldsVerification(){

        loginPage.open();

        loginPage.enterUsername("invalid");
        loginPage.enterPassword(INVALID_PASSWORD);
        loginPage.clickLogin();

        Assert.assertTrue("Error message was not displayed", loginPage.areErrorMessagesDisplayed(1));
    }

    @Test (groups = {"mobile"})
    public void invalidBothFieldsVerificationM(){

        loginPage.open();

        loginPage.enterUsername("invalid");
        loginPage.enterPassword(INVALID_PASSWORD);
        loginPage.clickLogin();

        Assert.assertTrue("Error message was not displayed", loginPage.isMobilePopupDisplayed());
    }

    @Test (groups = {"desktop", "tablet"})
    public void invalidPasswordVerification() {

        loginPage.enterUsername(username);
        loginPage.enterPassword(INVALID_PASSWORD);
        loginPage.clickLogin();

        Assert.assertTrue("Error message was not displayed", loginPage.areErrorMessagesDisplayed(1));
    }

    @Test (groups = {"mobile"})
    public void invalidPasswordVerificatioM(){

        loginPage.open();

        loginPage.enterUsername(username);
        loginPage.enterPassword(INVALID_PASSWORD);
        loginPage.clickLogin();

        Assert.assertTrue("Error message was not displayed", loginPage.isMobilePopupDisplayed());
    }

//    @Test (priority = 2)
//    public void validInputVerification() {
//
//        loginPage = (LoginPage)NavigationUtils.navigateToPage(PlayerCondition.guest, ConfiguredPages.login);
//
//        loginPage.enterUsername(username);
//        loginPage.enterPassword(VALID_PASSWORD);
//        loginPage.clickLogin();
//
//        //verify that user is logged-in successfully
//        Assert.assertTrue(loginPage.isMyAccountPage());
//
//        //after test-logout
//        loginPage.fortunaLogout();
//    }
//
//    @Test (priority = 3)
//    public void rememberMeVerification(){
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

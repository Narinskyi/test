import architecture.PageFactory;
import enums.AvailablePages;
import org.junit.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import utils.AbstractTest;

public class LoginTest extends AbstractTest {

    private static final String INVALID_USERNAME = "invalid";
    private static final String INVALID_PASSWORD = "jdss&@^n#342";
    private static final String VALID_PASSWORD = "Password1";

    private String username;
    private LoginPage loginPage;

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

        loginPage=PageFactory.getPage(AvailablePages.login);
        loginPage.open();

        loginPage.clickLogin();

        //verify header, placeholders and that remember me is unchecked by default
        Assert.assertTrue(loginPage.getUsernamePlaceholder().contains("username/email"));
        Assert.assertTrue(loginPage.getPasswordPlaceholder().contains("password"));
        Assert.assertTrue(loginPage.isUsernameInvalid());
        Assert.assertTrue(loginPage.isPasswordInvalid());
        Assert.assertTrue(!loginPage.isRememberMeChecked());
    }

    @Test (groups = {"tablet", "mobile"})
    public void uiElementsVerificationTM(){

        loginPage=PageFactory.getPage(AvailablePages.login);
        loginPage.open();

        loginPage.clickLogin();

        //verify header, placeholders and that remember me is unchecked by default
        Assert.assertTrue(loginPage.isUsernameInvalid());
        Assert.assertTrue(loginPage.isPasswordInvalid());
        Assert.assertTrue(!loginPage.isRememberMeChecked());
    }

    @Test (priority = 1)
    public void passwordVisibilityIconVerification(){

        loginPage=PageFactory.getPage(AvailablePages.login);
        loginPage.open();

        //verify that password is invisible by default
        loginPage.enterPassword(INVALID_PASSWORD);
        Assert.assertFalse(loginPage.isPasswordVisible());

        //verify that password visibility function works
        loginPage.clickEyeIcon();
        Assert.assertTrue(loginPage.isPasswordVisible());

    }
//
//    @Test (priority = 1)
//    public void forgottenPasswordLinkVerification(){
//
//        loginPage = (LoginPage)NavigationUtils.navigateToPage(PlayerCondition.guest, ConfiguredPages.login);
//
//        //verify forgotten password link
//        loginPage.clickForgottenPassword();
//        Assert.assertTrue(WebDriverUtils.getCurrentUrl().contains("forgotten-password"));
//
//    }
//
//    @Test (priority = 1)
//    public void registrationLinkVerification(){
//
//        loginPage = (LoginPage)NavigationUtils.navigateToPage(PlayerCondition.guest, ConfiguredPages.login);
//
//        //verify registration link
//        loginPage.clickRegister();
//        Assert.assertTrue(NavigationUtils.isNewTabOpened("register-step-1"));
//
//    }
//
//
//    @Test (priority = 1)
//    public void emptyFieldsVerification() {
//
//        loginPage = (LoginPage) NavigationUtils.navigateToPage(PlayerCondition.guest, ConfiguredPages.login);
//
//        //empty fields
//        loginPage.clickLogin();
//        Assert.assertTrue(loginPage.fieldsAreInvalid());
//        System.out.println("=================End of empty fields verification=================");
//
//        //empty password
//        loginPage.enterUsername(INVALID_USERNAME);
//        loginPage.clickRememberMe();
//        loginPage.clickLogin();
//        Assert.assertTrue(loginPage.isPasswordInvalid());
//        System.out.println("=================End of empty password verification=================");
//
//        //empty username
//        loginPage.clearUsername();
//        loginPage.enterPassword(INVALID_PASSWORD);
//        loginPage.clickLogin();
//        Assert.assertTrue(loginPage.isUsernameInvalid());
//        System.out.println("=================End of empty username verification=================");
//
//    }
//
//    @Test (priority = 1)
//    public void invalidBothFieldsVerification(){
//
//        loginPage = (LoginPage)NavigationUtils.navigateToPage(PlayerCondition.guest, ConfiguredPages.login);
//
//        loginPage.enterUsername("invalid");
//        loginPage.enterPassword(INVALID_PASSWORD);
//        loginPage.clickLogin();
//
//        Assert.assertTrue(loginPage.errorMessagesAreDisplayed(1));
//    }
//
//    @Test (priority = 1)
//    public void invalidUsernameVerification() {
//
//        loginPage = (LoginPage)NavigationUtils.navigateToPage(PlayerCondition.guest, ConfiguredPages.login);
//
//        loginPage.enterUsername(INVALID_USERNAME);
//        loginPage.enterPassword(VALID_PASSWORD);
//        loginPage.clickLogin();
//
//        Assert.assertTrue(loginPage.errorMessagesAreDisplayed(1));
//    }
//
//    @Test (priority = 1)
//    public void invalidPasswordVerification() {
//
//        loginPage = (LoginPage)NavigationUtils.navigateToPage(PlayerCondition.guest, ConfiguredPages.login);
//
//        loginPage.enterUsername(username);
//        loginPage.enterPassword(INVALID_PASSWORD);
//        loginPage.clickLogin();
//
//        Assert.assertTrue(loginPage.errorMessagesAreDisplayed(1));
//    }
//
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

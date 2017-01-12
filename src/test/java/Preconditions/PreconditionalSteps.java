package Preconditions;

import core.AbstractTest;
import pageObjects.FortunaextdevPage;
import pageObjects.LoginPage;
import pageObjects.RegistrationPage;
import springConstructors.UserData;
import utils.Backend;
import utils.DataProvider;

public class PreconditionalSteps extends AbstractTest {

    private static FortunaextdevPage fortunaextdevPage = new FortunaextdevPage();
    private static RegistrationPage registrationPage = new RegistrationPage();
    private static LoginPage loginPage = new LoginPage();
    private static UserData userData = DataProvider.getUserData();

    public void loginWithExisitingUser() {
        loginPage.login();
    }

    public static void prepareUser() {
        generateUniqueUserdata();
        registerThroughBackend();
        loginToFortunaExtdev();
    }

    public static void prepareUserAndLogin() {
        prepareUser();
        loginPage.login();
    }

    private static void generateUniqueUserdata(){
        String random = DataProvider.getRandomUsername();
        userData.setUsername(random);
        userData.setEmail(random+"@gmail.com");
    }

    private static void registerThroughUI() {
        registrationPage.open();
        registrationPage.registerUser();
        System.out.println("User with: "+userData.getUsername()+" username was registered successfully");
    }

    private static void registerThroughBackend(){
        Backend.createUser();
    }

    private static void loginToFortunaExtdev(){
        fortunaextdevPage.openFortunaextdev();
        fortunaextdevPage.loginWithDefaultCredentials();
    }

    private static void acceptTC(){
        loginPage.clickAcceptTC();
    }

    private static void logout(){
        registrationPage.logout();
    }

    public void makeBets() {
        fortunaextdevPage.makeBets(80);
    }
}

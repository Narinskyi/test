package Preconditions;

import core.AbstractTest;
import pageObjects.FortunaextdevPage;
import pageObjects.RegistrationPage;
import springConstructors.UserData;
import utils.DataProvider;
import java.util.UUID;

public class PreconditionalSteps extends AbstractTest {

    private static FortunaextdevPage fortunaextdevPage = new FortunaextdevPage();
    private static RegistrationPage registrationPage = new RegistrationPage();
    private static UserData userData = DataProvider.getUserData();

    public static void createUser() {
        generateUniqueUserdata();
        registerThroughUI();
        loginToFortunaExtdev();
        logout();
    }

    private static void generateUniqueUserdata(){
        String uniqeString = UUID.randomUUID().toString().substring(0,7);
        log.info("Registering user: "+ uniqeString);
        userData.setUsername(uniqeString);
        userData.setEmail(uniqeString+"@gmail.com");
    }

    private static void registerThroughUI() {
        registrationPage.open();
        registrationPage.registerUser();
    }

    private static void loginToFortunaExtdev(){
        fortunaextdevPage.openFortunaextdev();
        fortunaextdevPage.loginWithDefaultCredentials();
    }

    private static void logout(){
        registrationPage.logout();
    }

    public void makeBets() {
        fortunaextdevPage.makeBets(80);
    }
}

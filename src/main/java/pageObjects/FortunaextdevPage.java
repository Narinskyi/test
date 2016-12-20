package pageObjects;

import org.openqa.selenium.By;
import utils.DataProvider;
import utils.Driver;
import java.util.concurrent.ThreadLocalRandom;

//temporary solution
public class FortunaextdevPage extends AbstractPage {

    private static final By INPUT_USERNAME_XP = By.xpath("//input[@name='username']");
    private static final By INPUT_PASSWORD_XP = By.xpath("//input[@name='password']");
    private static final By BUTTON_SIGN_IN_XP = By.xpath("//input[@type='submit']");
    private static final By ICON_SIGNED_IN_XP = By.id("welcome_id");
    private static final By BUTTON_ADD_SLIP_XP = By.xpath("(//button[@name='add-to-slip'][not(contains(@disabled,'disabled'))])[5]");
    private static final By INPUT_STAKE_XP = By.xpath("//input[@class='stake']");
    private static final By BUTTON_PLACE_SLIP_XP = By.name("place_slip");
    private static final By BUTTON_CONFIRM_SLIP_XP = By.name("confirm_slip_placed");


    public void openFortunaextdev() {
        Driver.openPage("http://fortunacz-fortunaextdev.custenv.geneity.co.uk/");
        Driver.waitForElementVisibility(INPUT_USERNAME_XP);
    }

    private void loginWithCredentials(String username, String password) {

        Driver.inputTextToField(INPUT_USERNAME_XP, username);
        Driver.inputTextToField(INPUT_PASSWORD_XP, password);
        Driver.click(BUTTON_SIGN_IN_XP);
        Driver.waitForElementVisibility(ICON_SIGNED_IN_XP);
    }

    public void loginWithDefaultCredentials(){
        loginWithCredentials(DataProvider.getUserData().getUsername(),
                DataProvider.getUserData().getPassword());
    }


    public void makeBets(int howMany){

        for (int i=0; i<howMany; i++) {

            Driver.click(BUTTON_ADD_SLIP_XP);
            Driver.waitForElementVisibility(INPUT_STAKE_XP);
            Driver.inputTextToField(INPUT_STAKE_XP,
                    Integer.toString(ThreadLocalRandom.current().nextInt(70, 100 + 1)));
            Driver.waitForElementVisibility(BUTTON_PLACE_SLIP_XP);
            Driver.click(BUTTON_PLACE_SLIP_XP);
            Driver.waitForElementVisibility(BUTTON_CONFIRM_SLIP_XP);

        }

    }
}

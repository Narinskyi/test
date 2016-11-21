package pageObjects;

import org.openqa.selenium.By;
import utils.DataProvider;
import utils.WebDriverUtils;
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
        WebDriverUtils.openPage("http://fortunacz-fortunaextdev.custenv.geneity.co.uk/");
        WebDriverUtils.waitForElementVisibility(INPUT_USERNAME_XP);
    }

    private void loginWithCredentials(String username, String password) {

        WebDriverUtils.inputTextToField(INPUT_USERNAME_XP, username);
        WebDriverUtils.inputTextToField(INPUT_PASSWORD_XP, password);
        WebDriverUtils.click(BUTTON_SIGN_IN_XP);
        WebDriverUtils.waitForElementVisibility(ICON_SIGNED_IN_XP);
    }

    public void loginWithDefaultCredentials(){
        loginWithCredentials(DataProvider.getUserData().getUsername(),
                DataProvider.getUserData().getPassword());
    }


    public void makeBets(int howMany){

        for (int i=0; i<howMany; i++) {

            WebDriverUtils.click(BUTTON_ADD_SLIP_XP);
            WebDriverUtils.waitForElementVisibility(INPUT_STAKE_XP);
            WebDriverUtils.inputTextToField(INPUT_STAKE_XP,
                    Integer.toString(ThreadLocalRandom.current().nextInt(70, 100 + 1)));
            WebDriverUtils.waitForElementVisibility(BUTTON_PLACE_SLIP_XP);
            WebDriverUtils.click(BUTTON_PLACE_SLIP_XP);
            WebDriverUtils.waitForElementVisibility(BUTTON_CONFIRM_SLIP_XP);

        }

    }
}

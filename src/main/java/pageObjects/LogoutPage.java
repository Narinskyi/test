package pageObjects;

import org.openqa.selenium.By;
import utils.Driver;

public class LogoutPage extends AbstractFortunaPage {

    private static final By BUTTON_LOGOUT = By.cssSelector("button.fn-logout");
    private static final By BUTTON_ARE_YOU_SURE = By.cssSelector(".fn-accept");

    public void logout(){
        Driver.click(BUTTON_LOGOUT);
        Driver.click(BUTTON_ARE_YOU_SURE);
    }
}

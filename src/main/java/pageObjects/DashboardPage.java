package pageObjects;

import org.openqa.selenium.By;
import utils.Driver;

public class DashboardPage extends AbstractFortunaPage {

    private static final By ACCOUNT_INFO = By.cssSelector("div.myaccount_dashboard");

    public boolean isAccountInfoDisplayed(){
        return Driver.isElementVisible(ACCOUNT_INFO);
    }
}

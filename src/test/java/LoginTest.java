import architecture.PageFactory;
import enums.AvailablePages;
import org.junit.Test;
import pageObjects.LoginPage;
import utils.AbstractTest;
import utils.WebDriverUtils;

public class LoginTest extends AbstractTest {

    @Test
    public void loginTest(){
        LoginPage page = PageFactory.getPage(AvailablePages.login);
        page.open();
        WebDriverUtils.waitFor(5000);
        WebDriverUtils.click("//button[contains(@class, 'fn-login-btn')]");
    }
}

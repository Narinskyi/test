package tests;

import com.onarinskyi.core.AbstractTest;
import com.onarinskyi.core.PageFactory;
import com.onarinskyi.enums.AvailablePages;
import com.onarinskyi.enums.Platform;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.onarinskyi.pageObjects.LoginPage;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import com.onarinskyi.utils.DataProvider;

@Features("Guest")
@Stories("Login")
public class LoginTest extends AbstractTest {

    private static LoginPage loginPage = PageFactory.getPage(AvailablePages.login);

    private static final String INVALID_USERNAME = "invalid";
    private static final String INVALID_PASSWORD = "invalidPassword";

    @BeforeClass (alwaysRun = true)
    public void prepareUser(){

    }

    @Test (groups = {"desktop", "tablet", "mobile"})
    public void uiElementsTest(){

        loginPage.open();
        loginPage.clickLogin();

        //verify header, placeholders and that remember me is unchecked by default
        if (DataProvider.getCurrentPlatform().equals(Platform.desktop)) {
            Assert.assertTrue(loginPage.getUsernamePlaceholder().contains("username/email"), "Username placeholder failed");
            Assert.assertTrue(loginPage.getPasswordPlaceholder().contains("password"), "Password placeholder failed");
        }

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
}

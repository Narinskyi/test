package tests;

import com.onarinskyi.annotations.PageObject;
import gui.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import tests.base.SearchTest;

@Features("Amazon")
@Stories("Home page test")
public class HomePageTest extends SearchTest {

    @PageObject
    private HomePage homePage;

    @Test
    public void verifyThatPromoAreaIsDisplayed() {
        homePage.open();
        Assert.assertTrue(homePage.isPromoAreaVisible());
    }
}

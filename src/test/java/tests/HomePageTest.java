package tests;

import com.onarinskyi.annotations.PageObject;
import com.onarinskyi.core.AbstractJUnitTest;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import test_data.gui.pages.HomePage;

@Features("Amazon")
@Stories("Home page test")
public class HomePageTest extends AbstractJUnitTest {

    @PageObject
    private HomePage homePage;

    @Test
    public void verifyThatPromoAreaIsDisplayed() {
        homePage.open();
        homePage.findDummy();
//        homePage.findProduct("some");
//        driver.findElement(By.cssSelector(".dummy"));
    }
}

package tests;

import com.onarinskyi.annotations.PageObject;
import com.onarinskyi.driver.WebDriverDecorator;
import gui.pages.HomePage;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import tests.base.SearchTest;

@Features("Amazon")
@Stories("Home page test")
public class HomePageTest extends SearchTest {

    @PageObject
    private HomePage homePage = new HomePage();

    @Autowired
    WebDriverDecorator driver;

    @Test
    public void verifyThatPromoAreaIsDisplayed() {
        driver.openPage(homePage);
        driver.findElement(By.cssSelector(".dummy"));
    }
}

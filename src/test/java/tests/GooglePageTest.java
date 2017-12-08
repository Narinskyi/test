package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import tests.base.BaseTest;

@Features("Google")
@Stories("Demo test")
public class GooglePageTest extends BaseTest {

    @Test
    public void demoFlights() {
        googlePage.open();
        Assert.assertTrue(googlePage.isButtonVisible());
    }
}

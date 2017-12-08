package smoke;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import smoke.base.BaseTest;

@Features("Google")
@Stories("Demo test")
public class GooglePageTest extends BaseTest {

    @Test
    public void demoFlights() {
        googlePage.open();
        Assert.assertTrue(googlePage.isButtonVisible());
    }
}

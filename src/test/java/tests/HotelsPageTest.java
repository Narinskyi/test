package tests;

import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import tests.base.BaseTest;

@Features("Hotels")
@Stories("Demo test")
public class HotelsPageTest extends BaseTest {

    @Test
    public void demoHotels() {
        hotelsPage.open();
        softly.assertFalse(true);
        softly.assertAll();
    }
}

package smoke;

import com.onarinskyi.annotations.PageObject;
import com.onarinskyi.core.AbstractTest;
import pages.HotelsPage;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

@Features("Hotels")
@Stories("Demo test")
public class HotelsPageTest extends AbstractTest {

    @PageObject
    private HotelsPage hotelsPage;

    @Test
    public void demoHotels() {
        hotelsPage.open();
        softly.assertFalse(true);
        softly.assertAll();
    }
}

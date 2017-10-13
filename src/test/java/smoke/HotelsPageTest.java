package smoke;

import com.onarinskyi.annotations.PageObject;
import com.onarinskyi.core.AbstractTest;
import com.onarinskyi.pages.HotelsPage;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;

@Features("Hotels")
public class HotelsPageTest extends AbstractTest {

    @PageObject
    private HotelsPage hotelsPage;

    @Test
    public void demoHotels() {
        hotelsPage.open();
    }
}

package smoke;

import com.onarinskyi.annotations.PageObject;
import com.onarinskyi.core.AbstractTest;
import pages.FlightsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

@Features("Flights")
@Stories("Demo test")
public class FlightsPageTest extends AbstractTest {

    @PageObject
    private FlightsPage flightsPage;

    @Test
    public void demoFlights() {
        flightsPage.open();
        Assert.assertTrue(flightsPage.isFlightsLabelDisplayed());
    }
}

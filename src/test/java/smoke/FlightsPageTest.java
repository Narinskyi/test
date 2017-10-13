package smoke;

import com.onarinskyi.annotations.PageObject;
import com.onarinskyi.core.AbstractTest;
import com.onarinskyi.pages.FlightsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;

@Features("Flights")
public class FlightsPageTest extends AbstractTest {

    @PageObject
    private FlightsPage flightsPage;

    @Test
    public void demoFlights() {
        flightsPage.open();
        Assert.assertTrue(flightsPage.isFlightsLabelDisplayed());
    }
}

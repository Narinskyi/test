package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import tests.base.BaseTest;

@Features("Flights")
@Stories("Demo test")
public class FlightsPageTest extends BaseTest {

    @Test
    public void demoFlights() {
        flightsPage.open();
        Assert.assertTrue(flightsPage.isFlightsLabelDisplayed());
    }

    public static void main(String[] args) {
        System.out.println("asd".toString());
    }
}

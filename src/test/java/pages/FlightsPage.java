package pages;

import com.onarinskyi.annotations.Url;
import com.onarinskyi.core.AbstractPage;
import org.openqa.selenium.By;

@Url("/flight")
public class FlightsPage extends AbstractPage {

    private static final By LABEL_FLIGHTS = By.cssSelector(".flight-price-grids");

    public boolean isFlightsLabelDisplayed() {
        return driver.isElementVisible(LABEL_FLIGHTS);
    }
}

package pages;

import com.onarinskyi.annotations.FindBy;
import com.onarinskyi.annotations.Url;
import org.openqa.selenium.By;
import pages.base.BasePage;

@Url("/flight")
public class FlightsPage extends BasePage {

    @FindBy(css = ".flight-price-grids")
    private By labelFlights;

    public boolean isFlightsLabelDisplayed() {
        return driver.isElementVisible(labelFlights);
    }
}

package pages;

import com.onarinskyi.framework.annotations.FindBy;
import com.onarinskyi.framework.annotations.Url;
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

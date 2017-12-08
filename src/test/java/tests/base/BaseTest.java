package tests.base;

import com.onarinskyi.annotations.PageObject;
import com.onarinskyi.core.AbstractTest;
import pages.FlightsPage;
import pages.GooglePage;
import pages.HotelsPage;

public class BaseTest extends AbstractTest {
    @PageObject
    protected FlightsPage flightsPage;
    @PageObject
    protected HotelsPage hotelsPage;
    @PageObject
    protected GooglePage googlePage;
}

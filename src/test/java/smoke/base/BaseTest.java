package smoke.base;

import com.onarinskyi.framework.annotations.PageObject;
import com.onarinskyi.framework.core.AbstractTest;
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

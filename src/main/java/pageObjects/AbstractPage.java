package pageObjects;

import enums.AvailablePages;
import utils.DataProvider;
import utils.WebDriverUtils;

public abstract class AbstractPage {

    private String baseURL = DataProvider.getBaseUrl();

    public void open() {
        String url = baseURL + AvailablePages.getSuffix(this);
        WebDriverUtils.openPage(url);
    }

    public void refresh() {
        WebDriverUtils.refreshPage();
    }

    public void waitFor(long millisec) {
        WebDriverUtils.waitFor(millisec);
    }

}

package pageObjects;

import architecture.Page;
import enums.AvailablePages;
import utils.DataProvider;
import utils.WebDriverUtils;

import java.util.logging.Logger;

public abstract class AbstractPage implements Page {

    protected static Logger log = Logger.getAnonymousLogger();
    private String baseURL = DataProvider.getBaseUrl();

    public void open() {
        String url = baseURL + AvailablePages.getSuffix(this);

        log.info("Navigating to "+ url);
        WebDriverUtils.openPage(url);
    }

    public void refresh() {

    }

}

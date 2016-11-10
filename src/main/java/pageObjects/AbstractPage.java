package pageObjects;

import architecture.Page;
import com.sun.istack.internal.NotNull;
import enums.AvailablePages;
import utils.WebDriverUtils;

import java.util.logging.Logger;

public abstract class AbstractPage implements Page {

    protected static Logger log = Logger.getAnonymousLogger();
    private String baseURL = "http://wpl-licensee25-public.ptdev.eu/";

    public AbstractPage () {
       // PageFactory.initElements(driver, this);
    }

    public void open() {
        String url = baseURL+ AvailablePages.getSuffix(this);

        log.info("Navigating to "+ url);
        WebDriverUtils.openPage(url);
    }

    public void refresh() {

    }

}

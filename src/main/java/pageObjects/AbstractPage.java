package pageObjects;

import enums.AvailablePages;
import enums.Platform;
import utils.DataProvider;
import utils.Driver;

public abstract class AbstractPage {

    private String baseURL = DataProvider.getBaseUrl();

    public void open() {
        String url = baseURL + AvailablePages.getSuffix(this);
        Driver.openPage(url);
    }

//    public void refresh() {
//        Driver.refreshPage();
//    }

//    public void waitFor(long millisec) {
//        Driver.waitFor(millisec);
//    }

    boolean isPlatform(Platform platform) {
        return DataProvider.getCurrentPlatform().equals(platform);
    }

}

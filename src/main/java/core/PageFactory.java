package core;

import enums.AvailablePages;
import pageObjects.AbstractPage;

public class PageFactory {

    @SuppressWarnings("unchecked")
    public static <T extends AbstractPage> T getPage(AvailablePages page) {
            return (T) page.getPage();
    }

}

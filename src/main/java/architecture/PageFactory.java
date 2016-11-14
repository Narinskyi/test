package architecture;

import enums.AvailablePages;

public class PageFactory {

    @SuppressWarnings("unchecked")
    public static <T extends Page> T getPage(AvailablePages page) {
            return (T) page.getPage();
    }

}

package com.onarinskyi.core;

import com.onarinskyi.enums.AvailablePages;
import com.onarinskyi.pageObjects.AbstractPage;

public class PageFactory {

    @SuppressWarnings("unchecked")
    public static <T extends AbstractPage> T getPage(AvailablePages page) {
            return (T) page.getPage();
    }
}

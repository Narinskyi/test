package com.onarinskyi.pageObjects;

import com.onarinskyi.reflection.ReflectionUtils;
import com.onarinskyi.utils.DataProvider;
import com.onarinskyi.utils.Driver;

public abstract class AbstractPage {

    private String baseURL = DataProvider.getBaseUrl();

    public void open() {
        String url = ReflectionUtils.getUrlAnnotationValue(this.getClass());
//        String url = baseURL + AvailablePages.getSuffix(this);
        Driver.openPage(url);
    }
}

package com.onarinskyi.core;

import com.onarinskyi.interfaces.Page;
import com.onarinskyi.utils.Driver;

public abstract class AbstractPage implements Page {

    protected Driver driver = Driver.get();

    @Override
    public void open() {
        driver.openPage(this);
    }
}

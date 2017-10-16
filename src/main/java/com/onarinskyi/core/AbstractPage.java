package com.onarinskyi.core;

import com.onarinskyi.driver.Driver;
import com.onarinskyi.interfaces.Page;

public abstract class AbstractPage implements Page {

    protected Driver driver = new Driver();

    @Override
    public void open() {
        driver.openPage(this);
    }
}

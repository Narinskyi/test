package com.onarinskyi.core;

import com.onarinskyi.interfaces.Page;
import com.onarinskyi.utils.Driver;

public abstract class AbstractPage implements Page {

    @Override
    public void open() {
        Driver.openPage(this);
    }
}

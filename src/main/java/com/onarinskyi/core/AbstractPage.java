package com.onarinskyi.core;

import com.onarinskyi.driver.Driver;
import com.onarinskyi.interfaces.Page;
import com.onarinskyi.reflection.Reflection;

public abstract class AbstractPage implements Page {

    protected Driver driver = new Driver();

    {
        Reflection.instantiateFieldsAnnotatedWithBy(this);
    }

    @Override
    public void open() {
        driver.openPage(this);
    }
}

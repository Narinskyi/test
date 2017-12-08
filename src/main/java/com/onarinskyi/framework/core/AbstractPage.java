package com.onarinskyi.framework.core;

import com.onarinskyi.framework.driver.Driver;
import com.onarinskyi.framework.interfaces.Page;
import com.onarinskyi.framework.reflection.Reflection;

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

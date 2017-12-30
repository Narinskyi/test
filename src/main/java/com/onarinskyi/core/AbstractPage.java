package com.onarinskyi.core;

import com.onarinskyi.annotations.PageComponent;
import com.onarinskyi.driver.Driver;
import com.onarinskyi.interfaces.Page;
import com.onarinskyi.reflection.Reflection;

public abstract class AbstractPage implements Page {

    protected Driver driver = new Driver();

    {
        Reflection.instantiateFieldsAnnotatedWithBy(this);
        Reflection.instantiateAnnotatedField(this, PageComponent.class);
    }

    @Override
    public void open() {
        driver.openPage(this);
    }
}

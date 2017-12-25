package com.onarinskyi.core;

import com.onarinskyi.driver.Driver;
import com.onarinskyi.reflection.Reflection;

public abstract class AbstractComponent {
    protected Driver driver = new Driver();

    {
        Reflection.instantiateFieldsAnnotatedWithBy(this);
    }
}

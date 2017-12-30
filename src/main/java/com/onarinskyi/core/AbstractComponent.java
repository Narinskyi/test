package com.onarinskyi.core;

import com.onarinskyi.driver.Driver;
import com.onarinskyi.reflection.Reflection;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractComponent {

    @Autowired
    protected Driver driver = new Driver();

    {
        Reflection.instantiateFieldsAnnotatedWithBy(this);
    }
}

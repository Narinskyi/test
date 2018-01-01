package com.onarinskyi.core;

import com.onarinskyi.config.DriverConfig;
import com.onarinskyi.driver.WebDriverDecorator;

public abstract class AbstractTestEntity {

    protected WebDriverDecorator driver = DriverConfig.getDriver();

    {
        Reflection.instantiateAnnotatedFields(this);
    }
}

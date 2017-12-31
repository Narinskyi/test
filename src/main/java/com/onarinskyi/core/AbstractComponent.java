package com.onarinskyi.core;

import com.onarinskyi.driver.WebDriverDecorator;
import com.onarinskyi.reflection.Reflection;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractComponent {

    @Autowired
    protected WebDriverDecorator driver;

    {
        Reflection.instantiateFieldsAnnotatedWithBy(this);
    }
}

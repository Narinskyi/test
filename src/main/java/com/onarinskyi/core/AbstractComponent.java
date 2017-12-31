package com.onarinskyi.core;

import com.onarinskyi.driver.WebDriverFacade;
import com.onarinskyi.reflection.Reflection;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractComponent {

   protected final WebDriverFacade driver;

    {
        Reflection.instantiateFieldsAnnotatedWithBy(this);
    }

    @Autowired
    public AbstractComponent(WebDriverFacade driver) {
        this.driver = driver;
    }
}

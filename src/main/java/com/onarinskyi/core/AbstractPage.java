package com.onarinskyi.core;

import com.onarinskyi.annotations.PageComponent;
import com.onarinskyi.driver.WebDriverFacade;
import com.onarinskyi.interfaces.Page;
import com.onarinskyi.reflection.Reflection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractPage implements Page {

    private WebDriverFacade driver;

    @Autowired
    public AbstractPage(WebDriverFacade driver) {
        this.driver = driver;
    }

    public AbstractPage() {
    }

    {
        Reflection.instantiateFieldsAnnotatedWithBy(this);
        Reflection.instantiateAnnotatedField(this, PageComponent.class);
    }

    @Override
    public void open() {
        driver.openPage(this);
    }
}

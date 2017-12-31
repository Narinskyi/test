package com.onarinskyi.core;

import com.onarinskyi.annotations.PageComponent;
import com.onarinskyi.driver.WebDriverDecorator;
import com.onarinskyi.reflection.Reflection;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AbstractPage implements Page {

    @Autowired
    protected WebDriverDecorator driver;

    {
        Reflection.instantiateFieldsAnnotatedWithBy(this);
        Reflection.instantiateAnnotatedField(this, PageComponent.class);
    }

    @Override
    public void open() {
        driver.openPage(this);
    }
}

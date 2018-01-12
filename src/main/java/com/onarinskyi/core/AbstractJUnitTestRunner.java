package com.onarinskyi.core;

import com.onarinskyi.config.AppConfig;
import com.onarinskyi.driver.DriverManager;
import com.onarinskyi.driver.WebDriverDecorator;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.PostConstruct;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public abstract class AbstractJUnitTestRunner {

    protected WebDriverDecorator driver;

    @Rule
    @Autowired
    public TestWatcher onFailureWatcher;

    @PostConstruct
    public void init() {
        Reflection.instantiateAnnotatedFields(this);
        driver = DriverManager.getDriver();
    }
}


package com.onarinskyi.listeners;

import com.onarinskyi.config.DriverConfig;
import com.onarinskyi.driver.WebDriverDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;

public class TestNGOnTestFailureListener extends TestListenerAdapter {

    private WebDriverDecorator driver;

    @PostConstruct
    public void injectDriver() {
        driver = DriverConfig.getDriver();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        driver.takeScreenshot();
    }
}
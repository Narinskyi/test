package com.onarinskyi.listeners;

import com.onarinskyi.annotations.PageObject;
import com.onarinskyi.reflection.ReflectionUtils;
import org.testng.*;

public class ConfigurationListener implements IConfigurationListener2 {

    @Override
    public void beforeConfiguration(ITestResult iTestResult) {
        ReflectionUtils.instantiateAnnotatedFields("tests", PageObject.class);
    }

    @Override
    public void onConfigurationSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onConfigurationFailure(ITestResult iTestResult) {

    }

    @Override
    public void onConfigurationSkip(ITestResult iTestResult) {

    }
}

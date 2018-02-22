package com.onarinskyi.listeners;

import com.onarinskyi.driver.DriverManager;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.springframework.stereotype.Component;

@Component
public class JUnitExecutionListener extends TestWatcher {

    @Override
    protected void failed(Throwable e, Description description) {
        DriverManager.getDriver().takeScreenshot();
    }

    @Override
    protected void finished(Description description) {
        DriverManager.getDriver().quit();
    }
}

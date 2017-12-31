package com.onarinskyi.listeners;

import com.onarinskyi.driver.WebDriverDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import ru.yandex.qatools.allure.annotations.Attachment;

@Component
public class OnTestFailureListener extends TestListenerAdapter {

    private final WebDriverDecorator driver;

    @Autowired
    public OnTestFailureListener(WebDriverDecorator driver) {
        this.driver = driver;
    }

    @Override
    public void onTestFailure(ITestResult result) {
        makeScreenshot();
    }

    @Attachment(value = "PageObject screenshot", type = "image/png")
    private byte[] makeScreenshot() {
        return driver.makeScreenshot();
    }
}
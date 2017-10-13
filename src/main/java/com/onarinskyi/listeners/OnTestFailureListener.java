package com.onarinskyi.listeners;

import com.onarinskyi.utils.Driver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import ru.yandex.qatools.allure.annotations.Attachment;

public class OnTestFailureListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult result) {
        makeScreenshot();
    }

    @Attachment(value = "PageObject screenshot", type = "image/png")
    private byte[] makeScreenshot() {
        return new Driver().makeScreenshot();
    }
}

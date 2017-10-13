package com.onarinskyi.core;

import com.onarinskyi.interfaces.Page;
import com.onarinskyi.utils.Driver;
import org.openqa.selenium.WebDriver;

public abstract class AbstractPage implements Page {

    protected Driver driver = new Driver();

    @Override
    public void open() {
        driver.openPage(this);
    }
}

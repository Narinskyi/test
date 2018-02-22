package com.onarinskyi.core;

import org.openqa.selenium.By;

public abstract class AbstractPageComponent extends AbstractTestEntity {
    protected By locator;

    public By getLocator() {
        return locator;
    }
}

package com.onarinskyi.gui.components;

import com.onarinskyi.core.AbstractPageComponent;
import org.openqa.selenium.By;

public class Input extends AbstractPageComponent {
    public Input(By locator) {
        this.locator = locator;
    }

    public void type(String text) {
        driver.type(locator, text);
    }

    public String getText() {
        return driver.getElementText(locator);
    }
}

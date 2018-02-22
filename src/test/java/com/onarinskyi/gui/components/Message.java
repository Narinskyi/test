package com.onarinskyi.gui.components;

import com.onarinskyi.core.AbstractPageComponent;
import org.openqa.selenium.By;

import java.util.List;

public abstract class Message extends AbstractPageComponent {

    public List<String> getText() {
        return driver.getTextOfElements(locator);
    }

    public static class Success extends Message {
        {
            this.locator = By.cssSelector("");
        }
    }

    public static class Error extends Message {
        {
            this.locator = By.cssSelector("");
        }
    }
}

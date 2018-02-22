package com.onarinskyi.gui.components;

import com.onarinskyi.core.AbstractPageComponent;
import org.openqa.selenium.By;

public abstract class Button extends AbstractPageComponent {

    public void click() {
        driver.clickOn(locator);
    }

    public void clickAndAcceptAlert() {
        driver.clickOn(locator);
        driver.acceptAlert();
    }

    public boolean isEnabled() {
        return driver.isElementEnabled(locator);
    }

    public static class SignUp extends Button {
        {
            this.locator = By.cssSelector("");
        }
    }

    public static class LogIn extends Button {
        {
            this.locator = By.xpath("");
        }
    }

    public static class AskQuestion extends Button {
        {
            this.locator = By.xpath("//a[contains(text(),'Ask Question')] |" +
                    "//input[contains(text(),'Ask Question')]");
        }
    }
}

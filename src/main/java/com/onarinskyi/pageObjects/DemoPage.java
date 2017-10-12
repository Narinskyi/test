package com.onarinskyi.pageObjects;

import com.onarinskyi.annotations.Url;
import org.openqa.selenium.By;

@Url("http://google.com")
public class DemoPage extends AbstractPage {

    private static final By LOCATOR = By.id("");
    public void sayHello() {
        System.out.println("Hi");
    }
}

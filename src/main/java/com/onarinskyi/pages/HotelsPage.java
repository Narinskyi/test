package com.onarinskyi.pages;

import com.onarinskyi.annotations.Url;
import com.onarinskyi.core.AbstractPage;
import org.openqa.selenium.By;

@Url("hotels")
public class HotelsPage extends AbstractPage {

    private static final By LOCATOR = By.id("");
    public void sayHello() {
        System.out.println("Hi");
    }
}

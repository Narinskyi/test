package com.onarinskyi.test.smoke;

import com.onarinskyi.annotations.PageObject;
import com.onarinskyi.core.AbstractTest;
import com.onarinskyi.pageObjects.HotelsPage;
import org.testng.annotations.Test;

public class DemoTest extends AbstractTest {

    @PageObject
    private HotelsPage hotelsPage;

    @Test
    public void demo1() {
        hotelsPage.open();
        hotelsPage.sayHello();
    }
}

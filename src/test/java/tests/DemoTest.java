package tests;

import com.onarinskyi.annotations.PageObject;
import com.onarinskyi.core.AbstractTest;
import com.onarinskyi.pageObjects.DemoPage;
import org.testng.annotations.Test;

public class DemoTest extends AbstractTest {

    @PageObject
    private DemoPage demoPage;

    @Test
    public void demo1() {
        demoPage.open();
    }

}

package tests;

import com.onarinskyi.annotations.PageObject;
import com.onarinskyi.core.AbstractTestNGTestRunner;
import org.testng.annotations.Test;
import test_data.gui.pages.HomePage;

public class TestNGTest extends AbstractTestNGTestRunner {

    @PageObject
    private HomePage page;

    @Test
    public void testNgTest() {
        page.open();
        page.findDummy();
    }
}

package tests;

import com.onarinskyi.annotations.PageObject;
import com.onarinskyi.core.AbstractJUnitTestRunner;
import org.testng.annotations.Test;
import test_data.gui.pages.HomePage;

public class JUnitTest extends AbstractJUnitTestRunner {

    @PageObject
    private HomePage page;

    @Test
    public void junitTest() {
        page.open();
        page.findDummy();
    }
}

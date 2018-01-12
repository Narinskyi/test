package tests;

import com.onarinskyi.annotations.PageObject;
import com.onarinskyi.core.AbstractTestNGTestRunner;
import org.testng.annotations.Test;
import data.gui.pages.HomePage;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

@Features("Demo")
@Stories("Simple testNg test 2")
public class SecondTestNGTest extends AbstractTestNGTestRunner {

    @PageObject
    private HomePage page;

    @Test
    public void testNgTest() {
        page.open();
        page.findDummy();
    }
}

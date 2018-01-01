package tests;

import com.onarinskyi.annotations.PageObject;
import test_data.gui.pages.HomePage;
import test_data.model.Category;
import org.testng.annotations.Test;
import tests.base.SearchTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertTrue;

public class CategorySearchTest extends SearchTest {

    @PageObject
    private HomePage homePage;

    @Test
    public void verifySearchByText() {
        homePage.open();
        homePage.searchFor("Ferrari");
        assertTrue(getFoundCategories().size()==1);
    }

    @Test
    public void verifySearchByTextAndType() {
        homePage.open();
        homePage.searchFor("Ferrari", Category.VEHICLES);
        assertThat(getFoundCategories().size(), equalTo(1));
    }
}

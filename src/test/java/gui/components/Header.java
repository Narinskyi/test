package gui.components;

import com.onarinskyi.annotations.FindBy;
import com.onarinskyi.core.AbstractComponent;
import model.Category;
import org.openqa.selenium.By;

public class Header extends AbstractComponent {

    @FindBy(id = "twotabsearchtextbox")
    private By inputSearch;

    @FindBy(css = "nav-search-submit-text+input")
    private By buttonSearch;

    @FindBy(id = "searchDropdownBox")
    private By selectSearchType;

    public void searchFor(String request) {
        driver.type(inputSearch, request);
        driver.clickOn(buttonSearch);
    }

    public void searchFor(String request, Category category) {
        driver.type(inputSearch, request);
        driver.selectDropdownOptionByVisibleText(selectSearchType, category.value());
        driver.clickOn(buttonSearch);
    }
}

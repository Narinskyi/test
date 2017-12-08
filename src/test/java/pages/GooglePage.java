package pages;

import com.onarinskyi.annotations.FindBy;
import com.onarinskyi.annotations.Url;
import org.openqa.selenium.By;
import pages.base.BasePage;

@Url("/maps?hl=en")
public class GooglePage extends BasePage {

    @FindBy(xpath = "//*[@id='searchbox_form']")
    private By formSearch;

    public boolean isButtonVisible() {
        return driver.isElementVisible(formSearch);
    }
}

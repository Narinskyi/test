package pages;

import com.onarinskyi.framework.annotations.Url;
import org.openqa.selenium.By;
import pages.base.BasePage;

@Url("hotels")
public class HotelsPage extends BasePage {

    private static final By LOCATOR = By.id("");
    public void sayHello() {
        System.out.println("Hi");
    }
}

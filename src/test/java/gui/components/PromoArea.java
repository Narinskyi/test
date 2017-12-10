package gui.components;

import com.onarinskyi.annotations.FindBy;
import com.onarinskyi.core.AbstractComponent;
import org.openqa.selenium.By;

public class PromoArea extends AbstractComponent {

    @FindBy(id = "gw-desktop-herotator")
    private By promoAreaBlock;

    public boolean isVisible() {
        return driver.isElementVisible(promoAreaBlock);
    }
}

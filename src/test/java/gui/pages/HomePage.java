package gui.pages;

import com.onarinskyi.annotations.Component;
import com.onarinskyi.annotations.Url;
import gui.components.PromoArea;

@Url("/")
public class HomePage extends BasePage {

    @Component
    private PromoArea promoArea;

    public boolean isPromoAreaVisible() {
        return promoArea.isVisible();
    }
}

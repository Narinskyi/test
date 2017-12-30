package gui.pages;

import com.onarinskyi.annotations.PageComponent;
import com.onarinskyi.annotations.Url;
import gui.components.Header;
import gui.components.PromoArea;

@Url("/")
public class HomePage extends BasePage {

    @PageComponent
    private PromoArea promoArea;

    @PageComponent
    private Header header;

    public boolean isPromoAreaVisible() {
        return promoArea.isVisible();
    }

    public void findProduct(String product) {
        header.searchFor(product);
    }
}

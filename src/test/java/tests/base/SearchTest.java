package tests.base;

import com.onarinskyi.annotations.PageComponent;
import com.onarinskyi.core.AbstractTest;
import gui.components.ResultsFilter;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public abstract class SearchTest extends AbstractTest {

    @PageComponent
    private ResultsFilter resultsFilter;

    protected List<String> getFoundCategories() {
        return resultsFilter.getFoundCategoriesHeaders()
                .stream().map(WebElement::getText)
                .collect(Collectors.toList());
    }
}

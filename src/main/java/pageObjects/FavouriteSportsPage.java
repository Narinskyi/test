package pageObjects;


import org.openqa.selenium.By;

public class FavouriteSportsPage extends AbstractFortunaPage {

    private static final By ACCORDION = By.xpath("(//li[contains(@class,'fn-sport accordion__list')])[1]");
    private static final String ACCORDION_EXPANDED = "//li[contains(@class,'fn-sport')]/span[contains(@class,'expanded')]";


}

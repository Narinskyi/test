package com.onarinskyi.smoke;

import com.onarinskyi.BaseTest;
import com.onarinskyi.annotations.PageObject;
import com.onarinskyi.gui.pages.LandingPage;
import com.onarinskyi.gui.pages.LoginPage;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

@Features("Gui")
@Stories("Landing Page")
public class LandingPageTest extends BaseTest {

    @PageObject
    private LandingPage landingPage;

    @PageObject
    private LoginPage loginPage;

    @Test
    public void verifyLoginPageRedirection() {
        landingPage.open();
        landingPage.clickOnAskQuestion();
        loginPage.verifyLoginPageWasOpened();
    }
}

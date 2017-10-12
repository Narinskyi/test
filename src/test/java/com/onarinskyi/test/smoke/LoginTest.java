package com.onarinskyi.test.smoke;

import com.onarinskyi.annotations.PageObject;
import com.onarinskyi.core.AbstractTest;
import com.onarinskyi.pageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

@Features("Guest")
@Stories("Login")
public class LoginTest extends AbstractTest {

    @PageObject
    private static LoginPage loginPage;

    private static final String INVALID_PASSWORD = "invalidPassword";

    @BeforeClass (alwaysRun = true)
    public void prepareUser(){

    }

    @Test (groups = {"desktop", "tablet", "mobile"})
    public void passwordVisibilityIconTest(){
        loginPage.open();

        //verify that password is invisible by default
        loginPage.enterPassword(INVALID_PASSWORD);
        Assert.assertFalse(loginPage.isPasswordVisible(), "Password masking verification failed");
    }
}

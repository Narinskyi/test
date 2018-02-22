package com.onarinskyi.gui.pages;

import com.onarinskyi.annotations.FindBy;
import com.onarinskyi.annotations.PageComponent;
import com.onarinskyi.annotations.Url;
import com.onarinskyi.core.AbstractPage;
import com.onarinskyi.gui.components.Button;
import com.onarinskyi.gui.components.Input;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;

@Url("users/login")
public class LoginPage extends AbstractPage {

    @PageComponent(id = "email")
    private Input inputEmail;

    @PageComponent(id = "password")
    private Input inputPassword;

    @PageComponent
    private Button.LogIn buttonLogin;

    @FindBy(id = "login-form")
    private By loginForm;

    public void verifyLoginPageWasOpened() {
        MatcherAssert.assertThat(driver.isElementVisible(loginForm), CoreMatchers.equalTo(true));
    }
}

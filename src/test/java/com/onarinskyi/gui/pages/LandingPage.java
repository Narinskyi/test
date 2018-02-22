package com.onarinskyi.gui.pages;

import com.onarinskyi.annotations.FindBy;
import com.onarinskyi.annotations.PageComponent;
import com.onarinskyi.annotations.Url;
import com.onarinskyi.core.AbstractPage;
import com.onarinskyi.gui.components.Button;
import com.onarinskyi.gui.components.Input;
import org.openqa.selenium.By;

@Url("/")
public class LandingPage extends AbstractPage {

    @PageComponent
    private Button.AskQuestion buttonAskQuestion;

    @PageComponent(id = "display-name")
    private Input inputDisplayName;

    @PageComponent(css = "#password")
    private Input inputPassword;

    @FindBy(css = "small.-policy")
    private By policyText;

    public void clickOnAskQuestion() {
        buttonAskQuestion.click();
    }
}

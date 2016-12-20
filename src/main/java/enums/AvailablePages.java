package enums;

import pageObjects.AbstractPage;
import pageObjects.ChangePasswordPage;
import pageObjects.ForgottenPasswordPage;
import pageObjects.LoginPage;

public enum AvailablePages {

    changePassword("change-password"),
    forgottenPassword("forgotten-password"),
    login("login");


    private final String suffix;
    private AbstractPage page;

    AvailablePages (String suffix) {
        this.suffix = suffix;
    }

    public AbstractPage getPage(){

        switch (this){

            case changePassword: page = new ChangePasswordPage();
                break;
            case forgottenPassword: page = new ForgottenPasswordPage();
                break;
            case login: page = new LoginPage();
                break;

        }
        return this.page;
    }

    //get suffix - iterate through enum
    public static String getSuffix(AbstractPage soughtPage) {
        for (AvailablePages pages : AvailablePages.values()){
            if (pages.getPage().getClass().equals(soughtPage.getClass()))
                return pages.suffix;
        }
        return "/";
    }

//    public static void main(String[] args) {
//        ChangePasswordPage loginPage = (ChangePasswordPage) changePassword.getPage();
//    }

}

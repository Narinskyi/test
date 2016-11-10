package enums;

import architecture.Page;
import pageObjects.ChangePasswordPage;
import pageObjects.LoginPage;

public enum AvailablePages {

    login("login"),
    changePassword("change-password");

    private final String suffix;
    private Page page;

    AvailablePages (String suffix) {
        this.suffix = suffix;
    }

    public Page getPage(){

        switch (this){
            case login: page = new LoginPage();
                break;
            case changePassword: page = new ChangePasswordPage();
                break;

        }
        return this.page;
    }

    //get suffix - iterate through enum
    public static String getSuffix(Page soughtPage) {
        for (AvailablePages pages : AvailablePages.values()){
            if (pages.getPage().equals(soughtPage))
                return pages.suffix;
        }
        return "/";
    }

//    public static void main(String[] args) {
//        ChangePasswordPage loginPage = (ChangePasswordPage) changePassword.getPage();
//    }

}

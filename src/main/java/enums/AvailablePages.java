package enums;

import pageObjects.*;

public enum AvailablePages {

    changePassword("change-password"),
    dashboard("dashboard"),
    forgottenPassword("forgotten-password"),
    login("login"),
    logout("logout"),
    register("register-step-1");


    private final String suffix;
    private AbstractPage page;

    AvailablePages (String suffix) {
        this.suffix = suffix;
    }

    public AbstractPage getPage(){

        switch (this){

            case changePassword: page = new ChangePasswordPage();
                break;
            case dashboard: page = new DashboardPage();
                break;
            case forgottenPassword: page = new ForgottenPasswordPage();
                break;
            case login: page = new LoginPage();
                break;
            case logout: page = new LogoutPage();
                break;
            case register: page = new RegistrationPage();
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

}

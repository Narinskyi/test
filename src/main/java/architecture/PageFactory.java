package architecture;

import enums.AvailablePages;

public class PageFactory {


    @SuppressWarnings("unchecked")
    public static <T extends Page> T getPage(AvailablePages page) {
        try{
            return (T) page.getPage();
        } catch (Exception e) {
            System.out.println("Page type cast failed");
        }
        return null;
    }


//    public static void main(String[] args) {
//        LoginPage loginPage = PageFactory.getPage(AvailablePages.login);
//    }









}

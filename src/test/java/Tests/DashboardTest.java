package Tests;

import core.AbstractTest;
import core.PageFactory;
import enums.AvailablePages;
import org.testng.annotations.Test;
import pageObjects.DashboardPage;
import utils.Backend;

public class DashboardTest extends AbstractTest {

    private static DashboardPage dashboardPage = PageFactory.getPage(AvailablePages.dashboard);

    @Test
    public void userDataVerification(){

        Backend.createUser();

    }
}

package Preconditions;

import core.AbstractTest;
import org.testng.annotations.Test;
import pageObjects.FortunaextdevPage;

@Test(enabled = false)
public class GeneityRegistrationTest extends AbstractTest {

        private FortunaextdevPage fortunaextdevPage = new FortunaextdevPage();

        @Test
        public void makeBets() {

            fortunaextdevPage.openFortunaextdev();
            fortunaextdevPage.loginWithDefaultCredentials();

            fortunaextdevPage.makeBets(80);

        }
}

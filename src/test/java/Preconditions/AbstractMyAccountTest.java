package Preconditions;

import core.AbstractTest;
import org.testng.annotations.BeforeClass;
import springConstructors.UserData;
import utils.DataProvider;

import java.util.UUID;

public class AbstractMyAccountTest extends AbstractTest {

    protected String username;
    protected String password;

    //reason to create user in test body is to save the username (could have been done through AbstractMyAccount test)
    @BeforeClass
    public void registerUserAnd(){

        UserData userData = DataProvider.getUserData();
        
    }
}

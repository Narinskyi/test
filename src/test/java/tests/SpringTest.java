package tests;

import com.onarinskyi.config.AppConfig;
import com.onarinskyi.jdbc.services.PersonService;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;

@Test
@Transactional
@ContextConfiguration(classes = AppConfig.class)
public class SpringTest {

    @Autowired
    PersonService service;

    @Test
    public void testUser() {
        assertThat(service.getPerson(1).getName(), Matchers.equalTo("Alex"));
    }
}

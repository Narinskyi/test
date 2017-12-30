package com.onarinskyi.config;

import com.onarinskyi.environment.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class UserConfig {

    @Autowired
    private Environment environment;

    @Bean
    public User adminUser() {
        return new User(environment.getProperty("admin.name"),
                environment.getProperty("admin.password"));
    }

    @Bean
    public User user() {
        return new User(environment.getProperty("user.name"),
                environment.getProperty("user.password"));
    }
}

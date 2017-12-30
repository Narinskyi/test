package com.onarinskyi.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import(DbConfig.class)
@ComponentScan(basePackages = "com.onarinskyi")
@EnableTransactionManagement
public class AppConfig {
}

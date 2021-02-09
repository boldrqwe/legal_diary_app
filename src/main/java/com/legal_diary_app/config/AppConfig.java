package com.legal_diary_app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.legal_diary_app.repository")
@EnableTransactionManagement
@ComponentScan("com.legal_diary_app")
public class AppConfig {
}

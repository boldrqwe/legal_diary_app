package com.legal_diary_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LegalDiaryAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(LegalDiaryAppApplication.class, args);
    }

}

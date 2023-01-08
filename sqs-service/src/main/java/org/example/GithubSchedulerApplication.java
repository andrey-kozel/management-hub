package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GithubSchedulerApplication {
    public static void main(String[] args) {
        SpringApplication.run(GithubSchedulerApplication.class, args);
    }
}

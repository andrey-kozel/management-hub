package com.example.github_worker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients
public class GithubWorkerApplication {
    public static void main(String[] args) {
        SpringApplication.run(GithubWorkerApplication.class, args);
    }
}

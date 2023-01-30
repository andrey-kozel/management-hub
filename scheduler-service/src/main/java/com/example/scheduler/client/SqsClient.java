package com.example.scheduler.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "SQS", url = "${services.messages-service.url}/api/v1/queue")
public interface SqsClient {

    @GetMapping(value = "/message")
    ResponseEntity<String> getMessage();
}

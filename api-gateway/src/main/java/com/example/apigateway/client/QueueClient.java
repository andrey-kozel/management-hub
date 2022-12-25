package com.example.apigateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "queue", url = "${services.sqs-service.url}/api/v1/sqs")
public interface QueueClient {

    @PostMapping("/syncRequest")
    public void sendMessage();
}

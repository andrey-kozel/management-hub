package com.example.apigateway.client;

import com.example.apigateway.dto.QueueMessageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "queue", url = "${services.sqs-service.url}/api/v1/sqs")
public interface QueueClient {

    @PostMapping("/syncRequest")
    void sendMessage(QueueMessageDto queueMessageDto);
}

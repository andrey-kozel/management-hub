package com.example.apigateway.client;

import com.example.apigateway.dto.QueueMessageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "queue", url = "${services.messages-service.url}/api/v1/queue")
public interface QueueClient {

    @PostMapping("/repositories/sync")
    void sendMessage(QueueMessageDto queueMessageDto);
}

package com.example.scheduler.client;

import com.example.scheduler.dto.QueueMessageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "SQS", url = "${services.messages-service.url}/api/v1/queue")
public interface SqsClient {

    @GetMapping(value = "/repositories/sync")
    ResponseEntity<String> syncRequest(@RequestBody QueueMessageDto message);
}

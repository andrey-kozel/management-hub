package com.example.github_worker.client;

import com.example.github_worker.dto.SyncMessageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "SQS", url = "${services.queue-service.url}/api/v1/queue")
public interface SqsClient {
    @GetMapping(value = "/message")
    SyncMessageDto getMessage();
}

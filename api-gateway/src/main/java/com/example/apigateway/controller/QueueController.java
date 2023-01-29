package com.example.apigateway.controller;

import com.example.apigateway.dto.QueueMessageDto;
import com.example.apigateway.service.QueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/queue")
@RestController
@RequiredArgsConstructor
public class QueueController {
    QueueService queueService;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(value = "/repositories/sync", produces = MediaType.APPLICATION_JSON_VALUE)
    public void sendRepositoriesSyncRequest(QueueMessageDto queueMessageDto) {
        queueService.sendMessage(queueMessageDto);
    }
}

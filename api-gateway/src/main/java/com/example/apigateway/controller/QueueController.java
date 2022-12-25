package com.example.apigateway.controller;

import com.example.apigateway.service.QueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/v1/sqs")
@RestController
@RequiredArgsConstructor
public class QueueController {
    QueueService queueService;
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(value = "/syncRequest")
    public void sendSyncRequest() {
        queueService.sendMessage();
    }
}

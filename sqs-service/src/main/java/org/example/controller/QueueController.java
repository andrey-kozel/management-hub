package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.dto.QueueMessageDto;
import org.example.scheduler.QueueScheduler;
import org.example.service.QueueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/sqs")
public class QueueController {
    QueueService queueService;
    QueueScheduler queueScheduler;
    @PostMapping("/syncRequest")
    public ResponseEntity<String> syncRequest(@RequestBody QueueMessageDto message) {
        queueService.sendMessage(message);
        queueScheduler.sendMessage();
        return ResponseEntity.accepted()
                .body(
                        "organisationId: " + message.getOrganisationId() + "\n" +
                        "access_token: " + message.getAccessToken()
                );
    }
}

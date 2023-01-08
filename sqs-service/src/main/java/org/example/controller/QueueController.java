package org.example.controller;

import com.amazonaws.services.sqs.model.SendMessageResult;
import lombok.AllArgsConstructor;
import org.example.dto.QueueMessageDto;
import org.example.scheduler.QueueScheduler;
import org.example.service.QueueService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/queue")
public class QueueController {
    private final QueueService queueService;

    private final QueueScheduler queueScheduler;

    @PostMapping(value = "/repositories/sync", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> syncRequest(@RequestBody QueueMessageDto message) {
        SendMessageResult sendMessageResult = queueService.sendMessage(message);
        queueScheduler.sendMessage();
        return ResponseEntity.accepted()
                .body(
                        sendMessageResult.getMessageId()
                );
    }
}

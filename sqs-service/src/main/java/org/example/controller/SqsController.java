package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.dto.QueueMessage;
import org.example.service.SqsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/sqs")
public class SqsController {
    SqsService sqsService;
    @PostMapping("/syncRequest")
    public ResponseEntity<String> syncRequest(@RequestBody QueueMessage message) {
        return ResponseEntity.accepted()
                .body(
                        "organisationId: " + message.getOrganisationId() + "\n" +
                        "access_token: " + message.getAccessToken()
                );
    }
}

package com.example.apigateway.service;

import com.example.apigateway.client.QueueClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueueService {
    QueueClient queueClient;

    public void sendMessage() {
        queueClient.sendMessage();
    }
}

package com.example.apigateway.service;

import com.example.apigateway.client.QueueClient;
import com.example.apigateway.dto.QueueMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueueService {
    QueueClient queueClient;

    public void sendMessage(QueueMessageDto queueMessageDto) {
        queueClient.sendMessage(queueMessageDto);
    }
}

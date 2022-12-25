package org.example.service;

import lombok.AllArgsConstructor;
import org.example.client.QueueClient;
import org.example.dto.QueueMessage;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SqsService {
    QueueClient queueClient;
    public void sendMessage(QueueMessage message) {
        queueClient.sendMessage(message);
    }
}

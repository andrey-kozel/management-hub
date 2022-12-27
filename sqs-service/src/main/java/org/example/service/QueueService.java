package org.example.service;

import lombok.AllArgsConstructor;
import org.example.client.QueueClient;
import org.example.dto.QueueMessageDto;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QueueService {
    QueueClient queueClient;

    public void sendMessage(QueueMessageDto message) {
        queueClient.sendMessage(message);
    }
}

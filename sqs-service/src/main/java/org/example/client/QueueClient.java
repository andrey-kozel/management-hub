package org.example.client;

import org.example.dto.QueueMessage;
import org.springframework.stereotype.Component;

@Component
public interface QueueClient {
    void sendMessage(QueueMessage queueMessage);
}

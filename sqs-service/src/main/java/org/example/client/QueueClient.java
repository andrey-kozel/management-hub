package org.example.client;

import org.example.dto.QueueMessageDto;
import org.springframework.stereotype.Component;

@Component
public interface QueueClient {
    void sendMessage(QueueMessageDto queueMessage);
}

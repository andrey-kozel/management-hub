package org.example.client;

import com.amazonaws.services.sqs.model.SendMessageResult;
import org.example.dto.QueueMessageDto;

public interface QueueClient {

    String getQueueUrl();
    SendMessageResult sendMessage(QueueMessageDto queueMessage);
}

package org.example.client;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import lombok.RequiredArgsConstructor;
import org.example.dto.QueueMessage;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SqsQueueClient implements QueueClient{

    private final AmazonSQS amazonSQS;
    final private static String QUEUE_URL = "http://localhost:4566/000000000000/syncRequest";

    @Override
    public void sendMessage(QueueMessage message) {
        amazonSQS.sendMessage(
                new SendMessageRequest(QUEUE_URL, "organisationId: " + message.getOrganisationId() + "\n" +
                "access_token: " + message.getAccessToken()));
    }
}

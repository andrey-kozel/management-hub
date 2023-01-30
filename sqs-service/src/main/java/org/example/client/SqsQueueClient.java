package org.example.client;


import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import lombok.RequiredArgsConstructor;
import org.example.dto.QueueMessageDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SqsQueueClient implements QueueClient{

    private final AmazonSQS amazonSQS;
    private final CreateQueueRequest createQueueRequest;

    @Override
    public String getQueueUrl() {
        return amazonSQS.getQueueUrl(createQueueRequest.getQueueName()).getQueueUrl();
    }

    @Override
    public SendMessageResult sendMessage(QueueMessageDto message) {

        return amazonSQS.sendMessage(
                new SendMessageRequest(getQueueUrl(), "organisationId: " + message.getOrganisationId() + ";" +
                " access_token: " + message.getAccessToken()));
    }
}

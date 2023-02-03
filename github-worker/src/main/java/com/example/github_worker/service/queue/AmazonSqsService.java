package com.example.github_worker.service.queue;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.QueueDoesNotExistException;
import com.example.github_worker.model.SyncRepositoryMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AmazonSqsService implements QueueService {
    private static final int ORGANIZATION_ID_POSITION = 16;
    private static final int ACCESS_TOKEN_POSITION = 14;
    private final AmazonSQS amazonSQS;
    private final String syncRepositoriesQueueName;

    public AmazonSqsService(@Autowired
                            @Qualifier("sync-repositories")
                            AmazonSQS amazonSQS,
                            @Value("${aws.name.sqs.sync-repositories}")
                            String syncRepositoriesQueueName) {
        this.amazonSQS = amazonSQS;
        this.syncRepositoriesQueueName = syncRepositoriesQueueName;
    }

    @Override
    public List<SyncRepositoryMessage> getSyncRepositoryMessage() {
        try {
            String queueUrl = amazonSQS.getQueueUrl(syncRepositoriesQueueName).getQueueUrl();
            List<Message> messageList = amazonSQS
                    .receiveMessage(queueUrl)
                    .getMessages();

            return messageList.stream().map(message -> {
                String[] strArray = message.getBody().split("; ");

                amazonSQS.deleteMessage(queueUrl, message.getReceiptHandle());
                return SyncRepositoryMessage
                        .builder()
                        .organizationId(Long.parseLong(strArray[0].substring(ORGANIZATION_ID_POSITION)))
                        .accessToken(strArray[1].substring(ACCESS_TOKEN_POSITION))
                        .build();
            }).collect(Collectors.toList());
        } catch (QueueDoesNotExistException exception) {
            log.warn("Queue " + syncRepositoriesQueueName + " doesn't exists");
            return new ArrayList<>();
        }

    }
}

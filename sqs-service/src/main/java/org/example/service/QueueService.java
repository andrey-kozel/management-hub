package org.example.service;

import com.amazonaws.services.sqs.model.SendMessageResult;
import lombok.RequiredArgsConstructor;
import org.example.client.QueueClient;
import org.example.dto.QueueMessageDto;
import org.example.scheduler.GithubApi;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueueService {
    private final QueueClient queueClient;
    private final GithubApi githubApi;

    public SendMessageResult sendMessage(QueueMessageDto message) {
        return queueClient.sendMessage(message);
    }

    public String getToken(Long organisationId) {
        if (!githubApi.getOrgId().equals(organisationId)) {
            return null;
        }
        return githubApi.getAccessToken();
    }
}

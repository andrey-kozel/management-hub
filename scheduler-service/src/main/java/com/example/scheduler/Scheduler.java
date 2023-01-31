package com.example.scheduler;

import com.example.scheduler.client.OrganizationClient;
import com.example.scheduler.client.OrganizationSettingClient;
import com.example.scheduler.client.SqsClient;
import com.example.scheduler.dto.OrganizationResponse;
import com.example.scheduler.dto.QueueMessageDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Scheduler {
    private final SqsClient sqsClient;
    private final OrganizationClient organizationClient;
    private final OrganizationSettingClient organizationSettingClient;

    @Scheduled(fixedRate = 5000)
    public void syncRepository() {
        List<OrganizationResponse> responses = organizationClient.getAll();
        Map<Long, String> organizationIdAndAccessKeyMap = new HashMap<>();

        responses.forEach(organizationResponse -> {
            String token = organizationSettingClient.getAccessToken(organizationResponse.getId());

            if (token != null) {
                organizationIdAndAccessKeyMap.put(organizationResponse.getId(), token);
            }
        });

        organizationIdAndAccessKeyMap.forEach((organizationId, token) -> {
            QueueMessageDto message = QueueMessageDto
                    .builder()
                    .organizationId(organizationId)
                    .accessToken(token)
                    .build();

            sqsClient.syncRequest(message);
        });
    }

}

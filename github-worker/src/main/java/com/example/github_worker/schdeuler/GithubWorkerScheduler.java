package com.example.github_worker.schdeuler;

import com.example.github_worker.client.GithubApiClient;
import com.example.github_worker.client.OrganizationClient;
import com.example.github_worker.client.SqsClient;
import com.example.github_worker.dto.GithubRepositoryDto;
import com.example.github_worker.dto.SyncMessageDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GithubWorkerScheduler {
    private final static String AUTHORIZATION_HEADER_PREFIX = "Bearer ";

    private final SqsClient sqsClient;
    private final GithubApiClient githubApiClient;
    private final OrganizationClient organizationClient;

    @Scheduled(fixedRate = 1)
    public void syncRepository() {
        //endpoint getMessage ещё не готов!!
        SyncMessageDto message = sqsClient.getMessage();

        while (message != null) {
            List<GithubRepositoryDto> repositoryList =
                    githubApiClient.getReposByAccessKey(AUTHORIZATION_HEADER_PREFIX + message.getAccessToken());

            organizationClient.saveOrUpdateRepositories(message.getOrganizationId(), repositoryList);
        }
    }
}

package com.example.github_worker.schdeuler;

import com.example.github_worker.client.GithubApiClient;
import com.example.github_worker.client.GithubServiceClient;
import com.example.github_worker.dto.GithubRepositoryDto;
import com.example.github_worker.model.SyncRepositoryMessage;
import com.example.github_worker.service.queue.QueueService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GithubWorkerScheduler {
    private final static String AUTHORIZATION_HEADER_PREFIX = "Bearer ";

    private final GithubApiClient githubApiClient;
    private final GithubServiceClient githubServiceClient;
    private final QueueService queueService;

    @Scheduled(fixedRate = 1)
    public void syncRepositories() {
        List<SyncRepositoryMessage> messages = queueService.getSyncRepositoryMessage();

        messages.forEach(message -> {
            List<GithubRepositoryDto> repositoryList =
                    githubApiClient.getReposByAccessKey(AUTHORIZATION_HEADER_PREFIX + message.getAccessToken());

            githubServiceClient.saveOrUpdateRepositories(message.getOrganizationId(), repositoryList);
        });
    }
}

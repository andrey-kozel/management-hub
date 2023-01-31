package com.example.github_worker.schdeuler;

import com.example.github_worker.client.GithubApiClient;
import com.example.github_worker.client.SqsClient;
import com.example.github_worker.dto.RepositoryDto;
import com.example.github_worker.dto.SyncMessageDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GithubWorkerScheduler {
    private final SqsClient sqsClient;
    private final GithubApiClient githubApiClient;

    @Scheduled(fixedRate = 5000)
    public void syncRepository() {
        SyncMessageDto message = SyncMessageDto
                .builder()
                .organizationId(1L)
                .accessToken("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")
                .build();
//                sqsClient.getMessage();

        while (message != null) {
            List<RepositoryDto> repositoryList =
                    githubApiClient.getReposByAccessKey("Bearer " + message.getAccessToken());


//            message = sqsClient.getMessage();
        }


    }
}

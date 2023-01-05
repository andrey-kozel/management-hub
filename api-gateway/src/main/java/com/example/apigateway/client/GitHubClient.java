package com.example.apigateway.client;

import com.example.apigateway.dto.RepositoryCommitDayActivityResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "github", url = "${services.github.url}/api/v1/repositories")
public interface GitHubClient {

    @GetMapping("{repositoryId}/stat/commit_activity")
    List<RepositoryCommitDayActivityResponse> getOneYearCommitActivity(@PathVariable Long repositoryId);
}

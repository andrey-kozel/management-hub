package com.example.apigateway.client;

import java.util.List;

import com.example.apigateway.dto.RepositoryCommitDayActivityResponse;
import com.example.apigateway.dto.WeekCommitsActivityStatisticsRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "github", url = "${services.github.url}/api/v1/repositories")
public interface GitHubClient {

    @PostMapping("{repositoryId}/stat/commit_activity")
    List<RepositoryCommitDayActivityResponse> addOrUpdateCommitActivity(
            @RequestBody List<WeekCommitsActivityStatisticsRequest> weekCommitsActivityStatisticsList,
            @PathVariable Long repositoryId
    );

    @GetMapping("{repositoryId}/stat/commit_activity")
    List<RepositoryCommitDayActivityResponse> getOneYearCommitActivity(@PathVariable Long repositoryId);
}

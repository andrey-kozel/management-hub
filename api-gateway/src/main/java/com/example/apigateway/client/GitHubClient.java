package com.example.apigateway.client;

import com.example.apigateway.dto.ContributorsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "github", url = "${services.github.url}/api/v1/repositories")
public interface GitHubClient {
    @GetMapping("{repositoryId}/contributors")
    List<ContributorsResponse> getContributorsByRepositoryId(@PathVariable("repositoryId") Long repositoryId);
}

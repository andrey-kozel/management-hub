package com.example.github_worker.client;

import com.example.github_worker.dto.GithubRepositoryDto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "github-api", url = "${services.github-api.url}")
public interface GithubApiClient {
    @GetMapping(value = "user/repos")
    List<GithubRepositoryDto> getReposByAccessKey(@RequestHeader(value = "Authorization", required = true) String accessToken);
}

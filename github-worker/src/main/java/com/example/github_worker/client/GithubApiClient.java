package com.example.github_worker.client;

import com.example.github_worker.dto.RepositoryDto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "github-api", url = "${services.github-api.url}")
public interface GithubApiClient {
    @GetMapping("user/repos")
    List<RepositoryDto> getReposByAccessKey(@RequestHeader(value = "Authorization", required = true) String accessToken);
}

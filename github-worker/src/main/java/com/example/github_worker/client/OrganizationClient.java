package com.example.github_worker.client;

import com.example.github_worker.dto.GithubRepositoryDto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "github-service", url = "${services.github-service.url}/api/v1")
public interface OrganizationClient {
    @PostMapping("/organizations/{organizationId}/repositories")
    void saveOrUpdateRepositories(@PathVariable("organizationId") Long organizationId, @RequestBody List<GithubRepositoryDto> repositoryList);
}

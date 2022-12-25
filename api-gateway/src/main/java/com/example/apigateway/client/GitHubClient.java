package com.example.apigateway.client;

import com.example.apigateway.dto.ContributorsResponse;
import com.example.apigateway.dto.SaveOrGetContributorsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "github", url = "${services.github.url}/api/v1/repository")
public interface GitHubClient {

    @PostMapping("/contributors")
    List<ContributorsResponse> updateContributors(@RequestBody final SaveOrGetContributorsDto contributorsDto);

    @GetMapping("{repositoryId}/contributors")
    List<ContributorsResponse> getContributorsByRepositoryId(@PathVariable("repositoryId") Long repositoryId);
}

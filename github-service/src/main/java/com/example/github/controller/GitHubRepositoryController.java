package com.example.github.controller;

import com.example.github.converter.GitHubRepositoryConverter;
import com.example.github.dto.GitHubRepositoryDto;
import com.example.github.model.GitHubRepository;
import com.example.github.service.GitHubRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/repositories")
@RequiredArgsConstructor
public class GitHubRepositoryController {

  private final GitHubRepositoryService gitHubRepositoryService;
  private final GitHubRepositoryConverter gitHubRepositoryConverter;

  @GetMapping("{token}")
  public List<GitHubRepositoryDto> get(@PathVariable("token") final String token) {
    final List<GitHubRepository> repositories = gitHubRepositoryService.getGitHubRepositories(token);
    return gitHubRepositoryConverter.modelsToDto(repositories);
  }

}

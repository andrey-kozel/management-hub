package com.example.github.service;

import com.example.github.model.GitHubRepository;
import com.example.github.repository.GitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GitHubRepositoryService {

  private final GitRepository testRepository;

  public List<GitHubRepository> getGitHubRepositories(final String token) {
    return testRepository.findAllByToken(token);
  }

}

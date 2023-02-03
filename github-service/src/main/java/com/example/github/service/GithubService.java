package com.example.github.service;

import com.example.github.model.GitRepository;
import com.example.github.model.Repo;
import com.example.github.repository.GithubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GithubService {
    private final GithubRepository githubRepository;

   public List<Repo> findAllRepositoryByToken(final long token) {
       return githubRepository.findAllRepositoryByToken(token);
   }

   public void saveOrUpdateRepository(GitRepository repository) {
       githubRepository.upsert(repository.getGithubId(), repository.getNodeId(), repository.getName(), repository.getFullName(), repository.getIsPrivate(), repository.getDescription(), repository.getIsFork(), repository.getCreatedAt(), repository.getUpdatedAt(), repository.getPushedAt(), repository.getHomePage(), repository.getSize(), repository.getStargazersCount(), repository.getWatchersCount(), repository.getForksCount(), repository.getOpenIssuesCount(), repository.getLicense(), repository.getDefaultBranch(), repository.getLanguage(), repository.getPermissions(), repository.getIsArchived(), repository.getIsDisabled(), repository.getIsSynchronizationEnabled(), repository.getSynchronizedAt(), repository.getOrganizationId());
   }
}

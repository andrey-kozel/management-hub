package com.example.github.service;

import com.example.github.dto.ContributorsResponse;
import com.example.github.model.Contributor;
import com.example.github.repository.ContributorRepository;
import com.example.github.repository.RepositoryWithContributorsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ContributorService {

    private final ContributorRepository contributorRepository;
    private final RepositoryWithContributorsRepository repositoryWithContributorsRepository;

    public Long addContributor(final String login, final String accountId) {
        return contributorRepository.addContributor(login, accountId);
    }

    public Optional<Contributor> getContributor(final String login, final String accountId) {
        return contributorRepository.getContributor(login, accountId);
    }

    public List<ContributorsResponse> getContributorsByRepositoryId(final Long repositoryId) {
        return contributorRepository.getContributorByRepositoryId(repositoryId);
    }

    public void addOrUpdateRepositoryContributors(final Long repositoryId, final Long contributorId, final Long contributions) {
        repositoryWithContributorsRepository.addRepositoryContributors(repositoryId, contributorId, contributions);
    }
}

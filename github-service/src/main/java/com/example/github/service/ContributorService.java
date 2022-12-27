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

    public Contributor getContributor(final String login, final String accountId) {
        Optional<Contributor> contributor = contributorRepository.getContributor(login, accountId);
        return contributor.orElseGet(() -> Contributor.builder().build());
    }

    public void addOrUpdateContributor(final Long repositoryId, final Long contributorId, final Long contributions) {
        repositoryWithContributorsRepository.addOrUpdateRepositoryContributors(repositoryId, contributorId, contributions);
    }

    public List<ContributorsResponse> getContributorsByRepositoryId(Long repositoryId) {
        return contributorRepository.getContributorByRepositoryId(repositoryId);
    }
}

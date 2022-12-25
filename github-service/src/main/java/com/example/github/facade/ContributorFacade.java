package com.example.github.facade;

import com.example.github.dto.ContributorsResponse;
import com.example.github.dto.SaveOrGetContributorsDto;
import com.example.github.model.Contributor;
import com.example.github.service.ContributorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ContributorFacade {
    private final ContributorService contributorService;

    public List<ContributorsResponse> addOrUpdateContributors(final SaveOrGetContributorsDto contributorsDto) {
        List<ContributorsResponse> contributorsList = contributorService.getContributorsFromGitHubApi(contributorsDto);
        contributorsList.forEach(contributor -> {
            Contributor foundContributor = contributorService.getContributor(contributor.getLogin(), contributor.getId());
            Long contributorId = foundContributor.getId();

            if (foundContributor.getAccountId() == null) {
                contributorId = contributorService.addContributor(contributor.getLogin(), contributor.getId());
            }
            contributorService.addOrUpdateContributor(
                    contributorsDto.getRepositoryId(),
                    contributorId,
                    contributor.getContributions()
            );
        });
        if(contributorsList.size() != 0) {
            return contributorsList;
        }
        return new ArrayList<>();
    }
}

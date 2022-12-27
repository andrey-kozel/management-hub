package com.example.github.facade;

import com.example.github.dto.ContributorsResponse;
import com.example.github.dto.SaveContributorDto;
import com.example.github.model.Contributor;
import com.example.github.service.ContributorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ContributorFacade {
    private final ContributorService contributorService;

    public ContributorsResponse addOrUpdateContributors(final SaveContributorDto contributorsDto) {
        Contributor foundContributor = contributorService.getContributor(contributorsDto.getLogin(), contributorsDto.getAccountId());

        Long contributorId = 0L;
        if (foundContributor.getAccountId() == null) {
            contributorId = contributorService.addContributor(contributorsDto.getLogin(), contributorsDto.getAccountId());
        } else {
            contributorId = foundContributor.getId();
        }

        contributorService.addOrUpdateContributor(
                contributorsDto.getRepositoryId(),
                contributorId,
                contributorsDto.getContributions()
        );
        return ContributorsResponse
                .builder()
                .id(contributorId)
                .contributions(contributorsDto.getContributions())
                .login(contributorsDto.getLogin())
                .build();
    }

    public List<ContributorsResponse> getContributorsByRepositoryId(Long repositoryId) {
        return contributorService.getContributorsByRepositoryId(repositoryId);
    }
}

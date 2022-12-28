package com.example.github.facade;

import com.example.github.dto.ContributorsResponse;
import com.example.github.dto.SaveContributorDto;
import com.example.github.model.Contributor;
import com.example.github.service.ContributorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ContributorFacade {
    private final ContributorService contributorService;

    public List<ContributorsResponse> addOrUpdateContributors(final List<SaveContributorDto> contributorsDto) {
        List<ContributorsResponse> contributorsResponseList = new ArrayList<>();
        Long contributorId = 0L;

        for (SaveContributorDto saveContributorDto : contributorsDto) {
            Contributor foundContributor = contributorService.getContributor(saveContributorDto.getLogin(), saveContributorDto.getAccountId());

            contributorId = 0L;
            if (foundContributor.getAccountId() == null) {
                contributorId = contributorService.addContributor(saveContributorDto.getLogin(), saveContributorDto.getAccountId());
            } else {
                contributorId = foundContributor.getId();
            }

            contributorService.addOrUpdateContributor(
                    saveContributorDto.getRepositoryId(),
                    contributorId,
                    saveContributorDto.getContributions()
            );
            contributorsResponseList.add(
                    ContributorsResponse
                            .builder()
                            .id(contributorId)
                            .contributions(saveContributorDto.getContributions())
                            .login(saveContributorDto.getLogin())
                            .build()
            );
        }

        return contributorsResponseList;
    }

    public List<ContributorsResponse> getContributorsByRepositoryId(Long repositoryId) {
        return contributorService.getContributorsByRepositoryId(repositoryId);
    }
}

package com.example.github.facade;

import com.example.github.dto.ContributorsResponse;
import com.example.github.dto.SaveContributorDto;
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

    public List<ContributorsResponse> addOrUpdateContributors(final List<SaveContributorDto> contributorsDto) {
        List<ContributorsResponse> contributorsResponseList = new ArrayList<>();

        Long findContributorId = 0L;
        for(SaveContributorDto contributorDto : contributorsDto) {
            findContributorId =
                    contributorService.getContributor(contributorDto.getLogin(), contributorDto.getAccountId())
                            .map((findContributor) -> {
                                contributorService.updateRepositoryContributors(
                                        contributorDto.getRepositoryId(),
                                        findContributor.getId(),
                                        contributorDto.getContributions()
                                );

                                return findContributor.getId();
                            })
                            .orElseGet(() -> {
                                Long contributorId = contributorService.addContributor(
                                        contributorDto.getLogin(),
                                        contributorDto.getAccountId()
                                );
                                contributorService.addRepositoryContributors(
                                        contributorDto.getRepositoryId(),
                                        contributorId,
                                        contributorDto.getContributions()
                                );

                                return contributorId;
                            });

            contributorsResponseList.add(
                    ContributorsResponse
                            .builder()
                            .id(findContributorId)
                            .login(contributorDto.getLogin())
                            .contributions(contributorDto.getContributions())
                            .build()
            );
        }
        return contributorsResponseList;
    }

    public List<ContributorsResponse> getContributorsByRepositoryId(Long repositoryId) {
        return contributorService.getContributorsByRepositoryId(repositoryId);
    }
}

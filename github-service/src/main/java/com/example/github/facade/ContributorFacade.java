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

    public void addOrUpdateContributors(final List<SaveContributorDto> contributorsDto) {
        for(SaveContributorDto contributorDto : contributorsDto) {
                    contributorService.getContributor(contributorDto.getLogin(), contributorDto.getAccountId())
                            .map((findContributor) -> {
                                contributorService.addOrUpdateRepositoryContributors(
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
                                contributorService.addOrUpdateRepositoryContributors(
                                        contributorDto.getRepositoryId(),
                                        contributorId,
                                        contributorDto.getContributions()
                                );

                                return contributorId;
                            });
        }
    }

    public List<ContributorsResponse> getContributorsByRepositoryId(Long repositoryId) {
        return contributorService.getContributorsByRepositoryId(repositoryId);
    }
}

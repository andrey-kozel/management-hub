package com.example.github.facade;

import com.example.github.dto.SaveOrGetContributorsDto;
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

    public List<Contributor> updateContributors(final SaveOrGetContributorsDto contributorsDto) {
        List<Contributor> contributorsList = contributorService.getContributorsFromGitHubApi(contributorsDto);
        return new  ArrayList<>();
    }
}

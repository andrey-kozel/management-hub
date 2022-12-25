package com.example.github.service;

import com.example.github.dto.ContributorsResponse;
import com.example.github.dto.SaveOrGetContributorsDto;
import com.example.github.model.Contributor;
import com.example.github.repository.ContributorRepository;
import com.example.github.repository.RepositoryWithContributorsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ContributorService {

    private final ContributorRepository contributorRepository;
    private final RepositoryWithContributorsRepository repositoryWithContributorsRepository;

    private static final String URL_TO_GET_CONTRIBUTORS =
            "https://api.github.com/repos/{ownerName}/{repositoryName}/contributors";

    public List<ContributorsResponse> getContributorsFromGitHubApi(final SaveOrGetContributorsDto contributorsDto) {
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("ownerName", contributorsDto.getOwnerName());
        pathVariables.put("repositoryName", contributorsDto.getRepositoryName());

        URI uri = UriComponentsBuilder.fromUriString(URL_TO_GET_CONTRIBUTORS).buildAndExpand(pathVariables).toUri();

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<ContributorsResponse[]> responseEntity =
                restTemplate.getForEntity(uri, ContributorsResponse[].class);
        ContributorsResponse[] contributorsArray = responseEntity.getBody();
        if (contributorsArray != null) {
            return new ArrayList<>(Arrays.asList(contributorsArray));
        }
        return new ArrayList<>();
    }

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

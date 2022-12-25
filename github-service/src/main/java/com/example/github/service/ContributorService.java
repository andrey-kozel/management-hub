package com.example.github.service;

import com.example.github.dto.SaveOrGetContributorsDto;
import com.example.github.model.Contributor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ContributorService {
    private static final String URL_TO_GET_CONTRIBUTORS =
            "https://api.github.com/repos/{ownerName}/{repositoryName}/contributors";

    public List<Contributor> getContributorsFromGitHubApi(final SaveOrGetContributorsDto contributorsDto) {
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("ownerName", contributorsDto.getOwnerName());
        pathVariables.put("repositoryName", contributorsDto.getRepositoryName());

        URI uri = UriComponentsBuilder.fromUriString(URL_TO_GET_CONTRIBUTORS).buildAndExpand(pathVariables).toUri();

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Contributor[]> responseEntity =
                restTemplate.getForEntity(uri, Contributor[].class);
        Contributor[] contributorsArray = responseEntity.getBody();
        if (contributorsArray != null) {
            return new ArrayList<>(Arrays.asList(contributorsArray));
        }
        return new ArrayList<>();
    }
}

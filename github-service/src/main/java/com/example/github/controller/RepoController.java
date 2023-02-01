package com.example.github.controller;

import com.example.github.converter.GithubRepositoryConverter;
import com.example.github.dto.SaveRepositoryDto;
import com.example.github.model.GitRepository;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.example.github.converter.RepoConverter;
import com.example.github.dto.RepoDto;
import com.example.github.model.Repo;
import com.example.github.service.GithubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class RepoController {
    private final GithubService githubService;
    private final RepoConverter repoConverter;
    private final GithubRepositoryConverter githubRepositoryConverter;

    @GetMapping("/organizations/{organizationId}/repositories")
    protected List<RepoDto> getRepositories(@PathVariable long organizationId) {
        List<Repo> listRepo = githubService.findAllRepositoryByToken(organizationId);
        return repoConverter.toDto(listRepo);
    }

    @PostMapping("/organizations/{organizationId}/repositories")
    protected void saveOrUpdateRepositories(@PathVariable Long organizationId,
                                            @RequestBody List<SaveRepositoryDto> repositoryDtoList) {
        List<GitRepository> repositoryList = new ArrayList<>();

        repositoryDtoList.forEach(repositoryDto -> {
            GitRepository repository = githubRepositoryConverter.fromDto(repositoryDto);
            repository.setOrganizationId(organizationId);
            repository.setIsSynchronizationEnabled(true);
            repository.setSynchronizedAt(Timestamp.from(Instant.now()));

            repositoryList.add(repository);
        });

        repositoryList.forEach(githubService::saveOrUpdateRepository);
    }
}

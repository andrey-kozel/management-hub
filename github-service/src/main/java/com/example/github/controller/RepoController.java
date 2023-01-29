package com.example.github.controller;

import java.util.List;

import com.example.github.converter.RepoConverter;
import com.example.github.dto.RepoDto;
import com.example.github.model.Repo;
import com.example.github.service.GithubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class RepoController {
    private final GithubService githubService;
    private final RepoConverter repoConverter;

    @GetMapping("/organizations/{organizationId}/repositories")
    protected List<RepoDto> getRepositories(@ModelAttribute("token") long organizationId) {
        List<Repo> listRepo = githubService.findAllRepositoryByToken(organizationId);
        return repoConverter.toDto(listRepo);
    }
}

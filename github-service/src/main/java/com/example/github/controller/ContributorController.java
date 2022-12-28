package com.example.github.controller;

import com.example.github.dto.ContributorsResponse;
import com.example.github.dto.SaveContributorDto;
import com.example.github.facade.ContributorFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/repositories")
@RequiredArgsConstructor
public class ContributorController {
    private final ContributorFacade contributorFacade;

    @PostMapping("/contributors")
    public List<ContributorsResponse> addOrUpdateContributors(@RequestBody final List<SaveContributorDto> contributorsDto) {
        return contributorFacade.addOrUpdateContributors(contributorsDto);
    }

    @GetMapping("{repositoryId}/contributors")
    List<ContributorsResponse> getContributorsByRepositoryId(@PathVariable("repositoryId") Long repositoryId) {
        return contributorFacade.getContributorsByRepositoryId(repositoryId);
    }
}

package com.example.github.controller;

import com.example.github.dto.SaveOrGetContributorsDto;
import com.example.github.facade.ContributorFacade;
import com.example.github.model.Contributor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/repository")
@RequiredArgsConstructor
public class ContributorController {
    private final ContributorFacade contributorFacade;

    @PostMapping("/contributors")
    public List<Contributor> updateContributors(@RequestBody final SaveOrGetContributorsDto contributorsDto) {
        return contributorFacade.updateContributors(contributorsDto);
    }
}

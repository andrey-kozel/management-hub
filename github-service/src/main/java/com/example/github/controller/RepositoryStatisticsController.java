package com.example.github.controller;

import com.example.github.facade.RepositoryStatisticsFacade;
import com.example.github.model.WeekCommitsStatistic;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/repository/{repositoryId}/stat")
@RequiredArgsConstructor
public class RepositoryStatisticsController {
    private final RepositoryStatisticsFacade repositoryStatisticsFacade;

    @PostMapping("commit_activity")
    void addOrUpdateCommitActivity(@RequestBody List<WeekCommitsStatistic> weekCommitsStatisticList,
                           @PathVariable Long repositoryId) {
        repositoryStatisticsFacade.addOrUpdateCommitActivity(weekCommitsStatisticList, repositoryId);
    }
}

package com.example.github.controller;

import com.example.github.dto.RepositoryCommitDayActivityDto;
import com.example.github.facade.RepositoryStatisticsFacade;
import com.example.github.model.RepositoryCommitDayActivity;
import com.example.github.model.WeekCommitsActivityStatistic;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/repository/{repositoryId}/stat")
@RequiredArgsConstructor
public class RepositoryStatisticsController {
    private final RepositoryStatisticsFacade repositoryStatisticsFacade;

    @PostMapping("commit_activity")
    void addOrUpdateCommitActivity(@RequestBody List<WeekCommitsActivityStatistic> weekCommitsActivityStatisticList,
                                   @PathVariable Long repositoryId) {
        repositoryStatisticsFacade.addOrUpdateCommitActivity(weekCommitsActivityStatisticList, repositoryId);
    }

    @GetMapping("commit_activity")
    List<RepositoryCommitDayActivityDto> getOneYearCommitActivity(@PathVariable Long repositoryId) {
        return repositoryStatisticsFacade.getOneYearCommitActivity(repositoryId);
    }
}

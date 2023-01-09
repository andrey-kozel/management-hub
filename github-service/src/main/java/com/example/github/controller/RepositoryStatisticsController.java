package com.example.github.controller;

import com.example.github.dto.RepositoryCommitDayActivityDto;
import com.example.github.facade.RepositoryStatisticsFacade;
import com.example.github.model.WeekCommitsActivityStatistics;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/repositories/{repositoryId}/stat")
@RequiredArgsConstructor
public class RepositoryStatisticsController {
    private final RepositoryStatisticsFacade repositoryStatisticsFacade;

    @PostMapping("commit_activity")
    void addOrUpdateCommitActivity(@RequestBody List<WeekCommitsActivityStatistics> weekCommitsActivityStatisticsList,
                                   @PathVariable Long repositoryId) {
        repositoryStatisticsFacade.addOrUpdateCommitActivity(weekCommitsActivityStatisticsList, repositoryId);
    }

    @GetMapping("commit_activity")
    List<RepositoryCommitDayActivityDto> getOneYearCommitActivity(@PathVariable Long repositoryId) {
        return repositoryStatisticsFacade.getOneYearCommitActivity(repositoryId);
    }
}

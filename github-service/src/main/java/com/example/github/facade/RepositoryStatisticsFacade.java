package com.example.github.facade;

import com.example.github.model.WeekCommitsStatistic;
import com.example.github.service.RepositoryStatisticService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RepositoryStatisticsFacade {
    private final RepositoryStatisticService repositoryStatisticService;

    public void addOrUpdateCommitActivity(List<WeekCommitsStatistic> weekCommitsStatisticList, Long repositoryId) {
        repositoryStatisticService.addOrUpdateCommitActivity(weekCommitsStatisticList, repositoryId);
    }
}

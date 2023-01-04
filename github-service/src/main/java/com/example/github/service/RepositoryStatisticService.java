package com.example.github.service;

import com.example.github.model.WeekCommitsStatistic;
import com.example.github.repository.StatisticRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RepositoryStatisticService {
    private final StatisticRepository statisticRepository;
    private static final Long ONE_DAY_STEP = 86400L;

    public void addOrUpdateCommitActivity(List<WeekCommitsStatistic> weekCommitsStatisticList, Long repositoryId) {
        weekCommitsStatisticList.forEach(weekCommitsStatistic -> {
            if(weekCommitsStatistic.getTotalCommits() > 0) {
                List<Long> daysCommits = weekCommitsStatistic.getDays();

                for(int i = 0; i < daysCommits.size(); i++) {
                    if(daysCommits.get(i) > 0) {
                        statisticRepository.addOrUpdateCommitDayActivity(weekCommitsStatistic.getTimestamp() + ONE_DAY_STEP * (i + 1), daysCommits.get(i), repositoryId);
                    }
                }
            }
        });
    }
}

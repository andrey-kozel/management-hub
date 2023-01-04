package com.example.github.service;

import com.example.github.model.RepositoryCommitDayActivity;
import com.example.github.model.WeekCommitsActivityStatistic;
import com.example.github.repository.StatisticRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RepositoryStatisticService {
    private final StatisticRepository statisticRepository;
    private static final Long ONE_DAY_STEP = 86400L;

    public void addOrUpdateCommitActivity(List<WeekCommitsActivityStatistic> weekCommitsActivityStatisticList, Long repositoryId) {
        weekCommitsActivityStatisticList.forEach(weekCommitsActivityStatistic -> {
            if(weekCommitsActivityStatistic.getTotalCommits() > 0) {
                List<Long> daysCommits = weekCommitsActivityStatistic.getDays();

                for(int i = 0; i < daysCommits.size(); i++) {
                    if(daysCommits.get(i) > 0) {
                        statisticRepository.addOrUpdateCommitDayActivity(weekCommitsActivityStatistic.getTimestamp() + ONE_DAY_STEP * (i + 1), daysCommits.get(i), repositoryId);
                    }
                }
            }
        });
    }

    public Optional<List<RepositoryCommitDayActivity>> getCommitActivity(final Long repositoryId, final Long startDate) {
        return statisticRepository.getRepositoryCommitDayActivityByRepositoryId(repositoryId, startDate);
    }
}

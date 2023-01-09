package com.example.github.service;

import com.example.github.model.RepositoryCommitDayActivity;
import com.example.github.model.WeekCommitsActivityStatistics;
import com.example.github.repository.StatisticsRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RepositoryStatisticsService {
    private final StatisticsRepository statisticsRepository;
    private static final Long ONE_DAY_STEP = 86400L;

    public void addOrUpdateCommitActivity(List<WeekCommitsActivityStatistics> weekCommitsActivityStatisticsList, Long repositoryId) {
        weekCommitsActivityStatisticsList.forEach(weekCommitsActivityStatistics -> {
            if(weekCommitsActivityStatistics.getTotalCommits() > 0) {
                List<Long> daysCommits = weekCommitsActivityStatistics.getDays();

                for(int i = 0; i < daysCommits.size(); i++) {
                    if(daysCommits.get(i) > 0) {
                        statisticsRepository.addOrUpdateCommitDayActivity(weekCommitsActivityStatistics.getTimestamp() + ONE_DAY_STEP * (i + 1), daysCommits.get(i), repositoryId);
                    }
                }
            }
        });
    }

    public Optional<List<RepositoryCommitDayActivity>> getCommitActivity(final Long repositoryId, final Long startDate) {
        return statisticsRepository.getRepositoryCommitDayActivityByRepositoryId(repositoryId, startDate);
    }
}

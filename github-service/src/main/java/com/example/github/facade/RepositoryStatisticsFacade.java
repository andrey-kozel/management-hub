package com.example.github.facade;

import com.example.github.converter.RepositoryCommitDayActivityConverter;
import com.example.github.dto.RepositoryCommitDayActivityDto;
import com.example.github.model.RepositoryCommitDayActivity;
import com.example.github.model.WeekCommitsActivityStatistics;
import com.example.github.service.RepositoryStatisticsService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RepositoryStatisticsFacade {
    private final RepositoryStatisticsService repositoryStatisticsService;
    private final RepositoryCommitDayActivityConverter converter;
    private final Long SECONDS_IN_ONE_DAY = 86400L;
    private final Long SECONDS_IN_ONE_YEAR = 31536000L;

    public void addOrUpdateCommitActivity(List<WeekCommitsActivityStatistics> weekCommitsActivityStatisticsList, Long repositoryId) {
        repositoryStatisticsService.addOrUpdateCommitActivity(weekCommitsActivityStatisticsList, repositoryId);
    }

    public List<RepositoryCommitDayActivityDto> getOneYearCommitActivity(Long repositoryId) {
        Long nowDate = LocalDate.now().atTime(0, 0).toLocalDate().toEpochDay() * SECONDS_IN_ONE_DAY;
        final Long[] time = {nowDate - SECONDS_IN_ONE_YEAR};

        List<RepositoryCommitDayActivity> repositoryCommitDayActivityList =
                repositoryStatisticsService.getCommitActivity(repositoryId, time[0]).orElseGet(ArrayList::new)
                        .stream()
                        .sorted(Comparator.comparingLong(RepositoryCommitDayActivity::getDate))
                        .toList();

        List<RepositoryCommitDayActivityDto> repositoryCommitDayActivityDtos = new ArrayList<>();
        int size = repositoryCommitDayActivityList.size();
        AtomicInteger count = new AtomicInteger();

        repositoryCommitDayActivityList.forEach(dayActivity -> {
            while(time[0] < dayActivity.getDate()) {
                repositoryCommitDayActivityDtos.add(
                        RepositoryCommitDayActivityDto
                                .builder()
                                .commits(0L)
                                .date(time[0])
                                .build()
                );
                time[0] += SECONDS_IN_ONE_DAY;
            }
            RepositoryCommitDayActivityDto buffer = converter.toDto(dayActivity);
            buffer.setDate(time[0]);

            repositoryCommitDayActivityDtos.add(buffer);
            count.getAndIncrement();

            time[0] += SECONDS_IN_ONE_DAY;

            if(count.get() == size) {
                while (time[0] < nowDate) {
                    repositoryCommitDayActivityDtos.add(
                            RepositoryCommitDayActivityDto
                                    .builder()
                                    .commits(0L)
                                    .date(time[0])
                                    .build()
                    );
                    time[0] += SECONDS_IN_ONE_DAY;
                }
            }
        });
        return repositoryCommitDayActivityDtos;
    }
}

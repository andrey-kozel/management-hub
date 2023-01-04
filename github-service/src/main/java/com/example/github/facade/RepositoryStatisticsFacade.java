package com.example.github.facade;

import com.example.github.converter.RepositoryCommitDayActivityConverter;
import com.example.github.dto.RepositoryCommitDayActivityDto;
import com.example.github.model.RepositoryCommitDayActivity;
import com.example.github.model.WeekCommitsActivityStatistic;
import com.example.github.service.RepositoryStatisticService;
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
    private final RepositoryStatisticService repositoryStatisticService;
    private final RepositoryCommitDayActivityConverter converter;
    private final Long SECONDS_IN_ONE_DAY = 86400L;
    private final Long SECONDS_IN_ONE_YEAR = 31556926L;

    public void addOrUpdateCommitActivity(List<WeekCommitsActivityStatistic> weekCommitsActivityStatisticList, Long repositoryId) {
        repositoryStatisticService.addOrUpdateCommitActivity(weekCommitsActivityStatisticList, repositoryId);
    }

    public List<RepositoryCommitDayActivityDto> getOneYearCommitActivity(Long repositoryId) {
        Long nowDate = LocalDate.now().toEpochDay() * SECONDS_IN_ONE_DAY;
        final Long[] time = {nowDate - SECONDS_IN_ONE_YEAR};

        List<RepositoryCommitDayActivity> repositoryCommitDayActivityList =
                repositoryStatisticService.getCommitActivity(repositoryId, time[0]).orElseGet(ArrayList::new)
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
                if(time[0] + SECONDS_IN_ONE_DAY < dayActivity.getDate()) {
                    time[0] += SECONDS_IN_ONE_DAY;
                } else {
                    time[0] += SECONDS_IN_ONE_DAY * 2;
                }
            }
            repositoryCommitDayActivityDtos.add(converter.toDto(dayActivity));
            count.getAndIncrement();

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

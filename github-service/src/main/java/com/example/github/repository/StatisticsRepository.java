package com.example.github.repository;

import com.example.github.model.RepositoryCommitDayActivity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface StatisticsRepository extends Repository<RepositoryCommitDayActivity, Long> {

    @Query("INSERT INTO github_repository_commit_day_activity_table(date, commits, repository_id) VALUES(:date, :commits, :repositoryId)" +
            " ON CONFLICT (date, repository_id) " +
            "DO UPDATE SET commits=:commits")
    @Modifying
    void addOrUpdateCommitDayActivity(
            @Param("date") final Long date,
            @Param("commits") final Long commits,
            @Param("repositoryId") final Long repositoryId
    );

    @Query("SELECT date, commits, repository_id FROM github_repository_commit_day_activity_table " +
            "WHERE repository_id=:repositoryId AND date > :startDate")
    Optional<List<RepositoryCommitDayActivity>> getRepositoryCommitDayActivityByRepositoryId(
            @Param("repositoryId") final Long repositoryId,
            @Param("startDate") final Long startDate
    );
}

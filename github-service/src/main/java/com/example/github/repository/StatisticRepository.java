package com.example.github.repository;

import com.example.github.model.RepositoryCommitDay;
import java.util.Optional;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface StatisticRepository extends Repository<RepositoryCommitDay, Long> {

    @Query("INSERT INTO github_repository_commit_day_activity_table(date, commits, repository_id) VALUES(:date, :commits, :repositoryId)" +
            " ON CONFLICT (date, repository_id) " +
            "DO UPDATE SET commits=:commits")
    @Modifying
    Optional<RepositoryCommitDay> addOrUpdateCommitDayActivity(
            @Param("date") final Long date,
            @Param("commits") final Long commits,
            @Param("repositoryId") final Long repositoryId
    );
}

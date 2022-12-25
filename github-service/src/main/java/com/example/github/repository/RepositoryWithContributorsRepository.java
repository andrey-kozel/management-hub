package com.example.github.repository;

import com.example.github.model.Contributor;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface RepositoryWithContributorsRepository extends Repository<Contributor, Long> {
    @Query("UPDATE github_repository_with_contributors SET contributions=:contributions WHERE repository_id=:repositoryId and contributor_id=:contributorId; " +
            "INSERT INTO github_repository_with_contributors " +
            "SELECT :repositoryId, :contributorId, :contributions " +
            "WHERE NOT EXISTS (SELECT FROM github_repository_with_contributors WHERE repository_id=:repositoryId and contributor_id=:contributorId)")
    @Modifying
    void addOrUpdateRepositoryContributors(@Param("repositoryId") Long repositoryId,
                                           @Param("contributorId") Long contributorId,
                                           @Param("contributions") Long contributions);
}

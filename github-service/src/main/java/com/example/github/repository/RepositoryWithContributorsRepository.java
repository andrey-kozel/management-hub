package com.example.github.repository;

import com.example.github.model.Contributor;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface RepositoryWithContributorsRepository extends Repository<Contributor, Long> {
    @Query("INSERT INTO github_repository_with_contributors VALUES(:repositoryId, :contributorId, :contributions) " +
            "ON CONFLICT(repository_id, contributor_id) DO UPDATE SET contributions=:contributions")
    @Modifying
    void addRepositoryContributors(@Param("repositoryId") Long repositoryId,
                                   @Param("contributorId") Long contributorId,
                                   @Param("contributions") Long contributions);
}

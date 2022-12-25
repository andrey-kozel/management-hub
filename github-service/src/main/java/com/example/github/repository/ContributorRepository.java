package com.example.github.repository;

import com.example.github.model.Contributor;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ContributorRepository extends Repository<Contributor, Long> {
    @Query("INSERT INTO github_contributors (login, account_id) VALUES (:login, :accountId) RETURNING id")
    Long addContributor(@Param("login") String login, @Param("accountId") String accountId);

    @Query("SELECT * FROM github_contributors WHERE login=:login AND account_id=:accountId")
    Optional<Contributor> getContributor(@Param("login") String login, @Param("accountId") String accountId);
}

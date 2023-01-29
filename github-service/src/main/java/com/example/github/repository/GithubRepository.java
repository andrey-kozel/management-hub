package com.example.github.repository;

import java.util.List;

import com.example.github.model.Repo;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface GithubRepository extends Repository<Repo, Long> {

  @Query("select * from github_repository where organizationid = :token")
  List<Repo> findAllRepositoryByToken(@Param("token") final long organizationId);
}

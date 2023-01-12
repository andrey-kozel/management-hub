package com.example.github.repository;

import com.example.github.model.GitHubRepository;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GitRepository extends Repository<GitHubRepository, Long> {

  @Query("SELECT * FROM github_repository WHERE organization_id = :token")
  List<GitHubRepository> findAllByToken(@Param("token") final String token);

}

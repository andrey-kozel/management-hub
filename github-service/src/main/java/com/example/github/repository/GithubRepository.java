package com.example.github.repository;


import com.example.github.model.Repo;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GithubRepository extends Repository<Repo, Long> {

    @Query("select * from github_repository where organization_id = :token" )
    List<Repo> findAllRepositoryByToken(@Param("token") String token);
}

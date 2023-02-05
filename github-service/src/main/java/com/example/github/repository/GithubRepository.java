package com.example.github.repository;

import java.util.Date;
import java.util.List;

import com.example.github.model.Repo;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface GithubRepository extends Repository<Repo, Long> {

  @Query("select * from github_repository where organization_id = :token")
  List<Repo> findAllRepositoryByToken(@Param("token") final long organizationId);

  @Query("INSERT INTO github_repository(github_id, node_id, name, full_name, is_private, description, is_fork, " +
          "created_at, updated_at, pushed_at, home_page, size, stargazers_count, watchers_count, forks_count, " +
          "open_issues_count, license, default_branch, language, permissions, is_archived, is_disabled, " +
          "is_synchronization_enabled, synchronized_at, organization_id) " +
          "VALUES(:githubId, :nodeId, :name, :fullName, :aPrivate, :description, :fork, :createdAt, :updatedAt, " +
          ":pushedAt, :homePage, :size, :stargazersCount, :watchersCount, :forksCount, :openIssuesCount, :license, " +
          ":defaultBranch, :language, cast(:permissions AS JSONB), :archived, :disabled, :synchronizationEnabled, " +
          ":synchronizedAt, :organizationId) " +
          "ON CONFLICT (github_id) WHERE github_id=:githubId DO UPDATE " +
          "SET node_id=:nodeId, name=:name, full_name=:fullName, is_private=:aPrivate, " +
          "description=:description, is_fork=:fork, created_at=:createdAt, updated_at=:updatedAt, " +
          "pushed_at=:pushedAt, home_page=:homePage, size=:size, stargazers_count=:stargazersCount, " +
          "watchers_count=:watchersCount, forks_count=:forksCount, open_issues_count=:openIssuesCount, " +
          "license=:license, default_branch=:defaultBranch, language=:language, " +
          "permissions=cast(:permissions AS JSONB), is_archived=:archived, is_disabled=:disabled, " +
          "is_synchronization_enabled=:synchronizationEnabled, synchronized_at=:synchronizedAt, " +
          "organization_id=:organizationId;")
  @Modifying
  void upsert(@Param("githubId") long githubId, @Param("nodeId") String nodeId, @Param("name") String name,
              @Param("fullName") String fullName, @Param("aPrivate") boolean aPrivate,
              @Param("description") String description, @Param("fork") boolean fork,
              @Param("createdAt") Date createdAt, @Param("updatedAt") Date updatedAt,
              @Param("pushedAt") Date pushedAt, @Param("homePage") String homePage, @Param("size") long size,
              @Param("stargazersCount") int stargazersCount, @Param("watchersCount") int watchersCount,
              @Param("forksCount") int forksCount, @Param("openIssuesCount") int openIssuesCount,
              @Param("license") String license, @Param("defaultBranch") String defaultBranch,
              @Param("language") String language, @Param("permissions") String permissions,
              @Param("archived") boolean archived, @Param("disabled") boolean disabled,
              @Param("synchronizationEnabled") boolean synchronizationEnabled,
              @Param("synchronizedAt") Date synchronizedAt, @Param("organizationId") long organizationId);
}

package com.example.github.model;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("github_repository")
@Builder
@Value
public class GitHubRepository {

  @Id
  @Column("id")
  Long id;

  @Column("node_id ")
  String nodeId;

  @Column("name")
  String name;

  @Column("full_name")
  String fullName;

  @Column("is_private")
  boolean isPrivate;

  @Column("description")
  String description;

  @Column("is_fork")
  boolean isFork;

  @Column("create_at")
  LocalDateTime createdAt;

  @Column("updated_at")
  LocalDateTime updatedAt;

  @Column("pushed_at")
  LocalDateTime pushedAt;

  @Column("home_page")
  String homePage;

  @Column("size")
  Long size;

  @Column("stargazers_count")
  int stargazersCount;

  @Column("watchers_count")
  int watchersCount;

  @Column("forks_count")
  int forksCount;

  @Column("open_issues_count")
  int openIssuesCount;

  @Column("license")
  String license;

  @Column("default_branch")
  String defaultBranch;

  @Column("language")
  String language;

  @Column("is_archived")
  boolean isArchived;

  @Column("is_disabled")
  boolean isDisabled;

  @Column("is_synchronization_enabled")
  boolean isSynchronizationEnabled;

  @Column("synchronizedAt")
  LocalDateTime synchronizedAt;

  @Column("organizationId")
  UUID organizationId;

}

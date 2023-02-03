package com.example.github.model;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@FieldDefaults(level=AccessLevel.PRIVATE)
@Table(name = "github_repository")
public class GitRepository {
    @Id
    Long id;
    Long githubId;
    String nodeId;
    String name;
    String fullName;
    Boolean isPrivate;
    String description;
    Boolean isFork;
    Date createdAt;
    Date updatedAt;
    Date pushedAt;
    String homePage;
    Long size;
    Integer stargazersCount;
    Integer watchersCount;
    Integer forksCount;
    Integer openIssuesCount;
    String license;
    String defaultBranch;
    String language;
    String permissions;
    Boolean isArchived;
    Boolean isDisabled;

    Boolean isSynchronizationEnabled;
    Date synchronizedAt;
    Long organizationId;
}

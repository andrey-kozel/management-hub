package com.example.github_worker.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Value
public class RepositoryDto {
    Long id;
    Long nodeId;
    String name;
    String fullName;
    Boolean isPrivate;
    String description;
    Boolean isFork;
    String createdAt;
    String updatedAt;
    String pushedAt;
    String homePage;
    Long size;
    Long stargazersCount;
    Long watchersCount;
    Long forksCount;
    Long openIssuesCount;
    String license;
    String defaultBranch;
    String language;
    Permissions permissions;
    Boolean isArchived;
    Boolean isDisabled;
}

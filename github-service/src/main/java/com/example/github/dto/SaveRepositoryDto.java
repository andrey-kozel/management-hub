package com.example.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Value
public class SaveRepositoryDto {
    @JsonProperty("id")
    Long githubId;
    @JsonProperty("node_id")
    String nodeId;
    String name;
    @JsonProperty("full_name")
    String fullName;
    @JsonProperty("private")
    Boolean isPrivate;
    String description;
    @JsonProperty("fork")
    Boolean isFork;
    @JsonProperty("created_at")
    Timestamp createdAt;
    @JsonProperty("updated_at")
    Timestamp updatedAt;
    @JsonProperty("pushed_at")
    Timestamp pushedAt;
    @JsonProperty("home_page")
    String homePage;
    Long size;
    @JsonProperty("stargazers_count")
    Long stargazersCount;
    @JsonProperty("watchers_count")
    Long watchersCount;
    @JsonProperty("forks_count")
    Long forksCount;
    @JsonProperty("open_issues_count")
    Long openIssuesCount;
    String license;
    @JsonProperty("default_branch")
    String defaultBranch;
    String language;
    Permissions permissions;
    @JsonProperty("archived")
    Boolean isArchived;
    @JsonProperty("disabled")
    Boolean isDisabled;
}

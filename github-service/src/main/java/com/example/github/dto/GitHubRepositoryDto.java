package com.example.github.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.util.UUID;

@Jacksonized
@Builder
@Value
public class GitHubRepositoryDto {

  Long id;
  String name;
  String language;
  boolean isSynchronizationEnabled;
  boolean isPrivate;
  LocalDateTime synchronizedAt;
  int watchersCount;
  int stargazersCount;
  UUID organizationId;

}

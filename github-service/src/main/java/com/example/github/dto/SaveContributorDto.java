package com.example.github.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Value
public class SaveContributorDto {
    String login;
    String accountId;
    Long contributions;
    Long repositoryId;
}

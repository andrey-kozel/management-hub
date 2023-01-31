package com.example.github_worker.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Value
public class SyncMessageDto {
    Long organizationId;
    String accessToken;
}

package com.example.github_worker.model;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Value
public class SyncRepositoryMessage {
    Long organizationId;
    String accessToken;
}

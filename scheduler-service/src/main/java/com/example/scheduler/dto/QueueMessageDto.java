package com.example.scheduler.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Value
public class QueueMessageDto {
    Long organizationId;
    String accessToken;
}

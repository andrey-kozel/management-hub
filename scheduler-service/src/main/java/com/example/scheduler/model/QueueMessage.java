package com.example.scheduler.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Builder
@Data
@Jacksonized
public class QueueMessage {
    Long organizationId;
    String accessToken;
}

package com.example.apigateway.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Value
public class ContributorsResponse {
    String login;
    Long id;
    Long contributions;
}

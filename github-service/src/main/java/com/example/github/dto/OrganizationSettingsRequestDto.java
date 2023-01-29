package com.example.github.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Builder
@Value
@Jacksonized
public class OrganizationSettingsRequestDto {
    long organizationId;
    String accessToken;
}

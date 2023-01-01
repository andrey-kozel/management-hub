package com.example.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Builder
@Value
@Jacksonized
public class SaveOrganizationSettingsDto {
    long organizationId;
    String accessToken;
}

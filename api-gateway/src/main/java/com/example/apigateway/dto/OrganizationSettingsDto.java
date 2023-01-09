package com.example.apigateway.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
@Data
public class OrganizationSettingsDto {
    private long organizationId;
    private String accessToken;
}

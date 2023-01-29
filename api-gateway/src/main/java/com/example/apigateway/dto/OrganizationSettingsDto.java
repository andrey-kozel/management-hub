package com.example.apigateway.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class OrganizationSettingsDto {
    private long organizationId;
    private String accessToken;
}

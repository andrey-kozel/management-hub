package com.example.apigateway.dto;


import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OrganizationSettingsResponseDto {
    String accessToken;
}

package com.example.apigateway.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;

@Builder
@Value
@Jacksonized
public class SaveOrganizationSettingsDto {
    long organizationId;
    @Length(min = 5, message = "Access token min length is 5 characters!")
    String accessToken;
}

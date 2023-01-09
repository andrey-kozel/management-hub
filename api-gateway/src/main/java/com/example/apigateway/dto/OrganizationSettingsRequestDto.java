package com.example.apigateway.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.validator.constraints.Length;

@Value
@Builder
@Jacksonized
public class OrganizationSettingsRequestDto {
    @Length(min = 5, message = "Access token min length is 5 characters!")
    String accessToken;
}

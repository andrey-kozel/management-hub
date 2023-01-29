package com.example.apigateway.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CreateOrganizationDto {
    String name;
}

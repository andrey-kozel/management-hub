package com.example.apigateway.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Builder
@Data
@Jacksonized
public class OrganizationSettings {
    long id;
    long organizationId;
    String accessToken;
}

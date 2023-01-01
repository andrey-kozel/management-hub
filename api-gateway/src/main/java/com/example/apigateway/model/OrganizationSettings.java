package com.example.apigateway.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrganizationSettings {
    long id;
    long organizationId;
    String accessToken;
}

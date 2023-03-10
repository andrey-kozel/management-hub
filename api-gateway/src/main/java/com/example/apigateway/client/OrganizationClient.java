package com.example.apigateway.client;

import com.example.apigateway.dto.CreateOrganizationDto;
import com.example.apigateway.dto.OrganizationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "organization", url = "${services.user.url}/api/v1/organizations")
public interface OrganizationClient {
    @PostMapping
    OrganizationResponse save(final CreateOrganizationDto createOrganizationDto);
}

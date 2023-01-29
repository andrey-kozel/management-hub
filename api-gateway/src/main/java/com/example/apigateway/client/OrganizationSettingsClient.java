package com.example.apigateway.client;

import com.example.apigateway.dto.OrganizationSettingsDto;
import com.example.apigateway.model.OrganizationSettings;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "github-service",
        url = "${services.github.url}/api/v1/organization/settings")
public interface OrganizationSettingsClient {
    @RequestMapping(method = RequestMethod.POST, path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    OrganizationSettings saveAccessToken(@RequestBody OrganizationSettingsDto dto);

    @RequestMapping(method = RequestMethod.GET, path = "/{organizationId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    String getAccessToken(final @PathVariable("organizationId") Long organizationId);
}

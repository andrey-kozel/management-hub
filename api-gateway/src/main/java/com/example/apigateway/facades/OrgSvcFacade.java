package com.example.apigateway.facades;

import com.example.apigateway.dto.OrganizationSettingsDto;
import com.example.apigateway.model.OrganizationSettings;
import com.example.apigateway.service.OrganizationSettingsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrgSvcFacade {

    private final OrganizationSettingsService service;

    public OrganizationSettings save(OrganizationSettingsDto dto) {
        return service.save(dto);
    }

    public String getAccessToken(long organizationId) {
        log.info("Token had been requested. OrganizationId=[{}]", organizationId);
        return service.getAccessToken(organizationId);
    }
}

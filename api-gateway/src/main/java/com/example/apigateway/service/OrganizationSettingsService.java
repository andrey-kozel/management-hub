package com.example.apigateway.service;

import com.example.apigateway.client.OrganizationSettingsClient;
import com.example.apigateway.dto.SaveOrganizationSettingsDto;
import com.example.apigateway.model.OrganizationSettings;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Data
@RequiredArgsConstructor
@Slf4j
public class OrganizationSettingsService {
    private final OrganizationSettingsClient client;

    public OrganizationSettings save(SaveOrganizationSettingsDto dto) {
        return client.saveAccessToken(dto);
    }

    public String getAccessToken(long organizationId) {
        return client.getAccessToken(organizationId);
    }
}

package com.example.facades;

import com.example.client.dto.SaveOrganizationSettingsDto;
import com.example.converter.OrganizationSettingsConverter;
import com.example.model.OrganizationSettings;
import com.example.service.OrganizationSettingsService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@RequiredArgsConstructor
@Component
public class OrganizationSettingsFacade {
    private final OrganizationSettingsService service;
    private final OrganizationSettingsConverter converter;

    public OrganizationSettings buildOrganizationSettings(SaveOrganizationSettingsDto dto) {
        OrganizationSettings organizationSettings = service.find(dto.getOrganizationId());

        if (organizationSettings != null) {
            organizationSettings.setAccessToken(dto.getAccessToken());
        } else {
            organizationSettings = converter.dtoToOrganizationSettings(dto);
        }
        return organizationSettings;
    }

    public OrganizationSettings save(OrganizationSettings organizationSettings) {
        return service.save(organizationSettings);
    }
}

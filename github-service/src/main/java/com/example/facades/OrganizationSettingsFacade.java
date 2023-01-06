package com.example.facades;

import com.example.dto.SaveOrganizationSettingsDto;
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
        OrganizationSettings orgSvc = service.save(organizationSettings);
        orgSvc.setAccessToken(
                        cutToken(orgSvc.getAccessToken()));
        return organizationSettings;
    }

    public String getToken(long organizationId) {
        OrganizationSettings organizationSettings = service.find(organizationId);
        String token = organizationSettings.getAccessToken();
        if (!token.isEmpty()) {
            return cutToken(token);
        }
        return "";
    }

    public String cutToken(String token) {
        return token.substring(token.length() - 5);
    }
}

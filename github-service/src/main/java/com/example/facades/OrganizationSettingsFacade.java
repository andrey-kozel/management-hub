package com.example.facades;

import com.example.converter.OrganizationSettingsConverter;
import com.example.model.OrganizationSettings;
import com.example.service.OrganizationSettingsService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@RequiredArgsConstructor
@Component
public class OrganizationSettingsFacade {
    private final OrganizationSettingsService service;
    private final OrganizationSettingsConverter converter;

    @Value("${token.return-length}")
    private int tokenLength;

    public OrganizationSettings save(String token, long organizationId) {
        service.save(token, organizationId);
        return service.getSettings(organizationId).orElse(null);
    }

    public String getSettings(long organizationId) {
        return service.getSettings(organizationId)
                .map(OrganizationSettings::getAccessToken)
                .map(this::cutToken)
                .orElse("");
    }

    public String cutToken(String token) {
        return token.substring(token.length() - tokenLength);
    }
}

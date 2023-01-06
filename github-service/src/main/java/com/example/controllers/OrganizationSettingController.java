package com.example.controllers;

import com.example.dto.SaveOrganizationSettingsDto;
import com.example.facades.OrganizationSettingsFacade;
import com.example.model.OrganizationSettings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/github-service/organization-settings/access-token")
@RequiredArgsConstructor
public class OrganizationSettingController {
    private final OrganizationSettingsFacade facade;
    @RequestMapping(method = RequestMethod.POST, path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrganizationSettings saveAccessToken(final @RequestBody SaveOrganizationSettingsDto dto) {
        OrganizationSettings organizationSettings = facade.buildOrganizationSettings(dto);
        return facade.save(organizationSettings);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/existing", consumes = MediaType.APPLICATION_JSON_VALUE)
    String getAccessToken(@RequestBody Long organizationId) {
        return facade.getToken(organizationId);
    }
}

package com.example.controllers;

import com.example.dto.OrganizationSettingsRequestDto;
import com.example.facades.OrganizationSettingsFacade;
import com.example.model.OrganizationSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/organization/settings")
@RequiredArgsConstructor
public class OrganizationSettingController {

    private final OrganizationSettingsFacade facade;
    @RequestMapping(method = RequestMethod.POST, path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrganizationSettings saveAccessToken(final @RequestBody OrganizationSettingsRequestDto dto) {
        return facade.save(dto.getAccessToken(), dto.getOrganizationId());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{organizationId}")
    String getAccessToken(final @PathVariable("organizationId") Long organizationId) {
        return facade.getSettings(organizationId);
    }
}

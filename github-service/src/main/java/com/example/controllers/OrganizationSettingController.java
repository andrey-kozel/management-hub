package com.example.controllers;

import com.example.client.dto.SaveOrganizationSettingsDto;
import com.example.facades.OrganizationSettingsFacade;
import com.example.model.OrganizationSettings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/github-service/organization-settings/access-token")
@RequiredArgsConstructor
@Slf4j
public class OrganizationSettingController {
    private final OrganizationSettingsFacade facade;

    @RequestMapping(method = RequestMethod.POST, path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrganizationSettings> saveAccessToken(@RequestBody SaveOrganizationSettingsDto dto) {
        OrganizationSettings organizationSettings = facade.buildOrganizationSettings(dto);
        return ResponseEntity.ok(facade.save(organizationSettings));
    }
}

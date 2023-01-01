package com.example.controllers;

import com.example.dto.GetAccessTokenDto;
import com.example.dto.SaveOrganizationSettingsDto;
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
    @CrossOrigin("http://localhost:3000")
    @RequestMapping(method = RequestMethod.POST, path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrganizationSettings saveAccessToken(final @RequestBody SaveOrganizationSettingsDto dto) {
        OrganizationSettings organizationSettings = facade.buildOrganizationSettings(dto);
        return facade.save(organizationSettings);
    }
    @CrossOrigin("http://localhost:3000")
    @RequestMapping(method = RequestMethod.GET, path = "/{organizationId}")
    public ResponseEntity<GetAccessTokenDto> getAccessToken(final @PathVariable Long organizationId) {
        return ResponseEntity.ok(facade.getToken(organizationId));
    }
}

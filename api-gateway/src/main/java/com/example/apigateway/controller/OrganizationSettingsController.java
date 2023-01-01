package com.example.apigateway.controller;

import com.example.apigateway.dto.SaveOrganizationSettingsDto;
import com.example.apigateway.model.OrganizationSettings;
import com.example.apigateway.service.OrganizationSettingsService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Data
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/github-service/organization-settings/access-token")
public class OrganizationSettingsController {
    private final OrganizationSettingsService service;
    @RequestMapping(method = RequestMethod.POST, path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrganizationSettings> saveAccessToken(@RequestBody SaveOrganizationSettingsDto dto) {
        return ResponseEntity.ok(service.save(dto));
    }
}

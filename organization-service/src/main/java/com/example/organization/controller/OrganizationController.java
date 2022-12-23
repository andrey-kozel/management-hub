package com.example.organization.controller;

import com.example.organization.converter.OrganizationConverter;
import com.example.organization.dto.OrganizationResponse;
import com.example.organization.model.Organization;
import com.example.organization.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/organizations")
@RequiredArgsConstructor
public class OrganizationController {
    private final OrganizationService organizationService;
    private final OrganizationConverter organizationConverter;

    @PostMapping
    public OrganizationResponse save(@RequestParam final String organizationName) {
        Organization organization = organizationService.saveOrganization(organizationName);
        return organizationConverter.toDto(organization);
    }
}

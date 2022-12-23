package com.example.user.controller;

import com.example.user.converter.OrganizationConverter;
import com.example.user.dto.OrganizationResponse;
import com.example.user.model.Organization;
import com.example.user.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
package com.example.organization.controller;

import com.example.organization.model.Organization;
import com.example.organization.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/organizations")
@RequiredArgsConstructor
public class OrganizationController {
    private final OrganizationService organizationService;

    @PostMapping("/save")
    public void save(@RequestParam final String organizationName) {
        organizationService.saveOrganization(organizationName);
    }
}

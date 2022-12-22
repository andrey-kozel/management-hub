package com.example.apigateway.controller;

import com.example.apigateway.client.OrganizationClient;
import com.nimbusds.openid.connect.sdk.assurance.evidences.Organization;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/organizations")
@RequiredArgsConstructor
public class OrganizationController {
    private final OrganizationClient organizationClient;

    @PostMapping("/save")
    public Organization save(@RequestParam final String organizationName) {
        return organizationClient.save(organizationName);
    }
}

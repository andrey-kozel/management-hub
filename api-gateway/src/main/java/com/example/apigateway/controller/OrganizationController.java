package com.example.apigateway.controller;

import com.example.apigateway.client.UserClient;
import com.example.apigateway.dto.OrganizationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/organizations")
@RequiredArgsConstructor
public class OrganizationController {
    private final UserClient userClient;

    @PostMapping("/save")
    public OrganizationResponse save(@RequestParam final String organizationName) {
        return userClient.save(organizationName);
    }
}

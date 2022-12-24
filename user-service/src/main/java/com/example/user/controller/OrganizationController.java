package com.example.user.controller;

import com.example.user.converter.OrganizationConverter;
import com.example.user.dto.CreateOrganizationDto;
import com.example.user.dto.OrganizationResponse;
import com.example.user.facade.OrganizationFacade;
import com.example.user.model.Organization;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/organizations")
@RequiredArgsConstructor
public class OrganizationController {
    private final OrganizationConverter organizationConverter;
    private final OrganizationFacade organizationFacade;

    @PostMapping
    public OrganizationResponse save(@RequestBody final CreateOrganizationDto createOrganizationDto) {
        Organization organization = organizationFacade.save(createOrganizationDto.getName());
        return organizationConverter.toDto(organization);
    }
}

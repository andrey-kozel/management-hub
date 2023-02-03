package com.example.user.controller;

import com.example.user.converter.OrganizationConverter;
import com.example.user.dto.CreateOrganizationDto;
import com.example.user.dto.OrganizationResponse;
import com.example.user.facade.OrganizationFacade;
import com.example.user.model.Organization;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/organizations")
@RequiredArgsConstructor
public class OrganizationController {
    private static final int PAGE_SIZE = 20;

    private final OrganizationConverter organizationConverter;
    private final OrganizationFacade organizationFacade;

    @PostMapping
    public OrganizationResponse save(@RequestBody final CreateOrganizationDto createOrganizationDto) {
        Organization organization = organizationFacade.save(createOrganizationDto.getName());
        return organizationConverter.toDto(organization);
    }

    @GetMapping
    public List<OrganizationResponse> getFiltered(@RequestParam final Long idOffset) {
        List<Organization> organizations = organizationFacade.getFiltered(idOffset, PAGE_SIZE);

        return organizations.stream().map(organizationConverter::toDto).collect(Collectors.toList());
    }
}

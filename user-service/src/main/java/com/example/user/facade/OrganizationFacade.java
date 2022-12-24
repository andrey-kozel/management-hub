package com.example.user.facade;

import com.example.user.model.Organization;
import com.example.user.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrganizationFacade {
    private final OrganizationService organizationService;

    public Organization save(final String organizationName) {
        return organizationService.saveOrganization(organizationName);
    }
}

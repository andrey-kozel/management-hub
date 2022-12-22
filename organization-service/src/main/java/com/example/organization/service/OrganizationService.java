package com.example.organization.service;

import com.example.organization.model.Organization;
import com.example.organization.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganizationService {
    private final OrganizationRepository organizationRepository;
    public Organization saveOrganization(String organizationName) {
        Optional<Organization> organization = organizationRepository.get(organizationName);

        if (organization.isEmpty()) {
            int organizationId = organizationRepository.save(organizationName);
            return Organization
                    .builder()
                    .id(organizationId)
                    .name(organizationName)
                    .build();
        }
        return organization.get();
    }
}

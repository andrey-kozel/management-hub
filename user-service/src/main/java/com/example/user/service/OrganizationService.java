package com.example.user.service;

import java.util.List;
import java.util.Optional;

import com.example.user.model.Organization;
import com.example.user.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationService {
    private final OrganizationRepository organizationRepository;

    public Organization saveOrganization(String organizationName) {
        Optional<Organization> organization = organizationRepository.getByName(organizationName);

        if (organization.isEmpty()) {
            Long organizationId = organizationRepository.save(organizationName);
            return Organization
                    .builder()
                    .id(organizationId)
                    .name(organizationName)
                    .build();
        }
        return organization.get();
    }

    public List<Organization> getAll() {
        return organizationRepository.getAll();
    }
}

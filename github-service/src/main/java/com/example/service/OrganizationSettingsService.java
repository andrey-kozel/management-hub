package com.example.service;

import com.example.model.OrganizationSettings;
import com.example.repository.OrganizationSettingsRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Data
@RequiredArgsConstructor
@Slf4j
public class OrganizationSettingsService {
    private final OrganizationSettingsRepository repository;

    public OrganizationSettings save(OrganizationSettings organizationSettings) {
        return repository.save(organizationSettings);
    }

    public OrganizationSettings find(long id) {
        return repository.findByOrganizationId(id);
    }
}

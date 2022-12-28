package com.example.repository;

import com.example.model.OrganizationSettings;
import org.springframework.data.repository.Repository;

public interface OrganizationSettingsRepository extends Repository<OrganizationSettings, Long> {
    OrganizationSettings save(OrganizationSettings organizationSettings);

    OrganizationSettings findByOrganizationId(long id);
}

package com.example.github.service;

import com.example.github.model.OrganizationSettings;
import com.example.github.repository.OrganizationSettingsRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrganizationSettingsService {
    private final OrganizationSettingsRepository repository;

    @Cacheable(value = "organization-settings", key = "#id")
    public Optional<OrganizationSettings> getSettings(long id) {
        log.info("Loading settings for organization. OrganizationId=[{}]", id);
        return repository.findByOrganizationId(id);
    }

    @CacheEvict(value = "organization-settings", key="#organizationId")
    public void save(String token, long organizationId) {
        repository.update(token, organizationId);
    }
}

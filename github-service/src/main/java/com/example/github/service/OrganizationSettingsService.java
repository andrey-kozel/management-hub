package com.example.github.service;

import com.example.github.model.OrganizationSettings;
import com.example.github.repository.OrganizationSettingsRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class OrganizationSettingsService {
    private final OrganizationSettingsRepository repository;

    public Optional<OrganizationSettings> getSettings(long id) {
        return repository.findByOrganizationId(id);
    }

    public void save(String token, long organizationId) {
        repository.update(token, organizationId);
    }
}

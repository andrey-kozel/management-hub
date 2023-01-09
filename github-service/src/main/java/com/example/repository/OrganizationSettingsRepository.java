package com.example.repository;

import com.example.model.OrganizationSettings;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface OrganizationSettingsRepository extends Repository<OrganizationSettings, Long> {
    Optional<OrganizationSettings> findByOrganizationId(long id);

    @Modifying
    @Query("insert into organization_settings (organization_id, access_token) " +
            "values (:organizationId,:token) on conflict (organization_id) do update set access_token =:token;")
    @Transactional
    void update(@Param("token") String token, @Param("organizationId") long organizationId);
}

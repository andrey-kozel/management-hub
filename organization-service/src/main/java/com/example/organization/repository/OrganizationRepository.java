package com.example.organization.repository;

import com.example.organization.model.Organization;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrganizationRepository extends Repository<Organization, Long> {

    @Query("SELECT * FROM organizations WHERE name=:organizationName")
    Optional<Organization> get(@Param("organizationName") String organizationName);

    @Query("INSERT INTO organizations (name) VALUES (:organizationName) RETURNING id")
    int save(@Param("organizationName") String organizationName);
}

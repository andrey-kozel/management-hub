package com.example.user.repository;

import java.util.List;
import java.util.Optional;

import com.example.user.model.Organization;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface OrganizationRepository extends Repository<Organization, Long> {

    @Query("SELECT * FROM organizations WHERE name=:organizationName")
    Optional<Organization> getByName(@Param("organizationName") String organizationName);

    @Query("INSERT INTO organizations (name) VALUES (:organizationName) RETURNING id")
    Long save(@Param("organizationName") String organizationName);

    @Query("SELECT * FROM organizations")
    List<Organization> getAll();
}

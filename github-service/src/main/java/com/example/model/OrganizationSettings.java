package com.example.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table("organization_settings")
@Builder
@Data
public class OrganizationSettings {
    @Id
    long id;

    @Column("organization_id")
    long organizationId;

    @Column("access_token")
    String accessToken;
}

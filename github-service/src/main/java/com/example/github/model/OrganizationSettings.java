package com.example.github.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table("organization_settings")
@Builder
@Data
public class OrganizationSettings implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    long id;

    @Column("organization_id")
    long organizationId;

    @Column("access_token")
    String accessToken;
}

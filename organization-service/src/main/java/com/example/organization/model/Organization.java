package com.example.organization.model;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("organizations")
@Builder
@Value
public class Organization {
    @Id
    int id;

    @Column("name")
    String name;
}

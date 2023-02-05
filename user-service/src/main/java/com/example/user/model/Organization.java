package com.example.user.model;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("organizations")
@Builder
@Value
public class Organization {
    @Id
    Long id;

    @Column("name")
    String name;
}

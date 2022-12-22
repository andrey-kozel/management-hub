package com.example.organization.model;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("organization")
@Builder
@Value
public class Organization {
    @Id
    int id;
    String name;
}

package com.example.github.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@Table(name = "github_repository")
public class Repo {

    Long id;
    String node_id;
    String name;
    Date created_at;
    Long size;
    UUID organization_id;
}

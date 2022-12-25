package com.example.github.model;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Jacksonized
@Builder
@Value
@Table("github_contributors")
public class Contributor {
    Long id;
    String login;
    @Column("account_id")
    String accountId;
}

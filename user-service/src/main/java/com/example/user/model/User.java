package com.example.user.model;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
@Builder
@Value
public class User {

  @Id
  String id;

  @Column("account_id")
  String accountId;

  @Column("name")
  String name;

  String provider;

  @Column("organization_id")
  Long organizationId;
}

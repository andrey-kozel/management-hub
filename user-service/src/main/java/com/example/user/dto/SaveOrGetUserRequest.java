package com.example.user.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Value
public class SaveOrGetUserRequest {

  String accountId;
  String name;
  String provider;

}

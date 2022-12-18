package com.example.apigateway.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class SaveOrGetUserRequest {

  String id;
  String username;
  String name;

}

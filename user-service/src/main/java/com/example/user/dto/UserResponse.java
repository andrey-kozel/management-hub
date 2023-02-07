package com.example.user.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Value
public class UserResponse {

    Long id;
    Long organizationId;
    String login;
    String accountId;
}

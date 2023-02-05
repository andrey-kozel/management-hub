package com.example.github.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Value
public class Permissions {
    Boolean admin;
    Boolean maintain;
    Boolean push;
    Boolean triage;
    Boolean pull;
}

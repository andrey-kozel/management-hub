package com.example.github.model;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Value
public class Contributor {
    String login;
    String id;
    int contributions;
}

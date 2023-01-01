package com.example.apigateway.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GetAccessTokenDto {
    String accessToken;
}

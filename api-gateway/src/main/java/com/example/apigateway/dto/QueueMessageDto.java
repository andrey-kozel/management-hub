package com.example.apigateway.dto;

import lombok.Data;

@Data
public class QueueMessageDto {
    Long organisationId;
    String accessToken;
}

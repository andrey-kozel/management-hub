package com.example.apigateway.converter;

import com.example.apigateway.dto.OrganizationSettingsDto;
import com.example.apigateway.dto.OrganizationSettingsRequestDto;
import org.mapstruct.Mapper;

@Mapper
public interface OrganizationSettingsConverter {
    OrganizationSettingsDto requestToDto(OrganizationSettingsRequestDto dto);
}

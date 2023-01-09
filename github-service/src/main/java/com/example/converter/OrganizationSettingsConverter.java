package com.example.converter;

import com.example.dto.OrganizationSettingsRequestDto;
import com.example.model.OrganizationSettings;
import org.mapstruct.Mapper;

@Mapper
public interface OrganizationSettingsConverter {
    OrganizationSettings dtoToOrganizationSettings(OrganizationSettingsRequestDto dto);
}

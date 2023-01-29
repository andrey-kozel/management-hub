package com.example.github.converter;

import com.example.github.dto.OrganizationSettingsRequestDto;
import com.example.github.model.OrganizationSettings;
import org.mapstruct.Mapper;

@Mapper
public interface OrganizationSettingsConverter {
    OrganizationSettings dtoToOrganizationSettings(OrganizationSettingsRequestDto dto);
}

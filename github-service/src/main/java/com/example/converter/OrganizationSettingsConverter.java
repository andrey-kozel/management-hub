package com.example.converter;

import com.example.client.dto.SaveOrganizationSettingsDto;
import com.example.model.OrganizationSettings;
import org.mapstruct.Mapper;

@Mapper
public interface OrganizationSettingsConverter {
    OrganizationSettings dtoToOrganizationSettings(SaveOrganizationSettingsDto dto);
}

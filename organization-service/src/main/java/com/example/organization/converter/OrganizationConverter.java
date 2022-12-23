package com.example.organization.converter;

import com.example.organization.dto.OrganizationResponse;
import com.example.organization.model.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrganizationConverter {

  @Mapping(target = "id")
  OrganizationResponse toDto(final Organization savedOrganization);
}

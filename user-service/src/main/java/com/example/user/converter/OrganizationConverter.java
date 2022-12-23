package com.example.user.converter;

import com.example.user.dto.OrganizationResponse;
import com.example.user.model.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrganizationConverter {

  @Mapping(target = "id")
  OrganizationResponse toDto(final Organization savedOrganization);
}

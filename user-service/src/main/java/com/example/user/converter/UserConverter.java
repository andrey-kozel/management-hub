package com.example.user.converter;

import com.example.user.dto.SaveOrGetUserRequest;
import com.example.user.dto.SaveUserResponse;
import com.example.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserConverter {

  @Mapping(target = "accountId", source = "username")
  User fromDto(final SaveOrGetUserRequest request);

  SaveUserResponse toDto(final User savedUser);
}

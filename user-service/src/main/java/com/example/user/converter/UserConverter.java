package com.example.user.converter;

import com.example.user.dto.SaveOrGetUserRequest;
import com.example.user.dto.SaveUserResponse;
import com.example.user.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserConverter {

  User fromDto(final SaveOrGetUserRequest request);

  SaveUserResponse toDto(final User savedUser);
}

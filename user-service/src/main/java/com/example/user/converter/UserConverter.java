package com.example.user.converter;

import com.example.user.dto.SaveOrGetUserRequest;
import com.example.user.dto.UserResponse;
import com.example.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserConverter {

    User fromDto(final SaveOrGetUserRequest request);

    @Mapping(target = "login", source = "name")
    UserResponse toDto(final User savedUser);
}

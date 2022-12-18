package com.example.user.controller;

import com.example.user.converter.UserConverter;
import com.example.user.dto.SaveOrGetUserRequest;
import com.example.user.dto.SaveUserResponse;
import com.example.user.model.User;
import com.example.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final UserConverter userConverter;
  private final UserService userService;

  @PostMapping
  public SaveUserResponse saveOrGet(@RequestBody final SaveOrGetUserRequest request) {
    final User user = userConverter.fromDto(request);
    final User savedUser = userService.saveOrGet(user);
    return userConverter.toDto(savedUser);
  }

}

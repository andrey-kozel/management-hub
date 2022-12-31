package com.example.user.controller;

import com.example.user.converter.UserConverter;
import com.example.user.dto.SaveOrGetUserRequest;
import com.example.user.dto.UserResponse;
import com.example.user.facade.UserFacade;
import com.example.user.model.User;
import com.example.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserConverter userConverter;
    private final UserFacade userFacade;

    @PostMapping
    public UserResponse saveOrGet(@RequestBody final SaveOrGetUserRequest request) {
      final User user = userConverter.fromDto(request);
      User receivedUser = userFacade.saveOrGet(user);
      return userConverter.toDto(receivedUser);
    }

    @GetMapping("{userId}")
    public UserResponse get(@PathVariable("userId") final Long userId) {
        final User user = userService.get(userId);
        return userConverter.toDto(user);
    }
}

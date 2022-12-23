package com.example.apigateway.client;

import com.example.apigateway.dto.SaveOrGetUserRequest;
import com.example.apigateway.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "user", url = "${services.user.url}/api/v1")
public interface UserClient {

  @PostMapping
  UserResponse saveOrGet(final SaveOrGetUserRequest saveOrGetUserRequest);

  @GetMapping("/users/{userId}")
  UserResponse get(@PathVariable("userId") final Long userId);
}

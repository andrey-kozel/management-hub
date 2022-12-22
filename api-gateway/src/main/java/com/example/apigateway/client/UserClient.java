package com.example.apigateway.client;

import com.example.apigateway.dto.SaveOrGetUserRequest;
import com.example.apigateway.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user", url = "${services.user.url}/api/v1/users")
public interface UserClient {

  @PostMapping
  UserResponse saveOrGet(final SaveOrGetUserRequest saveOrGetUserRequest);

  @PostMapping("{userId}/change/organization")
  void changeOrganization(@PathVariable("userId") final Long userId, @RequestParam("organizationId") final int organizationId);

  @GetMapping("{userId}")
  UserResponse get(@PathVariable("userId") final Long userId);
}

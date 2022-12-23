package com.example.apigateway.client;

import com.example.apigateway.dto.OrganizationResponse;
import com.example.apigateway.dto.SaveOrGetUserRequest;
import com.example.apigateway.dto.UserResponse;
import org.aspectj.weaver.ast.Or;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user", url = "${services.user.url}/api/v1/users")
public interface UserClient {

  @PostMapping
  UserResponse saveOrGet(final SaveOrGetUserRequest saveOrGetUserRequest);

  @PostMapping("/organizations/{organizationId}/users")
  UserResponse changeOrganization(@PathVariable("organizationId") final int organizationId,
                                  @RequestParam("userId") final Long userId);

  @GetMapping("{userId}")
  UserResponse get(@PathVariable("userId") final Long userId);

  @PostMapping("/organizations")
  OrganizationResponse save(@RequestParam("organizationName") final String organizationName);
}

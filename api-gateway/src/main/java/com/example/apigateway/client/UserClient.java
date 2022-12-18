package com.example.apigateway.client;

import com.example.apigateway.dto.SaveOrGetUserRequest;
import com.example.apigateway.dto.SaveUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "user", url = "${services.user.url}/api/v1/users")
public interface UserClient {

  @PostMapping
  SaveUserResponse saveOrGet(final SaveOrGetUserRequest saveOrGetUserRequest);

}

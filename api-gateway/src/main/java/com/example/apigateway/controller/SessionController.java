package com.example.apigateway.controller;

import com.example.apigateway.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sessions")
@RequiredArgsConstructor
public class SessionController {

  private final UserClient userClient;

  @GetMapping
  public void getCurrentUser() {
    Object principal = SecurityContextHolder
      .getContext()
      .getAuthentication()
      .getPrincipal();
  }

}

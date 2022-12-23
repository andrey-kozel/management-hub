package com.example.apigateway.controller;

import com.example.apigateway.client.UserClient;
import com.example.apigateway.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserClient userClient;

    @PostMapping("/organizations/{organizationId}/users")
    UserResponse changeOrganization(@PathVariable final int organizationId, @RequestParam final Long userId){
        return userClient.changeOrganization(organizationId, userId);
    }
}

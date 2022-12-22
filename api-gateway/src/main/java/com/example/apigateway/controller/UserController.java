package com.example.apigateway.controller;

import com.example.apigateway.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserClient userClient;

    @PostMapping("{userId}/change/organization")
    void changeOrganization(@PathVariable final Long userId, @RequestParam final int organizationId){
        userClient.changeOrganization(userId, organizationId);
    }
}

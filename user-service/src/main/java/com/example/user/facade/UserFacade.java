package com.example.user.facade;

import java.util.Optional;

import com.example.user.model.Organization;
import com.example.user.model.User;
import com.example.user.service.OrganizationService;
import com.example.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {
    private final UserService userService;
    private final OrganizationService organizationService;

    public User get(final Long userId) {
        return userService.get(userId);
    }

    public User saveOrGet(User user) {
        Optional<User> receivedUser = userService.getByAccountIdAndProvider(user.getAccountId(), user.getProvider());

        if (receivedUser.isEmpty()) {
            Organization organization = organizationService.saveOrganization(user.getAccountId());
            user.setOrganizationId(organization.getId());
            return userService.saveOrGet(user);
        }
        return receivedUser.get();
    }
}

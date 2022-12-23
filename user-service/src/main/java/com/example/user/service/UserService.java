package com.example.user.service;

import com.example.user.model.Organization;
import com.example.user.model.User;
import com.example.user.repository.OrganizationRepository;
import com.example.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final OrganizationRepository organizationRepository;

  public User saveOrGet(User user) {
    return userRepository.getByAccountIdAndProvider(user.getAccountId(), user.getProvider())
      .orElseGet(() -> {
        int organizationId = organizationRepository.save(user.getAccountId());
        user.setOrganizationId(organizationId);
        return userRepository.save(user);
      });
  }

  public User get(final Long userId) {
    return userRepository.get(userId);
  }

  public User changeOrganization(final int organizationId, final Long userId) {
    return userRepository.changeOrganization(organizationId, userId);
  }
}

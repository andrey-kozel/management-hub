package com.example.user.service;

import com.example.user.model.User;
import com.example.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public User saveOrGet(final User user) {
    return userRepository.getByUsernameAndProvider(user.getAccountId(), user.getProvider())
      .orElseGet(() -> userRepository.save(user));
  }

}

package com.example.user.service;

import com.example.user.model.User;
import com.example.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public User saveOrGet(User user) {
    return userRepository.getByAccountIdAndProvider(user.getAccountId(), user.getProvider())
      .orElseGet(() -> userRepository.save(user));
  }

  public User get(final Long userId) {
    return userRepository.get(userId);
  }

  public Optional<User> getByAccountIdAndProvider(final String accountId, final String provider) {
    return userRepository.getByAccountIdAndProvider(accountId, provider);
  }

}

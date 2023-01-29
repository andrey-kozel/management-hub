package com.example.user.service;

import java.util.Optional;

import com.example.user.model.User;
import com.example.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> getByAccountIdAndProvider(final String accountId, final String provider) {
        return userRepository.getByAccountIdAndProvider(accountId, provider);
    }

    public User saveOrGet(final User user) {
        return userRepository.getByAccountIdAndProvider(user.getAccountId(), user.getProvider())
                .orElseGet(() -> userRepository.save(user));
    }

    public User get(final Long userId) {
        return userRepository.get(userId);
    }
}

package com.example.user.repository;

import com.example.user.model.User;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<Long, User> {

  User save(final User user);

}

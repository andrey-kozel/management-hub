package com.example.user.repository;

import java.util.Optional;

import com.example.user.model.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends Repository<User, Long> {

  User save(final User user);

  @Query("SELECT * FROM users where account_id = :accountId and provider = :provider")
  Optional<User> getByAccountIdAndProvider(
    @Param("accountId") final String accountId,
    @Param("provider") final String provider
  );

  @Query("SELECT * FROM users where id = :id")
  User get(@Param("id") Long userId);
}

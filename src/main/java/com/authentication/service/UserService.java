package com.authentication.service;

import com.authentication.entity.UserEntity;

import java.util.Optional;

public interface UserService {
    UserEntity register(String username, String password);
    Optional<UserEntity> findByUsername(String username);
}

package com.assessment.service;

import java.util.Optional;

import com.assessment.model.User;

public interface UserService {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    User save(User user);
}

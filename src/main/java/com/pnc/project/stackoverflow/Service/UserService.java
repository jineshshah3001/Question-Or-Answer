package com.pnc.project.stackoverflow.Service;

import com.pnc.project.stackoverflow.Entity.User;

import java.util.Optional;

public interface UserService {

    User addNewUser(User user);

    Optional<User> findById(String id);

    User findByEmail(String email);

    String get_SHA_512_SecurePassword(String passwordToHash, String   salt);
}

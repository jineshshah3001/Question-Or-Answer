package com.pnc.project.stackoverflow.Service;

import com.pnc.project.stackoverflow.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void addNewUser(User user);

    List<User> finaAll();

    Optional<User> findById(String id);

    User findByEmail(String email);
}

package com.pnc.project.stackoverflow.Repository;

import com.pnc.project.stackoverflow.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);
}

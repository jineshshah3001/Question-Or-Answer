package com.pnc.project.stackoverflow.Controller;

import com.pnc.project.stackoverflow.Entity.User;
import com.pnc.project.stackoverflow.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("stackoverflow/signup")
    public void addNewUser(@RequestBody User user){
        String encryptedPassword = userService.get_SHA_512_SecurePassword(user.getPassword(), "salt");
        user.setPassword(encryptedPassword);
        userService.addNewUser(user);
    }


    @GetMapping("stackoverflow/users/{id}")
    public Optional<User> findOneUser(@PathVariable String id){
            return userService.findById(id);
    }
}

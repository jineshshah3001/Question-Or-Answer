package com.pnc.project.stackoverflow.Controller;

import com.pnc.project.stackoverflow.Entity.User;
import com.pnc.project.stackoverflow.Service.JwtUserDetailsService;
import com.pnc.project.stackoverflow.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping("stackoverflow/signup")
    public ResponseEntity<?> addNewUser(@RequestBody User user){
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userService.addNewUser(user));
    }


    @GetMapping("stackoverflow/users/{id}")
    public Optional<User> findOneUser(@PathVariable String id){
            return userService.findById(id);
    }
}

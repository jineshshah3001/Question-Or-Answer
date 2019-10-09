package com.pnc.project.stackoverflow.Controller;

import com.pnc.project.stackoverflow.Entity.Login;
import com.pnc.project.stackoverflow.Entity.User;
import com.pnc.project.stackoverflow.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/stackoverflow/login")
    public ResponseEntity<Object> validateLogin(@RequestBody Login login){
        User user = userRepository.findByEmail(login.getEmail());
        if( (userRepository.findByEmail(login.getEmail()) != null) && (user.getPassword().equals(login.getPassword()))){
                return new ResponseEntity<>(HttpStatus.OK);
        }
        else
        {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}

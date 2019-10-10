package com.pnc.project.stackoverflow.Controller;

import com.pnc.project.stackoverflow.Entity.Login;
import com.pnc.project.stackoverflow.Entity.User;
import com.pnc.project.stackoverflow.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {


    @Autowired
    private UserService userService;

    @PostMapping("/stackoverflow/login")
    public ResponseEntity<Object> validateLogin(@RequestBody Login login){
        User user = userService.findByEmail(login.getEmail());

        if( (user != null) && (user.getPassword().equals(userService.get_SHA_512_SecurePassword(login.getPassword(),"salt"))))
        {
                return new ResponseEntity<>(HttpStatus.OK);
        }
        else
        {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}

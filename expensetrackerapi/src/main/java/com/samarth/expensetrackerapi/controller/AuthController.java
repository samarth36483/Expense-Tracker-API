package com.samarth.expensetrackerapi.controller;

import com.samarth.expensetrackerapi.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/user")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<String> login(){
        return new ResponseEntity<String>("Login successfull", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(UserDTO user){
        return new ResponseEntity<>("Registration successfull", HttpStatus.CREATED);
    }
}

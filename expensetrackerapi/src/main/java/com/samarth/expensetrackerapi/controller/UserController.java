package com.samarth.expensetrackerapi.controller;

import com.samarth.expensetrackerapi.dto.RegisterDTO;
import com.samarth.expensetrackerapi.models.User;
import com.samarth.expensetrackerapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<User> readUser(){
        return new ResponseEntity<User>(userService.readUser(), HttpStatus.OK);
    }

    @PutMapping("/profile")
    public ResponseEntity<User> updateUser(@RequestBody RegisterDTO dto){
        return new ResponseEntity<User>(userService.updateUser(dto), HttpStatus.OK);
    }

    @DeleteMapping("/deactivate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteUser(){
        userService.deletUser();
        return "User successfully deleted";
    }
}

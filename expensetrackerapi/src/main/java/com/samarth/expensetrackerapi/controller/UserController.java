package com.samarth.expensetrackerapi.controller;

import com.samarth.expensetrackerapi.dto.UserDTO;
import com.samarth.expensetrackerapi.models.User;
import com.samarth.expensetrackerapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO user){
        ResponseEntity<User> addedUser = new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
        //return userService.addUser(user);
        return addedUser;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> readUser(@PathVariable Long id){
        return new ResponseEntity<User>(userService.readUser(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody UserDTO dto, @PathVariable long id){
        return new ResponseEntity<User>(userService.updateUser(dto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteUser(@PathVariable long id){
        userService.deletUser(id);
        return "User successfully deleted";
    }
}

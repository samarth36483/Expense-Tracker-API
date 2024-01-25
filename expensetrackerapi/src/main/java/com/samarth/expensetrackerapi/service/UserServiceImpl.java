package com.samarth.expensetrackerapi.service;

import com.samarth.expensetrackerapi.dto.UserDTO;
import com.samarth.expensetrackerapi.exceptions.ResourceNotFoundException;
import com.samarth.expensetrackerapi.exceptions.UserAlreadyExistsException;
import com.samarth.expensetrackerapi.models.User;
import com.samarth.expensetrackerapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User addUser(UserDTO user) {
        if(userRepository.existsByEmail(user.getEmail())){
            throw new UserAlreadyExistsException("Email is already registered");
        }
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        return userRepository.save(newUser);
    }

    @Override
    public User readUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found for id " + id));
    }

    @Override
    public User updateUser(UserDTO user, long id) {
        User existUser = readUser(id);
        if(user.getName() != null){
            existUser.setName(user.getName());
        }
        if(user.getEmail() != null){
            existUser.setEmail(user.getEmail());
        }
        if(user.getPassword() != null){
            existUser.setPassword(user.getPassword());
        }
        return userRepository.save(existUser);
    }

    @Override
    public void deletUser(long id) {
        User ouser = readUser(id);
        userRepository.delete(ouser);
    }
}

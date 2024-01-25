package com.samarth.expensetrackerapi.service;

import com.samarth.expensetrackerapi.dto.UserDTO;
import com.samarth.expensetrackerapi.models.User;

public interface UserService {
    User addUser(UserDTO user);

    User readUser(Long id);

    User updateUser(UserDTO dto, long id);

    void deletUser(long id);
}

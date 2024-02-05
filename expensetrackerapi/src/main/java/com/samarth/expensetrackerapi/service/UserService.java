package com.samarth.expensetrackerapi.service;

import com.samarth.expensetrackerapi.dto.RegisterDTO;
import com.samarth.expensetrackerapi.models.User;

public interface UserService {
    User addUser(RegisterDTO user);

    User readUser(Long id);

    User updateUser(RegisterDTO dto, long id);

    void deletUser(long id);
}

package com.samarth.expensetrackerapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {

    @NotBlank(message = "Name can not be blank")
    private String name;

    @NotNull(message = "Email can not be null")
    @Email(message = "Enter a valid email")
    private String email;

    @NotNull(message = "Password can not be null")
    @Size(min = 6, message = "Password should be atleast 5 characters")
    private String password;

}

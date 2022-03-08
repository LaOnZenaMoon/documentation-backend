package me.lozm.user.dto;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class UserLoginRequestDto {

    @NotNull(message = "Email cannot be null")
    @Size(min = 8, message = "Email cannot be less than 8 characters")
    @Email
    private String email;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, max = 16, message = "Password must be grater than 8 characters")
    private String password;

}

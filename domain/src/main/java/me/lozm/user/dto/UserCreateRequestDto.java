package me.lozm.user.dto;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class UserCreateRequestDto {

    @Email
    @NotNull(message = "Email cannot be null")
    @Size(min = 2, message = "Email cannot be less than 2 characters")
    private String email;

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "Name cannot be less than 2 characters")
    private String name;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password must be equal or grater than 8 characters")
    private String password;

}

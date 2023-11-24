package com.challenge_5.challenge_5.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserDto {
    @NotBlank
    @Size(min = 4, message = "must has min 4 characters")
    private String username;

    @NotBlank
    @Email
    private String emailAddress;

    @NotBlank
    @Size(min = 4, message = "must has min 4 characters")
    private String password;

}

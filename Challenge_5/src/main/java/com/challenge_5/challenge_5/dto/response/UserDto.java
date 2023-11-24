package com.challenge_5.challenge_5.dto.response;

import java.util.UUID;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto {
    @Id
    private UUID id;

    @NotNull
    private String username;

    @NotNull
    private String emailAddress;

    // @NotNull
    // private String password;
}

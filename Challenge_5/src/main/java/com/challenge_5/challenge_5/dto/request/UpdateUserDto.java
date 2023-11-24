package com.challenge_5.challenge_5.dto.request;

import java.util.Optional;

import lombok.Data;

@Data
public class UpdateUserDto {
    private Optional<String> username;

    private Optional<String> emailAddress;

    private Optional<String> password;

}

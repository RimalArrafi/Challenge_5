package com.challenge_5.challenge_5.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import com.challenge_5.challenge_5.dto.request.CreateUserDto;
import com.challenge_5.challenge_5.dto.request.UpdateUserDto;
import com.challenge_5.challenge_5.dto.response.UserDto;
import com.challenge_5.challenge_5.entity.User;
import com.challenge_5.challenge_5.exception.ApiException;

public interface UserService {
    public UserDto createUser(CreateUserDto request);

    public UserDto updateUser(UUID userId, UpdateUserDto request) throws ApiException;

    public UserDto getUserById(UUID userId) throws ApiException;

    public UserDto deleteUser(UUID userId) throws ApiException;;

    public List<UserDto> getAllUsers(Specification<User> filterQueries);

}

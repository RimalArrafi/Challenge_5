package com.challenge_5.challenge_5.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge_5.challenge_5.dto.request.CreateUserDto;
import com.challenge_5.challenge_5.dto.request.UpdateUserDto;
import com.challenge_5.challenge_5.dto.response.UserDto;
import com.challenge_5.challenge_5.entity.User;
import com.challenge_5.challenge_5.exception.ApiException;
import com.challenge_5.challenge_5.service.UserService;
import com.challenge_5.challenge_5.utils.ResponseHandler;

import jakarta.persistence.criteria.Predicate;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<Object> createUser(@Valid @RequestBody CreateUserDto request) {
        try {
            UserDto newUser = userService.createUser(request);
            return ResponseHandler.generateResponseSuccess(HttpStatus.OK,
                    "Merchant has successfully created!", newUser);
        } catch (Exception e) {
            return ResponseHandler.generateResponseFailed(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable UUID userId, @Valid @RequestBody UpdateUserDto request) {
        try {
            UserDto editedUser = userService.updateUser(userId, request);
            return ResponseHandler.generateResponseSuccess(HttpStatus.OK,
                    "User has successfully edited!", editedUser);
        } catch (ApiException e) {
            return ResponseHandler.generateResponseFailed(
                    e.getStatus(), e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.generateResponseFailed(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable UUID userId) {
        try {
            UserDto user = userService.getUserById(userId);
            return ResponseHandler.generateResponseSuccess(HttpStatus.OK,
                    "User has successfully fetched!", user);
        } catch (ApiException e) {
            return ResponseHandler.generateResponseFailed(
                    e.getStatus(), e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.generateResponseFailed(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping(name = "Get all users")
    public ResponseEntity<Object> getAllUsers(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String emailAddress) {
        try {
            Specification<User> filterQueries = ((root, query, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();

                if (username != null && !username.isEmpty()) {
                    predicates.add(
                            criteriaBuilder.like(criteriaBuilder.lower(root.get("username")), "%" +
                                    username.toLowerCase() + "%"));
                }
                if (emailAddress != null && !emailAddress.isEmpty()) {
                    predicates.add(
                            criteriaBuilder.like(criteriaBuilder.lower(root.get("emailAddress")), "%"
                                    +
                                    emailAddress.toLowerCase() + "%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            });
            List<UserDto> users = userService.getAllUsers(filterQueries);
            return ResponseHandler.generateResponseSuccess(HttpStatus.OK,
                    "User has successfully fetched!", users);
        } catch (Exception e) {
            return ResponseHandler.generateResponseFailed(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable UUID userId) {
        try {
            UserDto deletedUser = userService.deleteUser(userId);
            return ResponseHandler.generateResponseSuccess(HttpStatus.OK,
                    "User has successfully deleted!", deletedUser);
        } catch (ApiException e) {
            return ResponseHandler.generateResponseFailed(
                    e.getStatus(), e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.generateResponseFailed(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}

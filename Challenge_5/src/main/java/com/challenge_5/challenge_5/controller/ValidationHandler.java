package com.challenge_5.challenge_5.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.challenge_5.challenge_5.utils.ResponseHandler;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class InputError {
    public String field;
    public String message;
}

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<InputError> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            InputError errObj = new InputError(fieldName, message);
            errors.add(errObj);
        });
        return ResponseHandler.generateResponseFailed(HttpStatus.BAD_REQUEST,
                "Value of some fields doesn't match the requirements.",
                errors);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
            TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return ResponseHandler.generateResponseFailed(HttpStatus.BAD_REQUEST,
                "Value of params or body '" + ex.getPropertyName() + "'' should be "
                        + ex.getRequiredType().getSimpleName());
    }

}
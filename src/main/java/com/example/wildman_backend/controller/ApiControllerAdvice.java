package com.example.wildman_backend.controller;

import com.example.wildman_backend.domain.dto.exception.ValidationErrorDto;
import com.example.wildman_backend.domain.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorDto handleValidationException(ValidationException e) {
        return new ValidationErrorDto(e.getFieldErrors());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorDto handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ValidationErrorDto(e.getFieldErrors());
    }

}

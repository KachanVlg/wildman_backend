package com.example.wildman_backend.domain.exception;



public class UserAlreadyExistsException extends RuntimeException {

    private static final String MESSAGE = "User with this username already exists";


    public UserAlreadyExistsException() {
        super(MESSAGE);
    }
}

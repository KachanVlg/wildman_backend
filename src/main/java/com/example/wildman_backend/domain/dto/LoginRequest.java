package com.example.wildman_backend.domain.dto;


import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String password;
}

package com.example.wildman_backend.domain.dto;


import lombok.Data;

@Data
public class CardDto {

    private Long id;
    private String word;
    private String translation;
}

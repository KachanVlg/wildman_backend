package com.example.wildman_backend.domain.dto.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class ValidationErrorDto {
    private Map<String, List<String>> fieldErrors;

    public ValidationErrorDto(List<FieldError> errors) {
        fieldErrors = new HashMap<>();
        errors.forEach(e -> {
            if (fieldErrors.containsKey(e.getField())) {
                List<String> oldErrors = fieldErrors.get(e.getField());
                oldErrors.add(e.getDefaultMessage());
                return;
            }
            List<String> newErrors = new ArrayList<>();
            newErrors.add(e.getDefaultMessage());
            fieldErrors.put(e.getField(), newErrors);
        });
    }
}

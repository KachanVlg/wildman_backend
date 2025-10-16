package com.example.wildman_backend.domain.exception;

import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class ValidationException extends RuntimeException {
    private final Map<String, List<String>> fieldErrors;

    public ValidationException(List<FieldError> errors) {
        fieldErrors = new HashMap<>();
        errors.forEach(e -> {
            if (fieldErrors.containsKey(e.getField())) {
                List<String> oldErrors = new ArrayList<>(fieldErrors.get(e.getField()));
                oldErrors.add(e.getDefaultMessage());
                fieldErrors.put(e.getField(), oldErrors);
            }
            fieldErrors.put(e.getField(), List.of(e.getDefaultMessage()));
        });
    }
}

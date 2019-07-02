package com.wearewaes.demo.exception;

import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Wraps {@link org.springframework.validation.FieldError} messages
 * into a {@link java.util.Map}<field, errorMessage> representation.
 *
 * @author Al-Hamidawi Hani
 */
class InvalidInputException extends RuntimeException {

    private static final long serialVersionUID = -7187504147077702538L;
    private final Map<String, String> messages;

    InvalidInputException(List<FieldError> errors) {
        messages = new HashMap<>();
        for (FieldError fieldError : errors) {
            messages.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

    Map<String, String> getMessages() {
        return messages;
    }
}
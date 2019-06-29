package com.wearewaes.demo.exception;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * Extracts {@link InvalidInputException} convert {@link org.springframework.validation.BindingResult}
 * errors and throws them.
 *
 * @author Hani Al-Hamidawi
 */
public class InvalidInputExceptionExtractor {

    private InvalidInputExceptionExtractor() {
        //hide it
    }

    public static void extractAndThrow(BindingResult bindingResult) {
        List<FieldError> errors = bindingResult.getFieldErrors();
        if (errors != null && !errors.isEmpty()) {
            throw new InvalidInputException(errors);
        }
    }
}
package com.wearewaes.demo.exception;

/**
 * Base exception class for Not Found status code.
 *
 * @author Hani Al-Hamidawi
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message){
        super(message);
    }
}

package com.wearewaes.demo.exception;


/**
 * Base exception class for Bad Request status code.
 *
 * @author Hani Al-Hamidawi
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message){
        super(message);
    }
}
